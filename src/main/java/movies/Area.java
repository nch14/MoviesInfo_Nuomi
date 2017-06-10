package movies;

import java.util.List;

/**
 * Created by chenhaonee on 2017/6/10.
 */
public class Area {
    private List<CityArea> cityArea;
    private List<CityArea> lineArea;

    public Area() {
    }

    public Area(List<CityArea> cityArea, List<CityArea> lineArea) {

        this.cityArea = cityArea;
        this.lineArea = lineArea;
    }

    public List<CityArea> getCityArea() {
        return cityArea;
    }

    public void setCityArea(List<CityArea> cityArea) {
        this.cityArea = cityArea;
    }

    public List<CityArea> getLineArea() {
        return lineArea;
    }

    public void setLineArea(List<CityArea> lineArea) {
        this.lineArea = lineArea;
    }
}
