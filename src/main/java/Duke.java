import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();
    private static FileStorage taskStorage = new FileStorage(System.getProperty("user.home"));
    private static boolean inUse = true;

    private static void startIntro() {
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        startChat();
    }

    private static void startChat() {
        readDataFile();
        Scanner sc = new Scanner(System.in);
        while (inUse) {
            String input = sc.nextLine();
            try {
                String[] str = input.split(" ", 2);
                String firstWord = str[0];
                if (str.length == 1) {
                    switch (firstWord) {
                    case "bye": {
                        endChat();
                        break;
                    }
                    case "list": {
                        displayList();
                        break;
                    }
                    case "get":
                    case "mark":
                    case "deadline":
                    case "unmark":
                    case "todo":
                    case "event":
                    case "delete":
                        throw new MissingDescriptionException(firstWord);
                    default:
                        throw new InvalidInputException();
                    }
                } else {
                    switch (firstWord) {
                    case "mark": {
                        markTaskDone(str[1]);
                        break;
                    }
                    case "unmark": {
                        markTaskUndone(str[1]);
                        break;
                    }
                    case "todo": {
                        addTask(str[1], TaskType.TODO);
                        break;
                    }
                    case "deadline": {
                        addTask(str[1], TaskType.DEADLINE);
                        break;
                    }
                    case "event": {
                        addTask(str[1], TaskType.EVENT);
                        break;
                    }
                    case "delete": {
                        deleteTask(str[1]);
                        break;
                    }
                    case "get": {
                        getDueTasks(str[1]);
                        break;
                    }
                    default:
                        throw new InvalidInputException();
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    private static void endChat() {
        inUse = false;
        taskStorage.writeToFile(listOfTasks);
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println((i + 1) + "." + listOfTasks.get(i));
        }
    }

    private static String displayListSize() {
        return "\nNow you have " + listOfTasks.size() + " tasks in the list.";
    }

    private static void addTask(String input, TaskType type) {
        Task task = null;
        try {
            switch (type) {
            case TODO: {
                task = new ToDo(input, TaskType.TODO);
                break;
            }
            case DEADLINE: {
                String[] deadlineComponents = input.split("/by ", 2);
                task = createTaskWithDate(deadlineComponents[0],
                        deadlineComponents[1].trim(), TaskType.DEADLINE);
                break;
            }
            case EVENT: {
                String[] eventComponents = input.split("/at ", 2);
                task = createTaskWithDate(eventComponents[0],
                        eventComponents[1].trim(), TaskType.EVENT);
                break;
            }
            default:
                throw new InvalidInputException();
            }
            listOfTasks.add(task);
            System.out.println("Got it. I've added this task:\n  " + task
                    + displayListSize());
        } catch (DateTimeParseException e) {
            System.out.println("Input proper date in the format YYYY-MM-DD HH:mm/YYYY-MM-DD for deadlines and " +
                    "YYYY-MM-DD HH:mm-HH:mm for events");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void markTaskDone(String str) {
        try {
            int index = Integer.parseInt(str) - 1;
            Task selectedTask = listOfTasks.get(index);
            selectedTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:" + "\n  " + selectedTask);
        } catch (NumberFormatException nfe) {
            throw new InvalidIndexException("please input a valid integer");
        } catch (IndexOutOfBoundsException er) {
            throw new InvalidIndexException("no tasks exist at this index");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void markTaskUndone(String str) {
        try {
            int index = Integer.parseInt(str) - 1;
            Task selectedTask = listOfTasks.get(index);
            selectedTask.markUndone();
            System.out.println("OK, I've marked this task as not done yet:" + "\n  " + selectedTask);
        } catch (NumberFormatException nfe) {
            throw new InvalidIndexException("please input a valid integer");
        } catch (IndexOutOfBoundsException er) {
            throw new InvalidIndexException("no tasks exist at this index");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteTask(String str) {
        try {
            int index = Integer.parseInt(str) - 1;
            Task selectedTask = listOfTasks.remove(index);
            System.out.println("Noted. I've removed this task:" + "\n  " + selectedTask
                    + displayListSize());
        } catch (NumberFormatException nfe) {
            throw new InvalidIndexException("please input a valid integer");
        } catch (IndexOutOfBoundsException er) {
            throw new InvalidIndexException("no tasks exist at this index");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void readDataFile() {
        if (!taskStorage.isDirectoryPresent()) {
            taskStorage.createDirectory();
        }
        if (!taskStorage.isFilePresent()) {
            taskStorage.createFile();
        }
        listOfTasks = taskStorage.retrieveFileContents();
    }

    private static Task createTaskWithDate(String description, String timePeriod, TaskType type) {
        LocalTime time = null;
        LocalDate date;
        Task task = null;
        String[] components = timePeriod.split(" ", 2);
        date = LocalDate.parse(components[0]);
        switch (type) {
        case DEADLINE:
            if (components.length != 1) {
                time = LocalTime.parse(components[1]);
            }
            task = new Deadline(description, date, time, type);
            break;
        case EVENT:
            LocalTime timeEnd;
            if (components.length == 1) {
                throw new DateTimeParseException("", components[0], 1);
            } else {
                String[] duration = components[1].split("-", 2);
                time = LocalTime.parse(duration[0]);
                timeEnd = LocalTime.parse(duration[1]);
            }
            task = new Event(description, date, time, timeEnd, type);
            break;
        }
        return task;
    }

    private static void getDueTasks(String input) {
        try {
            LocalDate date = LocalDate.parse(input);
            int i = 1;
            System.out.println("Here are the tasks due at this date:");
            for (Task task : listOfTasks) {
                if (task.isDateEqual(date)) {
                    System.out.println(i + "." + task);
                    i++;
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Input a valid date in the format YYYY-MM-DD");
        }
    }

    public static void main(String[] args) {
        startIntro();
    }
}
