public class BinarySearchTreeTest {
	public static void main(String[] args) {
		BinarySearchTree<Integer, Integer> arvore = new BinarySearchTree<>();

		// Arvore Completa
		
		arvore.insert(4, 1);
		arvore.insert(3, 2);
		arvore.insert(2, 3);
		arvore.insert(8, 4);
		arvore.insert(6, 5);
		arvore.insert(7, 6);
		arvore.insert(9, 7);
		arvore.insert(5, 8);
		arvore.insert(10, 9);
		arvore.insert(11, 10);

		// Arvore Estrita
/*		
		arvore.insert(4, "a");
		arvore.insert(3, "b");
		arvore.insert(8, "c");
		arvore.insert(6, "d");
		arvore.insert(5, "e");
		arvore.insert(7, "f");
		arvore.insert(9, "g");

		// Arvore Degenerada
	
		arvore.insert(5, "a");
		arvore.insert(4, "b");
		arvore.insert(3, "c");
		arvore.insert(2, "d");
		arvore.insert(1, "e");
*/	
		// Arvore Vazia
		
		System.out.println(arvore.toString());
		
		System.out.print("Pré-ordem: ");
		arvore.preOrder();
		System.out.println();
		System.out.print("Em-ordem: ");
		arvore.inOrder();
		System.out.println();
		System.out.print("Pós-ordem: ");
		arvore.postOrder();
		System.out.println();
		
		System.out.println("Nós: " + arvore.countNodes());
		System.out.println("Nós internos: " + arvore.countInternalNodes());
		System.out.println("Folhas: " + arvore.countLeaves());
		System.out.println("Grau de um nó: " + arvore.degree(3));
		System.out.println("Grau da árvore: " + arvore.degreeTree());
		System.out.println("Altura de um nó: " + arvore.height(8));
		System.out.println("Altura da árvore: " + arvore.heightTree());
		//System.out.println("Profundidade de um nó: " + arvore.depth(x));
		System.out.println("Ancestrais de um nó: " + arvore.ancestors(11));
		System.out.println("Descendesntes de um nó: " + arvore.descendents(4));
		//System.out.println(arvore.getNode(2));
		
		
	}
}
