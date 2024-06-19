public class HashArray {
    private int size;
    private Pair[] arr;
    private static int capacity;
    private Pair dummy;
    private static double a;

    public HashArray() {
        size = 0;
        capacity = 16;
        dummy = new Pair(999, 999);
        arr = new Pair[capacity];
        a = (1 + Math.sqrt(5)) / 2;
    }

    public HashArray(int arr_size) {
        size = 0;
        capacity = arr_size;
        dummy = new Pair(999, 999);
        arr = new Pair[capacity];
        a = (1 + Math.sqrt(5)) / 2;
    }

    private static int hash_function(int k) {
        int h = (int) Math.floor(((k * a) % 1) * capacity);
        return h;
    }

    public static int hash_function(int k, int i) {
        int h = (int) Math.floor(hash_function(k) + 0.5*i + 0.5*i*i) % capacity;
        return h;
    }

    private void resize(int new_cap) {
        int prev_cap = capacity;
        capacity = new_cap;
        Pair[] temp = new Pair[capacity];
        for(int i=0; i<prev_cap; i++) {
            boolean probe = true;
            int j = 0;
            if(arr[i]==null || arr[i]==dummy) probe=false;
            while(probe) {
                int h = hash_function(arr[i].key,j);
                if(temp[h]==null) {
                    temp[h] = arr[i];
                    probe = false;
                }
                if(j>100) System.out.println("too many iterations");
                j++;
            }
        }
        arr = new Pair[capacity];
        for(int i=0; i<capacity; i++) arr[i] = temp[i];
    }

    // returns number of elements
    public int size() {
        return size;
    }

    // add an element to the hash table; if the key already exists, update value
    public void insert(int k, int val) {
        if(size==capacity) resize(2*capacity);
        int i = 0;
        int h = hash_function(k, i);
        Pair set = new Pair(k, val);
        while (true) {
            if (arr[h] == null || arr[h] == dummy) {
                arr[h] = set;
                size++;
                return;
            } else if (arr[h].key == k) {
                arr[h].value = val;
                return;
            }
            else {
                i++;
                h = hash_function(k, i);
            }
                
        }
    }

    public boolean exists(int k) {
        int i = 0;
        int h = hash_function(k,i);
        while(true) {
            if (arr[h] == null) return false;
            else if(arr[h].key==k) return true;
            else {
                if(i==capacity) return false;
                i++;
                h = hash_function(k,i);
            }
        }
    }

    public int search(int k) {
        int i = 0;
        int h = hash_function(k,i);
        while(true) {
            if (arr[h] == null) return -1;
            else if(arr[h].key==k) return arr[h].value;
            else {
                if(i==capacity) return -1;
                i++;
                h = hash_function(k,i);
            }
        }
    }

    public void delete(int k) {
        int i = 0;
        int h = hash_function(k,i);
        while(true) {
            if (arr[h] == null) return;
            else if(arr[h].key==k) {
                arr[h] = dummy;
                size--;
                if(size==capacity/4) resize(capacity/2);
            }
            else {
                if(i==capacity) return;
                i++;
                h = hash_function(k,i);
            }
        }
    }

    private static class Pair {
        private int key;
        private int value;

        private Pair(int k, int v) {
            key = k;
            value = v;
        }
    }

    public String toString() {
        String str = "Hash Table of size "+ size + " and capacity " + capacity + ":";
        for(int i=0; i<capacity; i++) {
            if(arr[i]!=null) str = str + "\n" + i + ": " + arr[i].key + ", " + arr[i].value;
        }
        return str;
    }

    public static void main(String[] args) {
        // Create and populate Hash Table
        HashArray ha = new HashArray(8);
        ha.insert(0,1);
        ha.insert(6,2);
        ha.insert(8,3);
        ha.insert(17,4);
        ha.insert(101,5);
        ha.insert(5,6);
        ha.insert(7,7);
        ha.insert(10,8);
        System.out.println(ha.toString());

        // Push Hash Table into resizing to capacity 16
        ha.insert(100,9);
        System.out.println(ha.toString());

        // Push Hash Table into resizing to capacity 8
        ha.delete(101);
        ha.delete(0);
        ha.delete(7);
        ha.delete(17);
        ha.delete(10);
        System.out.println(ha.toString());

        // Generate hash function sequence for a given key
        /*String str = "Hash Function for 101 is: ";
        for(int i=0; i<16; i++) {
            str = str + " " + hash_function(101,i);
        }
        System.out.println(str);*/
    }
}
