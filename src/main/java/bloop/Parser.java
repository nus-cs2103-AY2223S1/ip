package bloop;

import java.io.IOException;

public class Parser {

    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public void parse(String text) throws BloopException, IOException {
        String[] textArr = text.split(" ");
        String command = textArr[0];
        if (!command.equals("list") && textArr.length == 1) {
            throw new BloopException("I don't know which task to perform " + textArr[0] + " on.");
        }
        switch (command) {
        case "list":
            tasks.listOut();
            break;
        case "unmark":
            tasks.unmark(tasks.get(Integer.parseInt(textArr[1]) - 1));
            break;
        case "mark":
            tasks.mark(tasks.get(Integer.parseInt(textArr[1]) - 1));
            break;
        case "todo":
            tasks.addTask(text, 'T');
            break;
        case "event":
            tasks.addTask(text, 'E');
            break;
        case "deadline":
            tasks.addTask(text, 'D');
            break;
        case "delete":
            Task task3 = tasks.get(Integer.parseInt(textArr[1]) - 1);
            tasks.remove(task3);
            break;
        default:
            throw new BloopException("I do not recognise your command.");
        }
    }
}
