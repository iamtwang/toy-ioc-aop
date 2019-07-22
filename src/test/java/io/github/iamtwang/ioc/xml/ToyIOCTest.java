package io.github.iamtwang.ioc.xml;

import io.github.iamtwang.ioc.pojo.School;
import io.github.iamtwang.ioc.pojo.Student;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.URI;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 * @author Tao
 */
public class ToyIOCTest {

    private ToyIOC toyIOC;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {

        URI location = ToyIOC.class.getClassLoader().getResource("ioc.xml").toURI();
        toyIOC = new ToyIOC(Paths.get(location).toString());
    }

    @Test
    public void getBean_StandardAttribute() {

        School school = (School) toyIOC.getBean("school");
        assertNotNull(school);
        assertEquals("St Joseph School", school.getName());
        assertEquals("UK", school.getAddress());
    }

    @Test
    public void getBean_ReferenceAttribute() {
        School school = (School) toyIOC.getBean("school");
        Student student = (Student) toyIOC.getBean("student");
        assertNotNull(student);
        assertEquals("YIFAN", student.getName());
        assertEquals("Male", student.getGender());
        assertEquals(school, student.getSchool());
    }

    @Test
    public void getBean_NotExistBean() {
        expectedException.expect(IllegalArgumentException.class);
        toyIOC.getBean("NotExist");
    }
}