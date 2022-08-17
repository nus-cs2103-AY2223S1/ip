public class Messages {
    /**
     * Child containing default messages
     */
    public static void welcome() {
        System.out.println("-------------------------------------------");
        System.out.println("Hello from Phil");
        System.out.println("How may I assist you on this fine day?");
        System.out.println("-------------------------------------------");
    }
    public static void bye() {
        System.out.println("See you later alligator!");
        System.out.println("-------------------------------------------");
    }

    public static void add(Task task) {
        System.out.println("added: " + task.toString());
    }

    public static void mark() {
        System.out.println("Roger sir the task has been marked!");
    }

    public static void unmark() {
        System.out.println("Aww okay the task has been unmarked.");
    }

    public static void delete(Task task) {
        System.out.println("Alrighty! I have deleted the following task:");
        System.out.println(task.toString());
    }

    public static void countTasks(TaskList list) {
        System.out.println("We now have " + list.getSize() + " tasks left.");
    }
}
