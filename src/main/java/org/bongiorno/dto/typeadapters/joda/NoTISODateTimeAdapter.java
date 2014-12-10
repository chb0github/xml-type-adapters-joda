package org.bongiorno.dto.typeadapters.joda;

import org.joda.time.DateTime;

public class NoTISODateTimeAdapter extends DateTimeAdapter {

    @Override
    public DateTime unmarshal(String input) {
        return super.unmarshal(input.replace(' ', 'T'));
    }

    @Override
    public String marshal(DateTime input){
        return super.marshal(input).replace('T', ' ');
    }
}
