import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public  Duke(){

    }
    ArrayList<Task> tasks = new ArrayList<>();
    private final static String horLine = "------------------------------------------------------";
    private final static String welcomeMsg = horLine + "\nHello! I'm Duke\nWhat can I do for you?\n" + horLine;

    private void addToList(Task task) {
        tasks.add(task);
    }


    private void userInput() {
        System.out.println(welcomeMsg);
        Scanner userCommand = new Scanner(System.in);
        String input = userCommand.nextLine();


        while (!input.equals("bye")) {
            if(!input.equals("list")) {
                Task newTask = new Task(input);
                addToList(newTask);
                System.out.println(horLine + "\n\tI have added " + "'" + input + "'" +
                        " to the list" + "\n" + horLine);
            } else {
                System.out.println(horLine + "\n");
                viewList();
                System.out.println("\n" + horLine);
            }
            input = userCommand.nextLine();

        }
        System.out.println(horLine + "\n\tBye. Hope to see you again soon!\n" + horLine);

    }

    private void viewList() {
        for(int i = 0; i<tasks.size(); i++) {
            int num = i+1;
            System.out.println("\t" + num + ". " + tasks.get(i).taskName );
        }

    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.userInput();
    }
}
