# ConnectionPool

###Fase Iniziale
*Creare un Dynamic Web Project in Eclipse
**Utilizzare Tomcat come Web Server
***Creare un DB con mySql

###File da Inserire nel Context.xml di Tomcat
Per creare un pool di connessioni (partiamo da 15 attive e 5 idle), inserire il
seguente codice nel context.xml di Tomcat:

```
<Resource name="jdbc/dbTest" auth="Container"
		type="javax.sql.DataSource" username="root" password="password"
		driverClassName="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3305/dbTest"
		maxActive="15" maxIdle="5" />
		
		<Resource name="jdbc/dbTest" auth="Container"
		type="javax.sql.DataSource" username="root" password="password"
		driverClassName="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3305/dbTest"
		maxActive="15" maxIdle="5" />
```


