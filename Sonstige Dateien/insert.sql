INSERT INTO produkttyp (bezeichnung, kurzbezeichnung) VALUES ('Alkoholische Getränke', 'Alk')

INSERT INTO produkttyp (bezeichnung, kurzbezeichnung) VALUES ('Anti-Alkoholische Getränke', 'AAlk')

INSERT INTO produkttyp (bezeichnung, kurzbezeichnung) VALUES ('Suppen', 'Sup')

INSERT INTO produkttyp (bezeichnung, kurzbezeichnung) VALUES ('Jause', 'Jau')

INSERT INTO produkttyp (bezeichnung, kurzbezeichnung) VALUES ('Wirtshausklassiker', 'Klass')

INSERT INTO produkt (name, typid, preis) VALUES ('Superbier', 1, 300)

INSERT INTO produkt (name, typid, preis) VALUES ('Megabier', 1, 400)

INSERT INTO produkt (name, typid, preis) VALUES ('Supermegabier', 1, 500)

INSERT INTO produkt (name, typid, preis) VALUES ('Wasser', 2, 100)

INSERT INTO produkt (name, typid, preis) VALUES ('Cola', 2, 200)

INSERT INTO produkt (name, typid, preis) VALUES ('Fanta', 2, 200)

INSERT INTO produkt (name, typid, preis) VALUES ('Frittatensuppe', 3, 300)

INSERT INTO produkt (name, typid, preis) VALUES ('Leberknödelsuppe', 3, 350)

INSERT INTO produkt (name, typid, preis) VALUES ('Nudelsuppe', 3, 300)

INSERT INTO produkt (name, typid, preis) VALUES ('Frankfurter', 4, 400)

INSERT INTO produkt (name, typid, preis) VALUES ('Berner', 4, 450)

INSERT INTO produkt (name, typid, preis) VALUES ('Wurstplatte', 4, 350)

INSERT INTO produkt (name, typid, preis) VALUES ('Wiener Schnitzel', 5, 900)

INSERT INTO produkt (name, typid, preis) VALUES ('Schweinsbraten', 5, 1000)

INSERT INTO produkt (name, typid, preis) VALUES ('Cordon Bleu', 5, 1100)

INSERT INTO ware (bezeichnung, lagerstand) VALUES ('Extrawurst', 50)

INSERT INTO ware (bezeichnung, lagerstand) VALUES ('Käsewurst', 43)

INSERT INTO ware (bezeichnung, lagerstand) VALUES ('Salami', 72)

INSERT INTO bestellung (tischnummer, produktid, preis, rechnungid, status) VALUES (1, 3, 500, 1, 'offen')

INSERT INTO bestellung (tischnummer, produktid, preis, rechnungid, status) VALUES (1, 5, 200, 1, 'offen')

INSERT INTO bestellung (tischnummer, produktid, preis, rechnungid, status) VALUES (1, 13, 900, 1, 'offen')

INSERT INTO bestellung (tischnummer, produktid, preis, rechnungid, status) VALUES (12, 7, 300, 2, 'erledigt')

INSERT INTO bestellung (tischnummer, produktid, preis, rechnungid, status) VALUES (5, 6, 200, 3, 'offen')

INSERT INTO bestellung (tischnummer, produktid, preis, rechnungid, status) VALUES (5, 10, 400, 4, 'offen')