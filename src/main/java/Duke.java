import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private boolean end;
    private ArrayList<Task> l;
    private enum Inputs {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        ELSE
    }

    public Duke() {
        this.end = false;
        this.l = new ArrayList<>();
    }

    private void run() {
        try {
            loadFile();
        } catch (IOException | DukeException e) {
            printException(e);
        }

        Scanner sc = new Scanner(System.in);
        intro();
        while (!end) {
            try {
                String line = sc.nextLine();
                output(line);
                writeFile();
            } catch (IOException | DukeException e) {
                printException(e);
            }
        }
        sc.close();
        exit();
    }

    private void output(String s) throws DukeException {
        switch (getInput(s)) {
        case BYE:
            this.end = true;
            break;
        case LIST:
            printList();
            break;
        case MARK:
            int markNum = Integer.parseInt(s.replace("mark ", ""));
            mark(markNum);
            break;
        case UNMARK:
            int unMarkNum = Integer.parseInt(s.replace("unmark ", ""));
            unMark(unMarkNum);
            break;
        case TODO:
            String tDes = s.replace("todo", "");
            addList(new Todo(tDes));
            break;
        case EVENT:
            String[] eDes = s.replace("event", "").split(" /at ");
            addList(new Event(eDes[0], eDes[1]));
            break;
        case DEADLINE:
            String[] dDes = s.replace("deadline", "").split(" /by ");
            addList(new Deadline(dDes[0], dDes[1]));
            break;
        case DELETE:
            int delNum = Integer.parseInt(s.replace("delete ", ""));
            delete(delNum);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    private void print(String s) {
        System.out.println("\t " + s);
    }

    private void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        print("Hello! I'm Duke");
        print("What can I do for you?");
        printLine();
    }

    private void exit() {
        printLine();
        print("Bye. Hope to see you again soon!");
        printLine();
    }

    private void printList() {
        printLine();
        print("Here are the tasks in your list:");
        for (int i = 0; i < l.size(); i++) {
            print(String.format("%d. %s", i + 1, this.l.get(i)));
        }
        printLine();
    }

    private void addList(Task t) {
        this.l.add(t);
        printLine();
        print("Got it. I've added this task:");
        print("  " + t.toString());
        print("Now you have " + l.size() + " tasks in the list.");
        printLine();
    }

    private void mark(int i) {
        Task t = this.l.get(i - 1);
        t.markDone();
        printLine();
        print("Nice! I've marked this task as done:");
        print("  " + t.toString());
        printLine();
    }

    private void unMark(int i) {
        Task t = this.l.get(i - 1);
        t.unmarkDone();
        printLine();
        print("OK, I've marked this task as not done yet:");
        print("  " + t.toString());
        printLine();
    }

    private void printException(Exception e) {
        printLine();
        print(e.getMessage());
        printLine();
    }

    private void delete(int i) {
        Task t = this.l.get(i - 1);
        this.l.remove(i - 1);
        printLine();
        print("Noted. I've removed this task:");
        print("  " + t.toString());
        print("Now you have " + l.size() + " tasks in the list.");
        printLine();
    }

    private Inputs getInput(String s) {
        if (s.equals("bye")) {
            return Inputs.BYE;
        } else if (s.equals("list")) {
            return Inputs.LIST;
        } else if (s.startsWith("mark")) {
            return Inputs.MARK;
        } else if (s.startsWith("unmark")) {
            return Inputs.UNMARK;
        } else if (s.startsWith("todo")) {
            return Inputs.TODO;
        } else if (s.startsWith("deadline")) {
            return Inputs.DEADLINE;
        } else if (s.startsWith("event")) {
            return Inputs.EVENT;
        } else if (s.startsWith("delete")) {
            return Inputs.DELETE;
        } else {
            return Inputs.ELSE;
        }
    }

    private void loadFile() throws IOException, DukeException {
        File directory = new File("data/");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File("data/duke.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String curr = sc.nextLine();
            String[] info = curr.split("\\|");
            switch (info[0]) {
            case "T":
                Task addTodo = new Todo(info[2], (info[1].equals("1")));
                l.add(addTodo);
                break;
            case "E":
                Task addEvent = new Event(info[2], info[3], info[1].equals("1"));
                l.add(addEvent);
                break;
            case "D":
                Task addDeadline = new Deadline(info[2], info[3], info[1].equals("1"));
                l.add(addDeadline);
                break;
            default:
                throw new IOException("Error in reading file");
            }
        }
    }

    private void writeFile() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        StringBuilder sb = new StringBuilder();
        for (Task t : l) {
            if (t instanceof Todo) {
                sb.append("T")
                        .append("|")
                        .append(t.isDone ? "1" : "0")
                        .append("|")
                        .append(t.description)
                        .append(System.lineSeparator());
            } else if (t instanceof Deadline) {
                sb.append("D")
                        .append("|")
                        .append(t.isDone ? "1" : "0")
                        .append("|")
                        .append(t.description)
                        .append("|")
                        .append(((Deadline) t).by)
                        .append(System.lineSeparator());
            } else {
                sb.append("E")
                        .append("|")
                        .append(t.isDone ? "1" : "0")
                        .append("|")
                        .append(t.description)
                        .append("|")
                        .append(((Event) t).at)
                        .append(System.lineSeparator());
            }
        }
        fw.write(sb.toString());
        fw.close();
    }


    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}