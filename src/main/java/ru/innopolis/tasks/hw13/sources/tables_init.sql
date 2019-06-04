CREATE TABLE public.users
(
    id          serial       NOT NULL,
    user_name   varchar(255) NOT NULL,
    login_id    int4         NOT NULL,
    city        varchar(255) NULL,
    email       varchar(255) NULL,
    description varchar(500) NULL,
    CONSTRAINT login_id_un UNIQUE (login_id),
    CONSTRAINT users_pk PRIMARY KEY (id)
)
    WITH (
        OIDS= FALSE
    );

CREATE TABLE public.roles
(
    id          serial       NOT NULL,
    role_name   varchar(255) NOT NULL,
    description varchar(500) NULL,
    CONSTRAINT roles_pk PRIMARY KEY (id),
    CONSTRAINT roles_un UNIQUE (role_name)
)
    WITH (
        OIDS= FALSE
    );


CREATE TABLE public.user_role
(
    id      serial NOT NULL,
    user_id int4   NOT NULL,
    role_id int4   NOT NULL,
    CONSTRAINT user_role_pk PRIMARY KEY (id)
)
    WITH (
        OIDS= FALSE
    );

INSERT INTO public.users(user_name, login_id, city, email, description)
values ('Rachel Green', 123, 'NY', 'green@friends.com', 'Very bad waitress');
INSERT INTO public.users(user_name, login_id, city, email, description)
values ('Ross Geller', 124, 'NY', 'dino@friends.com', 'Hiii....');
INSERT INTO public.users(user_name, login_id, city, email, description)
values ('Chandler Bing', 125, 'NY', 'woopa@friends.com', 'He has three...');
INSERT INTO public.users(user_name, login_id, city, email, description)
values ('Phoebe Buffe', 126, 'NY', 'spiritualist@friends.com', 'Musician');
INSERT INTO public.users(user_name, login_id, city, email, description)
values ('Joe Satriany', 127, 'NY', 'pizza@friends.com', 'How u doing?');
INSERT INTO public.users(user_name, login_id, city, email, description)
values ('Rachel Bloom', 369, 'SF', 'bloom@gmail.com', 'Another Rachel in DB');
