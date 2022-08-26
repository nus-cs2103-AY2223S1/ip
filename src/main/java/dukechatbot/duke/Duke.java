package dukechatbot.duke;
import dukechatbot.dukeexception.DukeException;
import dukechatbot.utility.Task;
import dukechatbot.utility.TaskList;
import dukechatbot.utility.Storage;
import dukechatbot.utility.Ui;
import dukechatbot.utility.Parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ArrayList<Task> al;
    private Parser parse;

    public Duke(String filePath) {
        this.al = new ArrayList<>();
        this.ui = new Ui(al);
        try {
            this.storage = new Storage(filePath, this.al);
            this.tasks = new TaskList(al);
            this.parse = new Parser(tasks, ui);
        } catch (IOException ioe) {
            System.out.println("--------------------------------------\n");
            System.out.println(ioe.getMessage());
            System.out.println("--------------------------------------\n");
        }
    }

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
