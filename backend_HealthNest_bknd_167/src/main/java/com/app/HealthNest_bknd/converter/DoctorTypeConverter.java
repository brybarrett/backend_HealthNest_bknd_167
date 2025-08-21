package com.app.HealthNest_bknd.converter;

import com.app.HealthNest_bknd.enums.DoctorType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class DoctorTypeConverter implements AttributeConverter<DoctorType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DoctorType doctorType) {
        return doctorType != null ? doctorType.ordinal() : null;
    }

    @Override
    public DoctorType convertToEntityAttribute(Integer dbData) {
		return DoctorType.getDoctorType(dbData);
    }
}
