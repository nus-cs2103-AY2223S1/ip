import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;

public class Duke {
    /**
     * Main class used to handle inputs
     */
    private TaskList list;
    private Storage storage;
    private String filePath;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        list = new TaskList(storage.load());
        this.filePath = filePath;
    }

    public void run() {
        Ui.welcome();

        String in = "";
        Scanner sc = new Scanner(System.in);

        while (true) {
            in = sc.nextLine();

            if (in.equals("bye")) {
                break;
            } else if (in.equals("clear")) {
                list.clear();
            } else if (in.equals("list")) {
                list.printTasks();
            } else if (in.startsWith("mark")) {
                int index = Integer.valueOf(in.split(" ")[1]) - 1;
                list.mark(index);
            } else if (in.startsWith("unmark")) {
                int index = Integer.valueOf(in.split(" ")[1]) - 1;
                list.unmark(index);
            } else if (in.startsWith("delete")) {
                int index = Integer.valueOf(in.split(" ")[1]) - 1;

                try {
                    if (index >= list.getSize()) {
                        throw new InvalidCommandException();
                    }

                    Task task = list.getTask(index);
                    Ui.delete(task);
                    list.delete(index);
                    Ui.countTasks(list);
                }

                catch (InvalidCommandException e) {
                    System.out.println(e.toString());
                }

            } else {
                try {
                    Task task = TaskCreator.CreateTask(in);
                    if (task == null) {
                        throw new InvalidCommandException();
                    } else if (task.getClass() == ErrorTask.class) {
                        throw new InvalidDateException();
                    } else if (task.description.length() < 1) {
                        throw new NoDescriptionException();
                    } else {
                        list.add(task);
                        Ui.add(task);
                    }
                }

                catch (InvalidCommandException e) {
                    System.out.println(e.toString());
                }

                catch (InvalidDateException e) {
                    System.out.println(e.toString());
                }

                catch (NoDescriptionException e){
                    System.out.println(e.toString());
                }
            }
            Ui.line();

            FileWriting.update("./data/duke.txt", list);
        }
        Ui.bye();
    }
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
