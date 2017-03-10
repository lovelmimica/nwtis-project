#"Advanced Web Technologies and Services" project specifications
System should be composed of 3 applications, which are individually specifed in the following lines.
##Installation, software and communication system architecture 
###Application_01: 
- web server: Tomcat EE features: EE6 / 7 
- web user interfaces: JSP 
- database: MySQL - name nwtis_ {username} _bp_1
- work with the database: JDBC, SQL 
- sending email 
- hosts socket server
- provides SOAP web service for current weather data for selected address 
- provides REST web service for prognostic weather data for selected address 
- uses openweathermap.org REST web service to download weather data 
- uses the Google Maps API REST web service to download relocation data for addresses 

###Application_02: 
- web server: Glass fish EE features: EE7 
- user interface: JSF (Faceless) with bilingual support
- database : Java DB, name: nwtis_ {username} _bp_2
- work with the database: ORM (EclipseLink), Criteria API 
- James e-mail server 
- reads and process e-mail messages 
- sends a JMS message in the message queue: NWTiS_ {username} _1 for statistical processing of email messages 
- sends a JMS message in the message queue: NWTiS_ {username} _2 for adding new address 
- uses socket server for approval and change rank of users, and server status management of {username}_app_1
- uses SOAP web service {username} _app_1 to get current weather data for selected addresses
- uses REST web service {username} _aplikacija_1 get forecast weather data for the selected addresses 
- provides a REST web service for active users and the added addresses 

###Application_03: 
- web server: Glassfish 
- EE features: EE7 
- user interface: JSF (Facelets) 
- database: not using localy 
- listens on JMS message queues NWTiS_ {username} _1 and NWTiS_ {username} _2 download, stores and reviews JMS messages 
- uses socket server {username} _aplikacija_1 to check and add an address and get associated meteorological data 
- uses REST web service {user name} _app_2 to view active users, and their added addresses
- uses SOAP web service {user name} _app_1 to get current weather data for the selected address

##Functional requirements
###Application_01
Web application ({username} _app_1) deals with weather forecasting and current weather data for some address. 

