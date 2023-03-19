package test.example.leader_it.converters;

import javax.persistence.AttributeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter implements AttributeConverter<String, Date> {

    @Override
    public Date convertToDatabaseColumn(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date date;
        try {
            date = formatter.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    @Override
    public String convertToEntityAttribute(Date date) {
        return  new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
