<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../general/header.jsp" />

	<a href="blacklistListar">[Blacklist]</a>
	<h1>Fórum</h1>
	<hr />
	
	<form action="topicoAdicionar" method="post">
		<input name="idPai" type="hidden" value="0" />
		<input name="pagina" type="hidden" value="${param['p']}" />
		<textarea name="texto" rows="4" cols="50"></textarea>
		<br />
		<input type="submit" value="Enviar" />
	</form>
	<br /><br />

	<table>
	
		<c:forEach var="topico" items="${lista}" varStatus="id">
			<tr>
				<td><a href="topicoListar?t=${topico.id}">${topico.id}.${topico.dataFormatada}</a></td>
				<td>${topico.texto}</td>
			</tr>
		</c:forEach>

		<tr><td colspan="2">&nbsp;</td></tr>
		
		<tr>
			<td colspan="2">
				[
					<c:forEach var="numeroPagina" begin="1" end="${paginacao}">
						<a href="/Forum/forum?p=${numeroPagina}">${numeroPagina}</a> 
					</c:forEach>
				]
			</td>
		</tr>
	</table>
		
<c:import url="../general/footer.jsp" />