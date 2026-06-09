# Sistema de Gerenciamento de Papelaria

## 📝 Descrição do Projeto
Projeto desenvolvido em Java para simular o gerenciamento de uma papelaria. O sistema permite controlar clientes, funcionários, produtos, fornecedores, estoque e vendas. Foi desenvolvido utilizando conceitos de Programação Orientada a Objetos (POO), padrão MVC e persistência de dados em arquivos `.txt`.

## ⚙️ Funcionalidades

- Cadastro de clientes
- Cadastro de funcionários
- Cadastro de produtos
- Cadastro de fornecedores
- Controle de estoque
- Registro de vendas
- Persistência de dados em arquivos
- Geração de logs do sistema

## 🎯 Objetivos e Conceitos Aplicados
Este trabalho atende aos requisitos da disciplina, incluindo:
- **Padrão MVC:** Separação clara entre Model, View e Controller.
- **POO:** Uso de herança (ex: Cliente herda de Pessoa), classes abstratas e polimorfismo.
- **Coleções:** Uso de `ArrayList` para manipulação de dados em memória.
- **Persistência:** Leitura e gravação de dados em arquivos de texto.
- **Logs:** Registro de atividades importantes (cadastros, erros, alterações) no arquivo `log.txt`.

## 📁 Estrutura de Pacotes
- `model`: Classes de entidade (Pessoa, Cliente, Funcionario, etc).
- `view`: Classes de interação com o usuário (Menus e inputs via Scanner).
- `controller`: Classes de regra de negócio e fluxo de dados.
- `interfaces`: Contratos implementados no sistema.
- `util`: Classes utilitárias (ex: ArquivoUtil para manipular os .txt).

## 🚀 Como Executar o Projeto
1. Clone este repositório: `git clone [https://github.com/lmanfron/sistema-papelaria.git]`
2. Abra o projeto na sua IDE de preferência (IntelliJ IDEA, VS Code ou Eclipse).
3. Execute o arquivo `Main.java` localizado no pacote raiz `papelaria`.
4. Os dados salvos serão armazenados automaticamente na pasta `dados/`.

## 👥 Integrantes e Responsabilidades
* **Luiz Henrique Manfron Campestrini:** Responsável pelos módulos de Cliente e Funcionário
* **Arthur Langa Dala Stella:** Responsável pelos módulos Produto e Categoria.
* **Luiz Guilherme Borghi Chuquer:** Responsável pelos módulos Fornecedor e Estoque.
* **Nome Integrante 4:** Responsável pelos módulos Venda e Pagamento.
* **Nome Integrante 5:** Responsável pelos módulos Pedidos e Relatórios.

## 🤖 Uso do ChatGPT
Utilizamos a inteligência artificial (ChatGPT / Claude) como uma ferramenta de apoio educacional para:
- Planejar a estrutura inicial de pastas e o padrão MVC.
- Apoio na documentação e estruturação do projeto.
- Tirar dúvidas pontuais sobre manipulação de arquivos no Java (`BufferedReader` e `BufferedWriter`).

## 📚 Referências
- Material de aula da disciplina de Programação Orientada a Objetos.
- Documentação oficial do Java.
