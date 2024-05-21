package org.example.flightticketmanagement.Controllers.Admin;

public class Time {
    private String hour;
    private String minutes;
    private String period;

    public Time(String hour,String minutes,String period){
        this.hour = hour;
        this.minutes = minutes;
        this.period = period;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


    @Override
    public String toString() {
        return (hour + ":" + minutes + " " + period);
    }

    public static Time parse(String timeString) {
        String[] parts = timeString.split(":");
        String hour = parts[0];
        String minute = "00"; // Giả sử phút luôn là "00"
        String period = "AM"; // Giả sử mặc định là buổi sáng

        if (timeString.contains("PM")) {
            period = "PM";
        }

        // Xử lý giờ 12 trong định dạng 12 giờ
        if (hour.equals("12")) {
            period = timeString.contains("AM") ? "AM" : "PM";
        }

        return new Time(hour, minute, period);
    }

}
