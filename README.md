A servlet which processes form data and reads/writes it to a data store.

This is a work in progress, the servlet's behaviour is:

Read data from the data store on an HTTP "GET" - and return an HTML page displaying data.

Write data to the data store on an HTTP "POST" - and then return an HTML page displaying the new state of the data.

Some additional notes:

- If no data is found in the data store, the HTML page will contain empty fields
- People can have first names, last names, or both
- If both the fields for a person's name are blanked on the form, that person will be removed from the data store

How to run this code using Maven:

The simple-webapp project within this repository contains a Maven POM (Project Object Model).

To build this project and install it to the local Maven repo, run "mvn install" from the simple-webapp directory in a terminal window

To run this project using the Jetty webserver, run "mvn jetty:run" from the simple-webapp directory in a terminal window. When the Jetty server has started, you can access the servlet through your web browser at http://localhost:8080/processform

In order to run the Jetty server, your Maven "settings.xml" file will need the org.eclipse.jetty plugin group configured. The sample file "sample-settings.xml" contains this configuration.
