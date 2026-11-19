# Alerta Cidadão

## Projeto Acadêmico — Global Solution FIAP 2026.1

**Uma solução digital para simular o monitoramento de áreas de risco e a apresentação de alertas à população.**

O **Alerta Cidadão** é um projeto acadêmico que busca demonstrar como a tecnologia pode auxiliar na identificação de situações de risco, organização de informações e comunicação preventiva com os cidadãos.

A proposta contempla situações como enchentes, deslizamentos, incêndios e ondas de calor, por meio de três módulos: aplicação Java, banco de dados e aplicação Web.

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

## 🎯 1. Objetivo do projeto

Desenvolver uma solução digital composta por três módulos que simulam o acompanhamento de regiões, cidadãos, leituras de sensores e alertas relacionados a situações de risco.

## 📁 2. Estrutura do projeto

```text
Global Solution 2026.1/
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
│   ├── areas.html
│   ├── alertas.html
│   ├── cadastro.html
│   ├── style.css
│   └── README.md
│
├── integrantes.txt
└── README.md
```

## ☕ 3. Módulo Java

A aplicação Java funciona por meio de um menu interativo no console e possui uma organização em camadas, separando a interface, as regras de negócio e as entidades do sistema.

### Organização das classes

| Camada | Responsabilidade |
|---|---|
| `model` | Representação das entidades do sistema. |
| `service` | Implementação das regras de negócio e operações. |
| `view` | Interface de interação com o usuário pelo console. |
| `Main` | Inicialização da aplicação. |

### Entidades principais

- **Regiao:** representa as regiões monitoradas.
- **Cidadao:** armazena informações dos cidadãos.
- **Leitura:** representa os dados obtidos pelos sensores.
- **Alerta:** representa os alertas associados às regiões.

### Funcionalidades

- Cadastro, consulta, atualização e remoção de cidadãos.
- Cadastro e consulta de regiões.
- Registro e consulta de leituras de sensores.
- Identificação de situações de risco.
- Geração e consulta de alertas.
- Validação de informações inseridas pelo usuário.

## 🗄️ 4. Banco de dados

O projeto utiliza um banco de dados relacional desenvolvido em **MySQL**, organizado em quatro tabelas principais.

| Tabela | Descrição |
|---|---|
| `regiao` | Armazena as regiões monitoradas e sua identificação de área de risco. |
| `cidadao` | Armazena os dados dos cidadãos e sua região associada. |
| `leitura` | Registra as leituras dos sensores vinculadas às regiões. |
| `alerta` | Armazena os alertas gerados e seus respectivos níveis de risco. |

### Relacionamentos

- Uma região pode estar associada a vários cidadãos.
- Uma região pode possuir várias leituras.
- Uma região pode possuir vários alertas.

Os relacionamentos são estabelecidos por meio de chaves estrangeiras, utilizando o campo `id_regiao`.

### Recursos implementados

- Chaves primárias (PK).
- Chaves estrangeiras (FK).
- Restrições de unicidade (`UNIQUE`).
- Campos obrigatórios (`NOT NULL`).
- Restrições de validação (`CHECK`).
- Inserção de dados para demonstração.
- Consulta utilizando `INNER JOIN`.

📄 **Script SQL:** `banco/alerta_cidadao.sql`

**Requisito:** MySQL 8.0.16 ou superior.

## 🌐 5. Aplicação Web

A aplicação Web foi desenvolvida com HTML5, CSS3 e JavaScript puro, apresentando uma interface responsiva e navegação entre diferentes páginas.

### Páginas disponíveis

| Página | Funcionalidade |
|---|---|
| `index.html` | Painel inicial com totais de áreas, alertas ativos e moradores cadastrados. |
| `areas.html` | Apresentação das regiões monitoradas, com filtros por nível de risco e busca por nome ou cidade. |
| `alertas.html` | Consulta de alertas ativos, com filtros por região, tipo de risco e gravidade. |
| `cadastro.html` | Formulário para cadastro de pessoas interessadas em receber avisos. |

### Características da aplicação

- Navegação compartilhada entre as páginas.
- Dados organizados em arrays de objetos JavaScript.
- Exibição e filtragem de informações na interface.
- Formulário com validação e mensagens na própria página.
- Layout responsivo para computadores e dispositivos móveis.

### ⚠️ Limitações da simulação

A aplicação utiliza dados fictícios e possui finalidade acadêmica e demonstrativa. Não realiza previsão meteorológica, envio real de SMS ou armazenamento permanente dos cadastros. As informações inseridas no formulário são perdidas ao atualizar a página.

O protótipo não deve ser utilizado para decisões reais de emergência.

## 📊 6. Diagramas

O repositório disponibiliza o diagrama de classes da aplicação Java nos seguintes formatos:

- PDF: `diagrama-classes.pdf`
- Imagem: `diagrama-classes.png`
- PlantUML: `diagrama-classes.puml`

Os arquivos estão disponíveis na pasta `diagramas/` e permitem consultar a estrutura das classes e seus relacionamentos.

## ▶️ 7. Como executar o projeto

### ☕ Java

1. Abra a pasta `java` em uma IDE compatível com Java.
2. Localize o arquivo `Main.java`.
3. Execute a aplicação para acessar o menu pelo console.

### 🗄️ Banco de dados

1. Instale e inicie o MySQL 8.0.16 ou superior.
2. Abra o arquivo `banco/alerta_cidadao.sql` em uma ferramenta compatível.
3. Execute o script para criar o banco, as tabelas e inserir os dados de demonstração.

> **Atenção:** o script contém comandos `DROP TABLE` que removem os dados existentes nas quatro tabelas antes de recriá-las.

### 🌐 Aplicação Web

1. Acesse a pasta `web`.
2. Abra o arquivo `index.html` em um navegador.
3. Utilize o menu da aplicação para navegar entre as páginas.

## 🔗 8. Links do projeto

| Recurso | Link |
|---|---|
| Repositório GitHub | [Global Solution 2026.1](https://github.com/FIAP-ON-2025/Global-Solution-2026-1) |
| Aplicação Web — GitHub Pages | Pendente de publicação |
| Vídeo Pitch | Pendente de gravação e publicação |

## 👥 9. Integrantes

O grupo é composto por **cinco integrantes**.

A relação dos participantes, acompanhada dos respectivos RMs, está disponível no arquivo [`integrantes.txt`](integrantes.txt).

## 🤖 10. Uso de inteligência artificial

Foi utilizada ferramenta de inteligência artificial generativa como apoio à orientação técnica relacionada à organização do repositório e à elaboração desta documentação.

As informações e os materiais do projeto devem ser revisados e validados pelos integrantes do grupo.

---

<p align="center">
  <strong>Global Solution FIAP 2026.1</strong><br/>
  <sub>Alerta Cidadão — Tecnologia e prevenção a serviço da sociedade.</sub>
</p>
