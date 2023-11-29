create database universidade;

use universidade;

create  table tb_aluno (
	cod_aluno int not null auto_increment,
    nome_aluno varchar(30),
    sobrenome_aluno varchar(30),
    cpf int(30),
    data_de_nascimento date,
    data_de_ingresso date,
    curso varchar(30),
    parente varchar(30),
    primary key (cod_aluno)
    
);

insert into tb_aluno values (0001, "lucas", "pedrosa", 71535316, 20000523, 20200208, "fisica", "pai");

create table tb_professor (
	cod_prof int not null auto_increment,
	nome_prof varchar(30,
    sobrenome_prof varchar(30),
    cpf int(30),
    data_de_nascimento date,
    salario double,
    disciplina varchar(30),
	data_de_ingresso date,
    parente varchar(30),
    primary key (cod_prof)
);

insert into tb_professor values (1, "jose", "alencar", 111111111, 19900501, 1500, "ciencias", 20170608);


create table tb_curso(
	cod_curso int not null auto_increment,
    nome_curso varchar(30),
    disciplinas varchar(30),
    atividades varchar(30),
    primary key(cod_curso)
);

insert into tb_curso values (1111,"fisica", "calculo", "futebol");

create table tb_disciplina(
	nome_disciplina varchar(30),
    carga_horaria int(10));

insert into tb_disciplina values ("calculo",20);

create table tb_telefone_prof (
	cod_prof int not null auto_increment,
    tel_professor int (30),
    primary key (cod_prof)
);

insert into tb_telefone_prof values (1, 419976412);

create table tb_telefone_aluno (
	cod_aluno int not null auto_increment,
    tel_aluno int (30),
    primary key (cod_aluno)
);

insert into tb_telefone_aluno values (0001,76426423);

create table tb_endereco_aluno (
	cod_aluno int not null auto_increment,
    rua varchar(30),
    bairro varchar(30),
    primary key (cod_aluno)
);

insert into tb_endereco_aluno values (0001, "Rua brasil", "Novo mundo");

create table tb_endereco_porf (
	cod_prof int not null auto_increment,
    rua varchar(30),
    bairro varchar(30),
    primary key (cod_prof)
);

insert into tb_endereco_prof values (1, "Rua liberdade", "portão");

create table tb_futebol (
	cod_aluno int not null auto_increment,
    nome_aluno varchar (30),
    primary key (cod_aluno)
);

insert into tb_futebol values (0001, "lucas");

create table tb_basquete (
	cod_aluno int not null auto_increment,
    nome_aluno varchar (30),
    primary key (cod_aluno)
);

insert into tb_basquete values (0001, "lucas");

create table tb_volei (
	cod_aluno int not null auto_increment,
    nome_aluno varchar (30),
    primary key (cod_aluno)
);

insert into tb_volei values (0001, "lucas");

create table tb_handball (
	cod_aluno int not null auto_increment,
    nome_aluno varchar (30),
    primary key (cod_aluno)
);

insert into tb_handball values (0001, "lucas");

create table tb_grupo_de_estudos (
	cod_aluno int not null auto_increment,
    nome_aluno varchar (30),
    primary key (cod_aluno)
);

insert into tb_grupo_de_estudos values (0001, "lucas");

create table tb_centro_academico (
	cod_aluno int not null auto_increment,
    nome_aluno varchar (30),
    primary key (cod_aluno)
);

insert into tb_centro_academico values (0001, "lucas"); 


create  table tb_parente (
	cod_parente int not null auto_increment,
    nome_parente varchar(30),
    sobrenome_parente varchar(30),
    cpf int(30),
    data_de_nascimento date,
    parentesco varchar(30),
    primary key (cod_parente)
    
);

insert into tb_parente values (1010, "andreia", "pedrosa", 47627323, 19850117, "mãe");

create table tb_telefone_parente (
	cod_parente int not null auto_increment,
    tel_parente int (30),
    primary key (cod_parente)
);

insert into tb_telefone_parente values (1010, 12988924);


create table tb_endereco_parente (
	cod_parente int not null auto_increment,
    rua varchar(30),
    bairro varchar(30),
    primary key (cod_parente)
); 

insert into tb_endereco_parente values(1010, "Rua brasil", "Novo mundo");

