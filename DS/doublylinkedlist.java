

public class DoublyLinkedList<T>{
    
    private int size=0;
    private Node<T> head = null;
    private Node<T> tail = null;
    
    //Internal node class to represent data
    private class Node<T> {
        T data;
        Node <T> prev,next;
        public Node(T data, Node<T> prev, Node<T> next){
            this.data=data;
            this.prev=prev;
            this.next=next;
        }
    }
    
    //clear all the elements of the linked list
    public void clear(){
        Node<T> trav = head;
        while(trav != null){
            Node<T> next=trav.next;
            trav.prev=null; trav.next=null; 
            trav.data=null;
            trav=next;
        }
        head = tail = trav = null;
        size=0;
    }
    
    //returns the size of list
    public int size(){
        return this.size;
    }
    
    //check if list is empty
    public boolean isEmpty(){
        return size() == 0;
    }
    
    //default add to last
    public void add(T elem){
        addLast(elem);
    }
    
    //add element to the front of list
    public void addFirst(T elem){
        if(isEmpty()) 
            head = tail = new Node<T> (elem,null,null);
        else{
            head.prev = new Node<T> (elem,null,head);
            head=head.prev;
        }
        this.size++;
    }
    
    //add element to the end of list
    public void addLast(T elem){
        if(isEmpty()){
            head = tail = new Node<T> (elem,null,null);
        }
        else{
            tail.next = new Node<T> (elem,tail,null);
            tail=tail.next;
        }
        this.size++;
    }
    
    //get the value of first (head) element
    public T peekFirst(){
        if(isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }
    
    //get the value of last (tail) element
    public T peekLast(){
        if(isEmpty()) throw new RuntimeException("Empty list");
        return tail.data;
    }
    
    //remove first element
    public T removeFirst(){
        //Cant remove data from empty list -_-
        if(isEmpty()) throw new RuntimeException("Empty list");
        
        //get the data and move head forward
        T data=head.data;
        head=head.next;
        --size;
        
        //if empty, tail is null as well, 
        //otherwise clean up memory for the removed element;
        if(isEmpty()) tail=null;
        else head.prev=null;
        
        return data;
    }
    
    //remove last element
    public T removeLast(){
        //Cant remove data from empty list -_-
        if(isEmpty()) throw new RuntimeException("Empty list");
        
        //get the data and move tail backward
        T data=tail.data;
        tail=tail.prev;
        --size;
        
        //if empty, head is null as well, 
        //otherwise clean up memory for the removed element;
        if(isEmpty()) head=null;
        else tail.next=null;
        
        return data;
    }
    
    //internal function to remove a node
    private T remove(Node<T> node){
        //manually handling first and last cases
        if(node==head) return removeFirst();
        if(node==tail)  return removeLast();
        
        //making the pointers of adjacent nodes skip over node
        node.next.prev=node.prev;
        node.prev.next=node.next;
        T data=node.data;
        
        //memory cleanup
        node.data=null;
        node=node.prev=node.next=null;
        
        --size;
        
        return data;
    }
    
    //remove at a particular given index
    public T removeAt(int index){
        if(isEmpty()) throw new RuntimeException("Empty list");
        if(index>=size) throw new RuntimeException("Out of Bounds");
        
        Node<T> trav=head;
        if((size/2)>=index){
            trav=head;
            while(index!=0){
                trav=trav.next; index--;
            }
        }
        else{
            trav=tail;
            while(index!=(size-1)){
                trav=trav.prev; index++;
            }
        }
        
        return remove(trav);
    }
    
    //remove the first occurence of a value 
    //and return a boolean also to indicate 
    //if the value exists
    public boolean remove(Object obj){
        Node<T> trav = head;
        
        //Support searching for null
        if(obj==null) {
            for(trav=head; trav!=null; trav=trav.next){
                if(trav.data==null){
                    remove(trav); return true;
                }
            }
        }
        
        //Searching for non null objects
        else{
            for(trav=head; trav != null; trav=trav.next){
                if(obj.equals(trav.data)){
                    remove(trav); return true;
                }
            }
        }
        
        return false;
    }
    
    //given particular value, find the index
    public int indexOf(Object obj){
        int index=0;
        Node<T> trav = head;
        
        //Support searching for null
        if(obj==null) {
            for(trav=head; trav!=null; trav=trav.next,index++){
                if(trav.data==null){
                    return index;
                }
            }
        }
        
        //Searching for non null objects
        else{
            for(trav=head; trav != null; trav=trav.next,index++){
                if(obj.equals(trav.data)){
                    return index;
                }
            }
        }
        
        return -1;
    }
    
    public void print(){
        Node<T> trav=head;
        if(isEmpty()){
            System.out.println("null"); return;
        }
        for(trav=head;trav.next!=null;trav=trav.next){
            System.out.print(trav.data+"<->");
        }
        System.out.print(trav.data+" (->null)");
    }
    
    public static void main(String[] args){
        DoublyLinkedList<Integer> d = new DoublyLinkedList<Integer>();
        d.add(1);
        d.add(3);
        d.add(5);
        d.addFirst(-1);
        d.addLast(7);
        d.print();
        System.out.println();
        System.out.println(d.size());
        System.out.println(d.peekFirst()+" " +d.peekLast());
        System.out.println("Index of 5 is "+d.indexOf(5));
        d.removeAt(3);
        //d.remove(3);
        d.print();
    }
}