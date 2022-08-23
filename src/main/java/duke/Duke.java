package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Duke {
    private static boolean isLoading = true;
    private static boolean isRunning = true;
    private static Scanner sc = new Scanner(System.in);
    private String filePath = "C:/Data/Duke.txt";

    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(this.filePath);
        this.tasks = new TaskList();
        this.parser = new Parser();
    }

    public void stop() {
        Duke.isRunning = false;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public boolean hasFinishedLoading() {
        return !this.isLoading;
    }

    public void getList() {
        this.tasks.getList();
    }

    public void markTask(int i) {
        this.tasks.markTask(i);
    }

    public void unmarkTask(int i) {
        this.tasks.unmarkTask(i);
    }

    public void addTodo(String s) {
        this.tasks.addTodo(s, this);
    }

    public void addDeadline(String s, LocalDate d) {
        this.tasks.addDeadline(s, d, this);
    }

    public void addDeadline(String s, String d) {
        this.tasks.addDeadline(s, d, this);
    }

    public void addEvent(String s, LocalDate d) {
        this.tasks.addEvent(s, d, this);
    }

    public void addEvent(String s, String d) {
        this.tasks.addEvent(s, d, this);
    }

    public void deleteTask(int i) {
        this.tasks.deleteTask(i, this);
    }

    public void parse(String s) {
        this.parser.parse(this, s, this.hasFinishedLoading());
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(duke.ui.logo());
        try {
            duke.storage.loadData(duke, duke.filePath);
        } catch (IOException e) {
            System.err.println("File not found.");
        }
        duke.isLoading = false;
        while (duke.isRunning) {
            duke.parse(sc.nextLine());
        }
    }
}