package studentsInfo;

import org.json.simple.JSONArray;
import org.junit.Test;
import testBase.TestBase;
import utils.TestUtils;

public class JsonArrayTest extends TestBase {

    private static String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    @Test
    public void jsonToArray() {
        String filePathForCreateDoctor = rootPath + "testData\\studentsList.json";
        JSONArray jsonArray = TestUtils.mappingJsonToArray(filePathForCreateDoctor);
        System.out.println(jsonArray);

    }
}
