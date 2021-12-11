INSERT INTO Managers (name, password, email) VALUES ("Johnny Does", "pass123", "jd@email.com");
INSERT INTO Managers (name, password, email) VALUES ("Tommy Tries", "pass12", "tt@email.com");
INSERT INTO Managers (name, password, email) VALUES ("Stayin Coolio", "pass1", "sc@email.com");

INSERT INTO Landlords (name, password, email) VALUES ("Chris Ferris", "pass987", "cf@landmail.com");
INSERT INTO Landlords (name, password, email) VALUES ("Anind Lorry", "pass98", "al@landmail.com");
INSERT INTO Landlords (name, password, email) VALUES ("Newla Loris", "pass9", "nl@landmail.com");
INSERT INTO Landlords (name, password, email) VALUES ("Jeff Ronald", "pass000", "jr@landmail.com");

INSERT INTO Renters (name, password, email, notify) VALUES ("Kool Erday", "gothru1", "em@ail.com", "NO");
INSERT INTO Renters (name, password, email, notify) VALUES ("Anew Ren", "gothru12", "ter@email.com", "NO");
INSERT INTO Renters (name, password, email, notify) VALUES ("Som Nam", "gothru123", "ee@mail.com", "NO");
INSERT INTO Renters (name, password, email, notify) VALUES ("Demetri Nowins", "gothru000", "dmn@mail.com", "NO");

INSERT INTO Properties (address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES ('56 Triangle st', 'Condo', 2, 3, 'Furnished', 'NE', 650.00, 'Suspended', 1513);
INSERT INTO Properties (address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES ('71 Covengrass pl', 'Duplex', 3, 4, 'Unfurnished', 'SW', 950.00, 'Rented', 1513);
INSERT INTO Properties (address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES ('49 Plasdale cir', 'Apartment', 2, 2, 'Furnished', 'NW', 760.00, 'Cancelled', 1514);
INSERT INTO Properties (address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES ('88 Greencount plaza', 'Apartment', 1, 2, 'Furnished', 'NE', 590.00, 'Active', 1512);
INSERT INTO Properties (address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES ('22 Elonia rd', 'Townhouse', 3, 3, 'Furnished', 'NW', 675.00, 'Active', 1512);
INSERT INTO Properties (address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES ('15 Cloverleap dr', 'Bungalow', 3, 5, 'Unfurnished', 'SW', 980.00, 'Active', 1512);
INSERT INTO Properties (address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES ('96 Redder blvd', 'Apartment', 1, 1, 'Unfurnished', 'SE', 1100.00, 'Suspended', 1510);
INSERT INTO Properties (address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord)
VALUES ('30 Viale grove', 'Duplex', 4, 4, 'Unfurnished', 'SW', 800.00, 'Active', 1510);