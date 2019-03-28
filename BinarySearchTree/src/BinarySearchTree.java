// Grupo 7
// Gabriel Ely Konrath
// Jorge Medeiros
// William Azevedo de Castro

import java.util.ArrayDeque;
import java.util.Queue;

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
    	if (node != root) {
    		Queue<Node> fila = new ArrayDeque<>();
    		fila.add(node);
    		while (!fila.isEmpty()) {
    			Node current = fila.remove();
    			System.out.print(current + " ");
    			if (current.left != null)
    				fila.add(current.left);
    			if (current.right != null)
    				fila.add(current.right);
    		}
    	}
    }

   // Método conta o número de nós existentes na árvore
    public int countNodes() {
    	if (isEmpty()) {
    		return 0;
    	}
    	
    	return countNodes(root);
    }
    private int countNodes(Node node) {
    	// Verifica se nó não é nulo, caso não seja, adiciona 1 na conta e avança para as próximas sub-árvores 
    	if (node != null) {
    		return 1 + countNodes(node.left) + countNodes(node.right);
    	}
    	
    	return 0;
    }
    
    // Método conta o número de nós internos, ou seja, os nós que não são a raiz nem as folhas
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
    	// Verifica se nó é folha ou raiz, caso não seja, adiciona 1 na conta e avança para as próximas sub-árvores
    	if (!node.isLeaf() && node != root) {
    		return 1 + countInternalNodes(node.left) +
    				   countInternalNodes(node.right);
    	}
    	
    	return countInternalNodes(node.left) + countInternalNodes(node.right);
    }
    
    // Método conta o número de folhas
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
    	// Verifica se nó é folha, caso positivo, conta + 1 e avança na árvore
    	if (node.isLeaf() && node != root) {
    		return 1 + countLeaves(node.left) +
    				   countLeaves(node.right);
    	}
    	
    	return countLeaves(node.left) + countLeaves(node.right);
    }
    
    // Método retorna o número de filhos de um nó
    public int degree(K key) {
    	if (isEmpty()) {
    		return -1;
    	}
    	// Verifica se nó existe
    	if (search(key) == null) {
    		return -1;
    	}
    	
    	return degree(key, root);
    }
    private int degree(K key, Node node) {
    	if (node == null) {
    		return 0;
    	}
    	// Verifica se nó é igual a chave, caso seja, verifica quantos filhos ou sub-árvores esse nó possui 
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
    
    // Método retorna o número máximo de grau existente na árvore
    public int degreeTree() {
    	if (isEmpty()) {
    		return -1;
    	}
    	
    	return degreeTree(root, 0);
    }
    private int degreeTree(Node node, int maiorGrau) {
    	// Verifica o maior grau existente na árvore e guarda o valor
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
    
    // Método retorna a altura de um nó
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
    	// Verifica se há sub-árvores do nó
    	if (node.right == null && node.left == null) {
    		return 0;
    	}
    	
    	// Variáveis para calcular altura das sub-árvores da direita e esquerda
    	int left = 0;
    	int right = 0;
    	
    	// Verifica possibilidade de ir para a esquerda ou direita e conta a altura
    	if (node.left != null) {
			left = height(key, node.left);
		}
    	if (node.right != null) {	
			right = height(key, node.right);
		}
    	
    	// Verifica qual sub-árvore é maior
    	if(left > right) {
    		return left + 1;
    	}
    	else {
    		return right + 1;
    	}
    	
    }
    
    // Método calcula a maior altura da árvore inteira
    public int heightTree() {
    	if (isEmpty()) {
    		return -1;
    	}
    	
    	return heightTree(root);
    }
    private int heightTree(Node node) {
    	// Verifica se há possibilidade de ir para a esquerda ou direita
    	if (node.right == null && node.left == null) {
    		return 0;
    	}
    	
    	// Variáveis para calcular tamanho da sub-árvore da direita e esquerda
    	int left = 0;
    	int right = 0;
    	
    	// Verifica possibilidade de ir para a esquerda ou direita
    	if (node.left != null) {
			left = heightTree(node.left);
		}
    	if (node.right != null) {	
			right = heightTree(node.right);
		}
    	
    	// Verifica qual sub-árvore é maior e retorna o valor
    	if(left > right) {
    		return left + 1;
    	}
    	else {
    		return right + 1;
    	}
    }
    
    // Método calcula a profundidade de um nó
    public int depth(K key) {
    	if (isEmpty()) {
    		return -1;
    	}
    	if (search(key) == null) {
    		return -1;
    	}
    	
    	return depth(key, root, 0);
    }
    private int depth(K key, Node node, int count) {
    	// Verifica se nó é nulo
    	if (node == null) 
            return 0; 
    	// Verifica se chegou no nó a ser contada a altura, caso positivo, retorna a altura
        if (node.key == key) 
            return count; 
   
        // Verifica o nível de profundidade e vai contando a medida que desce para a esquerda e direita
        int deep = depth(key, node.left, count+1); 
        // Verifica se nó foi encontrado na sub-árvore da esquerda, caso encontrou, retorna o valor da profundidade
        if (deep != 0) {
            return deep;
        }
        
        // Caso o nó não foi encontrado na sub-árvore da esquerda, método procura na sub-árvore da direita
        deep = depth(key, node.right, count+1); 
        
        return deep;
    }
    
    // Método retorna os ancestrais de um nó
    public String ancestors(K key) {
    	if (isEmpty()) {
    		return null;
    	}
    	if (search(key) == null) {
    		return null;
    	}
    	
    	Node node = root;
    	
    	return ancestors(key, node, key.toString());
    }
    private String ancestors(K key, Node node, String concat) {
	// Condições de saída
	// Caso não exista o nó (não encontrada a chave)
	if(node == null) {
		return null;
	}
	// Se a chave passada é da raiz, não tem ancestrais
	if(root.key == key) {
		concat = "";
		return "Esta chave não possui ancestrais";
	}
	// Concatena o valor do ancestral
	concat += " " + node.toString();
	// Verifica a direção
	int dir = node.key.compareTo(key);
	if(dir < 0) {
		//Verifica se não é o final
		if(key.compareTo(node.right.key) == 0) {
			return concat;
		}
	}
	if(dir > 0) {
		//Verifica se não é o final
		if(key.compareTo(node.left.key) == 0) {
			return concat;
		}
	}
			
	return ancestors(key, node.next(key), concat);
	}
    
    // Método retorna os descendentes de um nó
    public String descendents(K key) {
    	if (isEmpty()) {
    		return null;
    	}
    	if (search(key) == null) {
    		return null;
    	}
    	
    	// Ao invés de começar o método pelo root, ele começa pelo nó recebido por parâmetro
    	Node node = nodeSearch(key);
    	
    	return descendents(key, node, "");
    }
    private String descendents(K key, Node node, String concat) {
    	if (node == null) {
    		return concat;
    	}
    	// Verifica se nó possui filhos e se chegou ao fim da árvore, nesse caso retorna a string
    	if (node.right == null && node.left == null) {
    		if (concat != null) {
    			concat += node.toString() + " ";
    			return concat;
    		}
    		else {
    			return "Esta chave não possui descendentes";
    		}
    	}
    	
    	// Coloca valor do nó na string
    	concat += node.toString() + " ";
    	
    	// Verifica se pode ir para a esquerda ou direita da árvore
    	if (node.left != null) {
    		concat = descendents(key, node.left, concat);
    	}
    	if (node.right != null) {
    		concat = descendents(key, node.right, concat);
    	}
    	
    	return concat;
    }
    
    // Método semelhante ao search, porém retorna o nó
    public BinarySearchTree<K, V>.Node nodeSearch(K key) {
        return nodeSearch(root, key);
    }
    private BinarySearchTree<K, V>.Node nodeSearch(Node node, K key) {
        if (node == null) {
            return null;
        }
        
        // Ao invés de retornar o valor do nó, retorna o próprio nó 
        else if (key.compareTo(node.key) == 0) {
            return node;
        }
        
        return nodeSearch(node.next(key), key);
    }
    
}
