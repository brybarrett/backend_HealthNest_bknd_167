package com.app.HealthNest_bknd.converter;

import com.app.HealthNest_bknd.enums.BeforeOrAfter;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class BeforeOrAfterConverter implements AttributeConverter<BeforeOrAfter, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BeforeOrAfter beforeOrAfter) {
        return beforeOrAfter != null ? beforeOrAfter.ordinal() : null;
    }

    @Override
    public BeforeOrAfter convertToEntityAttribute(Integer dbData) {
		return BeforeOrAfter.getBeforeOrAfter(dbData);
    }
}
