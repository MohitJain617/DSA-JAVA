public class DoublyLL{
    Node head;
    Node tail;
    int size;
    private class Node{
        int val;
        Node next;
        Node prev;
        public Node(int data, Node next, Node prev){
            this.val = data;
            this.next = next;
            this.prev = prev;
        }
    }
    //constructor
    public DoublyLL(){
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
        Node temp = new Node(data,null,null);
        if(head == null){
            head = tail = temp;
        } else{
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
        size++;
    }
    //adds element at the start of the linked list
    public void addFirst(int data){
        Node temp = new Node(data,null,null);
        if(head == null){
            head = tail = temp;
        } else {
            temp.next = head;
            head.prev = temp;
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
            //toAdd points to temp.next and temp
            Node toAdd = new Node(val, temp.next, temp);
            temp.next = toAdd;
            toAdd.next.prev = toAdd;
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
        else if(head.next == null){
            int t = head.val;
            head = null;
            tail = null;
            size = 0;
            return t;
        }
        else{
            int t = head.val;
            head = head.next;
            head.prev = null;
            size--;
            return t;
        }
    }
    //deletes the last node and adjusts tail
    public int deleteLast(){
        if(head == null) return Integer.MIN_VALUE;
        else if(head == tail){
            int t = tail.val;
            head = tail = null;
            size = 0;
            return t;
        }
        else{
            Node temp = tail.prev;
            tail.prev = null;
            temp.next = null;
            int t = tail.val;
            tail = temp;
            size--;
            return t;
        }
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
            temp.next.prev = temp;
            
            size--;
            return t;
        }
    }

    public static void main(String[] args) throws Exception {
        DoublyLL link = new DoublyLL();
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
        link.deleteAt(1);
        link.deleteAt(0);
        System.out.println(link.length());
        link.print();
    }
}
