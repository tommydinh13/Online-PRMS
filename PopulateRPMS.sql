USE RPMSdb;

INSERT INTO Managers (mID, name, password, email) VALUES (1050, "Johnny Does", "pass123", "jd@email.com");
INSERT INTO Managers (mID, name, password, email) VALUES (1051, "Tommy Tries", "pass12", "tt@email.com");
INSERT INTO Managers (mID, name, password, email) VALUES (1052, "Stayin Coolio", "pass1", "sc@email.com");

INSERT INTO Landlords (lID, name, password, email) VALUES (1550, "Chris Ferris", "pass987", "cf@landmail.com");
INSERT INTO Landlords (lID, name, password, email) VALUES (1551, "Anind Lorry", "pass98", "al@landmail.com");
INSERT INTO Landlords (lID, name, password, email) VALUES (1552, "Newla Loris", "pass9", "nl@landmail.com");
INSERT INTO Landlords (lID, name, password, email) VALUES (1553, "Jeff Ronald", "pass000", "jr@landmail.com");

INSERT INTO Renters (rID, name, password, email, notify) VALUES (550, "Kool Erday", "gothru1", "em@ail.com", "NO");
INSERT INTO Renters (rID, name, password, email, notify) VALUES (551, "Anew Ren", "gothru12", "ter@email.com", "NO");
INSERT INTO Renters (rID, name, password, email, notify) VALUES (552, "Som Nam", "gothru123", "ee@mail.com", "NO");
INSERT INTO Renters (rID, name, password, email, notify) VALUES (553, "Demetri Nowins", "gothru000", "dmn@mail.com", "NO");

INSERT INTO Properties (pID, address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES (100, '56 Triangle st', 'Condo', 2, 3, 'Furnished', 'NE', 650.00, 'Suspended', 1550);
INSERT INTO Properties (pID, address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES (101, '71 Covengrass pl', 'Duplex', 3, 4, 'Unfurnished', 'SW', 950.00, 'Rented', 1550);
INSERT INTO Properties (pID, address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES (102, '49 Plasdale cir', 'Apartment', 2, 2, 'Furnished', 'NW', 760.00, 'Cancelled', 1550);
INSERT INTO Properties (pID, address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES (103, '88 Greencount plaza', 'Apartment', 1, 2, 'Furnished', 'NE', 590.00, 'Active', 1551);
INSERT INTO Properties (pID, address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES (104, '22 Elonia rd', 'Townhouse', 3, 3, 'Furnished', 'NW', 675.00, 'Active', 1551);
INSERT INTO Properties (pID, address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES (105, '15 Cloverleap dr', 'Bungalow', 3, 5, 'Unfurnished', 'SW', 980.00, 'Active', 1552);
INSERT INTO Properties (pID, address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES (106, '96 Redder blvd', 'Apartment', 1, 1, 'Unfurnished', 'SE', 1100.00, 'Suspended', 1551);
INSERT INTO Properties (pID, address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES (107, '30 Viale grove', 'Duplex', 4, 4, 'Unfurnished', 'SW', 800.00, 'Active', 1552);