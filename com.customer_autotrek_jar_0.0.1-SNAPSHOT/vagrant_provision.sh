#!/bin/bash

update_system() {
    ## Update the packaging system.
    yum -y update
    ## If wanted, upgrade to the latest and greatest
    yum -y upgrade
}

install_tools() {
    yum -y install wget
}

install_mysql_repo() {
    CURRENT_DIR=`pwd`
    cd /tmp
    wget https://dev.mysql.com/get/mysql57-community-release-el7-9.noarch.rpm

    rpm -ivh mysql57-community-release-el7-9.noarch.rpm

    yum -y update

    cd ${CURRENT_DIR}
}

install_mysql_server() {
    yum -y install mysql-server

    systemctl start mysqld
}

configure_mysql_server() {
    TEMP_ROOT_PASSWD=$(grep 'temporary password' /var/log/mysqld.log | cut -d' ' -f11)

    # Local so a lower restriction on passwords is OK
    if ! grep "validate_password_policy=LOW" /etc/my.cnf; then
        echo "validate_password_policy=LOW" >> /etc/my.cnf
    fi

    # We have to allow remote connections
    if ! grep "bind_address=0.0.0.0" /etc/my.cnf; then
        echo "bind_address=0.0.0.0" >> /etc/my.cnf
    fi

    systemctl restart mysqld

    ##
    # Change the root password
    ##
    mysqladmin -u root -p"${TEMP_ROOT_PASSWD}" password "root_password"
    
    mysql -u root -proot_password -e "CREATE DATABASE atlocaldb DEFAULT CHARACTER SET = utf8 COLLATE utf8_general_ci"

    # Note this is not a production setup.
    mysql -u root -proot_password -e "GRANT ALL ON atlocaldb.* TO 'atlocaldbuser'@'%' IDENTIFIED BY 'atpassword'"
}

##
# Main
##
update_system

##
# Install any tools needed
##
install_tools

##
# Installing MySQL is a two step process for CentOS 7
#
# 1) Install the repository.
# 2) Install the mysql server package.
#
install_mysql_repo
install_mysql_server
configure_mysql_server


