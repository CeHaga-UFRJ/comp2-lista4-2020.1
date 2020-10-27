<h2>Questionário</h2>

<h3>Os diagramas das questões 2 e 3 estão na pasta "models"</h3>

<h3>1. Quais foram as maiores dificuldades encontradas na resolução desse exercício e como conseguiu resolvê-las?</h3>
<p>Não tive tanta dificuldade, foi mais trabalhoso. Mas a parte que mais demorei foi na hora de fazer os rankings. Com um TreeSet para impedir duplicatas e alguns Comparators bem definidos, foi possível fazer as comparações.</p>

<h3>4. De que forma o atributo de pontos dos estudantes foi utilizado no seu sistema?</h3>
<p>Para um estudante pegar um livro, ele precisa ter pelo menos a quantidade de pontos desse livro. No momento que pega ele perde a quantidade de pontos do livro e no momento que retorna recebe o dobro, no final ficando com a pontuação do livro a mais.</p>

<h3>5. Quais foram os padrões de projeto aplicados no seu sistema e como foram implementados?</h3>
<ul>
  <li>Singleton</li>
  <pre>Classes como DataManager e StatsManager são únicos, pois os dados são comuns a todas as classes do programa.</pre>
  <li>Observer</li>
  <pre>Toda vez que acontece um empréstimo ou um cadastro, a classe DataManager notifica a StatsManager para atualizar as estatísticas.</pre>
  <li>Facade</li>
  <pre>A classe DataManager é responsável por toda integração com dados. Qualquer classe de fora, para poder acessar qualquer dado ou estatística, precisa passar por essa classe, centralizando assim as requisições a um único objeto e impedindo a chamada à classe StatsManager por clases externas.</pre>
</ul>
