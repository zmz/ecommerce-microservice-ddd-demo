#!/usr/bin/env bash

mvn clean

mvn install -pl com.ecommerce:ecommerce-order-service-api -am -Dmaven.test.skip=true
mvn spring-boot:run
