public class Bst {
    //root based tree
    public Node root;
    //number of elements
    int size;
    public Bst(){
        root = null;
        size = 0;
    }
    public int size(){
        return size;
    }

    //isEmpty
    public boolean isEmpty(){
        return (root == null);
    }
    
    public void insert(int data){
        //insert a new node with value data
        Node p = new Node(data);
        insert(p);
    }
    public void insert(Node p){
        size++;
        //insert the node p
        if(root == null){
            root = p;
            return;
        } 
        Node temp = root;
        //iterating to the appropriate position
        //and inserting the node p 
        while(temp != null){
            if(temp.data > p.data){
                if(temp.left == null){
                    temp.left = p;
                    return;
                } else {
                    temp = temp.left;
                }
            } 
            else {
                if(temp.right == null){
                    temp.right = p;
                    return;
                } else {
                    temp = temp.right;
                }
            }
        }
    }
    //returns true if key is present and deletes it as well
    //else false if key not matching any value
    public boolean delete(int key){
        //find the node and the its parent node
        //case 1: leaf node
        //case 2: intermediate with 1 child
        //case 3: intermediate with 2 child
        Node p = root;
        Node curr = root;
        while(curr != null){
            if(curr.data == key) break;
            else if(curr.data < key){
                p = curr;
                curr = curr.right;
            }
            else {
                p = curr;
                curr = curr.left;
            }
        }
        //no value matches key
        if(curr == null) return false;
        //decrease size
        size--;
        //deleting root case and one of case 1 or 2
        if(curr == root){
            if((root.left == null) && (root.right == null)){
                root = null;
                return true;
            }
            else if((root.left == null) || (root.right == null)){
                if(root.left != null){
                    root = root.left;
                    return true;
                } else {
                    root = root.right;
                    return true;
                }
            }
        }
        //case 1:
        if((curr.left == null) && (curr.right == null)){
            if(p.left == curr) p.left = null;
            else p.right = null;
            return true;
        }

        //case 2:
        else if((curr.left == null) || (curr.right == null)){
            if(curr.left != null){
                if(p.left == curr){
                    p.left = curr.left;
                    return true;
                } else {
                    p.right = curr.left;
                    return true;
                }
            }
            else{
                if(p.left == curr){
                    p.left = curr.right;
                    return true;
                } else {
                    p.right = curr.right;
                    return true;
                }
            }
        }

        //case 3:
        //iterate to find successor and parent of successor
        //case: parent node is curr node itself
        //case: all the other cases
        else{
            Node ssr = curr.right; //successor
            Node pssr = curr; //parent of successor
            while(ssr.left != null){
                pssr = ssr;
                ssr = ssr.left;
            }
            //curr is parent of successor
            if(pssr == curr){
                curr.data = ssr.data;
                curr.right = ssr.right;
                return true;
            } else {
                curr.data = ssr.data;
                pssr.left = ssr.right;
                return true;
            }
        }
    }

    //return boolean if root is in node
    public boolean search(int key){
        return search(root,key);
    }
    //helper function
    private boolean search(Node root, int key){
        if(root == null) return false;
        if(root.data == key) return true;
        else{
            if(root.data > key) return search(root.left,key);
            else  return search(root.right,key);
        }
    }

    public void inorderPrint(){
        inorder(root);
        System.out.println();
    }
    private void inorder(Node root){
        if(root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("--------------------------------------------------");
        Bst tree = new Bst();
        tree.insert(10);
        tree.insert(3);
        tree.insert(2);
        tree.insert(6);
        tree.insert(32);
        tree.insert(31);
        tree.insert(0);
        tree.insert(-1);

        tree.inorderPrint();

        tree.delete(10);
        tree.inorderPrint();
        tree.delete(2);
        tree.inorderPrint();
        tree.delete(-1);
        tree.inorderPrint();
        System.out.println(tree.size());
        System.out.println(tree.search(3));
        System.out.println(tree.search(5));
    }
}
class Node{
    int data;
    Node left;
    Node right;
    public Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
    public Node(int data, Node left, Node right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
