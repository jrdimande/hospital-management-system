package models.data_structures.Stack;


public class Stack {
    private Node top;
    private int size;

    public Stack(){
        this.top = null;
        this.size = 0;
    }

    public void push(Object element){
        Node newNode = new Node(element);
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

    public Object peek(){
        if (this.top == null){
            return null;
        }
        return this.top.getAppointment();
    }

    public int size(){
        return this.size;
    }
}
