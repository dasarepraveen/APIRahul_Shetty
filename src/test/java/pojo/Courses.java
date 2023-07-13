package pojo;

import java.util.List;

public class Courses {
    private List<webAutomation> webAutomations;
    private List<api> apis;

    public List<webAutomation> getWebAutomations() {
        return webAutomations;
    }

    public void setWebAutomations(List<webAutomation> webAutomations) {
        this.webAutomations = webAutomations;
    }

    public List<api> getApis() {
        return apis;
    }

    public void setApis(List<api> apis) {
        this.apis = apis;
    }

    public List<mobile> getMobiles() {
        return mobiles;
    }

    public void setMobiles(List<mobile> mobiles) {
        this.mobiles = mobiles;
    }

    private List<mobile> mobiles;

}
