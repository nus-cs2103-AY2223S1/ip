<img src="https://user-images.githubusercontent.com/97384776/190428501-965c225b-78ce-4ae1-ba4f-bf863d380fe6.png" alt="header" width="250"/>

# Tutter User Guide 

## Overview

Tutter is **not** your average chat bot! Modelled after our favourite loveable rat 🐁, Tutter can help you organise your tasks in a fun way!

## Quick start
1. Ensure you have `Java 11` or above installed in your Computer.

2. Download the latest `Duke.jar` from [here](https://github.com/EiffelLKF/ip/releases/tag/v0.1).

3. Copy the file to the folder you want to use as the home folder for Tutter's chatbot.

4. Right click anywhere in the folder to `Open in Terminal`.

5. Within your terminal, execute `java -jar Duke.jar` to launch the chatbot. 

6. The interface should look like this: 

   ![image](https://user-images.githubusercontent.com/97384776/190425169-636a29e1-dd0b-4cb2-8780-760fb81f8cc6.png)
  
7. Now you're ready to begin your journey with Tutter! Type any command in the command box and press Enter to execute it. 
   e.g. typing list and pressing Enter will show you all your current tasks.  
   
8. You can refer to [Features](#features) for the types of commands available and [Usage](#usage) for how to use each one!

## Features 

### Add 📝

Add new tasks to your task list. Tasks can be of the following types: (1) `Todo` (2) `event` (3) `deadline`

### Delete 🗑

Deletes task from task list given the task index.

### View 🧾

View current task list.

### Find 🔍

Search for list of tasks in task list that contain a given search term.

### Mark 📑

Mark a task as complete in the task list.

### Unmark ❌ 📑

Mark a task as incomplete in the task list. Tasks are marked as incomplete by default.

### Tag 🏷

Tag a task in task list with a custom tag message.

## Usage

### 📝 `todo` - adds a todo task to the task list.

Adds a todo task with the given task name. 
Displays a success message when task is added successfully.

Example of usage: 

`todo Buy Lunch`

Expected outcome:

Success message containing the task and the current number of tasks in your task list.

```
You have added "[T][ ] Buy Lunch" into your Task List!
You have 1 task in your Task List!
```

### 📝 `event` - adds an event task to the task list.

Adds an event task with the given task name and task duration in YYYY-MM-DD HHmm format. 
Displays a success message when task is added successfully.

Example of usage: 

`event Lunch /at 2022-09-13 1250 to 2022-09-13 1400`

Expected outcome:

Success message containing the task and the current number of tasks in your task list.

```
You have added "[E][ ] Lunch (at: SEP 13 2022 1250 to SEP 13 2022 1400)" into your Task List!
You have 1 task in your Task 

```

### 📝 `deadline` - adds a deadline task to the task list.

Adds an deadline task with the given task name and deadline date in YYYY-MM-DD HHmm format. 
Displays a success message when task is added successfully.

Example of usage: 

`deadline Homework /by 2022-09-13 1400`

Expected outcome:

Success message containing the task and the current number of tasks in your task list.

```
You have added "[D][ ] Homework (by: SEP 13 2022 1400)" into your Task List!
You have 1 task in your Task 

```
### 🗑 `delete` - delete task with a given task index

Delete task with a given task index.
Displays a success message when task is deleted successfully.

Example of usage: 

`delete 1`

Success message containing task that has been deleted.

```
You have deleted "{Task}" from your Task List!
You have 0 tasks in your Task List!
```

### 🧾 `list` - display task list.

Displays current task list.

Example of usage: 

`list`

Current task list.

```
1. [T][ ] Buy Lunch 
2. [D][ ] Homework (by: SEP 13 2022 1400)
```

### 🔍 `find` - search for tasks containing a given keyword

Display list of tasks that contain a given keyword.

Example of usage: 

`find lunch`

Expected outcome:

List of tasks containing search keyword (eg. **lunch**)

```
1. [T][ ] Buy Lunch
2. [E][ ] [E][ ] Lunch (at: SEP 13 2022 1250 to SEP 13 2022 1400)
```

### 📑 `mark` - mark a task as complete

Mark a task as complete given the task index.

Example of usage: 

`mark 1`

Expected outcome:

Success message containing task that has been marked.

```
Good Job! The following task "{Task}" has been marked as done!"
```

### ❌ 📑 `unmark` - mark a task as incomplete

Mark a task as incomplete given the task index.

Example of usage: 

`unmark 1`

Expected outcome:

Success message containing task that has been unmarked.

```
Okay! The following task "{Task}" has been marked as not done!"
```

### 🏷 `tag` - tag a task with a custom tag message

Tag a task given the task index and a custom tag message.
Display success message with tagged task.

Example of usage: 

`tag 1 Excited`

Expected outcome:

Success message containing task that has been tagged.

```
Sure! I'll tag this task, take a look: "[T][ ] Lunch [Tag: #Excited]"
```
