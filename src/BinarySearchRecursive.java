public class BinarySearchRecursive {

    public static int binary_search(int[] arr, int val) {
        return binary_search_recursive(arr, val, 0, arr.length-1);
    }

    private static int binary_search_recursive(int[] arr, int val, int low, int high) {
        int pointer = low+(high-low)/2;
        if(low>high) return -1;
        else if(arr[pointer]==val) return pointer;
        else if(arr[pointer]>val) return binary_search_recursive(arr,val,low,pointer-1);
        else return binary_search_recursive(arr,val,pointer+1,high);
    }

    public static void main(String[] args) {
        int[] test = {0,4,5,7,8,10};
        int v = 10;
        System.out.println(binary_search(test,v));
    }
}
