# Alerta Cidadão — Global Solution FIAP 2026/2

Protótipo web acadêmico (HTML, CSS e JavaScript puro), sem backend, banco ou API.

## Como executar
Abra `index.html` no navegador. Use o menu para navegar entre as quatro páginas.

## Entidades propostas para alinhar com o grupo
- Usuario: id, nome, email, cidade
- AreaRisco: id, nome, cidade, estado, nivelRisco
- EventoClimatico: id, tipo, data, intensidade, areaId
- Alerta: id, tipo, nivel, mensagem, data, areaId
- Ocorrencia: id, tipo, descricao, data, usuarioId, areaId

Relacionamentos sugeridos: AreaRisco 1:N EventoClimatico; AreaRisco 1:N Alerta; AreaRisco 1:N Ocorrencia; Usuario 1:N Ocorrencia.

## Requisitos
- 4 páginas com navegação compartilhada e HTML semântico
- Arrays de objetos em JS, renderização e filtros pelo DOM
- Formulário validado com mensagens na própria página
- CSS responsivo para celular e desktop

## Limitações
Os dados e alertas são fictícios. Não há previsão meteorológica, notificação real ou persistência. Registros do formulário se perdem ao atualizar a página. Não usar para decisões de emergência.

## Uso de IA
Código inicial criado com apoio de IA generativa; o grupo deve revisar, entender, testar e declarar o uso conforme o enunciado da FIAP.

## Versão visual
Interface redesenhada; os arquivos JavaScript e as funcionalidades originais foram preservados. A fonte web usa fallback local se não houver conexão.
