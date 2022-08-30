package ui;

import exception.DorisException;

import tasklist.Task;
import tasklist.TaskList;

import java.util.Scanner;

public class Ui {
    private static Scanner sc;
    String logo = "                                                      \n" +
                "                                                      \n" +
                "    ,---,                                             \n" +
                "  .'  .' `\\                       ,--,                \n" +
                ",---.'     \\    ,---.    __  ,-.,--.'|                \n" +
                "|   |  .`\\  |  '   ,'\\ ,' ,'/ /||  |,      .--.--.    \n" +
                ":   : |  '  | /   /   |'  | |' |`--'_     /  /    '   \n" +
                "|   ' '  ;  :.   ; ,. :|  |   ,',' ,'|   |  :  /`./   \n" +
                "'   | ;  .  |'   | |: :'  :  /  '  | |   |  :  ;_     \n" +
                "|   | :  |  ''   | .; :|  | '   |  | :    \\  \\    `.  \n" +
                "'   : | /  ; |   :    |;  : |   '  : |__   `----.   \\ \n" +
                "|   | '` ,/   \\   \\  / |  , ;   |  | '.'| /  /`--'  / \n" +
                ";   :  .'      `----'   ---'    ;  :    ;'--'.     /  \n" +
                "|   ,.'                         |  ,   /   `--'---'   \n" +
                "'---'                            ---`-'               \n" +
                "                                                      ";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(logo);
        System.out.println("Eh what you want?");
        System.out.println("You need help just say");
    }

    public void showCommands() {
        System.out.println("Eh I can do these things la trust me I'm a woman in STEM" +
                "\n list | View all tasks you need to do la" +
                "\n todo <task> | Add something you need to do la" +
                "\n deadline <task> /by <yyyy-MM-dd hh:mm AM/PM> | Add a deadline you need to meet la" +
                "\n event <task> /at <yyyy-MM-dd hh:mm AM/PM> | Add an event you need to go for la" +
                "\n mark <index of task> | Mark the task as done la" +
                "\n unmark <index of task> | Mark the task as not done la" +
                "\n bye | Stop using the bot la");
    }

    public void showAddTodo (TaskList tasks, Task task) {
        System.out.println("Eh must remember to do this ah:");
        System.out.println(task.getDescription());
        System.out.println("You have " + TaskList.size() + " tasks leh better hurry up");
    }

    public void showAddDeadline(TaskList tasks, Task task) {
        System.out.println("Eh this one due soon stop wasting time go do now:");
        System.out.println(task.getDescription());
        System.out.println("You have " + TaskList.size() + " tasks leh better hurry up");
    }

    public void showAddEvent(TaskList tasks, Task task) {
        System.out.println("Oi remember to attend this ah:");
        System.out.println(task.getDescription());
        System.out.println("You have " + TaskList.size() + " tasks leh better hurry up");
    }

    public void showBye() {
        System.out.println("Bye you annoying sia don't want talk to you anymore");
        sc.close();
    }

    public void showError(DorisException e) {
        System.out.println(e.toString());
    }

    public void showDeleted(TaskList tasks, Task task) {
        System.out.println("Eh you don't want do this just say la:");
        System.out.println(task.getDescription());
        System.out.println("You have " + TaskList.size() + " tasks leh better hurry up");
    }

    public void showList(TaskList tasks) {
        System.out.println("Eh faster go do these:");
        tasks.list();
    }

    public void showMark(Task task) {
        System.out.println("Huh you sure you do already or not?");
        System.out.println("Okay la I trust you I trust you");
    }

    public void showUnmark(Task task) {
        System.out.println("Eh don't laze leh go do go do");
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
