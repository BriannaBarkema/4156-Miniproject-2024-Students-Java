package dev.coms4156.project.individualproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Course class.
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeAll
  public static void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }


  @Test
  public void toStringTest() {
    setupCourseForTesting();
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void enrollStudentTest(){
    testCourse.setEnrolledStudentCount(0);
    assertTrue(testCourse.enrollStudent());
    testCourse.setEnrolledStudentCount(255);
    assertFalse(testCourse.enrollStudent());
  }

  @Test
  public void dropStudentTest(){
    testCourse.setEnrolledStudentCount(0);
    assertFalse(testCourse.dropStudent());
    testCourse.setEnrolledStudentCount(250);
    assertTrue(testCourse.dropStudent());
  }

  @Test
  public void setGetCourseLocationTest(){
    testCourse.reassignLocation("Pupin");
    assertEquals("Pupin", testCourse.getCourseLocation());
  }

  @Test
  public void setGetInstructorTest(){
    testCourse.reassignInstructor("Cheryl");
    assertEquals("Cheryl", testCourse.getInstructorName());
  }

  @Test
  public void setGetCourseTimeSlotTest(){
    testCourse.reassignTime("12:00am");
    assertEquals("12:00am", testCourse.getCourseTimeSlot());
  }

  @Test
  public void isCourseFullTest(){
    testCourse.setEnrolledStudentCount(250);
    assertTrue(testCourse.isCourseFull());
    testCourse.setEnrolledStudentCount(5);
    assertFalse(testCourse.isCourseFull());
    testCourse.setEnrolledStudentCount(260);
    assertTrue(testCourse.isCourseFull());
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}

