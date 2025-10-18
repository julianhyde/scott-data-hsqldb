# Scott data for hsqldb release history and change log

For a full list of releases, see
<a href="https://github.com/julianhyde/scott-data-hsqldb/releases">GitHub</a>.

## <a href="https://github.com/julianhyde/scott-data-hsqldb/releases/tag/scott-data-hsqldb-0.3">0.3</a> / 2025-10-18

This release moves the data from an HSQLDB `scott.script` file to a
`.csv` file for each table; consumers may now access those files from
the jar directly, if they wish.

Minimum HSQLDB version moves from 2.0.0 to 2.3.0; default HSQLDB
version is now 2.7.4.

* Bump Maven from 3.5.4 to 3.9.11,
  `build-helper-maven-plugin` to 3.6,
  `git-commit-id-plugin` to 4.9.10,
  `maven-compiler-plugin` to 3.14.0,
  `maven-enforcer-plugin` to 3.0,
  `maven-release-plugin` to 2.4.2;
  and add `maven-enforcer-plugin` version 3.0.0
* In Maven, add `central-publishing-maven-plugin`
* Add methods `tableNames()`, `tableUri(tableName)` and
  `generateInserts()`, so that consumers can access the data directly
* Convert from HSQLDB memory tables to text tables based on CSV files
* Bump HSQLDB from 2.6.1 to 2.7.4; minimum supported HSQLDB version moves
  from 2.0.0 to 2.3.0
* Add `googleformatter-maven-plugin` and reformat Java code
* Change git clone URL to HTTPS
* Add Maven Central badge to README
* Allow CI runs to be triggered manually, and monthly

## <a href="https://github.com/julianhyde/scott-data-hsqldb/releases/tag/scott-data-hsqldb-0.2">0.2</a> / 2022-03-08

* Bump HSQLDB from 2.3.1 to 2.6.1, and change HSQLDB file format from 1.8 to 2.0
* Add a GitHub workflow to build and test
* Add Apache Maven wrapper
* Enable Dependabot
* Bump junit from 4.11 to 4.13.2
* Document how to connect from SQLLine
* Typo in license
* Add distribution location

## <a href="https://github.com/julianhyde/scott-data-hsqldb/releases/tag/scott-data-hsqldb-0.1">0.1</a> / 2015-03-16

* Publish first release to <a href="http://search.maven.org/">Maven Central</a>
* Creation
