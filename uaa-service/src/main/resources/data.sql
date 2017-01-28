INSERT INTO user (username, password, enabled) VALUES
  ('admin', '$2a$10$OEJyRXkpYvVI/bw98JMUy.A600z1vh.NVEBjOxkFDfFK2XlfQ5sHe', 1);

INSERT INTO role (username, role) VALUES
  ('admin', 'admin'),
  ('admin', 'user');