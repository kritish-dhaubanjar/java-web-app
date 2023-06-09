package com.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet")
public class Servlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");

    PrintWriter out = response.getWriter();
    ServletContext context = getServletContext();

    out.println("{\"message\": \"Hello World\", \"param-name\": \"" + context.getInitParameter("param-name") + "\", \"servlet-param-name\": \"" + getInitParameter("servlet-param-name") +"\"}");
    // response.sendRedirect("https://google.com");
  }
}
