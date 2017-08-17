# Przygotowanie środowiska:

* Pobierz Apache Maven:http://maven.apache.org/download.cgi
* Rozpakuj oraz umieść folder w C:\Program Files\Apache\maven
* Sprawdź zmienne środowiskowe
**	“JAVA_HOME”: czy ustawiony jest do jdk, jeśli nie to ustaw.
** dodaj zmienne “M2_HOME” oraz “MAVEN_HOME” oraz wskaż folder gdzie jest Maven
** zeedutuj zmienną “PATH” oraz dodaj %M2_HOME%\bin
* Sprawdź czy Maven działa, polecenie: `mvn –version`

# Uruchomienie:

`mvn test`
