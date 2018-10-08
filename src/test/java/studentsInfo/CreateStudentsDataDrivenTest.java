package studentsInfo;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import serenity.StudentSerenitySteps;
import testBase.TestBase;

@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentsDataDrivenTest extends TestBase {

    @Steps
    StudentSerenitySteps steps;

    @Title("Data driven test for adding multiple students to the Students App.")
    @Test
    public void createMultipleStudents() {
        steps.createStudent(f)
    }
}
