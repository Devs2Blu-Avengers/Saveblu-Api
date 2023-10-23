-- Usuários
INSERT INTO core.users (email, name, telephone, type) VALUES
('user1@example.com', 'User 1', '9111-1111', 'CIDADAO'),
('user2@example.com', 'User 2', '9222-2222', 'ENFERMEIRO'),
('user3@example.com', 'User 3', '9333-3333', 'BOMBEIRO'),
('user4@example.com', 'User 4', '9444-4444', 'BRIGADISTA'),
('user5@example.com', 'User 5', '9555-5555', 'FISCAL_AMBIENTAL'),
('user6@example.com', 'User 6', '9666-6666', 'POLICIA_AMBIENTAL'),
('user7@example.com', 'User 7', '9777-7777', 'DEFESA_CIVIL'),
('user8@example.com', 'User 8', '9888-8888', 'FISCAL_SANITARIO');

-- Dispositivos
INSERT INTO core.device (token, user_id, last_latitude, last_longitude, number) VALUES
('token1', 1, -26.914543, -49.067765, 'Device 1'),
('token2', 2, -26.915123, -49.067891, 'Device 2'),
('token3', 3, -26.914987, -49.067432, 'Device 3'),
('token4', 4, -26.915345, -49.067557, 'Device 4'),
('token5', 5, -26.914324, -49.067932, 'Device 5'),
('token6', 6, -26.915432, -49.067567, 'Device 6'),
('token7', 7, -26.914765, -49.067123, 'Device 7'),
('token8', 8, -26.914987, -49.067890, 'Device 8');

-- Incidentes
INSERT INTO core.incidence (date, latitude, longitude, category, description, user_id, urgent, status, valid) VALUES
('2023-10-01', -26.914543, -49.067765, 'INCENDIO', 'Incêndio na rua principal', 1, true, true, true),
('2023-10-02', -26.915123, -49.067891, 'ENCHENTE', 'Enchente na avenida central', 2, true, true, true),
('2023-10-03', -26.914987, -49.067432, 'ALAGAMENTO', 'Alagamento no bairro', 3, false, true, true),
('2023-10-04', -26.915345, -49.067557, 'RISCO_ELETRICO', 'Risco elétrico na escola', 4, true, true, true),
('2023-10-05', -26.914324, -49.067932, 'AFOGAMENTO', 'Afogamento na praia', 5, true, true, true),
('2023-10-06', -26.915432, -49.067567, 'DESLIZAMENTO', 'Deslizamento de terra', 6, true, true, true),
('2023-10-07', -26.914765, -49.067123, 'OUTROS', 'Outra ocorrência', 7, false, true, true),
('2023-10-08', -26.914987, -49.067890, 'POLUICAO_AMBIENTAL', 'Poluição no rio', 8, false, true, true);


