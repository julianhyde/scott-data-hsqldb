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

import java.util.Arrays;
import java.util.List;

/** Scott data set in hsqldb format. */
public class ScottHsqldb {
  /** URI of the hsqldb database. */
  public static final String URI = "jdbc:hsqldb:res:scott";

  public static final String USER = "SCOTT";
  public static final String PASSWORD = "TIGER";

  /** Returns the list of table names in the Scott schema. */
  public static List<String> tableNames() {
    return Arrays.asList("DEPT", "EMP", "BONUS", "SALGRADE");
  }

  /**
   * Converts a table name to a CSV file URI relative to the jar file root.
   *
   * <p>For example, {@code tableUri("DEPT")} returns {@code "/csv/dept.csv"}.
   */
  public static String tableUri(String tableName) {
    return "/csv/" + tableName.toLowerCase() + ".csv";
  }

  /**
   * Returns the INSERT statements for all Scott schema data.
   *
   * <p>Generates statements by reading from the CSV files lazily using an
   * iterator.
   *
   * @return an iterable of INSERT statements
   */
  public static Iterable<String> generateInserts() {
    return CompositeIterator.concat(
        () ->
            new StatementGenerator(
                ScottHsqldb.class, "DEPT", new String[] {"DNAME", "LOC"}),
        () ->
            new StatementGenerator(
                ScottHsqldb.class,
                "EMP",
                new String[] {"ENAME", "JOB", "HIREDATE"}),
        () ->
            new StatementGenerator(
                ScottHsqldb.class, "BONUS", new String[] {"ENAME", "JOB"}),
        () ->
            new StatementGenerator(
                ScottHsqldb.class, "SALGRADE", new String[] {}));
  }
}

// End ScottHsqldb.java
