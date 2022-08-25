import java.util.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final static String fileLocation = "./data/duke.txt";

    public Duke(String filePath) throws Exception {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.readFiles());
    }

    public void run() throws Exception {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] strarr = str.split(" ");
            String command = strarr[0];
            if (command.equals("bye")) {
                ui.exit();
                storage.saveNewChanges(this.tasks);
                break;
            } else if (command.equals("list")) {
                tasks.getPrintedList();
            } else if (command.equals("mark")) {
                int taskNo = Integer.parseInt(strarr[1]);
                tasks.mark(taskNo);
            } else if (command.equals("unmark")) {
                int taskNo = Integer.parseInt(strarr[1]);
                tasks.unmark(taskNo);
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                tasks.add(str);
            } else if (command.equals("delete")) {
                int taskNo = Integer.parseInt(strarr[1]);
                tasks.delete(taskNo);
            } else {
                storage.saveNewChanges(this.tasks);
                throw new MismatchInputException(":( OOPS!!! I'm sorry, but I don't know what that means");
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new Duke(fileLocation).run();
    }

}

    /*public static void main(String[] args) throws MismatchInputException, Exception {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        //Scanner sc = new Scanner(System.in);
        //SkeletonDuke duke = new SkeletonDuke();
        //duke.greet();
        //duke.readFiles();
        /*while(sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] strarr = str.split(" ");
            String command = strarr[0];
            if(command.equals("bye")) {
                duke.exit();
                duke.saveNewChanges();
                break;
            } else if(command.equals("list")) {
                duke.getList();
            } else if(command.equals("mark")){
                int taskNo = Integer.parseInt(strarr[1]);
                duke.mark(taskNo);
            } else if(command.equals("unmark")){
                int taskNo = Integer.parseInt(strarr[1]);
                duke.unmark(taskNo);
            } else if(command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                duke.add(str);
            } else if(command.equals("delete")){
                int taskNo = Integer.parseInt(strarr[1]);
                duke.delete(taskNo);
            } else {
                duke.saveNewChanges();
                throw new MismatchInputException(":( OOPS!!! I'm sorry, but I don't know what that means" );
            }
        }
    }*/

