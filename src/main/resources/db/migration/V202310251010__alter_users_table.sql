ALTER TABLE users
ALTER COLUMN device_token SET NOT NULL,
ALTER COLUMN last_latitude SET NOT NULL,
ALTER COLUMN last_longitude SET NOT NULL;