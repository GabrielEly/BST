
public class BinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTreeADT<K, V> {
    protected Node root;

    protected class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public Node next(K other) {
            return other.compareTo(key) < 0 ? left : right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
        
        @Override
        public String toString() { 
        	return "" + key;
        }
        
    }

    @Override
    public void clear() { 
    	root = null; 
    }

    @Override
    public boolean isEmpty() { 
    	return root == null; 
    }
    
    @Override
    public V search(K key) {
        return search(root, key);
    }
    private V search(Node node, K key) {
        if (node == null) {
            return null;
        }
        else if (key.compareTo(node.key) == 0) {
            return node.value;
        }
        
        return search(node.next(key), key);
    }
    
    @Override
    public void insert(K key, V value) {
        root = insert(root, key, value);
    }
    private Node insert(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        } 
        else if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key, value);
        } 
        else if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        }
        
        return node;
    }
    
    @Override
    public String toString() {
        return root == null ? "[empty]" : printTree(new StringBuffer());
    }
    private String printTree(StringBuffer sb) {
        if (root.right != null) {
            printTree(root.right, true, sb, "");
        }
        sb.append(root + "\n"); 
        if (root.left != null) {
            printTree(root.left, false, sb, "");
        }
            
        return sb.toString();
    }

    private void printTree(Node node, boolean isRight, StringBuffer sb, String indent) {
        if (node.right != null) {
            printTree(node.right, true, sb, indent + (isRight ? "        " : " |      "));
        }
        sb.append(indent + (isRight ? " /" : " \\") + "----- " + node + "\n"); 
        if (node.left != null) {
            printTree(node.left, false, sb, indent + (isRight ? " |      " : "        "));
        }
    }
    
    @Override
    public boolean delete(K key) {
       return deleteByCopying(key);
    }
    private boolean deleteByCopying(K key) {
        Node parent = null, current = root;
        for (; current != null && key.compareTo(current.key) != 0; parent = current, current = current.next(key));
        
        if (current == null) 
            return false;
        else if (current.left != null && current.right != null) {
            // Caso 3
            Node tmp = current.left;     
            while (tmp.right != null) tmp = tmp.right;
            deleteByCopying(tmp.key); 
            current.key = tmp.key; 
        } else {
            // Caso 1 ou Caso 2
            Node nextNode = current.right == null ? current.left : current.right;
            if (current.equals(root)) root = nextNode;
            else if (parent.left.equals(current)) parent.left = nextNode;
            else parent.right = nextNode;
        }

        return true;
    }
    
