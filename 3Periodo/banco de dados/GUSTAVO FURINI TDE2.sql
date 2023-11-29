create database universidade;

use universidade;

create table tb_salas(
	nome_sala varchar(30),
    capacidade int(100));
    
insert into tb_salas values ("nao sei", 20);

drop table tb_salas;

commit;

create table tb_aluas_de_ingles(
	cod_aluno int(01),
    nome_aluno varchar(30));
	
drop table tb_aluas_de_ingles;

rollback;