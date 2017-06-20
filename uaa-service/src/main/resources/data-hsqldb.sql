INSERT INTO user (email, username, password, enabled) VALUES
--- g00glen00b:password
  ('john.doe@example.org', 'g00glen00b', '$2a$10$Fg.pwGKNEk8TtRq3C86DEeIo6CnUI05umcVQuvRh2DdwEKJUPtsJK', 1);

INSERT INTO role (email, role) VALUES
  ('john.doe@example.org', 'admin'),
  ('john.doe@example.org', 'user');