package main;

public class Ui {

    private static final String UI_LINE_SPACING = "----------------------------------------\n";
    private static final String greeting = "Hello! I'm Duke  \n" + "What can I do for you?\n";
    
    public Ui() {

    }

    public void greeting() {
        this.chat(this.greeting);
    }

    public void chat(String message) {
        System.out.println(UI_LINE_SPACING + message + UI_LINE_SPACING);
    }
}
