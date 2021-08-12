import java.util.*;

public class AllSort {
    //merge sort
    public static int[] mergeArray(int[] left, int[] right){
        int nL = left.length;
        int nR = right.length;
        int arr[] = new int[nL + nR];
        int i = 0, j = 0;
        int ctr = 0;
        while((i<nL) && (j<nR)){
            if(left[i] <= right[j]){
                arr[ctr++] = left[i++];
            }
            else{
                arr[ctr++] = right[j++];
            }
        }
        while(i < nL) arr[ctr++] = left[i++];
        while(j < nR) arr[ctr++] = right[j++];
        return arr;
    }
    public static int[] mergeSort(int[] arr){
        if(arr.length == 1) return arr;
        int mid = (arr.length)/2;
        int left[] = new int[mid];
        int right[] = new int[arr.length - mid];

        for(int i = 0; i < mid; i++) left[i] = arr[i];
        int i = 0;
        for(int j = mid; j < arr.length; j++) right[i++] = arr[j];

        left = mergeSort(left);
        right = mergeSort(right);
        return mergeArray(left, right);
    }
    
    //insertion sort
    public static void insertionSort(int[] arr){
        int len = arr.length;
        for(int i = 1; i < len; i++){
            int j = i;
            while(j > 0){
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
                j--;
            }
        }
        // return arr;
    }

    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //2 native ways of implementing quicksort
    static int partition(int[] arr, int left, int right){
        if(right <= left) return 0;
        int avg = arr[right];
        int i = left; int j = left;
        for(; j <= right; j++){
            if(arr[j] <= avg){
                swap(arr,i,j);
                i++;
            }
        }
        return i-1;
    }
    public static void quickSort(int[] arr, int left, int right){
        if((right-left) < 1) return;
        int p = partition(arr,left,right);
        quickSort(arr,left,p-1);
        quickSort(arr,p+1,right);
    }
    static int partition2(int[] arr, int left, int right){
        if(left >= right) return 0;
        int pivot = arr[left];
        int i = left+1;
        int j = right;
        while(i < j){
            if(arr[i] <= pivot) i++;
            else {
                while(arr[j] > pivot) j--;
                if(i >= j) break;
                //swap
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                i++; j--;
            }
        }
        if(arr[i] <= pivot) i++;
        //swap
        int temp = arr[left];
        arr[left] = arr[i-1];
        arr[i-1] = temp;
        return i-1;

    }
    public static void quickSort2(int[] arr, int left, int right){
        if((right-left) <= 0) return;
        int p = partition2(arr,left,right);
        quickSort2(arr,left,p-1);
        quickSort2(arr,p+1,right);
    }

    //randomized quick sort (considered best)
    static int partition_r(int[] arr, int left, int right){
        if((right - left) < 1) return 0;
        //choose a random value and replace with left
        Random random = new Random();
        int r = random.nextInt(right - left + 1) + left;
        swap(arr,left,r);

        //Hoare's algorithm
        int pivot = arr[left];
        int i = left+1;
        int j = right;
        while(true){
            while(arr[i] <= pivot){
                if(i != j) i++;
                else break;
            }
            while(arr[j] > pivot) j--;
            if(i >= j) break;
            else{
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, left, j);
        return j;
    }
    public static void rquickSort(int[] arr, int left, int right){
        if((right - left) < 1) return;
        int p = partition_r(arr, left, right);
        rquickSort(arr, left, p-1);
        rquickSort(arr, p+1, right);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++) arr[i] = scn.nextInt();

        rquickSort(arr, 0, n-1);
        for(int val: arr) System.out.print(val + " ");
        scn.close();
    }
}
