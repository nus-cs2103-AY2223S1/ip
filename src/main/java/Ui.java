public class Ui {
    private final String LINE_BREAK = "____________________________________________________________";

    public void hello() {
        System.out.println(LINE_BREAK + "\nHello! I'm Donovan\nWhat can I do for you?\n" + LINE_BREAK);
    }

    public void bye() {
        System.out.println(LINE_BREAK + "\n\tBye! Hope to see you again soon!\n" + LINE_BREAK);
    }

    public void showLoadingError() {
        System.out.println("OOPS! I have difficulty loading your file!");
    }

}
