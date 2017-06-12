package cn.chenhaonee.movie.nuomi.domain;

/**
 * Created by chenhaonee on 2017/6/11.
 */
public class MovieSnap {
    private int movieId;
    private String name;
    private String mark;
    private String imgUrl;

    public MovieSnap() {
    }

    public MovieSnap(int movieId, String name, String mark, String imgUrl) {

        this.movieId = movieId;
        this.name = name;
        this.mark = mark;
        this.imgUrl = imgUrl;
    }

    public int getMovieId() {

        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
