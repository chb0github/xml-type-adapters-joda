package org.bongiorno.dto.typeadapters.joda;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {

    private DateTimeFormatter formatter;

    public LocalTimeAdapter(){
        formatter = ISODateTimeFormat.localTimeParser();
    }

    public LocalTimeAdapter(String format) {
        formatter = DateTimeFormat.forPattern(format);
    }

    @Override
    public LocalTime unmarshal(String v) throws Exception {
        try {
            return LocalTime.parse(v, formatter);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage() + "; valid format example: " + formatter.print(new LocalTime()), e);
        }
    }

    @Override
    public String marshal(LocalTime v) throws Exception {
        return formatter.print(v);
    }
}
