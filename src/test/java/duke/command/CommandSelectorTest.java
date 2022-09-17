package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import duke.exceptions.CantDeleteDefaultException;
import duke.exceptions.CommandAlreadyExistException;
import duke.exceptions.CommandDoesNotExistException;
import duke.exceptions.DukeException;

public class CommandSelectorTest {
    @Test
    public void singletonTest() {
        CommandSelector cs = CommandSelector.getCs();
        assertEquals(cs, CommandSelector.getCs());
        CommandSelector.reset();
    }

    @Test
    public void addAliasTest1() {
        CommandSelector cs = CommandSelector.getCs();
        try {
            cs.addAlias("dummy", CommandsEnum.DELETE.commandType);
            cs.addAlias("a123", "dummy");
            cs.addAlias("a1dd", "a123");
        } catch (DukeException e) {
            fail();
        }
        assertEquals(cs.getCommand("dummy"), CommandsEnum.DELETE);
        assertEquals(cs.getCommand("a123"), CommandsEnum.DELETE);
        assertEquals(cs.getCommand("a1dd"), CommandsEnum.DELETE);
        CommandSelector.reset();
    }

    @Test
    public void addAliasTest2() {
        CommandSelector cs = CommandSelector.getCs();
        try {
            cs.addAlias("dummy", "lol");
            fail();
        } catch (CommandDoesNotExistException e) {
        } catch (DukeException e) {
            fail();
        }
        try {
            cs.addAlias("todo", "deadline");
            fail();
        } catch (CommandAlreadyExistException e) {
        } catch (DukeException e) {
            fail();
        }
        CommandSelector.reset();
    }

    @Test
    public void deleteAliasTest() {
        CommandSelector cs = CommandSelector.getCs();
        try {
            cs.addAlias("dummy", CommandsEnum.DELETE.commandType);
            cs.addAlias("a123", "dummy");
            cs.addAlias("a1dd", "a123");
        } catch (DukeException e) {
            fail();
        }
        try {
            cs.deleteAlias("dummy");
            cs.deleteAlias("a123");
            cs.deleteAlias("a1dd");
        } catch (Exception e) {
            fail();
        }

        try {
            cs.deleteAlias("asdfasdfasdf");
            fail();
        } catch (CommandDoesNotExistException e) {

        } catch (DukeException e) {
            fail();
        }

        try {
            cs.deleteAlias("deadline");
            fail();
        } catch (CantDeleteDefaultException e) {

        } catch (DukeException e) {
            fail();
        }
        CommandSelector.reset();
    }
}
