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

import org.junit.Test;

import java.sql.*;

/**
 * Kick the tires
 */
public class ScottHsqldbTest {
  @Test public void test() throws SQLException {
    final Connection connection = DriverManager.getConnection(ScottHsqldb.URI,
        ScottHsqldb.USER, ScottHsqldb.PASSWORD);
    final Statement statement = connection.createStatement();
    foo(statement.executeQuery("select * from EMP"));
    foo(statement.executeQuery("select * from DEPT"));
    foo(statement.executeQuery("select * from SALGRADE"));
    foo(statement.executeQuery("select * from BONUS"));
    statement.close();
    connection.close();
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
}

// End ScottHsqldbTest.java
