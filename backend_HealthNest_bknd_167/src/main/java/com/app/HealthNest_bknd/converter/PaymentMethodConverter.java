package com.app.HealthNest_bknd.converter;

import com.app.HealthNest_bknd.enums.PaymentMethod;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class PaymentMethodConverter implements AttributeConverter<PaymentMethod, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentMethod paymentMethod) {
        return paymentMethod != null ? paymentMethod.ordinal() : null;
    }

    @Override
    public PaymentMethod convertToEntityAttribute(Integer dbData) {
		return PaymentMethod.getPaymentMethod(dbData);
    }
}
