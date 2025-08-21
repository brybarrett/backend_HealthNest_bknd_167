package com.app.HealthNest_bknd.model;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


 
import com.app.HealthNest_bknd.model.Notification;
import com.app.HealthNest_bknd.model.Qualification;
import com.app.HealthNest_bknd.model.Slot;
import com.app.HealthNest_bknd.model.Doctor;
import com.app.HealthNest_bknd.model.Weekday;
import com.app.HealthNest_bknd.model.Specialty;
import com.app.HealthNest_bknd.model.Prescription;
import com.app.HealthNest_bknd.model.Medication;
import com.app.HealthNest_bknd.model.Reminder;
import com.app.HealthNest_bknd.model.SpecialtyCategory;
import com.app.HealthNest_bknd.model.Document;
import com.app.HealthNest_bknd.model.Availability;
import com.app.HealthNest_bknd.model.Chatroom;
import com.app.HealthNest_bknd.model.Patient;
import com.app.HealthNest_bknd.model.Review;
import com.app.HealthNest_bknd.model.ChatMessage;
import com.app.HealthNest_bknd.model.Clinic;
import com.app.HealthNest_bknd.model.Appointment;
import com.app.HealthNest_bknd.model.Payment;
import com.app.HealthNest_bknd.model.complex.Vitals;
import com.app.HealthNest_bknd.model.complex.MedicalInformation;
import com.app.HealthNest_bknd.model.complex.Address;
import com.app.HealthNest_bknd.enums.Gender;
import com.app.HealthNest_bknd.enums.DoctorType;
import com.app.HealthNest_bknd.enums.AppointmentStatus;
import com.app.HealthNest_bknd.enums.PaymentStatus;
import com.app.HealthNest_bknd.enums.NotificationType;
import com.app.HealthNest_bknd.enums.PrescribedItemType;
import com.app.HealthNest_bknd.enums.BookingStatus;
import com.app.HealthNest_bknd.enums.BeforeOrAfter;
import com.app.HealthNest_bknd.enums.ReminderType;
import com.app.HealthNest_bknd.enums.Day;
import com.app.HealthNest_bknd.enums.BookingType;
import com.app.HealthNest_bknd.enums.PaymentMethod;
import com.app.HealthNest_bknd.converter.BookingStatusConverter;
import com.app.HealthNest_bknd.converter.PrescribedItemTypeConverter;
import com.app.HealthNest_bknd.converter.PaymentMethodConverter;
import com.app.HealthNest_bknd.converter.AppointmentStatusConverter;
import com.app.HealthNest_bknd.converter.DayConverter;
import com.app.HealthNest_bknd.converter.BookingTypeConverter;
import com.app.HealthNest_bknd.converter.BeforeOrAfterConverter;
import com.app.HealthNest_bknd.converter.ReminderTypeConverter;
import com.app.HealthNest_bknd.converter.PaymentStatusConverter;
import com.app.HealthNest_bknd.converter.GenderConverter;
import com.app.HealthNest_bknd.converter.NotificationTypeConverter;
import com.app.HealthNest_bknd.converter.DoctorTypeConverter;
import com.app.HealthNest_bknd.converter.DurationConverter;
import com.app.HealthNest_bknd.converter.UUIDToByteConverter;
import com.app.HealthNest_bknd.converter.UUIDToStringConverter;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Duration;
import java.util.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Lob;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmMediaStream;

@Entity(name = "Appointment")
@Table(name = "\"Appointment\"", schema =  "\"healthnest_bknd_127\"")
@Data
                        
public class Appointment {
	public Appointment () {   
  }
	  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"AppointmentId\"", nullable = true )
  private Long appointmentId;
	  
  @Column(name = "\"Date\"", nullable = true )
  @Temporal(value = TemporalType.DATE)
  private Date date;  
  
	  
  @Column(name = "\"Notes\"", nullable = true )
  private String notes;
  
	  
  @Column(name = "\"StartTime\"", nullable = true )
  private Time startTime;
  
	  
  @Column(name = "\"Status\"", nullable = true)
  @Enumerated(value = EnumType.ORDINAL)
  @Convert(converter = AppointmentStatusConverter.class)
  private AppointmentStatus status;
  
	  
  @Column(name = "\"Complaints\"", nullable = true )
  private String complaints;
  
	  
  @Column(name = "\"EndTime\"", nullable = true )
  private Time endTime;
  
  
  
  
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"AppointmentBookedBy\"", referencedColumnName = "\"PatientId\"", insertable = false, updatable = false)
	private Patient bookedBy;
	
	@Column(name = "\"AppointmentBookedBy\"")
	private Long appointmentBookedBy;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"AppointmentConsultedDoctor\"", referencedColumnName = "\"DoctorId\"", insertable = false, updatable = false)
	private Doctor consultedDoctor;
	
	@Column(name = "\"AppointmentConsultedDoctor\"")
	private Long appointmentConsultedDoctor;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"AppointmentAppointmentReview\"", referencedColumnName = "\"ReviewId\"", insertable = false, updatable = false)
	private Review appointmentReview;
	
	@Column(name = "\"AppointmentAppointmentReview\"")
	private Long appointmentAppointmentReview;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"AppointmentIssuedPrescription\"", referencedColumnName = "\"PrescriptionId\"", insertable = false, updatable = false)
	private Prescription issuedPrescription;
	
	@Column(name = "\"AppointmentIssuedPrescription\"")
	private Long appointmentIssuedPrescription;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"AppointmentPaymentDetails\"", referencedColumnName = "\"PaymentId\"", insertable = false, updatable = false)
	private Payment paymentDetails;
	
	@Column(name = "\"AppointmentPaymentDetails\"")
	private Long appointmentPaymentDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"AppointmentAppointmentChatroom\"", referencedColumnName = "\"ChatroomId\"", insertable = false, updatable = false)
	private Chatroom appointmentChatroom;
	
	@Column(name = "\"AppointmentAppointmentChatroom\"")
	private Long appointmentAppointmentChatroom;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"AppointmentReferToOtherDoctor\"", referencedColumnName = "\"DoctorId\"", insertable = false, updatable = false)
	private Doctor referToOtherDoctor;
	
	@Column(name = "\"AppointmentReferToOtherDoctor\"")
	private Long appointmentReferToOtherDoctor;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"AppointmentAllotedSlot\"", referencedColumnName = "\"SlotId\"", insertable = false, updatable = false)
	private Slot allotedSlot;
	
	@Column(name = "\"AppointmentAllotedSlot\"")
	private Long appointmentAllotedSlot;
   
	
@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"AppointmentDocuments\"",
            joinColumns = @JoinColumn( name="\"AppointmentId\""),
            inverseJoinColumns = @JoinColumn( name="\"DocId\""), schema = "\"healthnest_bknd_127\"")
private List<Document> documents = new ArrayList<>();


@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"AppointmentReminders\"",
            joinColumns = @JoinColumn( name="\"AppointmentId\""),
            inverseJoinColumns = @JoinColumn( name="\"ReminderId\""), schema = "\"healthnest_bknd_127\"")
private List<Reminder> reminders = new ArrayList<>();
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "Appointment [" 
  + "AppointmentId= " + appointmentId  + ", " 
  + "Date= " + date  + ", " 
  + "Notes= " + notes  + ", " 
  + "StartTime= " + startTime  + ", " 
  + "Status= " + status  + ", " 
  + "Complaints= " + complaints  + ", " 
  + "EndTime= " + endTime 
 + "]";
	}
	
}
