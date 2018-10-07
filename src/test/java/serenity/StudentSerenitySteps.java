package serenity;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.Student;
import net.serenitybdd.rest.SerenityRest;

import java.util.List;

public class StudentSerenitySteps {

    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme,
                                             List<String> courses){

    Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

    return SerenityRest
            .rest()
            .given()
            .contentType(ContentType.JSON)
            .when()
            .body(student)
            .post()
            .then();
    }
}
