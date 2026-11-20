# Alerta Cidadão

## Projeto Acadêmico — Global Solution FIAP 2026.2

O Alerta Cidadão é uma solução digital voltada ao monitoramento de situações de risco, organização de informações e comunicação de alertas à população.

O projeto busca auxiliar na identificação de possíveis situações de risco, permitindo o acompanhamento de regiões, cidadãos, leituras e alertas relacionados a eventos que possam representar ameaças à comunidade.

---

## 🎯 Objetivo do projeto

Desenvolver uma solução que contribua para o monitoramento de regiões e o gerenciamento de informações relacionadas a possíveis situações de risco, utilizando recursos tecnológicos para organizar dados e facilitar o acesso às informações.

O projeto foi desenvolvido em três módulos: aplicação Java, banco de dados e aplicação Web.

---

## 🛠️ Tecnologias utilizadas

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white" />
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" />
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" />
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" />
</p>

---

## 📁 Estrutura do projeto

```text
Global-Solution-2026-2/
├── banco/
│   └── alerta_cidadao.sql
├── diagramas/
│   ├── diagrama-classes.pdf
│   ├── diagrama-classes.png
│   └── diagrama-classes.puml
├── java/
│   └── src/
│       ├── Main.java
│       ├── model/
│       ├── service/
│       └── view/
├── web/
│   └── arquivos da aplicação Web
├── .github/
│   └── workflows/
│       └── static.yml
├── integrantes.txt
└── README.md
```

---

## ☕ 1. Aplicação Java

A aplicação Java foi desenvolvida com organização em classes e separação de responsabilidades, utilizando modelos, serviços e interface de interação.

Entre as funcionalidades desenvolvidas estão:

- Cadastro, consulta, atualização e remoção de regiões.
- Gerenciamento de cidadãos.
- Registro e consulta de leituras.
- Geração e gerenciamento de alertas.
- Validações de dados durante as operações.

### Como executar

1. Abra o projeto em uma IDE compatível com Java.
2. Localize o arquivo `Main.java`.
3. Execute a aplicação.
4. Utilize o menu apresentado no terminal.

---

## 🗄️ 2. Banco de dados — Oracle

O projeto utiliza Oracle como tecnologia de banco de dados, com modelagem das entidades e relacionamentos necessários para representar as informações do Alerta Cidadão.

O script está disponível em:

`banco/alerta_cidadao.sql`

### Tabelas do projeto

- **REGIAO:** armazena informações das regiões monitoradas.
- **CIDADAO:** registra os cidadãos associados às regiões.
- **LEITURA:** armazena informações das leituras realizadas.
- **ALERTA:** registra os alertas relacionados às situações de risco.

### Recursos da modelagem

- Chaves primárias (PRIMARY KEY).
- Chaves estrangeiras (FOREIGN KEY).
- Restrições de integridade (NOT NULL).
- Restrições de unicidade (UNIQUE).
- Restrições de validação (CHECK).
- Registros de exemplo (INSERT).
- Consultas utilizando relacionamentos entre tabelas (JOIN).

### Ferramenta de modelagem

A modelagem do banco de dados pode ser realizada por meio do Oracle SQL Developer Data Modeler, utilizado para elaborar e visualizar diagramas entidade-relacionamento.

**Observação:** a identificação do banco foi atualizada para Oracle. A compatibilidade e a execução do script SQL devem ser verificadas no ambiente Oracle utilizado pela equipe.

---

## 🌐 3. Aplicação Web

A aplicação Web apresenta uma interface voltada à visualização de informações relacionadas ao monitoramento de regiões e alertas.

Foram utilizados HTML, CSS e JavaScript para desenvolver a interface e suas funcionalidades.

### Recursos desenvolvidos

- Interface com informações organizadas sobre o projeto.
- Visualização de regiões e alertas.
- Utilização de arrays de objetos em JavaScript.
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

O diagrama entidade-relacionamento (ER) também faz parte da documentação prevista para a modelagem do banco de dados.

---

## 🎥 5. Vídeo Pitch

A apresentação do projeto está disponível no YouTube:

▶️ **Assistir ao vídeo pitch:**

https://youtu.be/fTa_VRSmF3A

---

## 🔗 6. Repositório

📂 **Repositório oficial:**

https://github.com/FIAP-ON-2025/Global-Solution-2026-1

---

## 👥 7. Integrantes

O grupo é composto por cinco integrantes.

A relação com os nomes completos e respectivos RMs está disponível no arquivo `integrantes.txt`.

---

## 🤖 8. Utilização de Inteligência Artificial

Foi utilizada ferramenta de inteligência artificial generativa como apoio durante o desenvolvimento do trabalho, principalmente para orientação técnica, esclarecimento de dúvidas, organização do repositório e revisão da documentação.

As decisões de implementação, os códigos e os materiais entregues devem ser compreendidos, revisados e validados pelos integrantes da equipe.

---

<p align="center">
  <strong>Alerta Cidadão — Global Solution FIAP 2026.2</strong>
  <br />
  Projeto acadêmico desenvolvido por estudantes da FIAP.
</p>
