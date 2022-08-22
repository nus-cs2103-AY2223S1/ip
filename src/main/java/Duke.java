import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private final String FILE_PATH_DIR =  "data"; //"../../../data";
    private boolean acceptingInput;
    private ArrayList<Task> storage;
    private Scanner inputScanner;
    public void runDuke() throws DukeException {
            String totalString  = inputScanner.nextLine();
            String[] inputStringArray = totalString.split(" ");

            if (totalString.equals("bye")){
                acceptingInput = false;
                System.out.println("Bye. Hope to see you again soon!");
                try {
                    FileWriter fw = new FileWriter(FILE_PATH_DIR + "/duke.txt");
                    for (int i = 0; i < storage.size(); i++) {
                        fw.write(storage.get(i).toCSV() + System.lineSeparator());
                    }
                    fw.close();
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else if (totalString.equals("list")) {
                for (int i = 0; i < storage.size(); i++) {
                    int index = i+ 1;
                    System.out.println(index + ". " + storage.get(i));
                }
            } else if (inputStringArray[0].equals("mark")) {
                if (inputStringArray.length == 1) {
                    throw new DukeException("Please enter an argument (number) after mark!");
                }

                int index = Integer.parseInt(inputStringArray[1]) - 1;
                if (index < 0 || index >= storage.size()) {
                    throw new DukeException("Please enter between 1 to the last element of the list");
                } else if (storage.get(index).isMarked()) {
                    throw new DukeException("That task is already marked!");
                }

                storage.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done");
                System.out.println(storage.get(index));
            } else if (inputStringArray[0].equals("unmark")){
                if (inputStringArray.length == 1) {
                    throw new DukeException("Please enter an argument (number) after mark!");
                }

                int index = Integer.parseInt(inputStringArray[1]) - 1;

                if (index < 0 || index >= storage.size()) {
                    throw new DukeException("Please enter between 1 to the last element of the list");
                } else if (!storage.get(index).isMarked()) {
                    throw new DukeException("That task is already unmarked!");
                }

                storage.get(index).unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(storage.get(index));
            } else if (inputStringArray[0].equals("delete")){
                if (inputStringArray.length == 1) {
                    throw new DukeException("Please enter an argument (number) after delete!");
                }
                int index = Integer.parseInt(inputStringArray[1]) - 1;

                if (index < 0 || index >= storage.size()) {
                    throw new DukeException("Please enter between 1 to the last element of the list");
                }

                System.out.println("Noted. I've removed this task:");
                System.out.println(storage.get(index));
                storage.remove(index);
                System.out.println("Now you have " + storage.size() + " tasks in the list");
            } else {
                if (inputStringArray[0].equals("todo")) {
                    if (inputStringArray.length == 1) {
                        throw new DukeException("Please enter an argument after todo!");
                    }

                    String[] nameArray = Arrays.asList(inputStringArray).subList(1,inputStringArray.length).toArray(new String[0]);
                    String taskName = Arrays.stream(nameArray).reduce("", (a,b) -> a + " "+ b).trim();
                    Todo newTask = new Todo(taskName);
                    storage.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + storage.size() + " tasks in the list");
                } else if (inputStringArray[0].equals("deadline")){
                    if (inputStringArray.length == 1) {
                        throw new DukeException("Please enter an argument after deadline!");
                    }

                    int splitter = Arrays.asList(inputStringArray).indexOf("/by");
                    String[] nameArray = Arrays.asList(inputStringArray).subList(1,splitter).toArray(new String[0]);
                    String taskName = Arrays.stream(nameArray).reduce("", (a,b) -> a + " "+ b).trim();

                    String[] deadlineArray = Arrays.asList(inputStringArray).subList(splitter + 1,inputStringArray.length).toArray(new String[0]);
                    String deadlineName = Arrays.stream(deadlineArray).reduce("", (a,b) -> a + " "+ b).trim();

                    Deadlines newTask = new Deadlines(taskName, deadlineName);
                    storage.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + storage.size() + " tasks in the list");

                } else if (inputStringArray[0].equals("event")){
                    if (inputStringArray.length == 1) {
                        throw new DukeException("Please enter an argument after event!");
                    }

                    int splitter = Arrays.asList(inputStringArray).indexOf("/at");
                    String[] nameArray = Arrays.asList(inputStringArray).subList(1,splitter).toArray(new String[0]);
                    String taskName = Arrays.stream(nameArray).reduce("", (a,b) -> a + " "+ b).trim();

                    String[] eventArray = Arrays.asList(inputStringArray).subList(splitter + 1,inputStringArray.length).toArray(new String[0]);
                    String eventName = Arrays.stream(eventArray).reduce("", (a,b) -> a + " "+ b).trim();

                    Event newTask = new Event(taskName,eventName);
                    storage.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + storage.size() + " tasks in the list");

                } else {
                    throw new DukeException("No suitable name for that task");
                }
            }
    }
    public void initialise() {
        storage = new ArrayList<>();
        File fileDir =  new File(FILE_PATH_DIR);
        fileDir.mkdir();
        File f = new File(FILE_PATH_DIR + "/duke.txt");

        try {
            f.createNewFile();
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                storage.add(parseLine(s.nextLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task parseLine(String entry) {
        String[] entryArray  = entry.split(",");
        String taskType = entryArray[0];
        String name = entryArray[2];
        boolean isMarked = entryArray[1].equals("X");
        Task newTask;
        if (taskType.equals("T")) {
            newTask = new Todo(name);
        } else if (taskType.equals("D")) {
            newTask = new Deadlines(name, entryArray[3]);
        } else {
            newTask = new Event(name, entryArray[3]);
        }

        if (isMarked) {
            newTask.markAsDone();
        }
        return newTask;
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Duke currentSession = new Duke();
        currentSession.initialise();
        currentSession.acceptingInput = true;
        currentSession.inputScanner = new Scanner(System.in);
        System.out.println("Hello from Duke");
        System.out.println("What can I do for you?");
        while (currentSession.acceptingInput) {
            try {
                currentSession.runDuke();
            } catch (DukeException e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number after mark/unmark!");
            }
        }
    }

    public static boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
}
