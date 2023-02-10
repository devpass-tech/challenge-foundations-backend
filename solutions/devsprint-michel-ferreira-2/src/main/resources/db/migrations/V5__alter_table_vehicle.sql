ALTER TABLE vehicle ADD COLUMN license_plate varchar(8) NOT NULL after owner;
ALTER TABLE vehicle ADD CONSTRAINT UC_vehicle UNIQUE (license_plate);