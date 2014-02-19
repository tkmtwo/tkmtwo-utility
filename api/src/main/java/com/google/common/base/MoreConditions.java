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
package com.google.common.base;


import java.util.Collection;


/**
 * Additional check conditions based on same style as Guava's <code>Precondition</code>s.
 *
 *
 */
public final class MoreConditions {

  /**
   * Tell if the string is null, empty, or all whitespace.
   *
   * @param s a String
   * @return boolean telling if the string is "blank" or not
   */
  public static boolean isBlank(final String s) {
    return s == null || s.isEmpty() || s.trim().isEmpty();
  }

  /**
   * Ensures that a string is not blank.
   *
   * @param s a String
   * @return the non-blank reference that was validated
   * @throws IllegalArgumentException if {@code s} is blank
   */
  public static String checkNotBlank(String s) {
    return checkNotBlank(s, "Value can not be blank.");
  }

  /**
   * Ensures that a string is not blank.
   *
   *
   * @param s a String
   * @param msg the exception message to use if the check fails; will be converted to a
   *     string using {@link String#valueOf(Object)}
   * @return the non-blank reference that was validated
   * @throws IllegalArgumentException if {@code s} is blank
   */
  public static String checkNotBlank(String s, Object msg) {
    if (isBlank(s)) {
      throw new IllegalArgumentException(String.valueOf(msg));
    }
    return s;
  }

