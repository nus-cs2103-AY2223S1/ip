import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {
    Scanner scanner = new Scanner(System.in);
    int index = 0;
    ArrayList<Task> inputArray = new ArrayList<>();

    Duke() {

        Greet();
        loadTasks();
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    Bye();
                    break;
                } else if (input.equals("list")) {
                    List();
                } else if (input.startsWith("mark")) {
                    markTask(input);
                    saveTasks();
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input);
                    saveTasks();
                } else if (input.startsWith("todo")) {
                    addTodo(input);
                    saveTasks();
                } else if (input.startsWith("deadline")) {
                    addDeadlines(input);
                    saveTasks();
                } else if (input.startsWith("event")) {
                    addEvents(input);
                    saveTasks();
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                    saveTasks();
                } else {
                    System.out.println("Sorry, I do not accept that as a task!");
                    continue;
                }
                reportNum();
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException |
                    NumberFormatException | NullPointerException e) {
                System.out.println("error" + e);
                System.out.println("Please input correctly");
            }
        }
    }

    /*
     * Reports the number of items in the list
     *
     */
    private void reportNum() {
        System.out.println("Now you have " + index + " in the list.");
    }

    /*
     * Sends a greeting to the user
     *
     */
    private void Greet() {
        System.out.println("Hello! I'm BotChat123 \nWhat can I do for you?");
    }

    /*
     * Terminates the conversation with the user
     *
     */
    private void Bye(){
        System.out.println("Bye. Please chat with me again!");
    }

    /*
     * Reports all the items that are in the list
     *
     */
    private void List() {
        for (int i = 0; i < index; i++) {
            System.out.println(i + 1 + ": " + inputArray.get(i));
        }
    }

    /*
     * Adds a todo to the list
     *
     * @param input
     */
    private void addTodo(String input) {
        try {
            if (input.length() > 5) {
                inputArray.add(index, new Todo(input.substring(5)));
                System.out.println("Got it. I've added this task:\n" + inputArray.get(index));
                index++;
            } else {
                System.out.println("Please fill in what you want on the Todo!");
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please fill in what you want on the Todo!");
        }
    }

    /*
     * Adds an event to the list
     *
     * @param input
     */
    private void addEvents(String input) {
        try {
            if (input.length() > 6) {
                int slashIndex = input.indexOf("/");
                inputArray.add(index, new Events(input.substring(6, slashIndex), input.substring(slashIndex + 4)));
                System.out.println("Got it. I've added this task:\n" + inputArray.get(index));
                index++;
            } else {
                System.out.println("Please fill in what you want on the Events!");
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please fill in what you want on the Events!");
        }
    }

    /*
     * Adds a deadline to the list
     *
     * @param input
     */
    private void addDeadlines(String input) {
        try {
            if (input.length() > 9) {
                int slashIndex = input.indexOf("/");
                inputArray.add(index, new Deadlines(input.substring(9, slashIndex), input.substring(slashIndex + 4)));
                System.out.println("Got it. I've added this task:\n" + inputArray.get(index));
                index++;
            } else {
                System.out.println("Please fill in what you want on the Deadlines!");
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please fill in what you want on the Deadlines!");
        }
    }

    /*
     * Marks a task in the list as done
     *
     * @param input
     */
    private void markTask(String input) throws NullPointerException, ArrayIndexOutOfBoundsException,
            NumberFormatException {
        int markIndex = Integer.parseInt(input.substring(5));
        inputArray.get(markIndex -1).done();
        System.out.println("Nice! I've marked this task as done liao!:\n"
                + inputArray.get(markIndex - 1));
    }

    /*
     * Marks a task in the list as not done
     *
     * @param input
     */
    private void unmarkTask(String input) throws NullPointerException, ArrayIndexOutOfBoundsException,
            NumberFormatException {
        int markIndex = Integer.parseInt(input.substring(7));
        inputArray.get(markIndex - 1).unDone();
        System.out.println("OK, I unmark this task as not done le:\n"
                + inputArray.get(markIndex - 1));
    }

    /*
     * Deletes a task that is in the list
     *
     * @param input
     */
    private void deleteTask(String input) throws NullPointerException, ArrayIndexOutOfBoundsException , NumberFormatException {
        int markIndex = Integer.parseInt(input.substring(7));
        System.out.println("Swee! Task removed: " + inputArray.get(markIndex - 1));
        inputArray.remove(markIndex - 1);
        index--;
    }

    private void saveTasks() {
        try {
            File myFile = new File("./data/Duke.txt");
            OutputStream os = new FileOutputStream(myFile);
            PrintWriter pw = new PrintWriter(os);

            for (int i = 0; i < index; i++) {
                pw.println(inputArray.get(i).writeData());
            }

            pw.close();
        } catch (IOException e) {
            System.out.println("error" + e);
        }
    }

    private void loadTasks() {
        try {
            Boolean isDirectoryExist = Files.isDirectory(Paths.get("./data"));
            if (!isDirectoryExist) {
                new File("data").mkdir();
            }

            Boolean isFileExist = Files.isRegularFile(Paths.get("./data/Duke.txt"));
            if (!isFileExist) {
                new File("./data/Duke.txt").createNewFile();
            }

            FileReader fr = new FileReader("./data/Duke.txt");
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            System.out.println("These are the tasks that you had previously!");

            while (line != null) {
                String[] split = line.split("#");
                String type = split[0];
                String done = split[1];
                String name = split[2];
                String date;
                Task task = null;
                switch (type) {
                    case ("T"):
                        task = new Todo(name);
                        break;
                    case ("D"):
                        date = split[3];
                        task = new Deadlines(name, date);
                        break;
                    case ("E"):
                        date = split[3];
                        task = new Events(name, date);
                }

                if (done.equals("1")) {
                    task.done();
                }
                inputArray.add(index, task);
                index++;
                line = br.readLine();
            }

            br.close();

            for (int i = 0; i < index; i++) {
                System.out.println(i + 1 + ": " + inputArray.get(i));
            }
            System.out.println("Please use me to fill in more tasks");
        } catch (IOException e) {
            System.out.println("error" + e);
        }

    }

    public static void main(String[] args) {
//        System.out.println("working directory = " + System.getProperty("user.dir"));
        Duke duke = new Duke();
    }
}
