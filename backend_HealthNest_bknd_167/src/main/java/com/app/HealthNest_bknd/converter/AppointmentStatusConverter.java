package com.app.HealthNest_bknd.converter;

import com.app.HealthNest_bknd.enums.AppointmentStatus;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class AppointmentStatusConverter implements AttributeConverter<AppointmentStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AppointmentStatus appointmentStatus) {
        return appointmentStatus != null ? appointmentStatus.ordinal() : null;
    }

    @Override
    public AppointmentStatus convertToEntityAttribute(Integer dbData) {
		return AppointmentStatus.getAppointmentStatus(dbData);
    }
}
