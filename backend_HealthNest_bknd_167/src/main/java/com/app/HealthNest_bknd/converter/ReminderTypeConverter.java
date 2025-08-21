package com.app.HealthNest_bknd.converter;

import com.app.HealthNest_bknd.enums.ReminderType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class ReminderTypeConverter implements AttributeConverter<ReminderType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ReminderType reminderType) {
        return reminderType != null ? reminderType.ordinal() : null;
    }

    @Override
    public ReminderType convertToEntityAttribute(Integer dbData) {
		return ReminderType.getReminderType(dbData);
    }
}
