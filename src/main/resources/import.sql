insert into estado (nome) values ('Rio Grande Do Sul');
insert into estado (nome) values ('Sergipe');

insert into cidade (nome, estado_id) values ('Lagarto', 2);
insert into cidade (nome, estado_id) values ('Aracaju', 2);

insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Brasileira');

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas' );
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Thai Gourmet', 10, 1, 1, '49400-000', 'Rua Whatever', '1000', 'Centro');
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.50, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Chatamiolé Comida Indiana', 5, 2);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
