package duke;

import java.util.Scanner;

/**
 * Parse class that handles the string inputted by user.
 */
public class Parser {
    private Duke duke;

    /**
     * Constructor for Parser Class.
     *
     * @param duke The duke object the user is operating on.
     */
    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * Parses the various inputs inputted by user.
     */
    public void start() {
        Scanner input = new Scanner(System.in);
        String text = input.next();
        while (!text.equals("bye")) {
            try {
                switch (text) {
                    //Handle case when task aTodo
                    case "todo" :
                        String tDescription = input.nextLine().trim();
                        duke.addTodo(tDescription);
                        break;

                    //Handle case when task is a deadline
                    case "deadline": {
                        String str = input.nextLine().trim();
                        duke.addDeadline(str);
                        break;
                    }

                    //Handle case when task is an event
                    case "event": {
                        String str = input.nextLine().trim();
                        duke.addEvent(str);
                        break;
                    }

                    //Handle case when user wants to list tasks
                    case "list" :
                        duke.printList();
                        break;

                    //Handle case when user wants to handleMark task
                    case "mark": {
                        //-1 to get index in 0 indexing
                        int index = input.nextInt() - 1;
                        duke.handleMark(index);
                        break;
                    }

                    //Handle case when user wants to handleUnmark task
                    case "unmark": {
                        //-1 to get index in 0 indexing
                        int index = input.nextInt() - 1;
                        duke.handleUnmark(index);
                        break;
                    }

                    //Handle case when user wants to handleDelete task
                    case "delete": {
                        //-1 to get in 0 indexing
                        int index = input.nextInt() - 1;
                        duke.handleDelete(index);
                        break;
                    }

                    //Handle case when user wants to find tasks
                    case "find" :
                        String str = input.nextLine().trim();
                        duke.find(str);
                        break;

                    //Default case: Not any of the tasks(aTodo, Deadline, Event) and hence, throws an Exception
                    default:
                        //To handle any extra words the user keyed in
                        input.nextLine();
                        throw new DukeException("OOPS! I'm sorry, but I don't know what that means :-(");

                }
            } catch (DukeException dE) {
                System.out.println(dE);
            }
            text = input.next();
        }
    }

}