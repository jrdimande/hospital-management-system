package models.data_structures.HashTable;


public class HashTable {

    private Node[] table;
    private int size;

    public HashTable(int capacity) {
        table = new Node[capacity];
        size = capacity;
    }


    private int hash(String key) {

        int hash = 0;

        for (int i = 0; i < key.length(); i++) {

            char c = key.charAt(i);


            hash = hash + (c * (i + 1));
        }


        while (hash >= size) {
            hash = hash - size;
        }

        while (hash < 0) {
            hash = hash + size;
        }

        return hash;
    }


    public void put(String key, String value) {

        int index = hash(key);

        Node newNode = new Node(key, value);

        if (table[index] == null) {
            table[index] = newNode;
        } else {

            Node current = table[index];

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }
    }


    public String get(String key) {

        int index = hash(key);

        Node current = table[index];

        while (current != null) {

            if (current.key.equals(key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    public void printAll() {

        for (int i = 0; i < size; i++) {

            Node current = table[i];

            while (current != null) {
                System.out.println(current.key + " → " + current.value);
                current = current.next;
            }
        }
    }
}