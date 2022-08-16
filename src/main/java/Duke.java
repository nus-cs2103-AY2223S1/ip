import java.util.Scanner;

public class Duke {

    private static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : "");
        }

        public String toString() {
            return description;
        }

        public void setDone() {
            this.isDone = true;
        }
        public void setNotDone() {
            this.isDone = false;
        }
    }

    public static void main(String[] args) {
        //  Opening statements
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        int counter = 0;
        Task[] list = new Task[100];

        while (counter < 100) {
            //  User Input
            String str = sc.nextLine();
            //  Bye
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            //  List
            if (str.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int x = 0; x < counter; x++) {
                    System.out.println(String.format("%d.[%s] %s",(x+1), list[x].getStatusIcon(), list[x].toString()));
                }
                continue;
            }
            //  Marking
            if ((str.length() >= 6) && (str.substring(0, 4).equals("mark"))) {
                String remainder = str.substring(5);
                int index = Integer.valueOf(remainder) - 1;
                //  more error checking
                if (index < 0 || list[index] == null) {
                    System.out.println("Invalid task!");
                    continue;
                }
                list[index].setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format("%s.[%s] %s", index+1, list[index].getStatusIcon(), list[index]));
                continue;

            }
            //  Unmarking
            if ((str.length() >= 8) && (str.substring(0, 6).equals("unmark"))) {
                String remainder = str.substring(7);
                int index = Integer.valueOf(remainder) - 1;
                //  more error checking
                if (index < 0 || list[index] == null) {
                    System.out.println("Invalid task!");
                    continue;
                }
                list[index].setNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(String.format("%s.[%s] %s", index+1, list[index].getStatusIcon(), list[index]));
                continue;
            }
            list[counter] = new Task(str);
            counter++;
        }
    }
}
