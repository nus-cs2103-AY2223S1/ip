import java.util.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;



public class Duke {
    /**
     * Counter field that shows how many tasks there are in a list
     */
    public static int counter = 0;
    private static ArrayList<Task> lst = new ArrayList<Task>();

    /**
     * Prints the message when a user types "bye"
     */
    public static void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void getFile() throws IOException {
        File file = new File("./src/main/java/duke.txt");
        if (file.createNewFile()) {
            System.out.println(file.getName() + " has been created");
        } else {
            System.out.println(file.getName() + " already exists");
        }
    }

    public static void updateFile() throws IOException {
        File file = new File("./src/main/java/duke.txt");
        FileWriter writer = new FileWriter(file);
        for (Task task: lst) {
            writer.write(task.toFileString() + "\n");
        }
        writer.close();
    }

    public static void updateLst() throws IOException {
        File file = new File("./src/main/java/duke.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            String[] stringDetails = currentLine.split("\\|");
            String taskType = stringDetails[0];
            String isMark = stringDetails[1];
            String description = stringDetails[2];
            switch (taskType) {
                case "T ":
                    Todo todo = new Todo(description.substring(1));
                    if (isMark.equals(" 1 ")) {
                        todo.mark();
                    }
                    lst.add(todo);
                    break;
                case "D ": {
                    String by = stringDetails[3];
                    Deadline deadline = new Deadline(description.substring(1), by.substring(1));
                    if (isMark.equals(" 1 ")) {
                        deadline.mark();
                    }
                    lst.add(deadline);
                    break;
                }
                case "E ": {
                    String at = stringDetails[3];
                    Event event = new Event(description.substring(1), at.substring(1));
                    if (isMark.equals(" 1 ")) {
                        event.mark();
                    }
                    lst.add(event);
                    break;
                }
            }
            counter = lst.size();
        }
    }
    /**
     * Prints the list when user types "list"
     */
    public static void listMessage() throws DukeException{
        if (counter == 0) {
            throw new DukeException("Your list is empty!!");
        } else {
            for (int i = 1; i < counter + 1; i++) {
                System.out.println((i) + ". " + lst.get(i - 1));
            }
        }
    }

    /**
     * Marks a task as completed
     *
     * @param str The users input
     */
    public static void markMessage(String str) throws DukeException, IOException {
        char index = str.charAt(5);
        int number = Integer.parseInt(String.valueOf(index));
        if(number > counter) {
            throw new DukeException("No such task.");
        }
        Task t = lst.get(number - 1);
        t.mark();
        System.out.println("Nice! I've marked this task as done:\n  [X] " + t.description);
        updateFile();
    }

    /**
     * Marks a task as incomplete
     *
     * @param str The users input
     */
    public static void unmarkMessage(String str) throws DukeException, IOException {
        char index = str.charAt(7);
        int number = Integer.parseInt(String.valueOf(index));
        if(number > counter) {
            throw new DukeException("No such task.");
        }
        Task t = lst.get(number - 1);
        t.unmark();
        System.out.println("OK, I've marked this task as not done yet:\n  [ ] " + t.description);
        updateFile();
    }

    /**
     * Adds a Todo to the list
     *
     * @param str The users input
     */
    public static void todoMessage(String str) throws DukeException, IOException {
        if(str.length() > 5) {
            String description = str.substring(5);
            Todo d = new Todo(description);
            lst.add(counter, d);
            counter++;
            System.out.println("Got it. I've added this task:\n " + d +
                    "\nNow you have " + counter + " tasks in the list.");
            updateFile();
        } else {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Adds a Deadline to the list
     *
     * @param str The users input
     */
    public static void deadlineMessage(String str) throws DukeException, IOException {
        if(str.length() > 9) {
            int sepPos = str.indexOf("/");
            if (sepPos != -1) {
                String description = str.substring(9, sepPos);
                String by = str.substring(sepPos + 4);
                Deadline l = new Deadline(description, by);
                lst.add(counter, l);
                counter++;
                System.out.println("Got it. I've added this task:\n " + l +
                        "\nNow you have " + counter + " tasks in the list.");
                updateFile();
            } else {
                throw new DukeException("You have to include the deadline");
            }
        } else {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
    }

    /**
     * Adds a Event to the list
     *
     * @param str The users input
     */
    public static void eventMessage(String str) throws DukeException, IOException {
        if (str.length() > 6) {
            int sepPos = str.indexOf("/");
            if (sepPos != -1) {
                String description = str.substring(6, sepPos);
                String at = str.substring(sepPos + 4);
                Event e = new Event(description, at);
                lst.add(counter, e);
                counter++;
                System.out.println("Got it. I've added this task:\n " + e +
                        "\nNow you have " + counter + " tasks in the list.");
                updateFile();
            } else {
                throw new DukeException("You have to include the timings of an event");
            }
        } else {
            throw new DukeException("The description of a event cannot be empty.");
        }
    }

    /**
     * Deletes a task from the list
     *
     * @param str The users input
     */
    public static void deleteMessage(String str) throws DukeException, IOException {
        if (str.length() > 7) {
            char index = str.charAt(7);
            int number = Integer.parseInt(String.valueOf(index));
            if(number > counter) {
                throw new DukeException("No such task.");
            }
            Task t = lst.get(number - 1);
            lst.remove(number - 1);
            counter--;
            System.out.println("Noted. I've removed this task: \n " + t + "\nNow you have "
                    + counter + " tasks in the list.");
            updateFile();
        }
    }

    
    
    public static void main(String[] args) throws DukeException, IOException {
        getFile();
        updateLst();
        System.out.println("Hello! I'm SmartBot\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String first = sc.nextLine();
            try {
                if (first.equals("bye")) {
                    byeMessage();
                    break;
                } else if (first.equals("list")) {
                    listMessage();
                } else if (first.length() == 6 && first.substring(0, 4).equals("mark")) {
                    markMessage(first);
                } else if (first.length() == 8 && first.substring(0, 6).equals("unmark")) {
                    unmarkMessage(first);
                } else if (first.length() >= 4 && first.substring(0, 4).equals("todo")) {
                    todoMessage(first);
                } else if (first.length() >= 8 && first.substring(0, 8).equals("deadline")) {
                    deadlineMessage(first);
                } else if (first.length() >= 5 && first.substring(0, 5).equals("event")) {
                    eventMessage(first);
                } else if (first.length() == 8 && first.substring(0, 6).equals("delete")) {
                    deleteMessage(first);
                }
                else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex) {
                    System.out.println(ex);
            }
        }
    }
}
