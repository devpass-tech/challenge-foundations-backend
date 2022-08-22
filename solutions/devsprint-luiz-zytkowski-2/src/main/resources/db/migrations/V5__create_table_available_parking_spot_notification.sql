CREATE TABLE available_parking_spot_notification(
    id INT AUTO_INCREMENT PRIMARY KEY,
    parking_spot_id INT NOT NULL,
    email varchar(100) NOT NULL
);