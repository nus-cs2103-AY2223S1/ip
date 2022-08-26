import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Parser {

    protected TaskList taskList;

    protected ArrayList<Task> listOfActions;

    protected Boolean isAction = false;

    private Boolean isErreneous = true;

    private String type = "error";

    public Parser(TaskList list, String str) {
        String[] input = str.split(" ");
        this.taskList = list;
        this.listOfActions = this.taskList.taskArray;
        if (input.length == 1) {
            if (input[0].equals("bye")) {
                this.type = "bye";
                bye(listOfActions, Path.of("data/duke.txt").toFile());
                this.isErreneous = false;
            } else if (input[0].equals("list")) {
                this.type = "list";
                this.isErreneous = false;
            }
        } else if (input[0].equals("todo")) {
            if (input.length > 1) {
                this.type = "todo";
                isAction = true;
                list.add(new Todo(str.substring(5)));
                this.isErreneous = false;
            }
        } else if (input[0].equals("deadline")) {
            if (input.length > 5) {
                int pos = str.indexOf("/by") + 1;
                this.type = "deadline";
                isAction = true;
                list.add(new Deadline(str.substring(9, pos - 1), str.substring(pos + 3)));
                this.isErreneous = false;
            }
        } else if (input[0].equals("event")) {
            if (input.length > 4) {
                int pos = str.indexOf("/") + 1;
                this.type = "event";
                isAction = true;
                list.add(new Event(str.substring(6, pos - 1), str.substring(pos + 3)));
                this.isErreneous = false;
            }

        } else if (input[0].equals("mark")) {
            if (input.length == 2) {
                this.type = "mark";
                list.taskArray.get(Integer.parseInt(input[1]) - 1).mark();
                System.out.println("----------------------\n" + "Congrats on completing :)\n" +
                        listOfActions.get(Integer.parseInt(input[1]) - 1) + "\n----------------------\n");
                this.isErreneous = false;
            }
        } else if (input[0].equals("unmark")) {
            if (input.length == 2) {
                this.type = "unmark";
                list.taskArray.get(Integer.parseInt(input[1]) - 1).unMark();
                System.out.println("----------------------\n" + "One more mission ;)\n" +
                        listOfActions.get(Integer.parseInt(input[1]) - 1) + "\n----------------------\n");
                this.isErreneous = false;
            }

        } else if (input[0].equals("delete")) {
            if (input.length == 2) {
                int pos = Integer.parseInt(input[1]) - 1;
                this.type = "delete";
                list.delete(pos);
                this.isErreneous = false;
            }
        }


        if (this.type.equals("error")) {
            this.isErreneous = true;
        }
    }

    public String getType() {
        return this.type;
    }

    public Boolean isErreneous() {
        return this.isErreneous;
    }

    public Boolean getIsAction() {
        return this.isAction;
    }

    public void bye(ArrayList<Task> listOfActions, File file) {
        try {
            FileWriter writer = new FileWriter(file.getPath());
            for (Task t : listOfActions) {
                writer.write(t.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Oops");
        }
    }



}


