ALTER TABLE parking_spot_event MODIFY COLUMN event ENUM('CHECKIN', 'CHECKOUT', 'CHECKIN_DUPLICADO');

UPDATE parking_spot_event SET event = 'CHECKIN' where event = 'Check-in';
UPDATE parking_spot_event SET event = 'CHECKOUT' where event = 'Check-out';
UPDATE parking_spot_event SET event = 'CHECKIN_DUPLICADO' where event = 'Check-in-duplicado';