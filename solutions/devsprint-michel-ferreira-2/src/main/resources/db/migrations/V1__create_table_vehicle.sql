CREATE TABLE vehicle (
    id varchar(36) PRIMARY KEY,
    brand varchar(255) NOT NULL,
    color varchar(255) NOT NULL,
    owner varchar(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);