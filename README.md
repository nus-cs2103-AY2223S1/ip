# DukePro User Guide

DukePro is a desktop app for managing trips and events, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, DukePro can get your contact management tasks done faster than traditional GUI apps.

# Quickstart
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest dukepro.jar file.
3. Copy the file to the folder you want to use as the home folder for your DukePro.
4. Create a 'src' folder with a 'dukesave' folder inside of the home folder.
5. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

# Features

Notes: Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in todo TODO, TODO is a parameter which can be used as todo Study for CS2103T.

## Overview

| Features| Description |
| ------ | ----------- |
| Add To-do Task | A To-do task can be added to your task list |
| Add Event| An Event can be added to your task list |
| Add Deadline| A Deadline can be added to your task list |
| Mark Tasks| Your tasks can be marked as completed |
| Unmark Tasks | Your marked tasks can be marked as uncompleted  |
| View All Tasks| Your tasklist will be neatly displayed to you |
| Delete Tasks | You can delete specified tasks |
| Load Tasks | Your previously saved tasks can be loaded locally from a .txt file |
| Save Tasks| Your existing task list can be saved locally to a .txt file |

## Adding To-dos: `todo TODO`
Adds the specified To-do to the task list.
Format: todo TODO
- New to-do is added to the back of the current task list.
- To-do will automatically be marked as undone.
- To-do will be tagged with `[T]`, to identify the task as a to-do.
Examples:
- todo Feed cat

## Adding Deadlines: `deadline DEADLINE /by DATE`
Adds the specified Deadline to the task list.
Format: deadline DEADLINE /by DATE
- New deadline is added to the back of the current task list.
- Deadline will automatically be marked as undone.
- Deadline will be tagged with `[D]`, to identify the task as a deadline.
- Date has to be entered in a yyyy-mm-dd format.
Example:
- deadline CS2103T TP Submission /by 2022-10-30

## Adding Events: `event EVENT /at LOCATION`
Adds the specified Event to the task list.
Format: event EVENT /at LOCATION
- New event is added to the back of the current task list.
- Event will automatically be marked as undone.
- Event will be tagged with `[E]`, to identify the task as an event.
Example:
- event Viktor's birthday party /at Fullerton Hotel

## Viewing task list: `list`
Displays the current state of the task list.
Format: list
- Tasks will be displayed in a sequential order, numbered starting from 1.
- Tasks will be shown with their tags and completion status.

## Deleting tasks: `delete INDEX`
Deletes the specified task from the task list.
Format: delete INDEX
- Deletes the task at the specified index.
- The index refers to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3, …
Example:
- delete 5

## Marking tasks as completed: `mark INDEX`
Marks the specified task from the task list as completed.
Format: mark INDEX
- Marks the task at the specified index as completed.
- The index refers to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3, ...
- If the task is already marked as completed, nothing happens.
Example:
mark 2

## Marking tasks as uncompleted: `unmark INDEX`
Marks the specified task from the task list as incomplete.
Format: unmark INDEX
- Marks the task at the specified index as incomplete.
- The index referes to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3, ...
- If the task is already marked as incomplete, nothing happens.
Example:
unmark 3

## Finding specific tasks: `find KEYWORD`
Searches the task list for the tasks with the specified keyword, and a list of matching tasks will be returned and displayed.
Format: find KEYWORD
- If no task matches the keyword provided, an empty list will be returned and display.
- Tasks that contains the keyword provided will be added to a list before being returned and displayed.
Example:
find Study

## Loading data:
Data will be read automatically from the local .txt file in the dukesave folder. If this file does not exist, a fresh task list will be created.

## Saving data:
DukePro data will automatically be saved locally onto a .txt file upon exiting the program.

## Exiting the program: `bye`
Exits the program. DukePro data will be saved locally onto a .txt file.
