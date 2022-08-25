import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alpha {
    static String dir = System.getProperty("user.dir");
    public static final String FILE_PATH = dir + "/Alpha.txt";
    static FileOperations fileOperations = new FileOperations(FILE_PATH);
    public static List<Task> tasks = new ArrayList<>();
    public static final String ANSI_YELLOW = "\u001B[33m";

    static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        try {
            fileOperations.createFile();
        } catch(IOException e) {
             System.out.println(e.getMessage());
        }
        try {
            tasks = fileOperations.readFile();
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        welcomeMessage();
        Scanner in = new Scanner(System.in);
        try {
            enterMessage(in);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void welcomeMessage() {
        System.out.println("\n-----------------\n" + ANSI_BLUE + "Hello, I'm ALPHA!" + ANSI_RESET + "\n-----------------");
    }

    private static void enterMessage(Scanner in) throws AlphaException, IOException {
        String input = in.nextLine();
        String[] inputTokens = input.split(" ");
        switch (inputTokens[0]) {
            case "todo": {
                if (input.length() == 4) {
                    throw new AlphaException("Invalid input: Task description is missing!");
                }
                Task t = new Todo(input.substring(5), "T");
                tasks.add(t);
                String textToAppend = t.getTaskType() + " | " + t.getStatus() + " | " + t.getDescription() + "\n";
                fileOperations.writeToFile(FILE_PATH, textToAppend);
                System.out.println(ANSI_YELLOW + ">> " + "added: " + input + ANSI_RESET);
                break;
            }
            case "event": {
                if (input.length() == 5) {
                    throw new AlphaException("Invalid input: Task description is missing!");
                }
                int separator = input.indexOf('/');
                Task t = new Event(input.substring(6, separator - 1), input.substring(separator + 1), "E");
                tasks.add(t);
                Event currEvent = (Event) t;
                String textToAppend = t.getTaskType() + " | " + t.getStatus() + " | "
                        + t.getDescription() + " | on " + currEvent.getDate() + "\n";
                fileOperations.writeToFile(FILE_PATH, textToAppend);
                System.out.println(ANSI_YELLOW + ">> " + "added: " + input + ANSI_RESET);
                break;
            }
            case "deadline": {
                if (input.length() == 8) {
                    throw new AlphaException("Invalid input: Task description is missing!");
                }
                int separator = input.indexOf('/');
                Task t = new Deadline(input.substring(9, separator - 1), input.substring(separator + 1), "D");
                tasks.add(t);
                Deadline currDeadline = (Deadline) t;
                String textToAppend = t.getTaskType() + " | " + t.getStatus() + " | "
                        + t.getDescription() + " | by " + currDeadline.getDeadline() + "\n";
                fileOperations.writeToFile(FILE_PATH, textToAppend);
                System.out.println(ANSI_YELLOW + ">> " + "added: " + input + ANSI_RESET);
                break;
            }
            case "mark": {
                if (input.length() == 4) {
                    throw new AlphaException("Invalid input: Task number is missing!");
                }
                tasks.get(Integer.parseInt(inputTokens[1]) - 1).changeStatus(true);
                fileOperations.rewriteFile(FILE_PATH, tasks);
                System.out.println(ANSI_YELLOW + ">> " + "marked: Task " + inputTokens[1] + ANSI_RESET);
                break;
            }
            case "unmark": {
                if (input.length() == 6) {
                    throw new AlphaException("Invalid input: Task number is missing!");
                }
                tasks.get(Integer.parseInt(inputTokens[1]) - 1).changeStatus(false);
                fileOperations.rewriteFile(FILE_PATH, tasks);
                System.out.println(ANSI_YELLOW + ">> " + "unmarked: Task " + inputTokens[1] + ANSI_RESET);
                break;
            }
            case "list": {
                if (tasks.isEmpty()) {
                    System.out.println(ANSI_YELLOW + ">> " + "Your task list is empty!" + ANSI_RESET);
                    break;
                }
                System.out.println(ANSI_YELLOW + ">> " + "Your task list is as follows:" + ANSI_RESET);
                //fileOperations.printFileContents();
                int count = 1;
                for (Task task : tasks) {
                    System.out.print(ANSI_YELLOW + count + ") " + "[" + task.getTaskType() + "] ["
                            + task.getStatus() + "] " + task.getDescription() + ANSI_RESET);
                    if (task.getTaskType().equals("E")) {
                        Event e = (Event) task;
                        System.out.println(ANSI_RED + " on " + e.getDate() + ANSI_RESET);
                    } else if (task.getTaskType().equals("D")) {
                        Deadline d = (Deadline) task;
                        System.out.println(ANSI_RED + " by " + d.getDeadline() + ANSI_RESET);
                    } else {
                        System.out.println();
                    }
                    count++;
                }
                break;
            }
            case "delete": {
                if (input.length() == 6) {
                    throw new AlphaException("Invalid input: Task number is missing!");
                }
                tasks.remove(Integer.parseInt(inputTokens[1]) - 1);
                fileOperations.rewriteFile(FILE_PATH, tasks);
                System.out.println(ANSI_YELLOW + ">> " + "deleted: Task " + inputTokens[1] + ANSI_RESET);
                break;
            }
            case "bye": {
                System.out.println(ANSI_YELLOW + ">> " + "Bye, See you soon!" + ANSI_RESET);
                System.exit(0);
                break;
            }
            default: {
                AlphaException e = new AlphaException("Invalid input: Task type unknown!");
                throw e;
            }
        }
        try {
            enterMessage(in);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            enterMessage(in);
        }
    }
}
