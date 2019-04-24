package ru.innopolis.tasks.hw03.task02;

import java.util.Collection;

public class ObjectBox {

    private Collection<Object> objects;

    public <T extends Object> ObjectBox(Collection<T> objects) {
        this.objects = (Collection<Object>) objects;
    }

    public void addObject(Object o) {
        objects.add(o);
    }

    public boolean deleteObject(Object o) {
        return objects.remove(o);
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();

        for (Object o : objects) {
            sb.append(o.toString()).append(" ");
        }

        return sb.toString();
    }

}
