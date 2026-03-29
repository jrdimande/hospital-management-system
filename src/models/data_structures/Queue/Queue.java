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
        return patient;
    }

    public Patient peek(){
        return this.head.getPatient();
    }
}
