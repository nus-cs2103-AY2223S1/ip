import java.util.ArrayList;
import java.util.Scanner;

public class Interact {
    private String line = "_______________________________________";
    private TasksManager tasksManager;

    public void start() {
        tasksManager = new TasksManager();
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
    }
    public void handle(String word) throws DukeException {
        if (word.startsWith("bye") || word.startsWith("Bye")) {
            bye();
        } else if (word.startsWith("list") || word.startsWith("List")) {
            showList();
        } else if (word.startsWith("done") || word.startsWith("Done")) {
            handleDone(word);
        } else if (word.startsWith("delete") || word.startsWith("Delete")) {
            handleDelete(word);
        } else if (word.startsWith("todo") || word.startsWith("deadline") || word.startsWith("event")) {
            handleTasks(word);
        } else {
            throw new DukeException("bad input");
        }
    }

    public void handleTasks(String word) throws DukeException{
        String[] splitted = word.split(" ", 2);
        if (splitted.length < 2) {
            throw new EmptyDescException(splitted[0]);
        }

        if (splitted[0].equals("todo")) {
            addTask(splitted[1], null, 'T');
        } else {
            String[] stringAndDate;
            if (splitted[0].equals("deadline")) {
                stringAndDate = splitted[1].split("/by");
            } else {
                stringAndDate = splitted[1].split("/at");
            }

            if (stringAndDate.length < 2) {
                throw new BadFormatException("incorrect format", splitted[0]);
            }

            if (splitted[0].equals("deadline")) {
                addTask(stringAndDate[0], stringAndDate[1], 'D');
            } else {
                addTask(stringAndDate[0], stringAndDate[1], 'E');
            }
        }
    }

    public void addTask(String word, String date, char tag) {
        System.out.println(line);
        Task newTask;
        if (tag == 'D') {
            newTask = new Deadline(word, date);
        } else if (tag == 'E') {
            newTask = new Event(word, date);
        } else {
            newTask = new Todo(word);
        }
        tasksManager.addTask(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasksManager.numTasks() + " tasks in your list");
        System.out.println(line);
    }

    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void bye() {
        tasksManager.closePW();
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        System.exit(0);
    }

    public void showList() {
        tasksManager.showList();
    }

    public boolean isValidNum(String num) {
        char[] charas = num.toCharArray();
        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(charas[i])) {
                return false;
            }
        }
        return true;
    }

    public void handleDone(String word) throws DukeException {
        System.out.println(line);
        String[] doneTasks = word.split(" ");
        if (doneTasks.length < 2) {
            throw new BadFormatException("done", "done");
        }
        System.out.println("Nice! I've marked this task as done:");

        for (int i = 1; i < doneTasks.length; i++) {
            if (!isValidNum(doneTasks[i])) {
                throw new DukeException("invalid task");
            }
            int taskNo = Integer.parseInt(doneTasks[i]);
            tasksManager.markTaskAsDone(taskNo);
        }
        System.out.println(line);
    }

    public void handleDelete(String word) throws DukeException {
        System.out.println(line);
        String[] deleteTasks = word.split(" ");

        if (deleteTasks.length < 2) {
            throw new BadFormatException("failed delete", "delete");
        }
        System.out.println("The following task has been deleted:");
        for (int i = 1; i < deleteTasks.length; i++) {
            if (!isValidNum(deleteTasks[i])) {
                throw new DukeException("invalid task");
            }
            int taskNo = Integer.parseInt(deleteTasks[i]);
            tasksManager.deleteTask(taskNo);
        }
        System.out.println(line);
    }


    public static void main(String[] args) {
        Interact interact = new Interact();
        interact.start();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String nextWord = sc.nextLine();
            if (!nextWord.equals("")) {
                try {
                    interact.handle(nextWord);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
        }
    }
}

