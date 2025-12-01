-- Insertar técnicos de ejemplo
INSERT INTO TECHNICIAN (id, name, specialty) VALUES (1, 'Ana García', 'Redes y Conectividad');
INSERT INTO TECHNICIAN (id, name, specialty) VALUES (2, 'Carlos Pérez', 'Hardware y Periféricos');
INSERT INTO TECHNICIAN (id, name, specialty) VALUES (3, 'Beatriz López', 'Software y Aplicaciones');

-- Insertar tickets de ejemplo
INSERT INTO TICKET (title, description, status, priority, opening_date, assigned_technician_id) VALUES ('Impresora no funciona', 'La impresora del departamento de finanzas no responde.', 'PENDING', 'HIGH', '2023-10-26', 2);
INSERT INTO TICKET (title, description, status, priority, opening_date, closing_date, assigned_technician_id) VALUES ('Error al iniciar sesión en CRM', 'Varios usuarios reportan un error 503 al intentar acceder al CRM.', 'CLOSED', 'HIGH', '2023-10-25', '2023-10-26', 3);
INSERT INTO TICKET (title, description, status, priority, opening_date, assigned_technician_id) VALUES ('Solicitud de nuevo ratón', 'El ratón de mi puesto está fallando.', 'IN_PROGRESS', 'LOW', '2023-10-26', 2);
INSERT INTO TICKET (title, description, status, priority, opening_date) VALUES ('Acceso lento a la red', 'La navegación por la red interna es muy lenta desde esta mañana.', 'PENDING', 'MEDIUM', '2023-10-26');
INSERT INTO TICKET (title, description, status, priority, opening_date, assigned_technician_id) VALUES ('PC no enciende', 'El ordenador de recepción no arranca.', 'IN_PROGRESS', 'HIGH', '2023-10-27', 1);
