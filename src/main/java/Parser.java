import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.lang.Boolean.parseBoolean;

public class Parser {
    private static DateTimeFormatter DATE_TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static DateTimeFormatter DATE_TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Method that takes in user input and parses through it to return an executable command.
     *
     * @param input String input that the user keys in.
     * @return Command object that can be executed by Duke.
     * @throws UnknownCommandException When the input is invalid and is not executable.
     */
    public static Command parse(String input) throws UnknownCommandException {
        try {
            if (input.equals("bye")) {
                return new ExitCommand();
            }

            if (input.equals("list")) {
                return new ListCommand();
            }
            //logic to mark tasks with error handling
            if (input.indexOf("mark") == 0) {
                String substring = input.substring(5);
                //Since first task is of index 0 in ArrayList
                int taskIndex = Integer.parseInt(substring) - 1;
                return new MarkCommand(taskIndex, true);
            }
            //logic to unmark tasks with error handling
            if (input.indexOf("unmark") == 0) {
                String substring = input.substring(7);
                //Since first task is of index 0 in ArrayList
                int taskIndex = Integer.parseInt(substring) - 1;
                return new MarkCommand(taskIndex, false);
            }
            //logic to create ToDos
            if (input.indexOf("todo") == 0) {
                String description = input.substring(5);
                ToDos todo = new ToDos(description, false);
                return new AddCommand(todo);
            }
            //logic to create Deadlines
            if (input.indexOf("deadline") == 0) {
                //remove initial command
                String sub = input.substring(9);
                int timeIndex = sub.lastIndexOf("/by");
                if (timeIndex == -1) {
                    throw new MissingDeadlineException();
                }
                //get description part of input string
                String description = sub.substring(0, timeIndex - 1);
                String deadlineString = sub.substring(timeIndex + 4);
                LocalDateTime timing = LocalDateTime.parse(deadlineString, DATE_TIME_INPUT_FORMAT);
                Deadlines deadline = new Deadlines(description, timing, false);
                return new AddCommand(deadline);
            }
            //logic to create Events
            if (input.indexOf("event") == 0) {
                //remove initial command
                String sub = input.substring(6);
                int timeIndex = sub.lastIndexOf("/at");
                //get description part of input string
                if (timeIndex == -1) {
                    throw new MissingTimingException();
                }
                String description = sub.substring(0, timeIndex - 1);
                String timingString = sub.substring(timeIndex + 4);
                LocalDateTime timing = LocalDateTime.parse(timingString, DATE_TIME_INPUT_FORMAT);
                Events event = new Events(description, timing, false);
                return new AddCommand(event);
            }
            if (input.indexOf("delete") == 0) {
                String substring = input.substring(7);
                //Since first task is of index 0 in ArrayList
                int taskIndex = Integer.parseInt(substring) - 1;
                return new DeleteCommand(taskIndex);
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please indicate which task to mark!");
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid task index!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task with that index does not exist!");
        } catch (MissingTimingException e) {
            System.out.println("Please specify the timeline!");
        } catch (DateTimeParseException e) {
            System.out.println("Please input a valid time in the format DD/MM/YYYY HHMM");
        } catch (MissingDeadlineException e) {
            System.out.println("Please specify the deadline!");
        }
        throw new UnknownCommandException();
    }

    /**
     * Static method that reads task data stored in hard disk storage and returns the Task representation of the task.
     *
     * @param data String data representing a task.
     * @return Task object that encapsulates the specified task.
     */
    public static Task dataToInfo(String data) {
        String currData = data;
        String[] info = new String[4];

        //store string data between "|" characters in an array
        for (int i = 0; i < 4; i++) {
            int index = currData.indexOf('|');
            info[i] = currData.substring(0, index);
            currData = currData.substring(index + 1);
        }

        String taskType = info[0];
        boolean isDone = parseBoolean(info[1]);
        String description = info[2];
        String timingString = info[3];

        Task task;
        if (taskType.equals("T")) {
            task = new ToDos(description, isDone);
        } else if (taskType.equals("D")) {
            LocalDateTime timing = LocalDateTime.parse(timingString, DATE_TIME_INPUT_FORMAT);
            task = new Deadlines(description, timing, isDone);
        } else { //taskType.equals("E")
            LocalDateTime timing = LocalDateTime.parse(timingString, DATE_TIME_INPUT_FORMAT);
            task = new Events(description, timing, isDone);
        }
        return task;
    }

}
