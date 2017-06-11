package movies;

/**
 * Created by chenhaonee on 2017/6/11.
 */
public class CinemaDetail {

    private String key;
    private String value;

    public CinemaDetail() {
    }

    public CinemaDetail(String keyInt, String value) {
        this.key = parse(Integer.parseInt(keyInt));
        this.value = value;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String parse(int i) {
        switch (i) {
            case 7:
                return "3D眼镜";
            case 15:
                return "改签政策";
            case 3:
                return "停车信息";
            case 8:
                return "WIFI";
            case 6:
                return "儿票";
            case 2:
                return "交通方式";
            default:
                return null;
        }
    }

    public boolean valid(){
        return key!=null;
    }
}
