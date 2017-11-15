package anandgaur.com.maptesting;

/**
 * Created by Anand Gaur
 */

public class PumpDetail {
    String placename,placevicinity,distance;
    double lat;
    double longi;

    PumpDetail(String placename, double lat, double longi, String distance, String placevicinity){
        this.placename=placename;
        this.lat=lat;
        this.longi=longi;
        this.placevicinity=placevicinity;
        this.distance=distance;
        
    }

    public PumpDetail() {

    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getLat() {
        return lat;
    }

    public double getLongi() {
        return longi;
    }

    public String getPlacename() {
        return placename;
    }

    public String getPlacevicinity() {
        return placevicinity;
    }


    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setPlacevicinity(String placevicinity) {
        this.placevicinity = placevicinity;
    }
    
    
}
