%ALUNOS: GUSTAVO FURINI, LEONARDO NERVINO, 
%HENRIQUE CONCEIÇÃO E PEDRO ARAUJO
casou(n, w).
casou(f,d).
mae(w, d).
mae(w,s1).
mae(d,s2).
pai(f, n).
pai(f,s2).
pai(n, s1).
genro(f,n).
genro(f,w).
sogra(w,f).
madastra(d,n).
padrastro(n,d).
irmao(s1,d).
irmao(s2,n).
enteada(d,n).
cunhado(s1,f).
tio(s1,n).
neto(s2,n).
neto(s1,f).
avo(X,X) :-  irmao(s2, n), neto(s2, n).  
% narrador é avo do s2 e s2 é irmão do n, ou seja, n é avo de si propio
%TERMINAL: avo(n,n)