import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.Scanner;

/**
 * Class for the Poolsheen CLI program.
 * @author Ong Wee, Marcus (Tut Grp 03)
 * @version CS2103 AY22/23 Sem 1
 */

public class Poolsheen {
    public static void main(String[] args) {
        File saveFile = new File(SAVE_FILE_PATH);
        try {
            parseFile(saveFile);
        } catch (FileNotFoundException e) {
            System.out.println("No save file was found. Creating a new save file.");
            saveFile = createFile(SAVE_FILE_PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Poolsheen.WELCOME_MESSAGE);
        Poolsheen ps = new Poolsheen(saveFile);
        ps.run();
        System.out.println(Poolsheen.END_MESSAGE);
    }

    private static final String SAVE_FILE_PATH = "SAVE.TXT";

    public static final String START_REPLY = "      ";

    private static final String LOGO = "\n" +
            "POOLSHEEN";

    private static final String WELCOME_MESSAGE = "Hello from" + LOGO + "\n" +
            "Type something in for Poolsheen to respond to you:";

    private static final String LAST_REPLY = "meow *_*";

    private static final String HORIZONTAL_LINE = "---------------";

    private static final String EXIT_MESSAGE = "MeoAww... See you next time :(";

    private static final String END_MESSAGE = "THE POOLSHEEN PROGRAM HAS STOPPED RUNNING";

    /** Whether if this poolsheen object has stopped running */
    private boolean hasExited;

    /** The current input that the poolsheen object has received */
    private String userInput;

    /** The scanner object which this poolsheen uses */
    private Scanner scanner;

    /** The list of tasks that the Poolsheen class has */
    private static ArrayList<Task> listOfTasks = new ArrayList<>(100);

    /** The save file that the poolsheen object refers to */
    private File saveFile;

    /**
     * A private constructor to initialise the Poolsheen object.
     */
    private Poolsheen(File saveFile) {
        this.hasExited = false;
        this.userInput = "";
        this.scanner = new Scanner(System.in);
        this.saveFile = saveFile;
    }

    //The Enum variables here are all caps so that commands
    //can be processed even if they are caps-locked or not.

    /**
     * Types of commands which can be processed.
     */
    private enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    /**
     * Allows Poolsheen to listen to our user.
     */
    private void run() {
        while (!this.hasExited) {
            try {
                this.userInput = this.scanner.nextLine();
                //parse a string
                String[] arr = this.userInput.split(" ");
                //to convert primitive string array to arraylist<string>
                ArrayList<String> arl = new ArrayList<>();
                for (String s : arr) {
                    arl.add(s);
                }
                String command = arl.get(0);
                arl.remove(0);
                switch (Enum.valueOf(Command.class, command.toUpperCase())) {
                    case BYE:
                        if (!arl.isEmpty()) {
                            throw new IncompleteCommandException("this.userInput", "bye", "Were you trying to enter 'bye'?");
                        } else {
                            this.exit();
                        }
                        break;
                    case LIST:
                        if (!arl.isEmpty()) {
                            throw new IncompleteCommandException("this.userInput", "list", "Were you trying to enter 'list'?");
                        } else {
                            this.displayList();
                        }
                        break;
                    case MARK:
                        if (arl.isEmpty() || arl.size() != 1) {
                            throw new IncompleteCommandException(this.userInput, "mark", "Please enter 1 appropriate integer");
                        } else {
                            this.mark(java.lang.Integer.parseInt(arl.get(0)));
                        }
                        break;
                    case UNMARK:
                        if (arl.isEmpty()) {
                            throw new IncompleteCommandException(this.userInput, "unmark", "Please enter 1 appropriate integer");
                        } else {
                            this.unmark(java.lang.Integer.parseInt(arl.get(0)));
                        }
                        break;
                    case DELETE:
                        if (arl.isEmpty()) {
                            throw new IncompleteCommandException(this.userInput, "delete", "Please enter 1 appropriate integer");
                        } else {
                            this.deleteTask(java.lang.Integer.parseInt(arl.get(0)));
                        }
                        break;
                    case TODO:
                        if (arl.isEmpty()) {
                            throw new IncompleteCommandException(this.userInput, "todo", "The description of a todo cannot be empty");
                        } else {
                            String descTD = String.join(" ", arl);
                            ToDo t = new ToDo(descTD, false);
                            Poolsheen.listOfTasks.add(t);
                            this.say("Poolsheen now remembers: " + descTD);
                        }
                        break;
                    case DEADLINE:
                        if (arl.isEmpty()) {
                            throw new IncompleteCommandException(this.userInput, "deadline", "Deadlines need a description and time");
                        } else if (!arl.contains("/by")) {
                            throw new IncompleteCommandException(this.userInput, "deadline", "deadline commands need a '/by'");
                        } else {
                            String descD = String.join(" ", arl.subList(0, arl.indexOf("/by")));
                            String timeD = String.join(" ", arl.subList(arl.indexOf("/by") + 1, arl.size()));
                            if (descD.length() == 0 || timeD.length() == 0) {
                                throw new IncompleteCommandException(this.userInput, "deadline", "deadline commands must specify a description and time");
                            } else {
                                Deadline d = new Deadline(descD, false, timeD);
                                Poolsheen.listOfTasks.add(d);
                                this.say("Poolsheen now remembers: " + descD);
                            }
                        }
                        break;
                    case EVENT:
                        if (arl.isEmpty()) {
                            throw new IncompleteCommandException(this.userInput, "event", "Events need a description and time");
                        } else if (!arl.contains("/at")) {
                            throw new IncompleteCommandException(this.userInput, "event", "event commands need an '/at'");
                        } else {
                            String descE = String.join(" ", arl.subList(0, arl.indexOf("/at")));
                            String timeE = String.join(" ", arl.subList(arl.indexOf("/at") + 1, arl.size()));
                            if (descE.length() == 0 || timeE.length() == 0) {
                                throw new IncompleteCommandException(this.userInput, "event", "event commands must specify a description and time");
                            } else {
                                Event e = new Event(descE, false, timeE);
                                Poolsheen.listOfTasks.add(e);
                                this.say("Poolsheen now remembers: " + descE);
                            }
                        }
                        break;
                    default:
                        throw new UnknownCommandException(this.userInput);
                }
                update();
            }  catch (UnknownCommandException e) {
                this.say(e.toString() + "\n" + Poolsheen.START_REPLY + "Please try again!");
            } catch (IncompleteCommandException e) {
                this.say(e.toString());
            } catch (IndexOutOfBoundsException e) {
                this.say("Poolsheen thinks no task has this position. Please try another number!");
            } catch (NumberFormatException e) {
                this.say("Poolsheen believes this command needs an integer. Please try again!");
            } catch(IllegalArgumentException e) {
                this.say("Poolsheen has never seen this command and is confused :(\n" + Poolsheen.START_REPLY + "Please try again!");
            } catch(IOException e) {
                this.say("IOException occurred " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error has occurred and the program will end.");
                System.out.println("Error is: " + e.toString());
                this.exit();
            }
        }
    }

    /**
     * Cleans up leftover code before Poolsheen stops listening to our user.
     */
    private void exit() {
        this.hasExited = true;
        this.say(Poolsheen.EXIT_MESSAGE);
    }

    /**
     * Returns a string of the list of tasks that Poolsheen remembers.
     */
    private String getListOfTasks() {
        if (Poolsheen.listOfTasks.isEmpty()) {
            return "";
        } else {
            String displayStr = "";
            for (int i = 0; i < Poolsheen.listOfTasks.size(); i++) {
                int currPos = i + 1;
                Task t = Poolsheen.listOfTasks.get(i);
                if (t != null) {
                    String line = currPos + ". " + t.toString();
                    displayStr += Poolsheen.START_REPLY + line;
                    if (currPos < Poolsheen.listOfTasks.size()) {
                        displayStr += "\n";
                    }
                }
            }
            return displayStr;
        }
    }

    /**
     * Prints the list of tasks this Poolsheen remembers.
     */
    private void displayList() {
        if (Poolsheen.listOfTasks.isEmpty()) {
            this.say("Poolsheen thinks back... " +
                    "and remembers you said nothing :(");
        } else {
            String displayStr = "Poolsheen thinks back... " +
                    "and remembers you said:\n";
            this.say(displayStr + this.getListOfTasks());
        }
    }

    /**
     * Prints a formatted message by Poolsheen.
     * @param message The message to be printed.
     */
    private void say(String message) {
        System.out.println(Poolsheen.HORIZONTAL_LINE + "\n" +
                Poolsheen.START_REPLY + message + "\n" +
                Poolsheen.START_REPLY +
                Poolsheen.LAST_REPLY +
                "\n" + Poolsheen.HORIZONTAL_LINE);
    }


    /**
     * Updates the contents of the save file.
     */
    private void update() throws IOException{
        FileWriter fw = new FileWriter(SAVE_FILE_PATH);
        String str = "";
        int max = Poolsheen.listOfTasks.size();
        for (int pos = 0 ; pos < max; pos++) {
            Task t = Poolsheen.listOfTasks.get(pos);
            int listPos = pos + 1;
            str += listPos + "|" + String.join("|" , t.toArr());
            if (pos < max) {
                str += "\n";
            }
        }
        fw.write(str);
        fw.close();
    }

    /**
     * Marks a task as done assuming the user input is correct.
     * @param pos The index position of the task in the list.
     */
    private void mark(int pos) {
        Task selectedTask = Poolsheen.listOfTasks.get(pos-1);
        selectedTask.markAsDone();
        this.say("Poolsheen thinks you are done with "
                + selectedTask.description);
    }

    /**
     * Marks a task as not done assuming the user input is correct.
     * @param pos The index position of the task in the list.
     */
    private void unmark(int pos) {
        Task selectedTask = Poolsheen.listOfTasks.get(pos-1);
        selectedTask.markAsNotDone();
        this.say("Poolsheen thinks you are not done with "
                + selectedTask.description);
    }

    /**
     * Removes a task from the list of tasks that Poolsheen remembers.
     * @param pos The index+1 position of the task that is to be deleted.
     */
    private void deleteTask(int pos) {
        Task t = Poolsheen.listOfTasks.get(pos - 1);
        Poolsheen.listOfTasks.remove(pos-1);
        this.say("Poolsheen has forgot: " + t.description +
                " and you now have " + Poolsheen.listOfTasks.size() + " tasks left");
    }

    /**
     * Creates a new save.txt file.
     * @param filePath The relative path which the file will be created.
     * @return The File object which refers to the save.txt file.
     */
    private static File createFile(String filePath) {
        File f = new File(filePath);
        try {
            if (f.createNewFile()) {
                System.out.println("New Save File created");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return f;
    }

    /**
     * Parses the contents of the save.txt to fill up the list of tasks.
     * @param f The file which is being parsed.
     * @throws IOException Thrown if there is an error with parsing the file.
     */
    private static void parseFile(File f) throws IOException {
        if (f.exists()) {
            System.out.println("Poolsheen has loaded a save file");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] arr = s.nextLine().split("\\|");
                ArrayList<String> arl = new ArrayList<>();
                for (String str : arr) {
                    arl.add(str);
                }
                Predicate<String> pred = (x) -> x.equals("");
                arl.removeIf(pred);
                //Uncomment to see how the file contents are parsed as arrays.
//                System.out.println(arl.toString());
                String taskType = arl.get(1);
                boolean isDone;
                if (arl.get(2).equals("X")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                String taskDesc;
                String time;
                switch (taskType.toUpperCase()) {
                case "T":
                    taskDesc = arl.get(3);
                    Poolsheen.listOfTasks.add(new ToDo(taskDesc, isDone));
                    break;
                case "D":
                    if (arl.size() != 5) {
                        throw new IOException("Error with Deadline Task");
                    }
                    taskDesc = arl.get(3);
                    time = arl.get(4);
                    Poolsheen.listOfTasks.add(new Deadline(taskDesc, isDone, time));
                    break;
                case "E":
                    if (arl.size() != 5) {
                        throw new IOException("Error with Event Task");
                    }
                    taskDesc = arl.get(3);
                    time = arl.get(4);
                    Poolsheen.listOfTasks.add(new Event(taskDesc, isDone, time));
                    break;
                default:
                    throw new IOException("Error with loading");
                }
            }
        } else {
            System.out.println("Poolsheen was unable to find a save file to load");
        }
    }

    /**
     * Prints out all contents of the file.
     * @param f The file which is being referred to.
     * @throws FileNotFoundException Thrown if the save.txt file is not found.
     */
    private static void printFile(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }
}
