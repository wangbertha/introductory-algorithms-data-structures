public class SinglyLinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    // returns the number of data elements in the list
    public int size() {
        return size;
    }

    // bool returns true if empty
    public boolean is_empty() {
        if (size == 0)
            return true;
        return false;
    }

    // adds an item to the front of the list
    public void push_front(Object item) {
        Node n = new Node(this.head, item);
        this.head = n;
        size++;
    }

    // adds an item at the end
    public void push_back(Object item) {
        if (size == 0) {
            push_front(item);
            return;
        }
        Node n = new Node(null, item);
        Node last = this.head;
        while (last.next != null)
            last = last.next;
        last.next = n;
        size++;
    }

    // adds an item at index, so the current item at that index is pointed to by the
    // new item at the index
    public void insert(int index, Object item) {
        if (index == 0 && size == 0) {
            push_front(item);
            return;
        }
        if (index < 0 || index >= size) {
            System.out.println("Index is out of bounds for this SLL.");
            return;
        }
        Node n = new Node(null, item);
        Node hold1 = head;
        Node hold2 = hold1;
        for (int i = 0; i < index - 1; i++)
            hold1 = hold1.next;
        hold2 = hold1.next;
        hold1.next = n;
        n.next = hold2;
        size++;
    }

    // returns the value of the front item
    public Object at_front() {
        return head.item;
    }

    // returns the value of the end item
    public Object at_back() {
        Node traverse = head;
        while (traverse.next != null)
            traverse = traverse.next;
        return traverse.item;
    }

    // returns the value of the nth item (starting at 0 for first)
    public Object at_from_front(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index is out of bounds for this SLL.");
            return null;
        }
        Node traverse = head;
        for (int i = 0; i < index; i++)
            traverse = traverse.next;
        return traverse.item;
    }

    // returns the value of the node at the nth position from the end of the list
    public Object at_from_back(int index) {
        index = size - index;
        if (index < 0 || index >= size) {
            System.out.println("Index is out of bounds for this SLL.");
            return null;
        }
        Node traverse = head;
        for (int i = 0; i < index; i++)
            traverse = traverse.next;
        return traverse.item;
    }

    // returns first index the item appears
    public int find_index(Object ite) {
        Node traverse = head;
        int counter = 0;
        while (traverse.next != null) {
            if (traverse.item == ite)
                return counter;
            traverse = traverse.next;
            counter++;
        }
        return -1;
    }

    // remove the front item and return its value
    public Object pop_front() {
        if (size == 0) {
            System.out.println("There are no values to pop.");
            return null;
        }
        size--;
        Object ite = head.item;
        head = head.next;
        return ite;
    }

    // removes end item and returns its value
    public Object pop_back() {
        if (size == 0) {
            System.out.println("There are no values to pop.");
            return null;
        }
        size--;
        Node traverse = head;
        traverse = traverse.next;
        Node previous = head;
        while (traverse.next != null) {
            traverse = traverse.next;
            previous = previous.next;
        }
        previous.next = null;
        return traverse.item;
    }

    // removes node at given index
    public Object remove_index(int index) {
        if (index == 0)
            return pop_front();
        if (index < 0 || index >= size) {
            System.out.println("Index is out of bounds for this SLL.");
            return null;
        }
        size--;
        Node traverse = head;
        for (int i = 0; i < index - 1; i++) {
            traverse = traverse.next;
        }
        Node skip = traverse.next;
        Object ite = skip.item;
        skip = skip.next;
        traverse.next = skip;
        return ite;
    }

    // removes the first item in the list with this value
    public void remove_item(Object item) {
        Node traverse = head;
        if (traverse.item == item)
            pop_front();
        traverse = traverse.next;
        Node previous = head;
        while (traverse != null) {
            if (traverse.item == item) {
                traverse = traverse.next;
                previous.next = traverse;
                size--;
                return;
            }
            previous = previous.next;
            traverse = traverse.next;
        }
    }

    // reverses the list
    public void reverse() {
        if (size < 2)
            return;
        else if (size == 2) {
            Node hold = head;
            head = head.next;
            head.next = hold;
        } else {
            Node hold1 = head.next;
            Node hold2 = hold1.next;
            head.next = null;
            while (hold2 != null) {
                hold1.next = head;
                head = hold1;
                hold1 = hold2;
                hold2 = hold2.next;
            }
            hold1.next = head;
            head = hold1;
        }

    }

    public String toString() {
        Node traverse = head;
        String str = "";
        for (int i = 0; i < size; i++) {
            str = str + traverse.item + " ";
            traverse = traverse.next;
        }
        return str;
    }

    private static class Node {
        private Node next;
        private Object item;

        private Node() {
            next = null;
            item = null;
        }

        private Node(Node nex, Object ite) {
            next = nex;
            item = ite;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList sll = new SinglyLinkedList();
        // sll.push_front(1);
        sll.push_back(2);
        sll.insert(1, 7);
        sll.insert(1, 3);
        System.out.println("\nThe current values in the SLL are as follows:");
        sll.toString();
        System.out.println("The first value in the SLL is " + sll.at_front());
        System.out.println("The SLL size is " + sll.size());
        System.out.println("The last value in the SLL is " + sll.at_back());
        System.out.println("The value at index 1 is " + sll.at_from_front(-2) + "\n");
        System.out.println("The 2nd value from the back is " + sll.at_from_back(6) + "\n");
        System.out.println("7 appears first at this index " + sll.find_index(9) + "\n");
        System.out.println("Pop the front: " + sll.pop_front());
        System.out.println("Pop the back: " + sll.pop_back());
        sll.push_front(4);
        // sll.push_back(14);
        // sll.push_back(15);
        sll.toString();
        sll.reverse();
        sll.toString();
    }
}
