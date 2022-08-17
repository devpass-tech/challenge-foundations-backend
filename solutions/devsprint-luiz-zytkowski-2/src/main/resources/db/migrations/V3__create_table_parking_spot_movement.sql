CREATE TABLE parking_spot_movement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    parking_spot_id INT NOT NULL,
    event VARCHAR(500) NOT NULL,
    created_at TIMESTAMP NOT NULL
);