package cn.chenhaonee.movie.nuomi.movies;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chenhaonee on 2017/6/10.
 */
public class GetCinemas {
    public static int reqId = 0;

    private String buildUrl(String movieId, String cityId, String pageSize, String pageNum, String date, String metroLineId, String areaId) {
        String url = "https://dianying.nuomi.com/movie/cinema?pagelets[]=pageletCinema&reqID=REQ_ID&cityId=CITY_ID&pageSize=PAGE_SIZE&movieId=MOVIE_ID&pageNum=PAGE_NUM&t=TIMESTAMP";
        url = url.replace("MOVIE_ID", movieId);
        url = url.replace("CITY_ID", cityId);
        url = url.replace("REQ_ID", "" + reqId);
        url = url.replace("PAGE_SIZE", pageSize);
        url = url.replace("PAGE_NUM", pageNum);

        Calendar calendar = Calendar.getInstance();
        url = url.replace("TIMESTAMP", "" + calendar.getTimeInMillis());

        if (date != null) {
            LocalDate today = LocalDate.now();
            LocalDate thatDay = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (today.getDayOfMonth()!=thatDay.getDayOfMonth()){
                Period gapDays = Period.between(today, thatDay);
                int days = gapDays.getDays();
                calendar.set(Calendar.HOUR, -12);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                System.out.println(calendar.getTimeInMillis());
                url += "&date=" + ((calendar.getTimeInMillis() + days * 24 * 3600 * 1000));
            }
        }
        if (metroLineId != null)
            url += "&metroLineId=" + metroLineId;
        if (areaId != null)
            url += "&areaId=" + areaId;
        return url;
    }

    public Document getDocument(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                .get();
        reqId++;
        return doc;
    }

    public Area getAreaList(String movieId) {
        return getAreaList(movieId, "315", "6", "0", null, null, null);
    }

    public Area getAreaList(String movieId, String cityId, String pageSize, String pageNum, String date, String metroLineId, String areaId) {
        // url = "https://dianying.nuomi.com/movie/cinema?pagelets[]=pageletCinema&reqID=0&cityId=131&pageSize=6&movieId=15546&pageNum=0&t=1497081540931";
        String url = buildUrl(movieId, cityId, pageSize, pageNum, date, metroLineId, areaId);
        try {
            Document doc = getDocument(url);
            return getAreaList(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cinema> getCinemaList(String movieId, String cityId, String pageSize, String pageNum, String date, String metroLineId, String areaId) {
        String url = buildUrl(movieId, cityId, pageSize, pageNum, date, metroLineId, areaId);
        try {
            Document doc = getDocument(url);
            return getCinemaList(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cinema> getCinemaList(String movieId) {
        return getCinemaList(movieId, "315", "6", "0", null, null, null);
    }

    private Area getAreaList(Document doc) {
        Node daysAndAreas = doc.childNode(0).childNode(1).childNode(1).childNode(3);
        Node areas = daysAndAreas.childNode(3).childNode(3).childNode(3);
        Node cityArea = areas.childNode(1);
        Node lineArea = areas.childNode(3);

        List<CityArea> cityCityAreaList = cityArea.childNodes().stream().filter(node -> node.childNodeSize() != 0).map(this::parse).collect(Collectors.toList());
        List<CityArea> lineCityAreaList = lineArea.childNodes().stream().filter(node -> node.childNodeSize() != 0).map(this::parse).collect(Collectors.toList());
        Area area = new Area(cityCityAreaList, lineCityAreaList);
        return area;
    }

    private List<Cinema> getCinemaList(Document doc) {
        Node cinemaNodes = doc.childNode(0).childNode(1);
        List<Node> cinemaNodeData = cinemaNodes.childNodes();
        List<Cinema> cinemaList = cinemaNodeData.stream().filter(node -> node.childNodeSize() == 9).map(this::parseCinema).collect(Collectors.toList());
        return cinemaList;
    }

    private CityArea parse(Node areaNode) {
        String content = areaNode.outerHtml();
        content = content.replace("\\&quot;", "");
        int startAt = content.indexOf("data-id") + 9;
        int endAt = content.indexOf(" ", startAt)-1;
        String areaId = content.substring(startAt, endAt);

        Node thisItem = areaNode.childNode(0);
        String name = thisItem.childNode(0).outerHtml();
        return new CityArea(areaId, name);
    }

    private Cinema parseCinema(Node areaNode) {
        System.out.println(areaNode.outerHtml());

        String forCinemaId = areaNode.childNode(1).outerHtml();
        forCinemaId = forCinemaId.replace("\\&quot;", "");
        int cinemaIndexStartAt = forCinemaId.indexOf("cinemaId") + 9;
        int cinemaIndexEndAt = forCinemaId.indexOf("}", cinemaIndexStartAt);
        String cinemaId = forCinemaId.substring(cinemaIndexStartAt, cinemaIndexEndAt);
        String name = areaNode.childNode(1).childNode(1).childNode(0).childNode(0).outerHtml();
        String address = areaNode.childNode(1).childNode(3).childNode(0).childNode(0).outerHtml();
        String price = areaNode.childNode(7).childNode(1).childNode(1).childNode(0).outerHtml();
        return new Cinema(name, cinemaId, address, price);
    }
}
