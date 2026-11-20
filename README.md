# Alerta Cidadão

## Projeto Acadêmico — Global Solution FIAP 2026/2

O Alerta Cidadão é uma solução digital voltada ao monitoramento de situações de risco, organização de informações e comunicação de alertas à população.

O projeto busca auxiliar na identificação de possíveis situações de risco, permitindo o acompanhamento de regiões, cidadãos, leituras e alertas relacionados a eventos que possam representar ameaças à comunidade.

---

## 🎯 Objetivo do projeto

Desenvolver uma solução que contribua para o monitoramento de regiões e o gerenciamento de informações relacionadas a possíveis situações de risco, utilizando recursos tecnológicos para organizar dados e facilitar o acesso às informações.

O projeto foi desenvolvido em três módulos: aplicação Java, banco de dados e aplicação Web.

---

## ⚠️ O problema

Em maio de 2024, as enchentes no Rio Grande do Sul atingiram 469 das 497 cidades do estado, afetaram mais de 2,3 milhões de pessoas e causaram 183 mortes. Muitas famílias tiveram poucos minutos para sair de casa, ou nem isso.

O problema não é só a chuva: é a informação que não chega a tempo para quem mora na beira do rio ou na encosta do morro. E esse risco se repete todos os anos, com enchentes, deslizamentos, incêndios e ondas de calor.

---

## 💡 A solução

O Alerta Cidadão recebe leituras de chuva, nível do rio, temperatura e umidade de cada região. Quando um valor passa do limite de risco, o sistema gera o alerta automaticamente e avisa os moradores cadastrados naquela área, reduzindo o tempo entre o perigo e o aviso.

Regras de risco (limites de exemplo):

- Chuva a partir de 50 mm gera alerta de enchente (crítico a partir de 100 mm).
- Chuva a partir de 40 mm em área de risco gera alerta de deslizamento.
- Rio a partir de 3 m acima do normal gera alerta de enchente (crítico a partir de 5 m).
- Temperatura a partir de 38 °C gera alerta de onda de calor (crítico a partir de 42 °C).
- Umidade do ar até 20% gera alerta de incêndio (crítico até 12%).

Não são criados dois alertas ativos do mesmo tipo na mesma região: um alerta existente só é atualizado quando o novo risco é mais grave.

---

## 🌍 ODS relacionados

- **ODS 11 — Cidades e comunidades sustentáveis:** ajuda o município e a Defesa Civil a enxergar onde o risco está maior e a reduzir perdas causadas por desastres.
- **ODS 13 — Ação contra a mudança global do clima:** fortalece a capacidade de resposta da população a eventos climáticos extremos, que estão cada vez mais frequentes.
- **ODS 3 — Saúde e bem-estar:** o aviso antecipado protege vidas e reduz a exposição das famílias em áreas de risco.

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
Global-Solution-2026-1/
├── banco/
│   ├── alerta_cidadao.sql
│   ├── diagrama-er.pdf
│   ├── diagrama-er.png
│   └── diagrama-er.puml
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
│   ├── index.html
│   ├── areas.html
│   ├── alertas.html
│   ├── cadastro.html
│   ├── script.js
│   ├── style.css
│   └── README.md
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

### Diagrama entidade-relacionamento

O diagrama ER está na pasta `banco/` (`diagrama-er.pdf`, `diagrama-er.png` e `diagrama-er.puml`). Cada cidadão, leitura e alerta pertence a uma região, por meio das chaves estrangeiras `fk_cidadao_regiao`, `fk_leitura_regiao` e `fk_alerta_regiao`.

### Como executar

1. Abra o Oracle SQL Developer e conecte-se ao banco.
2. Abra o arquivo `banco/alerta_cidadao.sql`.
3. Execute como script (F5).

O script remove as tabelas do projeto, se já existirem, cria tudo novamente, insere os dados de exemplo (os mesmos da aplicação Java) e termina com a consulta das regiões em risco: alertas ativos, nível mais grave e quantidade de moradores a avisar. Pode ser executado várias vezes sem erro.

**Atenção:** a execução apaga as tabelas REGIAO, CIDADAO, LEITURA e ALERTA existentes e seus dados.

---

## 🌐 3. Aplicação Web

A aplicação Web apresenta uma interface voltada à visualização de informações relacionadas ao monitoramento de regiões e alertas.

Foram utilizados HTML, CSS e JavaScript para desenvolver a interface e suas funcionalidades.

### Páginas

- `index.html`: painel com totais e alertas ativos.
- `areas.html`: regiões monitoradas, com filtro por nível de risco e busca por nome ou cidade.
- `alertas.html`: alertas ativos, com filtros por região, tipo de risco e gravidade.
- `cadastro.html`: cadastro para receber avisos, com validação dos campos na própria página.

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

**Diagrama de classes (UML)** da aplicação Java, na pasta `diagramas/`:

- `diagrama-classes.pdf`
- `diagrama-classes.png`
- `diagrama-classes.puml`

**Diagrama entidade-relacionamento (ER)** do banco de dados, na pasta `banco/`:

- `diagrama-er.pdf`
- `diagrama-er.png`
- `diagrama-er.puml`

Os dois diagramas são coerentes entre si: as associações das classes `Cidadao`, `Leitura` e `Alerta` com `Regiao` correspondem às chaves estrangeiras das tabelas.

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
  <strong>Alerta Cidadão — Global Solution FIAP 2026/2</strong>
  <br />
  Projeto acadêmico desenvolvido por estudantes da FIAP.
</p>
