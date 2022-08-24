import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Jarvis {
    public static void main(String[] args) throws JarvisException {
        Scanner inputScanner = new Scanner(System.in);

        String userInput;
        String introduction = "Hello! I'm Jarvis \n"
                + "What can I do for you?";
        String farewell = "Bye. Hope to see you again soon!";

        List<Task> taskList = new ArrayList<>();

        System.out.println(introduction);
        try {
            while (true) {
                //receive user input
                userInput = inputScanner.nextLine();
                //check if userinput is bye, break if true
                if (userInput.equals("bye")) {
                    break;
                }
                //if userinput equals list, return task list
                if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:\n");
                    for (int i = 0; i < taskList.size(); i++) {
                        if (taskList.get(i) == null) {
                            break;
                        }
                        System.out.println((i + 1) + ". " + taskList.get(i).toString());
                    }
                    continue;
                }
                //if userinput equals mark, check which task and mark it
                if (userInput.length() > 4 && userInput.substring(0, 4).equals("mark")) {
                    int toMark = Integer.parseInt(userInput.substring(5)) - 1;
                    taskList.get(toMark).mark();
                    String markResponse = "Nice! I've marked this task as done:\n ";
                    System.out.println(markResponse + taskList.get(toMark).toString());
                    continue;
                }
                //if userinput equals unmark, check which task and unmark
                if (userInput.length() > 6 && userInput.substring(0, 6).equals("unmark")) {
                    int toMark = Integer.parseInt(userInput.substring(7)) - 1;
                    taskList.get(toMark).unmark();
                    String markResponse = "Ok, I've marked this task as not done yet:\n ";
                    System.out.println(markResponse + taskList.get(toMark).toString());
                    continue;
                }
                //if userinput equals delete, check which task and delete
                if (userInput.length() > 6 && userInput.substring(0, 6).equals("delete")) {
                    int toDelete = Integer.parseInt(userInput.substring(7)) - 1;
                    Task deleteTask = taskList.get(toDelete);
                    taskList.remove(toDelete);
                    String deleteResponse = "Noted. I've removed this task:\n ";
                    System.out.println(deleteResponse + deleteTask.toString());
                    continue;
                }

                //if userinput equals to do add new to do task to list
                if (userInput.length() > 3 && userInput.substring(0, 4).equals("todo")) {
                    String description = userInput.substring(4);
                    if (description.equals("")) {
                        throw new JarvisException("The description of a todo cannot be empty");
                    }
                    taskList.add(new ToDo(description));
                    System.out.println("Got it. I've added this task:\n " + taskList.get(Task.count - 1)
                            + "\nNow you have " + (Task.count) + " tasks in the list.");
                }

                //if userinput equals deadline add new deadline task to list
                if (userInput.length() > 8 && userInput.substring(0, 8).equals("deadline")) {
                    int divisor = userInput.indexOf("/by");
                    String description = userInput.substring(9, divisor - 1);
                    String date = userInput.substring(divisor + 4);
                    taskList.add(new Deadline(description, date));
                    System.out.println("Got it. I've added this task:\n " + taskList.get(Task.count - 1)
                            + "\nNow you have " + (Task.count) + " tasks in the list.");
                }
                //if userinput equals event add new event task to list
                if (userInput.length() > 7 && userInput.substring(0, 5).equals("event")) {
                    int divisor = userInput.indexOf("/at");
                    String description = userInput.substring(6, divisor - 1);
                    String date = userInput.substring(divisor + 4);
                    taskList.add(new Event(description, date));
                    System.out.println("Got it. I've added this task:\n " + taskList.get(Task.count - 1)
                            + "\nNow you have " + (Task.count) + " tasks in the list.");
                }
                throw new JarvisException("I'm sorry, but I don't know what that means");
            }

            System.out.println(farewell);
        }
        catch (JarvisException e) {
            System.out.println((e.toString()));
        }
    }
}
