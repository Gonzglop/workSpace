<%--
  Created by IntelliJ IDEA.
  User: gonzalolopez
  Date: 4/2/23
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Pedido</title>
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
        }
        form {
            background-color: #f2f2f2;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 2px 2px 10px #888888;
        }

        h1 {
            text-align: center;
            margin: 50px;
        }

        table {
            margin: 0 auto;
        }

        td,
        th {
            padding: 10px;
        }

        input[type="text"],
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: none;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007aff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>Nuevo pedido</h1>
<form action="order?action=create" method="post">
    <table>
        <tr>
            <td>Nombre:</td>
            <td><input type="text" name="customerName"></td>
        </tr>
        <tr>
            <td>Dirección:</td>
            <td><input type="text" name="address"></td>
        </tr>
        <tr>
            <td>Teléfono:</td>
            <td><input type="text" name="phoneNumber"></td>
        </tr>
        <tr>
            <td>Tamaño de la pizza:</td>
            <td><select name="pizzaSize">
                <option value="15">15 cm</option>
                <option value="20">20 cm</option>
                <option value="30">30 cm</option>
                <option value="50">50 cm</option>
            </select></td>
        </tr>
        <tr>
            <td>Tipo de pizza:</td>
            <td><select name="pizzaType">
                <option value="Margarita">Margarita</option>
                <option value="Barbacoa">Barbacoa</option>
                <option value="Vegetariana">Vegetariana</option>
                <option value="Hawaiana">Hawaiana</option>
            </select></td>
        </tr>
        <tr>
            <td>Ingredientes extra:</td>
            <td>
                <input type="checkbox" name="extraIngredients" value="Aceitunas">Aceitunas
                <input type="checkbox" name="extraIngredients" value="Gambas">Gambas
                <input type="checkbox" name="extraIngredients" value="Cebolla">Cebolla
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Enviar"></td>
        </tr>
    </table>
</form>
</body>
</html>