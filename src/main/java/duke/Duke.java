package duke;

import java.io.IOException;

/**
 * Main class that contains duke,
 * Duke keeps track of all tasks to be done
 *
 */
public class Duke {

    private Storage storage;
    private TaskList tLst;
    private Ui ui;

    /**
     * Runs by getting data from text file in filepath
     * Data contains list of tasks to be done
     *
     * @param filePath Contains text file with list of tasks or empty if no tasks yet
     * @throws DukeException Exception thrown if file empty
     */
    public Duke(String filePath) {
        try {
            ui = new Ui();
            ui.hello();
            storage = new Storage(filePath);
            tLst = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tLst = new TaskList();
        }
    }

    String helpResponse() {
        return "Welcome to DukeHam\n\n"
                + "Below are all the commands that you can use\n\n"
                + "bye: to exit the program\n\n"
                + "list: to view all tasks currently\n\n"
                + "mark: follow this command with task number to mark it\n\n"
                + "unmark: follow this command with task number to unmark it\n\n"
                + "delete: follow this command with task number to delete it\n\n"
                + "todo: follow command with 'task name' to add todo task\n\n"
                + "deadline: follow command with 'task name /by YYYY-MM-DD' to add deadline tasl\n\n"
                + "event: follow command with 'task name /at time' to add event task\n\n"
                + "find: follow command with keyword to find tasks with that word\n\n" ;
    }

    String byeResponse() {
        return "Bye see you again buddy !";
    }

    String listResponse() {
        return tLst.list();
    }

    String markResponse(String str) throws IOException {
        Task current = tLst.get(Integer.parseInt(str.split(" ")[1]) - 1);
        String response =  tLst.mark(current);
        Storage.writeToFile(tLst.getTasks());
        return response;
    }

    String unmarkResponse(String str) throws IOException {
        Task current = tLst.get(Integer.parseInt(str.split(" ")[1]) - 1);
        String response =  tLst.unmark(current);
        Storage.writeToFile(tLst.getTasks());
        return response;
    }

    String deleteResponse(String str) throws IOException {
        Task current = tLst.get(Integer.parseInt(str.split(" ")[1]) - 1);
        String response = tLst.delete(current);
        Storage.writeToFile(tLst.getTasks());
        return response;
    }

    String deadlineResponse(String str) throws IOException {
        if (!str.endsWith("deadline")) {
            String currD = str.substring(str.indexOf("deadline") + 8, str.indexOf("/by"));
            String response = tLst.add(new Deadline(currD.substring(1), str.substring(str.indexOf("/by") + 4)));
            Storage.writeToFile(tLst.getTasks());
            return response;
        }
        return "Oops The description of deadline cannot be empty !";
    }

    String eventResponse(String str) throws IOException {
        if (!str.endsWith("event")) {
            String currE = str.substring(str.indexOf("event") + 5, str.indexOf("/at"));
            String response = tLst.add(new Events(currE.substring(1), str.substring(str.indexOf("/at") + 4)));
            Storage.writeToFile(tLst.getTasks());
            return response;
        }
        return "Oops The description of event cannot be empty !";
    }

    String todoResponse(String str) throws IOException {
        if (!str.endsWith("todo")) {
            String currT = str.substring(str.indexOf("todo") + 4);
            String response = tLst.add(new ToDos(currT.substring(1)));
            Storage.writeToFile(tLst.getTasks());
            return response;
        }
        return "Oops The description of todo cannot be empty !";
    }

    String getResponse(String str) throws IOException {
        String first = str.split(" ")[0];
        if (first.equals("help")) {
            return helpResponse();
        } else if (first.equals("bye")) {
            return byeResponse();
        } else if (first.equals("list")) {
            return listResponse();
        } else if (first.equals("mark")) {
            return markResponse(str);
        } else if (first.equals("unmark")) {
            return unmarkResponse(str);
        } else if (first.equals("delete")) {
            return deleteResponse(str);
        } else if (first.equals("find")) {
            return tLst.find(str.split(" ")[1]);
        } else if (first.equals("deadline")) {
            return deadlineResponse(str);
        } else if (first.equals("event")) {
            return eventResponse(str);
        } else if (first.equals("todo")) {
            return todoResponse(str);
        } else {
                return "Oops Sorry I don't know what you are talking about :( ";
            }
        }
    }


