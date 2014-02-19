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
package com.tkmtwo.utility.java.util.regex;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;




/**
 * Utility class for Matcher objects.
 *
 * @author Tom Mahaffey
 * @version $Id$
 */
public class Matchers {
  
  /** An empty List of Matchers. */
  public static final List<Matcher> EMPTY_MATCHERS = Collections.emptyList();
  
  
  
  /**
   * Creates Matchers from String representations of regexes.
   *
   * @param regexes - List of Strings to compile into Patterns.
   * @param charSequence - the CharSequence to match against.
   * @return List<Matcher> - Matcher objects
   */
  public static List<Matcher> matchersFromRegexes(final List<String> regexes,
                                                  final CharSequence charSequence)
    throws PatternSyntaxException {
    List<Pattern> patterns = Patterns.patternsFromRegexes(regexes);
    return matchersFromPatterns(patterns, charSequence);
  }
  
  
  /**
   * Creates Matchers from compiled Patterns.
   *
   * @param patterns - List of Patterns
   * @param charSequence - the CharSequence to match against.
   * @return List<Matcher> - Matcher objects
   */
  public static List<Matcher> matchersFromPatterns(final List<Pattern> patterns,
                                                   final CharSequence charSequence) {
    if (patterns == null || patterns.isEmpty()) {
      return EMPTY_MATCHERS;
    }
    
    List<Matcher> matchers = new ArrayList<Matcher>(patterns.size());
    for (Pattern pattern : patterns) {
      matchers.add(pattern.matcher(charSequence));
    }
    
    return matchers;
  }

  public static String reportGroupMatches(Matcher m) {
    return reportGroupMatches(m, System.getProperty("line.separator"));
  }
  public static String reportGroupMatches(Matcher m, String separator) {
    if (m == null) { return null; }

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i <= m.groupCount(); i++) {
      sb
        .append(String.valueOf(i) + ": " + m.group(i))
        .append(separator);
    }
    return sb.toString();
  }



  public static List<String> findAllMatches(String s, Pattern p) {
    List<String> matches = new ArrayList<String>();
    Matcher m = p.matcher(s);
    while (m.find()) {
      matches.add(m.group());
    }
    return matches;
  }


  
}

