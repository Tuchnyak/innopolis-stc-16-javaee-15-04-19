package ru.innopolis.lectures.lec04;

import java.util.Collection;
import java.util.List;

public class ListBox<T> {

    private List<T> list;

    public ListBox(List<T> list) {
        this.list = list;
    }

    public void addAll(Collection<? extends T> list) {
        this.list.addAll(list);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
