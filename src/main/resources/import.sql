insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Brasileira');

insert into restaurante (nome,taxa_frete, cozinha_id) values ("Thai Gourmet", 10, 1);
insert into restaurante (nome,taxa_frete, cozinha_id) values ("Thai Delivery", 9.50, 1);
insert into restaurante (nome,taxa_frete, cozinha_id) values ("Chatamiolé Comida Indiana", 5,2);

insert into estado (nome) values ('Rio Grande Do Sul');
insert into estado (nome) values ('Sergipe');

insert into cidade (nome, estado_id) values ("Lagarto", 2);
insert into cidade (nome, estado_id) values ("Aracaju", 2);
