package stepDefinations;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeSCenario() throws IOException {
        stepDefination std = new stepDefination();
        if(stepDefination.place_id==null){
            std.add_place_payload("fromHooks","Java with cucumber","Udemy");
            std.user_calls_with_with_post_http_req("AddPlaceApi","post");
            std.verify_place_Id_from_response("fromHooks","GetPlaceApi");
        }

    }
}
