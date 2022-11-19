package test.scripts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.*;
import java.io.IOException;


/**
 *Coding Assginment
 * @authot Ankit Dwivedi on 19/11/2022
 */
public class IplBase {

    public String readTestData() throws IOException {

        String testDatPath = "src/test/resources/testdata/teamInfo.csv";
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = new BufferedReader(new FileReader(testDatPath));
        String str;
        while ((str = buffer.readLine()) != null) {
            builder.append(str).append("\n");
        }
        return builder.toString();
    }

    public JsonPath parseStringToJson(Object data) throws JsonProcessingException {

        ObjectMapper obj = new ObjectMapper();
        String jsonStr = obj.writeValueAsString(data);
        JsonPath json = new JsonPath(jsonStr);
        return json;
    }

}
