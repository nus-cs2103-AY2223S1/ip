package duke;


import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }


    public void readInput() throws DukeException, IOException {
        while (true) {

            Scanner input = new Scanner(System.in);
            String s = input.nextLine();

            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon.");
                break;
            }

            String[] word = s.split(" ");

            if (s.equals("list")) {
                taskList.list();


            } else if (word[0].equals("mark")) {
                Integer num = Integer.parseInt(word[1]);
                taskList.mark(num);


            } else if (word[0].equals("unmark")) {
                Integer num = Integer.parseInt(word[1]);
                taskList.unmark(num);


            } else if (word[0].equals("todo")) {
                if (s.endsWith("todo")) {
                    throw new DukeException("Ooops, the description of todo cannot be empty!");
                }

                String substringtd = s.replaceAll("todo ", "");
                taskList.todo(substringtd);


            } else if (word[0].equals("deadline")) {
                if (s.endsWith("deadline")) {
                    throw new DukeException("Ooops, the description of deadline cannot be empty!");
                }

                String[] phrase = s.split("/by");
                String substringdl1 = phrase[0].replaceAll("deadline", "");
                String substringdl2 = phrase[1];
                taskList.deadline(substringdl1, substringdl2);


            } else if (word[0].equals("event")) {
                if (s.endsWith("event")) {
                    throw new DukeException("Ooops, the description of event cannot be empty!");
                }

                String[] phrase = s.split("/at");
                String substringdl1 = phrase[0].replaceAll("event", "");
                String substringdl2 = phrase[1];
                taskList.event(substringdl1, substringdl2);

            } else if (word[0].equals("delete")) {
                Integer num = Integer.parseInt(word[1]);
                taskList.delete(num);

            } else if (word[0].equals("find")) {
                String content = s.replace("find", "");
                taskList.find(content);

            } else {
                throw new DukeException("Oooops, sorry I don't know what you are talking about :(");
            }

        }
    }
}
