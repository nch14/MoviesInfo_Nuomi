package cn.chenhaonee.movie.nuomi.controller;

import cn.chenhaonee.movie.nuomi.domain.*;
import cn.chenhaonee.movie.nuomi.movies.*;
import cn.chenhaonee.movie.nuomi.vo.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhaonee on 2017/6/9.
 */
@CrossOrigin
@Api(value = "影院信息", description = "")
@RequestMapping(value = "/cinema")
@RestController
public class CinemaController {

    @RequestMapping(value = "/nextWeek", method = RequestMethod.GET)
    @ApiOperation(value = "获得接下来七天的日期", notes = "")
    public ResponseData<List<String>> getNextWeek() {
        LocalDate today = LocalDate.now();
        List<String> days = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String dateInString = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            days.add(dateInString);
            today = today.plusDays(1);
        }
        return new ResponseData<>(days);
    }

    @RequestMapping(value = "/areaList", method = RequestMethod.GET)
    @ApiOperation(value = "获得区域列表", notes = "")
    public ResponseData<List<CityArea>> getAreaList(@RequestParam(value = "movieId") String movieId) {
        Area area = new GetCinemas().getAreaList(movieId);
        List<CityArea> cityAreas = area.getCityArea();
        cityAreas.add(0, new CityArea("-1", "全部"));
        return new ResponseData<>(cityAreas);
    }

    @RequestMapping(value = "/cinemaInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获得电影院详细", notes = "")
    public ResponseData<List<CinemaDetail>> getInfoList(@RequestParam(value = "cinemaId") String cinemaId) {
        List<CinemaDetail> cinemaDetails = new GetMovieTables().getCinemaInfo(cinemaId);
        return new ResponseData<>(cinemaDetails);
    }


    @RequestMapping(value = "/cinemaList", method = RequestMethod.GET)
    @ApiOperation(value = "获得指定影片影院列表", notes = "date以及area请按照服务端返回的值作为参数进行请求")
    public ResponseData<List<Cinema>> getCinemaList(@RequestParam(value = "movieId") String movieId,
                                                    @RequestParam(value = "date") String date,
                                                    @RequestParam(value = "cityId") String cityId,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "6") String pageSize,
                                                    @RequestParam(value = "pageNum", required = false, defaultValue = "0") String pageNum,
                                                    @RequestParam(value = "areaId") String areaId) {
        if (areaId.equals("-1"))
            areaId = null;
        List<Cinema> cinemaList = new GetCinemas().getCinemaList(movieId, cityId, pageSize, pageNum, date, null, areaId);
        return new ResponseData<>(cinemaList);
    }

    @RequestMapping(value = "/cinemaItemList", method = RequestMethod.GET)
    @ApiOperation(value = "获得指定日期电影安排列表", notes = "date以及area请按照服务端返回的值作为参数进行请求")
    public ResponseData<DaySchedule> getMovieItemList(@RequestParam(value = "movieId") String movieId,
                                                      @RequestParam(value = "cinemaId") String cinemaId,
                                                      @RequestParam(value = "date") String date) {
        String[] dateInString = date.split("-");
        if (dateInString.length == 3) {
            String month = dateInString[1].replace("0", "");
            String day = dateInString[2];
            String result = month + "月" + day + "日";
            List<DaySchedule> daySchedules = new GetMovieTables().getDaySchedules(movieId, cinemaId, date);
            DaySchedule thatDaySchedulesBox = daySchedules.stream().filter(daySchedule -> daySchedule.getDate().contains(result)).findFirst().get();
            return new ResponseData<>(thatDaySchedulesBox);
        }
        return null;
    }
}
