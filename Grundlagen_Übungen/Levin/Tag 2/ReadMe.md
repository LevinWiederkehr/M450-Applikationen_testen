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

## Übung 2:

### Die 5 wichtigsten funktionalen Black-Box Testfälle:

| ID | Beschreibung | Erwartetes Resultat | Effektives Resultat | Status | Mögliche Ursache |
|----|--------------|-------------------|-------------------|--------|------------------|
| AV1 | Fahrzeugsuche mit gültigen Daten (Ort, Datum, Fahrzeugtyp) | Liste verfügbarer Fahrzeuge wird angezeigt mit Preisen und Details | 17 Fahrzeuge vom 30.08 bis zum 01.09 am Zürich Flughafen aufgelistet | Erfolgreich | - |
| AV2 | Buchungsprozess komplett durchführen (Fahrzeug wählen → Kundendaten → Zahlung) | Buchungsbestätigung mit Reservierungsnummer wird generiert | Normalerweise würde eine Mail mit der Buchungsbestätigung kommen | Offen | - |
| AV3 | Benutzerregistrierung und Login-Funktionalität | Neuer Account wird erstellt und Login funktioniert mit korrekten Zugangsdaten | Account wurde erfolgreich erstellt und man ist angemeldet | Erfolgreich | - |
| AV4 | Stornierung einer bestehenden Buchung | Buchung wird storniert, Bestätigungsmail versendet, evtl. Storniergebühren berechnet | Es wurden keine Storniergebühren berechnet | Fehler | Fehler im Code beim berechnen -> Vielleicht vergessen die Storniergebühren dazu zu rechnen |
| AV5 | Preisberechnung mit verschiedenen Optionen (Versicherung, Zusatzfahrer, GPS) | Gesamtpreis wird korrekt berechnet inkl. aller gewählten Extras und Steuern | Der Gesamtpreis wurde erfolgreich und korrekt berechnet | Erfolgreich | - |

## Übung 3:

### Black-Box Testfälle (Benutzersicht):

| Test-ID | Testfall | Eingabe | Erwartete Ausgabe | Kategorie |
|---------|----------|---------|-------------------|-----------|
| BB1 | Konto auswählen | Gültige Kontonummer (1-5) | Kontoinformationen werden angezeigt | Navigation |
| BB2 | Alle Konten anzeigen | "a" eingeben | Liste aller existierenden Konten | Datenabfrage |
| BB3 | Geld einzahlen | "e" + gültiger Betrag | Kontostand wird um Betrag erhöht | Transaktion |
| BB4 | Geld abheben (genügend Guthaben) | "a" + Betrag ≤ Kontostand | Kontostand wird reduziert | Transaktion |
| BB5 | Geld abheben (ungenügend Guthaben) | "a" + Betrag > Kontostand | Fehlermeldung, Kontostand unverändert | Fehlerbehandlung |
| BB6 | Geld überweisen (gleiche Währung) | "ü" + Zielkonto + Betrag | Überweisung erfolgreich, beide Kontostände aktualisiert | Transaktion |
| BB7 | Geld überweisen (verschiedene Währungen) | "ü" + Zielkonto + Betrag | Betrag wird umgerechnet und überwiesen | Währungskonversion |
| BB8 | Kontostand abfragen | "k" eingeben | Aktueller Kontostand wird angezeigt | Datenabfrage |
| BB9 | Konto löschen | "l" + "j" zur Bestätigung | Konto wird aus Liste entfernt | Account Management |
| BB10 | Wechselkurs abfragen | "w" + "CHF USD" | Aktueller Wechselkurs wird von API abgerufen | API Integration |
| BB11 | Neues Konto erstellen | "e" + Name + Währung | Neues Konto wird erstellt und angezeigt | Account Management |
| BB12 | Ungültige Eingaben | Buchstaben statt Zahlen bei Beträgen | Fehlermeldung und Wiederholung der Eingabe | Input Validation |

### White-Box Testfälle (Code-Ebene):

| Method-ID | Methode/Klasse | Zu testende Logik | Testarten |
|-----------|----------------|-------------------|-----------|
| WB1 | `Account.deposit()` | Betrag zum Kontostand addieren | Unit Test, Boundary Tests |
| WB2 | `Account.withdraw()` | Abhebung mit Guthabenprüfung | Unit Test, Exception Tests |
| WB3 | `Bank.createAccount()` | Konto erstellen und zur Liste hinzufügen | Unit Test |
| WB4 | `Bank.getAccount()` | Konto anhand ID finden | Unit Test, Edge Cases |
| WB5 | `Bank.deleteAccount()` | Konto aus ArrayList entfernen | Unit Test |
| WB6 | `Counter.transferAmount()` | Geldtransfer zwischen Konten | Integration Test |
| WB7 | `Counter.convertCurrency()` | Währungsumrechnung mit festen Raten | Unit Test, Mathematical Tests |
| WB8 | `ExchangeRateOkhttp.getExchangeRate()` | API-Aufruf und JSON-Parsing | Integration Test, Mock Tests |
| WB9 | `Counter.chooseAccount()` | Eingabevalidierung mit RegEx | Unit Test, Input Validation |

### Code-Verbesserungsvorschläge:

- **Input Validation**: Bessere Validierung für negative Beträge und Null-Werte
- **Method Responsibility**: `Counter.java` macht zu viele verschiedene Dinge
- **Naming**: Inkonsistente Sprache (Deutsch/Englisch gemischt)
- **Code Duplication**: Eingabevalidierung wird mehrfach wiederholt

---