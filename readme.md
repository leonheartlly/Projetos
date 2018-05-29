# Spring Boot Project

## Requirements

1. Java 8 or latest
2. Maven
3. Lombok


## Instructions
<ul>
<li>Install lombok.jar to your IDE;
</li>

<li>After cloning this repository, update pom.xml with database config, go to the root folder, run the following command's:
<pre>
    mvn flyway:migrate
</pre>
</li>

<li>Configure the application.properties
</li>

<li>Run the project:
<pre>
	mvn clean install
</pre>
<pre>
    mvn spring-boot:run
</pre>
</li>

<li>Lombok manual install on MAC
<pre>
	mvn install:install-file -Dfile=lombok-edge.jar -DgroupId=org.projectlombok -DartifactId=lombok -Dversion=lombok-edge.UNRELEASED -Dpackaging=jar
</pre>
</li>
</ul>

## Docker
<ul>
<li>Maven build:
</li>
<pre>
	install dockerfile:build
</pre>
<li>Docker run:
</li>
<pre>
	docker run -p 8080:8080 -t spring/portal-prefeitura-araguacu
</pre>
</ul>
