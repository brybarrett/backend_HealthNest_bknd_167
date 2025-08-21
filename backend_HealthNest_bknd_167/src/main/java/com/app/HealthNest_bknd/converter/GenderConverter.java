package com.app.HealthNest_bknd.converter;

import com.app.HealthNest_bknd.enums.Gender;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class GenderConverter implements AttributeConverter<Gender, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        return gender != null ? gender.ordinal() : null;
    }

    @Override
    public Gender convertToEntityAttribute(Integer dbData) {
		return Gender.getGender(dbData);
    }
}
