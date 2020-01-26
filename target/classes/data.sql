
insert into turysci (idT, login, email, password) VALUES (10, 'adam123',  'adamos@gmail.com', 'haslohaslo');
insert into turysci (idT, login, email, password) VALUES (11, 'hanna_b',  'hannas@gmail.com', 'h2h2h2');
insert into turysci (idT, login, email, password) VALUES (12, 'jacek_placek',  'placuch@gmail.com', 'nalesnik');


insert into punkty (idP, nazwa, lat, lon, x, y) VALUES (0, 'Schronisko PTTK Murowaniec', 49.243341, 20.006962, 569, 16);
insert into punkty (idP, nazwa, lat, lon, x, y) VALUES (1, 'Czarny Staw Gąsienicowy', 49.232714, 20.014627, 786, 390);
insert into punkty (idP, nazwa, lat, lon, x, y) VALUES (2, 'Hala Gąsienicowa', 49.241453, 20.001769, 480, 78);
insert into punkty (idP, nazwa, lat, lon, x, y) VALUES (3, 'Kolejka krzesełkowa Gąsienicowa, stacja dolna', 49.237093, 19.996598, 362, 232);
insert into punkty (idP, nazwa, lat, lon, x, y) VALUES (4, 'Zielony Staw Gąsienicowy', 49.227816, 20.002394, 500, 565);
insert into punkty (idP, nazwa, lat, lon) VALUES (5, 'Świnicka Przełęcz', 49.220927, 20.003797);
insert into punkty (idP, nazwa, lat, lon) VALUES (6, 'Karb', 49.228818, 20.011715);
insert into punkty (idP, nazwa, lat, lon) VALUES (7, 'Kościelec', 49.225503, 20.014679);
insert into punkty (idP, nazwa, lat, lon) VALUES (8, 'Czarny Staw Gąsienicowy, pod Granatami', 49.230487, 20.020872);
insert into punkty (idP, nazwa, lat, lon) VALUES (9, 'Nad Zmarzłym Stawem', 49.225308, 20.022688);
insert into punkty (idP, nazwa, lat, lon) VALUES (10, 'Zawrat', 49.219090, 20.016392);
insert into punkty (idP, nazwa, lat, lon) VALUES (11, 'Pod Świnicą', 49.219324, 20.009599);
insert into punkty (idP, nazwa, lat, lon) VALUES (12, 'Dwoiśniak', 49.234298, 19.993685);
insert into punkty (idP, nazwa, lat, lon) VALUES (13, 'Liliowe', 49.225214, 19.992521);
insert into punkty (idP, nazwa, lat, lon) VALUES (14, 'Czarny Staw Gąsienicowy', 49.232714, 20.014627);

insert into odcinki(punkt_start, punkt_koniec) VALUES (6, 7);
insert into odcinki(punkt_start, punkt_koniec) VALUES (7, 6);

insert into odcinki(punkt_start, punkt_koniec) VALUES (6, 5);
insert into odcinki(punkt_start, punkt_koniec) VALUES (5, 6);

insert into odcinki(punkt_start, punkt_koniec) VALUES (0, 1);
insert into odcinki(punkt_start, punkt_koniec) VALUES (1, 0);

insert into odcinki(punkt_start, punkt_koniec) VALUES (1, 6);
insert into odcinki(punkt_start, punkt_koniec) VALUES (6, 1);

insert into odcinki(punkt_start, punkt_koniec) VALUES (1, 8);
insert into odcinki(punkt_start, punkt_koniec) VALUES (8, 1);

insert into odcinki(punkt_start, punkt_koniec) VALUES (0, 2);
insert into odcinki(punkt_start, punkt_koniec) VALUES (2, 0);

insert into odcinki(punkt_start, punkt_koniec) VALUES (2, 3);
insert into odcinki(punkt_start, punkt_koniec) VALUES (3, 2);

insert into odcinki(punkt_start, punkt_koniec) VALUES (3, 12);
insert into odcinki(punkt_start, punkt_koniec) VALUES (12, 3);

insert into odcinki(punkt_start, punkt_koniec) VALUES (12, 13);
insert into odcinki(punkt_start, punkt_koniec) VALUES (13, 12);

insert into odcinki(punkt_start, punkt_koniec) VALUES (13, 5);
insert into odcinki(punkt_start, punkt_koniec) VALUES (5, 13);

insert into odcinki(punkt_start, punkt_koniec) VALUES (3, 4);
insert into odcinki(punkt_start, punkt_koniec) VALUES (4, 3);

insert into odcinki(punkt_start, punkt_koniec) VALUES (4, 5);
insert into odcinki(punkt_start, punkt_koniec) VALUES (5, 4);

insert into odcinki(punkt_start, punkt_koniec) VALUES (5, 11);
insert into odcinki(punkt_start, punkt_koniec) VALUES (11, 5);

insert into odcinki(punkt_start, punkt_koniec) VALUES (11, 10);
insert into odcinki(punkt_start, punkt_koniec) VALUES (10, 11);

insert into odcinki(punkt_start, punkt_koniec) VALUES (10, 9);
insert into odcinki(punkt_start, punkt_koniec) VALUES (9, 10);

insert into odcinki(punkt_start, punkt_koniec) VALUES (9, 8);
insert into odcinki(punkt_start, punkt_koniec) VALUES (8, 9);

insert into odcinki(punkt_start, punkt_koniec) VALUES (8, 14);
insert into odcinki(punkt_start, punkt_koniec) VALUES (14, 8);

insert into odcinki(punkt_start, punkt_koniec) VALUES (14, 6);
insert into odcinki(punkt_start, punkt_koniec) VALUES (6, 14);

insert into odcinki(punkt_start, punkt_koniec) VALUES (14, 0);
insert into odcinki(punkt_start, punkt_koniec) VALUES (0, 14);
