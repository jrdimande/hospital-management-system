package models.data_structures.Stack;

public class Node {
    private Object element;
    private Node next;

    public Node(Object appointment){
        this.element = appointment;
        this.next = null;
    }

    public Object getAppointment() {
        return element;
    }

    public void setAppointment(Object element) {
        this.element = element;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
