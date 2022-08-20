import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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

        for (int i = 0; i < vocabList.size(); i++) {
            Task nextTask = vocabList.get(i);
            System.out.println(String.format("     %d. %s", i+1, nextTask.toString()));
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
        boolean isActive = true;

        while (isActive) {
            Scanner sc = new Scanner(System.in);

            if (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] taskArray = input.split(" ", 2);
                String action = taskArray[0];

                switch(action) {
                    case "bye":
                    echo("Goodbye! :P");
                    isActive = false;
                    break;

                    case "list":
                    list(vocabList);
                    break;

                    case "mark":
                    case "unmark":
                    boolean mark = action.equals("mark");
                    try {
                        int taskNumber = Integer.parseInt(taskArray[1]) - 1;
                        vocabList.get(taskNumber).toggleComplete(mark ? true : false);
                        String message = mark ? "Nice! I've marked this task as done:" 
                            : "OK, I've marked this task as not done yet:";;
                        echo(String.format("%s\n        %s", message, vocabList.get(taskNumber).toString()));
                    } catch (Exception e) {
                        echo(String.format("Invalid task number. Choose another task to %s! ^-^", mark ? "mark" : "unmark"));
                    }
                    break;
                    
                    default:
                    try {
                        Task newTask = Task.of(input);
                        vocabList.add(newTask);
                        printText = String.format("Got it. I've added this task:\n        %s\n     Now you have %d tasks in the list.", newTask, vocabList.size());
                        echo(printText);
                    } catch (DukeException e) {
                        echo(e.getMessage());
                    }
                }
            }
        }
    }
}
