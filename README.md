# ironbit
Proyecto de test para Ironbit

<h2>Requisitos proyecto</h2>
* Java 8
* Maven 3.2^
* Docker


<h2>Desplegar MYSQL</h2>

<h4>Desde el Dockerfile</h4>
<ol>
  <li>Nos colocamos en la carpeta ./ironbit/db/</li>
  <li>Ejecutamos:  <b>docker build -t ironbit_mysql .</b></li>
  <li>Se cargara la imagen customizada de MYSQL</li>
  <li>Ejecutamos:  <b>docker run --name ironbit_mysql -d -p 3306:3306 ironbit_mysql</b></li>
</ol>

<h4>Desde comando de Docker</h4>
<ol>
  <li>Ejecutamos el comando:<br><b>docker run -d --name ironbit_mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=ironbit -p 3306:3306 mysql:8.0.34</b></li>
</ol>
