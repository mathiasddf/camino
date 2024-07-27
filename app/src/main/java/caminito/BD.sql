    -- Crear la base de datos
    CREATE DATABASE  caminito;
    USE caminito;

    -- Crear tabla de usuarios
    CREATE TABLE  usuarios (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(100) NOT NULL
    );

    -- Crear tabla de preguntas
    CREATE TABLE preguntas (
        id INT AUTO_INCREMENT PRIMARY KEY,
        pregunta TEXT NOT NULL,
        respuesta_correcta TEXT NOT NULL,
        nivel INT NOT NULL
    );

    -- Crear tabla de alternativas
    CREATE TABLE alternativas (
        id INT AUTO_INCREMENT PRIMARY KEY,
        pregunta_id INT NOT NULL,
        alternativa TEXT NOT NULL,
        es_correcta BOOLEAN NOT NULL,
        FOREIGN KEY (pregunta_id) REFERENCES preguntas(id)
    );

    -- Insertar preguntas de ejemplo
    INSERT INTO preguntas (pregunta, respuesta_correcta, nivel) VALUES
    ('¿Cuál es la capital de Francia?', 'París', 1),  -- Fácil
    ('¿Cuál es el continente más grande?', 'Asia', 1),  -- Fácil
    ('¿Qué elemento químico tiene el símbolo O?', 'Oxígeno', 1),  -- Fácil
    ('¿Qué es el ADN?', 'Ácido desoxirribonucleico', 2),  -- Medio
    ('¿Cuántos planetas hay en el sistema solar?', '8', 2),  -- Medio
    ('¿Quién escribió "Cien años de soledad"?', 'Gabriel García Márquez', 2),  -- Medio
    ('¿Cuál es el valor de π (pi)?', '3.14159', 3),  -- Difícil
    ('¿Qué teoría propone la expansión del universo?', 'Teoría del Big Bang', 3),  -- Difícil
    ('¿Qué es la relatividad general?', 'La teoría de la relatividad general', 3);  -- Difícil

    -- Insertar alternativas de ejemplo
    INSERT INTO alternativas (pregunta_id, alternativa, es_correcta) VALUES
    (1, 'París', TRUE),
    (1, 'Londres', FALSE),
    (1, 'Berlín', FALSE),
    (1, 'Madrid', FALSE),

    (2, 'Asia', TRUE),
    (2, 'África', FALSE),
    (2, 'América', FALSE),
    (2, 'Europa', FALSE),

    (3, 'Oxígeno', TRUE),
    (3, 'Oro', FALSE),
    (3, 'Osmio', FALSE),
    (3, 'Plata', FALSE),

    (4, 'Ácido desoxirribonucleico', TRUE),
    (4, 'Ácido ribonucleico', FALSE),
    (4, 'Proteína', FALSE),
    (4, 'Carbohidrato', FALSE),

    (5, '8', TRUE),
    (5, '7', FALSE),
    (5, '9', FALSE),
    (5, '10', FALSE),

    (6, 'Gabriel García Márquez', TRUE),
    (6, 'Mario Vargas Llosa', FALSE),
    (6, 'Jorge Luis Borges', FALSE),
    (6, 'Pablo Neruda', FALSE),

    (7, '3.14159', TRUE),
    (7, '3.14', FALSE),
    (7, '3.16', FALSE),
    (7, '3.12', FALSE),

    (8, 'Teoría del Big Bang', TRUE),
    (8, 'Teoría de la Relatividad', FALSE),
    (8, 'Teoría de la Evolución', FALSE),
    (8, 'Teoría de Cuerdas', FALSE),

    (9, 'La teoría de la relatividad general', TRUE),
    (9, 'La teoría cuántica', FALSE),
    (9, 'La teoría del caos', FALSE),
    (9, 'La teoría del electromagnetismo', FALSE);
