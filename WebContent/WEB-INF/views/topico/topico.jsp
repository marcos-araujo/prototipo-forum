<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="../general/header.jsp" />

	<a href="forum">[Home]</a>
	<h1>Fórum</h1>
	<hr />
	
	<table>
		<c:forEach var="topico" items="${pagina.lista}" varStatus="id">
			<tr>
				<td><a style="padding-left: ${topico.nivel}em;" href="javascript:responder(${topico.id});">${topico.texto}</a></td>
			</tr>
			<tr>
				<td id="${topico.id}" class="esconder">
					<form action="topicoAdicionar" method="post">
						<input name="idTopico" type="hidden" value="${param['t']}" />
						<input name="pagina" type="hidden" value="${param['pagina']}" />
						<input name="idPai" type="hidden" value="${topico.id}" />
						<input name="nivel" type="hidden" value="${topico.nivel+1}" />
						<textarea name="texto" rows="3" cols="40"></textarea>
						<br />
						<input class="botao" type="submit" value="Responder" />
					</form>
					<br />
				</td>
			</tr>
		</c:forEach>
	
		<tr><td colspan="2">&nbsp;</td></tr>
		
		<tr>
			<td colspan="2">
				[
					<c:forEach var="numeroPagina" begin="1" end="${pagina.paginacao}">
						<a href="topicoListar?pagina=${numeroPagina}&t=${param['t']}">${numeroPagina}</a> 
					</c:forEach>
				]
			</td>
		</tr>
	</table>

<c:import url="../general/footer.jsp" />		