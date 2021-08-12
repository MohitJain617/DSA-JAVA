public class SinglyLinkedList{
    private Node head;
    private Node tail;
    private int size;
    private class Node{
        int val;
        Node next;
        public Node(int data, Node next){
            this.val = data;
            this.next = next;
        }
    }
    //constructor
    public SinglyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }
    //return boolean true if empty linked list
    public boolean isEmpty(){
        return (size == 0);
    }
    //adds element at the end of linked list
    public void addLast(int data){
        Node temp = new Node(data,null);
        if(head == null){
            head = tail = temp;
        } else{
            tail.next = temp;
            tail = temp;
        }
        size++;
    }
    //adds element at the start of the linked list
    public void addFirst(int data){
        Node temp = new Node(data,null);
        if(head == null){
            head = tail = temp;
        } else {
            temp.next = head;
            head = temp;
        }
        size++;
    }
    //by default add adds element at the end
    public void add(int data){
        addLast(data);
    }

    //insert val at position pos(0 indexed)
    public void insertAt(int pos, int val){
        if(pos == 0) addFirst(val);
        else if(pos == (size)) addLast(val);
        else{
            //iterate temp to its next pos-1 times
            int i = 1;
            Node temp = head;
            while(i < pos){
                temp = temp.next;
                i++;
            }
            Node toAdd = new Node(val, null);
            toAdd.next = temp.next;
            temp.next = toAdd;
            size++;
        }
    }
    
    //returns the first value of the linked list
    public int peekFirst(){
        if(isEmpty()) return Integer.MIN_VALUE;
        return head.val;
    }
    //returns the last value of linked list
    public int peekLast(){
        if(isEmpty()) return Integer.MIN_VALUE;
        return tail.val;
    }
    //returns length of the linked list
    public int length(){
        return size;
    }
    //deletes the first node and adjusts head
    public int deleteFirst(){
        if(head == null) return Integer.MIN_VALUE;
        int temp = head.val;
        head = head.next;
        if(head == null) tail = null;
        size--;
        return temp;
    }
    //deletes the last node and adjusts tail
    public int deleteLast(){
        if(head == null) return Integer.MIN_VALUE;
        Node temp = head;
        while(temp.next != tail){
            temp = temp.next;
        }
        int t = tail.val;
        tail = temp;
        tail.next = null;
        size--;
        return t;
    }
    public void print(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.val+" ");
            temp = temp.next;
        }
        System.out.println();
    }
    public int deleteAt(int pos){
        if(pos == 0) return deleteFirst();
        else if(pos == (size-1)) return deleteLast();
        else{
            int i = 1;
            Node temp = head;
            //reaching behind the node to be deleted
            while(i < pos){
                temp = temp.next;
                i++;
            }
            //deleting
            int t = temp.next.val;
            temp.next = temp.next.next;
            
            size--;
            return t;
        }
    }
    public void reverse(){
        if(head == null) return;
        if(head.next == null) return;
        Node ptr1 = head;
        Node ptr2 = head.next;
        Node ptr3 = head.next.next;
        ptr1.next = null;
        while(ptr3 != null){
            ptr2.next = ptr1;
            ptr1 = ptr2;
            ptr2 = ptr3;
            ptr3 = ptr3.next;
        }
        ptr2.next = ptr1;
        Node temp = head;
        //reversing head and tail;
        head = tail;
        tail = temp;
    }
    static SinglyLinkedList mergeSort(SinglyLinkedList a){
        if(a.head == null) return a;
        if(a.head.next == null) return a;
        Node temp = a.head;
        int size = a.length();
        int mid = size/2;
        SinglyLinkedList left = new SinglyLinkedList();
        SinglyLinkedList right = new SinglyLinkedList();
        temp = a.head;
        for(int i = 0; i < mid; i++){
            left.add(temp.val);
            temp = temp.next;
        }
        for(int i = mid; i < size; i++){
            right.add(temp.val);
            temp = temp.next;
        }
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left,right);
    }
    static SinglyLinkedList merge(SinglyLinkedList left, SinglyLinkedList right){
        SinglyLinkedList combine = new SinglyLinkedList();
        Node leftptr = left.head;
        Node rightptr = right.head;
        while((leftptr != null) && (rightptr != null)){
            if(leftptr.val <= rightptr.val){
                combine.add(leftptr.val);
                leftptr = leftptr.next;
            } else {
                combine.add(rightptr.val);
                rightptr = rightptr.next;
            }
        }
        while(leftptr != null){
            combine.add(leftptr.val);
            leftptr = leftptr.next;
        }
        while(rightptr != null){
            combine.add(rightptr.val);
            rightptr = rightptr.next;
        }
        return combine;
    }

    public static void main(String[] args) throws Exception {
        SinglyLinkedList link = new SinglyLinkedList();
        link.add(2);
        link.add(4);
        link.add(8);
        link.insertAt(2,6);
        link.print();
        link.deleteFirst();
        link.print();
        link.deleteLast();
        link.print();
        link.addFirst(10);
        link.addFirst(5);
        System.out.println(link.length());
        link.print();
        link.add(10);
        link.add(20);
        link.add(10);
        link.addFirst(0);
        link.addFirst(1);
        link.reverse();
        link.print();
        link = mergeSort(link);
        link.print();
        link.deleteAt(1);
        link.deleteAt(0);
        System.out.println(link.length());
        link.print();
    }
}
