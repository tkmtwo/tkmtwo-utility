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


import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 */
public class URIs {

  public static URI valueOf(String s) {
    URI uri = null;
    try {
      uri = new URI(s);
      return uri;
    } catch (URISyntaxException ex) {
      throw new IllegalArgumentException("String " + s + " is not a valid URI.", ex);
    }
  }
  
  public static UserInfo getUserInfo(URI uri) {
    if (uri == null) {
      //return new UserInfo();
      return null;
    }
    return parseUserInfo(uri.getUserInfo());
  }
  
  public static UserInfo parseUserInfo(String s) {
    return UserInfo.valueOf(s);
  }
  
  public static String confess(URI uri) {
    StringBuffer sb = new StringBuffer();
    sb
      .append(String.format("BEGIN URI: %s%n", uri.toString()))
      .append(String.format("%25s: %s%n", "isOpague", String.valueOf(uri.isOpaque())))
      .append(String.format("%25s: %s%n", "isAbsolute", String.valueOf(uri.isAbsolute())))
      .append(String.format("%25s: %s%n", "scheme", uri.getScheme()))
      .append(String.format("%25s: %s%n", "scheme-specific-part", uri.getSchemeSpecificPart()))
      .append(String.format("%25s: %s%n", "authority", uri.getAuthority()))
      .append(String.format("%25s: %s%n", "user-info", uri.getUserInfo()))
      .append(String.format("%25s: %s%n", "host", uri.getHost()))
      .append(String.format("%25s: %s%n", "port", String.valueOf(uri.getPort())))
      .append(String.format("%25s: %s%n", "path", uri.getPath()))
      .append(String.format("%25s: %s%n", "query", uri.getQuery()))
      .append(String.format("%25s: %s%n", "fragment", uri.getFragment()))
      .append(String.format("END URI%n"));
    return sb.toString();
  }
      
  
}
