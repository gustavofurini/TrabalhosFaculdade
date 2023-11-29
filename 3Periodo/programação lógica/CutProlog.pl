%alunos: gustavo furini, pedro araujo, leonardo nervino e henrique conceição

homem(albert).
homem(bob).
homem(edward).
mulher(alice).
mulher(victoria).
progenitor(albert, edward).
progenitor(victoria, edward).
progenitor(albert, alice).
progenitor(victoria, alice).
progenitor(albert, bob).
progenitor(victoria, bob).

filho(Y,X) :- progenitor(X,Y), !.
primo(Y1,Y1) :- progenitor(X,Y1), progenitor(X,	Y2), !.