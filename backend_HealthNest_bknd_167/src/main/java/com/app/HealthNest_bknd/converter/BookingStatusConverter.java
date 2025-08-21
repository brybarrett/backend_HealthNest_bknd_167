package com.app.HealthNest_bknd.converter;

import com.app.HealthNest_bknd.enums.BookingStatus;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class BookingStatusConverter implements AttributeConverter<BookingStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BookingStatus bookingStatus) {
        return bookingStatus != null ? bookingStatus.ordinal() : null;
    }

    @Override
    public BookingStatus convertToEntityAttribute(Integer dbData) {
		return BookingStatus.getBookingStatus(dbData);
    }
}
