package movies;

/**
 * Created by chenhaonee on 2017/6/10.
 */
public class Cinema {
    private String name;
    private String cinemaId;
    private String address;
    private String price;

    public Cinema() {
    }

    public Cinema(String name, String cinemaId, String address, String price) {

        this.name = name;
        this.cinemaId = cinemaId;
        this.address = address;
        this.price = price;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
