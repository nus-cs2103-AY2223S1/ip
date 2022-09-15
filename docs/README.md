# User Guide

## Installation and Setup
- Ensure that you have Java 11
- Download Duke.jar from release
- Double click the jar file to start the application
- [Alternatively]: `java -jar Duke.jar` to run it via CLI

## Features 

### Feature: Add and remove tasks

Be able to add and remove tasks to the task list.

### Feature: Save and load tasks from storage

Be able to save and load tasks from data/duke.txt.

## Usage

- `todo <description>`: Add a todo task to the task list.
- `deadline <description> /by <date>`: Add a deadline task to the task list. (Using formatted dates)
- `deadline <description> by <date>`: Add a deadline task to the task list.
- `event <description> at <date>`: Add an event task to the task list.
- `list`: List all tasks in the task list.
- `mark <index>`: Mark the task at the specified index as done.
- `unmark <index>`: Mark the task at the specified index as not done.
- `delete <index>`: Delete the task at the specified index.
- `find <keyword>`: Find all tasks that contains the keyword.
- `bye`: Exit the application.
- `save`: Save the task list to data/duke.txt.

