import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        class Task {
            private boolean isDone = false;
            private String item;

            public Task(String item){
                this.item = item;
            }

            public void setDone() {
                this.isDone = true;
            }

            public void setNotDone() {
                this.isDone = false;
            }

            public String getStatusIcon() {
                return (isDone ? "[X] " : "[ ] "); // mark done task with X
            }

            public String getTask() {
                return this.getStatusIcon() + this.item;
            }
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Task[] list = new Task[500];
        int count = 0;
        String marked = "[X]";
        String unmarked = "[ ]";

        System.out.println("Hello from\n" + logo);

        Scanner scan = new Scanner(System.in);
        System.out.println("Hello I'm Duke\nWhat can I do for you");
        System.out.print("You: ");
        String item = scan.nextLine();

        while (!item.equals("bye")) {
            if (item.equals("list")) {
                for(int i = 0; i < list.length; i++){
                    if (list[i] == null) {
                        break;
                    }
                    System.out.println(Integer.toString(i + 1) + ". " + list[i].getTask());
                }
            }
            else if(item.length() == 6 &&  item.substring(0,4).equals("mark")) {
                int index = Integer.parseInt(item.substring(5)) - 1;
                list[index].setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list[index].getTask());
            }
            else if(item.length() == 8 &&  item.substring(0,6).equals("unmark")) {
                int index = Integer.parseInt(item.substring(7)) - 1;
                list[index].setNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list[index].getTask());
            }
            else{
                list[count] = new Task(item);
                count = count + 1;
                System.out.println("Duke: added: " + item);
            }
            System.out.print("You: ");
            item = scan.nextLine();
        }
        System.out.println("Duke: Bye. hope to see you again soon!");
    }
}
