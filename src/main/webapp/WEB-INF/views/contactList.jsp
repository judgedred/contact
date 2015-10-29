<%@ page import="com.expert.contact.domain.Person" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact List</title>
</head>
<body>
<h2>Contact List</h2>
<table border="1">
    <tr>
        <td><a href="contactList?sortValue=personId">ID</a></td>
        <td><a href="contactList?sortValue=name">Name</a></td>
        <td><a href="contactList?sortValue=surname">Surname</a></td>
        <td><a href="contactList?sortValue=login">Login</a></td>
        <td><a href="contactList?sortValue=email">Email</a></td>
        <td><a href="contactList?sortValue=phoneNumber">Phone number</a></td>
    </tr>
<% List<Person> personList = (List<Person>) session.getAttribute("personList");
    if(personList != null)
    {
    for(Person p : personList)
    {
%>
        <tr>
            <td><%=p.getPersonId()%></td>
            <td><%=p.getPersonName()%></td>
            <td><%=p.getPersonSurname()%></td>
            <td><%=p.getLogin()%></td>
            <td><%=p.getEmail()%></td>
            <td><%=p.getPhoneNumber()%></td>
        </tr>
<%
    }
    }
%>
</table>
</body>
</html>
