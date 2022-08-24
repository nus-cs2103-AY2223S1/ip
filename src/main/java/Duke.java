import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static String breaker = "____________________________________________________________\n";

    private static ArrayList<Task> Tasklist = new ArrayList<>();
    private static String[] commandWords = new String[]{"list", "mark", "unmark", "todo", "event", "deadline", "delete", "bye"};
    private static String start = "Hello! I'm Duke\nWhat can I do for you?";
    private static String end = "Bye. Hope to see you again soon!";
<<<<<<< HEAD
=======

    public static class DukeException extends Exception {
        public DukeException(String msg) {
            super(msg);
        }
    }
    public static class EmptyMessageException extends DukeException {
        public EmptyMessageException() {
            super("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
    public static class InvalidCommandException extends DukeException {
        public InvalidCommandException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    protected static String dataFileName = "src/main/data/Duke.txt";
    protected static String dataDirectory = "src/main/data";

    public static void main(String[] args) throws InvalidCommandException, EmptyMessageException {

<<<<<<< HEAD
=======

>>>>>>> origin/master
        msg(start);

        //loading past data from file
        try {
            File directory = new File(dataDirectory);
            if (!directory.exists()){
                directory.mkdir();
            }

            File f = new File(dataFileName);
            if (!f.exists()){
                f.createNewFile();
            }

            Scanner s = new Scanner(f);
            int count = 0;
            while (s.hasNext()) {
                String[] temp = s.nextLine().split("\\|");
                count++;
                processInput(temp[0]);
                if ("1".equals(temp[1])) {
                    mark(count);
                }
            }
        } catch (FileNotFoundException e) {
            msg("invalid file name");
            return;
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        String text = "";
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        while (!"bye".equals(text)) {
            text = reader.nextLine();
            if (commandWords[7].equals(text)) {
                msg(end);
                reader.close();
                break;
            }
            try {
                processInput(text);
            } catch (InvalidCommandException e1) {
                msg(e1.getMessage());
            } catch (EmptyMessageException e2) {
                msg(e2.getMessage());
            }
        }

    }

    private static void writeToFile() throws IOException {
        //structure: command|1 (1 for mark, 0 for unmark)
        FileWriter fw = new FileWriter(dataFileName);
        for (int i = 0; i < Tasklist.size(); i++) {
            fw.write(Tasklist.get(i).getData() + "\n");
        }
        fw.close();
    }

    private static boolean checkCommand(String s, int i) {
        return s.length() >= commandWords[i].length() && commandWords[i].equals(s.substring(0,commandWords[i].length()));
    }

    private static void processInput(String text) throws EmptyMessageException, InvalidCommandException {

        if (checkCommand(text, 0)) {
            displayList(Tasklist);
        } else if (checkCommand(text, 1)) {
            int i = Integer.parseInt(text.substring(commandWords[1].length()+1, commandWords[1].length()+2));
            mark(i);
        } else if (checkCommand(text, 2)) {
            int i = Integer.parseInt(text.substring(commandWords[2].length() + 1, commandWords[2].length() + 2));
            unmark(i);
        } else if (checkCommand(text, 6)) {
            int i = Integer.parseInt(text.substring(commandWords[6].length() + 1, commandWords[6].length() + 2));
            delete(i);
        } else { // is a task, commandwords index 3-5 inclusive
            boolean sent = false;
            for (int i = 3; i < 6; i++) {
                if (checkCommand(text, i)) {
                    sent = true;
                    if (text.length() <= commandWords[i].length() + 1) {
                        throw new EmptyMessageException();
                    }
                    if (i > 3) {
                        String[] temp = text.split("/");
                        text = temp[0].substring(commandWords[i].length()+1) + "(" + temp[1] +")";
                    } else {
                        text = text.substring(commandWords[i].length()+1);
                    }
                    add(text, commandWords[i], Tasklist);
                    break;
                }
            }
            if (!sent) {
                throw new InvalidCommandException();
            }
        }
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void msg(String s) {
        System.out.println(breaker + s + "\n" + breaker);
    }

    public static void add(String s, String type, ArrayList<Task> l) {
        Task t = new Task(s, type);
        l.add(t);
        msg("Got it. I've added this task:\n " + "\t" + t + "\n" + "Now you have " + l.size() + " tasks in the list.");
    }

    public static void displayList(ArrayList<Task> l) {
        String result = "";
        if (l.isEmpty()) {
            msg("");
            return;
        }
        for (int i = 0; i < l.size()-1; i++) {
            result += (i+1) + ". " + l.get(i) + "\n";
        }
        result += (l.size()) + ". " + l.get(l.size()-1);
        msg(result);
    }

    public static void mark(int i) {
        Task task = Tasklist.get(i-1);
        task.setStatusIcon(true);
        msg("Nice! I've marked this task as done:\n" + "\t" + task);
    }

    public static void unmark(int i) {
        Task task = Tasklist.get(i-1);
        task.setStatusIcon(false);
        msg("OK, I've marked this task as not done yet:\n" + "\t" + task);
    }

    public static void delete(int i) {
        msg("Noted. I've removed this task:\n\t" + Tasklist.get(i-1) +"\nNow you have " + (Tasklist.size()-1) + " tasks in the list.");
        Tasklist.remove(i-1);
    }
}
