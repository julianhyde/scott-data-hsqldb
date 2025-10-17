<!--
{% comment %}
Licensed to Julian Hyde under one or more contributor license
agreements.  See the NOTICE file distributed with this work
for additional information regarding copyright ownership.
Julian Hyde licenses this file to you under the Apache
License, Version 2.0 (the "License"); you may not use this
file except in compliance with the License.  You may obtain a
copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
either express or implied.  See the License for the specific
language governing permissions and limitations under the
License.
{% endcomment %}
-->
[![Build Status](https://github.com/julianhyde/scott-data-hsqldb/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/julianhyde/scott-data-hsqldb/actions?query=branch%3Amain)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.hydromatic/scott-data-hsqldb/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.hydromatic/scott-data-hsqldb)
[![javadoc](https://javadoc.io/badge2/net.hydromatic/scott-data-hsqldb/javadoc.svg)](https://javadoc.io/doc/net.hydromatic/scott-data-hsqldb)

# scott-data-hsqldb
Scott data set in hsqldb format

This project contains the Scott data set as an
HSQLDB database.

It originated as the "<a href="https://community.oracle.com/message/10655227">Scott/Tiger</a>"
account that has been installed with Oracle since time immemorial.

## To connect and read data

Add the following to your Maven pom.xml:
```xml
<dependencies>
  <dependency>
    <groupId>net.hydromatic</groupId>
    <artifactId>scott-data-hsqldb</artifactId>
    <version>0.2</version>
  </dependency>
</dependencies>
```

(scott-data-hsqldb supports HSQLDB 2.3.0 and higher,
and Java 8 and higher;
note that HSQLDB 2.6.0 and higher requires
<a href="http://hsqldb.org/doc/2.0/changelist_2_0.txt">Java 11 and higher</a>.)

Connect to the database via the URL, username and password in the
`ScottHsqldb` class:

```java
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import net.hydromatic.scott.data.hsqldb;

Connection connection =
    DriverManager.getConnection(ScottHsqldb.URI,
        ScottHsqldb.USER, ScottHsqldb.PASSWORD);
Statement statement = connection.createStatement();
ResultSet resultSet =
    statement.executeQuery("select * from EMP");
```

## Using SQLLine

Connect from the command line using the [SQLLine](https://github.com/julianhyde/sqlline) shell:

```
$ ./sqlline
sqlline version 1.12.0
sqlline> !connect jdbc:hsqldb:res:scott SCOTT TIGER
0: jdbc:hsqldb:res:scott> select * from dept;
+--------+----------------+---------------+
| DEPTNO |     DNAME      |      LOC      |
+--------+----------------+---------------+
| 10     | ACCOUNTING     | NEW YORK      |
| 20     | RESEARCH       | DALLAS        |
| 30     | SALES          | CHICAGO       |
| 40     | OPERATIONS     | BOSTON        |
+--------+----------------+---------------+
4 rows selected (0.002 seconds)
```

You may need to edit the `sqlline` or `sqlline.bat` launcher script,
adding `scott-data-hsqldb.jar` to your class path.

## Get scott-data-hsqldb

### From Maven

Get scott-data-hsqldb from
<a href="https://search.maven.org/#search%7Cga%7C1%7Cg%3Anet.hydromatic%20a%3Ascott-data-hsqldb">Maven Central</a>:

```xml
<dependency>
  <groupId>net.hydromatic</groupId>
  <artifactId>scott-data-hsqldb</artifactId>
  <version>0.2</version>
</dependency>
```

### Download and build

Use Java version 11 or higher.

```bash
$ git clone https://github.com/julianhyde/scott-data-hsqldb.git
$ cd scott-data-hsqldb
$ ./mvnw install
```

On Windows, the last line is

```bash
> mvnw install
```

If you are using Java 8, you should add a parameter
`-Dhsqldb.version=2.5.1`, because HSQLDB 2.6.0 or higher
requires JDK 11 or higher.

## See also

Similar data sets:
* [chinook-data-hsqldb](https://github.com/julianhyde/chinook-data-hsqldb)
* [flight-data-hsqldb](https://github.com/julianhyde/flight-data-hsqldb)
* [foodmart-data-hsqldb](https://github.com/julianhyde/foodmart-data-hsqldb)
* [foodmart-data-json](https://github.com/julianhyde/foodmart-data-json)
* [foodmart-queries](https://github.com/julianhyde/foodmart-queries)
* [steelwheels-data-hsqldb](https://github.com/julianhyde/steelwheels-data-hsqldb)

## More information

* License: Apache License, Version 2.0
* Author: Julian Hyde
* Blog: http://blog.hydromatic.net
* Project page: https://www.hydromatic.net/scott-data-hsqldb
* Source code: https://github.com/julianhyde/scott-data-hsqldb
* Distribution: <a href="https://search.maven.org/#search%7Cga%7C1%7Ca%3A%22scott-data-hsqldb%22">Maven Central</a>
* Developers list:
  <a href="mailto:dev@calcite.apache.org">dev at calcite.apache.org</a>
  (<a href="https://mail-archives.apache.org/mod_mbox/calcite-dev/">archive</a>,
  <a href="mailto:dev-subscribe@calcite.apache.org">subscribe</a>)
* Issues: https://github.com/julianhyde/scott-data-hsqldb/issues
* <a href="HISTORY.md">Release notes and history</a>
