import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;


public class Duke {

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public static void list(ArrayList<Task> array) {
        System.out.printf("Here are the tasks in your list:\n");
        for (int i = 0; i < Task.taskCount; i++) {
            System.out.printf("%d.%s\n", i + 1, array.get(i).toString());
        }
    }

    public static void todo(Scanner scanner, ArrayList<Task> array) throws DukeException, IOException {
        try {
            ToDo todo = new ToDo(scanner.nextLine());
            array.add(todo);
            System.out.println(todo.added());
            saveData(array);

        } catch (NoSuchElementException e) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void deadline(Scanner scanner, ArrayList<Task> array) throws DukeException, IOException{
        String description = "";
        while (!scanner.hasNext("/by")) {
            description += scanner.next();
        }
        scanner.next();
        String date = scanner.nextLine();
        Deadline deadline = new Deadline(description, date);
        array.add(deadline);
        System.out.println(deadline.added());
        saveData(array);
    }

    public static void event(Scanner scanner, ArrayList<Task> array) throws DukeException, IOException{
        String description1 = "";
        while (!scanner.hasNext("/at")) {
            description1 += scanner.next() + " ";
        }
        scanner.next(); // skips /a
        String date1 = scanner.nextLine();
        Event event = new Event(description1, date1);
        array.add(event);
        System.out.println(event.added());
        saveData(array);
    }

    public static void mark(Scanner scanner, ArrayList<Task> array) throws DukeException, IOException{
        int taskNo = scanner.nextInt();
        array.get(taskNo).markAsDone();
        System.out.printf("Nice! I've marked this task as done: \n" +
                "  [X] %s\n", array.get(taskNo).description);
        saveData(array);
    }

    public static void unmark(Scanner scanner, ArrayList<Task> array) throws DukeException, IOException{
        int taskNo = scanner.nextInt();
        array.get(taskNo).markAsUndone();
        System.out.printf("OK, I've marked this task as not done yet: \n" +
                "  [ ] %s\n", array.get(taskNo).description);
        saveData(array);
    }

    public static void delete(Scanner scanner, ArrayList<Task> array) throws DukeException, IOException{
        int taskNo2 = scanner.nextInt();
        Task t = array.get(taskNo2);
        array.remove(taskNo2);
        System.out.printf("Noted. I've removed this task:\n" +
                "  %s\n" +
                "Now you have %d tasks in the list.\n", t, Task.taskCount);
        saveData(array);
    }

    public static void saveData(ArrayList<Task> array) throws DukeException, IOException {

        String path = "data/duke.txt";
        Files.delete(Paths.get(path));
//        File f= new File(path);
//        f.createNewFile();
        FileWriter fw = new FileWriter(path, true);
        for (Task t : array) {
            fw.write(t.toString() + System.lineSeparator());
        }
        fw.close();
    }


    public static void main(String[] args) throws DukeException, IOException {
        try {
            String path = "data/duke.txt";
            if (Files.exists(Paths.get(path))) Files.delete(Paths.get(path));
            File f = new File(path);
            f.createNewFile();
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS!!! File does not exist");
        }

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskArray = new ArrayList<>();

        while (true) {
            String str = sc.nextLine();
            Scanner sc2 = new Scanner(str);
            String first = sc2.next();

            switch (first) {
                case ("bye"):
                    bye();
                    break;

                case ("list"):
                    list(taskArray);
                    break;

                case ("todo") :
                    todo(sc2, taskArray);
                    break;

                case ("deadline") :
                    deadline(sc2, taskArray);
                    break;

                case ("event") :
                    event(sc2, taskArray);
                    break;

                case ("mark") :
                    mark(sc2, taskArray);
                    break;

                case ("unmark") :
                    unmark(sc2, taskArray);
                    break;

                case ("delete") :
                    delete(sc2, taskArray);
                    break;

                default :
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}