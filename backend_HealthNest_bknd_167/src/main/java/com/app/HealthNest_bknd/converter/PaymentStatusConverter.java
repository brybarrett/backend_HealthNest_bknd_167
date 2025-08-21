package com.app.HealthNest_bknd.converter;

import com.app.HealthNest_bknd.enums.PaymentStatus;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentStatus paymentStatus) {
        return paymentStatus != null ? paymentStatus.ordinal() : null;
    }

    @Override
    public PaymentStatus convertToEntityAttribute(Integer dbData) {
		return PaymentStatus.getPaymentStatus(dbData);
    }
}
