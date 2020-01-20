insert into turysci (idT, login, email, password) VALUES (1, 'adam123',  'adamos@gmail.com', 'haslohaslo');
insert into turysci (idT, login, email, password) VALUES (2, 'hanna_b',  'hannas@gmail.com', 'h2h2h2');
insert into turysci (idT, login, email, password) VALUES (3, 'jacek_placek',  'placuch@gmail.com', 'nalesnik');
insert into turysci (idT, login, email, password) VALUES (4, 'admin',  'admin@gmail.com', 'admin');

insert into punkty (idP, nazwa, lat, lon) VALUES (0, 'Schronisko PTTK Murowaniec', 49.243341, 20.006962);
insert into punkty (idP, nazwa, lat, lon) VALUES (1, 'Czarny Staw Gąsienicowy', 49.232714, 20.014627);
insert into punkty (idP, nazwa, lat, lon) VALUES (2, 'Hala Gąsienicowa', 49.241453, 20.001769);
insert into punkty (idP, nazwa, lat, lon) VALUES (3, 'Kolejka krzesełkowa Gąsienicowa, stacja dolna', 49.237093, 19.996598);
insert into punkty (idP, nazwa, lat, lon) VALUES (4, 'Zielony Staw Gąsienicowy', 49.227816, 20.002394);
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

insert into odcinki(punkt1, punkt2) VALUES (0, 2);
insert into odcinki(punkt1, punkt2) VALUES (2, 3);
insert into odcinki(punkt1, punkt2) VALUES (3, 12);
insert into odcinki(punkt1, punkt2) VALUES (12, 13);
insert into odcinki(punkt1, punkt2) VALUES (13, 5);
insert into odcinki(punkt1, punkt2) VALUES (3, 4);
insert into odcinki(punkt1, punkt2) VALUES (4, 5);
insert into odcinki(punkt1, punkt2) VALUES (5, 11);
insert into odcinki(punkt1, punkt2) VALUES (11, 10);
insert into odcinki(punkt1, punkt2) VALUES (10, 9);
insert into odcinki(punkt1, punkt2) VALUES (9, 8);
insert into odcinki(punkt1, punkt2) VALUES (8, 14);
insert into odcinki(punkt1, punkt2) VALUES (14, 6);


INSERT INTO wycieczki(IDT , CATEGORY , DISTANCE , END_DATE , MOUNTAIN , NAME  , SCORE , START_DATE , STATUS , TRACK  ) VALUES (0,'Dla początkujących',5.6, '2019-10-20 14:46:00', NULL, 'Śnieżka - 20.10.2019', 10,  '2019-10-20 12:20:00',FALSE, 'trasa bla bla bla');

--insert into komentarzy(idC, grade, text) VALUES (0, ,"Bardzo ładne widoki!");
--insert into komentarzy(idC, grade, text) VALUES (1, ,"Moja pierwsza wycieczka. Polecam początkującym.");
