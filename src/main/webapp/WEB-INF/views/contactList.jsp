<%@ page import="com.expert.contact.domain.Person" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact List</title>
   <%-- <script type="text/javascript" src="resources/js/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="resources/js/jquery-ui-1.11.4.js"></script>
    <script type="text/javascript" src="resources/js/paging.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $('#contact-table').paging({limit:5});
        });
    </script>--%>
</head>
<body>
<h2>Contact List</h2>
<p><a href="main">На главную</a></p>
<table border="1" id="contact-table" cellpadding="5" cellspacing="5">
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

<table border="1" cellpadding="5" cellspacing="5">
    <tr>
<%
            int currentPage = (Integer)session.getAttribute("currentPage");
            if(currentPage != 1)
            {
%>
        <td><a href="contactList?page=<%=currentPage-1%>">Предыдущая</a> </td>
        <%
            }
        %>
<%
            int pagesQuantity = (Integer)session.getAttribute("pagesQuantity");
            for(int i = 1; i <= pagesQuantity; i++)
            {
                if(i != currentPage)
                {
%>
                <td><a href="contactList?page=<%=i%>"><%=i%></a></td>
<%
                }
                else
                {
%>
                    <td><%=i%></td>
<%
                }
            }
%>

<% if(currentPage < pagesQuantity)
        {
 %>
            <td><a href="contactList?page=<%=currentPage+1%>">Следующая</a></td>
<%
        }
%>
    </tr>
</table>
</body>
</html>
