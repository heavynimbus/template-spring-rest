CREATE TABLE author
(
    id   SERIAL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT author_pk PRIMARY KEY (id)
);

CREATE TABLE book
(
    id        SERIAL,
    title     VARCHAR(255)  NOT NULL,
    price     NUMERIC(5, 2) NOT NULL,
    author_id INTEGER       NOT NULL,
    CONSTRAINT book_pk PRIMARY KEY (id),
    CONSTRAINT book_author_fk FOREIGN KEY (author_id) REFERENCES author (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
