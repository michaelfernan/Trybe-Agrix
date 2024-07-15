
# Projeto Final - Agrix

Bem-vindo ao repositório do "Projeto Final - Agrix", um projeto avaliativo dividido em três fases, desenvolvido durante o curso da Trybe. Este projeto simula a criação de uma API para gerenciamento de fazendas, plantações e fertilizantes, com funcionalidades que vão desde o cadastro até a autenticação e restrições de acesso.

## Estrutura do Projeto

O projeto está dividido em três fases principais:

- **Fase A**: Criação da API inicial com rotas para gerenciamento de fazendas e plantações.
- **Fase B**: Expansão da API, incluindo testes e funcionalidades adicionais como gestão de fertilizantes e buscas detalhadas.
- **Fase C**: Implementação de segurança através de autenticação e autorização, além da migração de código e melhorias de acessibilidade das rotas.

Cada fase constrói sobre a base da anterior, introduzindo novos conceitos e tecnologias.

### Fase A

- **POST /farms**: Cadastra novas fazendas.
- **GET /farms**: Lista todas as fazendas cadastradas.
- **GET /farms/{id}**: Retorna informações de uma fazenda específica.
- **POST /farms/{farmId}/crops**: Cadastra plantações em uma fazenda específica.
- **GET /farms/{farmId}/crops**: Lista plantações de uma fazenda específica.
- **GET /crops**: Lista todas as plantações cadastradas.
- **GET /crops/{id}**: Retorna informações de uma plantação específica.
- **Dockerfile**: Instruções para criar uma imagem Docker da aplicação.

### Fase B

- **Migração de código da Fase A**.
- **Testes de cobertura mínima de 80%** para `PersonService`.
- **Ajustes nas rotas de plantações** para uso de datas.
- **POST /fertilizers**: Cadastra novos fertilizantes.
- **GET /fertilizers**: Lista todos os fertilizantes cadastrados.
- **GET /fertilizers/{id}**: Retorna informações de um fertilizante específico.
- **POST /crops/{cropId}/fertilizers/{fertilizerId}**: Associa fertilizantes a plantações.
- **GET /crops/{cropId}/fertilizers**: Lista fertilizantes associados a uma plantação.

### Fase C

- **Migração de código da Fase B**.
- **POST /persons**: Cadastra novas pessoas no banco.
- **Autenticação**: Implementação de autenticação e geração de JWT.
- **Restrições de acesso**: Limitação de acesso a várias rotas com base na autenticação e roles.

## Como Executar

1. **Clone o Repositório**:
   ```bash
   git clone [URL_DO_REPOSITÓRIO]
   ```
2. **Instalação das Dependências**:
   ```bash
   npm install
   ```
3. **Execução em Desenvolvimento**:
   ```bash
   npm start
   ```
4. **Execução dos Testes**:
   ```bash
   npm test
   ```

## Contribuição

Este projeto é avaliativo e individual, não sendo permitido contribuições externas.

## Licença

Este projeto está licenciado sob a Licença MIT.
