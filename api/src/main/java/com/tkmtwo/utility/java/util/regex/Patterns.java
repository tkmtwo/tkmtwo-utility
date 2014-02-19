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

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *
 */
public class Patterns {

  public static final List<Pattern> EMPTY_PATTERNS = Collections.emptyList();
  
  
  
  public static List<Pattern> patternsFromRegexes(final List<String> regexes) {
    return patternsFromRegexes(regexes, 0);
  }
  
  public static List<Pattern> patternsFromRegexes(final List<String> regexes,
                                                  final int flags)
    throws PatternSyntaxException {
    
    if (regexes == null || regexes.isEmpty()) {
      return EMPTY_PATTERNS;
    }
    
    List<Pattern> patterns = new ArrayList<Pattern>(regexes.size());
    for (String regex : regexes) {
      patterns.add(Pattern.compile(regex, flags));
    }
    return patterns;
  }
  
  
  
}

