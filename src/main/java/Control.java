import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Control {

        public ArrayList<Task> dir;

        public Control() {
            this.dir = new ArrayList<>();
        }

        public void addDir(Task task) {
            this.dir.add(task);
        }

        public void deleteDir(int x) {
            Task temp = this.dir.remove(x - 1);
            System.out.println(String.format(
                    "Noted. I've removed this task:\n\t%s\nNow you have %d task%s in the list.",
                    temp, this.dir.size(), this.dir.size() == 1 ? "" : "s"));
        }
        public void eval(String cmd) throws DukeException {
            String[] temp = cmd.split(" ");
            String mainCmd = temp[0];
            String[] subCmd = Arrays.copyOfRange(temp, 1, temp.length);

            switch (mainCmd) {
                case "list" :
                    this.evalList(subCmd);
                    break;
                case "mark" :
                    this.evalMark(subCmd);
                    break;
                case "unmark" :
                    this.evalUnmark(subCmd);
                    break;
                case "todo" :
                    this.evaltodo(subCmd);
                    break;
                case "deadline" :
                    this.evalDeadline(subCmd);
                    break;
                case "event" :
                    this.evalEvent(subCmd);
                    break;
                case "delete" :
                    this.evalDelete(subCmd);
                    break;
                default:
                    this.dir.add(new Task(cmd));

                    System.out.println(String.format("added: %s\n", cmd));

            }
        }

        public void evalList(String[] subCmd) throws InvalidDescriptionException {
            if (subCmd.length != 0) {
                throw new InvalidDescriptionException();
            } else {
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < dir.size(); i++) {
                    Task currTask = dir.get(i);
                    System.out.println(String.format("    %d. %s", i + 1, currTask));
                }
            }
        }

        public void evalMark(String[] subCmd) throws InvalidDescriptionException {
            if (Integer.parseInt(subCmd[0]) <= 0 || Integer.parseInt(subCmd[0]) > this.dir.size()) {
                throw new InvalidDescriptionException();
            } else {
                this.dir.get(Integer.parseInt(subCmd[0]) - 1).mark();
            }
        }

        public void evalUnmark(String[] subCmd) throws InvalidDescriptionException {
            if (Integer.parseInt(subCmd[0]) <= 0 || Integer.parseInt(subCmd[0]) > this.dir.size()) {
                throw new InvalidDescriptionException();
            } else {
                this.dir.get(Integer.parseInt(subCmd[0]) - 1).unmark();
            }
        }

        public void evaltodo(String[] subCmd) throws EmptyDescriptionException {

            String tmp = String.join(" ", subCmd);
            if (tmp.equals("")) {
                throw new EmptyDescriptionException();
            } else {
                Todo tmpTask = new Todo(tmp);

                this.dir.add(tmpTask);
                System.out.println(String.format("Got it. I've added this task:\n\t%s\n" +
                        "Now you have %d task%s in the list.\n", tmpTask, this.dir.size(),
                        this.dir.size() == 1 ? "" : "s"));
            }
        }

    public void evalDeadline(String[] subCmd) throws DukeException {
        String tmp = String.join(" ", subCmd);
        if (tmp.equals("")) {
            throw new EmptyDescriptionException();
        } else {
            try {
                String[] tempSplit = tmp.split(" /by ");
                LocalDate tempDate = LocalDate.parse(tempSplit[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Deadline tmpTask = new Deadline(tempSplit[0], tempDate);

                this.addDir(tmpTask);
                System.out.println(String.format(
                        "Got it. I've added this task:\n\t%s\nNow you have %d task%s in the list.\n",
                        tmpTask, this.dir.size(), this.dir.size() == 1 ? "" : "s"));
            } catch (DateTimeParseException e) {
                throw new DukeException("Please change Date format to dd/mm/yyyy");
            }

        }

    }

        public void evalEvent(String[] subCmd) throws EmptyDescriptionException{
            String tmp = String.join(" ", subCmd);
            if (tmp.equals("")) {
                throw new EmptyDescriptionException();
            } else {
                String[] tempSplit = tmp.split(" /at ");
                Event tmpTask = new Event(tempSplit[0], tempSplit[1]);

                this.addDir(tmpTask);
                System.out.println(String.format(
                        "Got it. I've added this task:\n\t%s\nNow you have %d task%s in the list.\n",
                        tmpTask, this.dir.size(), this.dir.size() == 1 ? "" : "s"));
            }
        }

        public void evalDelete(String[] subCmd) throws InvalidDescriptionException {
            if (Integer.parseInt(subCmd[0]) <= 0 || Integer.parseInt(subCmd[0]) > this.dir.size()) {
                throw new InvalidDescriptionException();
            } else {
                this.deleteDir(Integer.parseInt(subCmd[0]));
            }
        }
}

