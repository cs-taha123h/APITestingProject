import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.runner.Request;

import java.io.IOException;

public class SearchSpartanHelper {
    Response response;
    String API;
    RequestSpecification request;
    private static final ObjectMapper mapper = new ObjectMapper();
    private final LoginHelper loginHelper;

    public SearchSpartanHelper(String API) {
        this.API = API;
        loginHelper = new LoginHelper(API);
        loginHelper.login("sparta", "global");
    }

    public Response searchFor(int spartanID) throws IOException {
        request = RestAssured
                .given()
                .baseUri(API)
                .basePath("/api/spartans/" + spartanID)
                .header("Accept", "text/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + loginHelper.getToken());

        response = request.when().get();
        return response;
    }
}
