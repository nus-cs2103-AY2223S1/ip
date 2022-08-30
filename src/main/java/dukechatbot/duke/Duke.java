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
    private ArrayList<Task> al;
    private Parser parse;

    /**
     * Constructs an instance of the Duke class.
     *
     * @param filePath path of file to be read for loading and saving task list contents.
     */
    public Duke(String filePath) {
        this.al = new ArrayList<>();
        this.ui = new Ui(al);
        try {
            this.tasks = new TaskList(al);
            this.storage = new Storage(filePath, this.tasks, this.ui);
            this.parse = new Parser(tasks, ui);
        } catch (IOException ioe) {
            System.out.println("--------------------------------------\n");
            System.out.println(ioe.getMessage());
            System.out.println("--------------------------------------\n");
        }
    }

    /**
     * Serves as the main entry point to the Duke application.
     *
     * @param args inputs from client.
     */
    public static void main(String[] args) {
        try {
            new Duke("storage.txt").run();
        } catch (DukeException de) {
            System.out.println("--------------------------------------\n");
            System.out.println(de.getMessage());
            System.out.println("--------------------------------------\n");
            main(args);
        } catch (IOException io) {
            System.out.println("--------------------------------------\n");
            System.out.println(io.getMessage());
            System.out.println("--------------------------------------\n");
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
        String uncap = str.toLowerCase();
        while (!str.equals("bye")) {
            this.parse.categorise(str);
            str = sc.nextLine();
        }
        sc.close();
        storage.save();
        ui.bye();
    }
}
