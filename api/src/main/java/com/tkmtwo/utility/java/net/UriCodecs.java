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
package com.tkmtwo.utility.java.net;

import java.util.BitSet;


/**
 * Handy <code>PercentCodec</code>s for URI parts.
 *
 */
public final class UriCodecs {
  
  public static final PercentCodec USER_INFO = new PercentCodec(getUserInfoBitSet());
  public static final PercentCodec PATH = new PercentCodec(getPathBitSet());
  public static final PercentCodec QUERY = new PercentCodec(getQueryBitSet());
  public static final PercentCodec FRAGMENT = new PercentCodec(getFragmentBitSet());
  



  private static final BitSet getAlphaBitSet() {
    BitSet bs = new BitSet(256);
    for (int i = 'a'; i <= 'z'; i++) {
      bs.set(i);
    }
    for (int i = 'A'; i <= 'Z'; i++) {
      bs.set(i);
    }
    
    return bs;
  }    
  
  
  private static final BitSet getDigitBitSet() {
    BitSet bs = new BitSet(256);
    for (int i = '0'; i <= '9'; i++) {
      bs.set(i);
    }
    
    return bs;
  }
  
  private static final BitSet getUnreservedBitSet() {
    BitSet bs = new BitSet(256);
    bs.or(getAlphaBitSet());
    bs.or(getDigitBitSet());
    bs.set('-');
    bs.set('.');
    bs.set('_');
    bs.set('~');
    return bs;
  }

  /*  
  private static final BitSet getReservedBitSet() {
    BitSet bs = new BitSet();
    bs.or(getGenDelimsBitSet());
    bs.or(getSubDelimsBitSet());
    return bs;
  }
  
  private static final BitSet getGenDelimsBitSet() {
    BitSet bs = new BitSet();
    bs.set(':');
    bs.set('/');
    bs.set('?');
    bs.set('#');
    bs.set('[');
    bs.set(']');
    bs.set('@');
    return bs;
  };
  */


  private static final BitSet getSubDelimsBitSet() {
    BitSet bs = new BitSet();
    bs.set('!');
    bs.set('$');
    bs.set('&');
    bs.set('\'');
    bs.set('(');
    bs.set(')');
    bs.set('*');
    bs.set('+');
    bs.set(',');
    bs.set(';');
    bs.set('=');
    return bs;
  };

  private static final BitSet getUserInfoBitSet() {
    BitSet bs = new BitSet();
    bs.or(getUnreservedBitSet());
    bs.or(getSubDelimsBitSet());
    bs.set(':');
    return bs;
  }
  private static final BitSet getPathBitSet() {
    BitSet bs = new BitSet();
    bs.or(getUnreservedBitSet());
    bs.or(getSubDelimsBitSet());
    bs.set(':');
    bs.set('@');
    return bs;
  }
  private static final BitSet getQueryBitSet() {
    BitSet bs = new BitSet();
    bs.or(getUnreservedBitSet());
    bs.or(getSubDelimsBitSet());
    bs.set(':');
    bs.set('@');
    bs.set('/');
    bs.set('?');
    return bs;
  }
  
  private static final BitSet getFragmentBitSet() {
    BitSet bs = new BitSet();
    bs.or(getUnreservedBitSet());
    bs.or(getSubDelimsBitSet());
    bs.set(':');
    bs.set('@');
    bs.set('/');
    bs.set('?');
    return bs;
  }
    

    
}
