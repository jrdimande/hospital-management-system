package models.data_structures.Queue;
import models.entities.Patient;

public class Queue {
    private Node head;
    private Node tail;
    private int size;

    public Queue(){
        this.head = this.tail = null;
        this.size = 0;
    }

    public void enqueue(Patient patient){
        Node newNode = new Node(patient);

        if (this.head == null){
            this.head = this.tail = newNode;
        }else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        this.size++;

    }

    public Patient getPatient(int id){
        Node current = this.head;

        while (current != null){
            if (current.getPatient().getId() == id){
                return current.getPatient();
            }
            current = current.getNext();
        }
        return null;
    }

    public Patient dequeue(){
        if (this.head == null){
            return null;
        }
        Patient patient = this.head.getPatient();
        this.head = this.head.getNext();

        if (this.head == null){
            this.tail = null;
        }
        this.size--;
        return patient;
    }


    public int size(){
        return size;
    }

    public Patient peek(){
        if (this.head == null){
            return null;
        }
        return this.head.getPatient();
    }

    public boolean contain(int ID){
        Node current = this.head;

        while (current != null){
            if (current.getPatient().getId() == ID){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public Node getHead(){
        return this.head;
    }
}
