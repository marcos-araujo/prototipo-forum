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
		<a href="/Forum">[Home]</a>
		<h1>Fórum</h1>
		<hr />
		<table>
			<%
				Integer pagina = (request.getParameter("p") != null) ? new Integer(request.getParameter("p")) : 0;
					Integer idThread = (request.getParameter("t") != null) ? new Integer(request.getParameter("t")) : 0;
					Integer numeroExibicao = (request.getParameter("n") != null) ? new Integer(request.getParameter("n")) : 0;
					TopicoDAO dao = new TopicoDAO();
					ArrayList<Topico> lista = new ArrayList<Topico>(); 
					lista = (ArrayList<Topico>)dao.listaTopicos(new Long(request.getParameter("t")),numeroExibicao);
					//Mostra 10 em cada página
					int tamanho = (pagina+1)*10;
					if(tamanho>lista.size())
						tamanho = lista.size();
					for(int x=pagina*10; x<tamanho; x++){
			%>		
				<tr>
					<td><%=lista.get(x).getNivel()%>. <%=lista.get(x).getDataFormatada()%>: <a href="javascript:responder(<%=lista.get(x).getId()%>);"><%=lista.get(x).getTexto()%> </a></td>
				</tr>
				<tr>
					<td id="<%=lista.get(x).getId()%>" class="esconder">
						<form action="adicionaTopico">
							<input name="thread" type="hidden" value="<%=idThread%>" />
							<input name="pagina" type="hidden" value="<%=pagina%>" />
							<input name="idPai" type="hidden" value="<%=lista.get(x).getId()%>" />
							<input name="numeroExibicao" type="hidden" value="<%=numeroExibicao%>" />
							<textarea name="texto" rows="3" cols="40"></textarea>
							<br />
							<input class="botao" type="submit" value="Responder" />
						</form>
						<br />
					</td>
				</tr>
			<%
			}
			%>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
				<%
				int paginacao;
				if(lista.size()>10){
					paginacao = (lista.size()%10 == 0)?(lista.size()/10)-1:(lista.size()/10);
				%>
				[
				<% for(int y=0; y<=paginacao; y++){%>
					<a href="/Forum/thread.jsp?p=<%=y%>&t=<%=idThread%>&n=<%=numeroExibicao%>"><%=y%></a> 
				<%} %>
				]
				<%} %>
				</td>
			</tr>
		</table>
	</body>
</html>