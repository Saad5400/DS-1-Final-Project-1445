public class Stack<E> {

    private class Node {
        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        E data;
        Node next;
    }

    private Node top;

    public void push(E data) {
        top = new Node(data, top);
    }

    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        E data = top.data;
        top = top.next;
        return data;
    }

    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void clear() {
        top = null;
    }

    public int size() {
        int size = 0;
        Node current = top;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    public void print() {
        Node current = top;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
