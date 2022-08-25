interface Command {

    void execute(TaskList tasks, Ui ui, Storage storage);
    boolean isExit();

}
