# User Guide

![Ui](Ui.png)

## Installation and Setup
- Ensure that you have Java 11
- Download Duke.jar from release
- Double click the jar file to start the application
- Alternatively: `java -jar Duke.jar` to run it via CLI

## Features 

### Feature: Add and remove tasks

Be able to add and remove tasks to the task list.

### Feature: Save and load tasks from storage

Be able to save and load tasks from data/duke.txt.

### Feature: Find tasks with keyword

Be able to find certain tasks based on keyword

## Usage

### `todo <description>`: Add a todo task to the task list.
    - Usage: `todo homework`
    - Format: `todo <description>`

### `deadline <description> /by <date>`: Add a deadline task to the task list. (Using formatted dates)
    - Usage: `deadline homework /by 2020-08-08`

### `deadline <description> by <date>`: Add a deadline task to the task list.
    - Usage: `deadline homework by 8 Aug 2020`
    - Format: `deadline <description> by <date>`

### `event <description> at <date>`: Add an event task to the task list.
    - Usage: `event meeting at 8 Aug 2020`
    - Format: `event <description> at <date>`

### `list`: List all tasks in the task list.
    - Usage: `list`

### `mark <index>`: Mark the task at the specified index as done.
    - Usage: `mark 1`
    - Format: `mark <index>`
    - Note: Index must be a positive integer.

### `unmark <index>`: Mark the task at the specified index as not done.
    - Usage: `unmark 1`
    - Format: `unmark <index>`
    - Note: Index must be a positive integer.

### `delete <index>`: Delete the task at the specified index.
    - Usage: `delete 1`
    - Format: `delete <index>`
    - Note: Index must be a positive integer.

### `find <keyword>`: Find all tasks that contains the keyword.
    - Usage: `find homework`
    - Format: `find <keyword>`

### `bye`: Exit the application.
    - Usage: `bye`

### `save`: Save the task list to data/duke.txt.
    - Usage: `save`

