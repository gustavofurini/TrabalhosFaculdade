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

insert into tb_aluno values (0001, "Lucas", "Pedrosa", 71535316, 20000523, 
20200208, "Fisica", "pai");
insert into tb_aluno values (0002, "João", "Alencar", 74816424, 19990217, 
20170510, "Administração", "mae");
insert into tb_aluno values (0003, "Lívia", "Ferreira", 17583950, 19800523, 
20190508, "Medicina", "pai");
insert into tb_aluno values (0004, "Amanda", "Nunes", 98076245, 20030523, 
20190708, "Engenharia", "pai");
insert into tb_aluno values (0005, "Felipe", "Augusto", 12347896, 19840523, 
20150308, "Filosofia", "pai");

create table tb_professor (
    cod_prof int not null auto_increment,
    nome_prof varchar(30),
    sobrenome_prof varchar(30),
    cpf int(30),
    data_de_nascimento date,
    salario double,
    disciplina varchar(30),
    data_de_ingresso date,
    parente varchar(30),
    primary key (cod_prof)
);

insert into tb_professor values (0001, "Debora", "Alencar", 67382974, 19950611, 2000.0, 
"Ciências", 20170608);
insert into tb_professor values (0002, "Fabricio", "Silva", 78654812, 19800413, 5000.0,
"Matemática", 20000126, "");
insert into tb_professor values (0003, "Marcia", "Moreira", 09878901, 19930110, 3400.0, 
"Química", 20101018, "");
insert into tb_professor values (0004, "Ester", "Soares", 78654789, 20001121, 1500.0, 
"Filosofia", 20210103, "");
insert into tb_professor values (0005, "Marcos", "Oliveira", 87346374, 19821015, 7000.0, 
"Sociologia", 20000515, "");

create table tb_curso(
    cod_curso int not null auto_increment,
    nome_curso varchar(30),
    disciplinas varchar(30),
    atividades varchar(30),
    primary key(cod_curso)
);

insert into tb_curso values (1111, "Física", "Calculo", "");
insert into tb_curso values (2222,"Letras", "Verbos", "");
insert into tb_curso values (3333,"Medicina", "Anatomia", "");
insert into tb_curso values (4444,"Direito", "Codigo Penal", "");
insert into tb_curso values (5555,"Teologia", "Filosofia", "");

create table tb_disciplina(
    nome_disciplina varchar(30),
    carga_horaria int(10));

insert into tb_disciplina values ("Calculo",20);
insert into tb_disciplina values ("Verbos",40);
insert into tb_disciplina values ("Anatomia",50);
insert into tb_disciplina values ("Código Penal",70);
insert into tb_disciplina values ("Filosofia",60);

create table tb_telefone_prof (
    cod_prof int not null auto_increment,
    tel_professor int (30),
    primary key (cod_prof)
);

insert into tb_telefone_prof values (1, 419976412);
insert into tb_telefone_prof values (2, 419999847);
insert into tb_telefone_prof values (3, 419964756);
insert into tb_telefone_prof values (4, 419900932);
insert into tb_telefone_prof values (5, 419967321);

create table tb_telefone_aluno (
    cod_aluno int not null auto_increment,
    tel_aluno int (30),
    primary key (cod_aluno)
);

insert into tb_telefone_aluno values (0001,769926423);
insert into tb_telefone_aluno values (0002,119974537);
insert into tb_telefone_aluno values (0003,119992736);
insert into tb_telefone_aluno values (0004,090997265);
insert into tb_telefone_aluno values (0005,419937484);

create table tb_endereco_aluno (
    cod_aluno int not null auto_increment,
    rua varchar(30),
    bairro varchar(30),
    primary key (cod_aluno)
);

insert into tb_endereco_aluno values (0001, "Rua brasil", "Novo mundo");
insert into tb_endereco_aluno values (0002, "Rua Pedro Zagonel", "Portão");
insert into tb_endereco_aluno values (0003, "Rua Republica Argentina", "Fazendinha");
insert into tb_endereco_aluno values (0004, "Rua Kennedy", "Pinheirinho");
insert into tb_endereco_aluno values (0005, "Rua Marechal Rondon", "Orleans");


create table tb_endereco_porf (
    cod_prof int not null auto_increment,
    rua varchar(30),
    bairro varchar(30),
    primary key (cod_prof)
);

insert into tb_endereco_prof values (1, "Rua liberdade", "Novo Mundo");
insert into tb_endereco_prof values (2, "Rua Vinte Um de Abril", "Portão");
insert into tb_endereco_prof values (3, "Rua Alfredo Moreira", "Portão");
insert into tb_endereco_prof values (4, "Rua Marechal Deodoro", "Fazendinha");
insert into tb_endereco_prof values (5, "Rua Pedro Gusso", "Portão");

create table tb_futebol (
    cod_aluno int not null auto_increment,
    nome_aluno varchar (30),
    primary key (cod_aluno)
);

insert into tb_futebol values (0001, "Lucas");
insert into tb_futebol values (0002, "João");

create table tb_basquete (
    cod_aluno int not null auto_increment,
    nome_aluno varchar (30),
    primary key (cod_aluno)
);

insert into tb_basquete values (0001, "Lucas");
insert into tb_basquete values (0002, "João");
insert into tb_basquete values (0005, "Felipe");

create table tb_volei (
    cod_aluno int not null auto_increment,
    nome_aluno varchar (30),
    primary key (cod_aluno)
);

