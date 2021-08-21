//replace ds shows lines where datatype needs to be changed if 
//anything other than int is to be stored in the array
//Naive but acceptable solution for now, until generics is mastered
public class DynamicArray {
    int[] arr;                                      //replace ds
    int size;
    int capacity;
    public DynamicArray(int capacity){
        this.size = 0;
        this.capacity = capacity;
        arr = new int[capacity];                    //replace ds
    }
    private void doubleCapacity(){
        int[] newarr = new int[capacity*2];         //replace ds
        for(int i = 0; i < size; i++){
            newarr[i] = arr[i];
        }
        this.capacity = (this.capacity)*2;
        this.arr = newarr;
    }
    public int get(int index){                      //replace ds
        return arr[index];
    }
    public void set(int index, int elem){           //replace ds
        arr[index] = elem;
    }
    public void add(int element){                   //replace ds
        if(size == (capacity)){
            doubleCapacity();
        }
        arr[size] = element;
        size++;
    }
    public void swap(int i, int j){
        int temp = arr[i];                          //replace ds
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public int remove(){                            //replace ds
        size = size-1;
        return arr[size];
    }
    public int removeAt(int index){                //replace ds
        if(index == size-1){
            return remove();
        }
        int temp = arr[index];                      //replace ds
        for(int i = index+1; i < size-1; i++){
            swap(i,i+1);
        }
        this.size--;
        return temp;
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
