package Duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import Duke.Storage;
import Duke.Parser;
import Duke.TaskList;
import Duke.Ui;
import Duke.Task.Task;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Exceptions.descriptionException;
import Duke.Exceptions.NoSuchCommandException;
public class Duke {
    private static Storage storage = new Storage();
    private static ArrayList<Task> todo;
    private static String[] commandList = new String[]{"list", "Bye", "todo", "mark", "unmark", "event", "deadline", "delete"};
    enum Commands {
        LIST,
        BYE,
        TODO,
        MARK,
        UNMARK,
        EVENT,
        DEADLINE,
        DELETE
    }


    public static String validateDescription(String description) throws descriptionException {
        if (description != "todo") {
            return description;
        }
        throw new descriptionException();
    }



    public static LocalDateTime formatTime(String dateTime) {
        String[] dateTimeArr = dateTime.split(" ");
        LocalDate date = LocalDate.parse(dateTimeArr[0]);
        Integer hour = Integer.parseInt(dateTimeArr[1].substring(0,2));
        Integer minute = Integer.parseInt(dateTimeArr[1].substring(2,4));
        LocalTime time = LocalTime.of(hour,minute,0);

        return LocalDateTime.of(date, time);
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        Scanner scanner = new Scanner((System.in));
        Parser parser = new Parser();
        TaskList taskList = new TaskList(storage.readFile());

        ui.showGreeting();
        loop: while (true) {
            String input = scanner.nextLine();
            String arr[] = input.split(" ");
            String commandString = arr[0];

            try {
                String description ;
                int startIndex;
                String deadline;
                Duke.Commands command = parser.analyzeCommand(input);
                switch (command) {
                    case BYE:
                        ui.showBye();
                        storage.writeFile(taskList.list);
                        break loop;
                    case LIST:
                        ui.showAllTask(taskList);
                        break;
                    case MARK:
                        int indexToUnMark = Integer.valueOf(arr[1]) - 1;
                        taskList.toggleTaskStatus(indexToUnMark);
                        ui.showMarkTask(taskList.get(indexToUnMark));
                        break;
                    case UNMARK:
                        int indexToMark = Integer.valueOf(arr[1]) - 1;
                        taskList.toggleTaskStatus(indexToMark);

                        ui.showUnmarkTask(taskList.get(indexToMark));
                        break;
                    case TODO:
                        try {
                            description = validateDescription(String.join(" ", Arrays.copyOfRange(arr, 1, arr.length)));

                            taskList.addTask(command, description);
                            ui.showAddTask(taskList, taskList.get(taskList.length() - 1));
                        } catch (descriptionException err) {
                            System.out.println(err.toString());
                        }
                        break;
                    case DEADLINE:
                    case EVENT:
                        startIndex = Arrays.asList(arr).indexOf(commandString.equals("deadline") ? "/by" : "/at");
                        try {
                            description = validateDescription(String.join(" ", Arrays.copyOfRange(arr, 1, startIndex)));
                            deadline = String.join(" ", Arrays.copyOfRange(arr, startIndex + 1, arr.length));
                            taskList.addTask(command, description, formatTime(deadline));
                            ui.showAddTask(taskList, taskList.get(taskList.length() - 1));

                        } catch (descriptionException err) {
                            System.out.println(err.toString());
                        }
                        break;
                    case DELETE:
                        int index = Integer.valueOf(arr[1]);
                        Task task = taskList.get(index - 1);
                        taskList.deleteTask(index);
                        ui.showRemoveTask(taskList, task);

                    default:
                }
            } catch (NoSuchCommandException err) {
                System.out.println(err);
            }
        }
    }
}
