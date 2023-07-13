package reqBody;

import pojo.requestPojo.addMaps;
import pojo.requestPojo.location;

import java.util.ArrayList;
import java.util.List;

public class addBody {
    public addMaps addPlace(String name,String language,String adress){
        addMaps am = new addMaps();
        am.setAccuracy("50");
        am.setAddress(adress);
        am.setLanguage(language);
        location l = new location();
        l.setLat(-38.38);
        l.setLng(33.42);
        am.setLocation(l);
        am.setName(name);
        am.setPhone_number("984582020");
        List<String> mylist = new ArrayList<>();
        mylist.add("shoes");
        mylist.add("shops");
        am.setTypes(mylist);
        am.setWebsite("https://www.abcd.com");
        return am;
    }
}
