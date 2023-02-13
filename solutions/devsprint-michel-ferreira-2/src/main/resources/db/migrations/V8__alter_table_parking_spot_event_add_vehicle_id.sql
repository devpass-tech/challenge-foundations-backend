ALTER TABLE parking_spot_event ADD vehicle_id varchar(36) after event;
UPDATE parking_spot_event SET vehicle_id = "0";
ALTER TABLE parking_spot_event MODIFY COLUMN vehicle_id varchar(36) NOT NULL;