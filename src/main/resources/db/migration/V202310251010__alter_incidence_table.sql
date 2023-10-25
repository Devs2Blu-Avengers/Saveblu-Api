-- CRIA A COLUNA
ALTER TABLE incidence
ADD COLUMN IF NOT EXISTS ticket_number varchar(255);

-- INSERE TICKETS NUMBERS PARA CADA REGISTRO

UPDATE incidence
SET ticket_number = CONCAT(
    22, 
    LPAD(EXTRACT(MONTH FROM current_timestamp)::varchar, 2, '0'), 
    LPAD(EXTRACT(DAY FROM current_timestamp)::varchar, 2, '0'), 
    LPAD(subquery.row_number::varchar, 4, '0')
)
FROM (
    SELECT id, row_number() OVER (ORDER BY id) 
    FROM incidence
) AS subquery
WHERE incidence.id = subquery.id;

-- ALTERA A COLUNA PARA NOT NULL
ALTER TABLE incidence
ALTER COLUMN ticket_number SET NOT NULL;

-- ALTERA A COLUNA PARA UNIQUE
ALTER TABLE incidence
ADD CONSTRAINT ticket_number_unique UNIQUE (ticket_number);
