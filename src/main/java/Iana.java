import java.util.ArrayList;
import java.util.Scanner;
<<<<<<< HEAD:src/main/java/Iana.java
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
=======
>>>>>>> parent of c3cc791 (Add DataLoader to load saved data):src/main/java/Duke.java

public class Iana {
    static ArrayList<Task> vocabList = new ArrayList<Task>();
    static String LINEBLOCK = "\t---------------------------------------------";

    private static void echo(String msg) {
        System.out.println(LINEBLOCK);
        System.out.println("\t" + msg);
        System.out.println(LINEBLOCK);
    }

    private static void list(ArrayList<Task> vocabList) {
        String listMessage = "\tHere are the tasks in your list:";

        System.out.println(String.format("%s\n%s",LINEBLOCK, listMessage));

        for (int i = 0; i < vocabList.size(); i++) {
            Task nextTask = vocabList.get(i);
            System.out.println(String.format("\t   %d. %s", i+1, nextTask.toString()));
        }
        System.out.println(LINEBLOCK);
    }

    private static void delete(ArrayList<Task> vocabList, int taskNumber) throws IanaException {
        if (taskNumber > vocabList.size()) {
            throw new IanaException("Oops!! This task number is invalid. Try to delete another task! xx");
        }
        Task deleted = vocabList.remove(taskNumber);
        echo(String.format("Noted. I've removed this task:\n\t   %s\n\tNow you have %d tasks in the list.", 
            deleted.toString(), vocabList.size()));
    }

    public static void main(String[] args) {
<<<<<<< HEAD:src/main/java/Iana.java
        Path storagePath = Paths.get("src", "main/data", "DataStorage.txt");
        String absPath = storagePath.toAbsolutePath().toString();

        try {
            vocabList = DataLoader.loadData(absPath);
        } catch (IanaException e) {
            echo(e.getMessage());
            return;
        } catch (FileNotFoundException e) {
            echo("File DataStorage.txt not found in [project_root]/src/main/data");
            return;
        }

        String printText = "\t> Hello there~ I'm IANA.\n\tWhat can I do for you today? : )\n";
=======
        String logo = "____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String welcomeMessage = "> What can I do for you today? : )\n";
        String printText = String.format("> Hello from\n %s%s", logo, welcomeMessage);
>>>>>>> parent of c3cc791 (Add DataLoader to load saved data):src/main/java/Duke.java
        System.out.println(printText);
        boolean isActive = true;

        while (isActive) {
            Scanner sc = new Scanner(System.in);

            if (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] taskArray = input.split(" ", 2);
                String action = taskArray[0];

                switch(action) {
                    case "bye":
                    try {
                        DataWriter.writeData(vocabList, absPath);
                    } catch (IOException e) {
                        echo("File DataStorage.txt not found in [project_root]/src/main/data. Cannot store data!");
                    }
                    echo("Goodbye! :P");
                    isActive = false;
                    break;

                    case "list":
                    list(vocabList);
                    break;

                    case "delete":
                    try {
                        int taskNumber = Integer.parseInt(taskArray[1]) - 1;
                        delete(vocabList, taskNumber);
                    } catch (IanaException e) {
                        echo(e.getMessage());
                    } catch (IndexOutOfBoundsException e) {
                        echo("Which task would you like me to delete? ^^");
                    } catch (NumberFormatException e) {
                        echo("Oops! You can't delete that! Delete a task number instead <[u_u]>");
                    }
                    break;

                    case "mark":
                    case "unmark":
                    boolean mark = action.equals("mark");
                    try {
                        int taskNumber = Integer.parseInt(taskArray[1]) - 1;
                        vocabList.get(taskNumber).toggleComplete(mark);
                        String message = mark ? "Nice! I've marked this task as done:" 
                            : "OK, I've marked this task as not done yet:";;
                        echo(String.format("%s\n\t   %s", message, vocabList.get(taskNumber).toString()));
                    } catch (Exception e) {
                        echo(String.format("Invalid task number. Choose another task to %s! ^-^", mark ? "mark" : "unmark"));
                    }
                    break;
                    
                    default:
                    try {
                        Task newTask = Task.of(input);
                        vocabList.add(newTask);
                        printText = String.format("Got it. I've added this task:\n\t   %s\n\tNow you have %d tasks in the list.", newTask, vocabList.size());
                        echo(printText);
                    } catch (IanaException e) {
                        echo(e.getMessage());
                    }
                }
            }
        }
    }
}
