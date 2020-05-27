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

update shop.customer
set authority_id = (select id from shop.authority where authority = 'ROLE_USER');

update shop.customer
set password = '$2y$12$hAtgEqN94PMOF.CibWMitedP3uGbtnG7hFE9kytIjuVS4YwvC.7kG'; --pass1234