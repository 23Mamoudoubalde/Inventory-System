
public class BinTree<G extends Comparable<G>> {
   private Node<G> root; 

   public BinTree() {
       this.root = null;
   }

   // Public insert method - calls the recursive helper
   public void insert(G payload) {
       root = insertRec(root, payload);
   }
   
   // Recursive insert helper method
   private Node<G> insertRec(Node<G> node, G payload) {
       // Base case: if node is null, create a new node
       if (node == null) {
           return new Node<>(payload);
       }
       
       // Compare and decide which subtree to insert into
       int compareResult = payload.compareTo(node.getPayload());
       
       if (compareResult < 0) {
           // Insert into left subtree
           node.setLeft(insertRec(node.getLeft(), payload));
       } else if (compareResult > 0) {
           // Insert into right subtree
           node.setRight(insertRec(node.getRight(), payload));
       }
       // If equal, do nothing (no duplicates allowed)
       
       // Return the node pointer
       return node;
   }

   // Public search method - calls the recursive helper
   public Node<G> search(G payload) {
       return searchRec(root, payload);
   }
   
   // Recursive search helper method
   private Node<G> searchRec(Node<G> node, G payload) {
       // Base case: if node is null or we found the value
       if (node == null) {
           return null;
       }
       
       int compareResult = payload.compareTo(node.getPayload());
       
       if (compareResult == 0) {
           return node; // Found the node
       } else if (compareResult < 0) {
           return searchRec(node.getLeft(), payload); // Search in left subtree
       } else {
           return searchRec(node.getRight(), payload); // Search in right subtree
       }
   }

   // In-order traversal method (already recursive)
   public void inOrderTraversal() {
       inOrderHelper(root);
   }
    
   private void inOrderHelper(Node<G> node) {
       if (node != null) {
           inOrderHelper(node.getLeft());
           if (node.getPayload() instanceof Game) {
               Game game = (Game) node.getPayload();
               System.out.printf("%-30s %10d %7d\n", game.getTitle(), game.getAvailable(), game.getRented());
           }
           inOrderHelper(node.getRight());
       }
   }

   // Public delete method - calls the recursive helper
   public void delete(G payload) {
       root = deleteRec(root, payload);
   }
   
   // Recursive delete helper method
   private Node<G> deleteRec(Node<G> node, G payload) {
       // Base case: if tree is empty
       if (node == null) {
           return null;
       }
       
       // Find the node to delete by recursively traversing the tree
       int compareResult = payload.compareTo(node.getPayload());
       
       if (compareResult < 0) {
           // Item is in the left subtree
           node.setLeft(deleteRec(node.getLeft(), payload));
       } else if (compareResult > 0) {
           // Item is in the right subtree
           node.setRight(deleteRec(node.getRight(), payload));
       } else {
           // Found the node to delete
           
           // Case 1: Node with no children
           if (node.getLeft() == null && node.getRight() == null) {
               return null;
           }
           // Case 2: Node with only one child (right)
           else if (node.getLeft() == null) {
               return node.getRight();
           }
           // Case 3: Node with only one child (left)
           else if (node.getRight() == null) {
               return node.getLeft();
           }
           // Case 4: Node with two children
           else {
               // Find the successor (minimum value in right subtree)
               Node<G> successor = findMin(node.getRight());
               
               // Copy successor's data to this node
               node.setPayload(successor.getPayload());
               
               // Delete the successor
               node.setRight(deleteRec(node.getRight(), successor.getPayload()));
           }
       }
       
       return node;
   }
   
   // Helper method to find the node with minimum value in a subtree
   private Node<G> findMin(Node<G> node) {
       if (node == null) {
           return null;
       }
       
       if (node.getLeft() == null) {
           return node;
       }
       
       return findMin(node.getLeft());
   }
}