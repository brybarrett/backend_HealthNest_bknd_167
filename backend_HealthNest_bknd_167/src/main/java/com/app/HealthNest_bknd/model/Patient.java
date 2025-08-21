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

@Entity(name = "Patient")
@Table(name = "\"Patient\"", schema =  "\"healthnest_bknd_127\"")
@Data
                        
public class Patient {
	public Patient () {   
  }
	  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"PatientId\"", nullable = true )
  private Long patientId;
	  
  @Column(name = "\"EmergencyContact\"", nullable = true )
  private String emergencyContact;
  
	  
  @Embedded
  @Column(name = "\"Address\"")
  @AttributeOverrides({
            	@AttributeOverride(name = "address", column = @Column(name = "\"Address_Address\"")) ,
            	@AttributeOverride(name = "city", column = @Column(name = "\"Address_City\"")) ,
            	@AttributeOverride(name = "state", column = @Column(name = "\"Address_State\"")) ,
            	@AttributeOverride(name = "country", column = @Column(name = "\"Address_Country\"")) ,
            	@AttributeOverride(name = "postalCode", column = @Column(name = "\"Address_PostalCode\""))  }) 
  private Address address;
  
	  
  @Embedded
  @Column(name = "\"MedicalInformation\"")
  @AttributeOverrides({
            	@AttributeOverride(name = "knownMedicalConditions", column = @Column(name = "\"MedicalInformation_KnownMedicalConditions\"")) ,
            	@AttributeOverride(name = "allergies", column = @Column(name = "\"MedicalInformation_Allergies\"")) ,
            	@AttributeOverride(name = "medications", column = @Column(name = "\"MedicalInformation_Medications\"")) ,
            	@AttributeOverride(name = "familyMedicalHistory", column = @Column(name = "\"MedicalInformation_FamilyMedicalHistory\""))  }) 
  private MedicalInformation medicalInformation;
  
	  
  @Column(name = "\"FullName\"", nullable = true )
  private String fullName;
  
	  
  @Column(name = "\"Age\"", nullable = true )
  private Integer age;
  
	  
  @Column(name = "\"DateOfBirth\"", nullable = true )
  @Temporal(value = TemporalType.DATE)
  private Date dateOfBirth;  
  
	  
  @Column(name = "\"Gender\"", nullable = true)
  @Enumerated(value = EnumType.ORDINAL)
  @Convert(converter = GenderConverter.class)
  private Gender gender;
  
	  
  @Column(name = "\"PhoneNumber\"", nullable = true )
  private String phoneNumber;
  
	  
  @Column(name = "\"Email\"", nullable = true )
  private String email;
  
  
  
  
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"PatientProfilePicture\"", referencedColumnName = "\"DocId\"", insertable = false, updatable = false)
	private Document profilePicture;
	
	@Column(name = "\"PatientProfilePicture\"")
	private Long patientProfilePicture;
   
	
@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"PatientAppointments\"",
            joinColumns = @JoinColumn( name="\"PatientId\""),
            inverseJoinColumns = @JoinColumn( name="\"AppointmentId\""), schema = "\"healthnest_bknd_127\"")
private List<Appointment> appointments = new ArrayList<>();


@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"PatientChatrooms\"",
            joinColumns = @JoinColumn( name="\"PatientId\""),
            inverseJoinColumns = @JoinColumn( name="\"ChatroomId\""), schema = "\"healthnest_bknd_127\"")
private List<Chatroom> chatrooms = new ArrayList<>();
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "Patient [" 
  + "PatientId= " + patientId  + ", " 
  + "EmergencyContact= " + emergencyContact  + ", " 
  + "Address= " + address  + ", " 
  + "MedicalInformation= " + medicalInformation  + ", " 
  + "FullName= " + fullName  + ", " 
  + "Age= " + age  + ", " 
  + "DateOfBirth= " + dateOfBirth  + ", " 
  + "Gender= " + gender  + ", " 
  + "PhoneNumber= " + phoneNumber  + ", " 
  + "Email= " + email 
 + "]";
	}
	
}
