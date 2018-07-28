<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String relayState = (String) request.getAttribute("relayState");
    String loginPage = (String)request.getAttribute("loginPage");
%>
<html>
<head>
    <title>Title</title>
    <script>
        var relayState = "<%=relayState%>";
        if(relayState == null || relayState == "null"){
            var loginPage = "<%=loginPage%>";
            window.location.href = loginPage;
        }else{
            window.location.href = relayState;
        }
    </script>
</head>
<body>
<%=relayState%>
<%=loginPage%>
</body>
</html>
