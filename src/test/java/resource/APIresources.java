package resource;

public enum APIresources {

    AddPlaceApi("/maps/api/place/add/json"),
    getPlaceApi("/maps/api/place/get/json"),
    deletePlaceApi("/maps/api/place/delete/json");
    private String apiResourcse;

    APIresources(String apiResourcse) {
        this.apiResourcse=apiResourcse;

    }
    public String getapiResourcse(){
        return apiResourcse;
    }
}
