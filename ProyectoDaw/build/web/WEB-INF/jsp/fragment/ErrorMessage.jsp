<%-- 
    Document   : ErrorMessage
    Created on : 19 dic 2023, 23:59:33
    Author     : alejandro
--%>

<%@page import="MVC.Models.Constants.JSP_NAME_ATTRIBUTE"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String msg = null;
    try {
        msg = (String) request.getAttribute(JSP_NAME_ATTRIBUTE.MESSAGE_ERROR);

    } catch (Exception e) {
    }

    if (msg != null) {

%>
<div class="alert alert-warning alert-dismissible fade show" role="alert">
    <%=msg%>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<%}%>
