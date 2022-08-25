import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Boolean;

import static java.lang.Boolean.parseBoolean;

public class Duke {
    private Scanner sc;
    private List<Task> taskList;
    private static String NAME = "DoiMoiBot: ";
    private static String COLON = "added task: ";
    private static String DATA_DIRECTORY = "./data";
    private static String DATA_FILE = "duke.txt";


    public Duke() {
        this.sc = new Scanner(System.in);
        this.taskList = new ArrayList<>(100);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.readData(DATA_DIRECTORY, DATA_FILE);
        String input;

        duke.greet();
        duke.storeInList();

        duke.farewell();
    }

    private void greet() {
        System.out.println(NAME + "Hello! I'm doimoibot\n" + "What can I do you for?");
    }

    private void farewell() {
        System.out.println(NAME + "Goodbye! See you soon!");
    }

    private void parrot(String input) {
        while (!input.equals("bye")) {
            System.out.println(NAME + input);
            input = this.getInput();
        }
    }

    private void storeInList() {
        String input;
        while (true) {
            input = this.getInput();

            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                this.printList();
                continue;
            }
            //logic to mark tasks with error handling
            if (input.indexOf("mark") == 0) {
                try {
                    String substring = input.substring(5);
                    //Since first task is of index 0 in ArrayList
                    int taskIndex = Integer.parseInt(substring) - 1;
                    Task task = taskList.get(taskIndex);
                    task.markAsDone();
                    this.overwriteData(DATA_DIRECTORY,DATA_FILE);
                    System.out.println("Okay! marked as done!\n" + task);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please indicate which task to mark!");
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid task index!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task with that index does not exist!");
                }
                continue;
            }
            //logic to unmark tasks with error handling
            if (input.indexOf("unmark") == 0) {
                try {
                    String substring = input.substring(7);
                    //Since first task is of index 0 in ArrayList
                    int taskIndex = Integer.parseInt(substring) - 1;
                    Task task = taskList.get(taskIndex);
                    task.markAsNotDone();
                    this.overwriteData(DATA_DIRECTORY,DATA_FILE);
                    System.out.println("Okay! marked as not done!\n" + task);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please indicate which task to mark!");
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid task index!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task with that index does not exist!");
                }
                continue;
            }
            //logic to create ToDos
            if (input.indexOf("todo") == 0) {
                try {
                    ToDos todo = new ToDos(input);
                    this.writeData(DATA_DIRECTORY, DATA_FILE, todo);
                    this.taskList.add(todo);
                    System.out.println("Okay! created ToDo!\n" + todo);
                } catch (MissingDescriptionException e) {
                    System.out.println("Please indicate what the task is!");
                }
                continue;
            }
            //logic to create Deadlines
            if (input.indexOf("deadline") == 0) {
                try {
                    Deadlines deadline = new Deadlines(input);
                    System.out.println("Okay! created ToDo!\n" + deadline);
                    this.taskList.add(deadline);
                    this.writeData(DATA_DIRECTORY, DATA_FILE, deadline);
                } catch (MissingDescriptionException e) {
                    System.out.println("Please indicate what the task is!");
                } catch (MissingDeadlineException e) {
                    System.out.println("Please specify the deadline!");
                }
                continue;
            }
            //logic to create Events
            if (input.indexOf("event") == 0) {
                try {
                    Events event = new Events(input);
                    System.out.println("Okay! created ToDo!\n" + event);
                    this.writeData(DATA_DIRECTORY, DATA_FILE, event);
                    this.taskList.add(event);
                } catch (MissingDescriptionException e) {
                    System.out.println("Please indicate what the task is!");
                } catch (MissingTimingException e) {
                    System.out.println("Please specify the timeline!");
                }
                continue;
            }
            if (input.indexOf("delete") == 0) {
                try {
                    String substring = input.substring(7);
                    //Since first task is of index 0 in ArrayList
                    int taskIndex = Integer.parseInt(substring) - 1;
                    Task task = taskList.remove(taskIndex);
                    this.overwriteData(DATA_DIRECTORY,DATA_FILE);
                    System.out.println("Okay! Deleted the specified task!\n" + task);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please indicate which task to mark!");
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid task index!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task with that index does not exist!");
                }
                continue;
            }
            System.out.println("Unknown command! Please do something reasonable!");
        }

    }

    private void readData(String directory, String fileName) {
        try {
            File dir = new File(directory);
            File actualFile = new File(dir, fileName);
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (actualFile.createNewFile()) {
                System.out.println("file created!");
            } else {
                System.out.println("file exists!");
            }

            Scanner reader = new Scanner(actualFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                this.writeToTaskList(data);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void writeToTaskList(String data) {
        String currData = data;
        String[] info = new String[4];

        //store string data between "|" characters in an array
        for (int i = 0; i < 4; i++) {
            int index = currData.indexOf('|');
            info[i] = currData.substring(0, index);
            currData = currData.substring(index + 1);
        }

        String task = info[0];
        boolean isDone = parseBoolean(info[1]);
        String description = info[2];
        String timing = info[3];

        Task toAdd;
        if (task.equals("T")) {
            toAdd = new ToDos(description, isDone);
        } else if (task.equals("D")) {
            toAdd = new Deadlines(description, timing, isDone);
        } else { //task.equals("E")
            toAdd = new Events(description, timing, isDone);
        }
        this.taskList.add(toAdd);
    }

    private void writeData(String directory, String fileName, Task toWrite) {
        System.out.println("writeData called");
        try {
            File dir = new File(directory);
            File actualFile = new File(dir, fileName);
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (actualFile.createNewFile()) {
                System.out.println("file created!");
            } else {
                System.out.println("file exists!");
            }
            FileWriter writer = new FileWriter(actualFile, true);
            writer.write(toWrite.processData() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("ioexception");
        }
      }

      private void overwriteData(String directory, String fileName) {
          try {
              File dir = new File(directory);
              File actualFile = new File(dir, fileName);
              if (!dir.exists()) {
                  dir.mkdir();
              }
              actualFile.createNewFile();
              FileWriter writer = new FileWriter(actualFile, false);
              writer.write("");
              writer.close();
              for (int i = 0; i < this.taskList.size(); i++) {
                  this.writeData(directory, fileName, this.taskList.get(i));
              }
          } catch (IOException e) {
              System.out.println("ioexception");
          }
      }

    private void printList() {
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + ": " + taskList.get(i - 1));
        }
    }

    private String getInput() {
        return this.sc.nextLine();
    }
}
