-- INCIDENTES

-- INCENDIO
INSERT INTO core.incidence (date, latitude, longitude, category, description, user_id, urgent, status, valid) VALUES
('2023-12-26', -26.918600, -49.063400, 'INCENDIO', 'Um incêndio de grandes proporções atinge uma casa na Rua das Palmeiras, Victor Konder. As chamas estão altas e há risco de desabamento. Bombeiros estão a caminho para controlar a situação.', 9, true, true, true),
('2023-12-27', -26.921200, -49.062800, 'INCENDIO', 'Um prédio comercial na Rua Alberto Stein, Velha, está em chamas. Há relatos de uma pessoa presa no segundo andar. As equipes de resgate estão mobilizadas para o salvamento.', 10, true, true, true),
('2023-12-28', -26.923800, -49.062200, 'INCENDIO', 'Um incêndio se alastra no Edifício Manoel Flor, Garcia. Os moradores estão em pânico e precisam ser evacuados com urgência. Bombeiros estão trabalhando para conter as chamas.', 12, true, true, true),
('2023-12-26', -26.925600, -49.059200, 'INCENDIO', 'Um incêndio de grandes proporções atinge uma residência na Rua Almirante Barroso, Progresso. O fogo se alastra rapidamente e a situação é crítica.', 9, true, true, true),
('2023-12-27', -26.923400, -49.065800, 'INCENDIO', 'Um incêndio atingiu o comércio na Rua Hermann Hering, Centro Histórico. O estabelecimento está tomado pelas chamas e é necessário um esforço conjunto para controlar a situação.', 10, true, true, true),
('2023-12-28', -26.920800, -49.069300, 'INCENDIO', 'Um incêndio em uma área de vegetação próxima ao Parque Ramiro Ruediger está se alastrando rapidamente. As chamas estão chegando perigosamente perto de residências.', 12, true, true, true);

-- ENCHENTE
INSERT INTO core.incidence (date, latitude, longitude, category, description, user_id, urgent, status, valid) VALUES
('2023-11-04', -26.912159, -49.072482, 'ENCHENTE', 'Enchente na Rua Belo Horizonte, Victor Konder. Ruas estão intransitáveis.', 12, true, true, true),
('2023-12-20', -26.911800, -49.055400, 'ENCHENTE', 'Uma forte tempestade atingiu a região, causando uma enchente repentina na Rua 7 de Setembro, Centro. A água subiu rapidamente, deixando moradores em situação de pânico. Equipes de resgate estão a caminho para ajudar.', 9, true, true, true),
('2023-12-21', -26.913900, -49.062200, 'ENCHENTE', 'As águas do rio transbordaram e invadiram a Rua Padre Jacobs, Ponta Aguda. Vários animais de estimação estão presos em casas e precisam ser resgatados.', 10, true, true, true),
('2023-12-22', -26.918700, -49.058600, 'ENCHENTE', 'A enchente atingiu a Rua XV de Novembro, Centro Histórico, deixando vários idosos em situação de risco. A água já está dentro de algumas residências e eles precisam de socorro urgente.', 12, true, true, true),
('2023-12-23', -26.912500, -49.058900, 'ENCHENTE', 'A inundação na Rua Curt Hering, Centro, deixou vários carros submersos. Os proprietários estão desesperados e pedem ajuda para resgatá-los.', 9, true, true, true),
('2023-12-24', -26.915800, -49.060700, 'ENCHENTE', 'A enchente atingiu a Rua João Pessoa, Progresso, e várias famílias tiveram que abandonar suas casas. Os desabrigados necessitam de abrigo e assistência imediata.', 10, true, true, true),
('2023-12-25', -26.917300, -49.062500, 'ENCHENTE', 'A inundação na Rua Amazonas, Victor Konder, afetou uma área de mata e vários animais silvestres estão em perigo. Equipes de resgate de animais estão a caminho para ajudar.', 12, true, true, true);

