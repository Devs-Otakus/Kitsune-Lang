

---

# 🦊 Kitsune Lang

**Kitsune Lang** é uma linguagem de script interpretada, feita em Java, criada como projeto educacional para explorar fundamentos de linguagens de programação e construção de interpretadores.  
Inspirada em em Linguagens funcional e fortementes tipadas, a Kitsune Lang foca na **simplicidade da sintaxe**, **execução em tempo real** e **facilidade de extensão**.

---

## 📚 Sumário

- [Objetivos do Projeto](#objetivos-do-projeto)
- [Filosofia e Pilares](#filosofia-e-pilares)
- [Visão Geral e Sintaxe](#visão-geral-e-sintaxe)
  - [Comentários](#comentários)
  - [Variáveis e Tipagem](#variáveis-e-tipagem)
  - [Funções](#funções)
  - [Controle de Fluxo](#controle-de-fluxo)
  - [Estruturas de Dados](#estruturas-de-dados)
  - [Objetos / Registros (Structs)](#objetos--registros-structs)
  - [Sistema de Módulos](#sistema-de-módulos)
- [Evolução Futura: Orientação a Objetos](#evolução-futura-orientação-a-objetos-oo)
- [Principais Conceitos Envolvidos](#principais-conceitos-envolvidos)
- [Estado Atual do Projeto](#estado-atual-do-projeto)
- [Equipe](#equipe)
- [Licença](#licença)

---

## ✨ Objetivos do Projeto

- Aprender e aplicar conceitos de **análise léxica**, **parsing** e **interpretação**
- Criar uma linguagem de script com sintaxe clara e expressiva
- Desenvolver um interpretador 100% funcional em Java
- Entender como linguagens reais são construídas por dentro

---

## ✨ Filosofia e Pilares

- **100% Tipada:** Todos os valores e expressões têm tipo definido estaticamente, garantindo segurança e previsibilidade.
- **Funcional (Foco Inicial):** Promove imutabilidade e trata funções como “cidadãos de primeira classe”.
- **Sintaxe Limpa e Distinta:** Não utiliza `{}` para blocos de código (reservadas para literais de objetos). Usa a palavra-chave `end` e setas (`->`, `<<`, `>>`) para fluxo e relações.

---

## 🦊 Visão Geral e Sintaxe

Kitsune Lang é projetada com foco em clareza, segurança de tipos e paradigma funcional, inspirando-se em Ruby, Kotlin e outras linguagens fortemente tipadas.

### Comentários

```kitsune
// Comentário de linha única

/*
  Comentário
  de múltiplas linhas.
*/
```

### Variáveis e Tipagem

- `let`: variável mutável
- `LET`: constante (imutável)
- Tipagem explícita ou inferida

```kitsune
let contador: Int = 0
let mensagem = "Olá, Kurama!"

LET PI: Float = 3.14159
LET ATIVO = true
```

### Funções

- Palavra-chave: `fun`
- Parâmetros: `nome: Tipo`
- Retorno: `-> Tipo` (ou `-> Void`)
- Blocos delimitados por `end`

```kitsune
fun somar(a: Int, b: Int) -> Int
    return a + b
end

fun saudacao(nome: String) -> Void
    print("Olá, ${nome}!")
end

// Função de ordem superior
fun aplicarOperacao(valor: Int, operacao: (Int) -> Int) -> Int
    return operacao(valor)
end
```

### Controle de Fluxo

- Condições sem parênteses
- Blocos com `end`

```kitsune
let idade = 20
if idade >= 18
    print("Maior de idade.")
else if idade >= 0
    print("Menor de idade.")
else
    print("Idade inválida.")
end

let contador = 0
while contador < 3
    print("Contador: ${contador}")
    contador = contador + 1
end

for i in 1..5
    print("Número: ${i}")
end
```

### Estruturas de Dados

#### Arrays / Listas

- Tipo: `List<ElementType>`
- Literal: `[ ]`
- Acesso: `[índice]`

```kitsune
let frutas: List<String> = ["Maçã", "Banana", "Uva"]
print("Primeira fruta: ${frutas[0]}")
for fruta in frutas
    print("Fruta: ${fruta}")
end
```

#### Objetos / Registros (Structs)

- Definidos com `def`
- Instanciados com `{ }`
- Campos acessados por ponto

```kitsune
def Produto {
    nome: String,
    preco: Int,
    categorias: List<String>
}

let meuNotebook: Produto = {
    nome: "Notebook Pro X",
    preco: 2500,
    categorias: ["Eletrônicos", "Informática"]
}
print "Detalhes do Produto: ${meuNotebook.nome}, Preço: R$${meuNotebook.preco}"
```

### Sistema de Módulos

- Declaração de pacote: `package "nome_do_pacote"`
- Exportação: `<<` (antes de `let`, `LET`, `fun`, `def`)
- Importação: `>> "caminho" (símbolos)`

```kitsune
// src/util/math.krm
package "util"

<< LET PI: Float = 3.14159
<< fun dobro(n: Int) -> Int
    return n * 2
end
<< def Ponto {
    x: Int,
    y: Int
}

// src/app/main.krm
package "app"
>> "util" (PI, dobro, Ponto as Coord)
```

---

## 📈 Evolução Futura: Orientação a Objetos (OO)

- Os `def` atuais são a base para dados estruturados.
- Futuramente: classes, encapsulamento, métodos, `this`, herança e polimorfismo.

---

## 🧠 Principais Conceitos Envolvidos

- Lexer (tokenização do código fonte)
- Parser (construção de AST)
- Visitor Pattern
- Execução baseada em AST (interpretação)
- Escopos, variáveis, controle de fluxo
- Extensibilidade do interpretador

---

## ✅ Estado Atual do Projeto

🚧 Em desenvolvimento

- [ ] Definição da sintaxe inicial
- [ ] Analisador léxico (lexer)
- [ ] Analisador sintático (parser)
- [ ] Árvore de Sintaxe Abstrata (AST)
- [ ] Interpretador básico (expressões e variáveis)
- [ ] Suporte a `if`, `while`, `print`
- [ ] Sistema de escopo e funções

---

## 🤝 Equipe

Projeto desenvolvido por entusiastas de programação e compiladores, com foco em aprendizado e experimentação.

---

## 📜 Licença

MIT License — use, modifique e distribua à vontade!

---