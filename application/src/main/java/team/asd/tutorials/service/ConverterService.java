package team.asd.tutorials.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class ConverterService implements IsConverterService {

    @Override
    public String convertIntegerIntoString(Integer value) {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }

    @Override
    public Integer convertStringIntoInteger(String value) {
        if (value == null) {
            return null;
        }
        return Integer.parseInt(value);
    }

    @Override
    public Double convertStringIntoDouble(String value) {
        if (value == null) {
            return null;
        }
        return Double.parseDouble(value);
    }

    @Override
    public LocalDate convertStringIntoLocalDate(String dateString) throws DateTimeParseException {
        if (dateString == null) {
            return null;
        }
        return LocalDate.parse(dateString);
    }
}
