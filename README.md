# AppDirect example integration
It supports:
  - Notification APIs (protected by OAuth 0L token)
  - SSO using OpenID 2


### Notification API
- Subscription Create Notification http://[HOST_NAME_SERVICE]/api/v1/subscription/create/notitication?eventurl={eventUrl}

- Subscription Change Notification

     http://[HOST_NAME_SERVICE]/api/v1/subscription/change/notification?eventurl={eventUrl}

- Subscription Cancel Notification

     http://[HOST_NAME_SERVICE]/api/v1/subscription/cancel/notification?eventurl={eventUrl}

- Subscription Status Notification
     
     http://[HOST_NAME_SERVICE]/api/v1/subscription/status/notification?eventurl={eventUrl}

- User Assignment Notification

     http://[HOST_NAME_SERVICE]/api/v1/user/assignment/notitication?eventurl={eventUrl}

- User Unassignment Notification 

     http://[HOST_NAME_SERVICE]/api/v1/user/unassignment/notification?eventurl={eventUrl}

- User Update Notification 

     http://[HOST_NAME_SERVICE]/api/v1/user/update/notification?eventurl={eventUrl}

### OpenID 2.0
These are the configurations required by the SSO provider:

- Login URL
http://[HOST_NAME_WEB_CLIENT]/login/openid?openid_identifier={openid}

### Installation

This example requires Java 8 and Maven
(from the project root folder)
```sh
$ mvn install
$ mvn spring-boot:run -pl eb-services (to execute the rest services)
$ java -jar eb-client-web/target/eb-client-web-0.0.1-SNAPSHOT.war (to execute the web site)
```

###  App settings
- spring.datasource.url=[Database connection string]
- spring.datasource.username=[Database user]
- spring.datasource.password=[Database password]
- spring.datasource.driver-className=[Driver]

- oauth.consumer.key=[App direct Oauth consumer key]
- oauth.consumer.secret=[App direct Oauth consumer password]

- eb.service.url=[Service URL] (eb-services module)
- api.consumer.key=[internal service api key]
- api.consumer.secret=[internal service api secret]
- com.eb.integration.appdirect.persist.stateless.event=[true|false] (True persists stateless events)