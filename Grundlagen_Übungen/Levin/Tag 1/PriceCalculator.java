public class PriceCalculator {

    // ORIGINAL CODE
    double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
        double addon_discount;
        double result;

        if (extras >= 3)
            addon_discount = 10;
        else if (extras >= 5)    // FEHLER: Diese Zeile wird nie erreicht!
            addon_discount = 15;
        else
            addon_discount = 0;

        if (discount > addon_discount)    // FEHLER: Händlerrabatt wird fälschlich auf Extras angewendet
            addon_discount = discount;

        result = baseprice/100.0 * (100-discount) + specialprice
                + extraprice/100.0 * (100-addon_discount);

        return result;
    }

    // NEUE VERSION
    double calculatePriceCorrected(double baseprice, double specialprice, double extraprice, int extras, double discount) {
        double addon_discount;

        // FEHLER BEHOBEN: Richtige Reihenfolge der Bedingungen
        if (extras >= 5)
            addon_discount = 15;
        else if (extras >= 3)
            addon_discount = 10;
        else
            addon_discount = 0;

        // FEHLER BEHOBEN: Rabatte werden getrennt angewendet
        double result = baseprice * (100 - discount) / 100.0 + specialprice
                + extraprice * (100 - addon_discount) / 100.0;

        return result;
    }

    // TESTTREIBER für ORIGINAL CODE
    boolean test_calculate_price() {
        boolean test_ok = true;
        double price;
        double expected;
        double tolerance = 0.01;

        System.out.println("=== Starte Tests für calculatePrice (ORIGINAL MIT FEHLERN) ===");

        // Test 1: Grundfall ohne Extras und ohne Rabatt
        price = calculatePrice(1000.0, 200.0, 300.0, 0, 0.0);
        expected = 1000.0 + 200.0 + 300.0; // 1500.0
        if (Math.abs(price - expected) > tolerance) {
            System.out.println("Test 1 FEHLER: Erwartet " + expected + ", erhalten " + price);
            test_ok = false;
        } else {
            System.out.println("Test 1 OK: " + price);
        }

        // Test 2: Mit Händlerrabatt (15%)
        price = calculatePrice(1000.0, 200.0, 300.0, 0, 15.0);
        expected = 1000.0 * 0.85 + 200.0 + 300.0;
        if (Math.abs(price - expected) > tolerance) {
            System.out.println("Test 2 FEHLER: Erwartet " + expected + ", erhalten " + price + " (Händlerrabatt fälschlich auch auf Extras)");
            test_ok = false;
        } else {
            System.out.println("Test 2 OK: " + price);
        }

        // Test 3: Mit 3 Extras (10% Rabatt auf Extras)
        price = calculatePrice(1000.0, 200.0, 300.0, 3, 0.0);
        expected = 1000.0 + 200.0 + 300.0 * 0.90; // 1000 + 200 + 270 = 1470.0
        if (Math.abs(price - expected) > tolerance) {
            System.out.println("Test 3 FEHLER: Erwartet " + expected + ", erhalten " + price);
            test_ok = false;
        } else {
            System.out.println("Test 3 OK: " + price);
        }

        // Test 4: Mit 5 Extras (sollte 15% Rabatt sein)
        price = calculatePrice(1000.0, 200.0, 300.0, 5, 0.0);
        expected = 1000.0 + 200.0 + 300.0 * 0.85; // 1000 + 200 + 255 = 1455.0
        if (Math.abs(price - expected) > tolerance) {
            System.out.println("Test 4 FEHLER: Erwartet " + expected + ", erhalten " + price + " (Nur 10% statt 15% Rabatt)");
            test_ok = false;
        } else {
            System.out.println("Test 4 OK: " + price);
        }

        // Test 5: Händlerrabatt höher als Extra-Rabatt
        price = calculatePrice(1000.0, 200.0, 300.0, 3, 20.0);
        expected = 1000.0 * 0.80 + 200.0 + 300.0 * 0.80; // 800 + 200 + 240 = 1240.0
        if (Math.abs(price - expected) > tolerance) {
            System.out.println("Test 5 FEHLER: Erwartet " + expected + ", erhalten " + price);
            test_ok = false;
        } else {
            System.out.println("Test 5 OK: " + price);
        }

        // Test 6: Grenzwerttest - genau 2 Extras (kein Rabatt)
        price = calculatePrice(1000.0, 200.0, 300.0, 2, 0.0);
        expected = 1000.0 + 200.0 + 300.0; // 1500.0
        if (Math.abs(price - expected) > tolerance) {
            System.out.println("Test 6 FEHLER: Erwartet " + expected + ", erhalten " + price);
            test_ok = false;
        } else {
            System.out.println("Test 6 OK: " + price);
        }

        // Test 7: Grenzwerttest - genau 4 Extras (10% Rabatt)
        price = calculatePrice(1000.0, 200.0, 300.0, 4, 0.0);
        expected = 1000.0 + 200.0 + 300.0 * 0.90; // 1470.0
        if (Math.abs(price - expected) > tolerance) {
            System.out.println("Test 7 FEHLER: Erwartet " + expected + ", erhalten " + price);
            test_ok = false;
        } else {
            System.out.println("Test 7 OK: " + price);
        }

        System.out.println("=== Tests für ORIGINAL CODE abgeschlossen ===");
        System.out.println("Alle Tests bestanden: " + test_ok);

        return test_ok;
    }

    // TESTTREIBER für NEUEN CODE
    boolean test_calculate_price_corrected() {
        boolean test_ok = true;
        double price;
        double expected;
        double tolerance = 0.01;

        System.out.println("\n=== Starte Tests für calculatePriceCorrected (KORRIGIERT) ===");

        // Test 1: Grundfall ohne Extras und ohne Rabatt
        price = calculatePriceCorrected(1000.0, 200.0, 300.0, 0, 0.0);
        expected = 1000.0 + 200.0 + 300.0; // 1500.0
        if (Math.abs(price - expected) > tolerance) {
            System.out.println("Test 1 FEHLER: Erwartet " + expected + ", erhalten " + price);
            test_ok = false;
        } else {
            System.out.println("Test 1 OK: " + price);
        }

        // Test 2: Mit Händlerrabatt (15%)
        price = calculatePriceCorrected(1000.0, 200.0, 300.0, 0, 15.0);
        expected = 1000.0 * 0.85 + 200.0 + 300.0; // 1350.0
        if (Math.abs(price - expected) > tolerance) {
            System.out.println("Test 2 FEHLER: Erwartet " + expected + ", erhalten " + price);
            test_ok = false;
        } else {
            System.out.println("Test 2 OK: " + price);
        }

        // Test 3: Mit 3 Extras (10% Rabatt auf Extras)
        price = calculatePriceCorrected(1000.0, 200.0, 300.0, 3, 0.0);
        expected = 1000.0 + 200.0 + 300.0 * 0.90; // 1470.0
        if (Math.abs(price - expected) > tolerance) {
            System.out.println("Test 3 FEHLER: Erwartet " + expected + ", erhalten " + price);
            test_ok = false;
        } else {
            System.out.println("Test 3 OK: " + price);
        }

        // Test 4: Mit 5 Extras (15% Rabatt)
        price = calculatePriceCorrected(1000.0, 200.0, 300.0, 5, 0.0);
        expected = 1000.0 + 200.0 + 300.0 * 0.85; // 1455.0
        if (Math.abs(price - expected) > tolerance) {
            System.out.println("Test 4 FEHLER: Erwartet " + expected + ", erhalten " + price);
            test_ok = false;
        } else {
            System.out.println("Test 4 OK: " + price);
        }

        // Test 5: Händler- und Extra-Rabatt getrennt
        price = calculatePriceCorrected(1000.0, 200.0, 300.0, 3, 20.0);
        expected = 1000.0 * 0.80 + 200.0 + 300.0 * 0.90; // 800 + 200 + 270 = 1270.0
        if (Math.abs(price - expected) > tolerance) {
            System.out.println("Test 5 FEHLER: Erwartet " + expected + ", erhalten " + price);
            test_ok = false;
        } else {
            System.out.println("Test 5 OK: " + price);
        }

        System.out.println("=== Tests für KORRIGIERTEN CODE abgeschlossen ===");
        System.out.println("Alle Tests bestanden: " + test_ok);

        return test_ok;
    }

    // Main-Methode zum Ausführen aller Tests
    public static void main(String[] args) {
        PriceCalculator calculator = new PriceCalculator();

        // Teste den ORIGINAL CODE (mit Fehlern)
        boolean originalTestsOk = calculator.test_calculate_price();

        // Teste den KORRIGIERTEN CODE
        boolean correctedTestsOk = calculator.test_calculate_price_corrected();

        System.out.println("\n=== ZUSAMMENFASSUNG ===");
        System.out.println("Original Code Tests erfolgreich: " + originalTestsOk + " (Erwartet: false - wegen Fehlern)");
        System.out.println("Korrigierter Code Tests erfolgreich: " + correctedTestsOk + " (Erwartet: true)");

        System.out.println("\n=== GEFUNDENE FEHLER IM ORIGINAL CODE ===");
        System.out.println("FEHLER 1: if (extras >= 3) steht vor if (extras >= 5)");
        System.out.println("  → Bei 5+ Extras wird nur 10% statt 15% Rabatt gewährt");
        System.out.println("FEHLER 2: if (discount > addon_discount) addon_discount = discount");
        System.out.println("  → Händlerrabatt wird fälschlich auch auf Extras angewendet");
    }
}