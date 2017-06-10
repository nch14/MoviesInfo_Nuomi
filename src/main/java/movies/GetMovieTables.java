package movies;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenhaonee on 2017/6/10.
 */
public class GetMovieTables {
    //

    private static String buildUrl(String movieId, String cinemaId, String cityId, String date) {
        String url = "https://dianying.nuomi.com/cinema/cinemadetail?cityId=CITY_ID&cinemaId=CINEMA_ID&movieId=MOVIE_ID&date=DATE";
        url = url.replace("MOVIE_ID", movieId);
        url = url.replace("CINEMA_ID", cinemaId);
        url = url.replace("CITY_ID", cityId);

        LocalDate thatDay = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime moonNight = thatDay.atStartOfDay();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = moonNight.atZone(zone).toInstant();
        long timestamp = Date.from(instant).getTime();
        url = url.replace("DATE", "" + timestamp);
        return url;
    }

    public static Document getDocument(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                .get();
        return doc;
    }

    public static void main(String[] args) {
        String url = buildUrl("15546", "803", "315", "2017-06-11");
        try {
            Document doc = getDocument(url);
            Node node = doc.childNode(1).childNode(2).childNode(16).childNode(0);
            Document body =Jsoup.parse(node.outerHtml());

            System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
