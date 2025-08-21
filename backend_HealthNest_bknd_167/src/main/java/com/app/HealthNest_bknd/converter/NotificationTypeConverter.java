package com.app.HealthNest_bknd.converter;

import com.app.HealthNest_bknd.enums.NotificationType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class NotificationTypeConverter implements AttributeConverter<NotificationType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(NotificationType notificationType) {
        return notificationType != null ? notificationType.ordinal() : null;
    }

    @Override
    public NotificationType convertToEntityAttribute(Integer dbData) {
		return NotificationType.getNotificationType(dbData);
    }
}
