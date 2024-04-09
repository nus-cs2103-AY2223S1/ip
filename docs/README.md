# User Guide
Duke is an interactive chatbot that can **help manage tasks**. It is in the form of a **desktop app**, and has a Graphical User Interface (GUI).

## Quick start
1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `duke.jar` from [here](https://github.com/hanwenlai/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Duke.

## Features 
- [Adding a task](https://hanwenlai.github.io/ip/#todo---adding-a-todo)
- [Marking a task as done](https://hanwenlai.github.io/ip/#mark---marking-a-task-as-done)
- [Deleting a task](https://hanwenlai.github.io/ip/#delete---deleting-a-task)
- [Listing all tasks](https://hanwenlai.github.io/ip/#list---listing-all-tasks)
- [Finding a task by keyword](https://hanwenlai.github.io/ip/#find---finding-a-task-by-keyword)
- [Saving the data](https://hanwenlai.github.io/ip/#saving-the-data)

## `todo` - Adding a todo

Adds a todo to the current list of tasks.

Format: `todo DESCRIPTION`

Example of usage: 
- `todo eat mala`
- `todo sleep`

## `deadline` - Adding a deadline

Adds a deadline to the current list of tasks.

Format: `deadline DESCRIPTION /by DATE`
- `DATE` must be in *yyyy-mm-dd* format, e.g. 2022-02-22

Example of usage:
- `deadline submit 2103t ip /by 2022-09-16`
- `deadline buy concert tickets /by 2022-11-22`

## `event` - Adding an event

Adds an event to the current list of tasks.

Format: `event DESCRIPTION /at TIME`
- `TIME` can be in any format.

Example of usage:
- `event attend concert /at Dec 8th 8-11pm`
- `event mid-autumn dinner /at Sat 7-10pm`

## `mark` - Marking a task as done

Marks the specified task in the task list as done.

Format: `mark INDEX`
- Marks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index ***must be a positive integer***, i.e. 1, 2, 3, ...

## `delete` - Deleting a task

Deletes the specified task from the task list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index ***must be a positive integer***, i.e. 1, 2, 3, ...

## `list` - Listing all tasks

Shows a list of all the tasks in the task list.

Format: `list`

## `find` - Finding a task by keyword

Find tasks with a description that contains the given keywords.

Format: `find KEYWORD`
- `KEYWORD` can contain one or more keywords.
- The search is case-sensitive, e.g. `eat` will not match `Eat`.
- The order of the keywords matter, e.g. `eat food` will not match `food eat`.
- Only the task description is searched.

Example of usage:
- `find assignment`
- `find bake cake`

## Saving the data

Task list is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.




