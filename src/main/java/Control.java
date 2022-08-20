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

        public void eval(String cmd) {
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
                default:
                    this.dir.add(new Task(cmd));

                    System.out.println(String.format("added: %s\n", cmd));

            }
        }

        public void evalList(String[] subCmd) {
            StringBuilder tmp = new StringBuilder();
            tmp.append("Here are the tasks in your list:\n");

            for(int i = 0; i < dir.size(); i++) {
                Task currTask = dir.get(i);
                tmp.append(String.format("%d. %s\n", i + 1, currTask));
            }
            System.out.println(tmp);
        }

        public void evalMark(String[] subCmd) {
            this.dir.get(Integer.parseInt(subCmd[1]) - 1).mark();
        }

        public void evalUnmark(String[] subCmd) {
            this.dir.get(Integer.parseInt(subCmd[1]) - 1).unmark();
        }

        public void evaltodo(String[] subCmd) {
            String tmp = String.join(" ", subCmd);
            Task tmpTask = new Task(tmp);

            this.dir.add(tmpTask);
            System.out.println(String.format("Got it. I've added this task:\n\t%s\n" +
                    "Now you have %d tasks in the list.", tmpTask, this.dir.size()));
        }
        public void evalDeadline(String[] subCmd) {
            String tmp = String.join(" ", subCmd);
            String[] tempSplit = tmp.split("/by");
            Deadline tmpTask = new Deadline(tempSplit[0], tempSplit[1]);

            this.addDir(tmpTask);
            System.out.println(String.format("Got it. I've added this task:\n\t%s\n" +
                    "Now you have %d tasks in the list.", tmpTask, this.dir.size()));

        }

        public void evalEvent(String[] subCmd) {
            String tmp = String.join(" ", subCmd);
            String[] tempSplit = tmp.split("/at");
            Event tmpTask = new Event(tempSplit[0], tempSplit[1]);

            this.addDir(tmpTask);
            System.out.println(String.format("Got it. I've added this task:\n\t%s\n" +
                    "Now you have %d tasks in the list.", tmpTask, this.dir.size()));
        }


        @Override
        public String toString() {
            StringBuilder tmp = new StringBuilder();
            tmp.append("Here are the tasks in your list:\n");

            for(int i = 0; i < dir.size(); i++) {
                Task currTask = dir.get(i);
                tmp.append(String.format("%d. %s\n", i + 1, currTask));
            }
            return tmp.toString();
        }
    }
