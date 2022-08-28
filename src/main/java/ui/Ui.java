package ui;

import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import task.Task;
import task.TaskList;

/**
 * <h1>Ui class</h1>
 * Layer of abstraction that handles the printing of
 * output to the user.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);
    private Image uncleCheong = new Image(this.getClass().getResourceAsStream("/images/unclecheong.jpeg"));
    private Insets leftRightPadding = new Insets(0, 10, 0, 10);

    private void appendUncleCheongResponseWithoutUserInput(String response, VBox dialogContainer) {
        dialogContainer.getChildren().add(DialogBox.getUncleCheongDialog(
                getDialogLabelWithPadding(response, leftRightPadding), new ImageView(uncleCheong)));
    }

    private void appendUncleCheongResponseAndUserInput(String response, VBox dialogContainer, DialogBox userDialog) {
        dialogContainer.getChildren().addAll(userDialog, DialogBox.getUncleCheongDialog(
                getDialogLabelWithPadding(response, leftRightPadding), new ImageView(uncleCheong)));
    }

    private Label getDialogLabelWithPadding(String text, Insets insets) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        textToAdd.setPadding(insets);
        return textToAdd;
    }

    /**
     * Prints the greeting text by the Chatbot to the Ui.
     *
     * @param dialogContainer VBox to add the greeting text to.
     */
    public void greet(VBox dialogContainer) {
        appendUncleCheongResponseWithoutUserInput("Eh hello, my name is Uncle Cheong. \n"
                + "What you want?\n", dialogContainer);
    }

    /**
     * Prints the goodbye text by the Chatbot to the Ui.
     *
     * @param dialogContainer VBox to add the goodbye text to.
     */
    public void sayGoodbye(VBox dialogContainer, DialogBox userDialog) {
        appendUncleCheongResponseAndUserInput("Eh you leaving me so soon?\n", dialogContainer, userDialog);
    }

    /**
     * Prints the Error message by the Chatbot to the Ui
     * with the user input.
     *
     * @param error the error message.
     * @param dialogContainer VBox to add the error text to.
     * @param userDialog contains the user's dialog to be added to the VBox.
     */
    public void sayErrorMessageWithUserInput(String error, VBox dialogContainer, DialogBox userDialog) {
        appendUncleCheongResponseAndUserInput("Eh something went wrong! " + error, dialogContainer, userDialog);
    }

    /**
     * Prints the Error message by the Chatbot to the Ui.
     *
     * @param error the error message.
     * @param dialogContainer VBox to add the error text to.
     */
    public void sayErrorMessageWithoutUserInput(String error, VBox dialogContainer) {
        dialogContainer.getChildren().add(DialogBox.getUncleCheongDialog(getDialogLabelWithPadding(
                "Eh something went wrong! " + error, leftRightPadding), new ImageView(uncleCheong)));
    }

    /**
     * Prints all the Tasks and their descriptions in the list to the Ui.
     *
     * @param tasks TaskList to br printed out.
     * @param notEmptyMessage message to be printed out if there are
     *                        Tasks in the list.
     * @param emptyMessage message to be printed out if there are no
     *                     Tasks in the list.
     * @param dialogContainer VBox to add the TaskList text to.
     * @param userDialog contains the user's dialog to be added to the VBox.
     */
    public void printTasks(TaskList tasks, String notEmptyMessage, String emptyMessage,
                           VBox dialogContainer, DialogBox userDialog) {
        StringBuilder stringBuilder = new StringBuilder();
        int numberOfTasks = tasks.getSize();
        if (numberOfTasks > 0) {
            stringBuilder.append(notEmptyMessage + "\n");
            for (int i = 0; i < numberOfTasks; i++) {
                stringBuilder.append(i + 1 + ". " + tasks.taskStringAtIndex(i) + "\n");
            }
        } else if (numberOfTasks == 0) {
            stringBuilder.append(emptyMessage + "\n");
        }
        appendUncleCheongResponseAndUserInput(stringBuilder.toString(), dialogContainer, userDialog);
    }

    /**
     * Prints out the number of Tasks in the TaskList.
     *
     * @param tasks TaskList to be printed out.
     * @param dialogContainer VBox to add the number of tasks text to.
     */
    public void printTaskCountMessage(TaskList tasks, VBox dialogContainer) {
        appendUncleCheongResponseWithoutUserInput("Boss, you got "
                + tasks.getSize() + " tasks now\n", dialogContainer);
    }

    /**
     * Prints out a message when a Task has been successfully added.
     *
     * @param task TaskList to be printed out.
     * @param dialogContainer VBox to add the success text to.
     * @param userDialog contains the user's dialog to be added to the VBox.
     */
    public void printAddedTaskMessage(Task task, VBox dialogContainer, DialogBox userDialog) {
        appendUncleCheongResponseAndUserInput("Swee lah! I added this task liao:\n"
                + task + "\n", dialogContainer, userDialog);
    }

    /**
     * Prints out a message when a Task has been successfully deleted.
     *
     * @param task TaskList to be printed out.
     * @param dialogContainer VBox to add the deletion text to.
     * @param userDialog contains the user's dialog to be added to the VBox.
     */
    public void printDeletedTaskMessage(Task task, VBox dialogContainer, DialogBox userDialog) {
        appendUncleCheongResponseAndUserInput("Okay boss, this task I delete le:\n"
                + task + "\n", dialogContainer, userDialog);
    }

    /**
     * Prints out a message when a Task has been successfully completed.
     *
     * @param task TaskList to be printed out.
     * @param dialogContainer VBox to add the marked text to.
     * @param userDialog contains the user's dialog to be added to the VBox.
     */
    public void printMarkedMessage(Task task, VBox dialogContainer, DialogBox userDialog) {
        appendUncleCheongResponseAndUserInput("Swee lah! Your task done liao: \n"
                + task, dialogContainer, userDialog);
    }

    /**
     * Prints out a message when a Task has been unmarked.
     *
     * @param task TaskList to br printed out.
     * @param dialogContainer VBox to add the unmarked text to.
     * @param userDialog contains the user's dialog to be added to the VBox.
     */
    public void printUnmarkedMessage(Task task, VBox dialogContainer, DialogBox userDialog) {
        appendUncleCheongResponseAndUserInput("Eh? Not done yet? Okay I change liao: \n"
                + task, dialogContainer, userDialog);
    }
}
