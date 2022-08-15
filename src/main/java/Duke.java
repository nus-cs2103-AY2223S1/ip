import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void makeDone() {
            this.isDone = true;
        }
        public void makeUndone() {
            this.isDone = false;
        }
    }

    public void dukeRun() {
        String line = "_______________________________\n";
        System.out.println(line +
                "Hello I'm Duke\n" +
                "What can I do for you?\n" +
                line
        );
        String input = "";
        Task[] arr = new Task[100];
        int index = 0;
        while (!input.equals("bye")) {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println(line +
                        "Bye. Hope to see you again soon!\n" +
                        line);
            } else if (input.startsWith("mark") && input.length() > 5){
                int taskNum = Integer.parseInt(input.substring(5));
                arr[taskNum-1].makeDone();
                System.out.println(line +
                        "Nice! I've marked this task as done: \n" +
                        "[" + arr[taskNum-1].getStatusIcon() + "] " +
                        arr[taskNum-1].description + "\n" + line);
            } else if (input.startsWith("unmark") && input.length() > 7){
                int taskNum = Integer.parseInt(input.substring(7));
                arr[taskNum-1].makeUndone();
                System.out.println(line +
                        "OK, I've marked this task as not done yet: \n" +
                        "[" + arr[taskNum-1].getStatusIcon() + "] " +
                        arr[taskNum-1].description + "\n" + line);
            } else if (input.equals("list")) {
                String list = line;
                for (int i = 0; i < index; i++) {
                    list += i + 1;
                    list += ". [";
                    list += arr[i].getStatusIcon();
                    list += "] ";
                    list += arr[i].description;
                    list += "\n";
                }
                System.out.println(list + line);
            } else {
                Task task = new Task(input);
                arr[index] = task;
                index ++;
                System.out.println(line + "added: " + task.description + "\n" + line);
            }
        }
    }
    public static void main(String[] args) {
       Duke duke = new Duke();
       duke.dukeRun();
    }
}
