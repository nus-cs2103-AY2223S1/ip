import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;

public class Actions { //actions that Duke does

    /**
     * A method for Duke to tell the user how many tasks they currently have.
     * @param ls The current list of tasks the user has.
     */
    public static void taskNumberMessage(ArrayList<Task> ls) {
         if (ls.size() == 1) {
             System.out.println("Now you have " + ls.size() + " task in the list.");
         }
         else {
             System.out.println("Now you have " + ls.size() + " tasks in the list.");
         }
    }

    /**
     * A method for Duke to process the input given by the user.
     * @param input The input string picked up by the scanner object.
     * @throws EmptyDescriptionException If the input is a recognized command but no appropriate description after that.
     * @throws InvalidCommandException If the input is an unrecognized command.
     */
    public static void processInput(String input) throws EmptyDescriptionException, InvalidCommandException {
        ArrayList<String> acceptedKeywords = new ArrayList<>();
        acceptedKeywords.add("list");
        acceptedKeywords.add("bye");
        acceptedKeywords.add("deadline");
        acceptedKeywords.add("event");
        acceptedKeywords.add("todo");
        acceptedKeywords.add("delete");
        String[] parts = input.split(" ", 2);
        String keyword = parts[0];
        if (input.equals("bye") || input.equals("list")) return;
        if (!acceptedKeywords.contains(keyword)) {
            throw new InvalidCommandException();
        }
        else if (parts.length == 1 || parts[1].equals("")) {
            throw new EmptyDescriptionException();
        }
    }

    public static void createFile(File fileToCreate) {
        try {
            fileToCreate.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * The current chatbot functionality which takes in user input and reacts accordingly to the input.
     */
    public static void toDoList() {
        ArrayList<Task> ls = new ArrayList<>();
        File taskList = new File("./src/main/data/duke.txt");
        try {
            Scanner myReader = new Scanner(taskList);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                char key = data.charAt(1);
                System.out.println(key);
                String temp = data.substring(7);
                if (key == 'T') { //todo
                    Todo task = new Todo(temp);
                    ls.add(task);
                }
                else if (key == 'D') { //deadline
                    String parts[] = temp.split(" \\(by: ", 2);
                    Deadline task = new Deadline(parts[0], parts[1].substring(0, parts[1].length() - 1)); //to handle the ) at the end
                    ls.add(task);
                }
                else { //event
                    String parts[] = temp.split(" \\(at: ", 2);
                    Event task = new Event(parts[0], parts[1].substring(0, parts[1].length() - 1)); //to handle the ) at the end
                    ls.add(task);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            createFile(taskList);
        }
        String input;
        System.out.println("Hello! I'm Duke, what's up today?");
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
            String[] parts = input.split(" ", 2);
            String keyword = parts[0];
            processInput(input);
            switch (keyword) {
                case "list":
                    System.out.println("Here are your tasks:");
                    for (int i = 0; i < ls.size(); i++) {
                        System.out.println(i + 1 + ". " + ls.get(i).toString());
                    }
                    break;
                case "mark": //command is mark 2
                    Task currTask = ls.get(Integer.parseInt(parts[1]) - 1); //the arraylist is 0 indexed, so task 1 is actually 0 index
                    currTask.setDone();
                    System.out.println("Let's go! I've marked this task as done:");
                    System.out.println(currTask);
                    break;
                case "unmark":
                    Task unmarkTask = ls.get(Integer.parseInt(parts[1]) - 1); //the arraylist is 0 indexed, so task 1 is actually 0 index
                    unmarkTask.setUndone();
                    System.out.println("Oh man! I've marked this task as undone:");
                    System.out.println(unmarkTask);
                    break;
                case "deadline": //can abstract this whole case to be generalized
                    String[] temp = input.split(" /by ", 2);
                    String by = temp[1];
                    String deadlineDesc = temp[0].split("deadline ")[1];
                    Deadline deadlineTask = new Deadline(deadlineDesc, by);
                    ls.add(deadlineTask);
                    deadlineTask.addTaskMessage();
                    taskNumberMessage(ls);
                    break;
                case "event": //can abstract this whole case to be generalized
                    String[] temp1 = input.split(" /at ", 2);
                    String at = temp1[1];
                    String eventDesc = temp1[0].split("event ", 2)[1];
                    Event eventTask = new Event(eventDesc, at);
                    ls.add(eventTask);
                    eventTask.addTaskMessage();
                    taskNumberMessage(ls);
                    break;
                case "todo": //can abstract this whole case to be generalized
                    String todoDesc = input.split("todo ")[1];
                    Todo todoTask = new Todo(todoDesc);
                    ls.add(todoTask);
                    todoTask.addTaskMessage();
                    taskNumberMessage(ls);
                    break;
                case "delete":
                    Task toBeDeleted = ls.get(Integer.parseInt(parts[1]) - 1);
                    System.out.println("Alrighty, this task's gone: ");
                    System.out.println(toBeDeleted);
                    ls.remove(toBeDeleted);
                    break;
                default:
                    Task curr = new Task(input);
                    ls.add(curr);
                    System.out.println("added: " + input);
            }

            FileWriter myWriter = new FileWriter("./src/main/data/duke.txt");
            for (Task curr: ls) {
                System.out.println("writing");
                myWriter.write(curr.toString());
                myWriter.write("\n");
            }
            myWriter.close();
            } catch (EmptyDescriptionException e) {
                System.out.println("Description cannot be empty, try again!");
            } catch (InvalidCommandException | IndexOutOfBoundsException e) {
                System.out.println("Invalid input, try again!");
            }

            catch (IOException e){
                System.out.println("Cannot write to file!");
            }
            input = sc.nextLine();

        } //end of while loop, means input is bye
        System.out.println("See ya! Come again~"); //end of bot
    }
}
