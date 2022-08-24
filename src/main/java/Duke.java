import Storage.DukeEncoder;
import TaskList.*;
import Ui.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // List to store text entered by the user and display them back to the user when requested
    public static ArrayList<Task> workList = Duke.loadDataFromList();
    /**
     * Start the program
     */
    private static void greet() {
        System.out.println("Hello I am\n" + Constants.LOGO);
        System.out.println("May I help you?");
    }

    /**
     * Exit when user type 'bye'
     */
    private static void exit() {
        System.out.println("Great that you joined. See you soon. Bye!");
    }

    /**
     * Print all item in the word list
     */
    private static void listItems() {
        System.out.println(Constants.LISTING_MESSAGE);
        for (int i = 0; i < Duke.workList.size(); i++) {
            System.out.println((i+1) + ") " + Duke.workList.get(i).toString());
        }
    }

    private static ArrayList<Task> loadDataFromList() {
        ArrayList<Task> workList = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File("src/main/List.txt"));
            while (s.hasNextLine()){
                String[] task = s.nextLine().split("\\|");
                String typeOfTask = task[0];
                switch (typeOfTask) {
                    case "T":
                        workList.add(new ToDo(task[2], task[1].equals("1")));
                        break;
                    case "D":
                        workList.add(new Deadline(task[2], task[1].equals("1"), task[3]));
                        break;
                    case "E":
                        workList.add(new Event(task[2], task[1].equals("1"), task[3]));
                        break;
                }
            }
            s.close();
            return workList;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return new ArrayList<Task>();
    }

    private static void process() {
        // User Input
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        // Processing
        while (!userInput.equals(Constants.EXIT)) {
            String typeOfTask = userInput.split(" ")[0];
            int index;
            switch (typeOfTask) {
                case Constants.LIST:
                    Duke.listItems();
                    break;
                case Constants.UNMARK:
                    try {
                        userInput.substring(8);
                        index = Integer.parseInt(userInput.split(" ")[1]);
                        workList.get(index - 1).unmark();
                    } catch (StringIndexOutOfBoundsException e) {
                        new DukeException.EmptyMarkingException();
                        break;
                    } catch (NumberFormatException e) {
                        new DukeException.EmptyMarkingException();
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        new DukeException.EmptyMarkingException();
                        break;
                    }
                    break;
                case Constants.MARK:
                    try {
                        userInput.substring(6);
                        index = Integer.parseInt(userInput.split(" ")[1]);
                        workList.get(index - 1).markAsDone();
                    } catch (StringIndexOutOfBoundsException e) {
                        new DukeException.EmptyMarkingException();
                        break;
                    } catch (NumberFormatException e) {
                        new DukeException.EmptyMarkingException();
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        new DukeException.EmptyMarkingException();
                        break;
                    }
                    break;
                case Constants.TODO:
                    try {
                        // Error when to-do followed by a blank space
                        userInput.substring(6);
                        // Error when just to-do
                        TaskOperation.add(new ToDo(userInput.substring(5)), workList);
                    } catch (StringIndexOutOfBoundsException e) {
                        new DukeException.EmptyTodoException();
                        break;
                    }
                    break;
                case Constants.DEADLINE:
                    try {
                        // Error when deadline followed by a blank space
                        userInput.substring(10);
                        // Error when just deadline
                        String[] deadline = userInput.substring(9).split(" /by ");
                        TaskOperation.add(new Deadline(deadline[0], deadline[1]),workList);
                    } catch (StringIndexOutOfBoundsException e) {
                        new DukeException.EmptyDeadlineException();
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        new DukeException.DeadlineWithoutByException();
                    }
                    break;
                case Constants.EVENT:
                    try {
                        // Error when event followed by a blank space
                        userInput.substring(7);
                        // Error when just event
                        String[] event = userInput.substring(6).split(" /at ");
                        TaskOperation.add(new Event(event[0], event[1]), workList);
                    } catch (StringIndexOutOfBoundsException e) {
                        new DukeException.EmptyEventException();
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        new DukeException.EventWithoutAtException();
                    }
                    break;
                case Constants.DELETE:
                    try {
                        userInput.substring(8);
                        index =  Integer.parseInt(userInput.split(" ")[1]);
                        TaskOperation.delete(workList.get(index-1), workList);
                    } catch (StringIndexOutOfBoundsException e) {
                        new DukeException.EmptyDeleteException();
                        break;
                    } catch (NumberFormatException e) {
                        new DukeException.EmptyDeleteException();
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        new DukeException.EmptyDeleteException();
                        break;
                    }
                    break;
                default:
                    new DukeException.InvalidInputException();
                    break;
            }
            // Update data
            DukeEncoder.rewriteList(Duke.workList);

            // Next input
            userInput = scanner.nextLine();
        }

        // Update data
        DukeEncoder.rewriteList(Duke.workList);
    }
    public static void main(String[] args){
        // Greeting
        Duke.greet();

        // Process
        Duke.process();

        // Bye
        Duke.exit();
    }
}
