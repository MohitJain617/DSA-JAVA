public class Stack<T>
{
    Node<T> top=null;
    int size=0;
    
    private class Node<T>{
        T data;
        Node<T> next;
        public Node(T val,Node<T> next){
            this.data=val;
            this.next=next;
        }
    }
    
    public int size(){
        return this.size;
    }
    
    public boolean isEmpty(){
        return (this.size==0);
    }
    
    public T pop(){
        if(isEmpty()) throw new RuntimeException("Empty stack");
        
        T val=top.data;
        Node<T> temp=top;
        top=top.next;
        temp=null;
        this.size--;
        return val;
    }
    
    public void push(T val){
        if(isEmpty()) top=new Node<T>(val,null);
        else{
            Node<T> temp=new Node<T> (val,top);
            top=temp;
        }
        this.size++;
    }
    
    public void printer(){
        if(isEmpty()){
            System.out.println("Empty"); return;
        }
        Node<T> temp=top;
        System.out.println();
        for(temp=top;temp!=null;temp=temp.next){
            System.out.print(temp.data+"->");
        }
    }
    
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();

        s.push(2);
        s.printer();
        s.push(3);
        s.push(4);
        s.push(6);
        s.printer();
        System.out.println();
        System.out.println(s.size());
        s.pop();
        s.pop();
        s.printer();
        System.out.println();
        System.out.println(s.size());
    }
}
