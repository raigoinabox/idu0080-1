<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<h1>Hello World!</h1>
	<p>This is the homepage!</p>

	<c:if test="${not empty books}">
		<table class="normal-table">
			<thead>
				<tr>
					<th>Raamatud</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${books}">
					<tr>
						<td><a
							href="<c:url  value="/service/book/${book.id}">
							</c:url>">${book.author}
								"${book.title}"</a></td>
						<td></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>
