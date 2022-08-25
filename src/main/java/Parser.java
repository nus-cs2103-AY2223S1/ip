import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

enum InputType {
    mark,
    unmark,
    todo,
    deadline,
    event,
    find,
}

public class Parser {
    private String DEFAULT_TIME_FORMAT = "dd/MM/yyyy HH:mm";
    private DateTimeFormatter formatter;

    public Parser() {
        this.formatter = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT);
    }

    public ParsedData parse(InputType type, String input) {
        ParsedData result;
        switch (type) {

            case todo:
            case deadline:
            case event:
                result = parseTask(input);
                break;
            default:
                //for now default is for mark and unmark
                result = parseMark(input);
        }

        return result;
    }

    private ParsedData parseMark(String input) {
        String[] first = input.split(" ", 2);
        String command = first[0];
        int listIndex = Integer.parseInt(first[1]);

        return new ParsedData(listIndex - 1);
    }

    private ParsedData parseTask(String input) {
        String command, task, during, time;
        String[] first = input.split(" ", 2);
        command = first[0];

        String[] second = first[1].split(" /", 2);
        task = second[0];

        if (second.length > 1) {
            String[] third = second[1].split(" ", 2);
            during = third[0];

            try {
                LocalDateTime dateTime = LocalDateTime.parse(third[1]);
                return new ParsedData(task, during, dateTime);
            } catch (DateTimeParseException e) {
                System.out.println("You could entire the time in this format: dd/MM/yyyy HH:mm");
            }
            time = third[1];

            return new ParsedData(task, during, time);
        }


        return new ParsedData(task);
    }
}
