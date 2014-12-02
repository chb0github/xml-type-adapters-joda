package org.bongiorno.dto.typeadapters.joda;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{


    public static final String DEFAULT_FORMAT = "MM/dd/yyyy";

    private DateTimeFormatter formatter;

    public LocalDateAdapter() {
        this(DEFAULT_FORMAT);
    }

    public LocalDateAdapter(String format) {
        formatter = DateTimeFormat.forPattern(format);
    }

    @Override
    public LocalDate unmarshal(String input) throws Exception {
        return transform(input);
    }

//    @Override
    public LocalDate transform(String input) {
        try {
            return LocalDate.parse(input, formatter);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage() + "; valid format example: " + formatter.print(new LocalDate()), e);
        }
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return formatter.print(v);
    }
}
