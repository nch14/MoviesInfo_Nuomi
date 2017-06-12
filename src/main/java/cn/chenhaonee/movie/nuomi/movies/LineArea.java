package cn.chenhaonee.movie.nuomi.movies;

/**
 * Created by chenhaonee on 2017/6/10.
 */
public class LineArea {
    private String metroLineId;
    private String name;

    public LineArea() {
    }

    public LineArea(String metroLineId, String name) {

        this.metroLineId = metroLineId;
        this.name = name;
    }

    public String getMetroLineId() {
        return metroLineId;
    }

    public void setMetroLineId(String metroLineId) {
        this.metroLineId = metroLineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
