# Factor
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/2cbcc0dfcb3e43cc98663442cea1948a)](https://www.codacy.com/manual/bangyen99/factor-lang?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bangyen/factor-lang&amp;utm_campaign=Badge_Grade)

Factor is an esoteric programming language based on the prime *factor*ization of numbers. Every program is a number, and the instructions of each program are determined by the number's prime factors and their multiplicity. Each factor is a different instruction (based on it's residue modulo `11`), and each factor's multiplicity is the number of times the instruction is carried out. The order of the instructions is determined by sorting the factors in ascending order. The different instructions are as follows:

| Z₁₁ Residue                     | 1 | 2 | 3 | 4 | 5 | 6 | 7  | 8  |
|-------------------------------------|---|---|---|---|---|---|----|----|
| Corresponding Brainfuck Instruction | > | < | + | - | . | , | \[ | \] |

## Example
In the `examples` folder is the familiar ["Hello, World!" program](https://en.wikipedia.org/wiki/%22Hello,_World!%22_program). The file, named `hello-world.fact`, consists of the following number spread among three lines:
```fact
1655681263349701521084659680611551719864071403625859675993155360184979650875317924075071663014170796
3982142000896058372565757592464788558159819435061699693781799182850358327927823218744238796733811436
76538661836790083866016752674868707301142092304365222517116382208838942082995905598124019955549
```

## Turing Completeness
Because Factor is isomorphic to Brainfuck, a Turing complete language, Factor is thus also Turing complete.

## Usage
All `.fact` programs are interpreted in Java. To run a `.fact` program, follow the following instructions:
-   Download the repository

-   Unzip the repository file

-   In the terminal, run the following commands in the `src` folder:
    -   `javac Factor.java`
    -   `java Factor your-file.fact`