-- ALAGAMENTO
INSERT INTO core.incidence (date, latitude, longitude, category, description, user_id, urgent, status, valid) VALUES
('2023-12-17', -26.922300, -49.056700, 'ALAGAMENTO', 'Chuvas intensas causaram alagamento na Rua Bahia, Garcia. Algumas casas estão com água dentro e moradores pedem socorro.', 9, true, true, true),
('2023-12-18', -26.919900, -49.059800, 'ALAGAMENTO', 'Rua São Paulo, Escola Agrícola, está completamente alagada devido às chuvas. Alguns moradores estão ilhados e precisam de ajuda para sair.', 10, true, true, true),
('2023-12-19', -26.926600, -49.051200, 'ALAGAMENTO', 'Ribeirão da Velha transbordou, causando alagamento na Rua Dois de Setembro, Itoupava Norte. Algumas pessoas estão em situação de risco e necessitam de resgate.', 12, true, true, true),
('2023-12-17', -26.923400, -49.065200, 'ALAGAMENTO', 'Devido às fortes chuvas, a Rua Antônio Cunha, Vorstadt, está completamente alagada. Moradores estão isolados em suas casas, precisando de ajuda para evacuação.', 9, true, true, true),
('2023-12-18', -26.929100, -49.058900, 'ALAGAMENTO', 'A Praça Dr. Blumenau, Centro, está inundada após o transbordamento do rio. Moradores estão em seus andares superiores aguardando resgate.', 10, true, true, true),
('2023-12-19', -26.933600, -49.053700, 'ALAGAMENTO', 'Devido às chuvas contínuas, a Rua XV de Novembro, Centro, está alagada. Moradores estão em suas casas, mas a água continua subindo.', 12, true, true, true);

-- RISCO_ELETRICO
INSERT INTO core.incidence (date, latitude, longitude, category, description, user_id, urgent, status, valid) VALUES
('2023-12-11', -26.929300, -49.061800, 'RISCO_ELETRICO', 'Fios elétricos caídos representam perigo na Rua Rudolfo Schreiber, Fortaleza.', 9, true, true, true),
('2023-12-12', -26.934700, -49.056500, 'RISCO_ELETRICO', 'Poste com fios descascados e expostos apresenta risco iminente na Rua Bahia, Garcia.', 10, true, true, true),
('2023-12-13', -26.940200, -49.052300, 'RISCO_ELETRICO', 'Fiação solta e exposta em ponto de ônibus na Rua Alberto Stein, Velha.', 12, true, true, true),
('2023-12-14', -26.946800, -49.048100, 'RISCO_ELETRICO', 'Transformador com vazamento de óleo na Rua Humberto de Campos, Victor Konder.', 9, true, true, true),
('2023-12-15', -26.951200, -49.043800, 'RISCO_ELETRICO', 'Fiação desgastada e exposta na Rua São Paulo, Escola Agrícola.', 10, true, true, true),
('2023-12-16', -26.956500, -49.040600, 'RISCO_ELETRICO', 'Poste inclinado e fios caídos na Rua Antônio da Veiga, Victor Konder.', 12, true, true, true);

-- AFOGAMENTO
INSERT INTO core.incidence (date, latitude, longitude, category, description, user_id, urgent, status, valid) VALUES
('2023-12-05', -26.922800, -49.077900, 'AFOGAMENTO', 'Criança de 8 anos escorregou na margem do córrego ao tentar pegar uma bola. A família estava em um piquenique na área próxima ao Parque Ramiro Ruediger.', 9, true, true, true),
('2023-12-06', -26.919600, -49.076200, 'AFOGAMENTO', 'Jovem de 21 anos tentou atravessar o rio nadando, mas foi arrastado pela correnteza forte. O incidente ocorreu próximo à Ponte Adolfo Konder, na região central.', 10, true, true, true),
('2023-12-07', -26.926500, -49.080500, 'AFOGAMENTO', 'Idoso de 73 anos escorregou na margem do rio enquanto pescava. O ocorrido se deu na área próxima ao Parque Vila Germânica.', 12, true, true, true),
('2023-12-08', -26.930200, -49.081800, 'AFOGAMENTO', 'Jovem de 19 anos tentava atravessar o rio em uma área não recomendada para banho, quando foi arrastado pela correnteza. O incidente ocorreu próximo à Rua Progresso, Velha.', 9, true, true, true),
('2023-12-09', -26.925100, -49.073600, 'AFOGAMENTO', 'Criança de 6 anos caiu no córrego enquanto brincava na beira da água com amigos. O ocorrido se deu próximo à Rua Benjamin Constant, Escola Agrícola.', 10, true, true, true),
('2023-12-10', -26.934700, -49.084200, 'AFOGAMENTO', 'Idoso de 68 anos estava pescando em uma área de rio com correnteza forte quando perdeu o equilíbrio e caiu na água. O incidente ocorreu próximo à Rua Victor Konder, Vila Nova.', 12, true, true, true);

