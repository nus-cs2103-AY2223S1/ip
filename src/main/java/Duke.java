import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) throws DukeException {
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
                Task task;

                if(order.equals("mark")) {
                    content = str[1];
                    int index = Integer.parseInt(content)-1;
                    task = taskList.get(index);
                    task.setIsDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task);

                }else if(order.equals("unmark")) {
                    content = str[1];
                    int index = Integer.parseInt(content) - 1;
                    task = taskList.get(index);
                    task.setIsDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + task);

                } else if(order.equals("delete")){
                    content = str[1];
                    int index = Integer.parseInt(content) - 1;
                    task = taskList.get(index);
                    taskList.remove(index);
                    int num = taskList.size();

                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + num + " tasks in the list.");



                } else if (order.equals("todo")) {
                    try {
                        content = str[1];

                        ToDo todo = new ToDo(content);
                        taskList.add(todo);
                        int num = taskList.size();
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + todo);
                        System.out.println("Now you have " + num + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e){
                        System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                } else if (order.equals("deadline")) {

                    content = str[1];
                    String[] contents = content.split(" /by ");

                    Deadline deadline = new Deadline(contents[0], contents[1]);
                    taskList.add(deadline);
                    int num = taskList.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + num + " tasks in the list.");

                } else if (order.equals("event")) {
                    content = str[1];
                    String[] contents = content.split(" /at ");

                    Event event = new Event(contents[0], contents[1]);
                    taskList.add(event);
                    int num = taskList.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + num + " tasks in the list.");

                } else {
                    System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }


            }

            input = sc.nextLine();

        }

        System.out.print("Bye. Hope to see you again soon!");

    }
}
