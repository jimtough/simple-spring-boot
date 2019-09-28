#!/bin/bash
# set -x will enable display of all subsequent commands on the terminal to aid with debugging
set -x
pwd
env
systemctl enable simple-spring-boot.service
systemctl start simple-spring-boot.service
systemctl status simple-spring-boot.service
