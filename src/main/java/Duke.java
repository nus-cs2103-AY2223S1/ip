import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static List<Task> todoList = new ArrayList<>();

    /**
     * class for each task
     */
    public static class Task {
        protected String description;
        protected boolean isDone;

        /**
         * Takes in a description for the task
         * @param description The task description
         * */
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUndone() {
            this.isDone = false;
        }

        /**
         * Returns a String representation of the task
         * @return string
         */
        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + this.description;
        }
    }
    public static class Deadline extends Task {

        protected String by;

        /**
         * Takes in a description and deadline for the task
         * @param description task description
         * @param by deadline of task
         */
        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        /**
         * Returns a String representation of the task
         * @return string
         */
        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
    public static class Todo extends Task {

        /**
         * Takes in a description for the task
         * @param description task description
         */
        public Todo(String description) {
            super(description);
        }

        /**
         * Returns a String representation of the task
         * @return string
         */
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Event extends Task {
        protected String at;

        /**
         * Takes in a description and time for the task
         * @param description task description
         * @param at time of task
         */
        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        /**
         * Returns a String representation of the task
         * @return string
         */
        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    public static void main(String[] args) {
        String reply = "";
        String exit = "bye"; // the keyword to exit

        System.out.println("hi im chompers what do u need!!!\n");

        while(true) {
            Scanner scanIn = new Scanner(System.in);
            reply = scanIn.nextLine(); // read from input

            if(reply.equals(exit)) {
                System.out.println("bye see u"); // exits the program
                scanIn.close();
                break;
            } else if (reply.equals("list")) {
                System.out.println("here! ur tasks:");
                printList(); // prints the list of tasks
            } else {
                String[] substr = reply.split(" ", 2); // to identify the keyword used
                Integer index;
                Task temp;
                switch (substr[0]) {
                    case "mark":
                        if(substr.length == 1) { // no number was given
                            System.out.println("enter an index!");
                            break;
                        }
                        try {
                            index = Integer.parseInt(substr[1]) - 1;
                            if(index < 0 || index >= todoList.size()) { // to check if index is out of range
                                System.out.println("thrs nth there :<");
                                continue;
                            }
                            temp = todoList.get(index);
                            temp.markAsDone();
                            System.out.println("oke this is done now:\n" + temp);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input"); // if index given cannot be converted or was the wrong format
                        }
                        break;
                    case "unmark":
                        if(substr.length == 1) {
                            System.out.println("enter an index!");
                            break;
                        }
                        try {
                            index = Integer.parseInt(substr[1]) - 1;
                            if(index < 0 || index >= todoList.size()) { // check if index is out of range
                                System.out.println("thrs nth there :<");
                                continue;
                            }
                            temp = todoList.get(index);
                            temp.markAsUndone();
                            System.out.println("oke this is undone now:\n" + temp);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input");
                        }

                        break;
                    case "delete":
                        if(substr.length == 1) {
                            System.out.println("enter an index!");
                            break;
                        }
                        try {
                            index = Integer.parseInt(substr[1]) - 1;
                            if(index < 0 || index >= todoList.size()) {
                                System.out.println("thrs nth there :<");
                                continue;
                            }
                            temp = todoList.get(index);
                            todoList.remove(temp);
                            System.out.println("oke this is deleted now:\n" + temp);
                            System.out.println("now u have " + todoList.size() + " task(s)!");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input");
                        }
                        break;
                    case "todo":
                        if(substr.length == 1) {
                            System.out.println("The description cannot be empty!");
                            break;
                        }
                        temp = new Todo(substr[1]);
                        addTask(temp);
                        break;
                    case "deadline":
                        if(substr.length == 1) {
                            System.out.println("The description cannot be empty!");
                            break;
                        }
                        String[] dlDesc = substr[1].split(" /by ", 2);
                        if(dlDesc.length < 2) {
                            System.out.println("The deadline cannot be empty");
                            break;
                        }
                        temp = new Deadline(dlDesc[0], dlDesc[1]);
                        addTask(temp);
                        break;
                    case "event":
                        if(substr.length == 1) {
                            System.out.println("The description cannot be empty!");
                            break;
                        }
                        String[] eventDesc = substr[1].split(" /at ", 2);
                        if(eventDesc.length < 2) {
                            System.out.println("The date cannot be empty");
                            break;
                        }
                        temp = new Event(eventDesc[0], eventDesc[1]);
                        addTask(temp);
                        break;
                    default:
                        System.out.println("idk what that means :(");
                        break;
                }

            }

        }
    }

    /**
     * Adds the task to the list
     * @param task task to be added
     */
    public static void addTask(Task task) {
        todoList.add(task);
        System.out.println("oke i added:\n" + task.toString());
        System.out.println("now u have " + todoList.size() + " task(s)!");
    }

    /**
     * Prints out the current list
     */
    public static void printList() {
        for(int i = 1; i <= todoList.size(); i++) {
            Task task = todoList.get(i-1);
            System.out.println(i + "." + task.toString());
        }
    }
}
