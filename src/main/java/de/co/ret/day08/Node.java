package de.co.ret.day08;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"left", "right"})
@ToString(exclude = {"left", "right"})
public class Node<T> {
    private Node left;
    private Node right;
    private final T value;

    static <T> Node<T> of(Node left, Node right, T value) {
        Node<T> node = new Node<>(value);
        node.setLeft(left);
        node.setRight(right);
        return node;
    }
}
