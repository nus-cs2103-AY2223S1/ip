package ui;

import java.time.LocalDate;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import task.Task;
import task.TaskList;


/**
 * <h1>Ui class</h1>
 * Layer of abstraction that handles the printing of
 * output to the user.
 */
public class Ui {
    private static final String GREETING = "Eh hello, my name is Uncle Cheong."
            + "\nWhat you want?\n";
    private static final String GOODBYE_MESSAGE = "Eh you leaving me so soon?\n";
    private static final String ERROR_MESSAGE_PREFIX = "Eh something went wrong! ";
    private static final String TASK_ADDED_MESSAGE = "Swee lah! I added this task liao:\n";
    private static final String TASK_DELETED_MESSAGE = "Okay boss, this task I delete le:\n";
    private static final String UNMARK_TASK_MESSAGE = "Eh? Not done yet? Okay I change liao: \n";
    private static final String SCHEDULE_MESSAGE_PREFIX = "Here are your tasks at ";
    private static final String EMPTY_SCHEDULE_MESSAGE = "You have no tasks scheduled on ";
    private static final String TASK_COUNT_MESSAGE_PREFIX = "Boss, you got ";
    private static final String MULTIPLE_TASK_COUNT_MESSAGE_SUFFIX = " tasks now\n";
    private static final String SINGLE_TASK_COUNT_MESSAGE_SUFFIX = " task now\n";
    private static final String NEXT_LINE_STRING = "\n";
    private static final String DOT_INDICATOR = ". ";
    private static final String MARK_TASK_MESSAGE = "Swee lah! I marked this task as done liao:\n";
    private Image uncleCheongImage = new Image(this.getClass().getResourceAsStream("/images/unclecheong.jpeg"));
    private Image errorImage = new Image(this.getClass().getResourceAsStream("/images/error.png"));

    private void appendUncleCheongResponseWithoutUserInput(String response, VBox dialogContainer) {
        dialogContainer.getChildren().add(DialogBox.getUncleCheongDialog(
               response, uncleCheongImage));
    }

    private void appendUncleCheongResponseAndUserInput(String response, VBox dialogContainer, DialogBox userDialog) {
        dialogContainer.getChildren().addAll(userDialog, DialogBox.getUncleCheongDialog(
                response, uncleCheongImage));
    }

    private void appendErrorResponseWithoutUserInput(String response, VBox dialogContainer) {
        dialogContainer.getChildren().add(DialogBox.getUncleCheongErrorDialog(
                response, errorImage));
    }

    private void appendErrorResponseAndUserInput(String response, VBox dialogContainer, DialogBox userDialog) {
        dialogContainer.getChildren().addAll(userDialog, DialogBox.getUncleCheongErrorDialog(
                response, errorImage));
    }

    /**
     * Prints the greeting text by the Chatbot to the Ui.
     *
     * @param dialogContainer VBox to add the greeting text to.
     */
    public void greet(VBox dialogContainer) {
        appendUncleCheongResponseWithoutUserInput(GREETING, dialogContainer);
    }

    /**
     * Prints the goodbye text by the Chatbot to the Ui.
     *
     * @param dialogContainer VBox to add the goodbye text to.
     * @param userDialog contains the user's dialog to be added to the VBox.
     */
    public void sayGoodbye(VBox dialogContainer, DialogBox userDialog) {
        appendUncleCheongResponseAndUserInput(GOODBYE_MESSAGE, dialogContainer, userDialog);
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
        appendErrorResponseAndUserInput(ERROR_MESSAGE_PREFIX + error, dialogContainer, userDialog);
    }

