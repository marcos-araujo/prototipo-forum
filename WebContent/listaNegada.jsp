<%@ page import="java.util.List" %>
<%@ page import="br.com.forum.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="br.com.forum.model.Topico" %>
<%@ page import="br.com.forum.dao.PalavraNegadaDAO" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Fórum</title>
		<link rel="stylesheet" type="text/css" href="estilo.css">
		<script type="text/javascript" src="script.js"></script>
	</head>
	<link rel="stylesheet" type="text/css" href="estilo.css">
	<script type="text/javascript" src="script.js"></script>
	<body>
		<div id="rodape">Desenvolvido por Marcos Araújo - <a href="sobre.html">Sobre</a></div>
		<br />
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
	</body>
</html>