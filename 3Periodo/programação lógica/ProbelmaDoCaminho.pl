%ALUNOS: GUSTAVO FURINI, HENRIQUE CONCEIÇÃO, LEONARDO NERVINO E 
%PEDRO ARAUJO
%ATIVIDADE CAMINHOS
conectado(1,2). 
conectado(3,4). 
conectado(5,6). 
conectado(7,8). 
conectado(9,10). 
conectado(12,13). 
conectado(13,14). 
conectado(15,16). 
conectado(17,18). 
conectado(19,20). 
conectado(4,1). 
conectado(6,3). 
conectado(4,7). 
conectado(6,11). 
conectado(14,9). 
conectado(11,15). 
conectado(16,12). 
conectado(14,17). 
conectado(16,19). 
caminho(X,Y).
caminho(X,Y) :- conectado(X,Z),
             caminho(Z,Y).
%ORDEM DE COMPRA
cliente(jose).
cliente(julia).
cliente(joao).
cliente(joaquim).
credito(X, cliente) :- (X > 0).
produto(lapis).
produtoValor(Y, lapis) :- (Y > 0).
quantidadeProduto(Q, lapis).
estoque(E) :- (E = Q).
compra(X,Y) :- X>Y,write('compra realizada'); X<Y, write('compra 
nao realizada').
%FAHRENHEIT PARA CELSIUS E CELSIUS PARA FAHRENHEIT
c_para_f(C, F) :- F is ((C * (9/5)) + 32).
f_para_c(F, C) :- C is ((F - 32) / 1.8).