package studentsInfo;

import io.restassured.http.ContentType;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import model.Student;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import testBase.TestBase;

@Slf4j
public class CRUDTest extends TestBase {

    static String firstName = "SMOKEUSER";
    static String lastName = "SMOKEUSER";
    static String programme = "ComputerScience";
    static String email = "xyz@gmail.com";

    @Title("Create a new student")
    @Test
    public void createStudent() {

        Student student_1 = new Student();
        student_1.setFirstName(firstName);
        student_1.setLastName(lastName);
        student_1.setProgramme(programme);
        student_1.setEmail(email);

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
}
