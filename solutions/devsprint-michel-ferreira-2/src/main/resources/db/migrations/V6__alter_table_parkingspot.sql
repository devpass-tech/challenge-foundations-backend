ALTER TABLE parking_spot MODIFY COLUMN in_use_by varchar(36);
ALTER TABLE parking_spot ADD CONSTRAINT UC_parkingspot UNIQUE (floor, spot, in_use_by);