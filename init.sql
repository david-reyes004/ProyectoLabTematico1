create database proyectoLabTem1;

describe usuarios;

describe artistas;

describe escenarios;

describe presentaciones;

SELECT * from usuarios;

SELECT *from eventos;

SELECT * FROM usuarios WHERE id ='5';

SELECT *FROM artistas;

SELECT *from escenarios;

SELECT * FROM presentaciones;

SELECT * from boletos;

DELETE FROM usuarios WHERE id = '2';

DELETE FROM boletos WHERE id_boletos =1;

DELETE FROM eventos WHERE id = 5;

insert into usuarios (nombre, correo, password)
values ( 'Raul', 'raul@gmail.com', '8769');

ALTER TABLE eventos DROP COLUMN hora_iniciol;

INSERT INTO eventos (nombre, fecha, hora_inicio)
VALUES ('Corona Capital', '2026-11-20', '13:00:00');

INSERT INTO artistas (genero, nombre_artistico)
VALUES ('RAP','Ice cube');

INSERT INTO boletos (compra, precio, eventos_id_eventos, usuarios_id_usuarios, nombre)
VALUES ('UN boleto', 1500, 1, 1, 'luis');

INSERT INTO escenarios(nombre,capacidad,eventos_id_eventos,ubicacion)
VALUES ('Freedom','8000',8,'Belgica')

INSERT INTO presentaciones(hora_presentacion, artistas_id_artistas, escenarios_id_escenarios, eventos_id_eventos)
VALUES ('21:00 HRS',1,6,9)



