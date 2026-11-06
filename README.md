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
[![Maven Central](https://img.shields.io/maven-central/v/net.hydromatic/scott-data-hsqldb.svg)](https://central.sonatype.com/artifact/net.hydromatic/scott-data-hsqldb)
[![javadoc](https://javadoc.io/badge2/net.hydromatic/scott-data-hsqldb/javadoc.svg)](https://javadoc.io/doc/net.hydromatic/scott-data-hsqldb)

# scott-data-hsqldb
Scott data set in HSQLDB format

This project contains the Scott data set as an
HSQLDB database.

It originated as the "[Scott/Tiger](https://web.archive.org/web/20200813154925/https://community.oracle.com/message/10655227)"
account that has been installed with Oracle since time immemorial.

## To connect and read data

Connect to the database via the URL, username and password in the
`ScottHsqldb` class:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import net.hydromatic.scott.data.hsqldb.ScottHsqldb;

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
[Maven Central](https://central.sonatype.com/artifact/net.hydromatic/scott-data-hsqldb):

```xml
<dependency>
  <groupId>net.hydromatic</groupId>
  <artifactId>scott-data-hsqldb</artifactId>
  <version>0.3</version>
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
requires at least JDK 11.

## See also

The same data set is available in other forms.

There are scripts that create the tables (EMP, DEPT, BONUS,
SALGRADE, DUMMY) in
[BigQuery](https://gist.github.com/julianhyde/6ea75e1b4e9c76ccdb48430717644eed),
[DuckDB](https://gist.github.com/julianhyde/32364a3d55151efe7b746bdd466ccda1),
[Oracle](https://gist.github.com/julianhyde/686145afd0af9fdf61f7b57c5a7299b7),
[PostgreSQL](https://gist.github.com/julianhyde/13b55f716060649da96d8dcad1316546) and
[SQL Server](https://gist.github.com/julianhyde/40c535b91c40a0c36e0ea8d764541c57),
and queries that define the tables as common table expressions
(CTEs), so you can use the data without creating any tables, in
[BigQuery](https://gist.github.com/julianhyde/6b45a541ea711a718ec1090a6d8fe592),
[Oracle](https://gist.github.com/julianhyde/bf44e5403a8086de58e201456db99c32) and
[PostgreSQL](https://gist.github.com/julianhyde/70609f4de92ccabec41b9a9df0689d23).

Similar data sets:
* [chinook-data-hsqldb](https://github.com/julianhyde/chinook-data-hsqldb)
* [flight-data-hsqldb](https://github.com/julianhyde/flight-data-hsqldb)
* [foodmart-data](https://github.com/hydromatic/foodmart-data)
* [foodmart-data-hsqldb](https://github.com/hydromatic/foodmart-data-hsqldb)
* [foodmart-data-json](https://github.com/julianhyde/foodmart-data-json)
* [foodmart-data-mysql](https://github.com/julianhyde/foodmart-data-mysql)
* [foodmart-queries](https://github.com/julianhyde/foodmart-queries)
* [look-data-hsqldb](https://github.com/hydromatic/look-data-hsqldb)
* [sakila-data-hsqldb](https://github.com/hydromatic/sakila-data-hsqldb)
* [steelwheels-data-hsqldb](https://github.com/julianhyde/steelwheels-data-hsqldb)

## More information

* **License:** Apache License, Version 2.0
* **Author:** [Julian Hyde](https://github.com/julianhyde)
  ([@julianhyde](https://x.com/julianhyde))
* **Blog:** http://blog.hydromatic.net
* **Source code:** https://github.com/julianhyde/scott-data-hsqldb
* **Distribution:**
  [Maven Central](https://central.sonatype.com/artifact/net.hydromatic/scott-data-hsqldb)
* **Developers list:**
  [dev@calcite.apache.org](mailto:dev@calcite.apache.org)
  ([archive](https://lists.apache.org/list.html?dev@calcite.apache.org),
  [subscribe](mailto:dev-subscribe@calcite.apache.org))
* **Issues:** https://github.com/julianhyde/scott-data-hsqldb/issues
* **Release notes:** [HISTORY.md](HISTORY.md)
