package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;

import java.util.*;
import java.util.function.Function;

/**
 * Dynamic comparator: order by any sequence of fields, asc/desc.
 */
public class DynamicAddressComparator implements Comparator<Address> {

    public enum Field { LASTNAME, FIRSTNAME, PHONENUMBER, REGISTRATION_DATE, ID }
    public record SortKey(Field field, boolean asc) {}

    private final List<SortKey> keys;

    public DynamicAddressComparator(List<SortKey> keys) {
        if (keys == null || keys.isEmpty()) {
            this.keys = List.of(new SortKey(Field.LASTNAME, true), new SortKey(Field.FIRSTNAME, true));
        } else {
            this.keys = List.copyOf(keys);
        }
    }

    @Override
    public int compare(Address a1, Address a2) {
        Comparator<Address> cmp = (x,y) -> 0;
        for (SortKey k : keys) {
            Comparator<Address> c = switch (k.field()) {
                case LASTNAME -> comparingNullable(Address::getLastname, String.CASE_INSENSITIVE_ORDER);
                case FIRSTNAME -> comparingNullable(Address::getFirstname, String.CASE_INSENSITIVE_ORDER);
                case PHONENUMBER -> comparingNullable(Address::getPhonenumber, String.CASE_INSENSITIVE_ORDER);
                case REGISTRATION_DATE -> comparingNullable(Address::getRegistrationDate, Comparator.naturalOrder());
                case ID -> Comparator.comparingInt(Address::getId);
            };
            if (!k.asc()) c = c.reversed();
            cmp = cmp.thenComparing(c);
        }
        return cmp.compare(a1, a2);
    }

    private static <T, U> Comparator<T> comparingNullable(Function<T, U> f, Comparator<? super U> cmp) {
        return Comparator.comparing(f, Comparator.nullsLast(cmp));
    }
}
