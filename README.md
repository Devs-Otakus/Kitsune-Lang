---

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
let idade: Int = 20;
LET PI: Float = 3.14;
let nome = "Kurama";
```

### Funções

```kitsune
fun saudacao(nome: String) -> Void do
    print("Olá, ${nome}!");
end

fun somar(a: Int, b: Int) -> Int do
    return a + b;
end
```

### Controle de Fluxo

```kitsune
if idade >= 18 do
    print("Adulto");
else if idade >= 0
    print("Menor de idade");
else
    print("Idade inválida");
end

while contador < 3 do
    print(contador);
    contador = contador + 1;
end

for i in 1..5 do
    print(i);
end
```

### Estruturas de Dados: Listas

```kitsune
let frutas: List<String> = ["Maçã", "Banana"];
print(frutas[0]);
```

### Objetos / Registros (Structs)

```kitsune
def struct Produto {
    nome: String,
    preco: Float
}

let p: Produto = {
    nome: "Café",
    preco: 14.99
};
```

### Sistema de Módulos

```kitsune
// utils.krm
package "utils"

<< fun dobro(n: Int) -> Int do
    return n * 2;
end

<< struct Ponto {
    x: Int,
    y: Int
}

// main.krm
package "main"
>> "utils" (dobro, Ponto as Coord);
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
