BASE DE DATOS


--------------------------------------------------------------------------------
CREATE TABLE medicos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255)
);

CREATE TABLE consultas (
    id BIGSERIAL PRIMARY KEY,
    nombre_paciente VARCHAR(40) NOT NULL,
    motivo_consulta VARCHAR(100) NOT NULL,
    numero_consultorio INTEGER,
    hora_inicio TIME,
    hora_fin TIME,
    medico_id BIGINT,
    CONSTRAINT fk_consultas_medico
        FOREIGN KEY (medico_id)
        REFERENCES medicos(id)
);

INSERT INTO medicos (nombre) VALUES
('Dr. Carlos Ramirez'),
('Dra. Laura Gomez'),
('Dr. Andres Torres');


INSERT INTO consultas (nombre_paciente, motivo_consulta, numero_consultorio, hora_inicio, hora_fin, medico_id) VALUES
('Diego Carvajal', 'Control general', 101, '08:00:00', '08:30:00', 1),
('Marta Lopez', 'Dolor de cabeza', 102, '09:00:00', '09:20:00', 2),
('Juan Castro', 'Chequeo cardiologico', 103, '10:00:00', '10:40:00', 3);



select * from consultas;


UPDATE consultas
SET nombre_paciente = 'Diego Perez'
WHERE nombre_paciente = 'Diego Carvajal';



---------------------------------------------------------------------------------------------
SWAGGER:
http://localhost:8080/swagger-ui/index.html
