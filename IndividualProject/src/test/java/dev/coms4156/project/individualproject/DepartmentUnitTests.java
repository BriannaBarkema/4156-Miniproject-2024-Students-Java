package dev.coms4156.project.individualproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit tests for the Department class.
 */
@SpringBootTest
@ContextConfiguration
public class DepartmentUnitTests {
  @BeforeAll
  public static void setupDepartmentForTesting() {
    courses = new HashMap<>();
    courses.put("AI", new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250));
    courses.put("Databases", new Course("Donald Ferguson", "417 IAB", "11:00-1:50", 300));
    testDepartment = new Department("4000", courses, "Mr. Smith", 6);
  }

  @Test
  public void getNumberOfMajorsTest(){
    setupDepartmentForTesting();
    assertEquals(6, testDepartment.getNumberOfMajors());
  }
  @Test
  public void getDepartmentChairTest(){
    setupDepartmentForTesting();
    assertEquals("Mr. Smith", testDepartment.getDepartmentChair());
  }

  @Test
  public void getCourseSelection(){
    setupDepartmentForTesting();
    assertEquals(courses, testDepartment.getCourseSelection());
  }

  @Test
  public void addPersonToMajorTest(){
    setupDepartmentForTesting();
    testDepartment.addPersonToMajor();
    assertEquals(7, testDepartment.getNumberOfMajors());
  }

  @Test
  public void dropPersonFromMajorTest(){
    setupDepartmentForTesting();
    testDepartment.dropPersonFromMajor();
    assertEquals(5, testDepartment.getNumberOfMajors());
    testDepartment.dropPersonFromMajor();
    testDepartment.dropPersonFromMajor();
    testDepartment.dropPersonFromMajor();
    testDepartment.dropPersonFromMajor();
    testDepartment.dropPersonFromMajor();
    testDepartment.dropPersonFromMajor();
    assertEquals(0, testDepartment.getNumberOfMajors());
  }

  @Test
  public void addCourseTest(){
    setupDepartmentForTesting();
    Course math = new Course("Nathan Mathy", "204 USB", "4:40-6:25", 110);
    testDepartment.addCourse("Math", math);
    courses.put("Math", math);
    assertEquals(courses, testDepartment.getCourseSelection());
  }

  @Test
  public void createCourseTest(){
    setupDepartmentForTesting();
    testDepartment.createCourse("Math", "Billy Mathy", "306 USB", "9:00-11:00", 55);
    assertEquals(3, testDepartment.getCourseSelection().size());
  }

  @Test
  public void toStringTest(){
    setupDepartmentForTesting();
    String stringRep = "4000 Databases: \nInstructor: Donald Ferguson; Location: 417 IAB; Time: " +
        "11:00-1:50\n4000 AI: \nInstructor: Griffin Newbold; Location: 417 IAB; Time: " +
        "11:40-12:55\n";
    assertEquals(stringRep, testDepartment.toString());
  }

  /** The test department instance used for testing. */
  public static Department testDepartment;
  public static HashMap<String, Course> courses;

}
