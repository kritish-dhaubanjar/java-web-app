# java-web-app

### [1. New Project Setup using maven](https://maven.apache.org/archetype/maven-archetype-plugin/usage.html)
```
mvn archetype:generate
```

- archeType: [maven-archetype-webapp](https://maven.apache.org/archetypes/maven-archetype-webapp/)
- groupId: com.example.java-web-app
- artifactId: java-web-app
- version: 1.0-SNAPSHOT
- packaging: war

### [2. Embedded Web Server jetty://](https://www.eclipse.org/jetty/documentation/jetty-9/index.html#jetty-maven-plugin)
```
<plugin>
  <groupId>org.eclipse.jetty</groupId>
  <artifactId>jetty-maven-plugin</artifactId>
  <configuration>
    <scanIntervalSeconds>10</scanIntervalSeconds>
    <webApp>
      <contextPath>/</contextPath>
    </webApp>
  </configuration>
</plugin>
```

```
mvn jetty:run
```

### [3. Introduction to the Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)
Having a common directory layout allows users familiar with one Maven project to immediately feel at home in another Maven project. The advantages are analogous to adopting a site-wide look-and-feel.

The next section documents the directory layout expected by Maven and the directory layout created by Maven. Try to conform to this structure as much as possible. However, if you can't, these settings can be overridden via the project descriptor.
```
src/main/java 	    Application/Library sources
src/main/resources 	Application/Library resources
src/main/filters 	  Resource filter files
src/main/webapp 	  Web application sources
src/test/java 	    Test sources
src/test/resources 	Test resources
src/test/filters 	  Test resource filter files
src/it 	            Integration Tests (primarily for plugins)
src/assembly 	      Assembly descriptors
src/site 	          Site
LICENSE.txt 	      Project's license
NOTICE.txt 	        Notices and attributions required by libraries that the project depends on
README.txt 	        Project's readme
```

At the top level, files descriptive of the project: a `pom.xml` file. In addition, there are textual documents meant for the user to be able to read immediately on receiving the source: `README.txt`, `LICENSE.txt`, etc.

There are just two subdirectories of this structure: `src` and `target`. The only other directories that would be expected here are metadata like `CVS`, `.git` or `.svn`, and any subprojects in a multiproject build (each of which would be laid out as above).

The `target` directory is used to house all output of the build.

The `src` directory contains all of the source material for building the project, its site and so on. It contains a subdirectory for each type: `main` for the main build artifact, `test` for the unit test code and resources, `site` and so on.

Within artifact producing source directories (ie. `main` and `test`), there is one directory for the language `java` (under which the normal package hierarchy exists), and one for `resources` (the structure which is copied to the target classpath given the default resource definition).

If there are other contributing sources to the artifact build, they would be under other subdirectories. For example `src/main/antlr` would contain Antlr grammar definition files.

### 4. Built-In Sever Objects
| Object | Description |
|-|-|
| request | Contains HTTP request headers & form data |
| response | Provides HTTP support for sending response |
| out | JspWriter for including content in HTML page |
| session | Unique session for each user of the web application |
| application | Shared data for all users of the web application |


### 5. JSP Session
| Method | Description |
|-|-|
| getAttribute(String attribute): Object | Get attribute in a session. eg: (List<String>)session.getAttribute("attribute") |
| setAttribute(String attribute, Object value): void | Set attribute in a session. |
| isNew(): boolean | Returns true if the session is new |
| getId(): String | Returns the session id |
| invalidate(): void | Invalidates this session and unbinds any object assocaited with it |
| setMaxInactiveInterval(long ms): void | Sets the idle time for a session to expire. The value is supplied in milliseconds. |

### [6. JSTL](https://docs.oracle.com/javaee/5/jstl/1.1/docs/tlddocs/index.html)
##### javax.servlet.jsp.jstl-api-1.2.1.jar
- https://mvnrepository.com/artifact/javax.servlet.jsp.jstl/jstl-api/1.2
- This contains the JSTL API interfaces and support classes. However a large number of the interfaces do not have implementation classes.

##### javax.servlet.jsp.jstl-1.2.1.jar
- https://mvnrepository.com/artifact/javax.servlet.jsp.jstl/jstl/1.2
- This contains an implementation of the JSTL API. This code implements all of the interface from the API above.


| Tag Lib | Description |
|-|-|
| JSTL core | JSTL core library |
| JSTL fmt | JSTL i18n-capable formatting library |
| JSTL sql | JSTL sql library |
| JSTL XML | JSTL XML library |
| JSTL functions | JSTL functions library |

### 6. JDBC
To add JDBC support to a Maven Webapp archetype, you can follow these steps: Open the pom.xml file of your Maven Webapp project. Add the following dependency to your pom.xml file to include JDBC driver in your project:

```
<dependency>
  <groupId>com.oracle.jdbc</groupId>
  <artifactId>ojdbc8</artifactId>
  <version>18.3.0.0</version>
</dependency>
```

Note: You may need to replace the groupId, artifactId, and version with the appropriate values for your JDBC driver.

Add a context.xml file to your project’s META-INF folder. This file is used to configure the database connection.

```
<Context>
  <Resource name="jdbc/MyDB" auth="Container" type="javax.sql.DataSource"
             maxActive="100" maxIdle="30" maxWait="10000"
             username="username" password="password" driverClassName="com.mysql.jdbc.Driver"
             url="jdbc:mysql://localhost:3306/mydb"/>
</Context>
```

Note: You may need to replace the name, username, password, driverClassName, and url with the appropriate values for your database. Update the web.xml file to include the resource reference for the database connection.

```
<resource-ref>
  <description>My Connection</description>
  <res-ref-name>jdbc/MyDB</res-ref-name>
  <res-type>javax.sql.DataSource</res-type>
  <res-auth>Container</res-auth>
</resource-ref>
```
Note: Make sure the res-ref-name matches the name attribute in your context.xml file.

With these steps, you have added JDBC support to your Maven Webapp archetype, and you should be able to connect to your database using JDBC in your project.

In your Servlet code, use the @Resource annotation to inject the database resource into a DataSource object. Here is an example:

java
```
@WebServlet("/my-servlet")
public class MyServlet extends HttpServlet {
 
  @Resource(name="jdbc/MyDB")
  private DataSource dataSource;
 
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Connection connection = null;
    Statement statement = null;
    try {
      connection = dataSource.getConnection();
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM mytable");
      // process the resultSet
    } catch (SQLException e) {
      // handle exception
    } finally {
      try { statement.close(); } catch (Exception e) { }
      try { connection.close(); } catch (Exception e) { }
    }
  }
 
}
```

In the above example, the @Resource annotation is used to inject the database resource into the dataSource object. Then, a Connection object is obtained from the DataSource object, and a Statement object is created to execute a SQL query. Finally, the ResultSet object is processed, and the Connection and Statement objects are closed.

Note: Make sure that the name attribute of the @Resource annotation matches the res-ref-name in the web.xml file. Also, make sure to handle exceptions properly in your code.

