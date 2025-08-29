# Software Testing - Aufgaben und Lösungen

## Aufgabe 1: Formen von Tests in der Informatik

### Unit Tests (Komponententests)
- **Beschreibung**: Testen von einzelnen Funktionen, Methoden oder Klassen
- **Durchführung**: Automatisiert mit Frameworks (Beispiele: JUnit, pytest oder NUnit)
- **Beispiel**: Test bei einem Tascheinrechner, ob die Eingabewerte funktionieren

### Integrationstests
- **Beschreibung**: Testen das Zusammenspiel zwischen verschiedenen Komponenten
- **Durchführung**: Schrittweise Integration der Module und Test ihrer Kommunikation
- **Beispiel**: Test der Kommunikation zwischen Frontend und Backend-API

### Systemtests (End-to-End Tests)
- **Beschreibung**: Testen das gesamte System in einer produktionsähnlichen Umgebung
- **Durchführung**: Oft automatisiert mit Tools (Selenium oder Cypress)
- **Beispiel**: Test eines kompletten Bestellprozesses in einem Online-Shop

## Aufgabe 2: SW-Fehler und SW-Mängel

### SW-Fehler Beispiel
**Division durch Null ohne entsprechende Fehlerbehandlung** - führt zu einem Programmabsturz.

### SW-Mangel Beispiel
**Eine Banking-Software hat keine Zwei-Faktor-Authentifizierung** - funktioniert zwar, erfüllt aber nicht die Sicherheitsanforderungen.

### Beispiel für hohen Schaden
**Der Therac-25 Strahlentherapie-Bug (1985-1987)**: Softwarefehler führten zu Überdosierungen von Strahlung, was mehrere Todesfälle zur Folge hatte.