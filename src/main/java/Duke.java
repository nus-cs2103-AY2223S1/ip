import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dukeExceptions.MissingDescriptionException;
import dukeExceptions.UnknownCommandException;
import dukeExceptions.WrongDatetimeFormatException;

enum COMMANDS {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE
}

public class Duke {
    static final String DEADLINE_DATETIME_FORMAT = "d/MM/uuuu HHmm";
    static final String EVENT_DATETIME_START_OR_END_FORMAT = "d/MM/uuuu HHmm";
    static final String EVENT_DATETIME_INPUT_FORMAT = "d/MM/uuuu HHmm - d/MM/uuuu HHmm";

    public static void main(String[] args) {
        List<Task> userData = new ArrayList<>();

        System.out.println("Hello! I'm Duke!\nWhat can I do for you?");
        System.out.println("-----------------------------------");
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            try {
                String line = input.nextLine();

                String userInputs[] = line.split(" ");
                if (!validCommandEnum(userInputs[0].toUpperCase())) {
                    throw new UnknownCommandException();
                }

                COMMANDS mainCommand =  COMMANDS.valueOf(userInputs[0].toUpperCase());
                switch (mainCommand) {
                    case BYE: {
                        System.out.println("Bye. Hope to see you again soon!");
                        return;
                    }
                    case LIST: {
                        for (int i = 1; i < userData.size() + 1; i++) {
                            System.out.format("%s. %s\n", i, userData.get(i - 1));
                        }
                        break;
                    }
                    case MARK: {
                        userData.get(Integer.valueOf(userInputs[1])).setIsComplete(true);
                        System.out.format("Nice! I've marked this task as done: %s\n", userData.get(Integer.valueOf(userInputs[1])).toString());
                        break;
                    }
                    case UNMARK: {
                        userData.get(Integer.valueOf(userInputs[1])).setIsComplete(false);
                        System.out.format("OK, I've marked this task as not done yet: %s\n", userData.get(Integer.valueOf(userInputs[1])).toString());
                        break;
                    }
                    case TODO: {
                        userInputs = Arrays.copyOfRange(userInputs, 1, userInputs.length);

                        // ERROR HANDLING: Check for empty ToDo description
                        if (userInputs.length == 0) {
                           throw new MissingDescriptionException("todo");
                        }
                        ToDo newToDo = new ToDo(String.join(" ", userInputs));
                        userData.add(newToDo);
                        System.out.format("Got it. I've added this task:\n  %s\nNow you have %s %s in the list.\n", newToDo, userData.size(), userData.size() != 1 ? "tasks" : "task");
                        break;
                    }
                    case DEADLINE: {
                        userInputs = Arrays.copyOfRange(userInputs, 1, userInputs.length);

                        String description = splitArrayIntoSubstrings(userInputs, "/by").get(0);
                        // ERROR HANDLING: Check for empty Deadline description
                        if (description.equalsIgnoreCase("")) {
                            throw new MissingDescriptionException("deadline");
                        }

                        // ERROR HANDLING: Check for missing "by" deadline
                        String unparsedDatetime = splitArrayIntoSubstrings(userInputs, "/by").get(1);

                        if (!isValidDatetime(unparsedDatetime, DEADLINE_DATETIME_FORMAT)) {
                            throw new WrongDatetimeFormatException(DEADLINE_DATETIME_FORMAT);
                        }
                        LocalDateTime todoDeadline = LocalDateTime.parse(unparsedDatetime, DateTimeFormatter.ofPattern(DEADLINE_DATETIME_FORMAT).withResolverStyle(ResolverStyle.STRICT.STRICT));
                        Deadline newDeadline = new Deadline(description, todoDeadline);
                        userData.add(newDeadline);
                        System.out.format("Got it. I've added this task:\n  %s\nNow you have %s %s in the list.\n", newDeadline, userData.size(), userData.size() != 1 ? "tasks" : "task");
                        break;
                    }
                    case EVENT: {
                        userInputs = Arrays.copyOfRange(userInputs, 1, userInputs.length);

                        String description = splitArrayIntoSubstrings(userInputs, "/at").get(0);

                        // ERROR HANDLING: Check for empty Deadline description
                        if (description.equalsIgnoreCase("")) {
                            throw new MissingDescriptionException("event");
                        }
                        String unparsedTimeRange = splitArrayIntoSubstrings(userInputs, "/at").get(1);
                        String unparsedStartDateTime = unparsedTimeRange.split("-")[0].strip();
                        String unparsedEndDateTime = unparsedTimeRange.split("-")[1].strip();

                        if (!isValidDatetime(unparsedStartDateTime, EVENT_DATETIME_START_OR_END_FORMAT) || !isValidDatetime(unparsedEndDateTime, EVENT_DATETIME_START_OR_END_FORMAT)) {
                            throw new WrongDatetimeFormatException(EVENT_DATETIME_INPUT_FORMAT);
                        }
                        LocalDateTime startTimeRange = LocalDateTime.parse(unparsedStartDateTime, DateTimeFormatter.ofPattern(EVENT_DATETIME_START_OR_END_FORMAT).withResolverStyle(ResolverStyle.STRICT.STRICT));
                        LocalDateTime endTimeRange = LocalDateTime.parse(unparsedEndDateTime, DateTimeFormatter.ofPattern(EVENT_DATETIME_START_OR_END_FORMAT).withResolverStyle(ResolverStyle.STRICT.STRICT));

                        Event newEvent = new Event(description, startTimeRange, endTimeRange);
                        userData.add(newEvent);
                        System.out.format("Got it. I've added this task:\n  %s\nNow you have %s %s in the list.\n", newEvent, userData.size(), userData.size() != 1 ? "tasks" : "task");
                        break;
                    }
                    case DELETE: {
                        Task toDelete = userData.get(Integer.valueOf(userInputs[1]) - 1);
                        userData.remove(Integer.valueOf(userInputs[1])-1);
                        System.out.format("Noted. I've removed this task:\n %s\nNow you have %s %s in the list.\n", toDelete, userData.size(), userData.size() != 1 ? "tasks" : "task");
                        break;
                    }
                }
                Path filePath = Paths.get("data" + File.separator + "duke.txt");
                Path dirPath = Paths.get("data");

                if (!Files.exists(dirPath)) {
                    Files.createDirectory(dirPath);
                    System.out.println("Creating new directory 'data' to store data...");
                }
                if (!Files.exists(filePath)) {
                    Files.createFile(filePath);
                    System.out.println("Creating new file 'duke.txt' to store data...");
                }
                System.out.println("Saving...");
                writeEventsToFile(filePath.toString(), userData);
                System.out.println("-----------------------------------");
            } catch (UnknownCommandException err) {
                System.out.println(err);
                System.out.println("-----------------------------------");
            } catch (MissingDescriptionException err) {
                System.out.println(err);
                System.out.println("-----------------------------------");
            } catch (IOException err) {
                System.out.printf("Error writing to file: %s\n", err);
            } catch (WrongDatetimeFormatException err) {
                System.out.println(err);
                System.out.println("-----------------------------------");
            }
        }
    }

    /**
     * Splits an array into subarrays at a given delimiter, and concatenates the substrings.
     *
     * For example, given ['a', 'b', '\n', 'c', 'd'], with the delimiter specified to be '\n',
     * the function splits the array at '\n' and concatenates the split subarrays to return
     * ['ab', 'cd']
     *
     * @param arr Array of strings to be split
     * @param delimiter Array is split into two subarrays at the delimiter,
     *                  and each subarray's elements are concatenated with a " " between each element
     * @return An array of two substrings
     */
    public static List<String> splitArrayIntoSubstrings(String[] arr, String delimiter) {
        StringBuilder builder = new StringBuilder();
        List<String> res = new ArrayList<>();

        for (String s : arr) {
            if (s.equalsIgnoreCase(delimiter)) {
                res.add(builder.toString().trim());
                builder = new StringBuilder();
            } else {
                builder.append(s).append(" ");
            }
        }
        res.add(builder.toString().trim());
        return res;
    }

    /**
     * Given a string, return true if it is a valid COMMANDS enum.
     *
     * @param str
     * @return
     */
    public static boolean validCommandEnum(String str) {
        for (COMMANDS cmd : COMMANDS.values()) {
            if (str.equalsIgnoreCase(cmd.name())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Given a string and a DateTime format, validate if the given string
     * follows the DateTime format.
     *
     * @param str The string to be validated against.
     * @param format The format the string should follow.
     * @return Boolean representing if str follows the specified format.
     */
    public static boolean isValidDatetime(String str, String format) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
            dtf.parse(str);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Given a list of events, write to an external .txt file with a specified format.
     *
     * This function overwrites the specified file everytime it is called.
     *
     * @param path The path to the specified .txt file
     * @param taskList The list of tasks to be written to the .txt file
     * @throws IOException
     */
    public static void writeEventsToFile(String path, List<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (Task task : taskList) {
            fw.write(task.toString() + "\n");
        }
        fw.close();
    }
}
