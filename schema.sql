create table produto(
	codigo serial primary key,
	nome varchar(250) not null,
	preco float not null,
	especificacao int
);

create table especificacao(
	codigo serial primary key,
	fabricante varchar(250) not null,
	cor varchar(250) not null,
	sistema varchar(250) not null,
	detalhes varchar(250) not null
);