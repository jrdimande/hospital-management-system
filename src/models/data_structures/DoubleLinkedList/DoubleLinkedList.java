package models.data_structures.DoubleLinkedList;

import models.entities.Nameable;

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

    public void removeByName(String name){
        Node current = this.head;

        if (this.head == null && this.tail == null){
            return;
        }
        while (current != null){
            Object obj = current.getElement();
            if (obj instanceof Nameable){
                Nameable nameable = (Nameable) obj;

                if (nameable.getName().equals(name)){
                    Node prev = current.getPrev();
                    Node next = current.getNext();

                    if (prev != null){
                        prev.setNext(next);
                    }else {
                        this.head = next;
                    }

                    if (next != null){
                        next.setPrev(prev);
                    }else {
                        this.tail = prev;
                    }

                    this.size--;
                    break;

                }
                current = current.getNext();
            }
        }


    }
    public void removeByID(){}
}
