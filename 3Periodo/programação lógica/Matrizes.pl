
:- module(matrix,
    [matrix_multiply/3
    ,dot_product/3
    ,cholesky_decomposition/2
    ,matrix_diagonal/2
    ,determinant/2
    ,list0/2
    ,matrix_inv_triang/2,
    ]).
:- use_module(library(clpfd), [transpose/2]).

% ----------- PRODUTO DAS MATRIZES --------

% O PREDICADO RECEBE DUAS MATRIZES QUE POSSUEM p x n - n x z.
% MULTIPLICANDO AS FILEIRAS CASO A MATRIZ TENHA OS PARAMETRO NECESSARIOS.
% A MATRIZ TRANSPOSTA DA PRIMEIRA MATRIZ IRA MULTPLICAR AS COLUNAS DA SEGUNDA
% MATRIZ

matrix_multiply(X,Y,M) :-
    matrix_mul(X,Y,M0),
    maplist(maplist(is),M,M0).
  
matrix_mul(X,Y,M) :-
    transpose(Y,T),
    maplist(row_multiply(T),X,M).


row_multiply(T,X,M) :-
    maplist(dot_product(X),T,M).

% CALCULA O PRODUTO DOS DOIS VETORES DA MATRIZ
dot_product([X|Xs],[T|Ts],M) :-
  	foldl(mul,Xs,Ts,X*T,M).
  	mul(X,T,M,M+X*T).


%-------------- DETERMINANTE -----------
% CALCULA O DETERMINANTE DA MATRIZ DEFINIDA NA QUERY,
% USANDO O CÓDIGO DE DECOMPOSICÃO DE CHOLESKY, QUE BASICAMENTE É UMA 
% FUNÇÃO QUE FATORA UMA MATRIZ TRIANGULAR QUE ATRAVÉS DAS DIAGONAIS 
% ENCONTRADAS ACHA O DETERMINANTE EM UMA OUTRA LISTA MULTIPLICANDO O VALOR 
% DA PRINCIPAL E SECUNDÁRIA E DEPOIS SUBSTITUINDO-AS

determinant(A,Det):-
     cholesky_decomposition(A,L),
     get_diagonal(L,D),
     foldl(prod,D,1,DetL),
     Det is DetL*DetL.
    
   prod(A,P0,P):- 
     P is P0*A.

cholesky_decomposition(A,L):-
    append(A,AL),
    length(AL,NL),
    AM =..[a|AL],
    list0(NL,LL),
    LM=..[l|LL],
    length(A,N),
    cholesky_i(0,N,AM,LM),
    term_to_list(LM,N,L).
  
cholesky_i(N,N,_A,_L):-!.
  
cholesky_i(I,N,A,L):-
    cholesky_j(0,I,N,A,L),
    I1 is I+1,
    cholesky_i(I1,N,A,L).
  
cholesky_j(I,I,N,A,L):-!,
    cholesky_k(0,I,I,N,0,S,L),
    get_v(I,I,N,A,Aii),
    V is sqrt(Aii-S),
    set_v(I,I,N,L,V).
  
  
cholesky_j(J,I,N,A,L):-
    cholesky_k(0,J,I,N,0,S,L),
    get_v(I,J,N,A,Aij),
    get_v(J,J,N,L,Ljj),
    V is 1.0/Ljj*(Aij-S),
    set_v(I,J,N,L,V),
    J1 is J+1,
    cholesky_j(J1,I,N,A,L).
  
cholesky_k(J,J,_I,_N,S,S,_L):-!.
  
cholesky_k(K,J,I,N,S0,S,L):-
    get_v(I,K,N,L,Lik),
    get_v(J,K,N,L,Ljk),
    S1 is S0+Lik*Ljk,
    K1 is K+1,
    cholesky_k(K1,J,I,N,S1,S,L).
  
get_v(I,J,N,M,V):-
    Argij is I*N+J+1,
    arg(Argij,M,V).
  
set_v(I,J,N,M,V):-
    Argij is I*N+J+1,
    setarg(Argij,M,V).
  
   
term_to_list(T,N,L):-
    T=..[_|E],
    identify_rows(E,N,L).
  
identify_rows([],_N,[]):-!.
  
identify_rows(E,N,[R|L]):-
    length(R,N),
    append(R,Rest,E),
    identify_rows(Rest,N,L).

list0(0,[]):-!.
  
  list0(N,[0|T]):-
    N1 is N-1,
    list0(N1,T).

matrix_inv_triang(L1,L2):-
    get_diagonal(L1,D),
    maplist(inv,D,ID),
    diag(ID,IDM),
    matrix_multiply(IDM,L1,LL1),
    list_to_term(LL1,LT),
    length(LL1,N),
    matrix_inv_i(1,N,LT),
    term_to_list(LT,N,LL2),
    matrix_multiply(LL2,IDM,L2).
  
  
  matrix_inv_i(N,N,_LT):-!.
  
  matrix_inv_i(I,N,LT):-
    matrix_inv_j(0,I,N,LT),
    I1 is I+1,
    matrix_inv_i(I1,N,LT).
  
  matrix_inv_j(I,I,_N,_LT):-!.
  
  matrix_inv_j(J,I,N,LT):-
    get_v(I,J,N,LT,Vij),
    V_ij is -Vij,
    set_v(I,J,N,LT,V_ij),
    J1 is J+1,
    matrix_inv_k(J1,J,I,N,LT),
    matrix_inv_j(J1,I,N,LT).
    
  matrix_inv_k(I,_J,I,_N,_LT):-!.
  
  matrix_inv_k(K,J,I,N,LT):-
    get_v(I,K,N,LT,Vik),
    get_v(K,J,N,LT,Vkj),
    get_v(I,J,N,LT,Vij),
    NVij is Vij-Vik*Vkj,
    set_v(I,J,N,LT,NVij),
    K1 is K+1,
    matrix_inv_k(K1,J,I,N,LT).
  
   
  diag(L,D):-
    length(L,N),
    NN is N*N,
    list0(NN,L0),
    DT =..[a|L0],
    N1 is N-1,
    numlist(0,N1,In),
    maplist(set_diag(DT,N),In,L),
    term_to_list(DT,N,D).
  
  set_diag(D,N,I,V):-
    set_v(I,I,N,D,V).
  
  inv(A,B):-
    B is 1.0/A.
  
  get_diagonal(L,D):-
    length(L,N),
    list_to_term(L,LT),
    get_diag(0,N,LT,D).
  
  get_diag(N,N,_L,[]):-!.
  
  get_diag(N0,N,L,[H|R]):-
    get_v(N0,N0,N,L,H),
    N1 is N0+1,
    get_diag(N1,N,L,R).
  
  list_to_term(L,LT):-
    append(L,LL),
    LT=..[a|LL].



