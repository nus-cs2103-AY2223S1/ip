package dateformat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HashMap;
import java.util.Locale;


public class DateFormat {
    protected String date;
    protected LocalDate dateFormatted;
    protected String afterBy;
    protected LocalTime timeFormatted;
    protected String stringDate;
    protected String stringTime;
    protected LocalDateTime dateAndTime;
    protected HashMap<String, String> months = new HashMap<>();

    public DateFormat(String date) {
        this.afterBy = date;
        this.date = date;
        this.dateFormatted = null;
        this.timeFormatted = null;

        String day;
        String month;
        String year;
        String hour;
        String minutes;
        String time = "";

        months.put("JAN", "1");
        months.put("FEB", "2");
        months.put("MAR", "3");
        months.put("APR", "4");
        months.put("MAY", "5");
        months.put("JUN", "6");
        months.put("JUL", "7");
        months.put("AUG", "8");
        months.put("SEP", "9");
        months.put("OCT", "10");
        months.put("NOV", "11");
        months.put("DEC", "12");


        if (this.afterBy.contains("/")) {
            if (this.afterBy.indexOf("/") < 2) {
                int afterDaySlashIndex = afterBy.indexOf("/");
                String afterDay;
                if (afterDaySlashIndex == 1) {
                    day = "0" + afterBy.charAt(0);
                    afterDay = afterBy.substring(afterDaySlashIndex + 1);
                } else {
                    day = afterBy.substring(0, 2);
                    afterDay = afterBy.substring(afterDaySlashIndex + 2);
                }
                int afterMonthSlashIndex = afterDay.indexOf("/");
                String afterMonth;
                if (afterMonthSlashIndex == 1) {
                    month = "0" + afterDay.charAt(0);
                    afterMonth = afterDay.substring(afterMonthSlashIndex + 1);
                } else {
                    month = afterDay.substring(0, 2);
                    afterMonth = afterDay.substring(afterMonthSlashIndex + 2);
                }
                year = afterMonth.substring(0, 4);
                if (afterMonth.length() > 5) {
                    if (afterMonth.charAt(7) == ':') {
                        time = afterMonth.substring(5, 7) + afterMonth.substring(8, 10);
                    } else {
                        time = afterMonth.substring(5);
                    }
                }
            } else if (afterBy.indexOf("/") == 3) {
                month = afterBy.substring(0, 3);
                int afterMonthSlashIndex = afterBy.indexOf("/");
                String afterMonth = afterBy.substring(afterMonthSlashIndex + 1);
                int afterDaySlashIndex = afterMonth.indexOf("/");
                String afterDay;
                if (afterDaySlashIndex == 1) {
                    day = "0" + afterMonth.charAt(0);
                    afterDay = afterMonth.substring(afterMonthSlashIndex + 1);
                } else {
                    day = afterMonth.substring(0, 2);
                    afterDay = afterMonth.substring(afterMonthSlashIndex + 2);
                }
                year = afterDay.substring(0, 4);
                if (afterDay.length() > 5) {
                    if (afterDay.charAt(7) == ':') {
                        time = afterDay.substring(5, 7) + afterDay.substring(8, 10);
                    } else {
                        time = afterDay.substring(5);
                    }
                }
            } else {
                year = afterBy.substring(0, 4);
                int afterYearSlashIndex = afterBy.indexOf("/");
                String afterYear = afterBy.substring(afterYearSlashIndex + 1);
                int afterMonthSlashIndex = afterYear.indexOf("/");
                String afterMonth;
                if (afterMonthSlashIndex == 1) {
                    month = "0" + afterYear.charAt(0);
                    afterMonth = afterYear.substring(afterMonthSlashIndex + 1);
                } else {
                    month = afterYear.substring(0, 2);
                    afterMonth = afterYear.substring(afterMonthSlashIndex + 2);
                }
                String afterDay;
                if (afterMonth.charAt(1) == ' ') {
                    day = "0" + afterMonth.charAt(0);
                    afterDay = afterMonth.substring(afterMonthSlashIndex + 1);
                } else {
                    day = afterMonth.substring(0, 2);
                    afterDay = afterMonth.substring(afterMonthSlashIndex + 2);
                }
                if (afterDay.length() > 5) {
                    if (afterDay.charAt(7) == ':') {
                        time = afterDay.substring(5, 7) + afterDay.substring(8, 10);
                    } else {
                        time = afterDay.substring(5);
                    }
                }
            }
        } else if (this.afterBy.contains("-")) {
            if (this.afterBy.indexOf("-") < 2) {
                int afterDayDashIndex = afterBy.indexOf("-");
                String afterDay;
                if (afterDayDashIndex == 1) {
                    day = "0" + afterBy.charAt(0);
                    afterDay = afterBy.substring(afterDayDashIndex + 1);
                } else {
                    day = afterBy.substring(0, 2);
                    afterDay = afterBy.substring(afterDayDashIndex + 2);
                }
                int afterMonthDashIndex = afterDay.indexOf("-");
                String afterMonth;
                if (afterMonthDashIndex == 1) {
                    month = "0" + afterDay.charAt(0);
                    afterMonth = afterDay.substring(afterMonthDashIndex + 1);
                } else {
                    month = afterDay.substring(0, 2);
                    afterMonth = afterDay.substring(afterMonthDashIndex + 2);
                }
                year = afterMonth.substring(0, 4);
                if (afterMonth.length() > 5) {
                    if (afterMonth.charAt(7) == ':') {
                        time = afterMonth.substring(5, 7) + afterMonth.substring(8, 10);
                    } else {
                        time = afterMonth.substring(5);
                    }
                }
            } else if (afterBy.indexOf("-") == 3) {
                month = afterBy.substring(0, 3);
                int afterMonthSlashIndex = afterBy.indexOf("-");
                String afterMonth = afterBy.substring(afterMonthSlashIndex + 1);
                int afterDaySlashIndex = afterMonth.indexOf("-");
                String afterDay;
                if (afterDaySlashIndex == 1) {
                    day = "0" + afterMonth.charAt(0);
                    afterDay = afterMonth.substring(afterMonthSlashIndex + 1);
                } else {
                    day = afterMonth.substring(0, 2);
                    afterDay = afterMonth.substring(afterMonthSlashIndex + 2);
                }
                year = afterDay.substring(0, 4);
                if (afterDay.length() > 5) {
                    if (afterDay.charAt(7) == ':') {
                        time = afterDay.substring(5, 7) + afterDay.substring(8, 10);
                    } else {
                        time = afterDay.substring(5);
                    }
                }
            } else {
                year = afterBy.substring(0, 4);
                int afterYearDashIndex = afterBy.indexOf("-");
                String afterYear = afterBy.substring(afterYearDashIndex + 1);
                int afterMonthSpaceIndex = afterYear.indexOf("-");
                String afterMonth;
                if (afterMonthSpaceIndex == 1) {
                    month = "0" + afterYear.charAt(0);
                    afterMonth = afterYear.substring(afterMonthSpaceIndex + 1);
                } else {
                    month = afterYear.substring(0, 2);
                    afterMonth = afterYear.substring(afterMonthSpaceIndex + 2);
                }
                String afterDay;
                if (afterMonth.charAt(1) == ' ') {
                    day = "0" + afterMonth.charAt(0);
                    afterDay = afterMonth.substring(afterMonthSpaceIndex + 1);
                } else {
                    day = afterMonth.substring(0, 2);
                    afterDay = afterMonth.substring(afterMonthSpaceIndex + 2);
                }
                if (afterDay.length() > 2) {
                    if (afterDay.charAt(7) == ':') {
                        time = afterDay.substring(5, 7) + afterDay.substring(8, 10);
                    } else {
                        time = afterDay.substring(5);
                    }
                }
            }
        } else {
            this.afterBy = this.afterBy.substring(1);
            if (this.afterBy.indexOf(" ") < 2) {
                int afterDaySpaceIndex = afterBy.indexOf(" ");
                String afterDay;
                if (afterDaySpaceIndex == 1) {
                    day = "0" + afterBy.charAt(0);
                    afterDay = afterBy.substring(afterDaySpaceIndex + 1);
                } else {
                    day = afterBy.substring(0, 2);
                    afterDay = afterBy.substring(afterDaySpaceIndex + 2);
                }
                int afterMonthSpaceIndex = afterDay.indexOf(" ");
                String afterMonth;
                if (afterMonthSpaceIndex == 1) {
                    month = "0" + afterDay.charAt(0);
                    afterMonth = afterDay.substring(afterMonthSpaceIndex + 1);
                } else {
                    month = afterDay.substring(0, 2);
                    afterMonth = afterDay.substring(afterMonthSpaceIndex + 2);
                }
                year = afterMonth.substring(0, 4);
                if (afterMonth.length() > 5) {
                    if (afterMonth.charAt(7) == ':') {
                        time = afterMonth.substring(5, 7) + afterMonth.substring(8, 10);
                    } else {
                        time = afterMonth.substring(5);
                    }
                }
                month = months.get(month.toUpperCase());
            } else if (afterBy.indexOf(" ") == 3) {
                month = afterBy.substring(0, 3);
                int afterMonthSpaceIndex = afterBy.indexOf(" ");
                String afterMonth = afterBy.substring(afterMonthSpaceIndex + 1);
                int afterDaySpaceIndex = afterMonth.indexOf(" ");
                String afterDay;
                if (afterDaySpaceIndex == 1) {
                    day = "0" + afterMonth.charAt(0);
                    afterDay = afterMonth.substring(afterDaySpaceIndex + 1);
                } else {
                    day = afterMonth.substring(0, 2);
                    afterDay = afterMonth.substring(afterDaySpaceIndex + 2);
                }
                year = afterDay.substring(0, 4);
                if (afterDay.length() > 5) {
                    if (afterDay.charAt(7) == ':') {
                        time = afterDay.substring(5, 7) + afterDay.substring(8, 10);
                    } else {
                        time = afterDay.substring(5);
                    }
                }
                month = months.get(month.toUpperCase());
            } else {
                year = afterBy.substring(0, 4);
                int afterYearSpaceIndex = afterBy.indexOf(" ");
                String afterYear = afterBy.substring(afterYearSpaceIndex + 1);
                int afterMonthSpaceIndex = afterYear.indexOf(" ");
                String afterMonth;
                if (afterMonthSpaceIndex == 1) {
                    month = "0" + afterYear.charAt(0);
                    afterMonth = afterYear.substring(afterMonthSpaceIndex + 1);
                } else {
                    month = afterYear.substring(0, 2);
                    afterMonth = afterYear.substring(afterMonthSpaceIndex + 2);
                }
                String afterDay;
                if (afterMonth.charAt(1) == ' ') {
                    day = "0" + afterMonth.charAt(0);
                    afterDay = afterMonth.substring(afterMonthSpaceIndex + 1);
                } else {
                    day = afterMonth.substring(0, 2);
                    afterDay = afterMonth.substring(afterMonthSpaceIndex + 2);
                }
                if (afterDay.length() > 2) {
                    if (afterDay.charAt(7) == ':') {
                        time = afterDay.substring(5, 7) + afterDay.substring(8, 10);
                    } else {
                        time = afterDay.substring(5);
                    }
                }
                month = months.get(month.toUpperCase());
            }
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        this.stringDate = year + "-" + month + "-" + day;
        this.dateFormatted = LocalDate.parse(year + "-" + month + "-" + day);
        if (date.length() > 10 && time.length() != 0) {
            if (time.length() <= 3) {
                hour = "0" + time.charAt(0);
                System.out.println(hour);
                minutes = time.substring(1, 3);
            } else {
                hour = time.substring(0, 2);
                minutes = time.substring(2, 4);
            }
            this.stringTime = hour + ":" + minutes + ":00";
            this.timeFormatted = LocalTime.parse(hour + ":" + minutes + ":" + "00");
        }
        if (this.timeFormatted != null) {
            this.dateAndTime = LocalDateTime.parse(this.stringDate + "T" + this.stringTime);
        }
    }

    public String formatDate(String pattern) {
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive()
                .appendPattern(pattern).toFormatter(Locale.ENGLISH);
        return this.dateFormatted.format(df);
    }

    public String formatTime() {
        return this.timeFormatted.format(DateTimeFormatter.ISO_TIME);
    }

    @Override
    public String toString() {
        if (this.timeFormatted == null) {
            return this.formatDate("MMM d yyyy");
        } else {
            return this.formatDate("MMM d yyyy") + " " + this.formatTime();
        }
    }

    public static void main(String args[]) {
        DateFormat d4 = new DateFormat("6/7/2021 1800");
        System.out.println(d4.formatDate("MMM d yyyy"));
        System.out.println(d4.formatTime());
    }
}
