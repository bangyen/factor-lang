# Factor
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/2cbcc0dfcb3e43cc98663442cea1948a)](https://www.codacy.com/manual/bangyen99/factor-lang?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bangyen/factor-lang&amp;utm_campaign=Badge_Grade)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

## About
[Factor](https://esolangs.org/wiki/Factor) is a zero-dimensional, cell-based, esoteric programming language based on the prime *factor*ization of numbers. Every program is a number, and the instructions of each program are determined by the number's prime factors and their multiplicity. Each factor is a different instruction (based on it's residue modulo `11`), and each factor's multiplicity is the number of times the instruction is carried out. The order of the instructions is determined by sorting the factors in ascending order. The different instructions are as follows:

| Z₁₁ Residue                         | 1 | 2 | 3 | 4 | 5 | 6 | 7  | 8  |
|-------------------------------------|---|---|---|---|---|---|----|----|
| Brainfuck Equivalent                | > | < | + | - | . | , | \[ | \] |

Left and right refer to moving the pointer, whereas increment and decrement refer to changing the cell designated by the pointer. Loops are started if the current cell value is nonzero, and loops are ended if the current cell is zero. The cells are right unbounded and wrap at 0 and 256. All characters other than 0123456789 should be considered comments and ignored. 

## Example
In the `examples` folder is the familiar ["Hello World!" program](https://en.wikipedia.org/wiki/%22Hello,_World!%22_program). The file, named `hello-world.fact`, consists of the following number spread among three lines:
```fact
1655681263349701521084659680611551719864071403625859675993155360184979650875317924075071663014170796
3982142000896058372565757592464788558159819435061699693781799182850358327927823218744238796733811436
76538661836790083866016752674868707301142092304365222517116382208838942082995905598124019955549
```
The [`cat`](https://esolangs.org/wiki/Cat_program) program, on the other hand, is represented as the number `310861643`. The brainfuck equivalent would be `,[.,]`, since its prime factorization is `17 * 29 * 71 * 83 * 107`. The equivalence is a result of the following congruencies: 
```latex
17 ≡ 6 (mod 11)      29 ≡ 7 (mod 11)      71 ≡ 5 (mod 11)      83 ≡ 6 (mod 11)      107 ≡ 8 (mod 11)
```

## Computational Class
The number `11` was chosen because it is the smallest number such that there exists at least `8` (the number of instructions) natural numbers less than and coprime to it. The reason why coprimality is important is because [Dirichlet's theorem](https://en.wikipedia.org/wiki/Dirichlet%27s_theorem_on_arithmetic_progressions) ensures that there are an infinite number of primes that are congruent to each coprime integer modulo `11`. As a result, because Factor is isomorphic to Brainfuck, a Turing complete language, Factor is *also* Turing complete.

## Brainfuck Translation
Below is Ruby code for translating brainfuck code into Factor.

```ruby
require 'prime'

def translate(code)
  res = 1; i = 2; str = '0><+-.,[]'
  for char in code.chars
    while i % 11 != str.index(char) || !i.prime?
      i += 1
    end
    res *= i
  end
  res
end
```

## Usage
All `.fact` programs are interpreted in Java. To run a `.fact` program, follow the following instructions:
-   Download the repository
-   Unzip the repository file
-   In the terminal, run the following commands in the `src` folder:
    -   `javac Factor.java`
    -   `java Factor your-file.fact`
