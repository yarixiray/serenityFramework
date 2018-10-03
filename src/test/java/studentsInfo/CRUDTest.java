package studentsInfo;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import testBase.TestBase;

public class CRUDTest extends TestBase {

    @Title("Create a new student")
    @Test
    public void createStudent() {
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body("")
                .post();


    }
}
