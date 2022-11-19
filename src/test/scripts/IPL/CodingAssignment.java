package test.scripts.IPL;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.lang.Assert;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import test.scripts.IplBase;
import java.io.IOException;

/**
*Coding Assginment
 * @authot Ankit Dwivedi on 19/11/2022
 */
public class CodingAssignment extends IplBase {

    private JsonPath jsonObj;
    private int playerObjSize;
    private int foreignPlayerCount;
    private boolean wicketKeeper;

    public CodingAssignment() throws IOException {
        this.jsonObj = new JsonPath(readTestData());
        this.playerObjSize = this.jsonObj.getList("player").size();
        this.foreignPlayerCount = 0;
        this.wicketKeeper = false;
    }

    @Test
    public void testForeignPlayerCount() throws JsonProcessingException {
        for(int i = 0; i < playerObjSize; i++){
            JsonPath jsonStr = parseStringToJson(jsonObj.getList("player").get(i));
            if(!jsonStr.getString("country").equalsIgnoreCase("INDIA")){
                foreignPlayerCount+=1;
            }
        }
        Assert.isTrue(foreignPlayerCount <= 4, "Foreign Player Count is Greater than 4");
    }

    @Test
    public void testAtleastOneWicketKeeper() throws JsonProcessingException {
        for(int i = 0; i < playerObjSize; i++) {
            JsonPath jsonStr = parseStringToJson(jsonObj.getList("player").get(i));
            if (!wicketKeeper) {
                if (jsonStr.getString("role").equalsIgnoreCase("WICKET-KEEPER")) {
                    wicketKeeper = true;
                    break;
                }
            }
        }
        Assert.isTrue(wicketKeeper == true,"There is no wicket keeper in the team");
    }

}
