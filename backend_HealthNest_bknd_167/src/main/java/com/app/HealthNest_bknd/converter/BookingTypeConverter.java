package com.app.HealthNest_bknd.converter;

import com.app.HealthNest_bknd.enums.BookingType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class BookingTypeConverter implements AttributeConverter<BookingType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BookingType bookingType) {
        return bookingType != null ? bookingType.ordinal() : null;
    }

    @Override
    public BookingType convertToEntityAttribute(Integer dbData) {
		return BookingType.getBookingType(dbData);
    }
}
