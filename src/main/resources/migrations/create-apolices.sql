    create table apolices (
        id  number GENERATED BY DEFAULT AS IDENTITY,
        cliente_id number,
        coberturas varchar2(255),
        primary key (id),
        foreign key (cliente_id) references clientes(id)
    );