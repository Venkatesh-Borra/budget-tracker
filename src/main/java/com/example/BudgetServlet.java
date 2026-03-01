package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class BudgetServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double income = Double.parseDouble(request.getParameter("income"));
        double expenses = Double.parseDouble(request.getParameter("expenses"));
        double loan = Double.parseDouble(request.getParameter("loan"));

        double savings = income - expenses - loan;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Budget Summary</h2>");
        out.println("Income: " + income + "<br>");
        out.println("Expenses: " + expenses + "<br>");
        out.println("Loan EMI: " + loan + "<br>");
        out.println("<h3>Remaining Savings: " + savings + "</h3>");
    }
}
