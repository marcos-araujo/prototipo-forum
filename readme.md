# Protótipo simplificado de Fórum

<ul> 
	<li>Projeto feito um um contexto teste.</li>
	<li>Requisitos:
		<ul> 
			<li>Deve ser possível enviar perguntas e respostas. </li> 
			<li>Elas devem ser exibidas em formato de árvore por meio de indentação.</li>
			<li>Deve ser possível cadastras palavras em uma blacklist.</li>
			<li>As palavras da blacklist são substituidas por x nas perguntas e respostas.</li>
		</ul>
	</li>
	<li>Toda a lógica foi desenvolvida por mim, sem utilização de frameworks e seguindo o padrão de projeto MVC.</li>
	<li>Banco de dados MySQL. Duas tabelas: Topico e Blacklist.</li>
	<li>Camada de View: JSP, JSTL, JavaScript e CSS.</li>
	<li>Lógica de ordenação das threads: Fila ordena a exibição das threads em árvore.</li>
	<li>Mediação: Expressões regulares cobrem todas as possibilidades.</li>
</ul>