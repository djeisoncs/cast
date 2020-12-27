create table castavaliacao.tb_categoria (
    id uuid not null,
    codigo int4,
    descricao varchar(255),
    primary key (id)
);

INSERT INTO castavaliacao.tb_categoria(id, codigo, descricao)VALUES
    ('0a650edf-ea0b-430b-a14e-2d933505f130', 1, 'Comportamental'),
    ('5bb92158-51f2-44bf-a28a-edb686a0eb78', 2, 'Programação'),
    ('8997c3f2-e265-455b-867c-04c5b9cdced7', 3, 'Qualidade'),
    ('fe112ddb-74db-4d25-9af8-fd5fe0601e27', 4, 'Processos');