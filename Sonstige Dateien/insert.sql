INSERT INTO produkttyp (bezeichnung, kurzbezeichnung) VALUES ('Alkoholische Getränke', 'Alk')

INSERT INTO produkttyp (bezeichnung, kurzbezeichnung) VALUES ('Anti-Alkoholische Getränke', 'AAlk')

INSERT INTO produkttyp (bezeichnung, kurzbezeichnung) VALUES ('Suppen', 'Sup')

INSERT INTO produkttyp (bezeichnung, kurzbezeichnung) VALUES ('Jause', 'Jau')

INSERT INTO produkttyp (bezeichnung, kurzbezeichnung) VALUES ('Wirtshausklassiker', 'Klass')

INSERT INTO produkt (name, typid, preis) VALUES ('Superbier', 0, 300)

INSERT INTO produkt (name, typid, preis) VALUES ('Megabier', 0, 400)

INSERT INTO produkt (name, typid, preis) VALUES ('Supermegabier', 0, 500)

INSERT INTO produkt (name, typid, preis) VALUES ('Wasser', 1, 100)

INSERT INTO produkt (name, typid, preis) VALUES ('Cola', 1, 200)

INSERT INTO produkt (name, typid, preis) VALUES ('Fanta', 1, 200)

INSERT INTO produkt (name, typid, preis) VALUES ('Frittatensuppe', 2, 300)

INSERT INTO produkt (name, typid, preis) VALUES ('Leberknödelsuppe', 2, 350)

INSERT INTO produkt (name, typid, preis) VALUES ('Nudelsuppe', 2, 300)

INSERT INTO produkt (name, typid, preis) VALUES ('Frankfurter', 3, 400)

INSERT INTO produkt (name, typid, preis) VALUES ('Berner', 3, 450)

INSERT INTO produkt (name, typid, preis) VALUES ('Wurstplatte', 3, 350)

INSERT INTO produkt (name, typid, preis) VALUES ('Wiener Schnitzel', 4, 900)

INSERT INTO produkt (name, typid, preis) VALUES ('Schweinsbraten', 4, 1000)

INSERT INTO produkt (name, typid, preis) VALUES ('Cordon Bleu', 4, 1100)

INSERT INTO ware (bezeichnung, lagerstand) VALUES ('Extrawurst', 50)

INSERT INTO ware (bezeichnung, lagerstand) VALUES ('Käsewurst', 43)

INSERT INTO ware (bezeichnung, lagerstand) VALUES ('Salami', 72)

INSERT INTO rechnung (datum, trinkgeld) VALUES (NOW(), 70)

INSERT INTO rechnung (datum, trinkgeld) VALUES (NOW(), 30)

INSERT INTO rechnung (datum, trinkgeld) VALUES (NOW(), 20)

INSERT INTO rechnung (datum, trinkgeld) VALUES (NOW(), 0)

INSERT INTO bestellung (tischnummer, produktid, preis, rechnungid, status) VALUES (1, 2, 500, 1, 'bestellt')

INSERT INTO bestellung (tischnummer, produktid, preis, rechnungid, status) VALUES (1, 4, 200, 1, 'bestellt')

INSERT INTO bestellung (tischnummer, produktid, preis, rechnungid, status) VALUES (1, 12, 900, 1, 'bestellt')

INSERT INTO bestellung (tischnummer, produktid, preis, rechnungid, status) VALUES (12, 6, 300, 2, 'geliefert')

INSERT INTO bestellung (tischnummer, produktid, preis, rechnungid, status) VALUES (5, 5, 200, 3, 'bestellt')

INSERT INTO bestellung (tischnummer, produktid, preis, rechnungid, status) VALUES (5, 9, 400, 4, 'bestellt')