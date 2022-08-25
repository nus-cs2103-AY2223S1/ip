import duke.command.Command;
import duke.model.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
//    private static final String LIST = "list";
//    private static final String MARK = "mark";
//    private static final String UNMARK = "unmark";
//    private static final String TODO = "todo";
//    private static final String DEADLINE = "deadline";
//    private static final String EVENT = "event";
//    private static final String BYE = "bye";
//    private static final String DELETE = "delete";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke (String filePath) {
        Scanner sc = new Scanner(System.in);
        this.ui = new Ui(sc);
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }
    public static void main(String[] args) {
        new Duke("data/Duke.txt").run();
    }

    public void run() {
        ui.greetUser();
        boolean isExit = false;

        ui.showDivider();
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showDivider();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, storage, ui);
            isExit = c.getIsExit();
            ui.showDivider();
        }
        ui.close();
        Ui.sayBye();
    }

//    public static void askUser() {
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            String userInput = sc.nextLine();
//            System.out.println("\t______________________________________________________");
//            String[] arr = userInput.split(" ", 2);
//
//            if (arr.length == 0) {
//                continue;
//            }
//
//            String command = arr[0].toLowerCase();
//            if (command.equals(LIST)) {
//                listTasks();
//            } else if (command.equals(MARK)) {
//                if (arr.length <= 1) {
//                    System.out.println("\tMissing task number to mark!");
//                } else {
//                    try {
//                        int taskNumber = Integer.parseInt(arr[1]);
//                        mark(taskNumber);
//                    } catch (NumberFormatException e) {
//                        System.out.println("\tInput is not a valid task number " + e.getMessage());
//                    }
//                }
//            } else if (command.equals(UNMARK)) {
//                if (arr.length <= 1) {
//                    System.out.println("\tMissing task number to unmark!");
//                } else {
//                    try {
//                        int taskNumber = Integer.parseInt(arr[1]);
//                        unmark(taskNumber);
//                    } catch (NumberFormatException e) {
//                        System.out.println("\tInput is not a valid task number " + e.getMessage());
//                    }
//                }
//            } else if (command.equals(TODO) || command.equals(DEADLINE) || command.equals(EVENT)) {
//                if (arr.length <= 1) {
//                    System.out.println("\tMissing description after given command!");
//                } else {
//                    try {
//                        userInput = arr[1];
//                        Task t = addTasks(command, userInput);
//                        System.out.println("\tGot it! I've added this task!");
//                        System.out.println("\t\t" + t);
//                        System.out.println("\tYou now have " + tasks.size() + " tasks in the list!");
//                    } catch (IOException e) {
//                        System.out.println(e.getMessage());
//                    }
//                }
//
//            } else if (command.equals(DELETE)) {
//                if (arr.length <= 1) {
//                    System.out.println("\tMissing task number to delete!");
//                } else {
//                    try {
//                        int taskNumber = Integer.parseInt(arr[1]);
//                        delete(taskNumber);
//                    } catch (NumberFormatException e) {
//                        System.out.println("\tInput is not a valid task number " + e.getMessage());
//                    }
//                }
//            } else {
//                System.out.println("\t'" + userInput + "'" + " not recognised!");
//            }
//            System.out.println("\t______________________________________________________\n");
//        }
//    }


//
//    public static Task addTasks(String taskType, String userInput) throws IOException {
//        File dir = new File(filePath.split("/")[0]);
//        System.out.println(dir.exists());
//        if (!dir.exists()) {
//            Boolean directoryCreated = dir.mkdirs();
//        }
//
//        File f = new File(filePath);
//        if (!f.exists()) {
//            Boolean fileCreated = f.createNewFile();
//        }
//
//        FileWriter fw = new FileWriter(filePath, true);
//        if (taskType.equals(TODO)) {
//            fw.write("T | 0 | " + userInput + "\n");
//        } else if (taskType.equals(DEADLINE)) {
//            String str = userInput.replace("/by", "|");
//            fw.write("D | 0 | " + str + "\n");
//        } else {
//            String str = userInput.replace("/at", "|");
//            fw.write("E | 0 | " + str + "\n");
//        }
//        fw.close();
//
//        if (f.exists()) {
//            Task t;
//            if (taskType.equals(TODO)) {
//                t = new ToDo(userInput);
//            } else if (taskType.equals(DEADLINE)) {
//                String[] arr = userInput.split(" /by ", 2);
//                t = new Deadline(arr[0], arr[1]);
//            } else {
//                String[] arr = userInput.split(" /at ", 2);
//                t = new Event(arr[0], arr[1]);
//            }
//            tasks.add(t);
//            return t;
//        } else {
//            throw new IOException();
//        }
//    }

//    public static void mark(int taskNumber) {
//        tasks.get(taskNumber - 1).markAsDone();
//        System.out.println("\tAlright! Marked this task as done!");
//        System.out.println("\t\t" + tasks.get(taskNumber - 1));
//    }
//
//    public static void unmark(int taskNumber) {
//        tasks.get(taskNumber - 1).markAsNotDone();
//        System.out.println("\tOkay! Unmarked this task!");
//        System.out.println("\t\t" + tasks.get(taskNumber - 1));
//    }
//
//    public static void delete(int taskNumber) {
//        Task t = tasks.get(taskNumber - 1);
//        tasks.remove(taskNumber - 1);
//        System.out.println("\tAlright! I've deleted this task!");
//        System.out.println("\t\t" + t);
//        System.out.println("\tYou now have " + tasks.size() + " tasks in the list!");
//    }
}
