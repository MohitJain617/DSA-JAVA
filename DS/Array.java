@SuppressWarnings("unchecked")

//dynamic array
public class Array <T> implements Iterable <T> 
{
    private T[] arr;
    private int len = 0;       // length user thinks array is
    private int capacity = 0;  // Actual array size
    
    public Array() { this(16); }
    
    public Array(int capacity)
    {
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity" + capacity);
        this.capacity=capacity;
        arr=(T[]) new Object[capacity];
    }
    
    public int size() { return len }
    public boolean isEmpty() { return size()==0; }
    
    public T get(int index) { return arr[index]; }
    public void set(int index, T val) { arr[index]=val; }
    
    public void clear()
    {
        for(int i=0;i<capacity;i++)
            arr[i]=null;
        len=0;
    }
    public void add(T val)
    {
        if(len+1>=capacity)
        {
            if(capacity==0) capacity =1;
            else capacity*=2;
            T newArr=(T[]) new Object[capacity];
            for(int i=0;i<len;i++) newArr[i]=arr[i];
            arr=newArr;
        }
        arr[len++]=val;
    }
    
    //Removes the element at the specified index in this list.
    public T removeAt(int index)
    {
        if(index >= len && index <0) throw new IndexOutOfBoundsException();
        T data =arr[index];
        T newArr=(T[]) new Object[len-1];
        for(int i=0;i<index;i++) newArr[i]=arr[i];
        for(int i=index+1;i<len;i++) newArr[i-1]=arr[i];
        arr=newArr;
        len--; capacity=len;
        return data;
    }
    
    //remove the first occurence of an object
    public boolean remove(Object val)
    {
        for(int i=0;i<len;i++)
        {
            if(arr[i].equals(val))
            {
                removeAt(i); return true;
            }
        }
        return false;
    }
    
    public int indexOf(Object val)
    {
        for(int i=0;i<n;i++)
        {
            if(arr[i].equals(val)) return i;
        }
        return -1;
    }
    
    public boolean contains(Object val)
    {
        return indexOf(val)!=-1;
    }
    
    @Override pubic java.util.Iterator <T> iterator()
    {
        return new java.util.Iterator <T> () 
        {
            int index=0;
            public boolean hasNext() { return index < len; }
            public T next() { return arr[index++]; }
        };
    }
    
    @Override public String toString()
    {
        if (len==0) return "[]";
        else
        {
            StringBuilder sb = new StringBuilder(len).append("[";);
            for(int i=0; i<len-1; i++)
                sb.append(arr[i] + ", ");
            return sb.append(arr[len-1] + "]").toString();
        }
    }
}