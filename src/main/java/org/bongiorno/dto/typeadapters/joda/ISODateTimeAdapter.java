package org.bongiorno.dto.typeadapters.joda;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ISODateTimeAdapter extends XmlAdapter<String, DateTime> {

    @Override
    public DateTime unmarshal(String input) throws Exception {
        return new DateTime(input);
    }

    @Override
    public String marshal(DateTime input) throws Exception {
        return input.toString();
    }
}
