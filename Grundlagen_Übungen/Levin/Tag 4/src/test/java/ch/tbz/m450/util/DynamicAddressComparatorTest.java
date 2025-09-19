package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

class DynamicAddressComparatorTest {

    @Test
    void sortByPhoneAscThenLastDesc() {
        Address a = new Address(2,"Ali","Baker","079", new Date(1_700_000_000_000L));
        Address b = new Address(1,"Eva","Adams","078", new Date(1_600_000_000_000L));
        Address c = new Address(3,"Bob","Baker","077", new Date(1_800_000_000_000L));

        var cmp = new DynamicAddressComparator(of(
            new DynamicAddressComparator.SortKey(DynamicAddressComparator.Field.PHONENUMBER, true),
            new DynamicAddressComparator.SortKey(DynamicAddressComparator.Field.LASTNAME, false)
        ));
        List<Address> list = new ArrayList<>(of(a,b,c));
        list.sort(cmp);

        assertEquals(of(c, b, a), list); // 077 < 078 < 079
    }

    @Test
    void sortByRegDateDescOnly() {
        Address a = new Address(2,"Ali","Baker","079", new Date(1_700_000_000_000L));
        Address b = new Address(1,"Eva","Adams","078", new Date(1_600_000_000_000L));
        Address c = new Address(3,"Bob","Baker","077", new Date(1_800_000_000_000L));

        var cmp = new DynamicAddressComparator(of(
            new DynamicAddressComparator.SortKey(DynamicAddressComparator.Field.REGISTRATION_DATE, false)
        ));
        List<Address> list = new ArrayList<>(of(a,b,c));
        list.sort(cmp);

        assertEquals(of(c, a, b), list);
    }
}
