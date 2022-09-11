package duke;

public class Ui {
    private final String HORIZONTAL_LINE_BREAK = "-------------------------";

    public void hello() {
        System.out.println("To all Subjects of Ymir. My name is Eren Yeager.\n" + "How can I help you?" + "\n" + HORIZONTAL_LINE_BREAK);
    }

    public void goodBye() {
        System.out.println(HORIZONTAL_LINE_BREAK + "\n" + "Keep moving forward until you finish all your tasks. Goodbye."
                + "\n" + HORIZONTAL_LINE_BREAK);
    }

    public void showLoadingError() {
        System.out.println("I cannot load your file!");
    }

}
