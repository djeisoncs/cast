create table castavaliacao.tb_curso (
    id uuid not null,
    data_inicio timestamp,
    data_termino timestamp,
    descricao varchar(255),
    qtd_alunos_por_turma int4,
    id_categoria uuid,
    primary key (id)
);

alter table castavaliacao.tb_curso
add constraint FK7d8p490x40dinsvaa9slj4s6c
foreign key (id_categoria)
references castavaliacao.tb_categoria;