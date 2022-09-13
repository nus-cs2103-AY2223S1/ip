# User Guide 

Duke is a **portable desktop app for managing tasks**<br>

![Ui](Ui.png)

--------------------------------------------------------------------------------------------------------------------
## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `Duke.jar` from [here].

3. Copy the file to the folder you want to use as the _home folder_ for your Duke ChatBot.

### For Windows
1. Double-click `Duke.jar` to start the app. The GUI similar to the above should appear in a few seconds.

### For Mac
1. Open the `terminal`
2. Navigate to the folder where `Duke.jar` is saved.
3. Input the following command into the `terminal` and hit `Enter`
   `java -jar Duke.jar`
--------------------------------------------------------------------------------------------------------------------

## Features

### Types of Tasks
Duke supports up to 3 types of task!<br>

`ToDo`: Tasks without time period.

`Deadline`: Tasks with a due date.

`Event`: Tasks that have yet to begin.

### Batch Delete

So what makes duke different from the rest?<br>
It allows you to delete multiple items in one go!

`BatchTypeDelete`: Deletes all tasks of specified type.

`BatchDescDelete`: Deletes all tasks containing certain description.

--------------------------------------------------------------------------------------------------------------------
## Usage

--------------------------------------------------------------------------------------------------------------------
### `ToDo` - Adds a Todo

Adds a ToDo with a description provided by the user.<br>

Format:`ToDo DESCRIPTION`
* `DESCRIPTION`: Description of ToDo.

Example of usage: 

`ToDo Complete README.md`

Expected outcome:<br>
* ToDo with description `Complete README.md`

Description of the outcome.

```
Got it. I've added this task:
[T][ ] Complete README.md
Now you have 1 tasks in the list.
```
--------------------------------------------------------------------------------------------------------------------
### `Deadline` - Adds a Deadline

Adds a Deadline with a description and time provided by the user.<br>

Format:`Deadline DESCRIPTION /by YYYY-MM-DD hhmm`
* `DESCRIPTION`: Description of Deadline.
* `YYYY-MM-DD`: Format of Date input (e.g. 2022-09-12)
* `hhmm`: Format of Time input (e.g. 2359)
* Note: 
  * Date and Time must be present
  * Use `/by` to separate `Description` from `Date` and `Time`

Example of usage:

`Deadline CS2103T IP Final Version /by 2022-09-16 2359`

Expected outcome:<br>
* Deadline with description `CS2103T IP Final Version`<br>
and due date `2022 Sep 16 11:59PM`

Description of the outcome.

```
Got it. I've added this task:
[D][ ] CS2103T IP Final Version (by: 2022 Sep 16 11:59PM)
Now you have 1 tasks in the list.
```
--------------------------------------------------------------------------------------------------------------------
### `Event` - Adds an Event

Adds an Event with a description and time provided by the user.<br>

Format:`Deadline DESCRIPTION /by YYYY-MM-DD hhmm`
* `DESCRIPTION`: Description of Event.
* `YYYY-MM-DD`: Format of Date input (e.g. 2022-09-12)
* `hhmm`: Format of Time input (e.g. 2359)
* Note:
    * Date and Time must be present
    * Use `/at` to separate `Description` from `Date` and `Time`

Example of usage:

`Event Lunch with friends /at 2022-09-14 1245`

Expected outcome:<br>
* Event with description `Lunch with friends`<br>
  and event date `2022 Sep 14 12:45PM`

Description of the outcome.

```
Got it. I've added this task:
[E][ ] Lunch with friends (at: 2022 Sep 14 12:45PM)
Now you have 1 tasks in the list.
```
--------------------------------------------------------------------------------------------------------------------
### `List` - List all tasks

Lists all tasks stored in Duke's TaskList.<br>

Format:`List`

