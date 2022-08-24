public class Parser {

    enum Keywords {
        mark,
        unmark,
        list,
        delete,
        todo,
        deadline,
        event
    }

    public Parser() {}

    public String parseTime(String time) {
        if (time.startsWith("00")) {
            return "12" + ":" + time.substring(2) + "AM";
        } else {
            int currTime = Integer.parseInt(time);
            if (currTime >= 1300) {
                int newTime = currTime/100 - 12;
                String hour = Integer.toString(newTime);
                return hour + ":" + time.substring(2) + "PM";
            } else {
                return time.charAt(1) + ":" + time.substring(2) + "AM";
            }
        }

    }

}
