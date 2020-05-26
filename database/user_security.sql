CREATE TABLE shop.authority(
       id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
       authority VARCHAR(50) NOT NULL
);

ALTER TABLE shop.customer
    ADD authority_id INT REFERENCES shop.authority(id),
    ADD password VARCHAR(128); --bcrypt

INSERT INTO shop.authority(authority)
VALUES('ROLE_USER');

INSERT INTO shop.authority(authority)
VALUES('ROLE_ADMIN');