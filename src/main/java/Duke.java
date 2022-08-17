import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Task[] tasks = new Task[100];

    /**
     * Points to the index of the tasks array to insert the next task in.
     * Also acts as a counter for the number of tasks in the tasks array.
     */
    private static int pointer = 0;

    private static void speak(String message) {
        System.out.println("============================================================\n");
        System.out.println(message);
        System.out.println("============================================================");
    }

    private static void greet() {
        speak(" Greetings! My Name is Alfred ^_^\n How may I be of service today?\n");
    }

    private static void goodbye() {
        speak(" Farewell!\n");
    }

    private static void listTasks() {
        if (pointer == 0) {
            speak(" You have not added any tasks!\n");
        } else {
            StringBuilder result = new StringBuilder(" Here are your current tasks:\n");
            for (int i = 0; i < pointer; i++) {
                result.append(" ").append(i + 1).append(".").append(tasks[i].toString());
            }
            speak(result.toString());
        }
    }

    private static void addTask(int type, String task, String dateTime) {
        Task newTask;
        switch (type) {
            case 0:
                newTask = new Todo(task);
                break;
            case 1:
                newTask = new Deadline(task, dateTime);
                break;
            case 2:
                newTask = new Event(task, dateTime);
                break;
            default:
                newTask = new Task(task);
        }
        tasks[pointer] = newTask;
        pointer++;
        speak(" Understood. I have added the following task:\n" +
                "   " + newTask +
                " You have a total of " + pointer + " task(s).\n");
    }

    private static void markTask(int taskNum) throws DukeException {
        if (taskNum <= pointer && taskNum > 0) {
            speak(tasks[taskNum - 1].mark());
        } else {
            throw new DukeException("Please indicate a task no. between 1 to " + pointer);
        }
    }

    private static void unmarkTask(int taskNum) throws DukeException {
        if (taskNum <= pointer && taskNum > 0) {
            speak(tasks[taskNum - 1].unmark());
        } else {
            throw new DukeException("Please indicate a task no. between 1 to " + pointer);
        }
    }

    private static void parseCommand(String cmd) throws DukeException {
        String[] firstParse = cmd.split(" ", 2);
        String firstTerm = firstParse[0];
        boolean hasSecondTerm = firstParse.length > 1;

        switch (firstTerm) {
            case "bye":
                goodbye();
                break;
            case "list":
                listTasks();
                break;
            case "mark":
                try {
                    String secondTerm = hasSecondTerm
                            ? firstParse[1].split(" ", 2)[0]
                            : "0";
                    markTask(Integer.parseInt(secondTerm));
                    break;
                } catch (NumberFormatException e) {
                    throw new DukeException("Please indicate the task no. in digits");
                } catch (DukeException f) {
                    speak(f.toString());
                    break;
                }
            case "unmark":
                try {
                    String secondTerm = hasSecondTerm
                            ? firstParse[1].split(" ", 2)[0]
                            : "0";
                    unmarkTask(Integer.parseInt(secondTerm));
                    break;
                } catch (NumberFormatException e) {
                    throw new DukeException("Please indicate the task no. in digits");
                } catch (DukeException f) {
                    speak(f.toString());
                    break;
                }
            case "todo":
                if (hasSecondTerm) {
                    addTask(0, firstParse[1], "");
                    break;
                } else {
                    throw new DukeException("Please provide a description for the todo");
                }
            case "deadline":
                if (hasSecondTerm) {
                    String[] secondParse = firstParse[1].split("/by", 2);
                    if (secondParse.length > 1) {
                        addTask(1, secondParse[0], secondParse[1]);
                        break;
                    } else {
                        throw new DukeException("Please provide a date/time for the deadline");
                    }
                } else {
                    throw new DukeException("Please provide a description for the deadline");
                }
            case "event":
                if (hasSecondTerm) {
                    String[] secondParse = firstParse[1].split("/at", 2);
                    if (secondParse.length > 1) {
                        addTask(2, secondParse[0], secondParse[1]);
                        break;
                    } else {
                        throw new DukeException("Please provide a date/time for the event");
                    }
                } else {
                    throw new DukeException("Please provide a description for the event");
                }
            default:
                throw new DukeException("Please enter a supported command");
        }
    }

    public static void main(String[] args) {
        greet();

        String cmd = "";
        while (!cmd.equals("bye")) {
            cmd = scanner.nextLine().trim();

            try {
                parseCommand(cmd);
            } catch (DukeException e) {
                speak(e.toString());
            }
        }
    }
}
