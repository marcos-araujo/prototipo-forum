<%@ page import="java.util.List" %>
<%@ page import="br.com.forum.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="br.com.forum.model.Topico" %>
<%@ page import="br.com.forum.dao.TopicoDAO" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Fórum</title>
		<link rel="stylesheet" type="text/css" href="estilo.css">
		<script type="text/javascript" src="script.js"></script>
	</head>
	<body>
		<div id="rodape">Desenvolvido por Marcos Araújo - <a href="sobre.html">Sobre</a></div>
		<br />
		<a href="listaNegada.jsp">[Blacklist]</a>
		<h1>Fórum</h1>
		<hr />
		<%
			Integer pagina = (request.getParameter("p") != null)? new Integer(request.getParameter("p")):0;
		%>
		<form action="adicionaTopico">
			<input name="idPai" type="hidden" value="0" />
			<input name="pagina" type="hidden" value="<%=pagina%>" />
			<textarea name="texto" rows="4" cols="50"></textarea>
			<br />
			<input type="submit" value="Enviar" />
		</form>
		<br /><br />
		<table>
			<%
				ArrayList<Topico> lista = (ArrayList<Topico>)request.getAttribute("lista");
				int tamanho = (pagina+1)*10;
				if(tamanho>lista.size())
					tamanho = lista.size();
				for(int x=pagina*10; x<tamanho; x++){
			%>		
				<tr>
					<td><a href="topico.jsp?n=<%=x+1%>&t=<%=lista.get(x).getId()%>"><%=(x+1)%>.<%=lista.get(x).getDataFormatada()%></a></td>
					<td><%=lista.get(x).getTexto()%></td>
				</tr>
			<%
			}
			%>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3">
				<%if(lista.size()>10){
					int paginacao;
						paginacao = (lista.size()%10 == 0)?(lista.size()/10)-1:(lista.size()/10);
				%>
				[
				<% for(int y=0; y<=paginacao; y++){%>
					<a href="/Forum/?p=<%=y%>"><%=y%></a> 
				<%} %>
				]
				<%} %>
				</td>
			</tr>
		</table>
	</body>
</html>