package ru.innopolis.tasks.hw03.task03;

import java.util.Collection;

/**
 * Класс для хранения коллекции объектов
 */
public class ObjectBox<T> {

    /**
     * Хранилище объектов
     */
    private Collection<T> objects;

    public ObjectBox(Collection<T> objects) {
        this.objects = objects;
    }

    /**
     * Добавление объекта в коллекцию
     *
     * @param o объект для добавления
     */
    public void addObject(T o) {
        objects.add(o);
    }

    /**
     * Удаление объекта из коллекции
     *
     * @param o объект для удаления
     * @return true, если объект удалён
     */
    public boolean deleteObject(T o) {
        return objects.remove(o);
    }

    /**
     * Формирование строки с содержимым
     *
     * @return строка с содержимым коллеции
     */
    public String dump() {
        StringBuilder sb = new StringBuilder();

        for (T o : objects) {
            sb.append(o.toString()).append(" ");
        }

        return sb.toString();
    }

    public Collection<T> getObjects() {
        return objects;
    }

    public void setObjects(Collection<T> objects) {
        this.objects = objects;
    }
}
