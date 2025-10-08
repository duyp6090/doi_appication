-- 1. Insert into tbl_user data
INSERT INTO tbl_user (user_name, email, phone, password, birth_date, status_user)
VALUES 
('alice', 'alice@example.com', '0901234567', '$2a$12$OEPa0SB283aEybkXpEKy9OGnBfryqKU8N.p7ZLa/vZNFWhKyOrQ2i', '1998-05-12', 'ACTIVE'),
('bob', 'bob@example.com', '0902234567', '$2a$12$OEPa0SB283aEybkXpEKy9OGnBfryqKU8N.p7ZLa/vZNFWhKyOrQ2i', '1995-09-20', 'ACTIVE'),
('carol', 'carol@example.com', '0903234567', '$2a$12$OEPa0SB283aEybkXpEKy9OGnBfryqKU8N.p7ZLa/vZNFWhKyOrQ2i', '2000-02-18', 'INACTIVE'),
('david', 'david@example.com', '0904234567', '$2a$12$OEPa0SB283aEybkXpEKy9OGnBfryqKU8N.p7ZLa/vZNFWhKyOrQ2i', '1992-11-01', 'ACTIVE');

-- 2. Insert into tbl_role data
INSERT INTO tbl_role (role_name)
VALUES 
('ADMIN'),
('OWNER'),
('CUSTOMER');

-- Insert tbl_permission data
INSERT INTO tbl_permission (name)
VALUES 
  ('read'),
  ('create'),
  ('update'),
  ('delete'),
  ('manage');

-- Insert tbl_group data
INSERT INTO tbl_group (created_by, updated_by, name, description, role_id)
VALUES
  (1, 1, 'Admins Group', 'Group for administrators', 1),
  (1, 1, 'Users Group', 'Group for regular users', 2),
  (1, 1, 'Owners Group', 'Group for owners', 3);


-- Insert tbl_role_has_permission data
INSERT INTO tbl_role_has_permission (created_by, updated_by, role_id, permission_id)
VALUES
  (1, 1, 1, 1), -- ADMIN có READ
  (1, 1, 1, 2), -- ADMIN có CREATE
  (1, 1, 1, 3), -- ADMIN có UPDATE
  (1, 1, 1, 4), -- ADMIN có DELETE
  (1, 1, 1, 5), -- ADMIN có MANAGE
  (1, 1, 2, 1), -- CUSTOMER có READ
  (1, 1, 2, 2), -- CUSTOMER có CREATE
  (1, 1, 2, 3), -- CUSTOMER có UPDATE
  (1, 1, 3, 1), -- OWNER có READ
  (1, 1, 3, 2), -- OWNER có CREATE
  (1, 1, 3, 3), -- OWNER có UPDATE
  (1, 1, 3, 4); -- OWNER có DELETE

  -- Insert tbl_user_has_group data
INSERT INTO tbl_user_has_group (created_by, updated_by, user_id, group_id)
VALUES
  (1, 1, 1, 1),
  (1, 1, 2, 2),
  (1, 1, 3, 3),
  (1, 1, 4, 2);

-- Insert tbl_user_has_role data
INSERT INTO tbl_user_has_role (created_by, updated_by, user_id, role_id)
VALUES
  (1, 1, 1, 1),
  (1, 1, 2, 2),
  (1, 1, 3, 3),
  (1, 1, 4, 2);

-- Insert tbl_locations data
INSERT INTO tbl_locations (name, province, ward, longitude, latitude)
VALUES 
('TP HCM - Q1', 'TP HCM', 'Bến Nghé', 106.7009, 10.7769),
('Hà Nội - Hoàn Kiếm', 'Hà Nội', 'Hàng Trống', 105.8522, 21.0285),
('Đà Nẵng - Hải Châu', 'Đà Nẵng', 'Thạch Thang', 108.2208, 16.0678);

-- Insert tbl_cars data
INSERT INTO tbl_cars (owner_id, brand, model, year, license_plate, price_per_hour, location_id)
VALUES 
(2, 'Toyota', 'Vios', 2020, '51A-12345', 150000, 1),
(2, 'Honda', 'Civic', 2021, '51B-54321', 200000, 1),
(4, 'Ford', 'Focus', 2019, '30A-67890', 180000, 2);

-- Insert tbl_bookings data
INSERT INTO tbl_bookings (customer_id, car_id, start_time, end_time, total_price, status)
VALUES 
(3, 1, '2025-10-01 08:00:00', '2025-10-01 12:00:00', 600000, 'COMPLETED'),
(4, 2, '2025-10-02 09:00:00', '2025-10-02 13:00:00', 800000, 'PENDING');

-- Insert tbl_reviews data
INSERT INTO tbl_reviews (booking_id, rating, comment)
VALUES 
(1, 5, 'Xe sạch, chủ xe thân thiện!'),
(2, 4, 'Ổn, nhưng hơi trễ giờ nhận xe.');