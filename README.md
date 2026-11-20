#  Alerta Cidadão

## Projeto Acadêmico — Global Solution FIAP 2026.1

Uma solução digital voltada ao monitoramento de situações de risco, organização de informações e comunicação de alertas à população.

O **Alerta Cidadão** tem como objetivo auxiliar na identificação de possíveis situações de risco, permitindo o acompanhamento de regiões, cidadãos, leituras e alertas relacionados a eventos que possam representar ameaças à comunidade.

---

## 🎯 Objetivo do projeto

Desenvolver uma solução que contribua para o monitoramento de regiões e o gerenciamento de informações relacionadas a possíveis situações de risco, utilizando recursos tecnológicos para organizar dados e facilitar o acesso às informações.

O projeto foi desenvolvido em três módulos: aplicação Java, banco de dados e aplicação Web.

---

## 🛠️ Tecnologias utilizadas

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" />
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" />
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" />
</p>

---

## 📁 Estrutura do projeto

```text
Global-Solution-2026-1/
│
├── banco/
│   └── alerta_cidadao.sql
│
├── diagramas/
│   ├── diagrama-classes.pdf
│   ├── diagrama-classes.png
│   └── diagrama-classes.puml
│
├── java/
│   └── src/
│       ├── Main.java
│       ├── model/
│       ├── service/
│       └── view/
│
├── web/
│   ├── index.html
│   ├── arquivos da aplicação Web
│   └── README.md
│
├── .github/
│   └── workflows/
│       └── static.yml
│
├── integrantes.txt
└── README.md
```

---

## ☕ 1. Aplicação Java

A aplicação Java foi desenvolvida com organização em classes e separação de responsabilidades, utilizando uma estrutura dividida em modelos, serviços e interface de interação.

Entre as funcionalidades desenvolvidas estão:

- Cadastro, consulta, atualização e remoção de regiões.
- Gerenciamento de cidadãos.
- Registro e consulta de leituras.
- Geração e gerenciamento de alertas.
- Validações de dados durante as operações.

A aplicação utiliza classes responsáveis pela representação das entidades e serviços que executam as operações do sistema.

### Como executar

1. Abra o projeto em uma IDE compatível com Java.
2. Localize o arquivo `Main.java`.
3. Execute a aplicação.
4. Utilize o menu apresentado no terminal para acessar as funcionalidades.

---

## 🗄️ 2. Banco de dados

O banco de dados foi desenvolvido em MySQL, utilizando relacionamentos entre as entidades do projeto.

O script está disponível em:

`banco/alerta_cidadao.sql`

### Tabelas utilizadas

- **regiao:** armazena informações das regiões monitoradas.
- **cidadao:** registra os cidadãos associados às regiões.
- **leitura:** armazena informações das leituras realizadas.
- **alerta:** registra os alertas relacionados às situações de risco.

### Recursos implementados

- Chaves primárias (PRIMARY KEY).
- Chaves estrangeiras (FOREIGN KEY).
- Restrições de integridade (NOT NULL).
- Restrições de unicidade (UNIQUE).
- Restrições de validação (CHECK).
- Registros de exemplo (INSERT).
- Consultas utilizando relacionamentos entre tabelas (INNER JOIN).

### Como executar

1. Abra o MySQL ou uma ferramenta compatível.
2. Localize o arquivo `alerta_cidadao.sql`.
3. Execute o script no ambiente MySQL.

**Atenção:** o script contém comandos DROP TABLE para recriar as tabelas. Sua execução remove as tabelas existentes com esses nomes e seus dados.

Recomendação: utilizar MySQL 8.0.16 ou superior para garantir o suporte às restrições CHECK utilizadas.

---

## 🌐 3. Aplicação Web

A aplicação Web apresenta uma interface voltada à visualização de informações relacionadas ao monitoramento de regiões e alertas.

Foram utilizados HTML, CSS e JavaScript para desenvolver a interface e suas funcionalidades.

### Recursos desenvolvidos

- Interface com informações organizadas sobre o projeto.
- Visualização de regiões e alertas.
- Utilização de arrays em JavaScript para organizar os dados.
- Filtros e manipulação de informações no DOM.
- Formulário com validação de campos.
- Layout responsivo para diferentes tamanhos de tela.

**Observação:** a aplicação Web utiliza dados fictícios e não possui integração com serviços externos de previsão meteorológica ou envio real de SMS. Os cadastros realizados na interface não são armazenados em um banco de dados.

### Acesso à aplicação

🌐 **GitHub Pages:**

https://fiap-on-2025.github.io/Global-Solution-2026-1/

---

## 📊 4. Diagramas

O projeto possui documentação visual da estrutura da aplicação Java por meio de um diagrama de classes UML.

Arquivos disponíveis na pasta `diagramas/`:

- `diagrama-classes.pdf`
- `diagrama-classes.png`
- `diagrama-classes.puml`

O diagrama apresenta as principais classes, seus relacionamentos e a organização dos componentes da aplicação.

---

## 🎥 5. Vídeo Pitch

A apresentação do projeto está disponível no YouTube:

▶️ **Assistir ao vídeo pitch:**

https://youtu.be/fTa_VRSmF3A

---

## 🔗 6. Repositório

O código-fonte e os demais arquivos do projeto estão disponíveis no GitHub.

📂 **Repositório oficial:**

https://github.com/FIAP-ON-2025/Global-Solution-2026-1

---

## 👥 7. Integrantes

O grupo é composto por cinco integrantes.

A relação com os nomes completos e respectivos RMs está disponível no arquivo:

`integrantes.txt`

---

## 🤖 8. Utilização de Inteligência Artificial

Foi utilizada ferramenta de inteligência artificial generativa como apoio durante o desenvolvimento do trabalho, principalmente para orientação técnica, esclarecimento de dúvidas, organização do repositório e revisão da documentação.

As decisões de implementação, os códigos e os materiais entregues devem ser compreendidos, revisados e validados pelos integrantes da equipe.

---

<p align="center">
  <strong>Alerta Cidadão — Global Solution FIAP 2026.1</strong>
  <br />
  Projeto acadêmico desenvolvido por estudantes da FIAP.
</p>
