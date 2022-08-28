package ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import parser.Parser;
import storage.Storage;
import task.TaskList;

public class MainWindowBuilder {
    //Storage storage, Ui ui, TaskList tasks, Button sendButton, TextField userInput,
    //                      Parser parser, VBox dialogContainer
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    public static MainWindowBuilder create() {
        return new MainWindowBuilder();
    }

    public MainWindowBuilder storage(Storage storage) {
        this.storage = storage;
        return this;
    }

    public MainWindowBuilder ui(Ui ui) {
        this.ui = ui;
        return this;
    }
    public MainWindowBuilder tasks(TaskList tasks) {
        this.tasks = tasks;
        return this;
    }
    public MainWindowBuilder sendButton(Button sendButton) {
        this.sendButton = sendButton;
        return this;
    }
    public MainWindowBuilder userInput(TextField userInput) {
        this.userInput = userInput;
        return this;
    }
    public MainWindowBuilder parser(Parser parser) {
        this.parser = parser;
        return this;
    }
    public MainWindowBuilder dialogContainer(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
        return this;
    }
}
