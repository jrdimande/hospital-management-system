package models.data_structures.Heap;

import models.entities.Patient;
import models.entities.Priority;

public class PriorityQueue {

    private Patient[] heap;
    private int size;

    public PriorityQueue(int capacity) {
        heap = new Patient[capacity];
        size = 0;
    }

    private int priorityValue(Patient p) {
        if (p.getPriority() == null) return 0;

        switch (p.getPriority()) {
            case HIGH: return 3;
            case MEDIUM: return 2;
            case LOW: return 1;
            default: return 0;
        }
    }


    public void add(Patient p) {
        if (size == heap.length) {
            throw new RuntimeException("PriorityQueue cheia!");
        }

        heap[size] = p;
        heapifyUp(size);
        size++;
    }


    public Patient poll() {
        if (size == 0) return null;

        Patient root = heap[0];

        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        heapifyDown(0);

        return root;
    }

    public Patient peek() {
        return size == 0 ? null : heap[0];
    }


    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;

            if (priorityValue(heap[i]) > priorityValue(heap[parent])) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }


    private void heapifyDown(int i) {
        while (true) {
            int largest = i;

            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size &&
                    priorityValue(heap[left]) > priorityValue(heap[largest])) {
                largest = left;
            }

            if (right < size &&
                    priorityValue(heap[right]) > priorityValue(heap[largest])) {
                largest = right;
            }

            if (largest == i) break;

            swap(i, largest);
            i = largest;
        }
    }

    private void swap(int i, int j) {
        Patient temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public int size() {
        return size;
    }
}