#!/bin/sh

mysql -u root -proot_password -e "DROP DATABASE IF EXISTS atlocaldb"
mysql -u root -proot_password -e "CREATE DATABASE atlocaldb DEFAULT CHARACTER SET = utf8 DEFAULT COLLATE utf8_general_ci"
mysql -u root -proot_password -e "GRANT ALL ON atlocaldb.* TO 'atlocaldbuser'@'%' IDENTIFIED BY 'atpassword'"