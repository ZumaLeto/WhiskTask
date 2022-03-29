import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Whisk;
import model.WhiskResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class WhiskTestsAPI {

    String bearerToken = "Hp1wwzc0AMBi2YyXE2UPqB2pQQoAyG1GLgf9fbDbtCRXNBNONrfMTLGHNohqaqyx";
    String api = "https://api.whisk-dev.com/list/v2";
    RequestSpecification requestSpecification = given().headers("Authorization", "Bearer " + bearerToken);
    String id;

    @BeforeMethod
    public void precondition() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Whisk whisk = new Whisk("string", false);
        String jsonBody = objectMapper.writeValueAsString(whisk);
        id =  requestSpecification.and().body(jsonBody)
                .when()
                .contentType(ContentType.JSON)
                .post(api)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response().as(WhiskResponse.class).getList().get("id");
    }

    @Test(priority = 1)
    public void checkPostRequest() {
        WhiskResponse whiskResponse = requestSpecification.contentType(ContentType.JSON)
                .when()
                .get(api + "/" + id)
                .then()
                .extract()
                .response().as(WhiskResponse.class);
        Assert.assertEquals(id,  whiskResponse.getList().get("id"), "id is incorrect");
        Assert.assertEquals("{}",  whiskResponse.getContent().toString(), "content is not empty");
    }

    @Test(priority = 2)
    public void checkDeleteRequest() {
        requestSpecification.when().delete(api + "/" + id).then().extract().response();

        Response response = requestSpecification.contentType(ContentType.JSON)
                .when()
                .get(api + "/" + id)
                .then()
                .extract()
                .response();
        // Не будет выполнятся, код статус 400
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code is incorrect");
        Assert.assertEquals(response.as(WhiskResponse.class).getCode(), "shoppingList.notFound", "Code response is incorrect");
    }
}
