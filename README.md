
#Installation, software and communication system architecture:

Web server: Tomcat
EE features: EE6 / 7
Web user interfaces: JSP
Database: MySQL - name nwtis_ {username} _bp_1
work with the database: JDBC, SQL
sends emails
hosts socket server
provides SOAP web service for current weather data for selected address
provides REST web service for prognostic weather data for selected address
uses openweathermap.org REST web service to download weather data
using the Google Maps API REST web service to download relocation data for addresses
#Functional requirements:

Web application ({username} _app_1) deals with weather forecasting and current weather data for some address.

Users of the system are classified into two types: administrators and ordinary users. Ordinary users during their work are divided in certain ranks (levels 1-5). Based on their categories they have the ability to perform a certain number of requests (defined in configuration file) in the same time interval (defined in configuration file. User request (to the primitive server and its related web services) can be realized only if the user did not exceed their quota limit during current time interval.

In background mode starts thread which downloads current and forecast weather data from openweathermap.org web service. Weather data is collected for selected set of addresses which are stored in database table "adrese". It is necessary to store at least the following meteorological and auxiliary data into a database: temperature, pressure, humidity, wind, visibility, precipitation and download time. In any takeover weather data are added to the data in the table (not updated).

Background thread management, and execution of other belonging tasks is carried out by the primitive server that acts like a socket server on a particular port (defined in configuration file). User authentication should be performed for all requests, and only if it is fine, task can continue to operate. The execution of these operations should be realized using separate thread, because it should not affect the ability of the server to receive the new requests.

The socket requesting is based on the string commands, which can be: 1. Admin commands: USER; PASSWD password; {PAUSE; | START; | STOP; | STATUS; | ADD user1; PASSWD password1; ROLE {ADMIN | USER}; | {UP | DOWN} user2;} 2. User commands: USER; PASSWD password; {TEST “address“; | GET “address“; | ADD “address“;}

Explanation of commands:

USER user; PASSWD password; User authentication. Returns ERR 20; if there is no user or password does not match. If this is the only content of the command, returns OK 10; otherwise continues to execution of other command parts.
PAUSE;
Temporarily interrupts download of weather data (but still can retrieve user commands). Returns ERR 21; if the user is not an administrator, OK 10; if server was not in pause state, or ERR 30; if it was in pause.
START; Continues to download weather data. Returns ERR 21; if the user is not an administrator, OK 10; if it was in pause state, or ERR 31; if not.
STOP; Completely stops download of weather data and receiving user requests. Returns ERR 21; if the user is not an administrator, OK 10; if server was not in the state of stop, or ERR 32; otherwise.
STATUS; Returns the current state of the server. Returns ERR 21; if the user is not an administrator. Returns OK nn; where n means: 00 – pause state, 01 – normal (working) state, 02 – stop state.
ADD user1; PASSWD password1; ROLE {ADMIN | USER}; Adds users with a password and role. Returns ERR 21; if the (requesting) user is not an administrator. If there user1 does not exist, adds it to the table "korisnici" with a password password1, and with the role of administrator if the ADMIN is selected and the role of the ordinary user if the USER is selected. Then an email message is sent (receiver, sender and subject are defined by the configuration), in the MIME type ” with information about the request. The content of the command are written in the first line of message, which is followed by data on the time of receiving the request, the total number of users, number of administrators and the number of ordinary users. Returns OK 10; If the user already exists returns ERR 33;
{UP | DOWN} user2; Changes the rank for 1 more (UP) or 1 less (DOWN). Returns ERR 21; if the user is not an administrator. If everything is fine returns OK 10; if the rank can't be changer returns ERR 34; and if the user does not exist returns ERR 35;
ADD "address"; Adds the address to the set for which are weather data downloaded. Based on the address name, particular geolocation data are downloaded from Google Maps API. Returns ERR 40; if user has exceeded the quota in current time interval. Returns OK 10; if there is no record of the address in database, or ERR 41; if address exists in database.
TEST "address"; Returns a status of ” which means whether it is present in database or not. Returns ERR 40; if user has exceeded the quota in current time interval. Returns OK 10; if there is particular address in database, or ERR 42; if it is not there.
Second task of the web application is to provide a SOAP web service for current weather data. Any request is to be authenticated through the user data in database, and user request quota has to be checked and decremented. Any request for a SOAP web service should be added to the log file in the database. SOAP web service relies on current weather data stored in database via background download thread.

SOAP web service offers following data:

the current weather data for the selected address (last update)
list of addresses that the particular user has added
rank list (the first “n“, “n“ is inputted) of addresses that has most of weather data records in database
last n (“n“ is inputted) weather data for a selected address
weather data for the address in a given time interval (from date to date)
The third task of the web application is to provide a REST web service for weather forecast data. Any request is to be authenticated through the user data in database, and user request quota has to be checked and decremented. Any request for a REST web service should be added to the log file in the database. REST web service relies on weather forecast data stored in database via background download thread.

SOAP web service offers following data:

the list of addresses for which data are collected
current weather forecast (for next 5 days, per 3 hours) for the selected address
all the weather forecast data for the selected address, which are in specified time interval
The fourth task is the visible part of the web application (that is the admin and user GUI).

The role of admin has following options:

list all users present in database read web service log records, with paging
read socket server log records, with paging
The role of ordinary user has following options:

read own socket requests
