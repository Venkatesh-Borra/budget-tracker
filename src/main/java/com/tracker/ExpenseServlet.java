package com.tracker;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Double totalAmount = (Double) session.getAttribute("totalAmount");
        String userName = (String) session.getAttribute("userName");
        List<String> expenses = (List<String>) session.getAttribute("expenses");

        if (totalAmount == null) totalAmount = 0.0;
        if (expenses == null) expenses = new ArrayList<>();

        String action = request.getParameter("action");

        if ("setAmount".equals(action)) {
            totalAmount = Double.parseDouble(request.getParameter("amount"));
            userName = request.getParameter("username");
            expenses = new ArrayList<>();
        }

        if ("addExpense".equals(action)) {
            String name = request.getParameter("expenseName");
            double amount = Double.parseDouble(request.getParameter("expenseAmount"));

            totalAmount -= amount;
            expenses.add(name + " - ₹" + amount);
        }

        session.setAttribute("totalAmount", totalAmount);
        session.setAttribute("userName", userName);
        session.setAttribute("expenses", expenses);

        response.sendRedirect(request.getContextPath() + "/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        request.setAttribute("totalAmount", session.getAttribute("totalAmount"));
        request.setAttribute("userName", session.getAttribute("userName"));
        request.setAttribute("expenses", session.getAttribute("expenses"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}