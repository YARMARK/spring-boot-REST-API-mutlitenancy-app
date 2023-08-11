# spring-boot-REST-API-mutlitenancy-app

## Description

This is a test project about implementing Java backend microservices on SAP BTP Cloud
Foundry Platform.

## Project details

* [Guide to developing cloud applications using SAP Cloud Platform and Cloud Foundry.](https://habr.com/ru/companies/sap/articles/350690/)
* Spring-Boot Crud operations
* Deployment on Cloud Foundry (SAP BTP)
* O-Auth 2.0 protocol (XSUAA service)
* SaaS multi-tenant application (Schema isolation), saas-registry service

## [Create application with Java and Buildpacks](https://developers.sap.com/tutorials/btp-cf-buildpacks-java-create.html#335aa6d6-211f-4b52-b5b3-b6f1b516e728)

* Simple Spring-Boot application with one GET-endpoint. This app is triggered through a web microservice (application router).
* Application will set authentication checks and an authorization role  to access your web application.

### XSUAA authorisation service:

* cf create-service xsuaa application <xsuaa-service_name> -c xs-security.json - create and bind service
* cf unbind-service 'application' <xsuaa-service_name> - unbind service
* cf delete-service '<xsuaa-service_name>' - delete service

### Build project

1. In 'web' directory run commands to create package.json and add approuter service:
    * npm install
    * npm install @sap/approuter --save
2. In root directory run:
    * mvn clean install

### Deploy application to SAP BTP 

1. Set the Cloud Foundry API endpoint for your subaccount on SAP BTP:
      * cf api https://api.cf.us10-001.hana.ondemand.com
      * cf login
2. Deploy application:
      * cf push

### [Using Postman to test Api with XSUAA](https://blogs.sap.com/2020/03/02/using-postman-for-api-testing-with-xsuaa/)

1) Get all environment variables of you application:
    * cf env <application_name>

2) Note values:
     * ACCESS_TOKEN_URL = VCAP_SERVICES.<xsuua_name>.credentials.url
     * CLIENT_ID = VCAP_SERVICES.<xsuua_name>.credentials.clientsecrid
     * CLIENT_SECRET = VCAP_SERVICES.<xsuua_name>.credentials.clientsecret
     * XSAPPNAME = VCAP_SERVICES.<xsuua_name>.xsappname
     * SCOPE = XSAPPNAME.scope (Can find in your xs-security.json)
   
3) Postman:
     * Open Postman
     * Create GET_REQUEST
     * Insert link of the request (https://api.cfapps.us10-001.hana.ondemand.com/getBook)
     * In Authorization tab select OAUTH 2.0 :
          * Grant Type: 'Password credential'
          * Access Token URL = ACCESS_TOKEN_URL
          * Client ID = CLIENT_ID
          * Client Secret = CLIENT_SECRET
          * Scope = SCOPE
       
## [Add Multi-tenancy](https://developers.sap.com/tutorials/cp-cf-security-xsuaa-multi-tenant.html)
https://blogs.sap.com/2021/12/24/multitenancy-develop-and-register-multitenant-application-to-the-sap-saas-provisioning-service-in-cloud-foundry/
### Add SaaS-registry

1. Create config.json for Saas-registry:

```
   {
   "xsappname":"application",
   "appUrls": {
   "onSubscription" : "https://application.cfapps.eu10.hana.ondemand.com/callback/v1.0/tenants/{tenantId}"
   },
   "displayName" : "Application MTA",
   "description" : "Application MTA sample application",
   "category" : "Custom SaaS Applications"
   }
```

2. Make some changes in xs-security.json:
```
   tenant-mode: shared
   oauth2-configuration: {
     "token-validity": 86400,
     "refresh-token-validity": 2592000,
     "redirect-uris": [
     "https://*.cfapps.us10-001.hana.ondemand.com/**"
     ]
     }
```

3. Unbind and delete xsuaa service:
   * cf unbind-service <app_name> <xsuaa_service_name>
   * cf unbind-service <approuter_name> <xsuaa_service_name>
   * cf delete-service <xsuaa_service_name>

4. Create xsuaa instance:
   * cf create-service xsuaa application <xsuaa_service_name> -c xsuaa/xs-security.json
   * cf create-service saas-registry application <saas-registry_name> -c saas-config/config.json

## [Tenant-Aware Persistency](https://blogs.sap.com/2017/12/20/deep-dive-6-with-sap-s4hana-cloud-sdk-extend-your-cloud-foundry-application-with-tenant-aware-persistency/)

   ### [Spring-Boot-REST-API-Mutlitenancy-App](https://github.com/Ragimzade/Spring-Boot-REST-API-Mutlitenancy-App) - Multitenancy Sap Btp
   ### [Spring-Boot-REST-API-Mutlitenancy-App-MTA](https://github.com/SergeichykAndrei/btpapp/tree/master) -  Multitenancy Sap Btp (MTA.yml)

## Remote Debug
### Links
  * [Debugging Java Web Applications on SAP Business Technology Platform](https://help.sap.com/docs/btp/sap-business-technology-platform/debug-java-web-application-running-on-sapmachine?locale=en-US)
  * [Remote debug on CloudFoundry](https://blogs.sap.com/2019/07/24/remote-debugging-on-cloud-foundry/)

### Debug by hand:
1. Login to Cloud Foundry:
```
cf login -a https://api.cf.sap.hana.ondemand.com (Canary Landscape).
```
2. Navigate to your space where your application is deployed
```
cf target -o <organisation name> -s <space name>
```
3. Enable ssh-tunnel for the application:
```
cf enable-ssh <application name>
```
4. Restage your application:
```
cf restage <application name>
```
6. Open ssh-tunnel:
```
cf ssh <app_name>
```
7. Run this command:
```
app/META-INF/.sap_java_buildpack/sap_machine_jre/bin/jcmd 7 VM.start_java_debugging
exit
```
8. After successfully restaging the application run the below command
```
cf ssh -N -T -L 8000:localhost:8000 <application name>
```
10. [Config remote debug configuration](https://blogs.sap.com/2019/07/24/remote-debugging-on-cloud-foundry/)