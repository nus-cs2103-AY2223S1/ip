import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // static HashSet<Task> vocabList = new HashSet<Task>();
    static ArrayList<Task> vocabList = new ArrayList<Task>();

    public static void echo(String msg) {
        String lineBlock = "     -----------------------------------------";
        System.out.println(lineBlock);
        System.out.println("     " + msg);
        System.out.println(lineBlock);
    }

    static void list(ArrayList<Task> vocabList) {
        String lineBlock = "     -----------------------------------------";
        String listMessage = "     Here are the tasks in your list:";

        System.out.println(String.format("%s\n%s",lineBlock, listMessage));

        for (Task nextTask : vocabList) {
            System.out.println(nextTask.toString());
        }
        System.out.println(lineBlock);
    }

    public static void main(String[] args) {
        String logo = "____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String welcomeMessage = "> What can I do for you today? : )\n";
        String printText = String.format("> Hello from\n %s%s", logo, welcomeMessage);
        System.out.println(printText);

        while (true) {
            Scanner sc = new Scanner(System.in);

            if (sc.hasNextLine()) {
                String nextString = sc.nextLine();

                if (nextString.equals("bye")) {
                    echo("bye");
                    break;
                }

                if (nextString.equals("list")) {
                    list(vocabList);
                }

                else if (nextString.startsWith("mark ") ||
                nextString.startsWith("unmark ")) {
                    String[] arr = nextString.split(" ");
                    int taskNumber = Integer.parseInt(arr[1]) - 1;
                    boolean mark = nextString.startsWith("mark ");

                    try {
                        vocabList.get(taskNumber).toggleComplete(mark ? true : false);
                        String message = mark ? "Nice! I've marked this task as done:" : 
                            "OK, I've marked this task as not done yet:";
                        echo(String.format("%s\n%s", message, vocabList.get(taskNumber).toString()));
                    } catch (Exception e) {
                        echo(nextString);
                    }
                }

                else {
                    Task newTask = new Task(nextString);

                    if (!vocabList.contains(newTask)) {
                        vocabList.add(newTask);
                        
                        printText = String.format("added: %s", nextString);
                        echo(printText);
                    }
    
                    else {
                        echo(nextString);
                    }
                }
                
            }
        }
    }
}
