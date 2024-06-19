public class QueueArray {

    private Object arr[];
    private int size;
    private int capacity;

    public QueueArray(int cap) {
        capacity = cap;
        arr = new Object[capacity];
        size = 0;
    }

    // number of items
    public int size() {
        return size;
    }

    // adds item at end of available storage
    public void enqueue(Object item) {
        arr[size] = item;
        size++;
    }

    // returns value and removes least recently added element
    public Object dequeue() {
        if(empty()) return null;
        Object item = arr[size-1];
        arr[size-1] = null;
        size--;
        return item;
    }

    // returns true if the queue is empty, false if not
    public boolean empty() {
        if(size==0) return true;
        return false;
    }

    // returns true if the queue array is full, false if not
    public boolean full() {
        if(size==capacity) return true;
        return false;
    }

    public String toString() {
        String str = "";
        for(int i=0; i<size; i++) str = str + arr[i] + " ";
        return str;
    }

    public static void main(String[] args) {
        QueueArray qa = new QueueArray(16);
        qa.enqueue(5);
        qa.enqueue(6);
        qa.enqueue(8);
        qa.enqueue(0);
        System.out.println("QueueArray is: " + qa.toString());
        System.out.println("QueueArray size is: " + qa.size());
        System.out.println("Dequeue 1 item: " + qa.dequeue());
        System.out.println("Dequeue 1 item: " + qa.dequeue());
        System.out.println("Dequeue 1 item: " + qa.dequeue());
        System.out.println("Dequeue 1 item: " + qa.dequeue());
        System.out.println("Dequeue 1 item: " + qa.dequeue());
        System.out.println("QueueArray is: " + qa.toString());
    }
}