Sample Input:
```
ToDo Complete README.md
Deadline CS2103T IP Final Version /by 2022-09-16 2359
Event Lunch with friends /at 2022-09-14 1245
```

Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] Complete README.md
2. [D][ ] CS2103T IP Final Version (by: 2022 Sep 16 11:59PM)
3. [E][ ] Lunch with friends (at: 2022 Sep 14 12:45PM)
```
--------------------------------------------------------------------------------------------------------------------
### `Mark` - Marks task as complete

Marks a task as complete.

Format:`Mark INDEX`
* `INDEX`: The index of the task as provided using the `list` command

Sample TaskList:
```
1. [T][ ] Complete README.md
2. [D][ ] CS2103T IP Final Version (by: 2022 Sep 16 11:59PM)
3. [E][ ] Lunch with friends (at: 2022 Sep 14 12:45PM)
```

Example of usage:

`Mark 2`

Expected outcome:
```
Nice! I've marked this task as done:
[D][X] CS2103T IP Final Version (by: 2022 Sep 16 11:59PM)
```
--------------------------------------------------------------------------------------------------------------------
### `Unmark` - Marks task as incomplete

Unmarks a task as complete.

Format:`Unmark INDEX`
* `INDEX`: The index of the task as provided using the `list` command

Sample TaskList:
```
1. [T][ ] Complete README.md
2. [D][X] CS2103T IP Final Version (by: 2022 Sep 16 11:59PM)
3. [E][ ] Lunch with friends (at: 2022 Sep 14 12:45PM)
```

Example of usage:

`Unmark 2`

Expected outcome:
```
Ok! I've marked this task as not done yet:
[D][ ] CS2103T IP Final Version (by: 2022 Sep 16 11:59PM)
```
--------------------------------------------------------------------------------------------------------------------
### `Find` - Find tasks using keyword

Finds the list of tasks with description contain the `KEYWORD` provided.

Note: 
* `Find` searches matching description via contiguous substring.
* `KEYWORD` is case-sensitive.

Format:`Find KEYWORD`
* `KEYWORD`: The keyword to be matched in descriptions.

Sample TaskList:
```
1. [T][ ] Complete README.md
2. [D][ ] CS2103T IP Final Version (by: 2022 Sep 16 11:59PM)
3. [E][ ] Lunch with friends (at: 2022 Sep 14 12:45PM)
4. [T][ ] CS2103T TP Stuff
```

Example of usage:

`Find CS2103T`

Expected outcome:
```
Here are the matching tasks in your list:
1. [D][ ] CS2103T IP Final Version (by: 2022 Sep 16 11:59PM)
2. [T][ ] CS2103T TP Stuff
```
--------------------------------------------------------------------------------------------------------------------
### `Delete` - Delete task

Delete a task stored in Duke's TaskList.

Format:`Delete INDEX`
* `INDEX`: The index of the task as provided using the `list` command

Sample TaskList:
```
1. [T][ ] Complete README.md
2. [D][ ] CS2103T IP Final Version (by: 2022 Sep 16 11:59PM)
3. [E][ ] Lunch with friends (at: 2022 Sep 14 12:45PM)
4. [T][ ] CS2103T TP Stuff
```

Example of usage:

`Delete 4`

Expected outcome:
```
Noted. I've removed this task:
[T][ ] CS2103T TP Stuff
Now you have 3 tasks in the list.
```
--------------------------------------------------------------------------------------------------------------------
### `BatchTypeDelete` - Deletes all tasks of specified type

Deletes all tasks of specified type stored in Duke's TaskList.

Format:`BatchTypeDelete TYPE`
* `TYPE`: The type of task (`ToDo`, `Event`, `Deadline`)

Sample TaskList:
```
1. [T][ ] Complete README.md
2. [D][ ] CS2103T IP Final Version (by: 2022 Sep 16 11:59PM)
3. [E][ ] Lunch with friends (at: 2022 Sep 14 12:45PM)
4. [T][ ] CS2103T TP Stuff
```

Example of usage:

`BatchTypeDelete ToDo`

Expected outcome:
```
Here are the tasks in your list:
1. [D][ ] CS2103T IP Final Version (by: 2022 Sep 16 11:59PM)
2. [E][ ] Lunch with friends (at: 2022 Sep 14 12:45PM)

Now you have 2 tasks in the list.
```
--------------------------------------------------------------------------------------------------------------------
### `BatchDescDelete` - Deletes all tasks with matching keyword
Deletes all tasks in Duke's TaskList with description contain the `KEYWORD` provided.

Note:
* `BatchDescDelete` searches matching description via contiguous substring.
* `KEYWORD` is case-sensitive.

Format:`Find KEYWORD`
* `KEYWORD`: The keyword to be matched in descriptions.

Sample TaskList:
```
1. [T][ ] Complete README.md
2. [D][ ] CS2103T IP Final Version (by: 2022 Sep 16 11:59PM)
3. [E][ ] Lunch with friends (at: 2022 Sep 14 12:45PM)
4. [T][ ] CS2103T TP Stuff
```

Example of usage:

`BatchDescDelete CS2103T`

Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] Complete README.md
3. [E][ ] Lunch with friends (at: 2022 Sep 14 12:45PM)

Now you have 2 tasks in the list.
```
--------------------------------------------------------------------------------------------------------------------
### `Help` - Returns a list of available commands

Lists all the commands supported by `Duke`.<br>

Format:`Help`

--------------------------------------------------------------------------------------------------------------------
### `Bye` - Exit the program

Exits Duke.<br>

Format: `Bye`

