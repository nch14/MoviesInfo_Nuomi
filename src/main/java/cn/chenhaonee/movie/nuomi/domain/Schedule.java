package cn.chenhaonee.movie.nuomi.domain;

/**
 * Created by chenhaonee on 2017/6/11.
 */
public class Schedule {
    private int remainedSeatNumber;
    private int totalSeatNumber;
    private int remainedSeatRate;

    private String language;

    private double price;
    private double memCardPrice;
    private double originalPrice;

    private long startTime;
    private long endTime;
    private String theaterName;

    private String version;

    public Schedule() {
    }

    public Schedule(int remainedSeatNumber, int totalSeatNumber, int remainedSeatRate, String language, double price, double memCardPrice, double originalPrice, long startTime, long endTime, String theaterName, String version) {
        this.remainedSeatNumber = remainedSeatNumber;
        this.totalSeatNumber = totalSeatNumber;
        this.remainedSeatRate = remainedSeatRate;
        this.language = language;
        this.price = price;
        this.memCardPrice = memCardPrice;
        this.originalPrice = originalPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.theaterName = theaterName;
        this.version = version;
    }

    public int getRemainedSeatNumber() {

        return remainedSeatNumber;
    }

    public void setRemainedSeatNumber(int remainedSeatNumber) {
        this.remainedSeatNumber = remainedSeatNumber;
    }

    public int getTotalSeatNumber() {
        return totalSeatNumber;
    }

    public void setTotalSeatNumber(int totalSeatNumber) {
        this.totalSeatNumber = totalSeatNumber;
    }

    public int getRemainedSeatRate() {
        return remainedSeatRate;
    }

    public void setRemainedSeatRate(int remainedSeatRate) {
        this.remainedSeatRate = remainedSeatRate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMemCardPrice() {
        return memCardPrice;
    }

    public void setMemCardPrice(double memCardPrice) {
        this.memCardPrice = memCardPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