insert into tb_volei values (0001, "Lucas");
insert into tb_volei values (0002, "João");
insert into tb_volei values (0003, "Lívia");
insert into tb_volei values (0004, "Amanda");

create table tb_handball (
    cod_aluno int not null auto_increment,
    nome_aluno varchar (30),
    primary key (cod_aluno)
);

insert into tb_handball values (0001, "Lucas");
insert into tb_handball values (0003, "Lívia");
insert into tb_handball values (0004, "Amanda");

create table tb_grupo_de_estudos (
    cod_aluno int not null auto_increment,
    nome_aluno varchar (30),
    primary key (cod_aluno)
);

insert into tb_grupo_de_estudos values (0001, "Lucas");
insert into tb_grupo_de_estudos values (0002, "João");
insert into tb_grupo_de_estudos values (0003, "Lívia");
insert into tb_grupo_de_estudos values (0004, "Amanda");
insert into tb_grupo_de_estudos values (0005, "Felipe");

create table tb_centro_academico (
    cod_aluno int not null auto_increment,
    nome_aluno varchar (30),
    primary key (cod_aluno)
);

insert into tb_centro_academico values (0001, "Lucas"); 
insert into tb_centro_academico values (0004, "Amanda"); 

create  table tb_parente (
    cod_parente int not null auto_increment,
    nome_parente varchar(30),
    sobrenome_parente varchar(30),
    cpf int(30),
    data_de_nascimento date,
    parentesco varchar(30),
    primary key (cod_parente)
    
);

insert into tb_endereco_parente values(1010, "Rua Pelé", "Novo mundo");
insert into tb_endereco_parente values(1020, "Rua Antonio Fagundes", "Santa Candida");
insert into tb_endereco_parente values(1030, "Rua Zumbi dos Palmares", "Novo mundo");
insert into tb_endereco_parente values(1040, "Rua Felicidade", "Portão");
insert into tb_endereco_parente values(1050, "Rua Nova Vida", "Capão Raso");

create table tb_telefone_parente (
    cod_parente int not null auto_increment,
    tel_parente int (30),
    primary key (cod_parente)
);

insert into tb_telefone_parente values (1010, 41978346);
insert into tb_telefone_parente values (1020, 41988924);
insert into tb_telefone_parente values (1030, 41976422);
insert into tb_telefone_parente values (1040, 41927638);
insert into tb_telefone_parente values (1050, 41923282);

create table tb_endereco_parente (
    cod_parente int not null auto_increment,
    rua varchar(30),
    bairro varchar(30),
    primary key (cod_parente)
); 

insert into tb_endereco_parente values(1010, "Rua Pelé", "Novo mundo");
insert into tb_endereco_parente values(1020, "Rua Antonio Fagundes", "Santa Candida");
insert into tb_endereco_parente values(1030, "Rua Zumbi dos Palmares", "Novo mundo");
insert into tb_endereco_parente values(1040, "Rua Felicidade", "Portão");
insert into tb_endereco_parente values(1050, "Rua Nova Vida", "Capão Raso");



# 1.Quantos professores há nessa universidade?

select count(cod_prof) from tb_professor;

# 2.Quantos professores ganham o salário maior que R$2000,00 reais?

select count(cod_prof) from tb_professor where salario > 2000.0;

# 3.Quantos professores tem mais de 35 anos?

select count(cod_prof) from tb_professor where year(data_de_nascimento) < 1987;

# 4.Quantos alunos tem menos de 20 anos?

select count(cod_aluno) from tb_aluno where year(data_de_nascimento) > 2002;

# 5.Quantos alunos ingressaram esse ano na universidade?

select count(cod_aluno) from tb_aluno where year(data_de_ingresso) = 2022;

# 6.Quantos cursos tem no plantel da universidade?

select count(cod_curso) from tb_curso;

# 7.Quantos alunos tem mais de 35 anos?

select count(cod_aluno) from tb_aluno where year(data_de_nascimento) < 1987;

# 8.Quantos alunos fazem parte do Centro Acadêmico?

select count(cod_aluno) from tb_centro_academico;

# 9.Quantos professores estão a mais de 5 anos na universidade?

select count(cod_prof) from tb_professor where year(data_de_ingresso) < 2017;

# 10.Quantos alunos estão participando do futebol?

select count(cod_aluno) from tb_futebol;

# 11.Quantos alunos estão participando do grupo de estudos?

select count(cod_aluno) from tb_grupo_de_estudos;

# 12.Quantos alunos estão participando do vôlei?

select count(cod_aluno) from tb_volei;

# 13.Quantos alunos estão participando do basquete?

select count(cod_aluno) from tb_basquete;

# 14.Quantos alunos estão participando do handball?

select count(cod_aluno) from tb_handball;

# 15.Quantos alunos tem nessa universidade?

select count(cod_aluno) from tb_aluno;

# 16.Quais disciplinas tem mais de 40 horas de carga horaria?

select count(carga_horaria) from tb_disciplina where carga_horaria > 40;

# 17.Quantos alunos estão há mais de 3 anos na universidade?

select count(cod_aluno) from tb_aluno where year(data_de_ingresso) < 2019;

# 18.Quantos professor ingressaram esse ano na universidade?

select count(cod_prof) from tb_professor where year(data_de_ingresso)  = 2022;

# 19. Quantos professores recebem menos de 2000 reais?

select count(cod_prof) from tb_professor where salario < 2000;

# 20. Quantos alunos ingressaram no ano passado na universidade?

select count(cod_aluno) from tb_aluno where year(data_de_ingresso) = 2019;


