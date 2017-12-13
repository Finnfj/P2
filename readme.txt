Diese Vorgabe verwendet für das Speichern der Level das Format JSON. Zum Lesen und Schreiben von 
JSON-Dateien verwenden wir eine Bibliothek: JSON-Simple (org.json.simple). Es gibt zwei Möglichkeiten, 
diese Bibliothek (.jar-Datei) einzubinden:

1) Manuelle Einrichtung des Eclipse-Projektes
Sie können die Bibliothek unter Projekt-Einstellungen -> Java Build Path -> Libraries angeben. Die
.jar-Datei liegt im Projektverzeichnis im Unterverzeichnis /lib.

2) Verwendung des Build-Systems Gradle
Eine Einführung in Gradle sprengt den Rahmen hier. Informationen finden Sie unter https://gradle.org/. 
Wenn Sie Gradle installiert haben, dann führen Sie im Projektverzeichnis über die Kommandozeile 
folgenden Befehl aus: gradle build eclipse
Damit wird  ein Eclipse-Projekt erstellt, dass auch auf die notwendige JSON-Simple Bibliothek verweist. 