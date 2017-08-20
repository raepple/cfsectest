# Cloud Foundry XSA security sample application for SAP Cloud Platform

This repository contains the security sample application for the units 5 and 6 of week 5 in the openSAP course [SAP Cloud Platform Essentials (Update Q3/2017)](https://open.sap.com/courses/cp1-2).

Follow the instructions below to install the application on your local development system and deploy it in your SAP Cloud Platform Neo trial account.

## Prerequisites

- You have [Maven](https://maven.apache.org/) and [npm](https://www.npmjs.com/) installed
- Run `npm config set @sap:registry="https://npm.sap.com"`
- You have the [Cloud Foundry CLI (Command Line Interface)](https://help.sap.com/viewer/65de2977205c403bbc107264b8eccf4b/Cloud/en-US/4ef907afb1254e8286882a2bdef0edf4.html) installed and your SAP CP trial account enabled for the CF environment


## Setup instructions
1. Building the application
    1. Edit the file **/manifest.yml** and search/replace the string *123456* with a random 6-digit number of your choice
    2. Go to the root directory (/) and run `mvn clean install`
    3. Go to the */web* directory and run `npm install`
    4. Check if you the deployable web archive file `/target/CFSecTest.war` has been created
    
2. Deploy the application
    1. Go to the root (/) of the application source directory
    2. Run `cf api https://api.cf.eu10.hana.ondemand.com` (replace eu10 with us10 if your are in region US East)
    3. Run `cf login` and authenticate with your SAP CP trial user and password
    4. Run `cf create-service xsuaa application java-uaa -c xs-security.json`
    5. Run `cf push`
    6. Run `cf map-route opensap-approuter cfapps.eu10.hana.ondemand.com -n p0123456789trial-opensap123456-approuter`. In this command, replace 123456 with the 6-digit number you chose in step 1.1, and replace p0123456789trial with your trial account name


