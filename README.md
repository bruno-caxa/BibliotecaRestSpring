# BibliotecaRestSpring

Back End de um projeto de estudos que simula uma biblioteca, feito em Spring Boot.

## Informações de funcionamento

Backend no formato de Rest API, com os componentes padrões do Spring Data, como controllers, services e repositories.
Também foi utilizado o Spring Security para realizar a validação se o usuario tem permissão para fazer determinada requisição, a parte de autenticação ficou responsável pelo JwtAuthFilter, onde é recebido o token JWT pelo header da requisição e verificado se o mesmo é válido para realizar a autenticação do usuario.

## Informações de desenvolvimento

- Rest API
- Uso da API Amazon S3, para armazenar imagens
- Token JWT para autenticação
- Controller Advice para tratamento personalizado de erros
- Spring Security
