import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class Duke {
    public static final String line = "____________________________________________________________";

    private static void printTaskList(ArrayList<Task> taskList) {
        System.out.println(Duke.line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i ++) {
            Task task = taskList.get(i);
            System.out.println((i+ 1) + "." + task.toString());
        }
        System.out.println(Duke.line);
    }

    private static void printResponse(String response) {
        System.out.println(Duke.line);
        System.out.println(response);
        System.out.println(Duke.line);
    }

    private static void updateAndPrintTaskStatus(ArrayList<Task> taskList, int index, boolean isMark) {
        System.out.println(Duke.line);
        index = index - 1;
        Task task = taskList.get(index);
        if (isMark) {
            System.out.println("Nice! I've marked this task as done:");
            task.setTaskStatus(true);
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            task.setTaskStatus(false);
        }
        taskList.set(index, task);
        System.out.println(task.toString());
        System.out.println(Duke.line);
    }

    private static void addAndPrintTask(ArrayList<Task> list, Task task) {
        list.add(task);
        System.out.println(Duke.line);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(Duke.line);
    }

    private static void deleteAndPrintTask(ArrayList<Task> taskList, int index) {
        index = index - 1;
        Task task = taskList.remove(index);
        System.out.println(Duke.line);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(Duke.line);
    }

    public enum Keyword
    {
        EXIT("bye"), LIST("list"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), DELETE("delete"), MARK("mark"),
        UNMARK("unmark");

        private String keyword;

        private Keyword(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return this.keyword;
        }
    }

    private static boolean processUserInput(String userInput, ArrayList<Task> taskList) throws DukeException{
        String command;
        String taskDetails;

        String exitMessage = "Bye. Hope to see you again soon!";

        if (userInput.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The user input cannot be empty.");
        }
        command = userInput.split(" ")[0];
        if (command.equals(Keyword.EXIT.getKeyword())) {
            printResponse(exitMessage);
            return true;
        } else if (command.equals(Keyword.LIST.getKeyword())) {
            printTaskList(taskList);
        } else if (command.equals(Keyword.MARK.getKeyword()) || command.equals(Keyword.UNMARK.getKeyword())) {
            if (userInput.split(" ").length == 1) {
                throw new DukeException("☹ OOPS!!! The mark/unmark command cannot have a missing index.");
            }
            String index = userInput.split(" ")[1];
            updateAndPrintTaskStatus(taskList, Integer.parseInt(index), (command.equals(Keyword.MARK.getKeyword()) ? true : false));
        } else {
            Task task;
            if (userInput.split(" ", 2).length == 1) {
                if (command.equals(Keyword.TODO.getKeyword())) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else if (command.equals(Keyword.EVENT.getKeyword())) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                } else if (command.equals(Keyword.DEADLINE.getKeyword())) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                } else if (command.equals(Keyword.DEADLINE.getKeyword())) {
                    throw new DukeException("☹ OOPS!!! The delete command cannot have a missing index.");
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            taskDetails = userInput.split(" ", 2)[1];
            if (command.equals(Keyword.TODO.getKeyword())) {
                task = new Todo(taskDetails);
                addAndPrintTask(taskList, task);
            } else if (command.equals(Keyword.EVENT.getKeyword())) {
                task = new Event(taskDetails.split("/")[0], taskDetails.split("/")[1].split(" ", 2)[1]);
                addAndPrintTask(taskList, task);
            } else if (command.equals(Keyword.DEADLINE.getKeyword())) {
                task = new Deadline(taskDetails.split("/")[0], taskDetails.split("/")[1].split(" ", 2)[1]);
                addAndPrintTask(taskList, task);
            }  else if (command.equals(Keyword.DELETE.getKeyword())) {
                int taskIndex = Integer.parseInt(taskDetails);
                if (taskIndex > taskList.size()  || taskIndex == 0) {
                    throw new DukeException("☹ OOPS!!! The task index is out of range");
                }
                deleteAndPrintTask(taskList, taskIndex);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return false;
    }

    public static ArrayList<Task> readTaskListFromStorage(ArrayList<Task> taskList, String filePath) {
        File file;
        try {
            file = new File(filePath);
            if (file.isDirectory()) {
                throw new DukeException("☹ OOPS!!! Invalid file path, path given is a directory");
            }
            if (file.exists()) {
                try {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        String nextLine = scanner.nextLine();
                        String[] splitString = nextLine.split("\\|");

                        Task task;
                        switch (splitString[0]) {
                            case "T":
                                task = new Todo(splitString[2]);
                                break;
                            case "D": {
                                task = new Deadline(splitString[2], splitString[3]);
                                break;
                            }
                            case "E": {
                                task = new Event(splitString[2], splitString[3]);
                                break;
                            }
                            default:
                                throw new DukeException("☹ OOPS!!! Invalid task type found in file!");
                        }

                        if ((splitString[1] == "1")) {
                            task.setTaskStatus(true);
                        } else {
                            task.setTaskStatus(false);
                        }

                        taskList.add(task);
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    throw new DukeException("☹ OOPS!!! File could not be found");
                }
            }
        } catch (DukeException exception) {
            printResponse(exception.toString());
        } finally {
            return taskList;
        }
    }

    public static void writeTaskListToStorage(ArrayList<Task> taskList, String filePath) {
        File file;
        try {
            file = new File(filePath);
            if (file.isDirectory()) {
                throw new DukeException("☹ OOPS!!! Invalid file path, path given is a directory");
            }

            if (!file.exists()) {
                try {
                    if (!file.getParentFile().mkdirs()) {
                        throw new DukeException("☹ OOPS!!! Directory could not be created");
                    }

                    if (!file.createNewFile()) {
                        throw new DukeException("☹ OOPS!!! File could not be created");
                    }
                } catch (IOException exception) {
                    throw new DukeException("☹ OOPS!!! Something went wrong when trying to create file. Error message: "
                            + exception.getMessage());
                }
            }

            try {
                FileWriter fileWriter = new FileWriter(file);
                for (Task task : taskList) {
                    //lineSeparator used to support multiple systems
                    fileWriter.write(task.toFileString() + System.lineSeparator());
                }
                fileWriter.close();
            } catch (IOException exception) {
                throw new DukeException("☹ OOPS!!! Could not be written to file. Error message: "
                        + exception.getMessage());
            }

        } catch (DukeException exception) {
            printResponse(exception.toString());
        }
    }

    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);

        String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";

        String userInput;

        ArrayList<Task> taskList = new ArrayList<>();

        taskList = readTaskListFromStorage(taskList, "data/duke.txt");

        printResponse(greetingMessage);

        while(true) {
            userInput = scanner.nextLine();
            try {
                Boolean exitCommand = processUserInput(userInput, taskList);
                if (exitCommand) {
                    writeTaskListToStorage(taskList, "data/duke.txt");
                    break;
                }
            } catch (DukeException exception) {
                printResponse(exception.toString());
            }

        }
    }
}