/*
 	@Override
    public boolean delete(K key) {
       return deleteByMerging(key);
    }
    private boolean deleteByMerging(K key) {
        Node parent = null, current = root;
        for (; current != null && key.compareTo(current.key) != 0; parent = current, current = current.next(key));

        if (current == null) 
            return false;
        else if (current.left != null && current.right != null) {
            // Caso 3
            Node tmp = current.left;                                     
            while (tmp.right != null) tmp = tmp.right;                            
            tmp.right = current.right;
                
            if (current.equals(root)) root = current.left;
            else if (parent.left.equals(current)) parent.left = current.left;
            else parent.right = current.left;
        } else {
            // Caso 1 ou Caso 2
            Node nextNode = current.right == null ? current.left : current.right;
            if (current.equals(root)) root = nextNode;
            else if (parent.left.equals(current)) parent.left = nextNode;
            else parent.right = nextNode;
        }

        return true;
    }
*/
    
    @Override
    public void preOrder() {
       preOrder(root);
    }
    private void preOrder(Node node) {
       if (node != null) {
           System.out.print(node + " ");
           preOrder(node.left);
           preOrder(node.right);
       }
    }
    
    @Override
    public void inOrder() {
        inOrder(root);
    }
    private void inOrder(Node node) {
       if (node != null) {
          inOrder(node.left);
          System.out.print(node + " ");
          inOrder(node.right);
       }
    }
    
    @Override
    public void postOrder() {
       postOrder(root);
    }
    private void postOrder(Node node) {
       if (node != null) {
         postOrder(node.left);
         postOrder(node.right);
         System.out.print(node + " ");
       }
    }
    
    @Override
    public void levelOrder() {
       levelOrder(root);
    }
    private void levelOrder(Node node) {
    	
    }

    public int countNodes() {
    	if (isEmpty()) {
    		return 0;
    	}
    	
    	return countNodes(root);
    }
    private int countNodes(Node node) {
    	if (node != null) {
    		return 1 + countNodes(node.left) + countNodes(node.right);
    	}
    	
    	return 0;
    }
    
    public int countInternalNodes() {
    	if (isEmpty()) {
    		return 0;
    	}
    	
    	return countInternalNodes(root);
    }
    private int countInternalNodes(Node node) {
    	if (node == null) {
    		return 0;
    	}
    	if (!node.isLeaf() && node != root) {
    		return 1 + countInternalNodes(node.left) +
    				   countInternalNodes(node.right);
    	}
    	
    	return countInternalNodes(node.left) + countInternalNodes(node.right);
    }
    
    public int countLeaves() {
    	if (isEmpty()) {
    		return 0;
    	}
    	
    	return countLeaves(root);
    }
    private int countLeaves(Node node) {
    	if (node == null) {
    		return 0;
    	}
    	if (node.isLeaf() && node != root) {
    		return 1 + countLeaves(node.left) +
    				   countLeaves(node.right);
    	}
    	
    	return countLeaves(node.left) + countLeaves(node.right);
    }
    
    public int degree(K key) {
    	if (isEmpty()) {
    		return -1;
    	}
    	if (search(key) == null) {
    		return -1;
    	}
    	
    	return degree(key, root);
    }
    private int degree(K key, Node node) {
    	if (node == null) {
    		return 0;
    	}
    	if (key.compareTo(node.key) == 0) {
    		if (node.left == null && node.right == null) {
    			return 0;
    		}
    		else if ((node.left != null && node.right == null) || (node.left == null && node.right != null)) {
    			return 1;
    		}
    		else if (node.left != null && node.right != null) {
    			return 2;
    		}
    	}
    	
    	return degree(key, node.left) + degree(key, node.right);
    }
    
    public int degreeTree() {
    	if (isEmpty()) {
    		return -1;
    	}
    	
    	return degreeTree(root, 0);
    }
    private int degreeTree(Node node, int maiorGrau) {
    	if (node.left != null && node.right == null) {
			maiorGrau = 1;
			return degreeTree(node.left, maiorGrau);
		}
    	else if (node.left == null && node.right != null) {
			maiorGrau = 1;
			return degreeTree(node.right, maiorGrau);
		}
    	else if (node.left != null && node.right != null) {
			maiorGrau = 2;
			return maiorGrau;
		}
    	
    	return maiorGrau;
    }
    
    public int height(K key) {
    	if (isEmpty()) {
    		return -1;
    	}
    	if (search(key) == null) {
    		return -1;
    	}
    	Node node = nodeSearch(key);
    	
    	return height(key, node);
    }
    private int height(K key, Node node) {
    	if (node.right == null && node.left == null) {
    		return 0;
    	}
    	
    	int left = 0;
    	int right = 0;
    	
    	if (node.left != null) {
			left = height(key, node.left);
		}
    	if (node.right != null) {	
			right = height(key, node.right);
		}
    	if(left > right) {
    		return left + 1;
    	}
    	else {
    		return right + 1;
    	}
    	
    }
    
    public int heightTree() {
    	if (isEmpty()) {
    		return -1;
    	}
    	
    	return heightTree(root);
    }
    private int heightTree(Node node) {
    	if (node.right == null && node.left == null) {
    		return 0;
    	}
    	
    	int left = 0;
    	int right = 0;
    	
    	if (node.left != null) {
			left = heightTree(node.left);
		}
    	if (node.right != null) {	
			right = heightTree(node.right);
		}
    	if(left > right) {
    		return left + 1;
    	}
    	else {
    		return right + 1;
    	}
    }
    
    // Errado
    public int depth(K key) {
    	if (isEmpty()) {
    		return -1;
    	}
    	if (search(key) == null) {
    		return -1;
    	}
    	
    	return depth(key, root);
    }
    private int depth(K key, Node node) {
    	int height1 = 0;
	int height2 = 0;
	if(root.left!=null)
        	height1 = depth(key, root.left);
	if(root.right!=null)
	    	height2 = depth(key, root.right);
	return Math.max(height1, height2) + 1;
    }
    
    // Errado
    public String ancestors(K key) {
    	if (isEmpty()) {
    		return null;
    	}
    	if (search(key) == null) {
    		return null;
    	}
    	Node node = nodeSearch(key);
    	
    	return ancestors(key, node);
    }
    private String ancestors(K key, Node node) {
    	if (node == null) {
    		return "";
    	}
    	if (node == root){
            System.out.println(getNodeString(key));
            return "";
        }
        System.out.print(root + " ");
        if(node.next(key) == node.left)
            ancestors(key, node.left);
        
        else
            ancestors(key, node.right);
    	
    	return "";
    }
    
    // Errado
    public String descendents(K key) {
    	if (isEmpty()) {
    		return null;
    	}
    	if (search(key) == null) {
    		return null;
    	}
    	Node node = nodeSearch(key);
    	
    	return descendents(key, node, "");
    }
    private String descendents(K key, Node node, String string) {
    	if (node.right == null && node.left == null) {
    		return string;
    	}
    	string += getNodeString(node.key) + " ";
    	if (node.left != null) {
    		descendents(key, node.left, string);
    	}
    	if (node.right != null) {
    		descendents(key, node.right, string);
    	}
    	
    	return string;
    }
    
    // Método semelhante ao search, porém retorna o nó
    public BinarySearchTree<K, V>.Node nodeSearch(K key) {
        return nodeSearch(root, key);
    }
    private BinarySearchTree<K, V>.Node nodeSearch(Node node, K key) {
        if (node == null) {
            return null;
        }
        else if (key.compareTo(node.key) == 0) {
            return node;
        }
        
        return nodeSearch(node.next(key), key);
    }
    
    // Gera uma String do nó
    public String getNodeString(K key) {
    	if (search(key) == null) {
    		return null;
    	}
    	return "" + key;
    }
    
}
