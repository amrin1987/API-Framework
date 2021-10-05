package resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    static Properties prop;
    public static RequestSpecification requestSpecBuilder;
    ResponseSpecification responseSpecBuilder;


//if logs are not capturing perfectly:
    public RequestSpecification requestSpecification1() throws IOException {
        if (requestSpecBuilder==null) {
            PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpecBuilder = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI"))
                    .addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(stream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                    .setContentType(ContentType.JSON).build();
            return requestSpecBuilder;
        }
        else {
            return requestSpecBuilder;
        }
    }
    public ResponseSpecification responseSpecification1(){
        responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
        return responseSpecBuilder;
    }

    public static String getGlobalValue(String key) throws IOException {
        FileInputStream inStream=new FileInputStream("src/test/java/resource/Global.properties");
        prop=new Properties();
        prop.load(inStream);
        return prop.getProperty(key);
    }
    public String createResponseJacksonNode(Response response, String key) throws JsonProcessingException {
        String responseAsString = response.asPrettyString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node;
        node = objectMapper.readTree(responseAsString);
        String keyValue=node.get(key).asText();
        return keyValue;
    }
}
