package com.google.common.base;



import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class MoreConditionsTest {

  @Test
  public void testIsBlank() {
    String[] ss = new String[] {
      null,
      "",
      "  ",
      " \t "
    };
    for (String s : ss) {
      assertTrue(MoreConditions.isBlank(s));
    }
  }
  
  
  @Test
  public void testIsEmpty() {
    assertTrue(MoreConditions.isEmpty(null));
    assertTrue(MoreConditions.isEmpty(new ArrayList()));

    List<String> l = new ArrayList<String>();
    l.add("one");

    assertTrue(!MoreConditions.isEmpty(l));
  }
  
  
  @Test
  public void testHasNulls() {
    assertTrue(MoreConditions.hasNulls(null));

    List<String> l = new ArrayList<String>();
    l.add("one");
    l.add(null);
    l.add("three");

    assertTrue(MoreConditions.hasNulls(l));
  }
    
  @Test
  public void testHasEmpties() {
    assertTrue(MoreConditions.hasEmpties(null));

    List<List<String>> lol = new ArrayList<List<String>>();
    List<String> listOne = new ArrayList<String>();
    listOne.add("one");

    lol.add(listOne);
    assertTrue(!MoreConditions.hasEmpties(lol));

    lol.add(new ArrayList<String>());
    assertTrue(MoreConditions.hasEmpties(lol));

  }
    


}