    /**
     * Prints the Error message by the Chatbot to the Ui.
     *
     * @param error the error message.
     * @param dialogContainer VBox to add the error text to.
     */
    public void sayErrorMessageWithoutUserInput(String error, VBox dialogContainer) {
        appendErrorResponseWithoutUserInput(ERROR_MESSAGE_PREFIX + error, dialogContainer);
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
            stringBuilder.append(notEmptyMessage + NEXT_LINE_STRING);
            for (int i = 0; i < numberOfTasks; i++) {
                stringBuilder.append(i + 1 + DOT_INDICATOR + tasks.taskStringAtIndex(i) + NEXT_LINE_STRING);
            }
        } else if (numberOfTasks == 0) {
            stringBuilder.append(emptyMessage + NEXT_LINE_STRING);
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
        assert(tasks.getSize() >= 1);
        if (tasks.getSize() == 1) {
            appendUncleCheongResponseWithoutUserInput(TASK_COUNT_MESSAGE_PREFIX
                    + tasks.getSize() + SINGLE_TASK_COUNT_MESSAGE_SUFFIX, dialogContainer);
        } else {
            appendUncleCheongResponseWithoutUserInput(TASK_COUNT_MESSAGE_PREFIX
                    + tasks.getSize() + MULTIPLE_TASK_COUNT_MESSAGE_SUFFIX, dialogContainer);
        }
    }

    /**
     * Prints out a message when a Task has been successfully added.
     *
     * @param task TaskList to be printed out.
     * @param dialogContainer VBox to add the success text to.
     * @param userDialog contains the user's dialog to be added to the VBox.
     */
    public void printAddedTaskMessage(Task task, VBox dialogContainer, DialogBox userDialog) {
        appendUncleCheongResponseAndUserInput(TASK_ADDED_MESSAGE
                + task + NEXT_LINE_STRING, dialogContainer, userDialog);
    }

    /**
     * Prints out a message when a Task has been successfully deleted.
     *
     * @param task TaskList to be printed out.
     * @param dialogContainer VBox to add the deletion text to.
     * @param userDialog contains the user's dialog to be added to the VBox.
     */
    public void printDeletedTaskMessage(Task task, VBox dialogContainer, DialogBox userDialog) {
        appendUncleCheongResponseAndUserInput(TASK_DELETED_MESSAGE
                + task + NEXT_LINE_STRING, dialogContainer, userDialog);
    }

    /**
     * Prints out a message when a Task has been successfully completed.
     *
     * @param task TaskList to be printed out.
     * @param dialogContainer VBox to add the marked text to.
     * @param userDialog contains the user's dialog to be added to the VBox.
     */
    public void printMarkedMessage(Task task, VBox dialogContainer, DialogBox userDialog) {
        appendUncleCheongResponseAndUserInput(MARK_TASK_MESSAGE
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
        appendUncleCheongResponseAndUserInput(UNMARK_TASK_MESSAGE
                + task, dialogContainer, userDialog);
    }

    /**
     * Prints all the Tasks and their descriptions in the list to the Ui.
     *
     * @param tasks TaskList to br printed out.
     * @param dialogContainer VBox to add the TaskList text to.
     * @param userDialog contains the user's dialog to be added to the VBox.
     * @param date the LocalDate at which the tasks are scheduled.
     */
    public void printSchedule(TaskList tasks, VBox dialogContainer, DialogBox userDialog, LocalDate date) {
        StringBuilder stringBuilder = new StringBuilder();
        int numberOfTasks = tasks.getSize();
        if (numberOfTasks > 0) {
            stringBuilder.append(SCHEDULE_MESSAGE_PREFIX + date + NEXT_LINE_STRING);
            for (int i = 0; i < numberOfTasks; i++) {
                stringBuilder.append(i + 1 + DOT_INDICATOR + tasks.taskStringAtIndex(i) + NEXT_LINE_STRING);
            }
        } else if (numberOfTasks == 0) {
            stringBuilder.append(EMPTY_SCHEDULE_MESSAGE + date + NEXT_LINE_STRING);
        }
        appendUncleCheongResponseAndUserInput(stringBuilder.toString(), dialogContainer, userDialog);
    }
}
