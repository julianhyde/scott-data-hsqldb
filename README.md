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
    <groupId>org.hsqldb</groupId>
    <artifactId>hsqldb</artifactId>
    <version>2.3.1</version>
  </dependency>
  <dependency>
    <groupId>net.hydromatic</groupId>
    <artifactId>scott-data-hsqldb</artifactId>
    <version>0.1</version>
  </dependency>
</dependencies>
```

Connect to the database via the URL, user name and password in the
`ScottHsqldb` class:

```java
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import net.hydromatic.scott.data.hsqldb;

Connection connection =
  DriverManager.getConnection(ScottHsqldb.URI, ScottHsqldb.USER, ScottHsqldb.PASSWORD);
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery("select * from EMP");
```

## Using SQLLine

Connect from the command line using the [SQLLine](https://github.com/julianhyde/sqlline) shell:

```
$ ./sqlline
sqlline version 1.2.0
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
  <version>0.1</version>
</dependency>
```

### Download and build

Java version 8 or higher.

```bash
$ git clone git://github.com/julianhyde/scott-data-hsqldb.git
$ cd scott-data-hsqldb
$ ./mvnw install
```

On Windows, the last line is

```bash
> mvnw install
```

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
