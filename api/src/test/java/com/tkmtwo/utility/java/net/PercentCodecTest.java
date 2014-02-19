package com.tkmtwo.utility.java.net;

import java.net.URI;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import java.util.BitSet;
import java.util.Map;
import java.nio.charset.Charset;
import java.util.HashMap;

public class PercentCodecTest {
  
  
  private void exerciseCodec(PercentCodec pc, Map<String,String> m)
    throws Exception {

    for (Map.Entry<String,String> me : m.entrySet()) {
      assertEquals(String.format("Failed encoding on %s -> %s%n", me.getKey(), me.getValue()),
                   me.getValue(), pc.encode(me.getKey()));
    }
    
    for (Map.Entry<String,String> me : m.entrySet()) {
      assertEquals(String.format("Failed decoding on %s -> %s%n", me.getKey(), me.getValue()),
                   me.getKey(), pc.decode(me.getValue()));
    }

  }
  
  
  
  @Test
  public void testNullsAndBlanks() {
    PercentCodec pc = new PercentCodec();
    String s = null;

    assertNull(pc.encode(s));
    assertNull(pc.decode(s));

    s = "";
    assertEquals(s, pc.encode(s));
    assertEquals(s, pc.decode(s));
  }

  @Test
  public void morePercent() {
    PercentCodec pc = new PercentCodec();

    try {
      pc.decode("Lorem%2Ipsum");
    } catch (Exception ex) {
      System.out.println("OK: " + ex.getMessage());
    }


    try {
      pc.decode("Lorem%20Ipsum%2");
    } catch (Exception ex) {
      System.out.println("OK: " + ex.getMessage());
    }
  }


  @Test
  public void testUserInfo()
    throws Exception
  {

    Map<String,String> m = new HashMap<String,String>();
    m.put("LoremIpsum", "LoremIpsum");
    m.put("Lorem Ipsum", "Lorem%20Ipsum");

    //unreserved
    m.put("Lorem-Ipsum", "Lorem-Ipsum");
    m.put("Lorem.Ipsum", "Lorem.Ipsum");
    m.put("Lorem_Ipsum", "Lorem_Ipsum");
    m.put("Lorem~Ipsum", "Lorem~Ipsum");

    //gendelims
    m.put("Lorem:Ipsum", "Lorem:Ipsum");   //extra
    m.put("Lorem/Ipsum", "Lorem%2FIpsum");
    m.put("Lorem?Ipsum", "Lorem%3FIpsum");
    m.put("Lorem#Ipsum", "Lorem%23Ipsum");
    m.put("Lorem[Ipsum", "Lorem%5BIpsum");
    m.put("Lorem]Ipsum", "Lorem%5DIpsum");
    m.put("Lorem@Ipsum", "Lorem%40Ipsum");


    //subdelims
    m.put("Lorem!Ipsum", "Lorem!Ipsum");
    m.put("Lorem$Ipsum", "Lorem$Ipsum");
    m.put("Lorem&Ipsum", "Lorem&Ipsum");
    m.put("Lorem'Ipsum", "Lorem'Ipsum");
    m.put("Lorem(Ipsum", "Lorem(Ipsum");
    m.put("Lorem)Ipsum", "Lorem)Ipsum");
    m.put("Lorem*Ipsum", "Lorem*Ipsum");
    m.put("Lorem+Ipsum", "Lorem+Ipsum");
    m.put("Lorem,Ipsum", "Lorem,Ipsum");
    m.put("Lorem;Ipsum", "Lorem;Ipsum");
    m.put("Lorem=Ipsum", "Lorem=Ipsum");


    exerciseCodec(UriCodecs.USER_INFO, m);
  }





  @Test
  public void testPath()
    throws Exception
  {

    Map<String,String> m = new HashMap<String,String>();
    m.put("LoremIpsum", "LoremIpsum");
    m.put("Lorem Ipsum", "Lorem%20Ipsum");

    //unreserved
    m.put("Lorem-Ipsum", "Lorem-Ipsum");
    m.put("Lorem.Ipsum", "Lorem.Ipsum");
    m.put("Lorem_Ipsum", "Lorem_Ipsum");
    m.put("Lorem~Ipsum", "Lorem~Ipsum");

    //gendelims
    m.put("Lorem:Ipsum", "Lorem:Ipsum");   //extra
    m.put("Lorem/Ipsum", "Lorem%2FIpsum");
    m.put("Lorem?Ipsum", "Lorem%3FIpsum");
    m.put("Lorem#Ipsum", "Lorem%23Ipsum");
    m.put("Lorem[Ipsum", "Lorem%5BIpsum");
    m.put("Lorem]Ipsum", "Lorem%5DIpsum");
    m.put("Lorem@Ipsum", "Lorem@Ipsum");   //extra

    //subdelims
    m.put("Lorem!Ipsum", "Lorem!Ipsum");
    m.put("Lorem$Ipsum", "Lorem$Ipsum");
    m.put("Lorem&Ipsum", "Lorem&Ipsum");
    m.put("Lorem'Ipsum", "Lorem'Ipsum");
    m.put("Lorem(Ipsum", "Lorem(Ipsum");
    m.put("Lorem)Ipsum", "Lorem)Ipsum");
    m.put("Lorem*Ipsum", "Lorem*Ipsum");
    m.put("Lorem+Ipsum", "Lorem+Ipsum");
    m.put("Lorem,Ipsum", "Lorem,Ipsum");
    m.put("Lorem;Ipsum", "Lorem;Ipsum");
    m.put("Lorem=Ipsum", "Lorem=Ipsum");

    exerciseCodec(UriCodecs.PATH, m);
  }




