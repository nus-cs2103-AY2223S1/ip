import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        ArrayList<Task> arr = new ArrayList<Task>();
        String input = "";
        int curr = 0;
        Task task = new Task(input, "");
        arr.add(curr, task);
        while(!task.getVal().equals("bye")) {
            task = new Task(sc.nextLine(), "");
            //System.out.println(task.getVal());
            if(task.getVal().equals("list")) {
                StringBuilder s = new StringBuilder();
                for(int i = 0; i < curr; i++) {
                    s = new StringBuilder();
                    if(arr.get(i) instanceof ToDo) {
                        s.append("[T] ");
                    }
                    else if(arr.get(i) instanceof  Deadline) {
                        s.append("[D] ");
                    }
                    else {
                        s.append("[E] ");
                    }
                    if(arr.get(i).getDone()) {
                        //System.out.println(String.format("%s. [X] %s", i + 1, arr[i].getVal()));
                        s.append(" [X]");
                    }
                    else {
                        //System.out.println(String.format("%s. [ ] %s", i + 1, arr[i].getVal()));
                        s.append(" [ ]");
                    }
                    System.out.println(String.format("%s. %s %s %s", i + 1, s, arr.get(i).getVal(), arr.get(i).getTiming()));
                }
                //System.out.println(input); level 1
            }
            else if(task.getVal().indexOf("mark") == 0) {
                String[] at = task.getVal().split(" ");
                try {
                    int index = Integer.parseInt(at[1]);
                    arr.set(index - 1, arr.get(index - 1).markDone());
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("[X] %s", arr.get(index - 1).getVal()));
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
                System.out.println(String.format("[ ] %s", arr.get(index-1).getVal()));
                } catch (Exception e) {
                    System.out.println("☹ OOPS!!! The description of a unmark cannot be empty.");
                }
            }
            else if(task.getVal().indexOf("delete") == 0) {
                String[] at = task.getVal().split(" ");
                try {
                    int index = Integer.parseInt(at[1]);
                    Task del = arr.get(index-1);
                    arr.set(index - 1, arr.get(index));
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
                    System.out.println(String.format("[T][ ] %s", task.getVal()));
                    System.out.println(String.format("Now you have %s tasks in the list.", curr));
                }
                 catch (Exception e) {
                        System.out.println("☹ OOPS!!! The description of a undo cannot be empty.");
                    }
            }
            else if(task.getVal().indexOf("deadline") == 0) {
                try {
                    String deadline = task.getVal().substring(9);
                    String[] at = deadline.split("/");
                    task = new Deadline(at[0], at[1]);
                    arr.add(curr++, task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(String.format("[D][ ] %s (%s)", task.getVal(), task.getTiming()));
                    System.out.println(String.format("Now you have %s tasks in the list.", curr));
                }
                catch (Exception e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            }
            else if(task.getVal().indexOf("event") == 0) {
                try {
                    String event = task.getVal().substring(6);
                    String[] at = event.split("/");
                    task = new Deadline(at[0], at[1]);
                    arr.add(curr++, task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(String.format("[E][ ] %s (%s)", task.getVal(), task.getTiming()));
                    System.out.println(String.format("Now you have %s tasks in the list.", curr));
                }
                catch (Exception e) {
                        System.out.println("☹ OOPS!!! The description of a ecvent cannot be empty.");
                    }
            }
            else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                //arr[curr++] = task;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
