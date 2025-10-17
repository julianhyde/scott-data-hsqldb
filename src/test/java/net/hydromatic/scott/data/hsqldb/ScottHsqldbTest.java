/*
 * Licensed to Julian Hyde under one or more contributor license
 * agreements.  See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership. Julian Hyde
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hydromatic.scott.data.hsqldb;

import static org.junit.Assert.assertEquals;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

/** Kick the tires */
public class ScottHsqldbTest {
  @Test
  public void test() throws SQLException {
    final Connection connection =
        DriverManager.getConnection(
            ScottHsqldb.URI, ScottHsqldb.USER, ScottHsqldb.PASSWORD);
    final Statement statement = connection.createStatement();
    foo(statement.executeQuery("select * from EMP"));
    foo(statement.executeQuery("select * from DEPT"));
    foo(statement.executeQuery("select * from SALGRADE"));
    foo(statement.executeQuery("select * from BONUS"));
    statement.close();
    connection.close();
  }

  @Test
  public void testTableNames() {
    final List<String> tableNames = ScottHsqldb.tableNames();
    assertEquals(4, tableNames.size());
    assertEquals("DEPT", tableNames.get(0));
    assertEquals("EMP", tableNames.get(1));
    assertEquals("BONUS", tableNames.get(2));
    assertEquals("SALGRADE", tableNames.get(3));
  }

  @Test
  public void testTableUri() {
    assertEquals(
        "/csv/dept.csv,/csv/emp.csv,/csv/bonus.csv,/csv/salgrade.csv",
        ScottHsqldb.tableNames().stream()
            .map(ScottHsqldb::tableUri)
            .collect(Collectors.joining(",")));
  }

  @Test
  public void testRowCounts() throws SQLException {
    final Connection connection =
        DriverManager.getConnection(
            ScottHsqldb.URI, ScottHsqldb.USER, ScottHsqldb.PASSWORD);
    final Statement statement = connection.createStatement();

    // Verify row counts for each table
    assertEquals(4, getRowCount(statement, "DEPT"));
    assertEquals(14, getRowCount(statement, "EMP"));
    assertEquals(0, getRowCount(statement, "BONUS"));
    assertEquals(5, getRowCount(statement, "SALGRADE"));

    statement.close();
    connection.close();
  }

  @Test
  public void testGenerateInserts() {
    final Iterable<String> statements = ScottHsqldb.generateInserts();
    int count = 0;
    int deptCount = 0;
    int empCount = 0;
    int salgradeCount = 0;
    boolean foundDept40 = false;
    boolean foundEmp7839 = false;
    boolean foundSalgrade1 = false;

    for (String stmt : statements) {
      count++;
      // Verify each statement is an INSERT
      assertEquals(
          "Statement should start with INSERT INTO",
          true,
          stmt.startsWith("INSERT INTO"));

      // Count statements by table
      if (stmt.contains("\"DEPT\"")) {
        deptCount++;
      } else if (stmt.contains("\"EMP\"")) {
        empCount++;
      } else if (stmt.contains("\"SALGRADE\"")) {
        salgradeCount++;
      }

      // Check for specific statements
      if (stmt.equals(
          "INSERT INTO \"DEPT\" VALUES(40,'OPERATIONS','BOSTON')")) {
        foundDept40 = true;
      }
      if (stmt.equals(
          "INSERT INTO \"EMP\" VALUES(7839,'KING','PRESIDENT',NULL,'1981-11-17',5000.00,NULL,10)")) {
        foundEmp7839 = true;
      }
      if (stmt.equals("INSERT INTO \"SALGRADE\" VALUES(1,700.00,1200.00)")) {
        foundSalgrade1 = true;
      }
    }

    // Verify total count and counts per table
    assertEquals(23, count); // 4 DEPT + 14 EMP + 5 SALGRADE
    assertEquals(4, deptCount);
    assertEquals(14, empCount);
    assertEquals(5, salgradeCount);

    // Verify specific statements exist
    assertEquals(
        "Should contain INSERT INTO \"DEPT\" VALUES(40,'OPERATIONS','BOSTON')",
        true,
        foundDept40);
    assertEquals(
        "Should contain INSERT INTO \"EMP\" VALUES(7839,'KING','PRESIDENT',NULL,'1981-11-17',5000.00,NULL,10)",
        true,
        foundEmp7839);
    assertEquals(
        "Should contain INSERT INTO \"SALGRADE\" VALUES(1,700.00,1200.00)",
        true,
        foundSalgrade1);
  }

  private int getRowCount(Statement statement, String tableName)
      throws SQLException {
    final ResultSet rs =
        statement.executeQuery("SELECT COUNT(*) FROM " + tableName);
    rs.next();
    final int count = rs.getInt(1);
    rs.close();
    return count;
  }

  private void foo(ResultSet resultSet) throws SQLException {
    final ResultSetMetaData metaData = resultSet.getMetaData();
    final int columnCount = metaData.getColumnCount();
    while (resultSet.next()) {
      for (int i = 0; i < columnCount; i++) {
        if (i > 0) {
          System.out.print(", ");
        }
        System.out.print(metaData.getColumnLabel(i + 1));
        System.out.print(": ");
        System.out.print(resultSet.getObject(i + 1));
      }
      System.out.println();
    }
  }

  /** Tests {@link CompositeIterator#concat}. */
  @Test
  public void testMultipleIterables() {
    List<String> empty = Collections.emptyList();
    List<String> ab = Arrays.asList("a", "b");
    List<String> cde = Arrays.asList("c", "d", "e");
    List<String> f = Collections.singletonList("f");

    assertConcat("a,b,c,d,e,f", ab, cde, f);
    assertConcat("a,b,c,d,e,f", empty, ab, cde, f);
    assertConcat("a,b,c,d,e,f", ab, empty, cde, f);
    assertConcat("", empty, empty);
    assertConcat("", empty);
    assertConcat("f", empty, f);
  }

  @SafeVarargs
  private static void assertConcat(String expected, List<String>... lists) {
    Iterable<String> composite = CompositeIterator.concat(lists);
    List<String> result = new ArrayList<>();
    for (String s : composite) {
      result.add(s);
    }
    assertEquals(expected, String.join(",", result));
  }
}

// End ScottHsqldbTest.java
