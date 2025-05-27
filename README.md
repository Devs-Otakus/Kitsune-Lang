# 🦊 Kurama Lang

Kurama Lang é uma linguagem de script interpretada, feita com Java, criada como um projeto educacional para aprender os fundamentos de linguagens de programação e construção de interpretadores.

Inspirada em linguagens como Python, Lua e JavaScript, Kurama Lang foca na **simplicidade da sintaxe**, **execução em tempo real**, e **facilidade de extensão**.

---

## ✨ Objetivos do Projeto

- Aprender e aplicar conceitos de **análise léxica**, **parsing** e **interpretação**.
- Criar uma linguagem de script com sintaxe clara e expressiva.
- Desenvolver um interpretador 100% funcional em Java.
- Entender como linguagens reais são construídas por dentro.

---

## 🦊 Visão Geral e Sintaxe (Design Atual)

Kurama Lang é uma linguagem de programação em desenvolvimento, projetada com foco em clareza, segurança de tipos e um paradigma funcional, inspirando-se em elementos de linguagens como Ruby, Kotlin e linguagens fortemente tipadas.


## ✨ Filosofia e Pilares
 * 100% Tipada: Todos os valores e expressões têm um tipo definido estaticamente, garantindo segurança e previsibilidade.
 * Funcional (Foco Inicial): Promove a imutabilidade e trata funções como "cidadãos de primeira classe".
 * Sintaxe Limpa e Distinta: Abandona o uso tradicional de chaves {} para blocos de código (funções, condicionais, loops), reservando-as exclusivamente para literais de objetos/registros. Adota a palavra-chave end para delimitar blocos e setas (->, <<, >>) para expressar fluxo e relações.

## 🚀 Sintaxe Básica

Comentários
```kurama
// Este é um comentário de linha única

/*
  Este é um comentário
  de múltiplas linhas.
*/
```

Variáveis e Tipagem
```kurama
 * let (mutável): Variáveis cujo valor pode ser alterado.
 * LET (imutável/constante): Variáveis cujo valor não pode ser alterado após a inicialização.
 * Declaração de Tipo: Usando : (dois pontos), tipo obrigatório se não houver inferência.
let contador: Int = 0          // Variável mutável, tipo explícito
let mensagem = "Olá, Kurama!" // Variável mutável, tipo String inferido

LET PI: Float = 3.14159        // Constante imutável, tipo Float explícito
LET ATIVO = true               // Constante imutável, tipo Bool inferido
```

Funções
 * Palavra-chave: fun
 * Parâmetros: nome: Tipo
 * Retorno: -> Tipo (ou -> Void para funções sem retorno).
 * Blocos: Delimitados por end. Não usam {}.
 * Sem this: Em funções, argumentos são acessados diretamente (modelo funcional).
 * Funções de Ordem Superior (HOFs): Suportadas.
```kurama
// Função simples que soma dois inteiros e retorna um inteiro
fun somar(a: Int, b: Int) -> Int
    return a + b
end

// Função que imprime uma saudação e não retorna nada
fun saudacao(nome: String) -> Void
    print("Olá, ${nome}!") // Interpolação de String!
end

// Exemplo de Função de Ordem Superior (recebe uma função como argumento)
fun aplicarOperacao(valor: Int, operacao: (Int) -> Int) -> Int
    return operacao(valor)
end

fun main() -> Void
    saudacao("Time Kurama")
    let resultadoSoma = somar(10, 5)
    print("Resultado da soma: ${resultadoSoma}")

    // Usando uma HOF
    let dobro = fun(n: Int) -> Int // Função anônima
        return n * 2
    end
    let valorDuplicado = aplicarOperacao(7, dobro)
    print("O dobro de 7 é: ${valorDuplicado}")
end
```

Controle de Fluxo
 * Condições não usam parênteses ().
 * Blocos são delimitados por end.
```kurama
// Condicionais (if/else if/else)
let idade = 20
if idade >= 18
    print("Maior de idade.")
else if idade >= 0
    print("Menor de idade.")
else
    print("Idade inválida.")
end

// Laço (while)
let contador = 0
while contador < 3
    print("Contador: ${contador}")
    contador = contador + 1
end

// Laço (for) - para iteração sobre ranges ou coleções
for i in 1..5 // Range de 1 a 5
    print("Número: ${i}")
end
```

📦 Estruturas de Dados
Arrays / Listas
 * Coleções ordenadas e homogêneas (todos os elementos do mesmo tipo).
 * Tipo: List<ElementType>
 * Literal: Colchetes []
 * Acesso: Índice dentro de colchetes []
```kurama
let frutas: List<String> = ["Maçã", "Banana", "Uva"]
print("Primeira fruta: ${frutas[0]}") // Acesso por índice (base 0)

for fruta in frutas
    print("Fruta: ${fruta}")
end
```

