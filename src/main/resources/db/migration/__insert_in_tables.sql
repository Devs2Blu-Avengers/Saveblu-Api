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
('token1', 1, 11.111, 22.222, 'Device 1'),
('token2', 2, 33.333, 44.444, 'Device 2'),
('token3', 3, 55.555, 66.666, 'Device 3'),
('token4', 4, 77.777, 88.888, 'Device 4'),
('token5', 5, 99.999, 101.010, 'Device 5'),
('token6', 6, 111.111, 121.212, 'Device 6'),
('token7', 7, 131.313, 141.414, 'Device 7'),
('token8', 8, 151.515, 161.616, 'Device 8');

-- Incidentes
INSERT INTO core.incidence (date, latitude, longitude, category, description, user_id, urgent, status, valid) VALUES
('2023-10-01', 40.123, -74.456, 'INCENDIO', 'Incêndio na rua principal', 1, true, true, true),
('2023-10-02', 35.678, -78.901, 'ENCHENTE', 'Enchente na avenida central', 2, true, true, true),
('2023-10-03', 38.246, -82.345, 'ALAGAMENTO', 'Alagamento no bairro', 3, false, true, true),
('2023-10-04', 42.345, -76.789, 'RISCO_ELETRICO', 'Risco elétrico na escola', 4, true, true, true),
('2023-10-05', 41.111, -71.111, 'AFOGAMENTO', 'Afogamento na praia', 5, true, true, true),
('2023-10-06', 45.555, -80.000, 'DESLIZAMENTO', 'Deslizamento de terra', 6, true, true, true),
('2023-10-07', 39.999, -75.555, 'OUTROS', 'Outra ocorrência', 7, false, true, true),
('2023-10-08', 36.667, -72.222, 'POLUICAO_AMBIENTAL', 'Poluição no rio', 8, false, true, true);


