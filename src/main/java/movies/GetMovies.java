package movies;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by chenhaonee on 2017/6/7.
 */
public class GetMovies {

    public static Movies GetMovieInfo(String movieId) {
        String url = "https://dianying.nuomi.com/movie/detail?movieId=MOVIEID";
        url = url.replace("MOVIEID", movieId);
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                    .get();
            Element container = doc.getElementById("infoCopy");
            Elements heads = container.select("#infoCopy > h4");
            Elements bodys = container.select("#infoCopy > div");

            Element head = heads.first();
            Element body = bodys.first();
            String name = head.childNode(1).childNode(0).outerHtml().replace("\n","");
            String marks = head.childNode(3).childNode(3).childNode(0).outerHtml();
            String des = body.childNode(1).childNode(0).outerHtml();
            String directors = body.childNode(3).childNode(1).outerHtml();
            String roles = body.childNode(5).childNode(1).outerHtml();
            String content = body.childNode(7).childNode(1).outerHtml();
            String area = body.childNode(9).childNode(1).outerHtml();
            String length = body.childNode(11).childNode(1).outerHtml();
            String on = body.childNode(13).childNode(1).outerHtml();
            Movies movies = new Movies(name, marks, des, directors, roles, area, length, on, content);
            return movies;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //#cinemaCinemalist  #cinemaCinemalist

    public static Movies GetCinemaList(String movieId){
        String url = "https://dianying.nuomi.com/movie/detail?movieId=MOVIEID";
        url = url.replace("MOVIEID", movieId);
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                    .get();
            System.out.println(doc.toString());
            Element list = doc.getElementById("cinemaCinemalist");


            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        //GetMovieInfo("15546");
        GetCinemaList("15546");
    }
}
