package stepDefination;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resource.APIresources;
import resource.TestDataBuilder;
import resource.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class stepDefination extends Utils {

    RequestSpecification request;
    ResponseSpecification responseSpecBuilder;
    Response response;
    TestDataBuilder testDataBuilder = new TestDataBuilder();
    private String expectedValue;
    static String place_id;

    @Given("Add place payload {string} {string} {string}")
    public void addPlacePayload(String name, String language, String address) throws IOException {
        request = given().spec(requestSpecification1()).body(testDataBuilder.AddPlaceAPI(name, language, address));
    }


    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String apiResource, String callingMethod) {
        APIresources apIresource = APIresources.valueOf(apiResource);
        System.out.println(apIresource.getapiResourcse());
        responseSpecBuilder = responseSpecification1();
        if (callingMethod.equalsIgnoreCase("POST")) {
            response = request.when().post(apIresource.getapiResourcse());
        } else if (callingMethod.equalsIgnoreCase("GET")) {
            response = request.when().get(apIresource.getapiResourcse());
        } else if (callingMethod.equalsIgnoreCase("delete")) {
            response = request.when().delete(apIresource.getapiResourcse());
        }
    }


    @Then("^the API call got success with status code 200$")
    public void the_api_call_got_success_with_status_code_200() throws Throwable {
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200);
    }


    @And("the {string} in response body is {string}")
    public void theInResponseBodyIs(String key, String expectedValue) throws JsonProcessingException {
        String keyValue = createResponseJacksonNode(response,key);

        assertEquals(expectedValue, keyValue);
    }

    @Given("delete place payload")
    public void delete_place_payload() throws IOException {
        request = given().spec(requestSpecification1()).body(testDataBuilder.deletePlaceAPI(place_id));
    }



    @And("return place details in a file")
    public void returnPlaceDetailsInAFile() throws JsonProcessingException, FileNotFoundException {
        String responseAsString = response.asPrettyString();
        File file =new File("C:\\getPlaceAPIs\\sample.txt");
        PrintStream stream = new PrintStream(file);
        stream.println(responseAsString);
        System.setOut(stream);

    }

    @And("capture the place_id")
    public void captureThePlace_id() throws JsonProcessingException {
        place_id = createResponseJacksonNode(response,"place_id");

    }


    @Given("place id for getPlace API {string}")
    public void placeIdForGetPlaceAPI(String idPlace) throws IOException{
        request = given().spec(requestSpecification1()).queryParam("place_id", idPlace);
    }

    @And("verify place_id created maps to {string} using {string}")
    public void verifyPlace_idCreatedMapsToUsing(String name, String resource) throws IOException {
        //requestSpec
        request = given().spec(requestSpecification1()).queryParam("place_id", place_id);

        user_calls_with_http_request(resource, "get");
        String realName =createResponseJacksonNode(response,"name" );
        assertEquals(realName,name);
    }
}





