import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {

    public static void greet() {
        System.out.println("Greetings Human! I am BetaGo, your personal robot assistant!\nHow may I assist you today?\n");
    }
    public static void readCommands(TaskList storage) {

        Scanner sc = new Scanner(System.in);
        String str= sc.nextLine();
        String[] inputs = str.split(" ", 2);

        while (!str.equalsIgnoreCase("bye")) {
            if (str.equalsIgnoreCase("list")) {
                try {
                    storage.listItems();
                } catch (EmptyListException e) {
                    System.out.println("You currently have no tasks in your list!\n");
                }
            } else if (inputs[0].equalsIgnoreCase("mark") || inputs[0].equalsIgnoreCase("unmark")) {
                try {
                    storage.markUnmarkItems(str);
                    storage.saveItems();
                } catch (InvalidCommandException e) {
                    System.out.println("Please indicate a valid task number!\n");
                }
            } else if (inputs[0].equalsIgnoreCase("todo")) {
                try {
                    storage.addTodo(str);
                    storage.saveItems();
                } catch (InvalidCommandException e) {
                    System.out.println("Please indicate a valid description for your Todo task!\n");
                }
            } else if (inputs[0].equalsIgnoreCase("deadline")) {
                try {
                    storage.addDeadline(str);
                    storage.saveItems();
                } catch (InvalidCommandException e) {
                    System.out.println("Please indicate a valid description and due date for your Deadline task!\n");
                }
            } else if (inputs[0].equalsIgnoreCase("event")) {
                try {
                    storage.addEvent(str);
                    storage.saveItems();
                } catch (InvalidCommandException e) {
                    System.out.println("Please indicate a valid description and location for your Event task!\n");
                }
            }  else if (inputs[0].equalsIgnoreCase("delete")) {
                try {
                    storage.deleteItems(str);
                    storage.saveItems();
                } catch (InvalidCommandException e) {
                    System.out.println("Please indicate a valid task number!\n");
                }
            }  else {
                System.out.println("Apologies Human. I do not understand that command.\n");
            }
            str = sc.nextLine();
            inputs = str.split(" ", 2);
        }
    }

    public static void goodbye() {
        System.out.println("Goodbye Human. Till next time.\n");
    }

    public static void loadFile(TaskList storage) {
        try {
            File dir = new File("data");
            File f = new File("data/duke.txt");
            if (dir.exists()) {
                if (f.exists()) {
                    Scanner sc1 = new Scanner(f);
                    while (sc1.hasNextLine()) {
                        String str = sc1.nextLine();
                        if (str.charAt(0) == 'T') {
                            storage.loadTodo(str);
                        } else if (str.charAt(0) == 'D') {
                            storage.loadDeadline(str);
                        } else if (str.charAt(0) == 'E') {
                            storage.loadEvent(str);
                        } else {
                            System.out.println("Data file consists of invalid command.");
                        }
                    }
                    sc1.close();
                } else {
                    f.createNewFile();
                }
            } else {
                dir.mkdir();
                f.createNewFile();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error in loading file. No data file detected.");
        } catch (IOException e) {
            System.out.println("Unable to create new data file.");
        } catch (InvalidDataFileException e) {
            System.out.println("Data file consist of invalid input.");
        }
    }

    public static void main(String[] args) {
        Duke.greet();
        TaskList storage = new TaskList();
        Duke.loadFile(storage);
        Duke.readCommands(storage);
        Duke.goodbye();
    }
}