Users of the system are classified into two types: administrators and ordinary users.&nbsp;Ordinary users during their work are divided in certain ranks (levels 1-5). Based on their categories they have the ability to perform a certain number of requests (defined in configuration file) in the same time interval (defined in configuration file.&nbsp;User request (to the primitive server and its related web services) can be realized only if the user did not exceed their quota limit during current time interval. 

In background mode starts thread which downloads current and forecast weather data from openweathermap.org web service. Weather data is collected for selected set of addresses which are stored in database table "adrese". It is necessary to store at least the following meteorological and auxiliary data into a database: temperature, pressure, humidity, wind, visibility, precipitation and download time. In any takeover weather data are added to the data in the table (not updated). 

Background thread management, and execution of other belonging tasks is carried out by the primitive server that acts like a socket server on a particular port (defined in configuration file). User authentication should be performed for all requests, and only if it is fine, task can continue to operate.&nbsp;The execution of these operations should be realized using separate thread, because it should not affect the ability of the server to receive the new requests. 

The socket requesting is based on the string commands, which can be:
####Admin commands:
- USER; PASSWD password; {PAUSE; | START; | STOP; | STATUS; | ADD user1; PASSWD password1; ROLE {ADMIN | USER}; | {UP | DOWN} user2;}

####User commands:
- USER; PASSWD password; {TEST “address“; | GET “address“; | ADD “address“;}

####Explanation of commands: 
- USER;&nbsp;PASSWD password; 
User authentication.&nbsp;Returns ERR 20; if there is no user or password does not match.&nbsp;If this is the only content of the command, returns OK 10;&nbsp;otherwise continues to execution of other command parts. 
- PAUSE; 
Temporarily interrupts download of weather data (but still can retrieve user commands).&nbsp;Returns ERR 21;&nbsp;if the user is not an administrator, OK 10;&nbsp;if server was not in pause state, or ERR 30;&nbsp;if it was in pause. 
- START; 
Continues to download weather data.&nbsp;Returns ERR 21;&nbsp;if the user is not an administrator,&nbsp;OK 10;&nbsp;if it was in pause state, or ERR 31;&nbsp;if not. 
- STOP;
Completely stops download of weather data and receiving user requests.&nbsp;Returns ERR 21;&nbsp;if the user is not an administrator.&nbsp;OK 10;&nbsp;if server was not in the state of stop, or ERR 32;&nbsp;otherwise.
- STATUS; 
Returns the current state of the server.&nbsp;Returns ERR 21;&nbsp;if the user is not an administrator.&nbsp;Returns OK nn;&nbsp;where n means: 00 – pause state, 01 – normal (working) state, 02 – stop state. 
- ADD user1;&nbsp;PASSWD password1;&nbsp;ROLE {ADMIN |&nbsp;USER};
Adds users with a password and role.&nbsp;Returns ERR 21;&nbsp;if the (requesting) user is not an administrator.&nbsp;If there user1 does not exist, adds it to the table "korisnici" with a password password1, and with the role of administrator if the ADMIN is selected and the role of the ordinary user if the USER is selected.&nbsp;Then an email message is sent (receiver, sender and subject are defined by the configuration), in the MIME type ” with information about the request. The content of the command are written in the first line of message, which is followed by data on the time of receiving the request, the total number of users, number of administrators and the number of ordinary users.&nbsp;Returns OK 10;&nbsp;If the user already exists returns ERR 33; 
- {UP | DOWN} user2; 
Changes the rank for 1 more (UP) or 1 less (DOWN).&nbsp;Returns ERR 21;&nbsp;if the user is not an administrator.&nbsp;If everything is fine returns OK 10;&nbsp;if the rank can't be changer returns ERR 34; and&nbsp;if the user does not exist returns ERR 35; 
- ADD "address"; 
Adds the address to the set for which are weather data downloaded. Based on the address name, particular geolocation data are downloaded from Google Maps API. Returns ERR 40;&nbsp;if user has exceeded the quota in current time interval.&nbsp;Returns OK 10;&nbsp;if there is no record of the address in database, or ERR 41;&nbsp;if address exists in database. 
- TEST "address"; 
Returns a status of ” which means whether it is present in database or not.&nbsp;Returns ERR 40;&nbsp;if user has exceeded the quota in current time interval. Returns OK 10;&nbsp;if there is particular address in database, or ERR 42;&nbsp;if it is not there.

Second task of the web application is to provide a SOAP web service for current weather data.&nbsp;Any request is to be authenticated through the user data in database, and user request quota has to be checked and decremented.&nbsp;Any request for a SOAP web service should be added to the log file in the database.&nbsp;SOAP web service relies on current weather data stored in database via background download thread.

SOAP web service offers following data: 
- the current weather data for the selected address (last update) 
- list of addresses that the particular user has added 
- rank list (the first “n“, “n“ is inputted) of addresses that has most of weather data records in database
- last n (“n“ is inputted) 
- weather data for a selected address weather data for the address in a given time interval (from date to date)

The third task of the web application is to provide a REST web service for weather forecast data. Any request is to be authenticated through the user data in database, and user request quota has to be checked and decremented.&nbsp;Any request for a REST web service should be added to the log file in the database.&nbsp;REST web service relies on weather forecast data stored in database via background download thread. 

SOAP web service offers following data: 
- the list of addresses for which data are collected 
- current weather forecast (for next 5 days, per 3 hours) for the selected address 
- all the weather forecast data for the selected address, which are in specified time interval

The fourth task is the visible part of the web application (that is the admin and user GUI). 

The role of admin has following options:
- list all users present in database read web service log records, with paging
- read socket server log records, with paging

The role of ordinary user has following options:
- read own socket requests

###Application_02
Enterprise application ({username} _app_2) that holds EJB and Web modules. 

The application in background mode runs thread which iterativly checks email inbox.&nbsp;From the received unread email messages, ones that have subject equal to the value set in configuration file of 1. Application, and are also formatted as MIME type ” messages, are called “NWTiS messages“.&nbsp;NWTiS messages where the first line of content corresponds to the command ADD user1;&nbsp;PASSWD password1;&nbsp;ROLE {ADMIN |&nbsp;USER}; (from the socket server of 1. Application), are “Correct NWTiS messages“. If the particular user exists in the database and has a waiting status, that status changes to approved and count of successful NWTiS messages is incremented. 

If the user exists in database, but does not have waiting status or if he does not even exist, the number of unsuccessful NWTiS messages is incremented. Processed NWTiS messages should be serialized to the arbitrary folders for storing them (special folders for successful, unsuccessful and incorrect NWTiS messages). &nbsp;Other non NWTiS messages should mared as read, but not serialized. 

At the end of each iteration JMS message should be sent to the queue named NWTiS_ {username} _1, with information about the thread start and end time, the number of read messages, the number NWTiS messages, the number of newly approved users and the number of users for which it was not successful approval. 

The second task is a web module with admin and user GUI.&nbsp;It is necessary to log of all requests with employing the web application filter. 

Web module is to be realized through JSF (Facelets) with minimal bilingual version (Croatian and English).&nbsp;This means that all the static texts in views should be set as ”, so they can flexibly get language translations.&nbsp;Language is selected on the home page of application. 

All users, at start, have options to register or to login. 

For registration, user has to input the username (must be unique), password (and repeated password) and type of user (admin or ordinary user). After a successful verification of registration data, new user record is entered into the database with the status of waiting for approval from the admin.&nbsp;As long as the admin does not approve the registration, the user can not log into the system.&nbsp;If it registration is rejected, the user may send a new request for registration with the modified data.&nbsp;After he was granted registration, the user can be logged into the system and perform certain activities.

The role of admin has following options:
- Overview and management of the status of primitive server from {username} _app_1. It is done by sending the corresponding socket commands: USER users;&nbsp;PASSWD password;&nbsp;{PAUSE;&nbsp;|&nbsp;START;&nbsp;|&nbsp;STOP;&nbsp;|&nbsp;STATUS};
- List the requests for registration and to approve or reject every particular request.&nbsp;Any approval entails sending command ADD user1;&nbsp;PASSWD password1;&nbsp;ROLE {ADMIN |&nbsp;USER};&nbsp;to the socket server from application {username} _app_1. Approvement does not entail the update of user status in local database, but rejectment does (rejected user record is deleted from local database).
- Change the rank for the selected user.&nbsp;Any change entails sending command {UP | DOWN} user2;&nbsp;the socket server from applications {username} _app_1. This does not entail update of user record in the local database.
- Review messages in the email inbox.&nbsp;User, can choose a folder to determine which class of email messages he wants to read.&nbsp;By selecting individual messages, user can see its contents. The user can also delete all messages from active (selected) folder. 
- Review the request log records from database.

The role of ordinary user has following options (which are mainly based on usage of web services from {username}_app_2):
- List addresses for which the weather data is collected 
- List addresses which were added by particular user
- Print most recent weather data for the selected address 
- To add a new address to which will weather data be collected.  It is realized by sending JMS message to queue named NWTiS_{username}_2, which contains information about user, his password and address which is to be added. 
- Print current weather forecast (for 5 days per 3 hours) for the selected address (last taken forecast) 

The third task is to provide a list of active (currently online) Web users, a list of addresses added by the selected active user, through a REST web service. 

Access to data in the database should be realized via ORM's (through the session, entity bean's and criteria API).

###Application_03
Enterprise application ({username} _app_3) that holds EJB and Web modules. 

The application receives two types of JMS message (by using MessageDriven Bean). One type is about reporting about email inbox processing, and other is about adding new addresses to database.

On receiving JMS messages about adding addresses, it is necessary to check the existence of particular address in set for which the weather data is downloaded. If address does not already exist in set and user did not exceeded his quota in current time interval, the request USER;&nbsp;PASSWD password;&nbsp;ADD ”; is sent to socket server at {user name} _app_1.

Received JMS messages are stored in application memory.&nbsp;If an application stops working, it is necessary to serialize messages in the external file.&nbsp;When the application starts up, serialized messages has to be loaded again in application memory.

The second task is the Web module that should be realized through JSF (Facelets).&nbsp;It offers function of review and earsing of the stored JMS messages. 

The user can also call a REST web service to retrieve and print data for the current active users at {username} _app_2, and for select active user can print set of addresses which were added by that particular (active) user. For selected address user can also request current weather data, by using SOAP web service at {username}_app_1. 
