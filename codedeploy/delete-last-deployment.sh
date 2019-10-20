#!/bin/bash
# set -x will enable display of all subsequent commands on the terminal to aid with debugging
set -x
pwd
env
echo "APPLICATION_NAME:" $APPLICATION_NAME
echo "DEPLOYMENT_ID:" $DEPLOYMENT_ID
echo "DEPLOYMENT_GROUP_NAME:" $DEPLOYMENT_GROUP_NAME
echo "DEPLOYMENT_GROUP_ID:" $DEPLOYMENT_GROUP_ID
echo "LIFECYCLE_EVENT:" $LIFECYCLE_EVENT
# remove backup of last deployment (if it exists)
rm -f /var/app/simple-spring-boot-app.jar.PREV
# backup current deployment
mv /var/app/simple-spring-boot-app.jar /var/app/simple-spring-boot-app.jar.PREV
