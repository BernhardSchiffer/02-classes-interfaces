package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable<Object> {

    private Element head;
    private Element tail;
    private int size;

    public SimpleListImpl() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(Object item) {
        Element newElement = new Element(item);
        /* add Element at the end */
        if (head == null) {
            head = newElement;
            tail = newElement;
        }
        else {
            tail.next = newElement;
            tail = tail.next;
        }
        /* add Element at head
        newElement.next = this.head;
        this.head = newElement;
         */
        size++;
    }

    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList result = new SimpleListImpl();

        for (Object o : this) {
            if (filter.include(o)) {
                result.add(o);
            }
        }
        return result;
    }

    @Override
    public Iterator<Object> iterator() {
        return new SimpleIteratorImpl();
    }

    public class SimpleIteratorImpl implements Iterator<Object> {

        private Element current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object temp = current.item;
            current = current.next;
            return temp;
        }
    }

    private static class Element {
        private Object item;
        private Element next;

        public Element(Object item) {
            this.item = item;
        }
    }

}
