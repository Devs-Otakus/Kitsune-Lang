# 🦊 Kitsune Lang

**Kitsune Lang** é uma linguagem de script interpretada, escrita em Java, criada como projeto educacional para explorar os fundamentos de linguagens de programação e construção de interpretadores.
Inspirada em linguagens funcionais e fortemente tipadas, a Kitsune Lang foca na **simplicidade da sintaxe**, **execução em tempo real** e **facilidade de extensão**.

---

## 📚 Sumário

* [Objetivos do Projeto](#✨-objetivos-do-projeto)
* [Filosofia e Pilares](#✨-filosofia-e-pilares)
* [Paradigmas e Características](#🧹-paradigmas-e-características-principais)
* [Visão Geral e Sintaxe](#🦊-visão-geral-e-sintaxe)

  * [Comentários](#comentários)
  * [Variáveis e Tipagem](#variáveis-e-tipagem)
  * [Funções](#funções)
  * [Controle de Fluxo](#controle-de-fluxo)
  * [Estruturas de Dados](#estruturas-de-dados)
  * [Objetos / Registros](#objetos--registros-structs)
  * [Sistema de Módulos](#sistema-de-módulos)
* [Evolução Futura: Orientação a Objetos](#📈-evolução-futura-orientação-a-objetos-oo)
* [Conceitos Envolvidos](#🧠-principais-conceitos-envolvidos)
* [Estado Atual](#✅-estado-atual-do-projeto)
* [Equipe](#🤝-equipe)
* [Licença](#📜-licença)

---

## ✨ Objetivos do Projeto

* Aprender e aplicar conceitos de **análise léxica**, **parsing** e **interpretação**
* Criar uma linguagem de script com sintaxe clara e expressiva
* Desenvolver um interpretador 100% funcional em Java
* Entender como linguagens reais funcionam por dentro

---

## ✨ Filosofia e Pilares

* **100% Tipada:** Tipos obrigatórios com inferência, evitando erros em tempo de execução.
* **Funcional (foco inicial):** Funções como valores, imutabilidade incentivada.
* **Sintaxe limpa e distinta:** Sem `{}` para blocos. Usa `do ... end` para delimitação.
* **Delimitação obrigatória:** Uso de `;` para separação de instruções.

---

## 🧹 Paradigmas e Características Principais

| Recurso                     | Status                          |
| --------------------------- | ------------------------------- |
| ✅ Tipagem forte             | Obrigatória, com inferência     |
| ✅ Paradigma funcional       | Suportado                       |
| ✅ Orientação a objetos      | Planejada                       |
| ✅ Sintaxe concisa e legível | Sem `{}` ou indent. obrigatória |
| ✅ Interpretada              | Foco inicial                    |
| 🔜 Compilada (futura)       | Planejada                       |
| ✅ Modularização             | Suporte a pacotes e módulos     |

---

## 🦊 Visão Geral e Sintaxe

### Comentários

```kitsune
// Linha única
/* Comentário de
   múltiplas linhas */
```

### Variáveis e Tipagem

```kitsune
let contador: Int = 0;        // variável mutável do tipo inteiro
let mensagem = "Olá, Kurama!"; // tipo inferido como String

LET PI: Float = 3.14159;      // constante do tipo float
LET ATIVO = true;             // constante booleana (tipo inferido)

```

### Funções

```kitsune
// Função que recebe dois inteiros e retorna a soma
fun somar(a: Int, b: Int) -> Int do
    return a + b;
end

// Função que imprime uma saudação
fun saudacao(nome: String) -> Void do
    print("Olá, ${nome}!");
end

// Função de ordem superior: recebe outra função como argumento
fun aplicarOperacao(valor: Int, operacao: (Int) -> Int) -> Int do
    return operacao(valor);
end
```

### Controle de Fluxo

```kitsune
let idade = 20;

// Condicional simples com else if
if idade >= 18 do
    print("Maior de idade.");
else if idade >= 0
    print("Menor de idade.");
else
    print("Idade inválida.");
end

// Loop while com incremento manual
let contador = 0;
while contador < 3 do
    print("Contador: ${contador}");
    contador = contador + 1;
end

// Loop for de 1 a 5 (inclusive)
for i in 1..5 do
    print("Número: ${i}");
end
```

### Estruturas de Dados: Listas

```kitsune
let frutas: List<String> = ["Maçã", "Banana", "Uva"];  // Declara lista de strings

print("Primeira fruta: ${frutas[0]}");                 // Acessa elemento da lista

// Itera sobre cada fruta
for fruta in frutas do
    print("Fruta: ${fruta}");
end
```

### Objetos / Registros (Structs)

```kitsune
// Define uma estrutura de dados
def struct Produto {
    nome: String,
    preco: Int,
    categorias: List<String>
}

// Instancia um objeto da struct Produto
let meuNotebook: Produto = {
    nome: "Notebook Pro X",
    preco: 2500,
    categorias: ["Eletrônicos", "Informática"]
}

// Acessa os campos da struct com ponto
print "Detalhes: ${meuNotebook.nome}, R$${meuNotebook.preco}";
```

### Sistema de Módulos

```kitsune
// Arquivo: src/util/math.krm
package "util"

// Exporta constante, função e tipo
<< LET PI: Float = 3.14159;

<< fun dobro(n: Int) -> Int do
    return n * 2;
end

<< def struct Ponto {
    x: Int,
    y: Int
}

// Arquivo: src/app/main.krm
package "app"

// Importa símbolos específicos do módulo
>> "util" (PI, dobro, Ponto as Coord);
```

---

## 📈 Evolução Futura: Orientação a Objetos (OO)

* `def struct` para objetos simples
* `def class` para classes completas com métodos
* `def interface` para interfaces
* `def enum` para tipos enumerados

### Exemplo planejado:

```kitsune
def class Pessoa {
    nome: String,
    idade: Int

    fun aniversario() -> Void do
        this.idade = this.idade + 1;
    end
}

def enum ROLES {
    ADMIN,
    USER,
    GUEST
}
```

---

## 🧠 Principais Conceitos Envolvidos

* Lexer (analisador léxico)
* Parser (analisador sintático)
* AST (Abstract Syntax Tree)
* Visitor Pattern
* Execução de código por interpretação da AST
* Gerenciamento de escopos e ambiente de execução

---

## ✅ Estado Atual do Projeto

* [ ] Definição da sintaxe
* [ ] Lexer básico
* [ ] Parser para expressões e blocos
* [ ] Construção de AST
* [ ] Execução de expressões e variáveis
* [ ] Sistema de escopo e chamadas de função

---

## 🤝 Equipe

Desenvolvido por entusiastas de linguagens e educação, como forma de aprendizado e exploração.

---

## 📜 Licença

MIT License — livre para uso, modificação e distribuição.

---
