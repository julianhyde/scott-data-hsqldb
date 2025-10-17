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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Iterator that generates INSERT statements from a single CSV file. Returns
 * false from hasNext() when the CSV file is exhausted.
 */
class StatementGenerator implements Iterator<String> {
  private final String tableName;
  private final BufferedReader reader;
  private final List<String> columnNames;
  private final List<String> quotedColumnNames;
  private String nextStatement;

  StatementGenerator(
      Class<?> resourceClass, String tableName, String[] quotedColumnNames) {
    this.tableName = tableName;
    this.quotedColumnNames = Arrays.asList(quotedColumnNames);
    String csvPath = "/csv/" + tableName.toLowerCase() + ".csv";
    try {
      InputStream is = resourceClass.getResourceAsStream(csvPath);
      if (is == null) {
        throw new RuntimeException("CSV file not found: " + csvPath);
      }
      this.reader = new BufferedReader(new InputStreamReader(is));

      // Read header line
      String headerLine = reader.readLine();
      this.columnNames = parseCsvLine(headerLine);

      // Read first data line
      advance();
    } catch (IOException e) {
      throw new RuntimeException("Failed to read CSV file: " + csvPath, e);
    }
  }

  @Override
  public boolean hasNext() {
    return nextStatement != null;
  }

  @Override
  public String next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    String result = nextStatement;
    advance();
    return result;
  }

  private void advance() {
    nextStatement = null;
    try {
      String line;
      while ((line = reader.readLine()) != null) {
        if (!line.trim().isEmpty()) {
          nextStatement = generateInsertStatement(line);
          return;
        }
      }
      // EOF reached, close reader
      reader.close();
    } catch (IOException e) {
      closeReader();
      throw new RuntimeException("Error reading CSV file", e);
    }
  }

  /** Generates a single INSERT statement from a CSV line. */
  private String generateInsertStatement(String line) {
    StringBuilder insert = new StringBuilder("INSERT INTO \"");
    insert.append(tableName).append("\" VALUES(");

    final List<String> values = parseCsvLine(line);
    for (int i = 0; i < values.size(); i++) {
      if (i > 0) {
        insert.append(",");
      }
      String value = values.get(i);
      if (value.isEmpty()) {
        insert.append("NULL");
      } else if (quotedColumnNames.contains(columnNames.get(i))) {
        // Quote string/date values and escape any quotes
        insert.append("'").append(value.replace("'", "''")).append("'");
      } else {
        // Numeric values don't need quotes
        insert.append(value);
      }
    }
    insert.append(")");
    return insert.toString();
  }

  private void closeReader() {
    try {
      reader.close();
    } catch (IOException e) {
      // Ignore close errors
    }
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  /** Parses a CSV line, handling quoted values. */
  private static List<String> parseCsvLine(String line) {
    final List<String> values = new ArrayList<>();
    final StringBuilder current = new StringBuilder();
    boolean inQuotes = false;

    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);

      if (c == '"') {
        inQuotes = !inQuotes;
      } else if (c == ',' && !inQuotes) {
        values.add(current.toString());
        current.setLength(0);
      } else {
        current.append(c);
      }
    }
    values.add(current.toString());

    return values;
  }
}

// End StatementGenerator.java
