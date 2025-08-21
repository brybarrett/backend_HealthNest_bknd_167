package com.app.HealthNest_bknd.integrationtest;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.app.HealthNest_bknd.SpringApp;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
class ControllerTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private WebApplicationContext context;
  @LocalServerPort
  private int port;

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.webAppContextSetup(context);
  }

  
  
   private JsonNode getJSONFromFile(String filePath) throws IOException {
    try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)){
      JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
      return jsonNode;
    }
    catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  
  private String getPayload(String filePath) throws IOException {
	  String jsonString = mapper.writeValueAsString( getJSONFromFile(filePath) );
	  return jsonString;
  }

  @Test
  void testRetrieveServiceDocument() {
    final String xml = given()
        .accept(ContentType.XML)
        .when()
        .get("/HealthNest_bknd/")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Collection<Node> n = ((Node) ((Node) path.get("service")).get("workspace")).get("collection");
    assertNotNull(n);
    assertFalse(n.isEmpty());
  }

  @Test
  void  testRetrieveMetadataDocument() {
    final String xml = given()
        .when()
        .get("/HealthNest_bknd/$metadata")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Node n = ((Node) ((Node) path.get("edmx:Ed mx")).get("DataServices")).get("Schema");
    assertNotNull(n);
    assertEquals("HealthNest_bknd", n.getAttribute("Namespace"));
    assertNotNull(n.get("EntityContainer"));
  }

	

	
  @Test
  void  testCreatePrescriptionInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("PrescriptionInstance.json"))
        .when()
        .post("/HealthNest_bknd/Prescriptions")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsPrescription() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("PrescriptionInstance.json"))
        .when()
        .post("/HealthNest_bknd/Prescriptions")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Prescriptions?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).PrescriptionId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Prescriptions/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateAvailabilityInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("AvailabilityInstance.json"))
        .when()
        .post("/HealthNest_bknd/Availabilities")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsAvailability() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("AvailabilityInstance.json"))
        .when()
        .post("/HealthNest_bknd/Availabilities")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Availabilities?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).AvailabiltyId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Availabilities/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateAppointmentInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("AppointmentInstance.json"))
        .when()
        .post("/HealthNest_bknd/Appointments")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsAppointment() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("AppointmentInstance.json"))
        .when()
        .post("/HealthNest_bknd/Appointments")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Appointments?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).AppointmentId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Appointments/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateReminderInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ReminderInstance.json"))
        .when()
        .post("/HealthNest_bknd/Reminders")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsReminder() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ReminderInstance.json"))
        .when()
        .post("/HealthNest_bknd/Reminders")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Reminders?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ReminderId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Reminders/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateSpecialtyCategoryInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("SpecialtyCategoryInstance.json"))
        .when()
        .post("/HealthNest_bknd/SpecialtyCategories")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsSpecialtyCategory() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("SpecialtyCategoryInstance.json"))
        .when()
        .post("/HealthNest_bknd/SpecialtyCategories")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/SpecialtyCategories?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).Name", equalTo("'<<replace_with_keyFieldValue>>'"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/SpecialtyCategories/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateChatMessageInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ChatMessageInstance.json"))
        .when()
        .post("/HealthNest_bknd/ChatMessages")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsChatMessage() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ChatMessageInstance.json"))
        .when()
        .post("/HealthNest_bknd/ChatMessages")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/ChatMessages?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ChatMessageId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/ChatMessages/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateChatroomInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ChatroomInstance.json"))
        .when()
        .post("/HealthNest_bknd/Chatrooms")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsChatroom() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ChatroomInstance.json"))
        .when()
        .post("/HealthNest_bknd/Chatrooms")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Chatrooms?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ChatroomId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Chatrooms/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreatePatientInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("PatientInstance.json"))
        .when()
        .post("/HealthNest_bknd/Patients")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsPatient() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("PatientInstance.json"))
        .when()
        .post("/HealthNest_bknd/Patients")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Patients?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).PatientId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Patients/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateDoctorInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("DoctorInstance.json"))
        .when()
        .post("/HealthNest_bknd/Doctors")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsDoctor() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("DoctorInstance.json"))
        .when()
        .post("/HealthNest_bknd/Doctors")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Doctors?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).DoctorId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Doctors/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateReviewInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ReviewInstance.json"))
        .when()
        .post("/HealthNest_bknd/Reviews")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsReview() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ReviewInstance.json"))
        .when()
        .post("/HealthNest_bknd/Reviews")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Reviews?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ReviewId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Reviews/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateDocumentInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("DocumentInstance.json"))
        .when()
        .post("/HealthNest_bknd/Documents")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsDocument() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("DocumentInstance.json"))
        .when()
        .post("/HealthNest_bknd/Documents")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Documents?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).DocId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Documents/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateNotificationInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("NotificationInstance.json"))
        .when()
        .post("/HealthNest_bknd/Notifications")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsNotification() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("NotificationInstance.json"))
        .when()
        .post("/HealthNest_bknd/Notifications")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Notifications?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).NotificationId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Notifications/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateSpecialtyInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("SpecialtyInstance.json"))
        .when()
        .post("/HealthNest_bknd/Specialties")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsSpecialty() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("SpecialtyInstance.json"))
        .when()
        .post("/HealthNest_bknd/Specialties")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Specialties?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).DocSpeId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Specialties/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateClinicInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ClinicInstance.json"))
        .when()
        .post("/HealthNest_bknd/Clinics")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsClinic() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ClinicInstance.json"))
        .when()
        .post("/HealthNest_bknd/Clinics")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Clinics?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ClinicId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Clinics/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateWeekdayInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("WeekdayInstance.json"))
        .when()
        .post("/HealthNest_bknd/Weekdays")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsWeekday() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("WeekdayInstance.json"))
        .when()
        .post("/HealthNest_bknd/Weekdays")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Weekdays?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).WeekdayId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Weekdays/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateQualificationInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("QualificationInstance.json"))
        .when()
        .post("/HealthNest_bknd/Qualifications")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsQualification() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("QualificationInstance.json"))
        .when()
        .post("/HealthNest_bknd/Qualifications")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Qualifications?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).QualificationId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Qualifications/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreatePaymentInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("PaymentInstance.json"))
        .when()
        .post("/HealthNest_bknd/Payments")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsPayment() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("PaymentInstance.json"))
        .when()
        .post("/HealthNest_bknd/Payments")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Payments?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).PaymentId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Payments/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateMedicationInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("MedicationInstance.json"))
        .when()
        .post("/HealthNest_bknd/Medications")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsMedication() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("MedicationInstance.json"))
        .when()
        .post("/HealthNest_bknd/Medications")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Medications?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).MedicationId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Medications/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateSlotInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("SlotInstance.json"))
        .when()
        .post("/HealthNest_bknd/Slots")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsSlot() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("SlotInstance.json"))
        .when()
        .post("/HealthNest_bknd/Slots")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/HealthNest_bknd/Slots?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).SlotId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/HealthNest_bknd/Slots/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
           
       
  
  
  
  
 
  @AfterEach
  void  teardown() {
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Prescription");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Availability");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Appointment");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Reminder");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.SpecialtyCategory");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.ChatMessage");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Chatroom");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Patient");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Doctor");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Review");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Document");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Notification");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Specialty");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Clinic");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Weekday");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Qualification");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Payment");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Medication");
    jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.Slot");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.DoctorPatientAppointments");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.DoctorAvailabilities");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.DoctorCertifications");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.AppointmentDocuments");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.DoctorDoctorChatrooms");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.DoctorWeeklyScheduleByDays");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.DoctorPayments");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.DoctorQualifications");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.AppointmentReminders");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.PrescriptionMeds");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.ClinicDoctors");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.PatientAppointments");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.SpecialtyAvailableDocs");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.AvailabilitySlots");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.ChatroomChats");
     jdbcTemplate.execute("DELETE FROM healthnest_bknd_127.PatientChatrooms");

    RestAssuredMockMvc.reset();
  }
}
