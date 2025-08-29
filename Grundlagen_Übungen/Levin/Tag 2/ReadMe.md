# Testfall-Übungen: Verkaufssoftware und Black-Box Testing

## Übung 1: 

### A) Abstrakte Testfälle mit logischen Operatoren

| Test-ID | Bedingung | Erwarteter Rabatt | Beschreibung |
|---------|-----------|-------------------|--------------|
| T1 | Kaufpreis < 15'000 | 0% | Kein Rabatt unter 15'000 CHF |
| T2 | Kaufpreis = 15'000 | 5% | Grenzwert: Genau 15'000 CHF |
| T3 | 15'000 < Kaufpreis ≤ 20'000 | 5% | Rabattbereich 5% |
| T4 | Kaufpreis = 20'000 | 5% | Grenzwert: Genau 20'000 CHF |
| T5 | 20'000 < Kaufpreis < 25'000 | 7% | Rabattbereich 7% |
| T6 | Kaufpreis = 25'000 | 8.5% | Grenzwert: Genau 25'000 CHF |
| T7 | Kaufpreis > 25'000 | 8.5% | Maximaler Rabatt über 25'000 CHF |
| T8 | Kaufpreis ≤ 0 | Error/0% | Ungültiger Wert (Grenzfall) |

### B) Konkrete Testfälle mit spezifischen Werten

| Test-ID | Eingabe (CHF) | Erwarteter Rabatt | Erwarteter Endpreis (CHF) | Beschreibung |
|---------|---------------|-------------------|---------------------------|--------------|
| T1 | 10'000 | 0% | 10'000 | Unter Mindestgrenze |
| T2 | 14'999 | 0% | 14'999 | Knapp darunter |
| T3 | 15'000 | 5% | 14'250 | Exakt erster Fall |
| T4 | 17'500 | 5% | 16'625 | Mitte des ersten Rabattbereichs |
| T5 | 20'000 | 5% | 19'000 | Exakt zweiter Fall |
| T6 | 22'500 | 7% | 20'925 | Mitte des zweiten Rabattbereichs |
| T7 | 24'999 | 7% | 23'249.07 | Knapp unter dritter Fall |
| T8 | 25'000 | 8.5% | 22'875 | Exakt dritter Fall |
| T9 | 35'000 | 8.5% | 32'025 | Über maximalem Fall |
| T10 | 0 | 0%/Error | 0/Error | Grenzfall: Nullwert |
| T11 | -5'000 | Error | Error | Negativer Wert |

## Übung 2: Funktionale Black-Box Tests für Autovermietung

### Die 5 wichtigsten funktionalen Black-Box Testfälle:

| ID | Beschreibung | Erwartetes Resultat | Effektives Resultat | Status | Mögliche Ursache |
|----|--------------|-------------------|-------------------|--------|------------------|
| AV1 | Fahrzeugsuche mit gültigen Daten (Ort, Datum, Fahrzeugtyp) | Liste verfügbarer Fahrzeuge wird angezeigt mit Preisen und Details | 17 Fahrzeuge vom 30.08 bis zum 01.09 am Zürich Flughafen aufgelistet | Erfolgreich | - |
| AV2 | Buchungsprozess komplett durchführen (Fahrzeug wählen → Kundendaten → Zahlung) | Buchungsbestätigung mit Reservierungsnummer wird generiert | Normalerweise würde eine Mail mit der Buchungsbestätigung kommen | Offen | - |
| AV3 | Benutzerregistrierung und Login-Funktionalität | Neuer Account wird erstellt und Login funktioniert mit korrekten Zugangsdaten | Account wurde erfolgreich erstellt und man ist angemeldet | Erfolgreich | - |
| AV4 | Stornierung einer bestehenden Buchung | Buchung wird storniert, Bestätigungsmail versendet, evtl. Storniergebühren berechnet | Es wurden keine Storniergebühren berechnet | Fehler | Fehler im Code beim berechnen -> Vielleicht vergessen die Storniergebühren dazu zu rechnen |
| AV5 | Preisberechnung mit verschiedenen Optionen (Versicherung, Zusatzfahrer, GPS) | Gesamtpreis wird korrekt berechnet inkl. aller gewählten Extras und Steuern | Der Gesamtpreis wurde erfolgreich und korrekt berechnet | Erfolgreich | - |

