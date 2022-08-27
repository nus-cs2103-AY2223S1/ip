import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        List<Task> arr = new ArrayList<Task>(); //should be list as compile time type
        String input = "";
        int curr = 0;
        Task task = new Task(input, "");
        arr.add(curr, task);

        while(!task.getVal().equals("bye")) {
            task = new Task(sc.nextLine(), "");
            //System.out.println(task.getVal());
            if(task.getVal().equals("list")) {
                for(int i = 0; i < curr; i++) {
                    System.out.println(arr.get(i));
                }
                //System.out.println(input); level 1
            }
            else if(task.getVal().indexOf("mark") == 0) {
                String[] at = task.getVal().split(" ");
                try {
                    int index = Integer.parseInt(at[1]);
                    arr.set(index - 1, arr.get(index - 1).markDone());
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("%s", arr.get(index - 1)));
                } catch (Exception e) {
                    System.out.println("☹ OOPS!!! The description of a mark cannot be empty.");
                }
            }
            else if(task.getVal().indexOf("unmark") == 0) {
                String[] at = task.getVal().split(" ");
                try {
                    int index = Integer.parseInt(at[1]);

                arr.set(index - 1, arr.get(index - 1).markUndone());
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(String.format("%s", arr.get(index-1)));
                } catch (Exception e) {
                    System.out.println("☹ OOPS!!! The description of a unmark cannot be empty.");
                }
            }
            else if(task.getVal().indexOf("delete") == 0) {
                String[] at = task.getVal().split(" ");
                try {
                    int index = Integer.parseInt(at[1]);
                    index--;
                    Task del = arr.get(index);
                    for (int i = index; i < curr; i++) {
                        arr.set(i, arr.get(i + 1));
                    }
                    curr--;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(del);
                    System.out.println(String.format("Now you have %s tasks in the list.", curr));

                } catch (Exception e) {
                    System.out.println("☹ OOPS!!! The description of a delete cannot be empty.");
                }
            }
            else if(task.getVal().indexOf("todo") == 0) {
                try {
                    String todo = task.getVal().substring(5);
                    task = new ToDo(todo);
                    arr.add(curr++, task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.println(String.format("Now you have %s tasks in the list.", curr));
                }
                 catch (Exception e) {
                        System.out.println("☹ OOPS!!! The description of a undo cannot be empty.");
                    }
            }
            else if(task.getVal().indexOf("deadline") == 0) {
                try {
                    String deadline = task.getVal().substring(9);
                    String[] at = deadline.split(" /by ");
                    String[] time = at[1].split("/");
                    time[0] = String.format("%02d", Integer.parseInt(time[0]));
                    time[1] = String.format("%02d", Integer.parseInt(time[1]));
                    String date = String.join("/", time);
                    task = new Deadline(at[0], date);
                    arr.add(curr++, task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.println(String.format("Now you have %s tasks in the list.", curr));
                }
                catch (Exception e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            }
            else if(task.getVal().indexOf("event") == 0) {
                try {
                    String event = task.getVal().substring(6);
                    String[] at = event.split(" /by ");
                    String[] time = at[1].split("/");
                    time[0] = String.format("%02d", Integer.parseInt(time[0]));
                    time[1] = String.format("%02d", Integer.parseInt(time[1]));
                    String date = String.join("/", time);
                    task = new Event(at[0], date);
                    arr.add(curr++, task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.println(String.format("Now you have %s tasks in the list.", curr));
                }
                catch (Exception e) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    }
            }

            else if(task.getVal().indexOf("things") == 0) {
                String[] at = task.getVal().split(" /on ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                String[] time = at[1].split("/");
                time[0] = String.format("%02d", Integer.parseInt(time[0]));
                time[1] = String.format("%02d", Integer.parseInt(time[1]));
                String date = String.join("/", time);
                LocalDateTime dateTime = LocalDateTime.parse(date + " 0000", formatter);
                task = new Task("", "");//to not go into bye block, change to switch
                for(int i = 0; i < curr; i++) {
                    if(arr.get(i).sameDay(dateTime)) {
                        System.out.println(arr.get(i));
                    }
                }

            }

            else if (!task.getVal().equals("bye")) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                //arr[curr++] = task;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
