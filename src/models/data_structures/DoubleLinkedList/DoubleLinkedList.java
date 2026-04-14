package models.data_structures.DoubleLinkedList;

import models.entities.Doctor;
import models.entities.Identifiable;
import models.entities.Nameable;
import models.entities.Patient;

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

    public void removeByID(int ID){
        Node current = this.head;

        while (current != null){
            Object obj = current.getElement();

            if (obj instanceof Identifiable){
                Identifiable identifiable = (Identifiable) obj;

                if (identifiable.getId() == ID){
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

                    this.size --;
                    break;
                }
            }
            current = current.getNext();
        }

    }


    public boolean contain(int ID){
        Node current = this.head;


        while (current != null){
            Object obj = current.getElement();
            if (obj instanceof Identifiable){
                Identifiable item = (Identifiable) obj;

                if (item.getId() == ID){
                    return true;
                }
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean contain(String name){
        Node current = this.head;

        while (current != null){
            Object obj = current.getElement();
            if (obj instanceof Nameable){
                Nameable item = (Nameable) obj;

                if (item.getName().equals(name)){
                    return true;
                }
            }
            current = current.getNext();
        }
        return false;
    }

    private Identifiable get(int id){
        Node current = this.head;

        while (current != null){
            Object obj = current.getElement();

            if (obj instanceof Identifiable){
                Identifiable item = (Identifiable) obj;

                if (item.getId() == id){
                    return item;
                }
            }

            current = current.getNext();
        }
        return null;
    }

    public Patient getPatient(int id){
        Patient patient = (Patient) get(id);
        return patient;
    }

    public Doctor getDoctor(int id){
        Doctor doctor = (Doctor) get(id);
        return doctor;
    }


}
