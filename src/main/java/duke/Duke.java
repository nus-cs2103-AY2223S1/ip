package duke;

import java.util.Scanner;

/**
 * Class containing the main Duke application.
 * Initialises the application.
 */
public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage st = new Storage("./data/dukedata.txt");
        TaskList tl = new TaskList(st.setFile());
        Ui ui = new Ui();

        ui.printIntro();

        while (sc.hasNext()) {
            String curr = sc.nextLine();
            String[] msgWords = curr.split("\\b");
            String command = msgWords[0];
            if (command.equalsIgnoreCase("bye")) {
                sc.close();
                ui.printGoodByeMessage();
                return;
            } else if (command.equalsIgnoreCase("list")) {
                ui.printList(tl);
            } else if (command.equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(msgWords[2]) - 1;
                if (index < 1 || index > tl.size()) {
                    ui.printOutOfBoundsMsg();
                    continue;
                }
                tl.mark(index);
                ui.printMarkedMsg(tl.get(index));
            } else if (command.equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(msgWords[2]) - 1;
                if (index < 1 || index > tl.size()) {
                    ui.printOutOfBoundsMsg();
                    continue;
                }
                tl.unMark(index);
                ui.printUnmarkedMsg(tl.get(index));
            } else if (command.equalsIgnoreCase("delete")) {
                int index = Integer.parseInt(msgWords[2]) - 1;
                Task removed = tl.get(index);
                tl.remove(index);
                ui.printDeleteMsg(removed.toString(), tl.size());
            } else if (command.equalsIgnoreCase("todo")) {
                if (msgWords.length < 2) {
                    ui.printNoTaskInputMsg();
                    continue;
                }
                Todo newTodo = Parser.parseTodoInput(curr);
                tl.add(newTodo);
                ui.printTaskAddedMsg(newTodo, tl.size());
            } else if (command.equalsIgnoreCase("deadline")) {
                if (curr.length() < 10 || !curr.contains("/by")) {
                    ui.printIncompleteDeadline();
                    continue;
                }
                Deadline newDL = Parser.parseDeadlineInput(curr);
                tl.add(newDL);
                ui.printTaskAddedMsg(newDL, tl.size());
            } else if (command.equalsIgnoreCase("event")) {
                if (msgWords.length < 2 || !curr.contains("/at")) {
                    ui.printIncompleteEvent();
                    continue;
                }
                Event newEvent = Parser.parseEventInput(curr);
                tl.add(newEvent);
                ui.printTaskAddedMsg(newEvent, tl.size());
            } else if (command.equalsIgnoreCase("clear")) {
                tl.clear();
                ui.printClearMsg();
            } else if (command.equalsIgnoreCase("find")) {
                String words = curr.substring(5).trim();
                TaskList filteredTaskList = new TaskList(tl.findMatching(words));
                ui.printFilteredList(filteredTaskList);
            } else {
                ui.printError();
            }
        }
    }
}