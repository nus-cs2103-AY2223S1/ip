package main;

import java.util.Scanner;

public class Ui {

    TaskList tasks;
    Scanner userInput;

    private static final String UI_LINE_SPACING = "----------------------------------------\n";
    private static final String greeting = "Hello! I'm Duke  \n" + "What can I do for you?\n";
    
    public Ui(TaskList tasks) {
        this.tasks = tasks;
        this.userInput = new Scanner(System.in);
    }

    public void greeting() {
        this.chat(this.greeting);
    }

    public void list(TaskList tasks) {
        this.chat(tasks.toString());
    }

    public void mark(int pos) {
        this.chat(tasks.get(pos).toString());
    }

    public void chat(String message) {
        System.out.println(UI_LINE_SPACING + message + UI_LINE_SPACING);
    }
}
