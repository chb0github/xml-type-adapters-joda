package org.bongiorno.dto.typeadapters.joda;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeAdapter extends XmlAdapter<String, DateTime>  {

    private final String PATTERN = "MM/dd/yyyy HH:mm:ss";
    private DateTimeFormatter formatter = DateTimeFormat.forPattern(PATTERN);

    private DateTimeZone timeZone;

    public DateTimeAdapter(){
        formatter = ISODateTimeFormat.dateTime();
    }

    public DateTimeAdapter(String format, String timeZone) {
        formatter = DateTimeFormat.forPattern(format);
        this.timeZone = DateTimeZone.forID(timeZone);
    }

    public DateTimeAdapter(String format) {
        formatter = DateTimeFormat.forPattern(format);
        this.timeZone = DateTimeZone.getDefault();
    }

    @Override
    public DateTime unmarshal(String v) {
        return transform(v);
    }

    public DateTime reverse(String input) {
        return transform(input);
    }

    public String apply(DateTime input) {
        return marshal(input);
    }


    public DateTime transform(String s) {
        try {
            return DateTime.parse(s,formatter);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage() + "; valid format example: '" + formatter.print(DateTime.now()) +"'", e);
        }
    }
    @Override
    public String marshal(DateTime v) {
        return formatter.print(v.toDateTime(timeZone));
    }

    public DateTime normalize(DateTime in){
        return unmarshal(marshal(in));
    }


    public static DateTime normalizeDate(DateTime in) {
        return new DateTimeAdapter().normalize(in);
    }
}
