package duke;

import command.ByeCommand;
import command.Command;
import task.DukeTask;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private ArrayList<DukeTask> tasklist;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        tasklist = new ArrayList<DukeTask>();
        Storage.setOnce(tasklist, filePath);
        // try {
        //     tasks = new DukeTaskList(storage.load());
        // } catch (DukeException e) {
        //     ui.showLoadingError();
        //     tasks = new DukeTaskList();
        // }
    }

    public void run() {
        storage.read();
        Scanner input = new Scanner(System.in);
        System.out.println("What are your commands sir:");
        boolean isRunning = true;
        while(isRunning) {
            if (input.hasNext()) {
                String str = input.nextLine();
                Command cmd = Parser.parse(str);
                if (cmd instanceof ByeCommand) {
                    System.out.println("Goodbye, hope to see you again");
                    isRunning = false;
                    input.close();
                    break;
                }
                try {
                    cmd.deconstruct(tasklist, ui, storage);
                } catch (Exception e) {
                    System.out.println(e);
                }
                
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/list.txt").run();
    }
}
/* 
    private static final ArrayList<DukeTask> tasklist = new ArrayList<>();
    public static void main(String[] args) {
        read();
        Scanner input = new Scanner(System.in);
        System.out.println("What are your commands sir:");
        boolean pred = true;
        while(pred) {
            if (input.hasNext()) {
                String str = input.nextLine();
                if (str.startsWith("list")) {
                    System.out.println("You requested to view your schedule:");
                    for (int j = 0; j < tasklist.size(); j++) {
                        System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
                    }
                } else if (str.startsWith("bye")) {
                    System.out.println("Bye. Hope to see you again");
                    pred = false;
                    input.close();

                } else if (str.startsWith("mark")) {
                    try {
                        int j = Integer.valueOf(str.substring(5));
                        tasklist.get(j).isMarked = true;
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
                        save();
                    } catch (Exception e) {
                        System.out.println("Something went wrong, here's the error message cuz im lazy to figure it out for you: " + e);
                    }


                } else if (str.startsWith("unmark")) {
                    int j = Integer.valueOf(str.substring(7));
                    tasklist.get(j).isMarked = false;
                    System.out.println("Got it. I've mark this task as not done:");
                    System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
                    save();

                } else if (str.startsWith("todo")) {
                    try {
                        str = str.substring(5);
                        if (str.isBlank()) {
                            System.out.println("Oops, todo can't be empty");
                            continue;
                        }
                        DukeTask t = new DukeTask(str, false, 'T');
                        tasklist.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(String.format("List %d: ", tasklist.size() - 1) + t.toString());
                        save();
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Oops, todo can't be empty");
                    } catch (Exception e) {
                        System.out.println("...");
                        System.out.println(e);
                    }


                } else if (str.startsWith("deadline")) {
                    try{
                        str = str.substring(9);
                        String s1 = str.substring(0, str.indexOf('/') - 1);
                        LocalDateTime ldt1 = LocalDateTime.parse(str.substring(str.indexOf('/') + 1), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        DukeTask t = new DukeTaskDeadline(s1, false, 'D', ldt1);
                        tasklist.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(String.format("List %d: ", tasklist.size() - 1) + t.toString());
                        save();
                    } catch (DateTimeParseException e) {
                        System.out.println("Looks like your date time formatting is wrong, please format it like so: \"yyyy-mm-dd hh:mm\"");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please format your Deadline request with a /{deadline}");
                    }
                    

                } else if (str.startsWith("event")) {
                    str = str.substring(6);
                    String s1 = str.substring(0, str.indexOf('/') - 1);
                    String s2 = "(" + str.substring(str.indexOf('/') + 1) + ')';
                    DukeTask t = new DukeTaskEvent(s1, false, 'E', s2);
                    tasklist.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(String.format("List %d: ", tasklist.size() - 1) + t.toString());
                    save();

                } else if (str.startsWith("delete")) {
                    try {
                        int j = Integer.valueOf(str.substring(7));
                        System.out.println("Alight! I've deleted this task for you:");
                        System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
                        tasklist.remove(j);
                        save();

                    } catch (Exception e) {
                        System.out.println("Something went wrong, here's the error message cuz im lazy to figure it out for you: " + e);
                    }


                } else {
                    System.out.println("Sorry, I don't know what that means");

                }
            }
        }

    }
    */

