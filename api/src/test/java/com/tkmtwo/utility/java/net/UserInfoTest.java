package com.tkmtwo.utility.java.net;

import java.net.URI;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;


public class UserInfoTest {

  @Test
  public void testValid()
  {
    UserInfo ui = UserInfo.valueOf("user:pass");
    assertEquals("user", ui.getUserName());
    assertEquals("pass", ui.getPassword());
  }
  
  //@Test
  public void testDefault()
  {
    UserInfo ui = UserInfo.valueOf((String) null);
    assertNull(ui.getUserName());
    assertNull(ui.getPassword());
  }

  //@Test
  public void testNull()
  {
    String s = null;
    UserInfo ui = UserInfo.valueOf(s);
    assertNull(ui.getUserName());
    assertNull(ui.getPassword());
  }

  //@Test
  public void testBlank()
  {
    String s = "";
    UserInfo ui = UserInfo.valueOf(s);
    assertNull(ui.getUserName());
    assertNull(ui.getPassword());
  }

  
  //@Test
  public void testNoColon()
  {
    UserInfo ui = UserInfo.valueOf("user");
    assertEquals("user", ui.getUserName());
    assertNull(ui.getPassword());
  }
  
    
  

}

