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
    public Duke(String filePath) {
        try {
            Files.createDirectories(Paths.get(filePath));
            File file = new File(filePath+ "/duke.txt");

            if (!file.exists()) {
                boolean result = file.createNewFile();
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Ui.welcome();

        String in = "";
        TaskList list = new TaskList();
        Scanner sc = new Scanner(System.in);

        while (true) {
            in = sc.nextLine();

            if (in.equals("bye")) {
                break;
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
            System.out.println("-------------------------------------------");

            FileWriting.update("./data/duke.txt", list);
        }
        Ui.bye();
    }
}
