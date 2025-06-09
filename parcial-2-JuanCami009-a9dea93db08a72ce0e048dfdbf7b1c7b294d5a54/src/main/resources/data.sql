INSERT INTO users (username, password, email, first_name, last_name, phone_number, address, is_active)
VALUES ('jdoe', '$2a$10$Xyfe1NDvBXTIgUn5na7TruVM7NqU3okrr4zfGh3AU5M56ljW/85iK', 'jdoe@example.com', 'John', 'Doe', '1234567890', '123 Main St', true);

INSERT INTO users (username, password, email, first_name, last_name, phone_number, address, is_active)
VALUES ('asmith', '$2a$10$Xyfe1NDvBXTIgUn5na7TruVM7NqU3okrr4zfGh3AU5M56ljW/85iK', 'asmith@example.com', 'Alice', 'Smith', '9876543210', '456 Oak Ave', false);


INSERT INTO permission (name, description) VALUES ('CREATE_VEHICLE', 'Permite crear una nueva vehiculo');
INSERT INTO permission (name, description) VALUES ('UPDATE_VEHICLE', 'Permite actualizar una vehiculo existente');
INSERT INTO permission (name, description) VALUES ('DELETE_VEHICLE', 'Permite eliminar una vehiculo');
INSERT INTO permission (name, description) VALUES ('VIEW_VEHICLE', 'Permite ver los detalles de una vehiculo');

INSERT INTO role (name, description) VALUES ('ADMIN', 'Administrador con acceso total');
INSERT INTO role (name, description) VALUES ('USER', 'Usuario básico que gestiona sus vehiculos');


INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 3);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 4);

INSERT INTO role_permission (role_id, permission_id) VALUES (2, 4);

INSERT INTO user_role (role_id, user_id) VALUES (1, 1);
INSERT INTO user_role (role_id, user_id) VALUES (2, 2);

INSERT INTO vehicle (license_plate, model, make, color, year_release, owner) VALUES
('ABC123', 'Civic', 'Honda', 'Red', 2018, 1),
('XYZ789', 'Corolla', 'Toyota', 'Blue', 2019, 2),
('LMN456', 'Model 3', 'Tesla', 'White', 2021, 1),
('JKL321', 'Mustang', 'Ford', 'Black', 2020, 2),
('DEF654', 'CX-5', 'Mazda', 'Silver', 2017, 1),
('GHI987', 'Impreza', 'Subaru', 'Grey', 2022, 2),
('QWE852', 'Altima', 'Nissan', 'Blue', 2016, 1),
('RTY159', 'A4', 'Audi', 'White', 2023, 2),
('UIO753', 'Golf', 'Volkswagen', 'Green', 2015, 1),
('POI258', 'Elantra', 'Hyundai', 'Orange', 2020, 2);
