package de.co.ret.day08;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"left", "right"})
@ToString(exclude = {"left", "right"})
public class Node<T> {
    private Node<T> left;
    private Node<T> right;
    private final T name;

    static <T> Node<T> of(Node<T> left, Node<T> right, T value) {
        Node<T> node = new Node<>(value);
        node.setLeft(left);
        node.setRight(right);
        return node;
    }
}
