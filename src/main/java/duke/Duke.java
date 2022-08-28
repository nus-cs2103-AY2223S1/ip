package duke;

import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage st = new Storage("./data/dukedata.txt");
        TaskList tl = new TaskList(st.setFile());
        Ui ui = new Ui();

        ui.printIntro();

        while (true) {
            String curr = sc.nextLine();
            String[] msgWords = curr.split("\\b");
            for (int i = 0; i < msgWords.length; i++) {
                if (msgWords[i].equalsIgnoreCase("bye")) {
                    ui.goodByeMessage();
                    return;
                } else if (msgWords[i].equalsIgnoreCase("list")) {
                    ui.printList(tl);
                    break;
                } else if (msgWords[i].equalsIgnoreCase("mark")) {
                    int index = Integer.parseInt(msgWords[2]) - 1;
                    if (index < 1 || index > tl.size()) {
                        ui.printOutOfBoundsMsg();
                        break;
                    }
                    tl.mark(index);
                    ui.printMarkedMsg(tl.get(index));
                    break;
                } else if (msgWords[i].equalsIgnoreCase("unmark")) {
                    int index = Integer.parseInt(msgWords[2]) - 1;
                    if (index < 1 || index > tl.size()) {
                        ui.printOutOfBoundsMsg();
                        break;
                    }
                    tl.unMark(index);
                    ui.printUnmarkedMsg(tl.get(index));
                    break;
                } else if (msgWords[i].equalsIgnoreCase("delete")) {
                    int index = Integer.parseInt(msgWords[2]) - 1;
                    Task removed = tl.get(index);
                    tl.remove(index);
                    ui.printDeleteMsg(removed.toString(), tl.size());
                    break;
                } else if (msgWords[i].equalsIgnoreCase("todo")) {
                    if (curr.length() < 6) {
                        ui.printNoTaskInputMsg();
                        break;
                    }
                    String item = curr.substring(4);
                    Todo newTodo = new Todo(item);
                    tl.add(newTodo);
                    ui.printTaskAddedMsg(newTodo, tl.size());
                    break;
                } else if (msgWords[i].equalsIgnoreCase("deadline")) {
                    if (curr.length() < 10 || !curr.contains("/by")) {
                        System.out.println("Why not complete deadline?");
                        break;
                    }
                    int slashPos = curr.indexOf("/by");
                    String taskName = curr.substring(8, slashPos - 1) + " ";
                    String deadline = curr.substring(slashPos + 3);
                    Deadline newDL = new Deadline(taskName, deadline);
                    tl.add(newDL);
                    ui.printTaskAddedMsg(newDL, tl.size());
                    break;
                } else if (msgWords[i].equalsIgnoreCase("event")) {
                    if (curr.length() < 7 || !curr.contains("/at")) {
                        System.out.println("Why not complete event?");
                        break;
                    }
                    int slashPos = curr.indexOf("/at");
                    String taskName = curr.substring(5, slashPos - 1) + " ";
                    String deadline = curr.substring(slashPos + 3);
                    Event newEvent = new Event(taskName, deadline);
                    tl.add(newEvent);
                    ui.printTaskAddedMsg(newEvent, tl.size());
                    break;
                } else if (msgWords[i].equalsIgnoreCase("clear")) {
                    tl.clear();
                    ui.printClearMsg();
                    break;
                } else if (msgWords[i].equalsIgnoreCase("find")) {
                    String words = curr.substring(5).trim();
                    TaskList filteredTaskList = new TaskList(tl.findMatching(words));
                    ui.printFilteredList(filteredTaskList);
                    break;
                } else {
                    ui.printError();
                    break;
                }
            }
        }
    }
}