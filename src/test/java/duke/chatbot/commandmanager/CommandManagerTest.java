package duke.chatbot.commandmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.chatbot.ChatBot;
import duke.chatbot.personality.Personality;
import duke.chatbot.personality.exceptions.LoadPersonalityException;
import duke.chatbot.taskmanager.TaskManager;
import duke.chatbot.taskmanager.task.DeadlineTask;
import duke.chatbot.taskmanager.task.EventTask;
import duke.chatbot.taskmanager.task.ToDoTask;

public class CommandManagerTest {
    private final String chatBotName = "Christina";
    private final CommandManager commandManager = new CommandManager();
    private final Personality personality = new Personality(chatBotName);

    @Test
    public void byeCommandHandler() {
        String command = "bye";
        ChatBot chatBot = new ChatBot(chatBotName);
        TaskManager taskManager = new TaskManager();
        try {
            personality.loadPersonality();
        } catch (LoadPersonalityException exception) {
            Assertions.fail();
        }

        commandManager.initialize(chatBot);

        try {
            assertEquals(commandManager.getCommand(command).execute(""),
                    "Goodbye! It was nice seeing you.\nPress enter to exit!\n");
            assertFalse(chatBot.isRunning());

            commandManager.getCommand(command).execute("test");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "Sorry, I don't understand what you mean :(\n");
        }
    }

    @Test
    public void listCommandHandler() {
        String command = "list";
        ChatBot chatBot = new ChatBot(chatBotName);
        TaskManager taskManager = new TaskManager();
        try {
            personality.loadPersonality();
        } catch (LoadPersonalityException exception) {
            Assertions.fail();
        }

        commandManager.initialize(chatBot);

        try {
            assertEquals(commandManager.getCommand(command).execute(""),
                    "You have no tasks in your list.\n");
            taskManager.addTask(new ToDoTask("task1"));
            assertTrue(commandManager.getCommand(command).execute("")
                    .startsWith("I have your list of tasks displayed below:\n"));

            commandManager.getCommand(command).execute("test");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "Sorry, I don't understand what you mean :(\n");
        }
    }

    @Test
    public void todoTaskCommandHandler() {
        String command = "todo";
        ChatBot chatBot = new ChatBot(chatBotName);
        TaskManager taskManager = new TaskManager();
        try {
            personality.loadPersonality();
        } catch (LoadPersonalityException exception) {
            Assertions.fail();
        }

        commandManager.initialize(chatBot);

        try {
            assertEquals(commandManager.getCommand(command).execute("task1"),
                    "> Added: task1\n");
            assertEquals(commandManager.getCommand(command).execute(" task 2 "),
                    "> Added: task 2\n");

            commandManager.getCommand(command).execute(" ");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You cannot have an empty Task!\n");
        }
    }

    @Test
    public void deadlineTaskCommandHandler() {
        String command = "deadline";
        ChatBot chatBot = new ChatBot(chatBotName);
        TaskManager taskManager = new TaskManager();
        try {
            personality.loadPersonality();
        } catch (LoadPersonalityException exception) {
            Assertions.fail();
        }

        commandManager.initialize(chatBot);

        try {
            assertEquals(commandManager.getCommand(command).execute("task1/by01/01/2020,0000"),
                    "> Added: task1\n");
            assertEquals(commandManager.getCommand(command).execute(" task2/by01/01/2020,0000 "),
                    "> Added: task2\n");
            assertEquals(commandManager.getCommand(command).execute("task3 /by01/01/2020,0000"),
                    "> Added: task3\n");
            assertEquals(commandManager.getCommand(command).execute("task4/by 01/01/2020,0000"),
                    "> Added: task4\n");
            assertEquals(commandManager.getCommand(command).execute("/at/by01/01/2020,0000"),
                    "> Added: /at\n");

            commandManager.getCommand(command).execute(" /by01/01/2020-0000");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You cannot have an empty Task!\n");
        }

        try {
            commandManager.getCommand(command).execute("test/by ");
        } catch (Exception exception) {
            assertTrue(exception.getMessage()
                    .startsWith("You have an invalid deadline!\nDeadlines should be in the format:"));
        }

        try {
            commandManager.getCommand(command).execute(" /by ");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You cannot have an empty Task!\n");
        }

        try {
            commandManager.getCommand(command).execute("test/bytest/bytest");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You placed invalid arguments!\n");
        }

        try {
            commandManager.getCommand(command).execute("test/bytest");
        } catch (Exception exception) {
            assertTrue(exception.getMessage()
                    .startsWith("You have an invalid deadline!\nDeadlines should be in the format:"));
        }

        try {
            commandManager.getCommand(command).execute("test/by01/01/2020-0000");
        } catch (Exception exception) {
            assertTrue(exception.getMessage()
                    .startsWith("You have an invalid deadline!\nDeadlines should be in the format:"));
        }
    }

    @Test
    public void eventTaskCommandHandler() {
        String command = "event";
        ChatBot chatBot = new ChatBot(chatBotName);
        TaskManager taskManager = new TaskManager();
        try {
            personality.loadPersonality();
        } catch (LoadPersonalityException exception) {
            Assertions.fail();
        }

        commandManager.initialize(chatBot);

        try {
            assertEquals(commandManager.getCommand(command).execute("task1/at01/01/2020,0000"),
                    "> Added: task1\n");
            assertEquals(commandManager.getCommand(command).execute(" task2/at01/01/2020,0000 "),
                    "> Added: task2\n");
            assertEquals(commandManager.getCommand(command).execute("task3 /at01/01/2020,0000"),
                    "> Added: task3\n");
            assertEquals(commandManager.getCommand(command).execute("task4/at 01/01/2020,0000"),
                    "> Added: task4\n");
            assertEquals(commandManager.getCommand(command).execute("/by/at01/01/2020,0000"),
                    "> Added: /by\n");

            commandManager.getCommand(command).execute(" /at01/01/2020-0000");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You cannot have an empty Task!\n");
        }

        try {
            commandManager.getCommand(command).execute("test/at ");
        } catch (Exception exception) {
            assertTrue(exception.getMessage()
                    .startsWith("You have an invalid event time!\nEvent times should be in the format:"));
        }

        try {
            commandManager.getCommand(command).execute(" /at ");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You cannot have an empty Task!\n");
        }

        try {
            commandManager.getCommand(command).execute("test/attest/attest");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You placed invalid arguments!\n");
        }

        try {
            commandManager.getCommand(command).execute("test/attest");
        } catch (Exception exception) {
            assertTrue(exception.getMessage()
                    .startsWith("You have an invalid event time!\nEvent times should be in the format:"));
        }

        try {
            commandManager.getCommand(command).execute("test/at01/01/2020-0000");
        } catch (Exception exception) {
            assertTrue(exception.getMessage()
                    .startsWith("You have an invalid event time!\nEvent times should be in the format:"));
        }
    }

    @Test
    public void markTaskCommandHandler() {
        String command = "mark";
        ChatBot chatBot = new ChatBot(chatBotName);
        TaskManager taskManager = new TaskManager();
        try {
            personality.loadPersonality();
        } catch (LoadPersonalityException exception) {
            Assertions.fail();
        }

        commandManager.initialize(chatBot);

        try {
            commandManager.getCommand(command).execute("test");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You need to put a number after your command!\n");
        }
        try {
            commandManager.getCommand(command).execute("1 2 3");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You need to put a number after your command!\n");
        }
        try {
            commandManager.getCommand(command).execute("");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You need to put a number after your command!\n");
        }

        try {
            commandManager.getCommand(command).execute("1");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }
        try {
            commandManager.getCommand(command).execute("0");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }
        try {
            commandManager.getCommand(command).execute("-1");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }

        try {
            taskManager.addTask(new ToDoTask("task1"));
            assertEquals(commandManager.getCommand(command).execute("1"),
                    "I've marked this task as done. Good Job!\n");
            assertEquals(commandManager.getCommand(command).execute("1"),
                    "The task is already marked you dummy.\n");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }
    }

    @Test
    public void unmarkTaskCommandHandler() {
        String command = "unmark";
        ChatBot chatBot = new ChatBot(chatBotName);
        TaskManager taskManager = new TaskManager();
        try {
            personality.loadPersonality();
        } catch (LoadPersonalityException exception) {
            Assertions.fail();
        }

        commandManager.initialize(chatBot);

        try {
            commandManager.getCommand(command).execute("test");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You need to put a number after your command!\n");
        }
        try {
            commandManager.getCommand(command).execute("1 2 3");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You need to put a number after your command!\n");
        }
        try {
            commandManager.getCommand(command).execute("");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You need to put a number after your command!\n");
        }

        try {
            commandManager.getCommand(command).execute("1");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }
        try {
            commandManager.getCommand(command).execute("0");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }
        try {
            commandManager.getCommand(command).execute("-1");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }

        try {
            taskManager.addTask(new ToDoTask("task1"));
            assertEquals(commandManager.getCommand(command).execute("1"),
                    "The task is still not done you idiot.\n");
            taskManager.markTask(1);
            assertEquals(commandManager.getCommand(command).execute("1"),
                    "The task has been unmarked.\n");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }
    }

    @Test
    public void deleteTaskCommandHandler() {
        String command = "delete";
        ChatBot chatBot = new ChatBot(chatBotName);
        TaskManager taskManager = new TaskManager();
        try {
            personality.loadPersonality();
        } catch (LoadPersonalityException exception) {
            Assertions.fail();
        }

        commandManager.initialize(chatBot);

        try {
            commandManager.getCommand(command).execute("test");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You need to put a number after your command!\n");
        }
        try {
            commandManager.getCommand(command).execute("1 2 3");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You need to put a number after your command!\n");
        }
        try {
            commandManager.getCommand(command).execute("");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You need to put a number after your command!\n");
        }

        try {
            commandManager.getCommand(command).execute("1");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }
        try {
            commandManager.getCommand(command).execute("0");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }
        try {
            commandManager.getCommand(command).execute("-1");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }

        try {
            taskManager.addTask(new ToDoTask("task1"));
            assertTrue(commandManager.getCommand(command).execute("1")
                    .startsWith("The following item has been removed.\n"));
            commandManager.getCommand(command).execute("1");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }
    }

    @Test
    public void findTaskCommandHandler() {
        String command = "find";
        ChatBot chatBot = new ChatBot(chatBotName);
        TaskManager taskManager = new TaskManager();
        try {
            personality.loadPersonality();
        } catch (LoadPersonalityException exception) {
            Assertions.fail();
        }

        commandManager.initialize(chatBot);

        try {
            taskManager.addTask(new ToDoTask("task1"));
            taskManager.addTask(new ToDoTask("task2"));
            taskManager.addTask(new ToDoTask("test1"));
            taskManager.addTask(new ToDoTask("test2"));

            assertTrue(commandManager.getCommand(command).execute("task")
                    .startsWith("I have the matching tasks displayed below:\n"));
            assertTrue(commandManager.getCommand(command).execute("test")
                    .startsWith("I have the matching tasks displayed below:\n"));
            assertTrue(commandManager.getCommand(command).execute("1")
                    .startsWith("I have the matching tasks displayed below:\n"));
            assertTrue(commandManager.getCommand(command).execute("2")
                    .startsWith("I have the matching tasks displayed below:\n"));
            assertEquals(commandManager.getCommand(command).execute("dummy"),
                    "You have no tasks in your list with the keyword \"dummy\".\n");
            commandManager.getCommand(command).execute("");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "Sorry, I don't understand what you mean :(\n");
        }
    }

    @Test
    public void updateTaskCommandHandler() {
        String command = "update";
        ChatBot chatBot = new ChatBot(chatBotName);
        TaskManager taskManager = new TaskManager();
        try {
            personality.loadPersonality();
        } catch (LoadPersonalityException exception) {
            Assertions.fail();
        }

        commandManager.initialize(chatBot);

        try {
            LocalDateTime dateTime = LocalDateTime.parse("01/01/2022,0000",
                    DateTimeFormatter.ofPattern(taskManager.getDateFormat()));
            ToDoTask task1 = new ToDoTask("task1");
            DeadlineTask task2 = new DeadlineTask("task2", dateTime);
            EventTask task3 = new EventTask("task3", dateTime);

            String task1String = task1.toString();
            String task2String = task2.toString();
            String task3String = task3.toString();

            taskManager.addTask(task1);
            taskManager.addTask(task2);
            taskManager.addTask(task3);

            assertEquals(commandManager.getCommand(command).execute("1 test1"),
                    "The following item has been updated.\n1) " + task1.toString() + "\n");
            assertEquals(commandManager.getCommand(command).execute("2 test2"),
                    "The following item has been updated.\n2) " + task2.toString() + "\n");
            assertEquals(commandManager.getCommand(command).execute("3 test3"),
                    "The following item has been updated.\n3) " + task3.toString() + "\n");
            assertNotEquals(task1String, task1.toString());
            assertNotEquals(task2String, task2.toString());
            assertNotEquals(task3String, task3.toString());

            String test2String = task2.toString();
            String test3String = task3.toString();

            assertEquals(commandManager.getCommand(command).execute("2 task2 /by "),
                    "The following item has been updated.\n2) " + task2.toString() + "\n");
            assertEquals(commandManager.getCommand(command).execute("3 task3 /at "),
                    "The following item has been updated.\n3) " + task3.toString() + "\n");
            assertEquals(task2String, task2.toString());
            assertEquals(task3String, task3.toString());

            assertEquals(commandManager.getCommand(command).execute("2 /by 01/01/2022,1111"),
                    "The following item has been updated.\n2) " + task2.toString() + "\n");
            assertEquals(commandManager.getCommand(command).execute("3 /at 01/01/2022,1111"),
                    "The following item has been updated.\n3) " + task3.toString() + "\n");
            assertNotEquals(task2String, task2.toString());
            assertNotEquals(task3String, task3.toString());

            assertEquals(commandManager.getCommand(command).execute("2 test2 /by 01/01/2022,0000"),
                    "The following item has been updated.\n2) " + task2.toString() + "\n");
            assertEquals(commandManager.getCommand(command).execute("3 test3 /at 01/01/2022,0000"),
                    "The following item has been updated.\n3) " + task3.toString() + "\n");
            assertEquals(test2String, task2.toString());
            assertEquals(test3String, task3.toString());

            commandManager.getCommand(command).execute("4 test");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }

        try {
            commandManager.getCommand(command).execute("1");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You placed invalid arguments!\n");
        }
        try {
            commandManager.getCommand(command).execute("1  ");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You placed invalid arguments!\n");
        }

        try {
            commandManager.getCommand(command).execute("2 /by 01/01/2020-0000");
        } catch (Exception exception) {
            assertTrue(exception.getMessage()
                    .startsWith("You have an invalid deadline!\nDeadlines should be in the format:"));
        }
        try {
            commandManager.getCommand(command).execute("3 /at 01/01/2020-0000");
        } catch (Exception exception) {
            assertTrue(exception.getMessage()
                    .startsWith("You have an invalid event time!\nEvent times should be in the format:"));
        }

        try {
            commandManager.getCommand(command).execute("0");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }
        try {
            commandManager.getCommand(command).execute("0 test");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }
        try {
            commandManager.getCommand(command).execute("-1");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }
        try {
            commandManager.getCommand(command).execute("-1 test");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "There is no such task!!\n");
        }

        try {
            commandManager.getCommand(command).execute("test");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "You need to put a number after your command!\n");
        }

        try {
            commandManager.getCommand(command).execute("");
        } catch (Exception exception) {
            assertEquals(exception.getMessage(), "Sorry, I don't understand what you mean :(\n");
        }
    }
}
