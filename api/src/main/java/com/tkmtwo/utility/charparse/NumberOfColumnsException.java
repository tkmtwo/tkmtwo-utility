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
package com.tkmtwo.utility.charparse;

/**
 * Custom exception.
 */
public class NumberOfColumnsException
  extends RuntimeException {

  private int columnsExpected;
  private int columnsReceived;

  public NumberOfColumnsException() { ; }
  
  public NumberOfColumnsException(String s, int e, int r) {
    super(s);
    columnsExpected = e;
    columnsReceived = r;
  }
  
  public NumberOfColumnsException(String s, Throwable t, int e, int r) {
    super(s, t);
    columnsExpected = e;
    columnsReceived = r;
  }
  
  public NumberOfColumnsException(Throwable t, int e, int r) {
    super(t);
    columnsExpected = e;
    columnsReceived = r;
  }
  
  public int getColumnsExpected() { return columnsExpected; }
  public int getColumnsReceived() { return columnsReceived; }
  
}
