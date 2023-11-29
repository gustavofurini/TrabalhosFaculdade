cidade(curitiba, parana, brasil). 
cidade(blumenau, santa_catarina, brasil). 
cidade(joenville, santa_catarina, brasil). 
cidade(foz_do_iguaçu, parana, brasil). 
cidade(londrina, parana, brasil). 
cidade(maringa, parana, brasil). 
cidade(ushuaia, terra_do_fogo, argentina). 
cidade(santa_rosa, la_pampa, argentina). 
cidade(cordoba, cordoba, argentina). 



%1)Encontre uma lista ordenada com todas as cidades da Argentina. 

%  findall(Cidade, cidade(Cidade,Estado, argentina), CidadeArgentina).

%2)Encontre uma lista com as cidades de cada estado do Brasil. 

%  bagof(Cidade/Estado, cidade(Cidade, Estado, brasil), CidadeEstados ).

%3)Encontre uma lista ordenada das cidades de cada estado da base de conhecimento. 

%  bagof(Cidade/Estado, cidade(Cidade, Estado, Pais), Cidades/Estados).

%4)Encontre uma lista ordenada dos estados de cada país da base de conhecimento. 

%  setof(Estado/Pais, cidade(Cidade,Estado, Pais), EstadosPaises).

%5)Encontre uma lista com repetições e não ordenada dos estados de cada país da base de conhecimento.

%  findall(Estado/Pais, cidade(Cidade,Estado, Pais), EstadosPaises).
 
%6)Encontre uma lista com todas as cidades do Uruguai. 

%  findall(Cidade, cidade(Cidade,Estado,uruguai), CidadesUruguai).
%  bagof(Cidade, cidade(Cidade,Estado,uruguai), CidadesUruguai).

%7)Encontre uma lista com todas as cidades, de um determinado estado e país.

% bagof(Cidade/Estado/Pais, cidade(Cidade,Estado,Pais), CidadesEstadoPais).