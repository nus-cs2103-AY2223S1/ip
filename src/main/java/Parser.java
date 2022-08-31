import org.apache.commons.text.WordUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Parser {

    private static final String LINE = "──────────────────────────────────────────\n";

    /**
     * Pretty-prints an output string
     *
     * @param output The string representing the output.
     */
    public static void printMsg(String output) {
        String[] lines = output.split("\n");
        String newStr = Arrays.stream(lines).map(line ->
                        String.format("\t %s%s\n", line.replace(line.stripLeading(), ""),
                                WordUtils.wrap(line, 40, "\n\t ", false)))
                .reduce("", String::concat);
        System.out.printf("\t%s%s\t%s%n", LINE, newStr, LINE);
    }

    /**
     * Parses a {@code Task} from a string (from the Duke data file).
     *
     * @param s The string read from the Duke data file to be parsed.
     * @return the parsed {@code Task}
     * @throws DukeException when the string in the data file is invalid.
     */
    public static Task parseTask(String s) throws DukeException {
        String[] strings = s.split(" \\| ", -1);
        Task task;
        Map<String, String> map = new HashMap<>();
        if (!strings[1].equals(" ") && !strings[1].equals("X")) {
            throw new DukeException("Error parsing Task");
        }
        boolean isDone = strings[1].equals("X");
        map.put("description", strings[2]);

        switch (strings[0]) {
        case "T":
            if (strings.length > 3) {
                throw new DukeException("Error parsing TodoTask");
            }
            task = new TodoTask(map, isDone);
            break;
        case "D":
            if (strings.length > 4) {
                throw new DukeException("Error parsing DeadlineTask");
            }
            map.put("by", strings[3]);
            task = new DeadlineTask(map, isDone);
            break;
        case "E":
            if (strings.length > 4) {
                throw new DukeException("Error parsing EventTask");
            }
            map.put("at", strings[3]);
            task = new EventTask(map, isDone);
            break;
        default:
            throw new DukeException("Error parsing Task");
        }
        return task;
    }
}
