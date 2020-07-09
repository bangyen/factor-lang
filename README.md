# Factor
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/2cbcc0dfcb3e43cc98663442cea1948a)](https://www.codacy.com/manual/bangyen99/factor-lang?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bangyen/factor-lang&amp;utm_campaign=Badge_Grade)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

## Table of Contents
-   [About](#about)
-   [Examples](#examples)
-   [Computational Class](#computational-class)
-   [Translations](#translations)
    -   [Brainfuck to Factor](#brainfuck-to-factor)
    -   [Factor to brainfuck](#factor-to-brainfuck)
-   [Usage](#usage)

## About
[Factor](https://esolangs.org/wiki/Factor) is a zero-dimensional, cell-based, esoteric programming language based on the prime *factor*ization of numbers. Every program is a number, and the instructions of each program are determined by the number's prime factors and their multiplicity. Each factor is a different instruction (based on it's residue modulo `11`), and each factor's multiplicity is the number of times the instruction is carried out. The order of the instructions is determined by sorting the factors in ascending order. The different instructions are as follows:

|                                     | Left      | Right      | Increment | Decrement | Output | Input | Start loop | End loop |
|-------------------------------------|:---------:|:----------:|:---------:|:---------:|:------:|:-----:|:----------:|:--------:|
| Z₁₁ Residue                         | 1         | 2          | 3         | 4         | 5      | 6     | 7          | 8        |
| Brainfuck Equivalent                | >         | <          | +         | -         | .      | ,     | \[         | \]       |

Left and right refer to moving the pointer, whereas increment and decrement refer to changing the cell designated by the pointer. Loops are started if the current cell value is nonzero, and loops are ended if the current cell is zero. The cells are right unbounded and wrap at 0 and 256. All characters other than 0123456789 should be considered comments and ignored. 

## Examples
In the `examples` folder is the familiar ["Hello World!" program](https://en.wikipedia.org/wiki/%22Hello,_World!%22_program). The file, named `hello-world.fact`, consists of the following number spread among three lines:
```fact
1655681263349701521084659680611551719864071403625859675993155360184979650875317924075071663014170796
3982142000896058372565757592464788558159819435061699693781799182850358327927823218744238796733811436
76538661836790083866016752674868707301142092304365222517116382208838942082995905598124019955549
```

The [`cat`](https://esolangs.org/wiki/Cat_program) program, on the other hand, is represented as the number `310861643`. The brainfuck equivalent would be `,[.,]`, since its prime factorization is `17 * 29 * 71 * 83 * 107`. The equivalence is a result of the following congruencies:

| Modular Equation | Instruction |
|:----------------:|:-----------:|
| 17 ≡ 6 (mod 11)  | Input       |
| 29 ≡ 7 (mod 11)  | Start loop  |
| 71 ≡ 5 (mod 11)  | Output      |
| 83 ≡ 6 (mod 11)  | Input       |
| 107 ≡ 8 (mod 11) | End loop    |

Finally, a [brainfuck interpreter](http://www.hevanet.com/cristofd/brainfuck/dbfi.b) by Daniel B. Cristofani.
```fact
3688554400115082608624508453730280728908132818764696846834669229770777761772478005527507177418120694
3297612030934022193841677684549386729697395145284043549103432860149551273007692661521521238378585373
6543472783033594456315859326953695889493408883009750425138050844651886706519801267566755335040672769
1693142463148500967183819862179365384409781357966931869892850435180570833406982731006507405891701959
4485354548311779084099725425043162987308040075063877407282532719150913822261233897714262058986360034
8218537048658597258852509353537173568671203552120996239185547386547855325347653142800549655880422575
4342462965925375667011906283136966897338144651903308546773857218905377988341330353123312187176812317
6858541109074968138841370163784851182103427577801536888591441648172842836826181982574882949180405500
8670081513669181308499057177529659148439008707075389446903465545471675098029277846800886915820990250
8715098077051532090061442181671936916536705010615531107665529612193880253925746489612770787088516247
9468037675600787428023542004518730028975200089798493828952946603080048923933137936720859162176172205
1490960163414413721023935201373298623861708704137798701766456816518388727419990119162281303945678618
3747448799859676672637117162392892706558226172974581878931097094777948014994380338004990013300346303
3242897233477436939477555630521742237843376755396947739081463696870730873223954238930152068730233776
6872505644966051907429523694391319691589154855599276779048735777479669316766704156814084591354216434
428512065158173722669676843275655431631295470654442175485742940558078916419832634153303347
```

## Computational Class
The number `11` was chosen because it is the smallest number such that there exists at least `8` (the number of instructions) natural numbers less than and coprime to it. The reason why coprimality is important is because [Dirichlet's theorem](https://en.wikipedia.org/wiki/Dirichlet%27s_theorem_on_arithmetic_progressions) ensures that there are an infinite number of primes that are congruent to each coprime integer modulo `11`. As a result, because Factor is isomorphic to Brainfuck, a Turing complete language, Factor is *also* Turing complete.

## Translations
### Brainfuck to Factor
```ruby
# Ruby program
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

### Factor to brainfuck
```ruby
# Ruby program
require 'prime'

def translate(num)
    res = ''; i = 2; str = '0><+-.,[]'
    while num != 1
        while num % i != 0
            i += 1
        end
        num /= i
	res += str[i % 11]
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
