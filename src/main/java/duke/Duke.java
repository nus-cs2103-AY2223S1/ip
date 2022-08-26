package duke;
import java.io.*;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static ArrayList<Task> listOfTask = new ArrayList<>();
    private final static String DIRECTORY = "./DATA";
    private final static String FILENAME = "duke.txt";
    private final static String PATHNAME = String.valueOf(Paths.get(DIRECTORY, FILENAME));

    //    static String getDirectory = DIRECTORY;
//    static String getFilename = FILENAME;
   // static String getPathname = PATHNAME;

//    public static void main(String[] args) throws DukeException {
//        Ui ui = new Ui();
//        ui.greetUi();
//        Scanner input = new Scanner(System.in);
//        Storage storage = new Storage(PATHNAME, listOfTask, DIRECTORY);
//        storage.getData();
//            while (input.hasNext()) {
//                try {
//                    String s = input.nextLine();
//                    int index = s.indexOf("/");
//                    TaskList taskList = new TaskList(listOfTask);
//                    Parser parser = new Parser(s);
//                    if (s.equals("bye")) {
//                        storage.updateFile();
//                        break;
//                    } else if (s.equals("list")) {
//                        taskList.showList();
//                    } else if (parser.isSubstringForMarkCommand()) {
//                        int i = Integer.parseInt(s.substring(5)) - 1;
//                        Task task = listOfTask.get(i);
//                        task.mark();
//                    } else if (parser.isSubstringForUnmarkCommand()) {
//                        int i = Integer.parseInt(s.substring(7)) - 1;
//                        Task task = listOfTask.get(i);
//                        task.unmark();
//                    } else if (parser.isSubstringForDeadlineCommand()) {
//                        if(s.length() == 8){
//                            throw new DukeDeadlineEmptyException();
//                        }
//                        String subS = s.substring(9, index - 1);
//                        String subString = s.substring(index + 4);
//                        try {
//                            LocalDate date = LocalDate.parse(subString);
//                            Task t = new Deadlines(subS,false, date);
//                            taskList.addToList(t);
//                        } catch (DateTimeException e) {
//                            Task t = new Deadlines(subS,false, subString);
//                            taskList.addToList(t);
//                        }
//                    } else if (parser.isSubstringForEventCommand()) {
//                        if(s.length() == 5){
//                            throw new DukeEventEmptyException();
//                        }
//                        Task t = new Events(s.substring(6, index - 1),
//                                false, s.substring(index + 4));
//                        taskList.addToList(t);
//                    } else if (parser.isSubstringForTodoCommand()) {
//                        if(s.length() == 4){
//                            throw new DukeTodoEmptyException();
//                        }
//                        Task t = new ToDos(s.substring(5), false);
//                        taskList.addToList(t);
//                    } else if (parser.isSubstringForDeleteCommand()) {
//                        taskList.delete(s);
//                    } else {
//                        throw new DukeUnknownWordException();
//                    }
//                } catch (DukeException e) {
//                    System.out.println(e.toString());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            ui.bidFarewellUi();
//    }
//}

//
//    public static void main(String[] args) {
//        Ui ui = new Ui();
//        ui.greetUi();
//        Storage storage = new Storage(PATHNAME, listOfTask, DIRECTORY);
//        storage.getData();
//        Scanner input = new Scanner(System.in);
//        while (input.hasNext()) {
//            try {
//                //String fullCommand = ui.readCommand();
//                String fullCommand = input.nextLine();
//                Parser parser = new Parser(fullCommand);
//                if (parser.isSubStringForExitCommand()){
//                    ExitCommand exitCommand = new ExitCommand();
//                    exitCommand.execute(fullCommand,listOfTask,ui,storage);
//                    break;
//                }
//                Command c = parser.parse();
//                c.execute(fullCommand, listOfTask, ui, storage);
//
//            } catch (DukeException e) {
//                System.out.println(e.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}



    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greetUi();
        Storage storage = new Storage(PATHNAME, listOfTask, DIRECTORY);
        storage.getData();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            try {
                String fullCommand = input.nextLine();
                Parser parser = new Parser(fullCommand);
                if (parser.isSubStringForExitCommand()){
                    ExitCommand exitCommand = new ExitCommand();
                    exitCommand.execute(fullCommand,listOfTask,ui,storage);
                    break;
                }
                Command c = parser.parse();
                c.execute(fullCommand, listOfTask, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




