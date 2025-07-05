INSERT INTO optica.parametros (id_parametro,id_padre,nombre,valor) VALUES
	 (1,NULL,'Tipo de contacto',NULL),
	 (2,1,'Teléfono fijo','FIJ'),
	 (3,1,'Celular','CEL'),
	 (4,1,'e-mail','EMAILL'),
	 (5,NULL,'Género',NULL),
	 (6,5,'Maculino','M'),
	 (7,5,'Femenino','F'),
	 (8,NULL,'Tipo documento',NULL),
	 (9,8,'Cédula','CC'),
	 (10,8,'Pasaporte','PAS');
INSERT INTO optica.parametros (id_parametro,id_padre,nombre,valor) VALUES
	 (11,8,'Visa','VISA'),
	 (12,NULL,'Estado civil',NULL),
	 (13,12,'Soltero','S'),
	 (14,12,'Casado','C'),
	 (15,12,'Divorciado','D'),
	 (16,12,'Viudo','V');
