# User Guide

Duke is a desktop app for managing task optimized for both GUI and CLI usage! Packaged like a chat bot, Duke will provide you with a personalised service to help you track and manage your tasks.

- Quick Start
- Features
  - Additional Launch flags
    - Launching in GUI Mode
    - Launching in CLI Mode
    - Specifying Save file
  - Creating Tasks
    - Todo
    - Deadline
    - Event
  - List All Task
  - Marking Tasks
    - Mark
    - Unmark
  - Searching for Tasks
    - By keywords
    - By time

------

# Quick Start

1. Ensure you have Java `11` or above installed on your Computer.

2. Download the latest `duke.jar` from here.

3. You can now choose to keep the jar file in a specified folder you wish to use for your Task tracker OR specify a location for your save data

4. Double click the file to start the app. (Or launch it via a terminal).

   <img src="./img/launch.png" style="zoom: 67%;" />

   5. Type the command in the command box for the GUI or via the terminal and click send on GUI or enter on CLI to execute them!

      Here are some of the basic commands that you can try:

      - `list`: List all added tasks
      - `todo <description>`: Create a `todo` task with the description
      - `deadline <description> /by <time/detail>`: create a deadline task with the description
      - `bye`: to exit Duke

   6. Refer to features below for all the commands and their usage.

# Features 

## Additional Launch flags

Need speed? Launch Duke in CLI mode and enjoy blazing smooth user experience with CLI or enjoy a clean flat look with the GUI.

<img src="./img/launchflag.png" style="zoom: 50%;" />

Launch Duke using `java -jar duke.jar` as you can pass in the addition flag displayed above.

### Launching in GUI mode 

- `-g` or `--gui` Launches the app in GUI mode

### Launching in CLI mode 

- `-ng` or `--no-gui` Launches the app in CLI mode

### Specifying save file 

- `-s` or `--use-save` allows you to specify/create a specific save file for your Duke session.

### Icon  Replace Us

Tired of seeing the same icon every time, get a fresh icon with a simple command.

Sampled from fake images generated using a GAN, you will hardly see the same icons ever again!

## Creating Tasks

Duke supports creation of 3 different kind of tasks: todo, deadline, event.

### Task todo 

​	Use todo for less urgent tasks / tasks of lower priority. 

- `todo [Task description]` : Adds a todo task to the task list

### Task deadline

​	Use deadline for task of higher priority, you can add a specific date modifier to it or just keep the extra information as a String

- `deadline [task description] /by [completion time/other information]`: Adds a deadline task to the task list

### Task event

​	Use event to mark things that are going to happen, you can add a specific date modifier to it or just keep the extra information as a String

- `event[task description] /by [event date/other information]`: Adds a event task to the task list

## Listing all tasks

​	To view all currently added tasks, use:

- `list` : Prints out all tasks

<img src="./img/list.png" style="zoom:67%;" />

## Marking Tasks

### Mark 

To mark task as completed: 

- `mark [task id]`: to mark the task with the specified id from `list` as marked

### Unmark 

To mark task as incomplete: 

- `unmark [task id]`: to unmark the task with the specified id from `list` as marked

<img src="./img/mark_unmark.png" style="zoom:67%;" />

## Searching For Tasks

### By keywords

​	To filter the current list using keywords, use the `find` command

- `find [search pattern]`: prints out all the tasks that have the specified pattern.

## <img src="./img/find.png" style="zoom:67%;" />   

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
