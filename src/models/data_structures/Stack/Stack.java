package models.data_structures.Stack;

import models.entities.Appointment;

public class Stack {
    private Node top;
    private int size;

    public Stack(){
        this.top = null;
        this.size = 0;
    }

    public void push(Appointment appointment){
        Node newNode = new Node(appointment);
        if (top == null){
            this.top = newNode;
            return;
        }
        newNode.setNext(this.top);
        this.top = newNode;

    }

    public void pop(){
        if (this.top == null){
            return;
        }
        this.top = top.getNext();
    }

    public Appointment peek(){
        if (this.top == null){
            return null;
        }
        return this.top.getAppointment();
    }
}
