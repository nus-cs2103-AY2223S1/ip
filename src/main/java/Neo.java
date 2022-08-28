import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;
public class Neo {
    private Storage stor;
    private Ui ui;
    private TaskList arrayLL;
    private Parser parser;

    public Neo() {
        this.stor = new Storage();
        this.ui = new Ui();
        this.arrayLL = new TaskList();
        this.parser = new Parser(ui, stor, arrayLL);
    }


    public static void main(String[] args) throws NeoException, IOException {

        List<String> arrayList = new ArrayList<String>();
        List<Task> arrayL = new ArrayList<Task>();
        String userText;

        Task[] tasks = new Task[100];
        Neo neo = new Neo();
        neo.ui.printStart();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter items you want to add to the list, if you want to quit enter bye");
            System.out.println("");
            System.out.print("Enter Your command: ");
            userText = sc.nextLine();
            System.out.println("");

            if (userText.equals("bye") || userText.equals("Bye")) {
                System.out.println("Exiting chatbot! Hope to see you again");
                break;
            }

            neo.parser.checkText(userText);
        }
    }
}


