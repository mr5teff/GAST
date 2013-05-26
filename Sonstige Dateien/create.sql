drop table guv IF EXISTS;
drop table einkauf IF EXISTS;
drop table rezept IF EXISTS;
drop table ware IF EXISTS;
drop table reservierung IF EXISTS;
drop table bestellung IF EXISTS;
drop table rechnung IF EXISTS;
drop table produkt IF EXISTS;
drop table produkttyp IF EXISTS;
drop table tisch IF EXISTS;

create table produkttyp (
id integer generated by default as identity (start with 0) PRIMARY KEY,
bezeichnung varchar(100),
kurzBezeichnung varchar(20)
deleted boolean default false
);

create table produkt (
id integer generated by default as identity (start with 0) primary key,
name varchar(100),
typid integer references produkttyp(id),
preis decimal(6,2),
deleted boolean default false
);

create table rechnung (
id integer generated by default as identity (start with 1) primary key,
datum DATE,
trinkgeld decimal(6,2)
);

create table bestellung (
id integer generated by default as identity (start with 0) primary key,
tischnummer integer,
produktid integer references produkt(id),
produktname varchar(100),
preis decimal(6,2),
rechnungid integer,
status varchar(30) check (status in ('bestellt','wirdGekocht','fertigGekocht','geliefert','bezahlt')),
deleted boolean,
bestelldatum datetime,
bestelldatumLong bigint,
bearbeitungszeit integer
);

create table reservierung (
id integer generated by default as identity (start with 0) primary key,
datum DATE,
dauer integer,
personen integer,
tischnummer integer,
name varchar(50),
telefonnummer varchar(50)
);

create table ware (
id integer generated by default as identity (start with 0) primary key,
bezeichnung varchar(100),
einheit varchar(20) check (einheit in ('gramm','milliliter','prozent')),
lagerstand integer
);

create table rezept (
produktid integer references produkt(id),
warenid integer references ware(id),
primary key (produktid , warenid)
);

create table einkauf (
id integer generated by default as identity (start with 0) primary key,
warenid integer references ware(id),
menge integer,
datum DATE,
preis decimal(6,2)
);

create table guv (
einkaufid integer references einkauf(id),
bestellungid integer references bestellung(id),
datum DATE,
betrag decimal(10,2)
);

create table tisch (
id integer generated by default as identity (start with 0) primary key,
nummer integer,
plaetze integer,
beschreibung varchar(100),
art varchar(100) check(art in ('Raucher','Nichtraucher')),
deleted boolean default false
);
