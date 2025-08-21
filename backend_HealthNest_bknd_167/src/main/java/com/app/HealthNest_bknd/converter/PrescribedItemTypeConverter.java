package com.app.HealthNest_bknd.converter;

import com.app.HealthNest_bknd.enums.PrescribedItemType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class PrescribedItemTypeConverter implements AttributeConverter<PrescribedItemType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PrescribedItemType prescribedItemType) {
        return prescribedItemType != null ? prescribedItemType.ordinal() : null;
    }

    @Override
    public PrescribedItemType convertToEntityAttribute(Integer dbData) {
		return PrescribedItemType.getPrescribedItemType(dbData);
    }
}
