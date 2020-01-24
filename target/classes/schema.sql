CREATE TABLE turysci(
    idT BIGINT AUTO_INCREMENT,
    login VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(50),
    CONSTRAINT t_pk PRIMARY KEY (idT)
);

CREATE TABLE punkty(
    idP BIGINT AUTO_INCREMENT,
    nazwa VARCHAR(50),
    lat DOUBLE,
    lon DOUBLE,
    x DOUBLE,
    y DOUBLE,
    CONSTRAINT p_pk PRIMARY KEY (idP)
);

CREATE TABLE odcinki(
    line_id BIGINT NOT NULL AUTO_INCREMENT,
    punkt_start BIGINT,
    punkt_koniec BIGINT,
    CONSTRAINT o_pk PRIMARY KEY (line_id),
    CONSTRAINT o_p1_fk FOREIGN KEY (punkt_start) REFERENCES punkty,
    CONSTRAINT o_p2_fk FOREIGN KEY (punkt_koniec) REFERENCES punkty
);

CREATE TABLE wycieczki(
    id_trip BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    CONSTRAINT w_pk PRIMARY KEY (id_trip)
);
--
-- CREATE TABLE wycieczki_odcinki (
--     id_trip BIGINT,
--     line_id BIGINT,
--     CONSTRAINT wo_it_fk FOREIGN KEY (id_trip) REFERENCES wycieczki,
--     CONSTRAINT wo_li_fk FOREIGN KEY (line_id) REFERENCES odcinki
-- )



