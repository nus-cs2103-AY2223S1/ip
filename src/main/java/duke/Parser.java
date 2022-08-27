package duke;

import java.io.IOException;
import java.util.Scanner;

public class Parser {
    private TaskList tLst;

    Parser(TaskList tLst) {
        this.tLst = tLst;
    }

    public void read() throws DukeException, IOException {
        Scanner help = new Scanner(System.in);
        while (true) {
            String str = help.nextLine();
            String first = str.split(" ")[0];

            if (first.equals("bye")) {
                System.out.println("Bye see you again buddy !");
                break;
            } else if (first.equals("list")) {
                tLst.list();
            } else if (first.equals("mark")) {
                Task current = tLst.get(Integer.parseInt(str.split(" ")[1]) - 1);
                tLst.mark(current);
                Storage.writeToFile(tLst.getTasks());

            } else if (first.equals("unmark")) {
                Task current = tLst.get(Integer.parseInt(str.split(" ")[1]) - 1);
                tLst.unmark(current);
                Storage.writeToFile(tLst.getTasks());
            } else if (first.equals("delete")) {
                Task current = tLst.get(Integer.parseInt(str.split(" ")[1]) - 1);
                tLst.delete(current);
                Storage.writeToFile(tLst.getTasks());
            } else {
                if (first.equals("deadline")) {
                    if (str.endsWith("deadline")) {
                        throw new DukeException("Oops The description of deadline cannot be empty !");
                    }
                    String currD = str.substring(str.indexOf("deadline") + 8, str.indexOf("/by"));
                    tLst.add(new Deadline(currD.substring(1), str.substring(str.indexOf("/by") + 4)));
                    Storage.writeToFile(tLst.getTasks());
                } else if (first.equals("event")) {
                    if (str.endsWith("event")) {
                        throw new DukeException("Oops The description of event cannot be empty !");
                    }
                    String currE = str.substring(str.indexOf("event") + 5, str.indexOf("/at"));
                    tLst.add(new Events(currE.substring(1), str.substring(str.indexOf("/at") + 4)));
                    Storage.writeToFile(tLst.getTasks());
                } else if (first.equals("todo")) {
                    if (str.endsWith("todo")) {
                        throw new DukeException("Oops The description of todo cannot be empty !");
                    }
                    String currT = str.substring(str.indexOf("todo") + 4);
                    tLst.add(new ToDos(currT.substring(1)));

                    Storage.writeToFile(tLst.getTasks());
                } else {
                    throw new DukeException("Oops Sorry I dont know what you are talking about :( ");

                }
            }
        }
    }

}
