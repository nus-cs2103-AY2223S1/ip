package duke;

import java.time.DateTimeException;
import java.util.Scanner;

public class Duke {

    Storage storage;
    TaskList tList;
    Ui ui;
    Boolean active;

    public Duke(String dirPath, String dataName) {
        this.storage = new Storage("data/", "save.txt");
        this.ui = new Ui();
        this.tList = new TaskList();
        this.active = true;
    }

    public void exit(){
        this.active = false;
    }


    public void processingInput(String s) {
        try {
            String[] parsed = Parser.parseInput(s);

            if (parsed[0].equals("bye")) {
                ui.printFarewell();
                exit();

            } else if (parsed[0].equals("list")) {
                ui.printList(tList);

            } else if (parsed[0].equals("mark")) {
                int a = Integer.parseInt(parsed[1]) - 1;
                tList.getTask(a).markDone();

                ui.printMarkTaskDone(tList.getTask(a));

            } else if (parsed[0].equals("unmarked")) {
                int a = Integer.parseInt(parsed[1]) - 1;
                tList.getTask(a).markUndone();

                ui.printMarkTestUndone(tList.getTask(a));

            } else if (parsed[0].equals("delete")) {
                int a = Integer.parseInt(parsed[1]) - 1;

                Task temp = tList.getTask(a);
                tList.removeTask(a);
                ui.printDelete(temp, tList);


            } else if (parsed[0].equals("todo")) {
                Todo a = new Todo(parsed[1]);
                tList.addTask(a);
                ui.printTaskAdded(a, tList);


            } else if (parsed[0].equals("deadline")) {
                Deadline d = new Deadline(parsed[1], parsed[2]);
                tList.addTask(d);
                ui.printTaskAdded(d, tList);

            } else if (s.indexOf("event") == 0 ) {
                Event e = new Event(parsed[1], parsed[2]);
                tList.addTask(e);
                ui.printTaskAdded(e, tList);

            } else {
                throw new InvalidTaskException("No valid task descriptor");
            }

        } catch (EmptyDescriptionException | InvalidTaskException | InvalidFormatException e) {
            ui.printException(e);
        } catch (DateTimeException e) {
            ui.printErrorMessage("Error! Date format incorrect (YYYY-MM-DD HH:mm)");
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorMessage("Invalid index access detected!");

        }

    }


    public void run() {
        this.ui.printGreetings();
        this.storage.loadFile(this.tList);

        Scanner myScan = new Scanner(System.in);

        while (active) {
            String s = myScan.nextLine();
            processingInput(s);
            storage.saveFile(this.tList);


        }

    }

    public static void main(String[] args) {

        Duke d = new Duke("data/", "save.txt");

        d.run();
    }
}
