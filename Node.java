
public class Node<G extends Comparable<G>> {
    protected G payload;
    protected Node<G> left;
    protected Node<G> right;

  // Default constructor
public Node() {
    this.payload = null;
    this.left = null;
    this.right = null;
}

// Overloaded constructor with payload
public Node(G payload) {
    this.payload = payload;
    this.left = null;
    this.right = null;
}

// Mutator for payload
public void setPayload(G payload) {
    this.payload = payload;
}

// Accessor for payload
public G getPayload() {
    return this.payload;
}

// Accessors and mutators for left child
public Node<G> getLeft() {
    return left;
}

public void setLeft(Node<G> left) {
    this.left = left;
}

// Accessors and mutators for right child
public Node<G> getRight() {
    return right;
}

public void setRight(Node<G> right) {
    this.right = right;
}

// toString method that utilizes the payload's toString
@Override
public String toString() {
    return (payload != null) ? payload.toString() : "null";
}

}
