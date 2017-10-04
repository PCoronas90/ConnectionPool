# ConnectionPool
La creazione di un Pool di connessione è una tecnica utilizzata da molti server applicativi per ottimizzare le prestazioni. 
L'attivazione di una connessione a DB è un'operazione costosa che influenza inevitabilmente le prestazioni dell'applicazione. 
Un Pool di connessioni sono semplicemente dei collegamenti a db già attivi 
che aspettano di essere utilizzati.
Nell'esempio riportato ho utilizzato Apache Tomcat, che fornisce un modo per creare delle DB Connection

### Fase Iniziale
* Creare un Dynamic Web Project in Eclipse
* Utilizzare Tomcat come Web Server
* Creare un DB con mySql

### Codice da Inserire nel context.xml di Tomcat
Per creare un pool di connessioni (15 attive e 5 idle), inserire il
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


