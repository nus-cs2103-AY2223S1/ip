import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] listOfTasks = new Task[100];
        int indexOfNextTask = 0;
        System.out.println("Hello! I'm Don\nHow may I help you?");
        while (true) {
            String response = sc.nextLine();
            if (response.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (response.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int index = 0; index < indexOfNextTask; index++) {
                    System.out.println(index+1 + ".["+ listOfTasks[index].getStatusIcon()+"] " + listOfTasks[index].getDescription());
                }
            }
            else if (response.length() > 3 && response.substring(0, 4).equals("mark")) {
                int taskIndex = Integer.parseInt(response.substring(5, response.length())) - 1;
                listOfTasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + "[" + listOfTasks[taskIndex].getStatusIcon() + "] " + listOfTasks[taskIndex].getDescription());
            }
            else if (response.length() > 5 && response.substring(0, 6).equals("unmark")) {
                int taskIndex = Integer.parseInt(response.substring(7, response.length())) - 1;
                listOfTasks[taskIndex].markAsNotDone();
                System.out.println("Ok, I've marked this task as not done yet:\n" + "[" + listOfTasks[taskIndex].getStatusIcon() + "] " + listOfTasks[taskIndex].getDescription());
            }
            else {
                listOfTasks[indexOfNextTask++] = new Task(response);
                System.out.println("added: " + response);
            }
        }
    }
}
