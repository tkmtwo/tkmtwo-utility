/*
 *
 * Copyright 2014 Tom Mahaffey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.tkmtwo.utility.charparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * A simple, single-character parser.
 */
public final class CharParser {

  public static final char DEFAULT_SEPARATOR = ',';
  public static final int DEFAULT_LINE_LENGTH = 10;
  public static final int DEFAULT_LINE_COUNT = 100;
  public static final int DEFAULT_ENFORCE_COLUMNS = -1;
  public static final String DEFAULT_ENCODING = "UTF-8";

  private char charSep;
  private int lineLength;
  private int lineCount;
  private int enforceColumns = DEFAULT_ENFORCE_COLUMNS;
  private String encoding = DEFAULT_ENCODING;


  private Pattern csvPattern;
  private Matcher csvMatcher;

  private Pattern dqPattern;
  private Matcher dqMatcher;


  public CharParser() {
    this(DEFAULT_SEPARATOR,
         DEFAULT_LINE_LENGTH,
         DEFAULT_LINE_COUNT);
  }
  
  public CharParser(char c)
    throws PatternSyntaxException {
    this(c,
         DEFAULT_LINE_LENGTH,
         DEFAULT_LINE_COUNT);
  }
  
  
  public CharParser(char c, int ll, int lc) {
    charSep = c;
    lineLength = ll;
    lineCount = lc;
    compilePattern(c);
  }

  public char getCharSep() { return charSep; }
  public void setCharSep(char c) { charSep = c; }

  public int getLineLength() { return lineLength; }
  public void setLineLength(int i) { lineLength = i; }

  public int getLineCount() { return lineCount; }
  public void setLineCount(int i) { lineCount = i; }

  public int getEnforceColumns() { return enforceColumns; }
  public void setEnforceColumns(int i) { enforceColumns = i; }
  
  public String getEncoding() { return encoding; }
  public void setEncoding(String s) { encoding = s; }
  
  private void compilePattern(char c)
    throws PatternSyntaxException {

    StringBuffer sb = new StringBuffer();


    sb
      .append("  \\G(?:^|").append(Pattern.quote(String.valueOf(c))).append(")       \n")
      .append("  (?:                                                                 \n")
      .append("     # Either a double-quoted field...                                \n")
      .append("     \" #field's opening double-quote                                 \n")
      .append(" (  [^\"]*+    (?: \"\"   [^\"]*+ ) *+)                               \n")
      .append("     \" #field's closing double-quote                                 \n")
      .append("  |                                                                   \n")
      .append("     # some non-quoted/non-comma text...                              \n")
      .append("     ( [^\"").append(Pattern.quote(String.valueOf(c))).append("]*+ )  \n")
      .append("  )");

    //warm it up, Chris...
    csvPattern = Pattern.compile(sb.toString(), Pattern.COMMENTS);
    csvMatcher = csvPattern.matcher("");
    
    dqPattern = Pattern.compile("\"\"");
    dqMatcher = dqPattern.matcher("");
  }
  
  
  
  public List<String> parse(String s) {
    if (s == null || s.length() < 1) {
      return new ArrayList<String>();
    }
    
    List<String> l = new ArrayList<String>(getLineLength());

    /*
      Sure wish I could do this in the regex...TODO for later excercise?
     */
    String matchString = s;
    if (s.startsWith(String.valueOf(getCharSep()))) {
      l.add("");
      matchString = s.substring(1);
    }


    csvMatcher.reset(matchString);
    while (csvMatcher.find()) {
      String field = null;
      String first = csvMatcher.group(2); //2
      
      if (first != null) {
        field = first;
      } else {
        dqMatcher.reset(csvMatcher.group(1)); //1
        field = dqMatcher.replaceAll("\"");
      }
      
      l.add(field);
    }
    
    if (getEnforceColumns() > 0) {
      if (l.size() !=  getEnforceColumns()) {
        throw new NumberOfColumnsException("Number of columns expected was "
                                           + String.valueOf(getEnforceColumns())
                                           + " but parsed " + String.valueOf(l.size()),
                                           getEnforceColumns(), l.size());
      }
    }
    
    
    return l;
  }
  
  
  public List<List<String>> read(String s) {
    return read(s, 0, -1);
  }
  public List<List<String>> read(String s, int skipCount) {
    return read(new File(s), skipCount, -1);
  }
  public List<List<String>> read(String s, int skipCount, int readCount) {
    return read(new File(s), skipCount, readCount);
  }


  public List<List<String>> read(File f) {
    return read(f, 0, -1);
  }
  public List<List<String>> read(File f, int skipCount) {
    return read(f, skipCount, -1);
  }
  
  public List<List<String>> read(File f, int skipCount, int readCount) {
    List<List<String>> l = null;
    try (FileInputStream fis = new FileInputStream(f)) {
      l = read(fis, skipCount, readCount);
    } catch (IOException ioex) {
      throw new CharParserException("Error reading.", ioex);
    }
    return l;
  }


  public List<List<String>> read(InputStream is) {
    return read(is, 0, -1);
  }
  public List<List<String>> read(InputStream is, int skipCount) {
    return read(is, 0, -1);
  }
  public List<List<String>> read(InputStream is, int skipCount, int readCount) {

    List<List<String>> ls = new ArrayList<List<String>>(getLineCount());

    String l = null;
    
    int linesRead = 0;
    int readCountRead = 0;

    try (BufferedReader br = new BufferedReader(new InputStreamReader(is, getEncoding()))) {
      while ((l = br.readLine()) != null) {
        linesRead++;
        if (linesRead <= skipCount) { continue; }
      
        readCountRead++;
        if (readCount > 0
            && readCountRead > readCount) {
          break;
        }
      
        if (l.startsWith("#")) { continue; }
        ls.add(parse(l));
      }
      
      br.close();
      
    } catch (IOException ioex) {
      throw new CharParserException("Error reading.", ioex);
    }
    
    return ls;
  }

  
  
  public String join(List<String> l) {
    return join((String[]) l.toArray(new String[l.size()]));
  }
  
  public String join(String s[]) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.length; i++) {
      sb.append("\"").append(s[i]).append("\"");
      if (i < s.length - 1) {
        sb.append(getCharSep());
      }
    }
    
    return sb.toString();
  }
  
  public String get(List<String> l, int i) {
    if (l == null
        || i < 0
        || i >= l.size()) {
      return "";
    }
    return l.get(i);
  }


                                
    
  
}
