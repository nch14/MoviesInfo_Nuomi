package cn.chenhaonee.movie.nuomi.domain;

import java.util.List;

/**
 * Created by chenhaonee on 2017/6/11.
 */
public class DaySchedule {
   private String date;
   private List<Schedule> schedules;

    public DaySchedule() {
    }

    public DaySchedule(String date, List<Schedule> schedules) {

        this.date = date;
        this.schedules = schedules;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
