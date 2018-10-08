package serenity;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.Student;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utils.ReuseableSpecifications;

import java.util.HashMap;
import java.util.List;

public class StudentSerenitySteps {

    @Step
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme,
                                             List<String> courses){

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return
                //SerenityRest
                RestAssured
                //.rest()
                .given()
                .spec(ReuseableSpecifications.getGenericRequestSpec())
                .when()
                .body(student)
                .post()
                .then();
    }

    @Step("Getting the student information with firstName")
    public HashMap<String,Object> getStudentsInfoByFirstName(String firstName){

        String p1 = "findAll{it.firstName =='";
        String p2 = "'}.get(0)";

        return
                SerenityRest.given()
                        .when()
                        .get("/list")
                        .then()
                        .log()
                        .all()
                        .statusCode(200)
                        .extract()
                        .path(p1 + firstName + p2);

    }

    @Step
    public ValidatableResponse updateStudent(int studentid, String firstName, String lastName, String email, String programme,
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
                .spec(ReuseableSpecifications.getGenericRequestSpec())
                .log()
                .all()
                .when()
                .body(student)
                .put("/" + studentid)
                .then();
    }
    @Step("Deleting the student")
    public void deleteStudent(int studentId){
        SerenityRest
                .rest()
                .given()
                .when()
                .delete("/" + studentId);

    }
    @Step
    public ValidatableResponse getStudentInfoByStudentId(int studentId){

       return SerenityRest
                .rest()
                .given()
                .when()
                .get("/" + studentId)
                .then();
    }

}
