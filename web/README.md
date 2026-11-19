# Alerta Cidadão — Global Solution FIAP 2026/2

Protótipo web acadêmico (HTML, CSS e JavaScript puro), sem backend, banco ou API.

## Como executar
Abra `index.html` no navegador. Use o menu para navegar entre as quatro páginas.

## Páginas
- `index.html`: painel com totais de áreas, alertas ativos e moradores cadastrados, e a lista de alertas ativos
- `areas.html`: regiões monitoradas, com filtro por nível de risco e busca por nome ou cidade
- `alertas.html`: alertas ativos, com filtros por região, tipo de risco e gravidade
- `cadastro.html`: cadastro para receber avisos (nome, telefone, e-mail, região e aceite), com validação e mensagens na própria página

## Dados
Os dados ficam em arrays de objetos em `script.js` e são os mesmos do sistema Java (`DadosExemplo.java`) e do script do banco: regiões, alertas e cidadãos. A validação do telefone e do e-mail segue as mesmas regras do `Validador.java`.

## Requisitos atendidos
- 4 páginas com navegação compartilhada e HTML semântico
- Arrays de objetos em JS, renderização e filtros pelo DOM
- Formulário validado com mensagens na própria página
- CSS responsivo para celular e desktop

## Limitações
Os dados e alertas são fictícios. Não há previsão meteorológica, envio real de SMS ou persistência. Cadastros feitos no formulário se perdem ao atualizar a página. Não usar para decisões de emergência.

## Uso de IA
Código criado com apoio de IA generativa; o grupo deve revisar, entender, testar e declarar o uso conforme o enunciado da FIAP.
