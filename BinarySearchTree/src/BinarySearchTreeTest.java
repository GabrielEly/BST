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
		
		System.out.print("Pr�-ordem: ");
		arvore.preOrder();
		System.out.println();
		System.out.print("Em-ordem: ");
		arvore.inOrder();
		System.out.println();
		System.out.print("P�s-ordem: ");
		arvore.postOrder();
		System.out.println();
		
		System.out.println("N�s: " + arvore.countNodes());
		System.out.println("N�s internos: " + arvore.countInternalNodes());
		System.out.println("Folhas: " + arvore.countLeaves());
		System.out.println("Grau de um n�: " + arvore.degree(3));
		System.out.println("Grau da �rvore: " + arvore.degreeTree());
		System.out.println("Altura de um n�: " + arvore.height(8));
		System.out.println("Altura da �rvore: " + arvore.heightTree());
		//System.out.println("Profundidade de um n�: " + arvore.depth(x));
		System.out.println("Ancestrais de um n�: " + arvore.ancestors(11));
		System.out.println("Descendesntes de um n�: " + arvore.descendents(4));
		//System.out.println(arvore.getNode(2));
		
		
	}
}