Objetos / Registros (Structs)
 * Representam estruturas de dados com campos nomeados e tipos fixos.
 * Não são objetos dinâmicos como em JavaScript (sem um def correspondente).
 * Definição do Tipo: Usando a palavra-chave def. Campos podem ter tipos heterogêneos.
 * Criação de Instâncias: Usando o literal de objeto com chaves {}. O compilador valida os campos e tipos contra a definição def.
 * Acesso a Campos: Notação de ponto .
```kurama
// Definição do tipo 'Produto' (nosso registro/struct)
def Produto {
    nome: String,
    preco: Int,
    categorias: List<String> // Campos podem ser outras estruturas ou listas
}

// Definição do tipo 'Cliente'
def Cliente {
    id: Int,
    nomeCompleto: String,
    email: String
}

// --- Exemplos de Uso ---

// Criação de uma instância de Produto
let meuNotebook: Produto = {
    nome: "Notebook Pro X",
    preco: 2500,
    categorias: ["Eletrônicos", "Informática"]
}

// Acesso a campos e interpolação de String
print "Detalhes do Produto: ${meuNotebook.nome}, Preço: R$${meuNotebook.preco}"
print "Categorias: ${meuNotebook.categorias}" // Acesso a listas dentro de registros

// Criação de uma instância de Cliente
let novoCliente: Cliente = {
    id: 1,
    nomeCompleto: "João Silva",
    email: "joao.silva@example.com"
}
print "Cliente ID: ${novoCliente.id}, Email: ${novoCliente.email}"

// Uso de Registros em Listas
LET PRODUTOS_EM_ESTOQUE: List<Produto> = [
    meuNotebook, // Reutilizando a instância
    {nome: "Teclado Mecânico", preco: 500, categorias: ["Periféricos", "Gamer"]},
    {nome: "Monitor UltraWide", preco: 1200, categorias: ["Periféricos"]}
]

print("\n--- Produtos em Estoque ---")
for prod in PRODUTOS_EM_ESTOQUE
    print("Nome: ${prod.nome}, Preço: R$${prod.preco}")
end
```

📦 Sistema de Módulos
Sistema de módulos visual e direto para organizar e reutilizar código.
 * Declaração de Pacote: package "nome_do_pacote"
   * No topo de cada arquivo, indica a qual pacote o arquivo pertence.
 * Exportação de Símbolos (<<):
   * Pré-fixa << à declaração (let, LET, fun, def) para torná-la acessível por outros pacotes.
 * Importação de Símbolos (>> "caminho" (...)):
   * >> indica importação. O caminho do pacote usa barras /.
   * Os parênteses () são usados para agrupar os símbolos a importar.
```kurama
// --- Arquivo: src/util/math.krm ---
package "util"

<< LET PI: Float = 3.14159
<< fun dobro(n: Int) -> Int
    return n * 2
end

<< def Ponto { // Podemos exportar definições de tipos também
    x: Int,
    y: Int
}


// --- Arquivo: src/app/main.krm ---
package "app"

// Importação seletiva: importando PI e dobro do pacote "util"
>> "util" (PI, dobro)

// Importação com Alias: importando Ponto do pacote "util" e renomeando para Coord
>> "util" (Ponto as Coord)

fun main() -> Void
    print("O valor de PI é: ${PI}")

    let numero = 10
    let duplicado = dobro(numero)
    print("O dobro de ${numero} é: ${duplicado}")

    let minhaCoordenada: Coord = {x: 10, y: 20}
    print("Coordenada: (${minhaCoordenada.x}, ${minhaCoordenada.y})")
end
```

📈 Evolução Futura: Orientação a Objetos (OO)
 * Os defs atuais fornecem uma base sólida para a parte de dados de objetos.
 * Futuramente, a Kurama Lang poderá estender a lógica dos defs para introduzir conceitos de OO, como classes (class ou obj), que encapsulam dados E comportamento (métodos). Isso pode envolver a reintrodução do conceito de this para métodos de instância, além de herança e polimorfismo.
Este resumo deve fornecer uma visão clara e concisa do design atual da Kurama Lang para sua equipe e para o README do GitHub!


---

## 🧠 Principais Conceitos Envolvidos

- Lexer (tokenização do código fonte)
- Parser (construção de árvore sintática - AST)
- Visitor Pattern
- Execução baseada em AST (interpretação)
- Escopos, variáveis, controle de fluxo
- Extensibilidade do interpretador

---

## ✅ Estado Atual

🚧 Projeto em desenvolvimento  
🔨 Etapas sendo construídas:

- [ ] Definição da sintaxe inicial
- [ ] Analisador léxico (lexer)
- [ ] Analisador sintático (parser)
- [ ] Árvore de Sintaxe Abstrata (AST)
- [ ] Interpretador básico (expressões e variáveis)
- [ ] Suporte a `if`, `while`, `print`
- [ ] Sistema de escopo e funções

---

##🤝 Equipe

Este projeto está sendo desenvolvido por um grupo de entusiastas da programação e compiladores, com o objetivo de aprendizado e experimentação.

##📜 Licença

MIT License — use, modifique e distribua à vontade!
