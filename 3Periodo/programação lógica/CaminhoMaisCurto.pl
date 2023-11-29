
aresta(a,b,1).
aresta(b,e,1).
aresta(a,c,1).
aresta(c,d,1).
aresta(e,d,1).
aresta(d,f,1).
aresta(d,g,1).

conectado(X,Y,L) :- aresta(X,Y,L) ; aresta(Y,X,l).

path(A,B,Path,Len) :-
       caminho(A,B,[A],Q,Len), 
       reverse(Q,Path).

caminho(A,B,P,[B|P],L) :- 
       conectado(A,B,L).
caminho(A,B,Visited,Path,L) :-
       conectado(A,C,D),           
       C \== B,
       \+member(C,Visited),
       caminho(C,B,[C|Visited],Path,L1),
       L is D+L1.  

curto(A,B,Caminho,Tamanho) :-
   setof([P,L],path(A,B,P,L),Set),
   Set = [_|_], 
   minimal(Set,[Caminho,Tamanho]).

minimal([F|R],M) :- min(R,F,M).


min([],M,M).
min([[P,L]|R],[_,M],Min) :- L < M, !, min(R,[P,L],Min). 
min([_|R],M,Min) :- min(R,M,Min).