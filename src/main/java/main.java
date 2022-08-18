import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Task[] arr = new Task[101];
        String input = "";
        int curr = 0;
        Task task = new Task(input);
        arr[curr] = task;
        while(!task.getVal().equals("bye")) {
            task = new Task(sc.nextLine());
            if(task.getVal().equals("list")) {
                for(int i = 0; i < curr; i++) {
                    if(arr[i].getDone()) {
                        System.out.println(String.format("%s. [X] %s", i + 1, arr[i].getVal()));
                    }
                    else {
                        System.out.println(String.format("%s. [ ] %s", i + 1, arr[i].getVal()));
                    }
                }
                //System.out.println(input); level 1
            }
            else if(task.getVal().indexOf("mark") == 0) {
                String[] at = task.getVal().split(" ");
                int index = Integer.parseInt(at[1]);
                arr[index - 1] = arr[index - 1].markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format("[X] %s", arr[index-1].getVal()));
            }
            else if(task.getVal().indexOf("unmark") == 0) {
                String[] at = task.getVal().split(" ");
                int index = Integer.parseInt(at[1]);
                arr[index - 1] = arr[index - 1].markUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(String.format("[ ] %s", arr[index-1].getVal()));
            }

            else {
                System.out.println(String.format("added: %s", task.getVal()));
                arr[curr++] = task;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
