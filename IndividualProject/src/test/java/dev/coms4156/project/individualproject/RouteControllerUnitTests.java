package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;

/**
 * Unit tests for the RouteController class.
 */
@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class RouteControllerUnitTests {
  @Test
  void indexTest() throws Exception {
    String response = routeController.index();
    assertEquals("Welcome, in order to make an API " +
            "call direct your browser or Postman to an endpoint "
            + "\n\n This can be done using the following format: \n\n http:127.0.0"
            + ".1:8080/endpoint?arg=value", response);
  }

  @Test
  void retrieveDepartmentTest() {
    ResponseEntity<?> response1 = routeController.retrieveDepartment("PHYS");
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals("PHYS 4040: \nInstructor: James C Hill; Location: 214 PUP; Time: " +
        "4:10-5:25\nPHYS 1602: \nInstructor: Kerstin M Perez; Location: 428 PUP; Time: " +
        "10:10-11:25\nPHYS 3008: \nInstructor: William A Zajc; Location: 329 PUP; Time: " +
        "10:10-11:25\nPHYS 1201: \nInstructor: Eric Raymer; Location: 428 PUP; Time: " +
        "2:40-3:55\nPHYS 4003: \nInstructor: Frederik Denef; Location: 214 PUP; Time: " +
        "4:10-5:25\nPHYS 1001: \nInstructor: Szabolcs Marka; Location: 301 PUP; Time: " +
        "2:40-3:55\nPHYS 4018: \nInstructor: James W McIver; Location: 307 PUP; Time: " +
        "2:40-3:55\nPHYS 2802: \nInstructor: Yury Levin; Location: 329 PUP; Time: " +
        "10:10-12:00\n", response1.getBody());

    ResponseEntity<?> response2 = routeController.retrieveDepartment("Fashion");
    assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    assertEquals("Department Not Found", response2.getBody());
  }

  @Test
  void retrieveCourseTest() {
    ResponseEntity<?> response1 = routeController.retrieveCourse("PHYS", 4040);
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals("\nInstructor: James C Hill; Location: 214 PUP; Time: 4:10-5:25",
        response1.getBody());

    ResponseEntity<?> response2 = routeController.retrieveCourse("Fashion", 4040);
    assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    assertEquals("Department Not Found", response2.getBody());

    ResponseEntity<?> response3 = routeController.retrieveCourse("PHYS", 5555);
    assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());
    assertEquals("Course Not Found", response3.getBody());
  }

  @Test
  void isCourseFullTest() {
    ResponseEntity<?> response1 = routeController.isCourseFull("PHYS", 4040);
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals(false, response1.getBody());

    ResponseEntity<?> response2 = routeController.isCourseFull("CHEM", 2444);
    assertEquals(HttpStatus.OK, response2.getStatusCode());
    assertEquals(true, response2.getBody());

    ResponseEntity<?> response3 = routeController.isCourseFull("PHYS", 5555);
    assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());
    assertEquals("Course Not Found", response3.getBody());
  }

  @Test
  void getMajorCtFromDeptTest() {
    HashMap<String, Department> departmentMapping;
    departmentMapping = IndividualProjectApplication.myFileDatabase.getDepartmentMapping();

    ResponseEntity<?> response1 = routeController.getMajorCtFromDept("PHYS");
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals("There are: " + departmentMapping.get("PHYS").getNumberOfMajors() + " majors in " +
            "the department",
        response1.getBody());

    ResponseEntity<?> response2 = routeController.getMajorCtFromDept("Fashion");
    assertEquals(HttpStatus.FORBIDDEN, response2.getStatusCode());
    assertEquals("Department Not Found", response2.getBody());
  }

  @Test
  void identifyDeptChairTest() {
    ResponseEntity<?> response1 = routeController.identifyDeptChair("PHYS");
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals("Dmitri N. Basov is the department chair.", response1.getBody());

    ResponseEntity<?> response2 = routeController.identifyDeptChair("Fashion");
    assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    assertEquals("Department Not Found", response2.getBody());
  }

  @Test
  void findCourseLocationTest() {
    ResponseEntity<?> response1 = routeController.findCourseLocation("PHYS", 2802);
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals("329 PUP is where the course is located.", response1.getBody());

    ResponseEntity<?> response2 = routeController.findCourseLocation("Fashion", 531);
    assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    assertEquals("Course Not Found", response2.getBody());
  }

  @Test
  void findCourseInstructorTest() {
    ResponseEntity<?> response1 = routeController.findCourseInstructor("PHYS", 2802);
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals("Yury Levin is the instructor for the course.", response1.getBody());

    ResponseEntity<?> response2 = routeController.findCourseInstructor("Fashion", 894);
    assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    assertEquals("Course Not Found", response2.getBody());
  }

  @Test
  void findCourseTimeTest() {
    ResponseEntity<?> response1 = routeController.findCourseTime("PHYS", 2802);
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals("The course meets at: 10:10-12:00", response1.getBody());

    ResponseEntity<?> response2 = routeController.findCourseTime("Fashion", 5023);
    assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    assertEquals("Course Not Found", response2.getBody());
  }

  @Test
  void addMajorToDeptTest() {
    ResponseEntity<?> response1 = routeController.addMajorToDept("PHYS");
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals("Attribute was updated successfully", response1.getBody());

    ResponseEntity<?> response2 = routeController.addMajorToDept("FASHION");
    assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    assertEquals("Department Not Found", response2.getBody());
  }

  @Test
  void removeMajorFromDeptTest() {
  }

  @Test
  void dropStudentTest() {
  }

  @Test
  void setEnrollmentCountTest() {
  }

  @Test
  void changeCourseTimeTest() {
  }

  @Test
  void changeCourseTeacherTest() {
  }

  @Test
  void changeCourseLocationTest() {
  }

  @InjectMocks
  private RouteController routeController;
}
