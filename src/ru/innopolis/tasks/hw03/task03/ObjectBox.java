package ru.innopolis.tasks.hw03.task03;

import java.util.Collection;

/**
 * Класс для хранения коллекции объектов
 */
public class ObjectBox {

    /**
     * Хранилище объектов
     */
    private Collection<Object> objects;

    public ObjectBox(Collection<Object> objects) {
        this.objects = objects;
    }

    /**
     * Добавление объекта в коллекцию
     * @param o объект для добавления
     */
    public void addObject(Object o) {
        objects.add(o);
    }

    /**
     * Удаление объекта из коллекции
     * @param o объект для удаления
     * @return true, если объект удалён
     */
    public boolean deleteObject(Object o) {
        return objects.remove(o);
    }

    /**
     * Формирование строки с содержимым
     * @return строка с содержимым коллеции
     */
    public String dump() {
        StringBuilder sb = new StringBuilder();

        for (Object o : objects) {
            sb.append(o.toString()).append(" ");
        }

        return sb.toString();
    }

    public Collection<Object> getObjects() {
        return objects;
    }

    public <T> void setObjects(Collection<T> objects) {
        this.objects = (Collection<Object>) objects;
    }
}
