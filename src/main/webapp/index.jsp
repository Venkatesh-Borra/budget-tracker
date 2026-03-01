<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%
    Double total = (Double) request.getAttribute("totalAmount");
    String user = (String) request.getAttribute("userName");
    List<String> expenses = (List<String>) request.getAttribute("expenses");

    if (total == null) total = 0.0;
    if (user == null) user = "User";
%>

<!DOCTYPE html>
<html>
<head>
    <title>Budget Tracker</title>
    <style>
        body {
            background: #0f0f0f;
            font-family: Arial;
            color: white;
        }
        .container {
            width: 600px;
            margin: auto;
            padding: 20px;
        }
        h1 {
            color: #00aaff;
            text-align: center;
        }
        .balance {
            font-size: 40px;
            text-align: center;
            margin: 20px 0;
        }
        .negative {
            color: red;
        }
        input, button {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
        }
        button {
            background: #0077cc;
            color: white;
            border: none;
        }
        .expense {
            background: #1a1a1a;
            padding: 10px;
            margin: 5px 0;
        }
    </style>
</head>
<body>

<div class="container">

    <h1>Welcome Nani, <%= user %></h1>

    <div class="balance <%= (total < 0) ? "negative" : "" %>">
        ₹ <%= total %>
    </div>

    <form method="post">
        <input type="hidden" name="action" value="setAmount">
        <input type="text" name="username" placeholder="Enter Name">
        <input type="number" name="amount" placeholder="Enter Total Amount">
        <button type="submit">Set Amount</button>
    </form>

    <hr>

    <form method="post">
        <input type="hidden" name="action" value="addExpense">
        <input type="text" name="expenseName" placeholder="Expense Name">
        <input type="number" name="expenseAmount" placeholder="Expense Amount">
        <button type="submit">Add Expense</button>
    </form>

    <hr>

    <% if (expenses != null) {
        for (String e : expenses) { %>
            <div class="expense"><%= e %></div>
    <%  }
    } %>

</div>

</body>
</html>