## Übung 3: Bank-Software Analyse

*Hinweis: Allgemeine Analyse für typische Banking-Software-Komponenten*

### Mögliche Black-Box Testfälle (Benutzersicht):

| Test-ID | Testfall | Eingabe | Erwartete Ausgabe | Kategorie |
|---------|----------|---------|-------------------|-----------|
| BB1 | Konto-Login | Gültige Benutzerdaten | Erfolgreicher Login, Kontoübersicht angezeigt | Authentifizierung |
| BB2 | Kontostand abfragen | Nach erfolgreichem Login | Aktueller Kontostand wird korrekt angezeigt | Datenabfrage |
| BB3 | Geld überweisen | Zielhonto, Betrag, Verwendungszweck | Überweisung wird ausgeführt, Bestätigung erhalten | Transaktion |
| BB4 | Transaktionshistorie | Zeitraum auswählen | Liste aller Transaktionen im gewählten Zeitraum | Reporting |
| BB5 | Ungültige Überweisung | Negativer Betrag oder ungenügend Guthaben | Fehlermeldung, Transaktion wird abgelehnt | Fehlerbehandlung |

### Mögliche White-Box Testfälle (Code-Ebene):

| Method-ID | Methode/Klasse | Zu testende Logik | Testarten |
|-----------|----------------|-------------------|-----------|
| WB1 | `calculateBalance()` | Berechnung des aktuellen Kontostands | Unit Test, Boundary Tests |
| WB2 | `validateTransaction()` | Überprüfung der Transaktionsvalidität | Unit Test, Exception Tests |
| WB3 | `authenticateUser()` | Benutzer-Authentifizierung | Security Tests, Unit Tests |
| WB4 | `processTransfer()` | Geldtransfer-Logik | Integration Tests, Unit Tests |
| WB5 | `getTransactionHistory()` | Datenbankabfragen für Transaktionen | Integration Tests, Performance Tests |

### Code-Verbesserungsvorschläge (Best Practices):

#### 1. **Sicherheit:**
- Implementierung von Passwort-Hashing (BCrypt)
- Eingabevalidierung gegen SQL-Injection
- Session-Management und Token-basierte Authentifizierung
- HTTPS-Verschlüsselung für alle Übertragungen

#### 2. **Code-Qualität:**
- Konsistente Naming-Conventions
- Ausführliche Javadoc-Dokumentation
- Separation of Concerns (Model-View-Controller)
- Dependency Injection für bessere Testbarkeit

#### 3. **Fehlerbehandlung:**
- Zentrale Exception-Behandlung
- Logging-Framework (Log4J/SLF4J)
- Graceful Error Handling mit benutzerfreundlichen Fehlermeldungen
- Rollback-Mechanismen für fehlgeschlagene Transaktionen

#### 4. **Testing:**
- Unit Tests für alle Business-Logic-Methoden
- Integration Tests für Datenbankoperationen
- Mock-Objects für externe Dependencies
- Test-Coverage-Tools zur Überwachung der Testabdeckung

#### 5. **Architektur:**
- Repository Pattern für Datenzugriff
- Builder Pattern für komplexe Objekterstellung
- Observer Pattern für Event-Handling
- Configuration externalisieren (Properties-Files)

#### 6. **Performance:**
- Connection Pooling für Datenbankzugriffe
- Caching für häufig abgerufene Daten
- Asynchrone Verarbeitung für langwierige Operationen
- Pagination für große Datensätze

### Empfohlene Teststrategie:

1. **Unit Tests:** 70% Coverage der Business-Logic
2. **Integration Tests:** Datenbankoperationen und API-Endpunkte
3. **Security Tests:** Authentifizierung und Autorisierung
4. **Performance Tests:** LastTests für kritische Transaktionen
5. **User Acceptance Tests:** End-to-End Workflows

---

## Verwendung

Diese Datei kann direkt als `README.md` in Ihr Repository kopiert werden. Die Tabellen sind in Standard-Markdown-Format erstellt und sollten in allen gängigen Git-Plattformen (GitHub, GitLab, etc.) korrekt dargestellt werden.

## Nächste Schritte

1. README.md in Repository speichern
2. Konkrete Testfälle implementieren
3. Testergebnisse in den Tabellen dokumentieren
4. Code-Verbesserungen nach Best Practices umsetzen