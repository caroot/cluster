cluster
=======

Der JBoss Cluster soll zur Kontrolle des JBoss-Servers dienen.
Dieser nutzt Methoden der  JBOSS zur Kontrolle des JBoss-Servers. (Quelle: https://docs.jboss.org/author/display/AS71/The+native+management+API)

Zum Nutzen des aktuellen Projektes müsst Ihr nach dem Import folgendes ausführen:
- Rechtsklick auf euer Projekt.
- Maven im Popup wählen.
- Darunter Maven Update.

:-( Falls das nicht geholfen hat. Dann bitte folgendes machen:
1. Euer benutzer verzeichnis aufsuchen:
   %USERPROFILE%\.m2
    
2. Hier die .settings sichern als .setting.bak

3. Dann solltet Ihr folgendes Eingetragen haben:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.1.0 http://maven.apache.org/xsd/settings-1.1.0.xsd">
  <profiles>
	<profile>
      <id>jboss-public-repository</id>
      <repositories>
        <repository>
          <id>jboss-public-repository-group</id>
          <name>JBoss Public Maven Repository Group</name>
          <url>https://repository.jboss.org/nexus/content/groups/public-jboss/</url>
          <layout>default</layout>
          <releases>
            <enabled>true</enabled>
            <updatePolicy>never</updatePolicy>
          </releases>
          <snapshots>
            <enabled>true</enabled>
            <updatePolicy>never</updatePolicy>
          </snapshots>
        </repository>
      </repositories>
      <pluginRepositories>
        <pluginRepository>
          <id>jboss-public-repository-group</id>
          <name>JBoss Public Maven Repository Group</name>
          <url>https://repository.jboss.org/nexus/content/groups/public-jboss/</url>
          <layout>default</layout>
          <releases>
            <enabled>true</enabled>
            <updatePolicy>never</updatePolicy>
          </releases>
          <snapshots>
            <enabled>true</enabled>
            <updatePolicy>never</updatePolicy>
          </snapshots>
        </pluginRepository>
      </pluginRepositories>
    </profile>
  </profiles>
  <activeProfiles>
    <activeProfile>jboss-public-repository</activeProfile>
  </activeProfiles>
  <mirrors>
    <mirror>
      <id>UK</id>
      <name>UK Central</name>
      <url>http://uk.maven.org/maven2</url>
      <mirrorOf>central</mirrorOf>
    </mirror>
  </mirrors>
 <dependency>
	<groupId>org.jboss.as</groupId>
	<artifactId>jboss-as-controller-client</artifactId>
	<version>7.1.3.Final</version>
</dependency>
</settings>


jbosscluster