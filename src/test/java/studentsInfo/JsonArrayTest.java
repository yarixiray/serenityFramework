package studentsInfo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.eclipse.jetty.http.HttpStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import testBase.TestBase;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JsonArrayTest extends TestBase {

    private static String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static String pathToJsonFile = "testData\\studentsList.json";
    Response response;
    private static List<String> doctorsLastName = new ArrayList<>();


    @Test
    public void test01jsonToArray() {
        String filePathForCreateDoctor = rootPath + pathToJsonFile;
        JSONArray jsonArray = TestUtils.mappingJsonToArray(filePathForCreateDoctor);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            obj.remove("email");
            obj.remove("lastName");
            obj.put("email", "email_" + TestUtils.getRandomValue() + "@mail.com");
            obj.put("lastName", "lastName_" + TestUtils.getRandomValue());
            doctorsLastName.add(obj.get("lastName").toString());
            response = given().contentType(ContentType.JSON).when().body(obj.toString()).post();
            response.then().statusCode(HttpStatus.CREATED_201);
        }
    }


    @Test
    public void test02arrayList() {
        System.out.println(doctorsLastName.toString());
    }

}
