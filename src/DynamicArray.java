import java.util.*;

public class DynamicArray {

    /*
     * Implement a vector (mutable array with automatic resizing):
     * Practice coding using arrays and pointers, and pointer math to jump to an
     * index instead of using indexing.
     * New raw data array with allocated memory
     * can allocate int array under the hood, just not use its features
     * start with 16, or if the starting number is greater, use power of 2 - 16, 32,
     * 64, 128
     * Constructor for new arrays?
     */
    private int size;
    private int capacity;
    private Object arr[];

    public DynamicArray() {
        size = 0;
        capacity = 16;
        arr = new Object[16];
    }

    public DynamicArray(Object[] init) {
        size = init.length;
        capacity = 16;
        while (capacity < init.length)
            capacity *= 2;
        arr = new Object[capacity];
        for (int i = 0; i < init.length; i++) {
            arr[i] = init[i];
        }
    }

    // number of items
    public int size() {
        return size;
    }

    // number of items it can hold
    public int capacity() {
        return capacity;
    }

    // true if empty, false if not
    public boolean is_empty() {
        if (size == 0)
            return true;
        return false;
    }

    // returns the item at a given index, blows up if index out of bounds
    public Object at(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index " + index + " does not exist in this array.");
            return null;
        }
        return arr[index];
    }

    // adds "item" to the end of the array
    public void push(Object item) {
        if (size == capacity)
            resize(2 * capacity);
        arr[size] = item;
        size++;
    }

    // inserts item at index, shifts that index's value and trailing elements to the
    // right
    public void insert(int index, Object item) {
        if (index < 0 || index >= size) {
            System.out.println("Index " + index + " does not exist in this array.");
            return;
        }
        if (size == capacity)
            resize(2 * capacity);
        for (int i = size - 1; i >= index; i--)
            arr[i + 1] = arr[i];
        arr[index] = item;
        size++;
    }

    // can use insert above at index 0
    public void prepend(Object item) {
        insert(0, item);
    }

    // remove from end, return value; if size is 1/4 capacity, resize to 1/2
    // capacity
    public Object pop() {
        if (size == 0)
            return null;
        Object item = arr[size - 1];
        arr[size - 1] = null;
        size--;
        if (size == capacity / 4) {
            capacity = capacity / 2;
            resize(capacity);
        }
        return item; // Object value
    }

    // delete item at index, shifting all trailing elements left
    public void delete(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index " + index + " does not exist in this array.");
            return;
        }
        for (int i = index; i < size; i++)
            arr[i] = arr[i + 1];
        arr[size - 1] = null;
        size--;
        if (size == capacity / 4) {
            capacity = capacity / 2;
            resize(capacity);
        }
    }

    // looks for value and removes index holding it (even if in multiple places)
    public void remove(Object item) {
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (arr[i] != item) {
                arr[k] = arr[i];
                k++;
            }
        }
        for (int i = k; i < size; i++)
            arr[i] = null;
        size = k;
        if (size <= capacity / 4) {
            capacity = capacity / 2;
            resize(capacity);
        }
    }

    // looks for value and returns first index with that value, -1 if not found
    public int find(Object item) {
        for (int i = 0; i < size; i++)
            if (arr[i] == item)
                return i;
        return -1;
    }

    // private function; when reaches capacity, resize to double the size
    private void resize(int new_capacity) {
        Object[] temp = new Object[size];
        for (int i = 0; i < size; i++)
            temp[i] = arr[i];
        arr = new Object[new_capacity];
        for (int i = 0; i < size; i++)
            arr[i] = temp[i];
        capacity = new_capacity;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++)
            str = str + " " + arr[i].toString();
        return str;
    }

    public static void main(String[] args) throws Exception {
        // Create array object
        DynamicArray a = new DynamicArray();
        System.out.println("\nIs empty?: " + a.is_empty());

        // Populate initial data, then modify
        // 0 1 2 3 4 5 6 7 8 9
        for (int i = 0; i < 10; i++)
            a.push(i);
        System.out.println("\nStarting array:");
        a.toString();
        System.out.println("Size: " + a.size());
        System.out.println("Capacity: " + a.capacity());
        System.out.println("Is empty?: " + a.is_empty());
        a.insert(25, 4);
        a.insert(1, 47);
        a.prepend(74);
        a.prepend(88);
        for (int i = 0; i < 6; i++)
            a.prepend(1);
        a.pop();
        a.delete(8);
        a.remove(88);
        System.out.println("Value at index 3 is: " + a.at(3));
        System.out.println("Value at index 40 is: " + a.at(40));
        System.out.println("74 is located at index: " + a.find(74));

        // Print resulting array
        // 1 1 1 1 1 1 74 47 1 2 3 4 5 6 7 8
        // Size: 16; Capacity: 32
        System.out.println("\nResized array:");
        a.toString();
        System.out.println("Size: " + a.size());
        System.out.println("Capacity: " + a.capacity());

        // Subtract enough to downsize capacity
        // 74 2 3 4 5 6 7
        // Size: 7; Capacity: 16
        a.pop();
        a.remove(1);
        a.delete(1);
        System.out.println("\nDownsized array:");
        a.toString();
        System.out.println("Size: " + a.size());
        System.out.println("Capacity: " + a.capacity());

        List<Integer> x = new ArrayList<Integer>();
        x.add(1);
        x.add(2);
        x.add(3);
        List<Integer> y = new ArrayList<Integer>();
        y.add(1);
        y.add(2);
        y.add(3);
        System.out.println("TEST");
        System.out.println(x.get(0) == y.get(0) && x.get(0) == y.get(0));
    }
}
