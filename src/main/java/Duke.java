import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> storeList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        try {
            File myObj = new File("data/duke.txt");
            if (myObj.createNewFile()) {
                System.out.println("No tasks at the moment");
            } else {
                try {
                    printFileContents("data/duke.txt");
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        greeting();

        logicRun();

        farewell();

    }

    public static void printFileContents(String filePath) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                renderStringsAsTasks(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        list();


    }

    public static void renderStringsAsTasks(String taskStr) {
        String taskType = String.valueOf(taskStr.charAt(1));
        String taskDescription = taskStr.split("] ", 2)[1];
        if(taskType.equals("T")) {
            Todo t = new Todo(taskDescription);
            storeList.add(t);
        }
        else if(taskType.equals("D")) {
            String duration = taskDescription.split("y: ", 2)[1];
            taskDescription = taskDescription.split("\\(", 2)[0];
            Deadline d = new Deadline(taskDescription, duration);
            storeList.add(d);
        }
        else {
            String duration = taskDescription.split("t: ", 2)[1];
            taskDescription = taskDescription.split("\\(", 2)[0];
            Event e = new Event(taskDescription, duration);
            storeList.add(e);
        }
    }

    public static void greeting() {
        String greet = "Hello! I'm Duke \n"
                + "What can I do for you? \n";
        System.out.println(greet);
    }

    public static void logicRun() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while(!userInput.equals("bye")) {
            userInput = scanner.nextLine();

            if(userInput.equals("list")) {
                list();
                continue;
            }
            if(userInput.equals("bye")) {
                break;
            }
            if(userInput.split(" ", 2)[0].equals("mark")) {
                int inputTaskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                mark(inputTaskIndex);
                continue;
            }
            if(userInput.split(" ", 2)[0].equals("unmark")) {
                int inputTaskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                unmark(inputTaskIndex);
                continue;
            }
            if(userInput.split(" ", 2)[0].equals("delete")) {
                int inputTaskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                deleteTask(inputTaskIndex);
                continue;
            }

            try{
                userInputCheck(userInput);

                if(userInput.split(" ", 2)[0].equals("todo")) {
                    String taskInput = userInput.split(" ", 2)[1];
                    todo(taskInput);
                    continue;
                }
                if(userInput.split(" ", 2)[0].equals("deadline")) {
                    String taskInput = userInput.split(" ", 2)[1].split("/", 2)[0];
                    String by = userInput.split("/", 2)[1].split(" ", 2)[1];
                    deadline(taskInput, by);
                    continue;
                }
                if(userInput.split(" ", 2)[0].equals("event")) {
                    String taskInput = userInput.split(" ", 2)[1].split("/", 2)[0];
                    String duration = userInput.split("/", 2)[1].split(" ", 2)[1];
                    event(taskInput, duration);
                    continue;
                }

            }
            catch (DukeException err) {
                System.out.println(err.getMessage());
                continue;
            }
            addTask(userInput);
        }
    }

    public static void appendToFile(String filePath, String textToAppend) {
        try {
            File newFile = new File(filePath);
            FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
            if(newFile.length() == 0) {
                fw.write(textToAppend);
            }
            else {
                fw.write("\n" + textToAppend);
            }
            fw.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void editTextInFile(String filePath, String newText, String oldText) {
        try {
            Path path = Paths.get(filePath);
            List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(oldText)) {
                    fileContent.set(i, newText);
                    break;
                }
            }

            Files.write(path, fileContent, StandardCharsets.UTF_8);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void removeLineInText(String filePath, String textToRemove) {
        try {

            File inFile = new File(filePath);

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                if (!line.trim().equals(textToRemove)) {

                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void echo(String userInput) {
        System.out.println(userInput);
    }

    public static void addTask(String userInput) {
        Task t = new Task(userInput);
        storeList.add(t);
        System.out.println("added: " + t.description);
        appendToFile("data/duke.text", t.description);
    }

    public static void deleteTask(int taskIndex) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + storeList.get(taskIndex).toString());
        removeLineInText("data/duke.txt",storeList.get(taskIndex).toString());
        storeList.remove(taskIndex);
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for(int i=0; i < storeList.size(); i++){
//            System.out.printf("%i. [%c] %s", i + 1, storeList.get(i).getStatusIcon(), storeList.get(i).description);
            System.out.println(i + 1 + ". " + storeList.get(i).toString());
        }
    }

    public static void mark(int taskIndex) {
        String oldText = storeList.get(taskIndex).toString();
        storeList.get(taskIndex).markAsDone();
        String newText = storeList.get(taskIndex).toString();
        editTextInFile("data/duke.txt", newText, oldText);
    }

    public static void unmark(int taskIndex) {
        String oldText = storeList.get(taskIndex).toString();
        storeList.get(taskIndex).markAsNotDone();
        String newText = storeList.get(taskIndex).toString();
        editTextInFile("data/duke.txt", newText, oldText);
    }

    public static void todo(String userInput) throws DukeException {
        Todo t = new Todo(userInput);
        if (userInput.isEmpty()) {
            throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
        storeList.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + t.toString());
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");
        appendToFile("data/duke.txt", t.toString());

    }

    public static void deadline(String userInput, String by) {
        Deadline d = new Deadline(userInput, by);
        dateProcessor(d);
        storeList.add(d);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + d.toString());
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");
        appendToFile("data/duke.txt", d.toString());
    }

    public static void event(String userInput, String duration) {
        Event e = new Event(userInput, duration);
        storeList.add(e);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + e.toString());
        System.out.println("Now you have " + storeList.size() + " tasks in the list.");
        appendToFile("data/duke.txt", e.toString());
    }

    public static void userInputCheck(String userInput) throws DukeException {
        String firstCommand = userInput.split(" ", 2)[0];
        if(!firstCommand.equals("todo") && !firstCommand.equals("deadline") && !firstCommand.equals("event")) {
            throw new DukeException("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if(userInput.split(" ", 2).length == 1) {
            throw new DukeException("\t ☹ OOPS!!! The description of a " + firstCommand + " cannot be empty.");
        }
        if((firstCommand.equals("deadline") || firstCommand.equals("event")) && userInput.split("/", 2).length == 1) {
            throw new DukeException("\t ☹ OOPS!!! The time due or needed cannot be empty.");
        }
    }

    public static void dateProcessor(Deadline d) {
        d.convertToDate();
    }

    public static void farewell() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }
}
