# Mono User Guide

Mono is a JavaFX task manager for organising to-do items, deadlines, and
events. It supports creating, viewing, searching, completing, reopening, and
deleting tasks. Tasks are saved automatically in `data/tasks.txt`.

## Adding deadlines

Add a deadline by entering a description followed by `/by` and a date in
`yyyy-MM-dd` format.

Example:

```text
deadline submit report /by 2026-09-15
```

Mono adds the deadline and displays:

```text
[D][ ] submit report (by: Sep 15 2026)
```

Invalid dates, missing descriptions, and missing dates are rejected with an
error message.

## Adding tasks and events

Add a task without a date using:

```text
todo read a book
```

Add an event using a start date and an end date:

```text
event project retreat /from 2026-09-16 /to 2026-09-18
```

Tasks are assigned one-based IDs in the order they are listed. For example:

```text
1.[T][ ] read a book
2.[E][ ] project retreat (from: Sep 16 2026 to: Sep 18 2026)
```

## Managing tasks

Use `list` to display all tasks and their IDs. Use `find <keyword>` to search
task descriptions; matching is case-sensitive.

Use the task ID shown by `list` to update or remove a task:

```text
mark 1
unmark 1
delete 1
```

The commands `mark` and `unmark` change the completion status, while `delete`
removes the task permanently. Mono reports an error for malformed commands or
task IDs that do not exist.

Use `bye` to end the session:

```text
bye
```

Changes made through `todo`, `deadline`, `event`, `mark`, `unmark`, and
`delete` are saved automatically. Run Mono from the project root with:

```text
./gradlew run
```

JDK 25 and a desktop environment that supports JavaFX are required.
