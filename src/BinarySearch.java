public class BinarySearch {

    public static int binary_search(int[] arr, int val) {
        int low = 0;
        int high = arr.length-1;
        int pointer = -1;
        while(low<=high) {
            pointer = (high-low)/2+low;
            if (arr[pointer]==val) return pointer;
            else if (arr[pointer]<val) low = pointer+1;
            else high = pointer-1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] test = new int[]{0,4,5,7,8,10};
        int v = 10;
        System.out.println(binary_search(test,v));
    }
}
