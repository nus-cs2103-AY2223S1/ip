# User Guide

## Start
1. Download the latest duke.jar from [here](https://github.com/angkl0/ip/releases).
2. Move the jar file to the directory you wish to use as your home directory for this application.
3. Using a terminal, head over to the directory containing the jar file and execute the command `java -jar duke.jar`.

## Commands
1. todo `DESCRIPTION`
2. deadline `DESCRIPTION` /by `TIMEDATE`
3. event `DESCRIPTION` /at `TIMEDATE`
4. list
5. mark `INDEX`
6. unmark `INDEX`
7. delete `INDEX`
7. find `KEYWORD`

## todo

Adds a todo task to the task manager.

**Usage:** 

`todo DESCRIPTION` 

`todo` is the command and `DESCRIPTION` is the description of the todo task.

**Example of usage:** 

`todo buy dinner`

**Expected outcome:**

A todo task with description `buy dinner` is added to the task list.

**Expected output:**
```
added: [T][ ] buy dinner
Now, you have X task(s) in the list.
```

## deadline

Adds a deadline task to the task manager.

**Usage:** 

`deadline DESCRIPTION /by DATETIME` 

`deadline` is the command, `DESCRIPTION` is the description of the deadline task and `DATETIME` is the deadline of the task in YYYY-MM-DD HH:MM format.

**Example of usage:** 

`deadline CS2100 lab /by 2022-09-09 10:00`

**Expected outcome:**

A deadline task with description `CS2100 lab` and deadline `(by: 10:00 am on 09/09/2022)` is added to the task list.

**Expected output:**
```
added: [D][ ] CS2100 lab (by: 10:00 am on 09/09/2022)
Now, you have X task(s) in the list.
```

## event

Adds an event task to the task manager.

**Usage:** 

`event DESCRIPTION /by DATETIME` 

`event` is the command, `DESCRIPTION` is the description of the event task and `DATETIME` is the time of the task in YYYY-MM-DD HH:MM format.

**Example of usage:** 

`event family dinner /by 2022-09-17 20:00`

**Expected outcome:**

An event task with description `family dinner` and time `(at: 08:00 pm on 17/09/2022)` is added to the task list.

**Expected output:**
```
added: [E][ ] family dinner (at: 08:00 pm on 17/09/2022)
Now, you have X task(s) in the list.
```

## list

Shows a list containing all the tasks currrently stored in the task manager.

**Usage:** 

`list` 

`list` is the command.

**Example of usage:** 

`list`

**Expected outcome:**

A list containing the tasks currently stored in the task manager and their corresponding index.

**Expected output:**
```
1. [T][ ] buy dinner
2. [D][ ] cs2100 lab (by: 10:00 am on 09/09/2022)
3. [E][ ] family dinner (at: 08:00 pm on 17/09/2022)
```

## mark

Marks a task as done.

**Usage:** 

`mark INDEX` 

`mark` is the command and `INDEX` is the index of the task to be marked which can be found using `list`.

**Example of usage:** 

`mark 3`

**Expected outcome:**

The task at index 3 is marked as done.

**Expected output:**
```
Nice! I've marked this task as done:
[E][X] family dinner (at: 08:00 pm on 17/09/2022)
```

## unmark

Unmarks a task as not done.

**Usage:** 

`unmark INDEX` 

`unmark` is the command and `INDEX` is the index of the task to be unmarked which can be found using `list`.

**Example of usage:** 

`unmark 3`

**Expected outcome:**

The task at index 3 is unmarked as undone.

**Expected output:**
```
I've unmarked this task as undone:
[E][ ] family dinner (at: 08:00 pm on 17/09/2022)
```

## delete

Deletes a task from the task manager.

**Usage:** 

`delete INDEX` 

`delete` is the command and `INDEX` is the index of the task to be delete which can be found using `list`.

**Example of usage:** 

`delete 3`

**Expected outcome:**

The task at index 3 is deleted from the task manager.

**Expected output:**
```
Noted. I've removed this task:
[E][ ] family dinner (at: 08:00 pm on 17/09/2022)
Now, you have X task(s) in the list.
```

## find

Lists all the tasks which description contain the keyword specified which is case insensitive.

**Usage:** 

`find KEYWORD` 

`find` is the command and `KEYWORD` is the search keyword.

**Example of usage:** 

`find CS2100`

**Expected outcome:**
A list with all the tasks which description contain the case insensitive keyword `CS2100`.

**Expected output:**
```
Here are the matching tasks in the list:
1. [D][ ] CS2100 lab (by: 10:00 am on 09/09/2022)
```
