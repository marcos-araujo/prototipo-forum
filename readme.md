# Prot�tipo simplificado de F�rum

<ul> 
	<li>Projeto feito um um contexto teste.</li>
	<li>Requisitos:
		<ul> 
			<li>Deve ser poss�vel enviar perguntas e respostas. </li> 
			<li>Elas devem ser exibidas em formato de �rvore por meio de indenta��o.</li>
			<li>Deve ser poss�vel cadastras palavras em uma blacklist.</li>
			<li>As palavras da blacklist s�o substituidas por x nas perguntas e respostas.</li>
		</ul>
	</li>
	<li>Toda a l�gica foi desenvolvida por mim, sem utiliza��o de frameworks e seguindo o padr�o de projeto MVC.</li>
	<li>Banco de dados MySQL. Duas tabelas: Topico e Blacklist.</li>
	<li>Camada de View: JSP, JSTL, JavaScript e CSS.</li>
	<li>L�gica de ordena��o das threads: Fila ordena a exibi��o das threads em �rvore.</li>
	<li>Media��o: Express�es regulares cobrem todas as possibilidades.</li>
</ul>