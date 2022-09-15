package dukechatbot.duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dukechatbot.dukeexception.DukeException;
import dukechatbot.utility.Parser;
import dukechatbot.utility.Storage;
import dukechatbot.utility.Task;
import dukechatbot.utility.TaskList;
import dukechatbot.utility.Ui;

/**
 * The Duke program implements an application that allows users
 * to interactively create a task list for them to keep track of their tasks.
 *
 * @author A0233290M
 * @version  Week3
 *
 * */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ArrayList<Task> taskArrayList;
    private Parser parse;
    private MainWindow gui;
    /**
     * Constructs an instance of the Duke class.
     *
     */
    public Duke() {
        this.gui = new MainWindow();
        this.taskArrayList = new ArrayList<>();
        this.ui = new Ui(taskArrayList);
        try {
            this.tasks = new TaskList(this.taskArrayList, this.ui);
            this.storage = new Storage(this.tasks, this.ui);
            this.parse = new Parser(tasks, ui);
        } catch (IOException ioe) {
            System.out.println("--------------------------------------\n");
            System.out.println(ioe.getMessage());
            System.out.println("--------------------------------------\n");
        }
    }

    /**
     * Helper method to print out a dotted line.
     */
    private static void printLine() {
        System.out.println("--------------------------------------\n");
    }
    /**
     * Serves as the main entry point to the Duke application.
     *
     * @param args inputs from client.
     */
    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (DukeException de) {
            printLine();
            System.out.println(de.getMessage());
            printLine();
            main(args);
        } catch (IOException io) {
            printLine();
            System.out.println(io.getMessage());
            printLine();
        }
    }

    /**
     * Serves as the method that calls on utility classes that implements the features
     * of the Duke Chat bot.
     *
     * @throws DukeException If Categorise encounters input issue when reading Duke commands.
     * @throws IOException If save throws an IOException which will be passed to run to throw.
     */
    public void run() throws DukeException, IOException {
        this.ui.greet();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        assert(str != null);
        String uncap = str.toLowerCase();
        while (!str.equals("bye")) {
            this.parse.categorise(str);
            str = sc.nextLine();
        }
        sc.close();
        Storage.save(this.taskArrayList);
        ui.bye();
    }

    public String getResponse(String input) throws IOException {
        String response = parse.categorise(input);
        if (response.equals(this.ui.bye())) {
            System.exit(0);
        }
        return response;
    }

}
