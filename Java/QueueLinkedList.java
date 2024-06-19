public class QueueLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public QueueLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // number of items
    public int size() {
        return size;
    }

    // adds item at end of available storage
    public void enqueue(Object item) {
        Node n = new Node(item);
        if(size==0) {
            head = n;
            tail = head;
            size++;
            return;
        }
        tail.next = n;
        tail = tail.next;
        tail.next = null;
        size++;
    }

    // returns value and removes least recently added element
    public Object dequeue() {
        if(size==0) return null;
        Object ite = head.item;
        head = head.next;
        return ite;
    }

    // returns true if the queue is empty, false if not
    public boolean empty() {
        return true;
    }

    public String toString() {
        String str = "";
        Node traverse = head;
        while(traverse!=null) {
            str = str + traverse.item + " ";
            traverse = traverse.next;
        }
        return str;
    }

    private static class Node {

        private Object item;
        private Node next;

        private Node() {
            item = null;
            next = null;
        }

        private Node(Object ite) {
            item = ite;
            next = null;
        }
    }

    public static void main(String[] args) {
        QueueLinkedList qll = new QueueLinkedList();
        qll.dequeue();
        qll.enqueue(5);
        qll.enqueue(3);
        qll.enqueue(2);
        qll.enqueue(6);
        qll.enqueue(1);
        qll.dequeue();
        System.out.println(qll.toString());
    }
}