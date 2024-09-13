package dev.coms4156.project.individualproject;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;

/**
 * Unit tests for the MyFileDatabase class.
 */
@SpringBootTest
@ContextConfiguration
public class MyFileDatabaseUnitTests {

  @BeforeAll
  public static void setupBeforeForTesting() {
    courses = new HashMap<>();
    courses.put("AI", new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250));
    courses.put("Databases", new Course("Donald Ferguson", "417 IAB", "11:00-1:50", 300));
    deptMap = new HashMap<>();
    deptMap.put("CompSci", new Department("4000", courses, "Mr. Smith", 6));
    db = new MyFileDatabase(1, "./mydata.txt");
  }

  @Test
  public void setGetMappingTest(){
    setupBeforeForTesting();
    db.setMapping(deptMap);
    assertEquals(deptMap, db.getDepartmentMapping());
  }

  @Test
  public void saveAndDeSerializeTest(){
    setupBeforeForTesting();
    db.setMapping(deptMap);
    db.saveContentsToFile();
    assertThat(db.deSerializeObjectFromFile())
        .usingRecursiveComparison()
        .isEqualTo(deptMap);
  }

  @Test
  public void toStringTest(){
    db = new MyFileDatabase(0, "./mydata.txt");
    String stringRep = "For the CompSci department: \n4000 Databases: \nInstructor: Donald " +
        "Ferguson; Location:" +
        " 417 IAB; Time: " +
        "11:00-1:50\n4000 AI: \nInstructor: Griffin Newbold; Location: 417 IAB; Time: " +
        "11:40-12:55\n";
    assertEquals(stringRep, db.toString());
  }

  public static MyFileDatabase db;
  public static HashMap<String, Course> courses;
  public static HashMap<String, Department> deptMap;
}
