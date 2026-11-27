import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.java.Log;

import java.io.IOException;

public class LoginHelper {
    Response response;
    String API;
    RequestSpecification request;
    private static final ObjectMapper mapper = new ObjectMapper();

    public LoginHelper(String api) {
        this.API = api;
    }

    public void login(String username, String password){
        request = RestAssured
                .given()
                .baseUri(API)
                .basePath("auth/login")
                .header("Accept", "text/json")
                .body("{"
                        + "\"username\": \"" + username + "\","
                        + "\"password\": \"" + password + "\""
                        + "}").header("Content-Type", "application/json");

        postRequest();
    }

    public void postRequest(){
        this.response = request.when().post();
    }

    public String getToken() throws IOException {
        JsonNode token = mapper.readTree(response.getBody().asInputStream());

        return token.get("token").asText();
    }

}
