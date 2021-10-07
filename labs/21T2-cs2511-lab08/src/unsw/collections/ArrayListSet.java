package unsw.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ArrayListSet<E> implements Set<E>{

    List<E> listSet;

    public ArrayListSet() {
        this.listSet =  new ArrayList<>();
    }

    @Override
    public void add(E e) {
        if (contains(e))
            return;
        listSet.add(e);
    }

    @Override
    public void remove(E e) {
        if (!contains(e))
            return;
        listSet.remove(e);
    }

    @Override
    public boolean contains(Object e) {
        return listSet.contains(e);
    }

    @Override
    public int size() {
        return listSet.size();
    }

    @Override
    public boolean subsetOf(Set<?> other) {

        for (E e: listSet) {
            if (!other.contains(e))
                return false;
        }

        return true;
    }

    @Override
    public Set<E> union(Set<? extends E> other) {
        ArrayListSet<E> newSet = new ArrayListSet<>();
        for (E e: listSet) {
            newSet.add(e);
        }
        for (E e: other) {
            newSet.add(e);
        }
        return newSet;
    }

    @Override
    public Set<E> intersection(Set<? extends E> other) {
        ArrayListSet<E> newSet = new ArrayListSet<>();
        for (E e: listSet) {
            if (other.contains(e))
                newSet.add(e);
        }
        return newSet;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int currentIndex = -1;

            @Override
            public boolean hasNext() {
                return currentIndex + 1 < size();
            }

            @Override
            public E next() {
                currentIndex++;
                return listSet.get(currentIndex);
            }
        };
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof ArrayListSet))
            return false;
        if (((ArrayListSet<?>) other).size() != size())
            return false;
        for (E e: listSet) {
            if (!((ArrayListSet<?>) other).contains(e))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(listSet);
    }
}
