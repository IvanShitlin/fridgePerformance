package com.higeco.fridgeperformance.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@Slf4j
@Component
public class DateValidator {
    
    public void validate(String from, String to) {

        var d1 = validateIfDateCanBeParsed(from);
        var d2 = validateIfDateCanBeParsed(to);

        if (d1.isAfter(d2)) {
            throw new InvalidDateException("The given dates are overlapping");
        }
    }

    private static ZonedDateTime validateIfDateCanBeParsed(String from) {
        Objects.requireNonNull(from, "Date cannot be null");
        if (from.trim().isEmpty()) {
            throw new InvalidDateException("Date cannot be empty");
        }
        try {
            return ZonedDateTime.parse(from);
        } catch (DateTimeParseException e) {
            log.info(e.getMessage());
            throw new InvalidDateException("Invalid date: " + from);
        }
    }


}
