#!/bin/bash

update_system() {
    ## Update the packaging system.
    apt-get -y update
}

check_pkg_installed() {
    PACKAGE=$1

    VAL=$(dpkg-query -W -f-'${Status} ${Version}\n' ${PACKAGE} | awk -F" " '{print $3}')

    if [ "${VAL}" == "installed" ]
    then
      return 0
    else
      return 1
    fi
}

apt_install() {
    PACKAGE=$1

    if check_pkg_installed ${PACKAGE}; then
      echo "Package ${PACKAGE} is already installed..."
    else
      echo "Installing package ${PACKAGE}..."
      sudo apt-get install -y -q ${PACKAGE}
      echo "Finished installing package ${PACKAGE}."
    fi
}

mysql_configure_root_password() {
    debconf-set-selections <<< 'mysql-server mysql-server/root_password password root_password'
    debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password root_password'
}

mysql_configure() {
    # Make sure this is last by calling it zz.
    cat << EOF > /etc/mysql/mysql.conf.d/zz-local-dev.cnf
[mysqld]
bind-address = 0.0.0.0
EOF
    mysql -u root -proot_password -e "CREATE DATABASE atlocaldb DEFAULT CHARACTER SET = utf8 COLLATE utf8_general_ci"
    mysql -u root -proot_password -e "GRANT ALL ON atlocaldb.* TO 'atlocaldbuser'@'%' IDENTIFIED BY 'atpassword'"
    sudo service mysql restart
}

##
# Main
##
update_system
mysql_configure_root_password
apt_install mysql-server-5.7
mysql_configure