-- DESLIZAMENTO
INSERT INTO core.incidence (date, latitude, longitude, category, description, user_id, urgent, status, valid) VALUES
('2023-11-05', -26.899200, -49.082800, 'DESLIZAMENTO', 'Deslizamento de terra no Bairro Progresso, próximo à Rua Rio de Janeiro.', 9, true, true, true),
('2023-11-06', -26.895600, -49.082300, 'DESLIZAMENTO', 'Deslizamento de terra no Bairro Velha, próximo à Rua Dois de Setembro.', 10, true, true, true),
('2023-11-07', -26.892100, -49.078900, 'DESLIZAMENTO', 'Deslizamento de terra no Bairro Fortaleza, próximo à Rua Werner Duwe.', 12, true, true, true),
('2023-12-02', -26.912100, -49.078900, 'DESLIZAMENTO', 'Deslizamento de terra em área de risco próximo à Rua Sete de Setembro, Centro.', 9, true, true, true),
('2023-12-03', -26.917300, -49.086400, 'DESLIZAMENTO', 'Deslizamento de encosta próximo à Rua Hermann Tribess, Tribess.', 10, true, true, true),
('2023-12-04', -26.920700, -49.089100, 'DESLIZAMENTO', 'Deslizamento de morro próximo à Rua Almirante Tamandaré, Vila Itoupava.', 12, true, true, true);

-- POLUICAO_AMBIENTAL
INSERT INTO core.incidence (date, latitude, longitude, category, description, user_id, urgent, status, valid) VALUES
('2023-11-08', -26.915400, -49.088100, 'POLUICAO_AMBIENTAL', 'Poluição do Rio Itajaí-Açu devido a despejo de produtos químicos, próximo à Rua Benjamin Constant.', 11, false, true, true),
('2023-11-09', -26.898200, -49.086900, 'POLUICAO_AMBIENTAL', 'Poluição do Rio Itajaí devido a vazamento de óleo, próximo à Rua Sete de Setembro.', 9, false, true, true),
('2023-11-10', -26.894600, -49.090800, 'POLUICAO_AMBIENTAL', 'Poluição do Rio Testo Salto devido a descarte incorreto de resíduos, próximo à Rua Amazonas.', 10, false, true, true),
('2023-11-29', -26.943800, -49.070500, 'POLUICAO_AMBIENTAL', 'Emissão de poluentes atmosféricos por indústria na Rua Hermann Huscher, Itoupava Central.', 9, false, true, true),
('2023-11-30', -26.924200, -49.073800, 'POLUICAO_AMBIENTAL', 'Despejo irregular de produtos químicos em córrego na Rua Amazonas, Garcia.', 10, false, true, true),
('2023-12-01', -26.938600, -49.058200, 'POLUICAO_AMBIENTAL', 'Descarte ilegal de resíduos industriais na Rua Norberto Seara Heusi, Vila Nova.', 12, false, true, true);

