package com.example.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dispatch")
public class Dispatch extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    String[] payload = {"Banana", "Mango", "Papaya"};
    req.setAttribute("payload", payload);
    RequestDispatcher dispatcher = req.getRequestDispatcher("/views/dispatch.jsp");
    dispatcher.forward(req, res);
  }
}
