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

@Entity(name = "Doctor")
@Table(name = "\"Doctor\"", schema =  "\"healthnest_bknd_127\"")
@Data
                        
public class Doctor {
	public Doctor () {   
  }
	  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"DoctorId\"", nullable = true )
  private Long doctorId;
	  
  @Column(name = "\"FullName\"", nullable = true )
  private String fullName;
  
	  
  @Column(name = "\"Email\"", nullable = true )
  private String email;
  
	  
  @Column(name = "\"Gender\"", nullable = true)
  @Enumerated(value = EnumType.ORDINAL)
  @Convert(converter = GenderConverter.class)
  private Gender gender;
  
	  
  @Column(name = "\"DateOfBirth\"", nullable = true )
  @Temporal(value = TemporalType.DATE)
  private Date dateOfBirth;  
  
	  
  @Column(name = "\"PhoneNumber\"", nullable = true )
  private String phoneNumber;
  
	  
  @Column(name = "\"Title\"", nullable = true )
  private String title;
  
	  
  @Column(name = "\"Experience\"", nullable = true )
  private String experience;
  
	  
  @Column(name = "\"ConsultationFee\"", nullable = true )
  private Double consultationFee;
  
	  
  @Column(name = "\"Type\"", nullable = true)
  @Enumerated(value = EnumType.ORDINAL)
  @Convert(converter = DoctorTypeConverter.class)
  private DoctorType type;
  
	  
  @Column(name = "\"IsApprovedByClinic\"", nullable = true )
  private Boolean isApprovedByClinic;
  
	  
  @Column(name = "\"ReviewCount\"", nullable = true )
  private Integer reviewCount;
  
	  
  @Column(name = "\"OverallRating\"", nullable = true )
  private Integer overallRating;
  
	  
  @Column(name = "\"IsWeekly\"", nullable = true )
  private Boolean isWeekly;
  
	  
  @Column(name = "\"DateOfJoining\"", nullable = true )
  @Temporal(value = TemporalType.DATE)
  private Date dateOfJoining;  
  
	  
  @Embedded
  @Column(name = "\"Address\"")
  @AttributeOverrides({
            	@AttributeOverride(name = "address", column = @Column(name = "\"Address_Address\"")) ,
            	@AttributeOverride(name = "city", column = @Column(name = "\"Address_City\"")) ,
            	@AttributeOverride(name = "state", column = @Column(name = "\"Address_State\"")) ,
            	@AttributeOverride(name = "country", column = @Column(name = "\"Address_Country\"")) ,
            	@AttributeOverride(name = "postalCode", column = @Column(name = "\"Address_PostalCode\""))  }) 
  private Address address;
  
	  
  @Column(name = "\"About\"", nullable = true )
  private String about;
  
  
  
  
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"DoctorDoctorImage\"", referencedColumnName = "\"DocId\"", insertable = false, updatable = false)
	private Document doctorImage;
	
	@Column(name = "\"DoctorDoctorImage\"")
	private Long doctorDoctorImage;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"DoctorSpeciality\"", referencedColumnName = "\"DocSpeId\"", insertable = false, updatable = false)
	private Specialty speciality;
	
	@Column(name = "\"DoctorSpeciality\"")
	private Long doctorSpeciality;
   
	
@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"DoctorQualifications\"",
            joinColumns = @JoinColumn( name="\"DoctorId\""),
            inverseJoinColumns = @JoinColumn( name="\"QualificationId\""), schema = "\"healthnest_bknd_127\"")
private List<Qualification> qualifications = new ArrayList<>();


@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"DoctorPatientAppointments\"",
            joinColumns = @JoinColumn( name="\"DoctorId\""),
            inverseJoinColumns = @JoinColumn( name="\"AppointmentId\""), schema = "\"healthnest_bknd_127\"")
private List<Appointment> patientAppointments = new ArrayList<>();


@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"DoctorAvailabilities\"",
            joinColumns = @JoinColumn( name="\"DoctorId\""),
            inverseJoinColumns = @JoinColumn( name="\"SlotId\""), schema = "\"healthnest_bknd_127\"")
private List<Slot> availabilities = new ArrayList<>();


@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"DoctorPayments\"",
            joinColumns = @JoinColumn( name="\"DoctorId\""),
            inverseJoinColumns = @JoinColumn( name="\"PaymentId\""), schema = "\"healthnest_bknd_127\"")
private List<Payment> payments = new ArrayList<>();


@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"DoctorDoctorChatrooms\"",
            joinColumns = @JoinColumn( name="\"DoctorId\""),
            inverseJoinColumns = @JoinColumn( name="\"ChatroomId\""), schema = "\"healthnest_bknd_127\"")
private List<Chatroom> doctorChatrooms = new ArrayList<>();


@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"DoctorCertifications\"",
            joinColumns = @JoinColumn( name="\"DoctorId\""),
            inverseJoinColumns = @JoinColumn( name="\"DocId\""), schema = "\"healthnest_bknd_127\"")
private List<Document> certifications = new ArrayList<>();


@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"DoctorWeeklyScheduleByDays\"",
            joinColumns = @JoinColumn( name="\"DoctorId\""),
            inverseJoinColumns = @JoinColumn( name="\"WeekdayId\""), schema = "\"healthnest_bknd_127\"")
private List<Weekday> weeklyScheduleByDays = new ArrayList<>();
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "Doctor [" 
  + "DoctorId= " + doctorId  + ", " 
  + "FullName= " + fullName  + ", " 
  + "Email= " + email  + ", " 
  + "Gender= " + gender  + ", " 
  + "DateOfBirth= " + dateOfBirth  + ", " 
  + "PhoneNumber= " + phoneNumber  + ", " 
  + "Title= " + title  + ", " 
  + "Experience= " + experience  + ", " 
  + "ConsultationFee= " + consultationFee  + ", " 
  + "Type= " + type  + ", " 
  + "IsApprovedByClinic= " + isApprovedByClinic  + ", " 
  + "ReviewCount= " + reviewCount  + ", " 
  + "OverallRating= " + overallRating  + ", " 
  + "IsWeekly= " + isWeekly  + ", " 
  + "DateOfJoining= " + dateOfJoining  + ", " 
  + "Address= " + address  + ", " 
  + "About= " + about 
 + "]";
	}
	
}