  /**
   * Ensures that a string is not blank.
   *
   *
   * @param s a String
   * @param msgTemplate a template for the exception message should the check fail. The
   *     message is formed by replacing each {@code %s} placeholder in the template with an
   *     argument. These are matched by position - the first {@code %s} gets {@code
   *     msgArgs[0]}, etc.  Unmatched arguments will be appended to the formatted message
   *     in square braces. Unmatched placeholders will be left as-is.
   * @param msgArgs the arguments to be substituted into the message template. Arguments
   *     are converted to strings using {@link String#valueOf(Object)}.
   * @return the non-blank reference that was validated
   * @throws IllegalArgumentException if {@code s} is blank
   */
  public static String checkNotBlank(String s, String msgTemplate, Object... msgArgs) {
    if (isBlank(s)) {
      throw new IllegalArgumentException(Preconditions.format(msgTemplate, msgArgs));
    }
    return s;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  /**
   * Tell if a Collection is empty.
   *
   * @param c a Collection
   * @return boolean telling if the Collection is empty or not
   */
  public static boolean isEmpty(Collection c) {
    return c == null || c.isEmpty();
  }
  
  
  /**
   * Ensures that a Collection is not empty.
   *
   * @param c a Collection
   * @return the non-empty reference that was validated
   * @throws IllegalArgumentException if {@code c} is empty
   */
  public static Collection checkNotEmpty(Collection c) {
    return checkNotEmpty(c, "Collection is empty.");
  }
  
  
  /**
   * Ensures that a Collection is not empty.
   *
   *
   * @param c a Collection
   * @param msg the exception message to use if the check fails; will be converted to a
   *     string using {@link String#valueOf(Object)}
   * @return the non-empty reference that was validated
   * @throws IllegalArgumentException if {@code c} is empty
   */
  public static Collection checkNotEmpty(Collection c, Object msg) {
    if (isEmpty(c)) {
      throw new IllegalArgumentException(String.valueOf(msg));
    }
    return c;
  }
  
  
  /**
   * Ensures that a Collection is not empty.
   *
   *
   * @param c a Collection
   * @param msgTemplate a template for the exception message should the check fail. The
   *     message is formed by replacing each {@code %s} placeholder in the template with an
   *     argument. These are matched by position - the first {@code %s} gets {@code
   *     msgArgs[0]}, etc.  Unmatched arguments will be appended to the formatted message
   *     in square braces. Unmatched placeholders will be left as-is.
   * @param msgArgs the arguments to be substituted into the message template. Arguments
   *     are converted to strings using {@link String#valueOf(Object)}.
   * @return the non-empty reference that was validated
   * @throws IllegalArgumentException if {@code c} is empty
   */
  public static Collection checkNotEmpty(Collection c, String msgTemplate, Object... msgArgs) {
    if (isEmpty(c)) {
      throw new IllegalArgumentException(Preconditions.format(msgTemplate, msgArgs));
    }
    return c;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  /**
   * Tell if a Collection has nulls.
   *
   * @param c a Collection
   * @return boolean telling if the Collection has nulls or not
   */
  public static boolean hasNulls(Collection c) {
    if (c == null) { return true; }
    for (Object o : c) {
      if (o == null) { return true; }
    }
    return false;
  }
  
  
  
  /**
   * Ensures that a Collection has no nulls.
   *
   * @param c a Collection
   * @return the non-null-having reference that was validated
   * @throws IllegalArgumentException if {@code c} has nulls
   */
  public static Collection checkNoNulls(Collection c) {
    return checkNoNulls(c, "Collection is empty.");
  }
  
  
  
  
  /**
   * Ensures that a Collection has no nulls.
   *
   * @param c a Collection
   * @param msg the exception message to use if the check fails; will be converted to a
   *     string using {@link String#valueOf(Object)}
   * @return the non-null-having reference that was validated
   * @throws IllegalArgumentException if {@code c} has nulls
   */
  public static Collection checkNoNulls(Collection c, Object msg) {
    if (hasNulls(c)) {
      throw new IllegalArgumentException(String.valueOf(msg));
    }
    
    return c;
  }
  
  
  /**
   * Ensures that a Collection has no nulls.
   *
   * @param c a Collection
   * @param msgTemplate a template for the exception message should the check fail. The
   *     message is formed by replacing each {@code %s} placeholder in the template with an
   *     argument. These are matched by position - the first {@code %s} gets {@code
   *     msgArgs[0]}, etc.  Unmatched arguments will be appended to the formatted message
   *     in square braces. Unmatched placeholders will be left as-is.
   * @param msgArgs the arguments to be substituted into the message template. Arguments
   *     are converted to strings using {@link String#valueOf(Object)}.
   * @return the non-null-having reference that was validated
   * @throws IllegalArgumentException if {@code c} has nulls
   */
  public static Collection checkNoNulls(Collection c, String msgTemplate, Object... msgArgs) {
    if (hasNulls(c)) {
      throw new IllegalArgumentException(Preconditions.format(msgTemplate, msgArgs));
    }
    
    return c;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  /**
   * Tell if a Collection of Collections has empty Collections.
   *
   * @param c a Collection
   * @return boolean telling if the Collection has empties or not
   */
  public static boolean hasEmpties(Collection<? extends Collection> c) {
    if (c == null) { return true; }
    for (Collection o : c) {
      if (o == null) { return true; }
      if (o.isEmpty()) { return true; }
    }
    return false;
  }
  
  
  /**
   * Ensures that a Collection of Collections has no empty Collections.
   *
   * @param c a Collection
   * @return the non-empty-having reference that was validated
   * @throws IllegalArgumentException if {@code c} has empty Collections
   */
  public static Collection checkNoEmpties(Collection<? extends Collection> c) {
    return checkNoEmpties(c, "Collection is empty.");
  }
  
  
  /**
   * Ensures that a Collection of Collections has no empty Collections.
   *
   * @param c a Collection
   * @param msg the exception message to use if the check fails; will be converted to a
   *     string using {@link String#valueOf(Object)}
   * @return the non-empty-having reference that was validated
   * @throws IllegalArgumentException if {@code c} has empty Collections
   */
  public static Collection checkNoEmpties(Collection<? extends Collection> c, Object msg) {
    if (hasEmpties(c)) {
      throw new IllegalArgumentException(String.valueOf(msg));
    }
    return c;
  }
  
  
  /**
   * Ensures that a Collection of Collections has no empty Collections.
   *
   * @param c a Collection
   * @param msgTemplate a template for the exception message should the check fail. The
   *     message is formed by replacing each {@code %s} placeholder in the template with an
   *     argument. These are matched by position - the first {@code %s} gets {@code
   *     msgArgs[0]}, etc.  Unmatched arguments will be appended to the formatted message
   *     in square braces. Unmatched placeholders will be left as-is.
   * @param msgArgs the arguments to be substituted into the message template. Arguments
   *     are converted to strings using {@link String#valueOf(Object)}.
   * @return the non-empty-having reference that was validated
   * @throws IllegalArgumentException if {@code c} has empty Collections
   */
  public static Collection checkNoEmpties(Collection<? extends Collection> c,
                                          String msgTemplate, Object... msgArgs) {
    if (hasEmpties(c)) {
      throw new IllegalArgumentException(Preconditions.format(msgTemplate, msgArgs));
    }
    return c;
  }



  
  


}
