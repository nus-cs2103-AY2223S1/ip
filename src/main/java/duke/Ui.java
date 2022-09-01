package duke;

import java.util.Scanner;

/**
 * Continuously asks the user for input and handles the inputs
 *
 * @author Sean Lam
 */
public class Ui {
    TaskList itemList;

    public Ui(TaskList itemList) {
        this.itemList = itemList;
    }

    public String getOneInput(String input) {
        int index;
        try {
            String command = Parser.getCommand(input);
            switch (command) {
                // Exit
                case "bye":
                    return "Bye. Hope to see you again soon!";
                // List out items
                case "list":
                    return "Here are the tasks in your list:" + itemList;
                case "delete":
                    return itemList.deleteTask(Parser.getDescription(input));
                // mark items
                case "mark":
                    index = Parser.getIndex(input);
                    return itemList.markTask(index);
                // unmark items
                case "unmark":
                    index = Parser.getIndex(input);
                    return itemList.unmarkTask(index);
                case "todo":
                    if (Parser.isValidDescription(input)) {
                        Task toAdd = new ToDo(Parser.getDescription(input));
                        return itemList.addTask(toAdd);
                    } else {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                case "deadline":
                    if (!Parser.isValidDescription(input)) {
                        throw new DukeException("OOPS!!! The description of a duke.Deadline cannot be empty.");
                    } else if (!Parser.isValidDateTime(input)) {
                        throw new DukeException("OOPS!!! The time and date of the duke.Deadline cannot be empty.");
                    } else {
                        //eg. by 2019-10-03 18:00
                        Task toAdd = new Deadline(Parser.getDescription(input),
                                Parser.getDate(input),
                                Parser.getFrom(input));
                        return itemList.addTask(toAdd);
                    }
                case "event":
                    if (!Parser.isValidDescription(input)) {
                        throw new DukeException("OOPS!!! The description of a duke.Event cannot be empty.");
                    } else if (!Parser.isValidDateTime(input)) {
                        throw new DukeException("OOPS!!! The time and date of the duke.Event cannot be empty.");
                    } else {
                        Task toAdd = new Event(Parser.getDescription(input),
                                Parser.getDate(input),
                                Parser.getFrom(input),
                                Parser.getTo(input));
                        return itemList.addTask(toAdd);
                    }
                case "find":
                    return itemList.findTask(Parser.getDescription(input));
                // unrecognised commands
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
}

    public void getUserInputs() {
        Scanner sc = new Scanner(System.in);
        int index;
        System.out.print("Hello! I'm duke.Duke \nWhat can I do for you? \n");

        String input = sc.nextLine();
        boolean carryOn = true;

        while (carryOn) {
            try {
                String command = Parser.getCommand(input);
                switch (command) {
                    // Exit
                    case "bye":
                        carryOn = false;
                        System.out.print("Bye. Hope to see you again soon!");
                        break;
                    // List out items
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        System.out.println(itemList);
                        input = sc.nextLine();
                        break;
                    case "delete":
                        itemList.deleteTask(Parser.getDescription(input));
                        input = sc.nextLine();
                        break;
                    // mark items
                    case "mark":
                        index = Parser.getIndex(input);
                        itemList.markTask(index);
                        input = sc.nextLine();
                        break;
                    // unmark items
                    case "unmark":
                        index = Parser.getIndex(input);
                        itemList.unmarkTask(index);
                        input = sc.nextLine();
                        break;
                    case "todo":
                        if (Parser.isValidDescription(input)) {
                            Task toAdd = new ToDo(Parser.getDescription(input));
                            itemList.addTask(toAdd);
                            input = sc.nextLine();
                        } else {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        break;
                    case "deadline":
                        if (!Parser.isValidDescription(input)) {
                            throw new DukeException("OOPS!!! The description of a duke.Deadline cannot be empty.");
                        } else if (!Parser.isValidDateTime(input)) {
                            throw new DukeException("OOPS!!! The time and date of the duke.Deadline cannot be empty.");
                        } else {
                            //eg. by 2019-10-03 18:00
                            Task toAdd = new Deadline(Parser.getDescription(input),
                                    Parser.getDate(input),
                                    Parser.getFrom(input));
                            itemList.addTask(toAdd);
                            input = sc.nextLine();
                        }
                        break;
                    case "event":
                        if (!Parser.isValidDescription(input)) {
                            throw new DukeException("OOPS!!! The description of a duke.Event cannot be empty.");
                        } else if (!Parser.isValidDateTime(input)) {
                            throw new DukeException("OOPS!!! The time and date of the duke.Event cannot be empty.");
                        } else {
                            Task toAdd = new Event(Parser.getDescription(input),
                                    Parser.getDate(input),
                                    Parser.getFrom(input),
                                    Parser.getTo(input));
                            itemList.addTask(toAdd);
                            input = sc.nextLine();
                        }
                        break;
                    case "find":
                        itemList.findTask(Parser.getDescription(input));
                        input = sc.nextLine();
                        break;
                    // unrecognised commands
                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                input = sc.nextLine();
            }
        }
    }

    public void showLoadingError() {
        System.out.println("Unable to load file. New file has been created");
    }
}
