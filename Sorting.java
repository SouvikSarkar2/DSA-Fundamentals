import javax.swing.KeyStroke;

public class Sorting {
    public static void main(String[] args) {
        int arr[] = { 6, 9, 4, 9, 5, 8, 3 };
        quickSort(arr,0,arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    public static void bubble(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void selection(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;

                    int temp = arr[min];
                    arr[min] = arr[i];
                    arr[i] = temp;
                }

            }
        }
    }

    public static void insertion(int arr[]) {
				for(int i = 1 ; i<arr.length ; i++){
            int curr = arr[i];
            int prev=i-1;
            while(prev>=0 && arr[prev]>curr){
                arr[prev+1]=arr[prev];
                prev--;
            }
            arr[prev+1]=curr;
        }
    }

    public static void counting(int arr[]) {
        int k = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int freq[] = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            freq[arr[i]]++;
        }
        for (int i = 0; i < freq.length; i++) {
            while (freq[i] != 0) {
                k++;
                arr[k] = i;
                freq[i]--;
            }
        }
    }

    public static void mergeSort(int arr[], int si, int ei) {
        if (si >= ei) {
            return;
        }
        int mid = (si + ei) / 2;
        mergeSort(arr, si, mid);
        mergeSort(arr, mid + 1, ei);
        mergefunction(arr, si, mid, ei);
    }

    public static void mergefunction(int arr[], int si, int mid, int ei) {
        int temp[] = new int[ei - si + 1];
        int i = si;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= ei) {
            if (arr[i] > arr[j]) {
                temp[k] = arr[j];
                j++;
            } else {
                temp[k] = arr[i];
                i++;
            }
            k++;
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= ei) {
            temp[k++] = arr[j++];
        }
        for( k = 0, i=si ; k<temp.length ; k++,i++){
            arr[i]=temp[k];
        }
        
    }

    public static void quickSort(int arr[], int si, int ei) {
        if (si >= ei) {
            return;
        }
        int pidx = partition(arr,si,ei);
        quickSort(arr, si, pidx - 1);
        quickSort(arr, pidx + 1, ei);
    }

    public static int partition(int arr[],int si,int ei) {
        int pivot = arr[ei];
        int k = si-1;
        for (int i = si; i < ei; i++) {
            if (pivot >= arr[i]) {
                k++;
                int temp = arr[k];
                arr[k] = arr[i];
                arr[i] = temp;
            }
        }
        k++;
        int temp = pivot;
        arr[ei] = arr[k];
        arr[k] = temp;

        return k;
    }

    public static void heapSort(int arr[]) {
        // create max heap
        for (int i = arr.length / 2; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        // pushing into the end
        for (int i = arr.length-1; i > 0; i--) {
            // swap
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, 0, i);
        }
    }

    public static void heapify(int arr[],int i, int size) {
        int max = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && arr[left] > arr[max]) {
            max = left;
        }

        if (right < size && arr[right] > arr[max]) {
            max = right;
        }

        if (max != i) {
            // swap
            int temp = arr[max];
            arr[max] = arr[i];
            arr[i] = temp;

            heapify(arr, max, size);

        }
    }

}
