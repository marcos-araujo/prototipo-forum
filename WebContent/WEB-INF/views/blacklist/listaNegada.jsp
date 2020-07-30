<%@ page import="java.util.List" %>
<%@ page import="br.com.forum.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="br.com.forum.model.Topico" %>
<%@ page import="br.com.forum.dao.PalavraNegadaDAO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../general/header.jsp" />

	<a href="forum">[Home]</a>
	<h1>Blacklist</h1>
	<hr />
	<form action="adicionarPalavraNegada" method="post">
		<input type="hidden" name="blacklist" value="1" />
		<input type="text" name="palavra" />
		<input type="submit" value="Gravar" />
	</form>
	<br />
	<table>
		<%
		ArrayList<PalavraNegada> lista = (ArrayList<PalavraNegada>)request.getAttribute("lista");
		for(int x=0; x<lista.size(); x++){
		%>		
		
		<tr>
			<td><%=lista.get(x).getPalavra()%></td>
			<td><a href="/Forum/deletarPalavraNegada?idPalavra=<%=lista.get(x).getId()%>">Remover</a></td>
		</tr>
		
		<% } %>
	</table>

<c:import url="../general/footer.jsp" />