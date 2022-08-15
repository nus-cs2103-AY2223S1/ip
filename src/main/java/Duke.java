import java.util.ArrayList;
import java.util.Scanner;

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

        public void mark() {
            this.isDone = true;
        }
        public void unmark() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            String s = "[%s] %s";
            return String.format(s, getStatusIcon(), description);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        System.out.println("Oi, What u want?");
        ArrayList<Task> list = new ArrayList<>();
        while (in.hasNext()) {
            String next = in.nextLine();
            String[] command = next.split(" ", 2);
            if (next.equals("bye")) {
                System.out.println("Bye! Don't Come back!");
                return;
            } else if (next.equals("list")) {
                for(int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            } else if (command[0].equals("mark")){
                int index = Integer.parseInt(next.split(" ", 2)[1]) - 1;
                list.get(index).mark();
                System.out.println("I have marked this task as done:");
                System.out.println(list.get(index));
            } else if (command[0].equals("unmark")){
                int index = Integer.parseInt(next.split(" ", 2)[1]) - 1;
                list.get(index).unmark();
                System.out.println("I have marked this task as not done:");
                System.out.println(list.get(index));
            }
            else {
                System.out.println("added: " + next);
                list.add(next);
            }
        }


    }
}
