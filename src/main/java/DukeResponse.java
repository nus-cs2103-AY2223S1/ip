public abstract class DukeResponse {
    private static final String DIVIDER = "____________________________________________________________";

    public static void intro() {
        String text = "Hello! I'm Duke!\n" + "What can I do for you?";
        System.out.println(DIVIDER + "\n" + text + "\n" + DIVIDER);
    }

    public static void outro() {
        String text = "Bye. Hope to see you again soon!";
        System.out.println(DIVIDER + "\n" + text + "\n" + DIVIDER);
    }

    public void message(String text) {
        System.out.println(DIVIDER + "\n" + text + "\n" + DIVIDER);
    }

    public abstract void run() throws DukeException;
}