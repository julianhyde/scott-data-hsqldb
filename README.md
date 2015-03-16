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

## More information

* License: Apache License, Version 2.0
* Author: Julian Hyde
* Blog: http://julianhyde.blogspot.com
* Project page: http://www.hydromatic.net/scott-data-hsqldb
* Source code: http://github.com/julianhyde/scott-data-hsqldb
* Developers list:
  <a href="mailto:dev@calcite.incubator.apache.org">dev at calcite.incubator.apache.org</a>
  (<a href="http://mail-archives.apache.org/mod_mbox/incubator-calcite-dev/">archive</a>,
  <a href="mailto:dev-subscribe@calcite.incubator.apache.org">subscribe</a>)
* Issues: https://github.com/julianhyde/scott-data-hsqldb/issues
* <a href="HISTORY.md">Release notes and history</a>
