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


    String getResponse(String str) throws IOException {
        String first = str.split(" ")[0];
        if (first.equals("bye")) {
            return "Bye see you again buddy !";
        } else if (first.equals("list")) {
            return tLst.list();
        } else if (first.equals("mark")) {
            Task current = tLst.get(Integer.parseInt(str.split(" ")[1]) - 1);
            String response =  tLst.mark(current);
            Storage.writeToFile(tLst.getTasks());
            return response;
        } else if (first.equals("unmark")) {
            Task current = tLst.get(Integer.parseInt(str.split(" ")[1]) - 1);
            String response =  tLst.unmark(current);
            Storage.writeToFile(tLst.getTasks());
            return response;
        } else if (first.equals("delete")) {
            Task current = tLst.get(Integer.parseInt(str.split(" ")[1]) - 1);
            String response = tLst.delete(current);
            Storage.writeToFile(tLst.getTasks());
            return response;
        } else if (first.equals("find")) {
            return tLst.find(str.split(" ")[1]);
        } else {
            if (first.equals("deadline")) {
                if (str.endsWith("deadline")) {
                    return "Oops The description of deadline cannot be empty !";
                }
                String currD = str.substring(str.indexOf("deadline") + 8, str.indexOf("/by"));
                String response = tLst.add(new Deadline(currD.substring(1), str.substring(str.indexOf("/by") + 4)));
                Storage.writeToFile(tLst.getTasks());
                return response;
            } else if (first.equals("event")) {
                if (str.endsWith("event")) {
                    return "Oops The description of event cannot be empty !";
                }
                String currE = str.substring(str.indexOf("event") + 5, str.indexOf("/at"));
                String response = tLst.add(new Events(currE.substring(1), str.substring(str.indexOf("/at") + 4)));
                Storage.writeToFile(tLst.getTasks());
                return response;
            } else if (first.equals("todo")) {
                if (str.endsWith("todo")) {
                    return "Oops The description of todo cannot be empty !";

                }
                String currT = str.substring(str.indexOf("todo") + 4);
                String response = tLst.add(new ToDos(currT.substring(1)));
                Storage.writeToFile(tLst.getTasks());
                return response;
            } else {
                return "Oops Sorry I dont know what you are talking about :( ";

            }
        }
    }
}

