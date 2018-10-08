package studentsInfo;

import lombok.Getter;
import lombok.Setter;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import serenity.StudentSerenitySteps;
import testBase.TestBase;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@UseTestDataFrom("testData/studentInfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentsDataDrivenTest extends TestBase {

    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private String course;

    @Steps
    StudentSerenitySteps steps;

    @Title("Data driven test for adding multiple students to the Students App.")
    @Test
    public void createMultipleStudents() {
        List<String> courses = new ArrayList<>();
        courses.add(course);
        steps.createStudent(firstName, lastName, email, programme, courses)
                .statusCode(HttpStatus.CREATED_201);
    }
}
