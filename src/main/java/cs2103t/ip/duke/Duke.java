package cs2103t.ip.duke;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Duke {

    public static final String FOLDER_PATH = "../data";
    public static final String FILE_PATH = "../data/duke.txt";

    private Storage storage;
    private Tasklist tasks;
    private Ui ui;
    private Parser parser;


    public Duke() {
        storage = new Storage(FOLDER_PATH, FILE_PATH);
        ui = new Ui();
        parser = new Parser();
        try {
            tasks = new Tasklist(storage.loadFileData());
        } catch (DukeException e) {
            ui.showLoadingError();
            ArrayList<Task> array = new ArrayList<>();
            tasks = new Tasklist(array);
        }
    }

    public void dukeRun() throws DukeException, IOException {
        ui.showWelcome();
        String input = "";
        int index = tasks.size();
        while (!parser.isBye(input)) {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            if (parser.isBye(input)) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.getTasks().get(i);
                    String toSave = task.saveString();
                    if (i == 0) {
                        storage.writeToFile(FILE_PATH, toSave);
                    } else {
                        storage.appendToFile(FILE_PATH, toSave);
                    }
                }
                ui.showBye();
            } else if (parser.isMark(input)){
                if (input.length() > 5) {
                    int taskNum = Integer.parseInt(input.substring(5));
                    tasks.markDone(taskNum);
                    ui.showMark(tasks, taskNum);
                } else {
                    throw new DukeException("☹ OOPS!!! Please include the index of the task you'd like to mark as completed!");
                }
            } else if (parser.isUnmark(input)){
                if (input.length() > 7) {
                    int taskNum = Integer.parseInt(input.substring(7));
                    tasks.markUndone(taskNum);
                    ui.showUnmark(tasks, taskNum);
                } else {
                    throw new DukeException("☹ OOPS!!! Please include the index of the task you'd like to mark as incomplete!");
                }
            } else if (parser.isList(input)) {
                ui.showList(tasks, tasks.size());
            } else if (parser.isTodo(input)){
                if (input.length() > 5) {
                    Todo todo = new Todo(input.substring(5));
                    tasks.addTasks(todo);
                    index++;
                    ui.showTodo(todo, index);
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (parser.isDeadline(input)){
                if (input.length() > 9) {
                    String[] dead = input.split(" /by ");
                    Deadlines deadlines = new Deadlines(dead[0].substring(9), LocalDate.parse(dead[1]));
                    tasks.addTasks(deadlines);
                    index++;
                    ui.showDeadline(deadlines, index);
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (parser.isEvent(input)){
                if (input.length() > 6) {
                    String[] time = input.split(" /at ");
                    Event event = new Event(time[0].substring(6), LocalDate.parse(time[1]));
                    tasks.addTasks(event);
                    index++;
                    ui.showEvent(event, index);
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
            } else if (parser.isDelete(input)) {
                if (input.length() > 7) {
                    int taskNum = Integer.parseInt(input.substring(7));
                    Task toDelete = tasks.getTasks().get(taskNum - 1);
                    tasks.deleteTasks(taskNum - 1);
                    index--;
                    ui.showDelete(toDelete, index);
                } else {
                    throw new DukeException("☹ OOPS!!! Please include the index of the task you'd like to delete!");
                }
            } else if (parser.isFind(input)) {
                if (input.length() > 5) {
                    ArrayList<Task> arr = new ArrayList<>();
                    Tasklist filteredTasks = new Tasklist(arr);
                    for (Task t : tasks.getTasks()) {
                        if (t.toString().contains(input.substring(5))) {
                            filteredTasks.addTasks(t);
                        }
                    }
                    ui.showFilteredList(filteredTasks, filteredTasks.size());
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            duke.dukeRun();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}