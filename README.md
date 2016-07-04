A servlet which processes form data and reads/writes it to a data store.

This is a work in progress, when the code is stable the servlet will:

Read data from the data store on an HTTP "GET" - and return an HTML page displaying data.

Write data to the data store on an HTTP "POST" - and then return an HTML page displaying the new state of the data.

Some additional notes:

- If no data is found in the data store, the HTML page will contain empty fields
- If empty data items are submitted to the servlet, these store will not be updated
