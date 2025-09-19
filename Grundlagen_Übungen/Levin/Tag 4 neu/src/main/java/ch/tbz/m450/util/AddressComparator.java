package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;

import java.util.Comparator;
import java.util.Date;

/**
 * Compares by lastName, then firstName, then registrationDate, then id.
 * Null-safe; null strings and dates are sorted last.
 */
public class AddressComparator implements Comparator<Address> {

    private static final Comparator<String> STR_CI = Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER);
    private static final Comparator<Date> DATE_CMP = Comparator.nullsLast(Comparator.naturalOrder());

    @Override
    public int compare(Address a1, Address a2) {
        if (a1 == a2) return 0;
        if (a1 == null) return 1;
        if (a2 == null) return -1;

        int byLast = STR_CI.compare(a1.getLastname(), a2.getLastname());
        if (byLast != 0) return byLast;

        int byFirst = STR_CI.compare(a1.getFirstname(), a2.getFirstname());
        if (byFirst != 0) return byFirst;

        int byReg = DATE_CMP.compare(a1.getRegistrationDate(), a2.getRegistrationDate());
        if (byReg != 0) return byReg;

        return Integer.compare(a1.getId(), a2.getId());
    }
}
