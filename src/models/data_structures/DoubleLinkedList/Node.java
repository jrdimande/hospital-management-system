package models.data_structures.DoubleLinkedList;

public class Node {
    private Object element;
    private Node next;
    private Node prev;

    public Node(Object element){
        this.element = element;
        this.next = null;
        this.prev = null;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
