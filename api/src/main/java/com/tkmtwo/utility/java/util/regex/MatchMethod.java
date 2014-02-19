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


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;



/**
 * Enumeration for different forms of pattern matching.
 *
 * @author Tom Mahaffey
 */
public enum MatchMethod {
  
  /** Match using matches(). */
  MATCHES {
    public boolean matches(final Matcher matcher) {
      if (matcher == null) { return false; }
      return matcher.matches();
    }
  },
  /** Match using lookingAt(). */
  LOOKINGAT {
    public boolean matches(final Matcher matcher) {
      if (matcher == null) { return false; }
      return matcher.lookingAt();
    }
  },
  /** Match using find(). */
  FIND {
    public boolean matches(final Matcher matcher) {
      if (matcher == null) { return false; }
      return matcher.find();
    }
  };
  
  
  
  /**
   * Tests regular expression matching.
   *
   * The regex is compiled with flags == 0.
   *
   *
   * @param regex a <code>String</code> representing a regex
   * @param charSequence a <code>CharSequence</code> to match against
   * @return a <code>boolean</code>
   */
  public boolean matches(final String regex,
                         final CharSequence charSequence)
  throws PatternSyntaxException {
    return matches(regex, 0, charSequence);
  }
  
  
  /**
   * Tests regular expression matching.
   *
   *
   * @param regex a <code>String</code> representing a regex
   * @param flags a <code>int</code> representing <code>Pattern</code> flags
   * @param charSequence a <code>CharSequence</code> to match against
   * @return a <code>boolean</code>
   */
  public boolean matches(final String regex,
                         final int flags,
                         final CharSequence charSequence)
  throws PatternSyntaxException {
    
    Pattern pattern = Pattern.compile(regex, flags);
    return matches(pattern, charSequence);
  }
  
  
  /**
   * Tests regular expression matching.
   *
   *
   * @param pattern a precompiled <code>Pattern</code>
   * @param charSequence a <code>CharSequence</code> to match against
   * @return a <code>boolean</code>
   */
  public boolean matches(final Pattern pattern,
                         final CharSequence charSequence) {
    Matcher matcher = pattern.matcher(charSequence);
    return matches(matcher);
  }
  
  
  /**
   * Tests regular expression matching.
   *
   * Return the first <code>Matcher</code> that matchers.  Otherwise,
   * returns null.
   *
   *
   * @param matchers a <code>List</code> of <code>Matcher</code> objects.
   * @return a <code>Matcher</code>
   */
  public Matcher matches(final List<Matcher> matchers) {
    if (matchers == null || matchers.isEmpty()) {
      return null;
    }
    for (Matcher m : matchers) {
      if (matches(m)) {
        return m;
      }
    }
    return null;
  }
  
  
  /**
   * Tests regular expression matching.
   *
   * @param matcher a <code>Matcher</code>
   * @return a <code>boolean</code>
   */
  public abstract boolean matches(Matcher matcher);
  
  
  
}
