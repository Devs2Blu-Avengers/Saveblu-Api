ALTER TABLE users
ADD COLUMN IF NOT EXISTS device_token varchar(255),
ADD COLUMN IF NOT EXISTS last_latitude float(53),
ADD COLUMN IF NOT EXISTS last_longitude float(53);