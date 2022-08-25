package Duke;

// deals with interactions with the user
public class Ui {

    public Ui() {}

    public static void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; j < tasks.getSize(); j++) {
            System.out.println((j + 1) + ". " + tasks.getTask(j).toString());
        }
    }

    public static void printHelloMsg() {
        System.out.println("Hi I'm Duke, What can I do for you?");
    }

    public static void printAddSuccessfulMsg(Task task, int size) {
        System.out.println("Got it! I've added this task:\n"
                + task.toString()
                + "\nNow you've got " + size + " tasks in the list!");
    }

    public static void printDeleteSuccessfulMsg(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n" + task.toString() +
                "\nNow you have " + size + " tasks in the list.");
    }

    public static void printMarkTaskSuccessfulMsg(Task task) {
        System.out.println("Nice! I have mark this task as done:\n" + task);
    }

    public static void printUnMarkTaskSuccessfulMsg(Task task) {
        System.out.println("Ok, I have unmark this task to not done:\n" + task);
    }

    public static void printDontUnderstandMsg() {
        System.out.println("OOPS!!! I don't understand what that means!");
    }

    public static void printDescriptionCantBeEmptyMsg(String typeStr) {
        System.out.println("OOPS!!! The description of a " + typeStr + " cannot be empty");
    }

    public static void printErrorMessage(String e) {
        System.out.println(e);
    }
}
