public class UI {

    private final static String WELCOME_MESSAGE = "Hello! I am Duke. Please enter your command: ";

    public static void welcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void added(Task task) {
        System.out.println(task.added());
    }
}
