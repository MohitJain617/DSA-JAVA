public class ArrStack
{
    private int capacity;
    private int[] arr;
    private int top;
    //fixed stack of lenght capacity
    public ArrStack(int n){
        this.capacity = n;
        arr = new int[n];
        top = -1;
    }

    public boolean isEmpty(){
	return (top == -1);
    }
    public boolean isFull(){
	return (top == (capacity-1));
    }

    public void clear(){
        top = -1;
    }
    public int size(){
        return top+1;
    }

    public void push(int val){
        if(isFull()){ System.out.println("full"); return;}
	top++;
	arr[top] = val;
    }
    public int pop(){
        if(isEmpty()){ System.out.println("empty"); return -1;}
    	return arr[top--];
    }
    public int peek(){
        if(isEmpty()) return -1;
        return arr[top];
    }

 public int search(int val){
        for(int i = top; i >= 0; i--){
            if(arr[i] == val) return (top-i);
        }
        return -1;
    }
}


