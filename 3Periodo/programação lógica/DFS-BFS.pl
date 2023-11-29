
conectado(a,b).
conectado(a,e).
conectado(b,c).
conectado(b,d).
conectado(b,e).
conectado(d,e).
conectado(e,f).
conectado(f,g).
conectado(g,h).
conectado(h,i).

conectado2(X,Y):-
conectado(X,Y);conectado(Y,X).


%----Depth First Search (DFS)-----
% O DFS BUSCA PELA LARGURA PRIMERIAMENTE, AVANÇA O GRAFO OU A ARVORE 
% TUDO QUE PODE E QUANDO NÃO FOR MAIS POSSÍVEL, FAZ A VOLTA E BUSCA 
% O MELHOR CAMINHO.

% CONDIÇÃO DO NÓ COM O DESTINO
dfs(Orig,Dest,Cam):-
dfs(Orig,Dest,[Orig],Cam).
dfs(Dest,Dest,LA,Cam):-
reverse(LA,Cam).
dfs(Act,Dest,LA,Cam):-
% TESTAR SE O NÓ ATUAL ESTA CONECTADO COM A VARIAVEL EM QUESTAO
conectado2(Act,X),
% TESTE PARA EVITAR PASSAR NOS CAMIHOS JA PERCORRIDOS
\+ member(X,LA),
% RECURSIVAIDADE PARA CONTINUAR CHAMANDO ATÉ CHEGAR NO DESTINO
dfs(X,Dest,[X|LA],Cam).

%   dfs(a,f,Caminho). 



%------ Breadth First Search (BFS) ---------
% O BFS BUSCA PRIMEIRAMENTE EM LARGURA, EXPLORA TODOS OS NÓ ADJACENTES 
% EM PRIMEIRO LUGAR E DEPOIS VAI PARA OS ADJACENTES DOS ADJACENTES E
% ASSIM VAI SEGUINDO. GARANTIDO ASSIM QUE O MENOR CAMINHO SEJA 
% ENCONTRADO ANTES

% CONDIÇÃO FINAL -> DESTINO = NÓ RAIZ DO CAMINHO EM QUESTAO
bfs(Orig,Dest,Cam):-bfs2(Dest,[[Orig]],Cam).
bfs2(Dest,[[Dest|T]|_],Cam):-
reverse([Dest|T],Cam).
bfs2(Dest,[LA|Outros],Cam):-
LA=[Act|_],
% CALCULAR O NÓS ADJACENTES QUE NÃO FORAM PERCORRIDOS E GERAR UM NOVO
% CAMINHO PARA CADA NÓ
findall([X|LA],
(Dest\==Act,conectado2(Act,X),\+ member(X,LA)),
Novos),
% COLOCAR OS NOVOS CAMINHOS ENCONTRADOS NO FINAL DA LISTA PARA PERCORRES
append(Outros,Novos,Todos),
% CHAMADA RECURSIVA
bfs2(Dest,Todos,Cam).

%   bfs(a,f,Caminho).  %
