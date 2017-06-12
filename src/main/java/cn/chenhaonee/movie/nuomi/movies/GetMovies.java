package cn.chenhaonee.movie.nuomi.movies;

import cn.chenhaonee.movie.nuomi.domain.MovieSnap;
import cn.chenhaonee.movie.nuomi.domain.Movies;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by chenhaonee on 2017/6/7.
 */
public class GetMovies {

    public Movies GetMovieInfo(String movieId) {
        String url = "https://dianying.nuomi.com/movie/detail?movieId=MOVIEID";
        url = url.replace("MOVIEID", movieId);
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                    .get();
            Elements imgs = doc.select("#detailIntro > div > div.fl.poster");
            String imgUrl = "";
            try {
                imgUrl = imgs.first().childNode(1).attr("src");
            } catch (Exception e) {
                e.printStackTrace();
            }

            Element container = doc.getElementById("infoCopy");

            Elements heads = container.select("#infoCopy > h4");
            Elements bodys = container.select("#infoCopy > div");

            Element head = heads.first();
            Element body = bodys.first();
            String name = head.childNode(1).childNode(0).outerHtml().replace("\n", "");
            String marks = head.childNode(3).childNode(3).childNode(0).outerHtml();
            String des = body.childNode(1).childNode(0).outerHtml();
            String directors = body.childNode(3).childNode(1).outerHtml();
            String roles = body.childNode(5).childNode(1).outerHtml();
            String content = body.childNode(7).childNode(1).outerHtml();
            String area = body.childNode(9).childNode(1).outerHtml();
            String length = body.childNode(11).childNode(1).outerHtml();
            String on = body.childNode(13).childNode(1).outerHtml();
            Movies movies = new Movies(name, marks, des, directors, roles, area, length, on, content, imgUrl);
            return movies;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<MovieSnap> getRecentMovies() {
        String url = "https://dianying.nuomi.com/index";
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                    .get();
            Elements container = doc.select("ul");
            Element element = container.get(1);
            List<MovieSnap> movieSnaps = element.childNodes().stream().filter(node -> node.childNodeSize() != 0).map(node -> {
                String imgUrl = node.childNode(1).childNode(1).attr("src");
                String filmName = node.childNode(3).childNode(0).outerHtml();
                String filmIdJsonString = node.childNode(1).attr("data-data");
                JSONObject filmIdJson = new JSONObject(filmIdJsonString);
                int filmId = filmIdJson.getInt("movieId");

                String filmMark = node.childNode(5).childNode(1).childNode(0).outerHtml();
                MovieSnap movieSnap = new MovieSnap(filmId, filmName, filmMark, imgUrl);
                return movieSnap;
            }).collect(Collectors.toList());
            return movieSnaps;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        //GetMovieInfo("15546");
        //GetCinemaList("15546", "2017-06-11");
        new GetMovies().getRecentMovies();
    }


}
