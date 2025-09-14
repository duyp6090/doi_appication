-- Insert tbl_role data
INSERT INTO tbl_role (created_by, updated_by, role_name)
VALUES 
  (1, 1, 'ADMIN'),
  (1, 1, 'USER'),
  (1, 1, 'MODERATOR');

-- Insert tbl_permission data
INSERT INTO tbl_permission (created_by, updated_by, name)
VALUES 
  (1, 1, 'read'),
  (1, 1, 'create'),
  (1, 1, 'update'),
  (1, 1, 'delete');

-- Insert tbl_group data
INSERT INTO tbl_group (created_by, updated_by, name, description, role_id)
VALUES
  (1, 1, 'Admins Group', 'Group for administrators', 1),
  (1, 1, 'Users Group', 'Group for regular users', 2),
  (1, 1, 'Moderators Group', 'Group for moderators', 3);

-- Insert tbl_user data
INSERT INTO tbl_user (created_by, updated_by, user_name, password, birth_date, email, type_user, status_user)
VALUES
  (1, 1, 'admin1', '$2a$12$OEPa0SB283aEybkXpEKy9OGnBfryqKU8N.p7ZLa/vZNFWhKyOrQ2i', '1980-01-01', 'admin1@example.com', 'ADMIN', 'ACTIVE'),
  (1, 1, 'user1', '$2a$12$nepRBzAZdwGtSTAunCJcBeXaByQ1rkNXLc6uA8hFs03osXNMJpKGK', '1990-05-10', 'user1@example.com', 'CUSTOMER', 'ACTIVE'),
  (1, 1, 'mod1', '$2a$12$YYejdIrTgRSsCMejI6H.Ru/7KObh/wErnN8vCYQztX3HECTsAm13O', '1985-03-15', 'mod1@example.com', 'MODERATOR', 'ACTIVE');

-- Insert tbl_role_has_permission data
INSERT INTO tbl_role_has_permission (created_by, updated_by, role_id, permission_id)
VALUES
  (1, 1, 1, 1), -- ADMIN có READ
  (1, 1, 1, 2), -- ADMIN có CREATE
  (1, 1, 1, 3), -- ADMIN có UPDATE
  (1, 1, 1, 4), -- ADMIN có DELETE
  (1, 1, 2, 1), -- USER có READ
  (1, 1, 3, 1), -- MODERATOR có READ
  (1, 1, 3, 2); -- MODERATOR có CREATE

  -- Insert tbl_user_has_group data
INSERT INTO tbl_user_has_group (created_by, updated_by, user_id, group_id)
VALUES
  (1, 1, 1, 1),
  (1, 1, 2, 2),
  (1, 1, 3, 3);

-- Insert tbl_user_has_role data
INSERT INTO tbl_user_has_role (created_by, updated_by, user_id, role_id)
VALUES
  (1, 1, 1, 1),
  (1, 1, 2, 2),
  (1, 1, 3, 3);
