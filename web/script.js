// Alerta Cidadão · protótipo web
// Dados de demonstração: os mesmos do sistema Java e do script SQL.

const regioes = [
  { id: 1, nome: "Centro Histórico", cidade: "Porto Alegre", estado: "RS", nivelRisco: "Crítico" },
  { id: 2, nome: "Sarandi", cidade: "Porto Alegre", estado: "RS", nivelRisco: "Alto" },
  { id: 3, nome: "Alto da Serra", cidade: "Petrópolis", estado: "RJ", nivelRisco: "Médio" },
  { id: 4, nome: "Zona Rural", cidade: "Cuiabá", estado: "MT", nivelRisco: "Alto" },
];

const alertas = [
  {
    id: 4, tipo: "Enchente", nivel: "Crítico", regiaoId: 1,
    mensagem: "Chuva extrema de 120 mm. Saia de áreas baixas e siga para um local alto.",
  },
  {
    id: 1, tipo: "Enchente", nivel: "Alto", regiaoId: 2,
    mensagem: "Chuva forte de 65 mm. Prepare documentos e evite ruas alagadas.",
  },
  {
    id: 2, tipo: "Deslizamento", nivel: "Alto", regiaoId: 2,
    mensagem: "Solo encharcado em área de risco. Observe rachaduras e saia se ouvir estalos.",
  },
  {
    id: 3, tipo: "Incêndio", nivel: "Alto", regiaoId: 4,
    mensagem: "Umidade do ar em 18%. Não faça queimadas e beba bastante água.",
  },
];

const cidadaos = [
  { nome: "Maria Souza", telefone: "51999990001", email: "maria@email.com", regiaoId: 1 },
  { nome: "João Pereira", telefone: "51999990002", email: "joao@email.com", regiaoId: 2 },
  { nome: "Ana Lima", telefone: "51999990003", email: "ana@email.com", regiaoId: 2 },
  { nome: "Carlos Rocha", telefone: "24999990004", email: "carlos@email.com", regiaoId: 3 },
  { nome: "Fernanda Alves", telefone: "65999990005", email: "fernanda@email.com", regiaoId: 4 },
];

const PESO = { "Baixo": 1, "Médio": 2, "Alto": 3, "Crítico": 4 };
const CLASSE = { "Baixo": "baixo", "Médio": "medio", "Alto": "alto", "Crítico": "critico" };

function regiaoPorId(id) {
  return regioes.find((r) => r.id === id);
}

function tag(nivel) {
  return `<span class="tag ${CLASSE[nivel]}">${nivel}</span>`;
}

function preencherRegioes(select, rotuloVazio) {
  select.innerHTML = `<option value="">${rotuloVazio}</option>` +
    regioes.map((r) => `<option value="${r.id}">${r.nome} · ${r.cidade}/${r.estado}</option>`).join("");
}

function linhaVazia(colunas, texto) {
  return `<tr><td colspan="${colunas}" class="muted">${texto}</td></tr>`;
}

// ---------- Dashboard ----------
function iniciarDashboard() {
  document.getElementById("total-areas").textContent = regioes.length;
  document.getElementById("total-alertas").textContent = alertas.length;
  document.getElementById("total-cidadaos").textContent = cidadaos.length;

  const ordenados = [...alertas].sort((a, b) => PESO[b.nivel] - PESO[a.nivel]);
  document.getElementById("recentes").innerHTML = ordenados.map((a) => {
    const r = regiaoPorId(a.regiaoId);
    return `<tr><td>${a.tipo}</td><td>${r.nome} · ${r.cidade}/${r.estado}</td><td>${tag(a.nivel)}</td></tr>`;
  }).join("");
}

// ---------- Áreas de risco ----------
function iniciarAreas() {
  const nivel = document.getElementById("filtro-nivel");
  const busca = document.getElementById("filtro-cidade");
  const corpo = document.getElementById("lista-areas");
  const contagem = document.getElementById("contagem");

  function render() {
    const texto = busca.value.trim().toLowerCase();
    const lista = regioes
      .filter((r) => !nivel.value || r.nivelRisco === nivel.value)
      .filter((r) => !texto || `${r.nome} ${r.cidade}`.toLowerCase().includes(texto))
      .sort((a, b) => PESO[b.nivelRisco] - PESO[a.nivelRisco]);

    contagem.textContent = `${lista.length} região(ões) encontrada(s)`;
    corpo.innerHTML = lista.length
      ? lista.map((r) => `<tr><td>${r.nome}</td><td>${r.cidade}/${r.estado}</td><td>${tag(r.nivelRisco)}</td></tr>`).join("")
      : linhaVazia(3, "Nenhuma região com esses filtros.");
  }

  nivel.addEventListener("change", render);
  busca.addEventListener("input", render);
  render();
}

