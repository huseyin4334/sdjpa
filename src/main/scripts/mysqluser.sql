DROP DATABASE IF EXISTS applicationdb;
DROP USER IF EXISTS `paymentadmin`@`%`;
DROP USER IF EXISTS `paymentuser`@`%`;
CREATE DATABASE IF NOT EXISTS applicationdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `paymentadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `applicationdb`.* TO `paymentadmin`@`%`;
CREATE USER IF NOT EXISTS `paymentuser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `applicationdb`.* TO `paymentuser`@`%`;
FLUSH PRIVILEGES;