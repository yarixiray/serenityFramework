package studentsInfo;

import io.restassured.http.ContentType;
import model.Student;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.http.HttpConnection;
import org.apache.http.protocol.HTTP;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import serenity.StudentSerenitySteps;
import testBase.TestBase;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CRUDTest extends TestBase {

    private static String firstName = TestUtils.getRandomValue() + " SMOKEUSER";
    private static String lastName = TestUtils.getRandomValue() + " SMOKEUSER";
    private static String programme = "ComputerScience";
    private static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    private static int studentId;

    @Steps
    private static StudentSerenitySteps steps;


    @Title("Create a new student")
    @Test
    public void test01createStudent() {

        List<String> courses = new ArrayList<>();
        courses.add("C++");
        courses.add("Python");

        steps.createStudent(firstName,lastName,email,programme,courses)
             .statusCode(HttpStatus.CREATED_201);

    }

    @Title("Verify if the student was added to the application")
    @Test
    public void test02getStudents() {

        HashMap<String,Object> value = steps.getStudentsInfoByFirstName(firstName);
        assertThat(value, hasValue(firstName));
        studentId = (int) value.get("id");
    }

    @Title("Update the user information and verify updated information")
    @Test
    public void test03updateStudent() {

        List<String> courses = new ArrayList<>();
        courses.add("C++");
        courses.add("Python");
        firstName = firstName + "_Updated";
        steps.updateStudent(studentId,firstName,lastName,email,programme,courses);
        HashMap<String,Object> value = steps.getStudentsInfoByFirstName(firstName);
        assertThat(value,hasValue(firstName));
    }

    @Title("Verify if the updated student was added to the application")
    @Test
    public void test04getUpdatedStudent() {

        String p1 = "findAll{it.firstName =='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> value =
                SerenityRest.given()
                        .when()
                        .get("/list")
                        .then()
                        .log()
                        .all()
                        .statusCode(200)
                        .extract()
                        .path(p1 + firstName + p2);

        System.out.println(value);

        assertThat(value, hasValue(firstName));

    }

    @Title("Delete the student and verify if the student is deleted")
    @Test
    public void test05deleteStudent() {
    steps.deleteStudent(studentId);
    steps.getStudentInfoByStudentId(studentId).statusCode(HttpStatus.NOT_FOUND_404);


    }

}
