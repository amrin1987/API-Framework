package resource;

import POJOs.AddPlace;
import POJOs.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {
    public AddPlace AddPlaceAPI(String name, String language, String address) {
        AddPlace pojoBody=new AddPlace();

        List<String> typeList=new ArrayList<String>();
        typeList.add("Shoe rack");
        typeList.add("reptile");

        Location location=new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        pojoBody.setLocation(location);
        pojoBody.setAccuracy(40);
        pojoBody.setAddress("address");
        pojoBody.setLanguage(language);
        pojoBody.setPhone_number("313-888-3936");
        pojoBody.setWebsite("amrinwahid01@gmail.com");
        pojoBody.setName(name);
        pojoBody.setTypes(typeList);

        return pojoBody;
    }

    public String deletePlaceAPI(String idPlace) {
        return "{\n" +
                " \n" +
                "\"place_id\" : \"" + idPlace + "\"\n" +
                " \n" +
                "}";

    }
}
