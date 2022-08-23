import java.time.LocalDateTime;
import java.util.ArrayList;

public class Parser {
    private static String[] splitInput(String input, String regex) {
        return input.split(regex,2);
    }

    static LocalDateTime extractDateTime(String rawInput, String timeIdentifier) {
        String filterDate = splitInput(rawInput, timeIdentifier)[1];
        String[] dateAndTime = filterDate.split(" ");
        int time =  Integer.parseInt(dateAndTime[1]);
        int hours = time / 100;
        int minutes = time % 100;
        String[] splitDate = dateAndTime[0].split("-");
        ArrayList<Integer> dateInfo = new ArrayList<Integer>();
        for (String s : splitDate) {
            dateInfo.add(Integer.parseInt(s));
        }
        return LocalDateTime.of(dateInfo.get(0), dateInfo.get(1), dateInfo.get(2),
                hours, minutes);
    }

    static String extractCommand(String rawInput) {
        return splitInput(rawInput, " ")[0];
    }

    static String extractDetail(String rawInput, String timeIdentifier) {
        if (timeIdentifier.length() > 0) {
            return splitInput(splitInput(rawInput, " ")[1], timeIdentifier)[0];
        } else {
            return splitInput(rawInput, " ")[1];
        }
    }

}
