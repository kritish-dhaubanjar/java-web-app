package com.example.databases;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/database")
public class Data extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // Define datasource/connection pool for Resource Injection
  // @Resource(name="jdbc/web_student_tracker")
  // private DataSource dataSource;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      InitialContext ctx = new InitialContext();
      DataSource dataSource = (DataSource) ctx.lookup("jdbc/web_student_tracker");

      // Step 1: Set up the printwriter
      PrintWriter out = response.getWriter();
      response.setContentType("text/plain");

      // Step 2: Get a connection to the database
      Connection myConn = null;
      Statement myStmt = null;
      ResultSet myRs = null;

      myConn = dataSource.getConnection();

      // Step 3: Create a SQL statements
      String sql = "select * from student";
      myStmt = myConn.createStatement();

      // Step 4: Execute SQL query
      myRs = myStmt.executeQuery(sql);

      // Step 5: Process the result set
      while (myRs.next()) {
        String email = myRs.getString("email");
        out.println(email);
      }
    } catch (Exception exc) {
      exc.printStackTrace();
    }
  }

}
