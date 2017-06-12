package cn.chenhaonee.movie.nuomi.domain;

/**
 * Created by chenhaonee on 2017/6/10.
 */
public class CityArea {
    private String id;
    private String name;

    public CityArea() {
    }

    public CityArea(String id, String name) {

        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
