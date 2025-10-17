<h2>Desafio SCMovie Jacoco</h2>
<p>Você deve implementar todos os testes unitários de service para o projeto SCMovie.</p>

<p><h3>Sobre o projeto SCMovie:</h3>
Este é um projeto de filmes e avaliações de filmes. A visualização dos dados dos filmes é pública (não necessita login), porém as alterações de filmes (inserir, atualizar, deletar) são permitidas apenas para usuários ADMIN. As avaliações de filmes podem ser registradas por qualquer usuário logado CLIENT ou ADMIN. A entidade Score armazena uma nota de 0 a 5 (score) que cada usuário deu a cada filme. Sempre que um usuário registra uma nota, o sistema calcula a média das notas de todos usuários, e armazena essa nota média (score) na entidade Movie, juntamente com a contagem de votos (count).  Veja vídeo explicativo.</p>
 
<img width="889" height="267" alt="image" src="https://github.com/user-attachments/assets/469ac8d7-bd55-4b7e-93b8-e9d4fcef63a6" />

<p>Abaixo estão os testes unitários que você deverá implementar. Com todos os testes, o Jacoco deve reportar 100% de cobertura, mas o mínimo para aprovação no desafio são 12 dos 15 testes.</p>

<ul><h3>MovieServiceTests:</h3>
<li>findAllShouldReturnPagedMovieDTO</li>
<li>findByIdShouldReturnMovieDTOWhenIdExists</li>
<li>findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist</li>
<li>insertShouldReturnMovieDTO</li>
<li>updateShouldReturnMovieDTOWhenIdExists</li>
<li>updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist</li>
<li>deleteShouldDoNothingWhenIdExists</li>
<li>deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist</li>
<li>deleteShouldThrowDatabaseExceptionWhenDependentId</li></ul>
<ul><h3>ScoreServiceTests:</h3>
<li>saveScoreShouldReturnMovieDTO</li>
<li>saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId</li></ul>
<ul><h3>UserServiceTests:</h3>
<li>authenticatedShouldReturnUserEntityWhenUserExists</li>
<li>authenticatedShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists</li>
<li>loadUserByUsernameShouldReturnUserDetailsWhenUserExists</li>
<li>loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists</li></ul>