-- TRAFICO_ANIMAIS
INSERT INTO core.incidence (date, latitude, longitude, category, description, user_id, urgent, status, valid) VALUES
('2023-11-11', -26.919900, -49.097600, 'TRAFICO_ANIMAIS', 'Tráfico de aves silvestres na região do Bairro Salto Weissbach. Aves em cativeiro em residência.', 9, false, true, true),
('2023-11-12', -26.932300, -49.068900, 'TRAFICO_ANIMAIS', 'Tráfico de animais exóticos na região do Bairro Itoupava Norte. Animais mantidos em condições precárias.', 10, false, true, true),
('2023-11-13', -26.948700, -49.077200, 'TRAFICO_ANIMAIS', 'Tráfico de filhotes de capivara na região do Bairro Testo Salto. Animais em situação de maus-tratos.', 12, false, true, true),
('2023-11-26', -26.940700, -49.048200, 'TRAFICO_ANIMAIS', 'Tráfico de animais silvestres na região do Bairro Tribess. Animais mantidos em cativeiro em residência.', 9, false, true, true),
('2023-11-27', -26.955300, -49.037900, 'TRAFICO_ANIMAIS', 'Tráfico de animais exóticos na região do Bairro Salto do Norte. Animais em situação de maus-tratos.', 10, false, true, true),
('2023-11-28', -26.965600, -49.023600, 'TRAFICO_ANIMAIS', 'Tráfico de filhotes de cães e gatos na região do Bairro Itoupavazinha. Animais em condições precárias.', 12, false, true, true);

-- DESMATAMENTO_ILEGAL
INSERT INTO core.incidence (date, latitude, longitude, category, description, user_id, urgent, status, valid) VALUES
('2023-11-14', -26.895600, -49.075200, 'DESMATAMENTO_ILEGAL', 'Desmatamento ilegal na área de preservação do Parque Nacional da Serra do Itajaí, próximo à Rua Hermann Huscher.', 9, false, true, true),
('2023-11-15', -26.890700, -49.058500, 'DESMATAMENTO_ILEGAL', 'Desmatamento ilegal na área de preservação do Parque Ecológico Spitzkopf, próximo à Rua Mário Giese.', 10, false, true, true),
('2023-11-16', -26.920200, -49.071800, 'DESMATAMENTO_ILEGAL', 'Desmatamento ilegal na área de preservação do Parque Natural Municipal Nascentes do Ribeirão Garcia, próximo à Rua Alfredo Vogelbacher.', 12, false, true, true),
('2023-11-23', -26.897400, -49.076700, 'DESMATAMENTO_ILEGAL', 'Desmatamento ilegal na área de preservação do Parque Natural Municipal São Francisco de Assis, próximo à Rua Otto Benjamim Laubenstein.', 9, false, true, true),
('2023-11-24', -26.921600, -49.066400, 'DESMATAMENTO_ILEGAL', 'Desmatamento ilegal na área de preservação do Parque Natural Municipal Nascentes do Garcia, próximo à Rua Arnoldo Hemkemeier.', 10, false, true, true),
('2023-11-25', -26.938200, -49.058800, 'DESMATAMENTO_ILEGAL', 'Desmatamento ilegal na área de preservação do Parque Natural Municipal São João, próximo à Rua Max Tressmann.', 12, false, true, true);

-- CONTAMINACAO
INSERT INTO core.incidence (date, latitude, longitude, category, description, user_id, urgent, status, valid) VALUES
('2023-11-17', -26.915100, -49.066800, 'CONTAMINACAO', 'Contaminação do solo por vazamento de produtos químicos na Rua Dr. Pedro Zimmermann, Itoupava Central.', 9, false, true, true),
('2023-11-18', -26.900500, -49.077300, 'CONTAMINACAO', 'Contaminação do córrego devido a descarte irregular de resíduos na Rua Gustavo Zimmermann, Itoupavazinha.', 10, false, true, true),
('2023-11-19', -26.918400, -49.072700, 'CONTAMINACAO', 'Poluição do ar devido a emissões de uma fábrica na Rua Bahia, Água Verde.', 12, false, true, true),
('2023-11-20', -26.909700, -49.071200, 'CONTAMINACAO', 'Contaminação do solo por resíduos industriais na Rua São Paulo, Victor Konder.', 9, false, true, true),
('2023-11-21', -26.891500, -49.069800, 'CONTAMINACAO', 'Contaminação do córrego devido a vazamento de produtos químicos na Rua Almirante Tamandaré, Ponta Aguda.', 10, false, true, true),
('2023-11-22', -26.912300, -49.078500, 'CONTAMINACAO', 'Poluição do ar devido a queima de resíduos na Rua Sete de Setembro, Centro.', 12, false, true, true);
