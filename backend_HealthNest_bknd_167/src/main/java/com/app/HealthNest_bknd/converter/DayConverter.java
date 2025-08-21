package com.app.HealthNest_bknd.converter;

import com.app.HealthNest_bknd.enums.Day;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class DayConverter implements AttributeConverter<Day, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Day day) {
        return day != null ? day.ordinal() : null;
    }

    @Override
    public Day convertToEntityAttribute(Integer dbData) {
		return Day.getDay(dbData);
    }
}
