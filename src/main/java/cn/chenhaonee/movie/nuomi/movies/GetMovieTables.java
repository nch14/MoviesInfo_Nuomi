package cn.chenhaonee.movie.nuomi.movies;

import cn.chenhaonee.movie.nuomi.domain.CinemaDetail;
import cn.chenhaonee.movie.nuomi.domain.DaySchedule;
import cn.chenhaonee.movie.nuomi.domain.Schedule;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by chenhaonee on 2017/6/10.
 */
public class GetMovieTables {

    public List<DaySchedule> getDaySchedules(String movieId, String cinemaId, String date) {
        String url = buildUrl(movieId, cinemaId, date);
        //String url = buildUrl("9720", "803", "315", "2017-06-11");

        try {
            Document doc = getDocument(url);
            Node node = doc.childNode(1).childNode(2).childNode(16).childNode(0);
            Document body = Jsoup.parse(node.outerHtml());
            Node films = body.childNode(0).childNode(1);
            Node thisFilm = films.childNodes().stream().filter(item -> item.outerHtml().contains("\"movieId\\\":" + movieId)).findFirst().get();
            String content = thisFilm.outerHtml();
            content = content.replace("\\", "");
            int startFrom = content.indexOf("{\"movieId\"");
            content = content.substring(startFrom);
            int schedulesStartAt = content.indexOf("\"schedules\"");
            int endAt = content.indexOf(",\"movieActivityInfos\"");
            content = content.substring(schedulesStartAt, endAt);
            content = '{' + content;
            content = content + '}';
            JSONObject jsonObject = new JSONObject(content);
            JSONArray list = jsonObject.getJSONArray("schedules");
            List<DaySchedule> dayScheduleList = new CopyOnWriteArrayList<>();
            list.forEach(o -> parseDay(o, dayScheduleList));
            return dayScheduleList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CinemaDetail> getCinemaInfo(String cinemaId) {
        String url = buildCinemaDetailUrl(cinemaId);
        try {
            Document doc = getDocument(url);
            Node node = doc.childNode(1).childNode(2).childNode(16).childNode(0);
            Document body = Jsoup.parse(node.outerHtml());
            Node cinemaInfosBox = body.childNode(0).childNode(1);
            Node cinemaInfos = cinemaInfosBox.childNode(cinemaInfosBox.childNodeSize() - 1);
            String content = cinemaInfos.outerHtml();
            content = content.replace("\\", "");
            int startFrom = content.indexOf("\"features\"");
            content = content.substring(startFrom);
            int endAt = content.indexOf(']') + 1;
            content = content.substring(0, endAt);
            content = '{' + content;
            content = content + '}';
            JSONObject jsonObject = new JSONObject(content);
            JSONArray features = jsonObject.getJSONArray("features");
            List<Object> detailsList = features.toList();
            List<CinemaDetail> details = detailsList.stream().map(this::parseDetails).filter(cinemaDetail -> cinemaDetail != null).filter(CinemaDetail::valid).collect(Collectors.toList());
            return details;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        new GetMovieTables().getDaySchedules("9720", "", "");
    }

    private String buildUrl(String movieId, String cinemaId, String date) {
        String url = "https://dianying.nuomi.com/cinema/cinemadetail?cinemaId=CINEMA_ID&movieId=MOVIE_ID&date=DATE";
        url = url.replace("MOVIE_ID", movieId);
        url = url.replace("CINEMA_ID", cinemaId);

        LocalDate thatDay = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime moonNight = thatDay.atStartOfDay();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = moonNight.atZone(zone).toInstant();
        long timestamp = Date.from(instant).getTime();
        url = url.replace("DATE", "" + timestamp);
        return url;
    }

    private String buildCinemaDetailUrl(String cinemaId) {
        String url = "https://dianying.nuomi.com/cinema/cinemadetail?cinemaId=CINEMA_ID";
        url = url.replace("CINEMA_ID", cinemaId);
        return url;
    }

    private Document getDocument(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                .get();
        return doc;
    }

    private void parseDay(Object o, List<DaySchedule> list) {
        if (o instanceof JSONObject) {
            JSONObject item = (JSONObject) o;
            String today = item.getString("dateStr");
            JSONArray schedules = item.getJSONArray("dailySchedules");

            List<Schedule> scheduleList = new CopyOnWriteArrayList<>();
            schedules.forEach(schedule -> parseSchedules(schedule, scheduleList));
            DaySchedule daySchedule = new DaySchedule(today, scheduleList);
            list.add(daySchedule);
        }
    }

    private void parseSchedules(Object o, List<Schedule> schedules) {
        if (o instanceof JSONObject) {
            JSONObject item = (JSONObject) o;
            int remainedSeatNumber = item.getInt("remainedSeatNumber");
            int totalSeatNumber = item.getInt("totalSeatNumber");
            int remainedSeatRate = item.getInt("remainedSeatRate");

            String language = item.getString("language");

            int price = item.getInt("price");
            double priceToShow = ((double) price) / 100;
            int memCardPrice = item.getInt("memCardPrice");
            double memCardPriceToShow = ((double) memCardPrice) / 100;
            int originalPrice = item.getInt("originalPrice");
            double originalPriceToShow = ((double) originalPrice) / 100;

            long startTime = item.getLong("startTime");
            long endTime = item.getLong("endTime");
            String theaterName = item.getString("theaterName");

            String version = item.getString("version");
            Schedule schedule = new Schedule(remainedSeatNumber, totalSeatNumber, remainedSeatRate, language, priceToShow, memCardPriceToShow, originalPriceToShow, startTime, endTime, theaterName, version);
            schedules.add(schedule);
        }
    }

    private CinemaDetail parseDetails(Object o) {
        if (o instanceof Map) {
            Map item = (Map) o;
            String key = (String) item.get("key");
            String value = (String) item.get("value");
            CinemaDetail cinemaDetail = new CinemaDetail(key, value);
            return cinemaDetail;
        }
        return null;
    }
}
