<%@ page import="com.docker.java_jsp.jsp.modelsDAO.PersonDAO" %>
<%@ page import="com.docker.java_jsp.jsp.models.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: jaime
  Date: 19/04/2025
  Time: 05:20 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Personas</h1>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <%
            PersonDAO dao = new PersonDAO();
            List<Person> persons = dao .list();

            Iterator<Person> iter = persons.iterator();
            Person per;

            while(iter.hasNext()) {
                per = iter.next();
                
        %>
        <tbody>
            <tr>
                <td><%= per.getId()%>></td>
                <td><%= per.getName()%>></td>
                <td><%= per.getEmail()%>></td>
                <td>
                    <a>Edit</a>
                    <a>Remove</a>
                </td>
            </tr>
            <%}%>
        </tbody>
    </table>
</body>
</html>
