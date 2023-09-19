DROP DATABASE IF EXISTS storage2;
CREATE DATABASE storage2;
USE storage2;


CREATE TABLE prodotto (	
  ID INT PRIMARY KEY AUTO_INCREMENT,
  specie char(20) not null,
  nome char(60) not null,
  descrizione char(200) not null,
  prezzo decimal(6,2) default 0,
  quantita int default 0,
  nome_immagine varchar(45) NOT NULL
);
CREATE TABLE Utente (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  Nome VARCHAR(60) NOT NULL,
  Cognome VARCHAR(60) NOT NULL,
  Email VARCHAR(60) NOT NULL,
  Password CHAR(40) NOT NULL,
  Telefono VARCHAR(20),
  Citta VARCHAR(60),
  Indirizzo VARCHAR(100),
  Data_Nascita DATE,
  Data_Registrazione DATE,
  admin VARCHAR(10)
);
CREATE TABLE Carrello (
  IDCarrello INT AUTO_INCREMENT PRIMARY KEY,
  IDUtente INT NOT NULL,
  IDProdotto INT NOT NULL,
  Quantita INT NOT NULL,
  FOREIGN KEY (IDUtente) REFERENCES Utente(ID),
  FOREIGN KEY (IDProdotto) REFERENCES Prodotto(ID)
);


DROP TABLE IF EXISTS `consegna`;
CREATE TABLE `consegna` (
  id_consegna int PRIMARY KEY AUTO_INCREMENT,
  via varchar(45) NOT NULL,
  cap int NOT NULL,
  numero int NOT NULL,
  citta varchar(45) NOT NULL,
  cod_utente int NOT NULL,
   FOREIGN KEY (cod_utente) REFERENCES utente (ID)
);

DROP TABLE IF EXISTS Ordine;

create table Ordine(
ID_Ordine int auto_increment primary key,
ID_Utente int references Utente(ID),
Totale double(10,2) not null,
ID_Prodotto int references Prodotto(ID),
Quantita int not null,
Prezzo double(10,2)
);



  DROP TABLE IF EXISTS Acquisto;

CREATE TABLE Acquisto (
  IDAcquisto INT AUTO_INCREMENT PRIMARY KEY,
  IDOrdine INT NOT NULL,
  IDprodotto INT NOT NULL,
  quantita INT DEFAULT 1 NOT NULL,
  immagine VARCHAR(400),
  prezzoAq FLOAT DEFAULT 0 NOT NULL,
  ivaAq INT DEFAULT 0 NOT NULL,
 
  FOREIGN KEY (IDOrdine) REFERENCES Ordine(ID_Ordine)
    ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (IDprodotto) REFERENCES prodotto(ID)
    ON UPDATE CASCADE ON DELETE NO ACTION
);


DROP TABLE IF EXISTS `metodo_pagamento`;
CREATE TABLE `metodo_pagamento` (
  `id_pagamento` int PRIMARY KEY AUTO_INCREMENT,
  `nominativo` varchar(80) NOT NULL,
  `CVV` int NOT NULL,
  `meseScadenza` varchar(10) NOT NULL,
  `codice_carta` varchar(16) NOT NULL,
  `annoScadenza` int NOT NULL,
  `e_utente` int NOT NULL,
  FOREIGN KEY (`e_utente`) REFERENCES `utente` (`ID`)
);

CREATE TABLE Recensione (
  IDRecensione INT AUTO_INCREMENT PRIMARY KEY,
  IDUtente INT NOT NULL,
  IDProdotto INT NOT NULL,
  TestoRecensione TEXT,
  Voto INT,
  FOREIGN KEY (IDUtente) REFERENCES utente(ID),
  FOREIGN KEY (IDProdotto) REFERENCES prodotto(ID)
);





use storage2;

