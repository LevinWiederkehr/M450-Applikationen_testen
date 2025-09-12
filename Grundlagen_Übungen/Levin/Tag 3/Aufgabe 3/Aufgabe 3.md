# Banken-Simulation - Dokumentation

## Überblick
- Bankensystem mit verschiedenen Kontotypen
- Java + Maven, TBZ-IT M450 Unit Testing

## Klassen

### Bank
- `TreeMap<String, Account>` für Kontoverwaltung
- ID-Generierung: S-1000, Y-1001, P-1002 (automatisch)
- Factory Methods: `createSavingsAccount()`, `createPromoYouthSavingsAccount()`, `createSalaryAccount(creditLimit)`
- CRUD: `deposit()`, `withdraw()`, `getBalance()`, `print()`
- Reports: `printTop5()`, `printBottom5()` mit Comparatoren

### Account (abstrakt)
- Attribute: `id`, `balance` (Millirappen), `bookings` (ArrayList)
- `deposit()`, `withdraw()` (abstrakt), `canTransact()`, `print()`
- Validierung: Negative Beträge verboten, chronologische Transaktionen

### Kontotypen
- **SavingsAccount**: Sparkonto (S-)
- **PromoYouthSavingsAccount**: Jugendsparkonto (Y-)  
- **SalaryAccount**: Lohnkonto mit Kreditlimit (P-)

### Hilfklassen
- **Booking**: `date`, `amount`, `print()`
- **BankUtils**: `formatBankDate()`, `formatAmount()`, DecimalFormat-Konstanten
- **Comparatoren**: Balance-Sortierung aufsteigend/absteigend

## Datenformate
- **Geld**: long in Millirappen (1 CHF = 100'000)
- **Datum**: int-Tage seit 1970 (360-Tage-Jahr)
- **Kontonummer**: String mit Präfix

## Geschäftsregeln
- Kreditlimit nur bei SalaryAccount (muss negativ sein)
- Chronologische Transaktionsreihenfolge zwingend
- Bankbilanz = Summe negativer Kontostände
- TreeMap sortiert Konten automatisch

## Design Patterns  
- Template Method (Account)
- Factory Methods (Bank)
- Strategy (Comparatoren)