create table seguros
(
    id  number GENERATED BY DEFAULT AS IDENTITY,
    tipo_seguro  varchar2(255) NOT NULL,
    apolice_id number NOT NULL,
    vl_cobertura number NOT NULL,
    premio number not null,
    status_seguro varchar2(10) not null,
    PRIMARY KEY (id),
    foreign key (apolice_id) references apolices(id)
);