ALTER TABLE users
ADD COLUMN IF NOT EXISTS device_token varchar(255),
ADD COLUMN IF NOT EXISTS last_latitude decimal(10, 8),
ADD COLUMN IF NOT EXISTS last_longitude decimal(11, 8);