  @Test
  public void testQuery()
    throws Exception
  {

    Map<String,String> m = new HashMap<String,String>();
    m.put("LoremIpsum", "LoremIpsum");
    m.put("Lorem Ipsum", "Lorem%20Ipsum");

    //unreserved
    m.put("Lorem-Ipsum", "Lorem-Ipsum");
    m.put("Lorem.Ipsum", "Lorem.Ipsum");
    m.put("Lorem_Ipsum", "Lorem_Ipsum");
    m.put("Lorem~Ipsum", "Lorem~Ipsum");

    //gendelims
    m.put("Lorem:Ipsum", "Lorem:Ipsum");   //extra
    m.put("Lorem/Ipsum", "Lorem/Ipsum");   //extra
    m.put("Lorem?Ipsum", "Lorem?Ipsum");   //extra
    m.put("Lorem#Ipsum", "Lorem%23Ipsum");
    m.put("Lorem[Ipsum", "Lorem%5BIpsum");
    m.put("Lorem]Ipsum", "Lorem%5DIpsum");
    m.put("Lorem@Ipsum", "Lorem@Ipsum");   //extra


    //subdelims
    m.put("Lorem!Ipsum", "Lorem!Ipsum");
    m.put("Lorem$Ipsum", "Lorem$Ipsum");
    m.put("Lorem&Ipsum", "Lorem&Ipsum");
    m.put("Lorem'Ipsum", "Lorem'Ipsum");
    m.put("Lorem(Ipsum", "Lorem(Ipsum");
    m.put("Lorem)Ipsum", "Lorem)Ipsum");
    m.put("Lorem*Ipsum", "Lorem*Ipsum");
    m.put("Lorem+Ipsum", "Lorem+Ipsum");
    m.put("Lorem,Ipsum", "Lorem,Ipsum");
    m.put("Lorem;Ipsum", "Lorem;Ipsum");
    m.put("Lorem=Ipsum", "Lorem=Ipsum");

    exerciseCodec(UriCodecs.QUERY, m);
  }





  @Test
  public void testFragment()
    throws Exception
  {

    Map<String,String> m = new HashMap<String,String>();
    m.put("LoremIpsum", "LoremIpsum");
    m.put("Lorem Ipsum", "Lorem%20Ipsum");

    //unreserved
    m.put("Lorem-Ipsum", "Lorem-Ipsum");
    m.put("Lorem.Ipsum", "Lorem.Ipsum");
    m.put("Lorem_Ipsum", "Lorem_Ipsum");
    m.put("Lorem~Ipsum", "Lorem~Ipsum");

    //gendelims
    m.put("Lorem:Ipsum", "Lorem:Ipsum");   //extra
    m.put("Lorem/Ipsum", "Lorem/Ipsum");   //extra
    m.put("Lorem?Ipsum", "Lorem?Ipsum");   //extra
    m.put("Lorem#Ipsum", "Lorem%23Ipsum");
    m.put("Lorem[Ipsum", "Lorem%5BIpsum");
    m.put("Lorem]Ipsum", "Lorem%5DIpsum");
    m.put("Lorem@Ipsum", "Lorem@Ipsum");   //extra


    //subdelims
    m.put("Lorem!Ipsum", "Lorem!Ipsum");
    m.put("Lorem$Ipsum", "Lorem$Ipsum");
    m.put("Lorem&Ipsum", "Lorem&Ipsum");
    m.put("Lorem'Ipsum", "Lorem'Ipsum");
    m.put("Lorem(Ipsum", "Lorem(Ipsum");
    m.put("Lorem)Ipsum", "Lorem)Ipsum");
    m.put("Lorem*Ipsum", "Lorem*Ipsum");
    m.put("Lorem+Ipsum", "Lorem+Ipsum");
    m.put("Lorem,Ipsum", "Lorem,Ipsum");
    m.put("Lorem;Ipsum", "Lorem;Ipsum");
    m.put("Lorem=Ipsum", "Lorem=Ipsum");

    exerciseCodec(UriCodecs.FRAGMENT, m);
  }


}

/*
    //gendelims
    m.put("Lorem:Ipsum", "Lorem%3AIpsum");
    m.put("Lorem/Ipsum", "Lorem%2FIpsum");
    m.put("Lorem?Ipsum", "Lorem%3FIpsum");
    m.put("Lorem#Ipsum", "Lorem%23Ipsum");
    m.put("Lorem[Ipsum", "Lorem%5BIpsum");
    m.put("Lorem]Ipsum", "Lorem%5DIpsum");
    m.put("Lorem@Ipsum", "Lorem%40Ipsum");

*/
