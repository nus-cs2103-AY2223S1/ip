import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public  Duke(){

    }
    ArrayList<Task> tasks = new ArrayList<>();
    private final static String horLine = "------------------------------------------------------";
    private final static String welcomeMsg = horLine + "\nHello! I'm Duke\n" +
            "What can I do for you?\n" + horLine;

    private void addToList(Task task) {
        tasks.add(task);
        System.out.println(horLine + "\n\tI have added " + "'" + task.taskName + "'" +
                " to the list" + "\n" + horLine);
    }


    private void userInput() {
        System.out.println(welcomeMsg);
        Scanner userCommand = new Scanner(System.in);
        String input = userCommand.nextLine();



        while (!input.equals("bye")) {
            if(input.equals("list")) {
                viewList();
            } else if (input.matches("\\bmark\\s\\d+\\b")) {
                taskDone(Integer.parseInt(input.replaceAll("[^0-9]", "")));
            } else if (input.matches("\\bunmark\\s\\d+\\b")) {
                taskUndone(Integer.parseInt(input.replaceAll("[^0-9]", "")));
            } else {
                Task newTask = new Task(input);
                addToList(newTask);
            }
            input = userCommand.nextLine();

        }
        System.out.println(horLine + "\n\tBye. Hope to see you again soon!\n" + horLine);

    }

    private void taskDone(int num) {
        tasks.get(num-1).markAsDone();
        System.out.println(horLine + "\n\tNice! I've marked this task as done:\n" +
                "\t[" + tasks.get(num-1).getStatusIcon() +"] " + tasks.get(num-1).taskName + "\n" + horLine);

    }

    private void taskUndone(int num) {
        tasks.get(num-1).markAsUndone();
        System.out.println(horLine + "\n\tOK, I've marked this task as not done yet:\n" +
                "\t[" + tasks.get(num-1).getStatusIcon() + "] " + tasks.get(num-1).taskName + "\n" + horLine);
    }

    private void viewList() {
        System.out.println(horLine + "\n");
        System.out.println("\tHere are the tasks in your list:\n");
        for(int i = 0; i<tasks.size(); i++) {
            int num = i+1;
            System.out.println("\t" + num + "." + "[" + tasks.get(i).getStatusIcon() + "] "
                    + tasks.get(i).taskName );
        }
        System.out.println("\n" + horLine);
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.userInput();
    }
}
