//for simplicity, assume no duplicate values
class Avl {
	TreeNode root;
	int size;
	public Avl(){
		root = null;
		size = 0;
	}
	public int size(){ return this.size;}

	//inserts a Treenode and mantains the avl invariant
	public boolean insert(int data){
		if(contains(data) == true) return false;
		else{
			TreeNode p = new TreeNode(data);
			this.root = insert(this.root, p);
			this.size++;
			return true;
		}
	}
	//inductive assumption:
		//it inserts, balances that part, updtates height and returns root
	//base case
		//if root is null, returns p
	//nth case
		//insert p to n-1, balance and update height
	private TreeNode insert(TreeNode root, TreeNode p){
		if(root == null){
			return p;
		}
		if(root.data < p.data){
			root.right = insert(root.right,p);
		} else {
			root.left = insert(root.left,p);
		}
		root = update(root);
		root = balance(root);
		return root;
	}
	
	// updates the height and balance factor of a TreeNode
	private TreeNode update(TreeNode root){
		if(root == null) return null;
		int lh;
		int rh;
		if(root.left == null) lh = 0;
		else{
			lh = root.left.height();
		}
		if(root.right == null) rh = 0;
		else{
			rh = root.right.height();
		}
		root.height = 1+Math.max(lh,rh);
		root.bf = lh - rh;
		return root;
	}

	//balancing of the tree through rotations
	private TreeNode balance(TreeNode root){
		//left
		if(root == null) return null;
		 if(root.bf == 2){
			if(root.left.bf >= 0){
				//left left 
				root = rightRotation(root);
			} else {
				//left right 
				root.left = leftRotation(root.left);
				root = rightRotation(root);
			}
		 }
		 //right
		 else if(root.bf == -2){
			if(root.right.bf <= 0){
				//right right 
				root = leftRotation(root);
			} else {
				//right left 
				root.right = rightRotation(root.right);
				root = leftRotation(root);
			}
		 }
		 return root;
	}

	//search/contains, returns boolean 
	//true if element in tree, false otherwise
	public boolean contains(int key){
		if(root == null) return false;
		//binary search;
		TreeNode trav = root;
		while(trav != null){
			if(trav.data == key) return true;
			else if(trav.data > key) trav = trav.left;
			else trav = trav.right;
		}
		return false;
	}
	
	private TreeNode leftRotation(TreeNode p){
		if(p == null) return null;
		TreeNode rightp = p.right;
		p.right = rightp.left;
		rightp.left = p;
		update(p);
		update(rightp);
		return rightp;
	}
	private TreeNode rightRotation(TreeNode p){
		if(p == null) return null;
		TreeNode leftp = p.left;
		p.left = leftp.right;
		leftp.right = p;
		update(p);
		update(leftp);
		return leftp;
	}

	//deletes a Treenode with value key and mantains 
	//the avl invariant
	public boolean delete(int key){
		if(contains(key) == false) return false;
		else{
			this.root = delete(this.root,key);
			size--;
			return true;
		}
	}
	//algorithm for deleting nodes
		//if node is in left side, delete in left side
		//if node is in right side, delete in right side
		//if current is to be deleted:
			//if node is leaf or has only one child, can be deleted and returned
			//now if node has 2 children...  find successor and swap the successor
			//with node to be deleted and call the deletion of the successor node in the right side
			//
	private TreeNode delete(TreeNode root, int key){
		if(root == null){}
		else if(root.data < key){
			root.right = delete(root.right,key);
		}
		else if(root.data > key){
			root.left = delete(root.left,key);
		}
		else{
			if((root.left == null) && (root.right == null)) return null;
			if((root.left == null) || (root.right == null)){
				if(root.left != null) return root.left;
				else return root.right;
			}
			//last case
			TreeNode successor = root.right;
			while(successor.left != null){
				successor = successor.left;
			}
			root.data = successor.data;
			root.right = delete(root.right,successor.data);
		}
		root = update(root);
		root = balance(root);
		return root;
	}
	//print height of the avl tree
	public int height(){
		if(this.root == null) return 0;
		return this.root.height();
	}
	public boolean checkAvl(){
		return checkAvl(this.root);
	}
	private boolean checkAvl(TreeNode root){
		if(root == null) return true;
		if(root.left == null && root.right == null) return true;
		if(checkAvl(root.left) == false) return false;
		if(checkAvl(root.right) == false) return false;
		int lh,rh;
		if(root.left == null) lh = 0;
		else lh = root.left.height();

		if(root.right == null) rh = 0;
		else rh = root.right.height();

		if(Math.abs(lh-rh) < 2) return true;
		else return false;
	}
	public void inorder(){
		inorder(this.root);
	}
	private void inorder(TreeNode root){
		if(root == null) return;
		inorder(root.left);
		System.out.print(root.data + " ");
		inorder(root.right);
	}

	
}

class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;

	//height from that part, null is zero, leaf is 1
	int height;

	//balance factor
	int bf;

    public TreeNode(int data){
        this.data = data;
		height = 1;
		bf = 0;
        this.left = null;
        this.right = null;
    }
	//be vary of using this one
	public int height(){
		return this.height;
	}
	
}