INSERT INTO prodotto values (1,"Cani","Ciotolola Doppia","Ciotola doppia per cani dal design unico, per contenere alimenti e/o acqua.",10.99,5,"1.jpg");
INSERT INTO prodotto values (2,"Gatti","Ciotolola in Ceramica","Perfetta per cani e gatti, questa ciotola è la soluzione migliore per i pasti di tutti i giorni del tuo pet.",15.99,5,"2.jpg");
INSERT INTO prodotto values (3,"Cani","Virtus Dog Protein Selection","Alimento secco completo e bilanciato per cani adulti di qualsiasi razza e taglia ",25.99,13,"3.jpg");
INSERT INTO prodotto values (4,"Uccelli","Voliera in legno","Una raffinata e spaziosa voliera per canarini, cocorite e uccelli esotici.",270.30,5,"4.jpg");
INSERT INTO prodotto values (5,"Cani","Collare Outdoor Blu","Adatto ad un cane di piccola taglia",8.99,20,"5.jpg");
INSERT INTO prodotto values (6,"Cani","Shampoo per Cani dal Pelo Riccio","Ideato per cani col manto riccio, questo shampoo contribuisce a render eil pelo morbido e luminoso",15.99,50,"6.jpg");
INSERT INTO prodotto values (7,"Cani","Set Ossa di gomma","Gioco di gomma ",11.99,20,"7.jpg");
INSERT INTO prodotto values (8,"Gatti","Trasportino Lux Vision ","Adatta per lunghi viaggi ",74.99,22,"8.jpg");
INSERT INTO prodotto values (9,"Cani","Croccantini per cani", "Alimento completo per cani di taglia media", 20.99, 100,"9.jpg");
INSERT INTO prodotto values (10,"Gatti","Croccantini per gatti", "Alimento completo per gatti adulti", 15.99, 50,"10.jpg");
INSERT INTO prodotto values (11,"Pesci","Cibo per pesci rossi", "Alimento in fiocchi per pesci rossi", 5.49, 200,"11.jpg");
INSERT INTO prodotto values (12,"Cani","Letto per cani", "Letto imbottito per cani di piccola taglia", 35.99, 20,"12.jpg");
INSERT INTO prodotto values (13,"Gatti","Letto per gatti", "Letto in tessuto morbido per gatti", 25.99, 30,"13.jpg");
INSERT INTO prodotto values (14,"Gatti","Shampoo per Gatti alla Camomilla","Delicato shampoo alla camomilla per gatti dalla pelle sensibile",12.99,40,"14.jpg");
INSERT INTO prodotto values (15,"Uccelli","Accessori per Uccelli ","Set di accessori per una gabbia",8.99,15,"15.jpg");
INSERT INTO prodotto values (16,"Cani","Croccantini Grain-Free","Alimento secco per cani senza cereali, con proteine di alta qualità",29.99,50,"16.jpg");
INSERT INTO prodotto values (17,"Cani","Collare e Guinzaglio in Pelle","Elegante collare e guinzaglio in pelle per cani di taglia media-grande",45.99,10,"17.jpg");
INSERT INTO prodotto values (18,"Pesci","Cibo per Pesci Tropicali","Alimento in granuli per pesci tropicali, arricchito con vitamine e minerali",7.99,150,"18.jpg");
INSERT INTO prodotto values (19,"Gatti","Scratcher per Gatti a Forma di Gatto","Scratcher in cartone a forma di gatto, perfetto per la cura delle unghie dei felini",18.99,25,"19.jpg");
INSERT INTO prodotto values (20,"Cani","Pettorina per Cani","Comoda e resistente pettorina per cani di taglia media",22.99,30,"20.jpg");
INSERT INTO prodotto values (21,"Gatti","Shampoo per gatti al profumo di fiori","Shampoo delicato per il manto dei gatti, arricchito con estratti di fiori per un profumo fresco e duraturo",12.99,30,"21.jpg");
INSERT INTO prodotto values (22,"Gatti","Set di palline colorate","Set di 4 palline colorate per il divertimento dei gatti, adatte per giocare in casa o all'aperto",6.99,50,"22.jpg");
INSERT INTO prodotto values (23,"Uccelli","Semi misti per uccelli esotici","Mix di semi ideale per la dieta degli uccelli esotici, confezionato con ingredienti di alta qualità",8.49,150,"23.jpg");
INSERT INTO prodotto values (24,"Pesci","Termoriscaldatore per acquario","Termoriscaldatore adatto per acquari di piccola e media taglia, per garantire una temperatura costante dell'acqua",19.99,15,"24.jpg");
INSERT INTO prodotto values (25,"Uccelli","Shampoo azzurro per pappagalli","Shampoo naturale all'essenza di mirtilli",7.99,40,"25.jpg");
INSERT INTO prodotto values (26,"Pesci","Pietre decorative per acquario","Set di 5 pietre decorative per creare un ambiente naturale e piacevole nell'acquario",12.99,30,"26.jpg");
INSERT INTO prodotto values (27,"Pesci","Cibo per pesci tropicale","Alimento in granuli per pesci tropicali, confezionato con ingredienti di alta qualità per una dieta sana ed equilibrata",6.99,100,"27.jpg");
INSERT INTO prodotto values (28,"Cani","Rare felpa per cani","felpa colorata per canid di tutte le taglie",6.99,50,"28.jpg");
INSERT INTO prodotto values (29,"Cani","Palla in gomma","Palla di gomma per far divertire il tuo cane ",8.49,150,"29.jpg");
INSERT INTO prodotto values (30,"Cani","Snack per Cane ","Snack per Cane Anatra e Zucchine Happy Farm",19.99,15,"30.jpg");
INSERT INTO prodotto values (31,"Uccelli","Alba Gabbia per Canarini","Gabbia gialla per canarini",7.99,40,"31.jpg");
INSERT INTO prodotto values (32,"Gatti","Tiragraffi per gatti ricambio – Azalea","Tiragraffi",12.99,30,"32.jpg");
INSERT INTO prodotto values (33,"Cani","Ciotola per cani","Ciotola rosa per il tuo cucciolo",6.99,100,"33.jpg");


