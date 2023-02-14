insert into editora (nome, descricao) values ('Alta Books','Alta Books Editora');
insert into editora (nome, descricao) values ('Editora de Livros','Editora de Livro de Informação');

insert into categoria (nome) values ('Computação');
insert into categoria (nome) values ('Arquitetura');

insert into livro (isbn, nome, categoria_id, editora_id) values ('111111', 'Código Limpo', 1, 1);
insert into livro (isbn, nome, categoria_id, editora_id) values ('222222', 'Clean Architecture', 2, 2);


-- insert into perfil (nome, descricao) values ('ROLE_ADMIN', 'Perfil de administrador');
-- insert into perfil (nome, descricao) values ('ROLE_USER', 'Perfil de usuário comum');
