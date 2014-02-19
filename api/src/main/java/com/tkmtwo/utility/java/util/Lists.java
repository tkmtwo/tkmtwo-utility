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
package com.tkmtwo.utility.java.util;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.apache.commons.collections.CollectionUtils;





/**
 * Utility methods for Lists.
 *
 * @author Tom Mahaffey
 * @since 1.0
 */
public final class Lists {

  private static <T> void assertNonEmptySourceLists(List<List<T>> srcLists) {
    //if (srcLists == null || srcLists.isEmpty()) {
    if (srcLists == null) {
      throw new IllegalArgumentException("Source lists is empty.");
    }
    for (int i = 0; i < srcLists.size(); i++) {
      List<T> srcList = srcLists.get(i);
      //if (srcList == null || srcList.isEmpty()) {
      if (srcList == null) {
        throw new IllegalArgumentException("Source list at " + String.valueOf(i) + " is empty.");
      }
      for (int j = 0; j < srcList.size(); j++) {
        T t = srcList.get(j);
        if (t == null) {
          throw new IllegalArgumentException("Element at "
                                             + String.valueOf(j)
                                             + "in source list at "
                                             + String.valueOf(i)
                                             + " is empty.");
        }
      }
    }
  }
  
  
  
  /**
   * Get all combinations of a list of lists, in order.
   *
   *
   *<p>This example:
   *
   * <pre>
   * {@code
   *   List<String> listA = new ArrayList<String>();
   *   listA.add("A1");
   *   listA.add("A2");
   *    
   *   List<String> listB = new ArrayList<String>();
   *   listB.add("B1");
   *   listB.add("B2");
   *   listB.add("B3");
   *    
   *   List<List<String>> srcLists = new ArrayList<List<String>>();
   *   srcLists.add(listA);
   *   srcLists.add(listB);
   *    
   *   List<List<String>> combos = Lists.combinate(srcLists);
   *    
   *   for (List<String> combo : combos) {
   *     System.out.println(StringUtils.join(combo.iterator(), ", "));
   *   }
   * }
   *</pre>
   *
   *
   *<p>Prints this output:
   *
   * <p>
   * <pre>
   *  A1, B1
   *  A1, B2
   *  A1, B3
   *  A2, B1
   *  A2, B2
   *  A2, B3
   * </pre>
   *
   *
   *
   */  
  public static <T> List<List<T>> combinate(List<List<T>> srcLists) {
    checkNotNull(srcLists, "Source lists is null.");

    List<List<T>> dstLists = new ArrayList<List<T>>();

    if (srcLists.isEmpty()) { return dstLists; }

    for (int i = 0; i < srcLists.size(); i++) {
      List<T> l = srcLists.get(i);
      checkNotNull(l, "List at position %s is null.", String.valueOf(i));
      if (l.isEmpty()) {
        return dstLists;
      }
    }
    
    
    combinateRecurse(srcLists, dstLists, new ArrayList<T>(), 0);
    return dstLists;
  }


  /* TODO(mahaffey): isn't there a more elegant way? */  
  private static <T> void combinateRecurse(List<List<T>> srcLists,
                                           List<List<T>> dstLists,
                                           List<T> curList,
                                           int listDepth) {
    if (listDepth == srcLists.size()) {
      dstLists.add(curList);
      return;
    }
    for (int k = 0; k < srcLists.get(listDepth).size(); k++) {
      //T t = srcLists.get(listDepth).get(k);
      T t = checkNotNull(srcLists.get(listDepth).get(k),
                         "Element at list {} element {} is null.",
                         String.valueOf(listDepth),
                         String.valueOf(k));
      List<T> ts = new ArrayList<T>(curList);
      ts.add(t);
      combinateRecurse(srcLists, dstLists, ts, listDepth + 1);
    }
  }




  
  












  /**
   * Transform all combinations of a list of lists, in order, and
   * add names.
   *
   *<p>This example:
   *
   * <pre>
   * {@code
   *    List<String> listA = new ArrayList<String>();
   *    listA.add("A1");
   *    listA.add("A2");
   *    
   *    List<String> listB = new ArrayList<String>();
   *    listB.add("B1");
   *    listB.add("B2");
   *    listB.add("B3");
   *    
   *    List<List<String>> srcLists = new ArrayList<List<String>>();
   *    srcLists.add(listA);
   *    srcLists.add(listB);
   *    
   *    List<String> srcNames = new ArrayList<String>();
   *    srcNames.add("valueA");
   *    srcNames.add("valueB");
   *    
   *    List<Map<String,String>> maps = Lists.mapinate(srcLists,
   *                                                   srcNames);
   *    
   *    for (int i = 0; i < maps.size(); i++) {
   *      Map<String,String> map = maps.get(i);
   *      System.out.println("Map " + String.valueOf(i));
   *      for (String srcName : srcNames) {
   *        System.out.println("  " + srcName + " -> " + map.get(srcName));
   *      }
   *    }
   * }
   * </pre>
   *
   * <p>Prints:
   *
   * <pre>
   * Map 0 has 2 pairs.
   *   valueA -> A1
   *   valueB -> B1
   * Map 1 has 2 pairs.
   *   valueA -> A1
   *   valueB -> B2
   * Map 2 has 2 pairs.
   *   valueA -> A1
   *   valueB -> B3
   * Map 3 has 2 pairs.
   *   valueA -> A2
   *   valueB -> B1
   * Map 4 has 2 pairs.
   *   valueA -> A2
   *   valueB -> B2
   * Map 5 has 2 pairs.
   *   valueA -> A2
   *   valueB -> B3
   * </pre>
   *
   *
   */
  public static <T> List<Map<String, T>> mapinate(List<List<T>> srcLists,
                                                 List<String> srcNames) {
    assertNonEmptySourceLists(srcLists);
    if (srcNames == null || srcNames.isEmpty()) {
      throw new IllegalArgumentException("Source names is empty.");
    }
    if (srcNames.size() != srcLists.size()) {
      throw new IllegalArgumentException("There are "
                                         + String.valueOf(srcLists.size())
                                         + " source lists, but "
                                         + String.valueOf(srcNames.size())
                                         + " names.");
    }
    
    
    
    List<Map<String, T>> dstMaps = new ArrayList<Map<String, T>>();
    mapinateRecurse(srcLists, srcNames, dstMaps, new HashMap<String, T>(), 0);
    return dstMaps;
  }
  private static <T> void mapinateRecurse(List<List<T>> srcLists,
                                          List<String> srcNames,
                                          List<Map<String, T>> dstMaps,
                                          Map<String, T> curMap,
                                          int listDepth) {
    if (listDepth == srcLists.size()) {
      dstMaps.add(curMap);
      return;
    }
    for (int k = 0; k < srcLists.get(listDepth).size(); k++) {
      String name = srcNames.get(listDepth);
      T t = srcLists.get(listDepth).get(k);
      Map<String, T> tm = new HashMap<String, T>(curMap);
      tm.put(name, t);
      mapinateRecurse(srcLists, srcNames, dstMaps, tm, listDepth + 1);
    }
  }

  
  
}


