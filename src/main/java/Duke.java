import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        @Override
        public String toString() {
            return this.description;
        }

        public void setStatus(boolean value) {
            this.isDone = value;
        }
    }
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        ArrayList<Task> ls = new ArrayList<>();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            else if(line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i=0; i<ls.size(); i++) {
                    System.out.println(i+1 + "." +" " + "[" + ls.get(i).getStatusIcon()+"]" +" " + ls.get(i).toString());
                }
            }

            else if(line.contains("unmark")) {
                int num = Integer.parseInt(line.substring(7));
                ls.get(num-1).setStatus(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + ls.get(num-1).getStatusIcon() +"]" +" " +ls.get(num-1).toString());
            }

            else if(line.contains("mark")) {
                int num = Integer.parseInt(line.substring(5));
                ls.get(num-1).setStatus(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + ls.get(num-1).getStatusIcon() +"]" +" " +ls.get(num-1).toString());
            }

            else {
                System.out.println("added: " + line);
                ls.add(new Task(line));
            }


        }


    }


}
