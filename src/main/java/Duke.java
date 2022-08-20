import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +  "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input;
        input = sc.nextLine();
        List<Task> taskList = new ArrayList<>();

        String[] str;
        String order;
        String content;

        while(!input.equals("bye")){

            if(input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < taskList.size(); i++) {
                    System.out.print(i+1 + ".");
                    System.out.println(taskList.get(i));
                    }

            } else {
                str = input.split(" ", 2);
                order = str[0];
                content = str[1];


                if(order.equals("mark")) {
                    int index = Integer.parseInt(content)-1;
                    Task task = taskList.get(index);
                    task.setIsDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                } else if(order.equals("unmark")) {
                    int index = Integer.parseInt(content) - 1;
                    Task task = taskList.get(index);
                    task.setIsDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task);
                }

                if (order.equals("todo")) {
                    ToDo todo = new ToDo(content);
                    taskList.add(todo);
                    int num = taskList.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo);
                    System.out.println("Now you have " + num + " tasks in the list");

                } else if (order.equals("deadline")) {
                    String[] contents = content.split(" /by ");
                    Deadline deadline = new Deadline(contents[0], contents[1]);
                    taskList.add(deadline);
                    int num = taskList.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + num + " tasks in the list");
                } else if (order.equals("event")) {
                    String[] contents = content.split(" /at ");
                    Event event = new Event(contents[0], contents[1]);
                    taskList.add(event);
                    int num = taskList.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + num + " tasks in the list");
                }


            }

            input = sc.nextLine();

        }
        System.out.print("Bye. Hope to see you again soon!");



    }
}
