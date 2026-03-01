package com.tracker;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseServlet extends HttpServlet {

    private double totalAmount = 0;
    private String userName = "User";
    private List<String> expenses = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("setAmount".equals(action)) {
            totalAmount = Double.parseDouble(request.getParameter("amount"));
            userName = request.getParameter("username");
            expenses.clear();
        }

        if ("addExpense".equals(action)) {
            String name = request.getParameter("expenseName");
            double amount = Double.parseDouble(request.getParameter("expenseAmount"));
            totalAmount -= amount;
            expenses.add(name + " - ₹" + amount);
        }

        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("userName", userName);
        request.setAttribute("expenses", expenses);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("userName", userName);
        request.setAttribute("expenses", expenses);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}