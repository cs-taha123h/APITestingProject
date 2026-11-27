package AuthStepDefs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class AuthStepDefs {

    String API = "";
    private static RequestSpecification request;
    private static Response response;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static String basePath;
    private LoginHelper loginHelper;
    private SearchSpartanHelper searchSpartan;

    @Before
    public void prepareApi() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/EnvironmentFiles/enviroment.properties"));

            API = properties.getProperty("API");
            loginHelper = new LoginHelper(API);
            searchSpartan = new SearchSpartanHelper(API);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("I am on the {string} api")
    public void IamOnTheApi(String api){

        basePath = api;

        request = RestAssured
                .given()
                .baseUri(API)
                .basePath(api)
                .header("Accept", "text/json");
    }

    @When("my username is {string} and password is {string}")
    public void myUsernameIsAndPasswordIs(String username, String password) {
        request.body("{"
                + "\"username\": \"" + username + "\","
                + "\"password\": \"" + password + "\""
                + "}").header("Content-Type", "application/json");
    }

    @And("I submit my request as a post")
    public void iSubmitMyRequestAsAPost() {
        response = request.when().post();
    }

    @And("I submit my request as a get")
    public void iSubmitMyRequestAsAGet() {
        response = request.when().get();
    }

    @And("the list should contain {int}")
    public void thereShouldBeItemsInList(int listCount) throws IOException {
        JsonNode items = mapper.readTree(response.getBody().asInputStream());

        MatcherAssert.assertThat(items.size(), Matchers.is(listCount));
    }

    @Then("my response should be status code {int}")
    public void myResponseShouldBeStatusCode(int statusCode) {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(statusCode));
    }

    @Given("the courseID is {string}")
    public void theCourseIDIs(String courseID) {
        request = RestAssured
                .given()
                .baseUri(API)
                .basePath(basePath + "/" + courseID)
                .header("Accept", "text/json")
                .header("Content-Type", "application/json");
    }

    @And("courseName should be {string}")
    public void coursenameShouldBe(String courseName) throws IOException {
        JsonNode course = mapper.readTree(response.getBody().asInputStream());

        MatcherAssert.assertThat(course.get("name").asText(), Matchers.is(courseName));
    }

    @And("I have a valid authorisation token")
    public void iHaveAValidAuthorisationToken() throws IOException {
        loginHelper.login("sparta", "global");
        String token = loginHelper.getToken();

        request.header("Authorization", "Bearer " + token);

    }


    @And("the spartan name should {string}")
    public void theSpartanNameShould(String name) throws IOException {
        JsonNode spartan = mapper.readTree(response.getBody().asInputStream());

        MatcherAssert.assertThat(spartan.get("firstName").asText() + " " + spartan.get("lastName").asText(), Matchers.is(name));
    }

    @And("I send a valid spartan request body")
    public void iSendAValidSpartanRequestBody() {
        request.body("""
                          {
                          "id": 40,
                          "firstName": "Johnathon",
                          "lastName": "Mangin",
                          "university": "Keele",
                          "degree": "Computer Science",
                          "course": {
                            "id": 1,
                            "name": "TECH 300",
                            "stream": {
                              "id": 1,
                              "name": "C# Dev",
                              "courses": []
                            },
                            "trainer": "Phil Windridge",
                            "startDate": "2026-11-12T00:00:00",
                            "endDate": "2026-11-25T00:00:00",
                            "courseId": 1
                          },
                          "graduated": true
                        }""")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
    }

    @And("the response should contain the new spartan details")
    public void theResponseShouldContainTheNewSpartanDetails() throws IOException {
        JsonNode spartan = mapper.readTree(response.getBody().asInputStream());
        MatcherAssert.assertThat(spartan.get("firstName").asText() + " " + spartan.get("lastName").asText(), Matchers.is("John Smith"));
    }

    @And("I send an invalid spartan request body")
    public void iSendAnInvalidSpartanRequestBody() {
        request.body("{"
                + "\"id\": 35,"
                + "\"firstName\": \"John\","
                + "\"lastName\": \"Smith\","
                + "\"spartans\": []"
                + "},"
                + "\"courseId\": 2,"
                + "\"graduated\": false"
                + "}");
    }

    @Given("the spartanID is {int}")
    public void theSpartanIDIs(Integer spartanID) {
        request = RestAssured
                .given()
                .baseUri(API)
                .basePath(basePath + "/" + spartanID)
                .header("Accept", "text/json")
                .header("Content-Type", "application/json");
    }

    @And("I submit my request as a delete")
    public void iSubmitMyRequestAsADelete() {
        response = request.when().delete();
    }

    @Then("when I search for id {int} it should return status code {int}")
    public void whenISearchForIdItShouldReturnStatusCode(int spartanID, int statusCode) throws IOException {
        MatcherAssert.assertThat(searchSpartan.searchFor(spartanID).getStatusCode(), Matchers.is(statusCode));
    }

    @And("I submit my request as a put")
    public void iSubmitMyRequestAsAPut() {
        response = request.when().put();
        System.out.println("Response Body: " + response.asPrettyString());
    }

    @And("the spartan details should be updated")
    public void theSpartanDetailsShouldBeUpdated() throws IOException {
        JsonNode spartan = mapper.readTree(searchSpartan.searchFor(2).getBody().asInputStream());

        MatcherAssert.assertThat(spartan.get("firstName").asText(), Matchers.is("Johnathon"));
    }

    @And("I have a valid spartan update request body")
    public void iHaveAValidSpartanUpdateRequestBody() throws IOException {
        loginHelper.login("sparta", "global");
        String token = loginHelper.getToken();

        request.body("""
                          {
                          "id": 2,
                          "firstName": "Johnathon",
                          "lastName": "TestingSpartan",
                          "university": "CastlePlace University",
                          "degree": "Computer Science",
                          "course": {
                            "id": 1,
                            "name": "TECH 300",
                            "stream": {
                              "id": 1,
                              "name": "C# Dev",
                              "courses": []
                            },
                            "trainer": "Phil Windridge",
                            "startDate": "2023-03-01T00:00:00",
                            "endDate": "2023-05-01T00:00:00",
                            "courseId": 1
                          },
                          "graduated": true
                        }
                        """)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token);
    }
}
