package studentsInfo;

import io.restassured.http.ContentType;
import model.Student;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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

    static String firstName = TestUtils.getRandomValue() + " SMOKEUSER";
    static String lastName = TestUtils.getRandomValue() + " SMOKEUSER";
    static String programme = "ComputerScience";
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static int studentId;


    @Title("Create a new student")
    @Test
    public void test01createStudent() {

        List<String> courses = new ArrayList<String>();
        courses.add("C++");
        courses.add("Python");

        Student student_1 = new Student();
        student_1.setFirstName(firstName);
        student_1.setLastName(lastName);
        student_1.setProgramme(programme);
        student_1.setEmail(email);
        student_1.setCourses(courses);

        SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(student_1)
                .post()
                .then()
                .log()
                .all()
                .statusCode(201);


    }

    @Title("Verify if the student was added to the application")
    @Test
    public void test02getStudents() {

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

        studentId = (int) value.get("id");
    }

    @Title("Update the user information and verify updated information")
    @Test
    public void test03updateStudent() {

        List<String> courses = new ArrayList<String>();
        courses.add("C++");
        courses.add("Python");

        firstName = firstName + "_Updated";

        Student student_1 = new Student();
        student_1.setFirstName(firstName);
        student_1.setLastName(lastName);
        student_1.setProgramme(programme);
        student_1.setEmail(email);
        student_1.setCourses(courses);

        SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(student_1)
                .put("/" + studentId)
                .then()
                .log()
                .all()
                .statusCode(200);
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

}
