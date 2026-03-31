package models.data_structures.DoubleLinkedList;

public class DoubleLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public DoubleLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(Object element){
        Node newNode = new Node(element);

        if (head == null){
            this.head = newNode;
            this.tail = newNode;
        }else {
            this.tail.setNext(newNode);
            newNode.setPrev(this.tail);
            this.tail = newNode;
        }
        this.size++;
    }

    public int size(){
        return size;
    }
}