INSERT INTO Utente (Nome, Cognome, Email, Password, Telefono, Citta, Indirizzo, Data_Nascita, Data_Registrazione,  admin)
VALUES ('Roksana', 'Duda', 'roksid09@gmail.com', 'password', '1234567890', 'Milano', 'Via Roma 1', '1990-01-15', '2023-08-30', '1');

INSERT INTO Utente (Nome, Cognome, Email, Password, Telefono, Citta,  Indirizzo, Data_Nascita, Data_Registrazione,  admin)
VALUES ('Anna', 'Bianchi', 'anna@example.com', 'sha1password2', '9876543210', 'Roma',  'Via Nazionale 10', '1985-05-20', '2023-08-30','0');

INSERT INTO Utente (Nome, Cognome, Email, Password, Telefono, Citta, Indirizzo, Data_Nascita, Data_Registrazione,  admin)
VALUES ('Carlo', 'Verdi', 'carlo@example.com', 'sha1password3', '5553334444', 'Torino',  'Corso Vittorio Emanuele 50', '1998-09-10', '2023-08-30',  '0');

INSERT INTO Utente (Nome, Cognome, Email, Password, Telefono, Citta,  Indirizzo, Data_Nascita, Data_Registrazione,  admin)
VALUES ('Elena', 'Gialli', 'elena@example.com', 'sha1password4', '1112223333', 'Napoli',  'Via Toledo 20', '1995-07-02', '2023-08-30',  '0');


INSERT INTO consegna ( via, cap, numero, citta, cod_utente)
VALUES ( 'Via Roma 123', 00100, 5, 'Roma', 1);
INSERT INTO consegna ( via, cap, numero, citta, cod_utente)
VALUES ( 'Via Romaconsegna 123', 00100, 5, 'Roma', 1);

INSERT INTO metodo_pagamento (id_pagamento, nominativo, CVV, meseScadenza, codice_carta, annoScadenza, e_utente)
VALUES (1, 'Mario Rossi', 123, 12, '1234567890123456', 2025, 1);
INSERT INTO metodo_pagamento (id_pagamento, nominativo, CVV, meseScadenza, codice_carta, annoScadenza, e_utente)
VALUES (2, 'Mario Rossi', 123, 12, '1234567890122456', 2026, 4);



-- Inserimento nella tabella consegna
INSERT INTO consegna (via, cap, numero, citta, cod_utente)
VALUES ('Via Spedizione 123', 12345, 10, 'Città Spedizione', 1);

-- Inserimento nella tabella metodo_pagamento
INSERT INTO metodo_pagamento (nominativo, CVV, meseScadenza, codice_carta, annoScadenza, e_utente)
VALUES ('Nome Cognome', 123, '12/25', '1234567890123456', 2025, 1);

