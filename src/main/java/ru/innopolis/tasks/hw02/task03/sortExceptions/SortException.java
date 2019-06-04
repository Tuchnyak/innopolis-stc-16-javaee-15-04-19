package ru.innopolis.tasks.hw02.task03.sortExceptions;

/**
 * Класс ошибки во время сортировки
 */
public class SortException extends RuntimeException {

    public SortException() {
        super();
    }

    public SortException(String message) {
        super(message);
    }

}
