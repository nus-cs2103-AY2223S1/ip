import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private UI ui;
    private Storage storage;
    private TaskList tasks;

    private File file = Path.of("data/duke.txt").toFile();

    private ArrayList<Task> listOfActions = new ArrayList<Task>(100);

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
            //Ok got shit in the task
            this.tasks.printContent();
            ui.showGotTask();
        } catch (DukeException e) {
            ui.showNoTask();
            //does nothing but instantiate a object
            this.tasks = new TaskList();
        }

    }

    public void run() {
        String type;
        int currentAction = this.storage.getSize();
        int end = 0;
        ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (end != 1) {
            //If user wants to check the list
            String output = list(tasks.taskArray, currentAction);
            String[] input = userInput.split(" ");
            Parser parse = new Parser(this.tasks, userInput);
            if (parse.isErreneous()) {
                type = parse.getType();
                ui.showInaccurateInput();
            } else  {
                if (parse.isAction) {
                    currentAction++;
                }
                type = parse.getType();
            }
            if (type.equals("bye")) {
                ui.goodByeMessage();
                break;
            } else if (type.equals("list")) {
                ui.showList(tasks.getList());
            } else if (type.equals("delete")) {
                currentAction--;
            }

            userInput = sc.nextLine();
        }
    }


    public static void main(String[] args) {

        new Duke("data/duke.txt").run();

    }

    /*public static void bye(ArrayList<Task> listOfActions, File file) {

        System.out.println("Thank you and ATB :)");
        try {
            FileWriter writer = new FileWriter(file.getPath());
            for (Task t : listOfActions) {
                writer.write(t.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Oops");
        }
    }

    public static void mark(String userInput, ArrayList<Task> listOfActions) throws DukeException{
        try {
            int position = Character.getNumericValue(userInput.charAt(5)) - 1;
            listOfActions.get(position).mark();
            System.out.println("----------------------\n" + "Congrats on completing :)\n" +
                    listOfActions.get(position) + "\n----------------------\n");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

    //Unmark method
    public static void unMark(String userInput, ArrayList<Task> listOfActions) throws DukeException{
        try {
            int position = Character.getNumericValue(userInput.charAt(7)) - 1;
            listOfActions.get(position).unMark();
            System.out.println("----------------------\n" + "One more mission ;)\n" +
                    listOfActions.get(position) + "\n----------------------\n");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

    //td method
    public static void toDo(String userInput, ArrayList<Task> listOfActions, int currentAction) throws DukeException{
        try {
            //action
            if (userInput.length() < 5) {
                throw new StringIndexOutOfBoundsException("");
            }
            String action = userInput.substring(4);
            Todo td = new Todo(action.strip());
            listOfActions.add(td);
            System.out.println("----------------------\n" + "Ok Solid you got this work to do:\n" +
                    listOfActions.get(currentAction) + String.format("\nYou have a total of %d work to do", currentAction + 1)
                    + "\n----------------------\n");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

    //dedline method
    public static void deadLine(String userInput, ArrayList<Task> listOfActions, int currentAction) throws DukeException{
        try {
            //get action
            String[] split_Description = userInput.split(" ");
            String string_Date = split_Description[4];
            String string_Time = split_Description[5];
            String action = split_Description[1];
            //get deadline
            String dedline = string_Date + " " + string_Time;
            Deadline ded = new Deadline(action, dedline);
            listOfActions.add(ded);
            System.out.println("----------------------\n" + "Ok Solid you got this work to do:\n" +
                    listOfActions.get(currentAction) + String.format("\nYou have a total of %d work to do", currentAction + 1)
                    + "\n----------------------\n");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }


    //event method
    public static void event(String userInput, ArrayList<Task> listOfActions, int currentAction) throws DukeException {
        try {
            //get action
            int index = userInput.indexOf("/");
            String action = userInput.substring(6, index);
            //get date
            String dedline = userInput.substring(index + 3);
            Event e = new Event(action.strip(), dedline.strip());
            listOfActions.add(e);
            System.out.println("----------------------\n" + "Ok Solid you got this work to do:\n" +
                    listOfActions.get(currentAction) + String.format("\nYou have a total of %d work to do", currentAction + 1)
                    + "\n----------------------\n");
        } catch(StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

    //delete method
    public static void removeTask(String userInput, ArrayList<Task> listOfActions, int currentAction)
            throws DukeException {
        try {
            int index = Character.getNumericValue(userInput.charAt(7)) - 1;
            String removed = listOfActions.get(index).toString();
            listOfActions.remove(index);
            System.out.println("----------------------\n" + "Noted, The following task has been removed\n" +
                    removed + String.format("\nYou have a total of %d work to do still", currentAction - 1) +
                    "\n----------------------\n");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
    }

     */

    public static String list(ArrayList<Task> listOfActions, int currentAction) {
        String o = "";
        for (int i = 0; i < currentAction; i++) {
            o = o + String.format("%d", i + 1) + "." + listOfActions.get(i) + "\n";
        }
        return o;
    }





}


