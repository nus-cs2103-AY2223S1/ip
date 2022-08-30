import java.time.LocalDate;

public class dateCommand extends Command{
    private LocalDate localDate;

    public dateCommand (LocalDate date) {
        this.localDate = date;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.printTasksOnSpecificDate(this.localDate);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
