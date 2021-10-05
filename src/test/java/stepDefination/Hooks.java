package stepDefination;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //Write a code to generate place_id
        //execute this code only place_id is null

        stepDefination step_defination=new stepDefination();
        if (stepDefination.place_id==null) {
            step_defination.addPlacePayload("AmrinOwais", "Kashmiri", "290 vasser drive Piscataway");
            step_defination.user_calls_with_http_request("AddPlaceApi", "post");
            step_defination.captureThePlace_id();
        }
    }
}
