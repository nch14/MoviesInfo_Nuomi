package cn.chenhaonee.movie.nuomi.movies;

/**
 * Created by chenhaonee on 2017/6/7.
 */
public class Movies {
    private String name;
    private String marks;
    private String des;
    private String director;
    private String roles;
    private String country;
    private String length;
    private String time;
    private String content;

    public Movies(String name, String marks, String des, String director, String roles, String country, String length, String time, String content) {
        this.name = name;
        this.marks = marks;
        this.des = des;
        this.director = director;
        this.roles = roles;
        this.country = country;
        this.length = length;
        this.time = time;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
