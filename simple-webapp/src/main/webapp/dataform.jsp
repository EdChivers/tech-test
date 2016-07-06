<%@ page import="java.util.List, edchivers.webapp.data.Person" %>
<html>
<head>
<title>Web form for simple-webapp</title>
</head>
<body>
<form method="post" action="/processform">
    <table>
        <tr>
            <th>First name</th>
            <th>Last name</th>
        </tr>
        <%
        	List<Person> people = (List<Person>) request.getAttribute("FORM_DATA");
        
        	// pad up to a minimum size
        	while (people.size() < 4)
        	{
        		people.add(new Person("",""));
        	}
        
        	for (Person p : people)
        	{
        %>
        
        <tr>
            <td><input type="text" name="people[][firstname]" value="<%=p.getFirstName()%>" /></td>
            <td><input type="text" name="people[][surname]" value="<%=p.getLastName()%>" /></td>
        </tr>
        
        <%
        	//end for loop
        	}
        %>
    </table>
    <input type="submit" value="OK" />
</form>
</body>
</html>