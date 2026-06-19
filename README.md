# Sistema de Gerenciamento de Papelaria

Projeto em Java puro para um trabalho em grupo de Programacao Orientada a Objetos.
O sistema simula a gestao basica de uma papelaria usando MVC, colecoes, persistencia
em arquivos `.txt`, logs e conceitos de POO.

## Funcionalidades implementadas

- CRUD de clientes.
- CRUD de funcionarios.
- CRUD de categorias.
- CRUD de produtos.
- Consulta de produtos com estoque baixo.
- Persistencia automatica na pasta `dados/`.
- Registro de atividades e erros em `dados/log.txt`.

Com esses modulos, o projeto possui pelo menos 3 CRUDs completos, conforme solicitado
no trabalho.

## Conceitos aplicados

- MVC: separacao entre `model`, `view` e `controller`.
- Classe abstrata: `Pessoa`.
- Heranca: `Cliente` e `Funcionario` herdam de `Pessoa`.
- Polimorfismo: `getTipo()` e sobrescrito nas subclasses de `Pessoa`.
- Interface: `Identificavel`, implementada por entidades identificaveis.
- Colecoes: uso de `ArrayList` nos controllers.
- Associacao: `Produto` possui uma `Categoria`.
- Tratamento de excecoes: validacao de dados e tratamento de arquivos.
- Persistencia: leitura e escrita de arquivos de texto.
- Log: historico de operacoes em arquivo.

## Estrutura do projeto

```text
src/
└── papelaria/
    ├── Main.java
    │
    ├── controller/
    │   ├── CategoriaController.java
    │   ├── ClienteController.java
    │   ├── EstoqueController.java
    │   ├── FornecedorController.java
    │   ├── FuncionarioController.java
    │   ├── PagamentoController.java
    │   ├── PedidoController.java
    │   ├── ProdutoController.java
    │   ├── RelatorioController.java
    │   └── VendaController.java
    │
    ├── model/
    │   ├── Pessoa.java         
    │   ├── Cliente.java         
    │   ├── Funcionario.java     
    │   ├── Categoria.java
    │   ├── Estoque.java
    │   ├── Fornecedor.java
    │   ├── Pagamento.java
    │   ├── PagamentoCartao.java
    │   ├── PagamentoPix.java
    │   ├── Pedido.java
    │   ├── Produto.java
    │   └── Venda.java
    │
    ├── view/
    │   ├── CategoriaView.java
    │   ├── ClienteView.java
    │   ├── EstoqueView.java
    │   ├── FornecedorView.java
    │   ├── FuncionarioView.java
    │   ├── PagamentoView.java
    │   ├── PedidoView.java
    │   ├── ProdutoView.java
    │   ├── RelatorioView.java
    │   └── VendaView.java
    │
    ├── interfaces/
    │   └── Identificavel.java
    │
    ├── enums/
    │   └── StatusFornecedor.java
    │
    ├── exception/
    │   ├── CnpjInvalidoException.java
    │   ├── EstoqueInsuficienteException.java
    │   ├── FornecedorJaCadastradoException.java
    │   ├── FornecedorNaoEncontradoException.java
    │   ├── IdInvalidoException.java
    │   ├── ProdutoJaCadastradoException.java
    │   ├── ProdutoNaoEncontradoException.java
    │   └── QuantidadeInvalidaException.java
    │
    └── util/
        └── ArquivoUtil.java
```

## Como executar

No terminal, dentro da pasta do projeto:

```bash
javac -encoding UTF-8 -d out src/papelaria/Main.java src/papelaria/controller/*.java src/papelaria/model/*.java src/papelaria/view/*.java src/papelaria/interfaces/*.java src/papelaria/util/*.java
java -cp out papelaria.Main
```

Tambem e possivel abrir o projeto no IntelliJ IDEA, VS Code ou Eclipse e executar
a classe `papelaria.Main`.

## Dados gerados

Os arquivos abaixo sao criados automaticamente durante a execucao:

- `dados/clientes.txt`
- `dados/funcionarios.txt`
- `dados/categorias.txt`
- `dados/fornecedores.txt`
- `dados/estoque.txt`
- `dados/produtos.txt`
- `dados/vendas.txt`
- `dados/pagamentos.txt`
- `dados/log.txt`

Esses arquivos ficam fora do versionamento porque representam dados locais de uso.

## Integrantes e responsabilidades

- Luiz Henrique Manfron Campestrini: modulos Cliente e Funcionario.
- Arthur Langa Dala Stella: modulos Produto e Categoria.
- Luiz Guilherme Borghi Chuquer: modulos Fornecedor e Estoque.
- André Gadens: modulos Venda e Pagamento.
- Edison Zoarces Zaneta Negrão: modulos Pedidos e Relatorios.

## Uso de IA

A IA foi usada como apoio para revisar os requisitos do trabalho, integrar os modulos
desenvolvidos separadamente, ajustar a organizacao do repositorio e conferir a
compilacao do projeto. O codigo foi mantido simples para facilitar a apresentacao e
a defesa individual.

## Referencias

- Material de aula da disciplina de Programacao Orientada a Objetos.
- Documentacao oficial do Java.
