/**
 *@author Kundai Dziwa <a href="mailto:kundai.dziwa@ucalgary.ca">
 *         kundai.dziwa@ucalgary.ca</a>
 *
 *@author Tommy Dinh <a href="mailto:tommy.dinh@ucalgary.ca">
 *         tommy.dinh@ucalgary.ca</a>
 * 
 *@author Tien Dat Johny Do <ahref ="tiendat.do@ucalgary.ca">
 *        tiendat.do@ucalgary.ca</a>
 * 
 *@author Stalin D Cunha<a href="mailto:stalin.dcunha@ucalgary.ca">
 *         stalin.dcunha@ucalgary.ca</a>
 * 
 * @version 1.1
 * @since 1.0
 */

package Domain;

public class Property {
    private String address;
    private String houseType;
    private int numBathrooms;
    private int numBedrooms;
    private String furnishedStatus;
    private String cityQuadrant;
    private double price;
    private String stateofListing;
    private int idNum;
    private Landlord landlord;

    // Constructors
    public Property(String a, String ht, int ba, int be, String fs, String cq, double p) {
        address = a;
        houseType = ht;
        numBathrooms = ba;
        numBedrooms = be;
        furnishedStatus = fs;
        cityQuadrant = cq;
        price = p;
    }

    // Getters and Setters
    public String getAddress() {
        return address;
    }
    public String getHouseType() {
        return houseType;
    }
    public int getBathrooms() {
        return numBathrooms;
    }
    public int getBedrooms() {
        return numBedrooms;
    }
    public String getFurnishedStatus() {
        return furnishedStatus;
    }
    public String getCityQuadrant() {
        return cityQuadrant;
    }
    public double getPrice() {
        return price;
    }
    public String getStateofListing() {
        return stateofListing;
    }
    public int getID() {
        return idNum;
    }
    public Landlord getLandlord() {
        return landlord;
    }
    public void setSOL(String sol) {
        stateofListing = sol;
    }
    public void setPrice(double p) {
        price = p;
    }
    public void setID(int id) {
        idNum = id;
    }
    public void setLandlord(Landlord l) {
        landlord = l;
    }
}
