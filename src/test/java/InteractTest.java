import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import dukepro.exceptions.DukeException;
import dukepro.expenses.Expense;
import dukepro.expenses.ExpenseCalculator;
import dukepro.handlers.Decoder;
import dukepro.handlers.Manager;
import dukepro.tasks.Task;
import dukepro.tasks.TaskFunction;
import dukepro.tasks.Todo;

public class InteractTest {
    @Test
    public void handleTodoTest() {
        Task testTask;
        try {
            testTask = Decoder.handleTasks("todo sleep");
            assertEquals(testTask.toString(), "[T][X] sleep");
        } catch (DukeException e) {
            System.out.println("failed");
        }
    }

    @Test
    public void handleDeadlineTest() {
        Task testTask;
        try {
            testTask = Decoder.handleTasks("deadline cs2103t /by 2022-10-20");
            String correctString = "[D][X] cs2103t  (by: Oct 20 2022)";
            assertEquals(testTask.toString(), correctString);
        } catch (DukeException e) {
            System.out.println("failed");
        }
    }

    //TaskManager tests
    @Test
    public void addDeleteTest() {
        Function<String, Task> decoder = str -> Decoder.parseFromFile(str);
        Manager<Task> tm = new Manager<Task>("data/test", "data/test/testTasklist", decoder, "tasks");
        tm.add(new Todo("say hello"));
        tm.add(new Task("write essay"));
        tm.delete(2);
        tm.add(new Task("whatever"));
        assertEquals(tm.numStored(), 3);
    }

    @Test
    public void checkMarkDone() {
        Function<String, Task> decoder = str -> Decoder.parseFromFile(str);
        Manager<Task> tm = new Manager<>("data/test", "data/test/testTasklist", decoder, "tasks");
        Task test = new Todo("test");
        tm.add(test);
        int taskNo = 1;
        Task doneTask = tm.operateOnList(arr -> TaskFunction.markAsDone(arr, taskNo, true));
        String correct = "[T][/] test";
        assertEquals(correct, doneTask.toString());
    }

    //ExpenseManager tests
    @Test
    public void addDeleteExpenseTest() {
        Function<String, Expense> decoder = str -> Decoder.parseFromFileExpense(str);
        Manager<Expense> expenseManager = new Manager<>("data/test", "data/test/testExLst",
                decoder, "expenses");
        Expense expense = new Expense("food", 5, LocalDate.parse("2022-10-20"));
        expenseManager.add(expense);
        String deleted = expenseManager.delete(1);
        String correct = "The following task has been deleted:\n" + "food: $5 at 2022-10-20";
        System.out.println(deleted);
        assertEquals(correct, deleted);
    }

    @Test
    public void checkTotal() {
        Function<String, Expense> decoder = str -> Decoder.parseFromFileExpense(str);
        Manager<Expense> expenseManager = new Manager<>("data/test", "data/test/testExLst",
                decoder, "expenses");
        Expense expense1 = new Expense("food", 5, LocalDate.parse("2022-10-20"));
        Expense expense2 = new Expense("dinner", 10, LocalDate.parse("2021-10-10"));
        expenseManager.add(expense1);
        expenseManager.add(expense2);
        double total = expenseManager.operateOnList(arr -> ExpenseCalculator.sumArrayList(arr));
        assertEquals(15, total);
    }

    @Test
    public void checkSpentOn() {
        Function<String, Expense> decoder = str -> Decoder.parseFromFileExpense(str);
        Manager<Expense> expenseManager = new Manager<>("data/test", "data/test/testExLst",
                decoder, "expenses");
        Expense expense1 = new Expense("food", 5, LocalDate.parse("2022-10-20"));
        Expense expense2 = new Expense("dinner", 10, LocalDate.parse("2021-10-10"));
        Expense expense3 = new Expense("lunch", 10, LocalDate.parse("2021-10-10"));
        expenseManager.add(expense1);
        expenseManager.add(expense2);
        expenseManager.add(expense3);
        double spentToday = expenseManager.operateOnList(arr -> ExpenseCalculator.spentDay(arr,
                LocalDate.parse("2021-10-10")));
        assertEquals(20, spentToday);
    }
}
