import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private boolean isAcceptingInput;
    private ArrayList<Task> tasks;

    public Duke(String savePath, String saveName) {
        this.storage = new Storage(savePath, saveName);
        tasks = new ArrayList<>();
        storage.loadFile(this);
        isAcceptingInput = true;
        printStartupMessage();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void loadTask(Task task) {
        tasks.add(task);
    }

    private void printMessage(String msg) {
        String border = "____________________________________________________________\n";
        System.out.println(border + msg + "\n" + border);
    }

    private void printStartupMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String startupMsg = "Hello! I'm Duke\n" + "What can I do for you?";
        printMessage(startupMsg);
    }

    private String getCurrentListStatus() {
        return "Now you have " + tasks.size() + " task(s) in the list.";
    }

    private void exit() {
        isAcceptingInput = false;
        String exitMsg = "Bye. Hope to see you again soon!";
        printMessage(exitMsg);
    }

    private void listTasks() {
        int pointer = 1;
        String reply = "Here are the tasks in your list:";
        for (Task task : tasks) {
            reply += "\n" + pointer + "." + task;
            pointer++;
        }
        printMessage(reply);
    }

    private void addTask(Task task) {
        tasks.add(task);
        String msg = "Got it. I've added this task:\n"
                + task + "\n"
                + getCurrentListStatus();
        printMessage(msg);
    }

    private void removeTask(int index) {
        Task removedTask = tasks.remove(index);
        String msg = "Noted. I've removed this task:\n"
                + removedTask + "\n"
                + getCurrentListStatus();
        printMessage(msg);
    }

    private void addTodo(String description) {
        Todo todo = new Todo(description);
        addTask(todo);
    }

    private void addDeadline(String description, String date, String time) {
        Deadline deadline = new Deadline(description, date, time);
        addTask(deadline);
    }

    private void addEvent(String description,
                          String dateStart, String timeStart,
                          String dateEnd, String timeEnd) {
        Event event = new Event(description, dateStart, timeStart, dateEnd, timeEnd);
        addTask(event);
    }

    private void markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        printMessage("Nice! I've marked this task as done:\n" + task);
    }

    private void unmarkTask(int index) {
        Task task = tasks.get(index);
        task.markAsUndone();
        printMessage("OK, I've marked this task as not done yet:\n" + task);
    }

    private void processInput(String input) {
        try {
            input = input.trim();
            if (input.contains("|")) {
                throw new BannedDukeCharacterException("|");
            }
            if (input.equals("bye")) {
                exit();
                return;
            } else if (input.equals("list")) {
                listTasks();
                return;
            }

            String[] str = input.split(" ", 2);
            String cmd = str[0];
            if (str.length == 1 &&
                    (cmd.equals("mark")
                            || cmd.equals("unmark")
                            || cmd.equals("delete")
                            || cmd.equals("todo")
                            || cmd.equals("deadline")
                            || cmd.equals("event"))) {
                throw new MissingDukeInputException(cmd);
            }

            switch (cmd) {
            case "mark": {
                int taskIndex = Integer.parseInt(str[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    throw new InputIndexOutOfBoundsException("tried to mark task " + str[1]);
                }
                markTask(taskIndex);
                break;
            }
            case "unmark": {
                int taskIndex = Integer.parseInt(str[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    throw new InputIndexOutOfBoundsException("tried to unmark task " + str[1]);
                }
                unmarkTask(taskIndex);
                break;
            }
            case "delete": {
                int taskIndex = Integer.parseInt(str[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    throw new InputIndexOutOfBoundsException("tried to delete task " + str[1]);
                }
                removeTask(taskIndex);
                break;
            }
            case "todo":
                addTodo(str[1]);
                break;
            case "deadline": {
                String[] str2 = str[1].split(" /by ");
                if (str2.length == 1) {
                    throw new MissingDukeInputException(cmd);
                }
                String description = str2[0];
                String[] dateTime = Arrays.copyOf(str2[1].split(" "), 2);
                String date = dateTime[0];
                String time = dateTime[1] == null ? "" : dateTime[1];
                addDeadline(description, date, time);
                break;
            }
            case "event": {
                String[] str2 = str[1].split(" /at ");
                if (str2.length == 1) {
                    throw new MissingDukeInputException(cmd);
                }
                String description = str2[0];
                String[] dateTimes = Arrays.copyOf(str2[1].split(" "), 4);
                String dateStart = dateTimes[0];
                String timeStart = dateTimes[1] == null ? "" : dateTimes[1];
                String dateEnd = dateTimes[2] == null ? "" : dateTimes[2];
                String timeEnd = dateTimes[3] == null ? "" : dateTimes[3];
                addEvent(description, dateStart, timeStart, dateEnd, timeEnd);
                break;
            }
            default:
                throw new InvalidDukeInputException();
            }
        } catch (BannedDukeCharacterException e) {
            printMessage("☹ Woah there!!! Your input contains a \"" + e.getMessage()
                    + "\" character which is not allowed!!");
        } catch (InvalidDukeInputException e) {
            printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (MissingDukeInputException e) {
            printMessage("☹ OOPS!!! The description after a \"" + e.getMessage() + "\" is missing or incomplete!!");
        } catch (InputIndexOutOfBoundsException e) {
            printMessage("☹ OOPS!!! You " + e.getMessage()
                    + " but it doesn't exist in the list!");
        } catch (DateTimeParseException e) {
            printMessage("☹ OOPS!!! I can't recognise the date you just inputted :-(\n"
                    + "Dates should be inputted in a 'YYYY-MM-DD HH:MM' format, and events should have 2 dates.");
        }
    }

    private void runDuke() {
        Scanner sc = new Scanner(System.in);
        while (isAcceptingInput) {
            String input = sc.nextLine();
            processInput(input);
            storage.saveFile(this);
        }
        sc.close();
    }

    public static void main(String[] args) {
        String savePath = "savedata/";
        String saveName = "duke.txt";
        new Duke(savePath, saveName).runDuke();
    }
}
