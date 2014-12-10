package org.bongiorno.dto.typeadapters.joda;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author chribong
 */
public class AdapterTests {


    @Test
    public void testDateAdapter() throws Exception {
        DateTimeAdapter da = new DateTimeAdapter("MM/dd/yyyy HH:mm", null);
        DateTime first = da.unmarshal(da.marshal(DateTime.now()));
        DateTime second = da.unmarshal(da.marshal(first));
        assertEquals(first, second);

    }

    @Test
    public void testLocalDateAdapter() throws Exception {
        LocalDateAdapter adapter = new LocalDateAdapter("MM/dd/yyyy");

        String string = "09/20/1984";
        LocalDate localDate = new LocalDate(1984, 9, 20);

        assertEquals(localDate, adapter.unmarshal(string));
        assertEquals(string, adapter.marshal(localDate));
    }

    @Test
    public void testLocalTimeAdapter() throws Exception {
        LocalTimeAdapter adapter = new LocalTimeAdapter("HH:mm");

        String string = "16:20";
        LocalTime localTime = new LocalTime(16, 20);

        assertEquals(localTime, adapter.unmarshal(string));
        assertEquals(string, adapter.marshal(localTime));
    }


    @Test
    public void testISOLocalDateAdapter() throws Exception {
        LocalDateAdapter adapter = new LocalDateAdapter();

        LocalDate expectedDate = new LocalDate(1976, 9, 10);
        String string = "1976-09-10";

        assertEquals(expectedDate, adapter.unmarshal(string));
        assertEquals(string, adapter.marshal(expectedDate));
    }

    @Test
    public void testISODateTimeAdapter() throws Exception {
        DateTimeAdapter adapter = new DateTimeAdapter();

        DateTime expectedDate = new DateTime(1976, 9, 10, 9, 17);
        String string = "1976-09-10T09:17:00.000-07:00";

        assertEquals(expectedDate, adapter.unmarshal(string));
        assertEquals(string, adapter.marshal(expectedDate));
    }

    @Test
    public void testNoTISODateTimeAdapter() throws Exception {
        NoTISODateTimeAdapter adapter = new NoTISODateTimeAdapter();

        DateTime expectedDate = new DateTime(1976, 9, 10, 9, 17);
        String string = "1976-09-10 09:17:00.000-07:00";

        assertEquals(expectedDate, adapter.unmarshal(string));
        assertEquals(string, adapter.marshal(expectedDate));
    }
}