// ---------- Alertas ----------
function iniciarAlertas() {
  const regiao = document.getElementById("filtro-regiao");
  const tipo = document.getElementById("filtro-tipo");
  const gravidade = document.getElementById("filtro-gravidade");
  const corpo = document.getElementById("lista-alertas");
  const contagem = document.getElementById("contagem");

  preencherRegioes(regiao, "Todas");

  function render() {
    const lista = alertas
      .filter((a) => !regiao.value || a.regiaoId === Number(regiao.value))
      .filter((a) => !tipo.value || a.tipo === tipo.value)
      .filter((a) => !gravidade.value || a.nivel === gravidade.value)
      .sort((a, b) => PESO[b.nivel] - PESO[a.nivel]);

    contagem.textContent = `${lista.length} alerta(s) ativo(s)`;
    corpo.innerHTML = lista.length
      ? lista.map((a) => {
        const r = regiaoPorId(a.regiaoId);
        return `<tr><td>${a.tipo}</td><td>${r.nome} · ${r.cidade}/${r.estado}</td><td>${tag(a.nivel)}</td><td>${a.mensagem}</td></tr>`;
      }).join("")
      : linhaVazia(4, "Nenhum alerta ativo com esses filtros.");
  }

  [regiao, tipo, gravidade].forEach((el) => el.addEventListener("change", render));
  render();
}

// ---------- Cadastro para receber avisos ----------
// Mesmas regras do Validador.java
function validarTelefone(valor) {
  const digitos = valor.replace(/[()\-\s]/g, "");
  if (!digitos) return "Informe o telefone.";
  if (!/^\d+$/.test(digitos)) return "Telefone deve conter apenas números.";
  if (digitos.length < 10 || digitos.length > 11) return "Telefone deve ter DDD + número (10 ou 11 dígitos).";
  return "";
}

function validarEmail(valor) {
  const e = valor.trim().toLowerCase();
  if (!e) return "Informe o e-mail.";
  if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(e)) return "E-mail inválido. Exemplo: nome@dominio.com";
  if (cidadaos.some((c) => c.email === e)) return "Este e-mail já está cadastrado.";
  return "";
}

function iniciarCadastro() {
  const form = document.getElementById("form-cadastro");
  const resultado = document.getElementById("resultado");
  const campos = {
    nome: document.getElementById("nome"),
    telefone: document.getElementById("telefone"),
    email: document.getElementById("email"),
    regiao: document.getElementById("regiao"),
    aceite: document.getElementById("aceite"),
  };

  preencherRegioes(campos.regiao, "Selecione");

  const regras = {
    nome: () => (campos.nome.value.trim().length >= 3 ? "" : "Informe seu nome (mínimo 3 letras)."),
    telefone: () => validarTelefone(campos.telefone.value),
    email: () => validarEmail(campos.email.value),
    regiao: () => (campos.regiao.value ? "" : "Selecione a região onde você mora."),
    aceite: () => (campos.aceite.checked ? "" : "Marque o aceite para receber os avisos."),
  };

  function validar(nome) {
    const erro = regras[nome]();
    document.getElementById(`${nome}-erro`).textContent = erro;
    campos[nome].classList.toggle("invalido", Boolean(erro));
    return !erro;
  }

  // Valida cada campo quando o usuário sai dele
  Object.keys(campos).forEach((nome) => {
    const evento = nome === "regiao" || nome === "aceite" ? "change" : "blur";
    campos[nome].addEventListener(evento, () => validar(nome));
  });

  form.addEventListener("submit", (e) => {
    e.preventDefault();
    resultado.innerHTML = "";
    const ok = Object.keys(campos).map(validar).every(Boolean);
    if (!ok) {
      form.querySelector(".invalido")?.focus();
      return;
    }

    const regiaoId = Number(campos.regiao.value);
    const r = regiaoPorId(regiaoId);
    cidadaos.push({
      nome: campos.nome.value.trim(),
      telefone: campos.telefone.value.replace(/[()\-\s]/g, ""),
      email: campos.email.value.trim().toLowerCase(),
      regiaoId,
    });

    const ativos = alertas.filter((a) => a.regiaoId === regiaoId);
    const listaAtivos = ativos.length
      ? `<div class="aviso-regiao"><b>Alertas ativos em ${r.nome} agora:</b><ul>${ativos.map((a) => `<li>${a.tipo} ${tag(a.nivel)} · ${a.mensagem}</li>`).join("")}</ul></div>`
      : `<div class="aviso-regiao">Nenhum alerta ativo em ${r.nome} no momento.</div>`;

    resultado.innerHTML = `Cadastro feito, ${campos.nome.value.trim().split(" ")[0]}! Você vai receber os avisos de ${r.nome} (${r.cidade}/${r.estado}).${listaAtivos}`;
    form.reset();
    Object.values(campos).forEach((c) => c.classList.remove("invalido"));
  });
}

const iniciar = {
  dashboard: iniciarDashboard,
  areas: iniciarAreas,
  alertas: iniciarAlertas,
  cadastro: iniciarCadastro,
};

iniciar[document.body.dataset.page]?.();
