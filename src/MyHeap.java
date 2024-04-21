import java.util.NoSuchElementException;

public class MyHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap;

    public MyHeap() {
        heap = new MyArrayList<T>();
    }

    public T get(int index) {
        return heap.get(index);
    }

    public boolean empty() {
        return heap.length() == 0;
    }

    public int size() {
        return heap.length();
    }

    public T extractMin() {
        T min = getMin();

        heap.remove(heap.indexOf(min));

        int startIdx = (heap.length() / 2) - 1;

        for (int i = startIdx; i >= 0; i--) {
            heapify(i);
        }

        return min;
    }

    public T getMin() {
        if (empty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T min = heap.get(0);

        for (int i = 0; i < heap.length(); i++) {
            if (min.compareTo(heap.get(i)) > 0) {
                min = heap.get(i);
            }
        }

        return min;
    }

    public void insert(T item) {
        heap.add(item);
        traverseUp(heap.indexOf(item));
    }

    private T leftChildOf(int index) {
        int leftChildIndex = 2 * index + 1;
        if (leftChildIndex < heap.length()) {
            return heap.get(leftChildIndex);
        } else {
            throw new IndexOutOfBoundsException("no heap for that");
        }
    }

    private T rightChildOf(int index) {
        int rightChildIndex = 2 * index + 2;
        if (rightChildIndex < heap.length()) {
            return heap.get(rightChildIndex);
        } else {
            throw new IndexOutOfBoundsException("no heap for that");
        }
    }


    private int parentOf(int index) {
        if (index < 0 || index > heap.length()) {
            throw new IndexOutOfBoundsException("There is no parent of that heap");
        }

        return index / 2;
    }

    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    private void heapify(int index) {
        int smallest = index;
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;

        if (leftChildIndex < heap.length() && heap.get(leftChildIndex).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChildIndex;
        }

        if (rightChildIndex < heap.length() && heap.get(rightChildIndex).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChildIndex;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }


    private void traverseUp(int index) {
        int parentIndex = parentOf(index);

        while (index > 0 && heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = parentOf(index);
        }
    }


}