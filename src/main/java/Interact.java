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
            Decoder.handleDone(word, tasksManager);
        } else if (word.startsWith("delete") || word.startsWith("Delete")) {
            Decoder.handleDelete(word, this.tasksManager);
        } else if (word.startsWith("todo") || word.startsWith("deadline") || word.startsWith("event")) {
            tasksManager.addTask(Decoder.handleTasks(word));
        } else {
            throw new DukeException("bad input");
        }
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

