package de.co.ret.day08;

import de.co.ret.day05.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record MapDocument(List<Direction> directions, Node<String> nodeMap) {

    private static final Pattern NODE_PATTERN = Pattern.compile("^(?<value>\\w{3}) = \\((?<left>\\w{3}), (?<right>\\w{3})\\)$");
    private static final String ROOT_NODE_NAME = "AAA";
    private static final String TERMINATOR_NODE_NAME = "ZZZ";
    private static final Node<String> TERMINATOR_NODE = Node.of(null, null, TERMINATOR_NODE_NAME);

    public static MapDocument fromLines(List<String> lines) {
        List<Direction> parsedDirection = Stream.of(lines.getFirst().split(""))
                .map(Direction::parse)
                .toList();

        Map<String, Tuple<String, String>> lookupMap = lines.stream()
                .skip(2)
                .map(MapDocument::toNodeTuple)
                .collect(Collectors.toMap(Tuple::first, Tuple::second));

        var nodeCache = new HashMap<String, Node>();
        nodeCache.put(TERMINATOR_NODE_NAME, TERMINATOR_NODE);

        var rootNode = buildTreeFrom(ROOT_NODE_NAME, lookupMap, nodeCache);

        return new MapDocument(parsedDirection, rootNode);
    }

    private static Node<String> buildTreeFrom(String nodeName, Map<String, Tuple<String, String>> lookupMap, HashMap<String, Node> nodeCache) {
        if (nodeName == null) {
            return null;
        }

        Node<String> thisNode = Node.of(null, null, nodeName);
        nodeCache.put(nodeName, thisNode);
        Tuple<String, String> rootNodeTuple = lookupMap.get(nodeName);

        var cachedLeft = nodeCache.get(rootNodeTuple.first());
        var cachedRight = nodeCache.get(rootNodeTuple.second());

        var left = cachedLeft != null ? cachedLeft : buildTreeFrom(rootNodeTuple.first(), lookupMap, nodeCache);
        var right = cachedRight != null ? cachedRight : buildTreeFrom(rootNodeTuple.second(), lookupMap, nodeCache);

        thisNode.setLeft(left);
        thisNode.setRight(right);

        return thisNode;
    }

    private static Tuple<String, Tuple<String, String>> toNodeTuple(String nodeString) {
        Matcher matcher = NODE_PATTERN.matcher(nodeString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unable to parse node: " + nodeString);
        }

        String value = matcher.group("value");
        String left = matcher.group("left");
        String right = matcher.group("right");

        return new Tuple<>(value, new Tuple<>(left, right));
    }

    public long stepsToWalkTo(String targetNodeName) {
        return recursiveStepsToWalkTo(targetNodeName, this.nodeMap, 0);
    }

    private long recursiveStepsToWalkTo(String targetNodeName, Node<String> currentNode, int steps) {
        if (targetNodeName.equals(currentNode.getValue())) {
            return steps;
        }

        var nextDirection = directions.get((steps) % directions.size());
        return switch (nextDirection) {
            case LEFT -> recursiveStepsToWalkTo(targetNodeName, currentNode.getLeft(), steps + 1);
            case RIGHT -> recursiveStepsToWalkTo(targetNodeName, currentNode.getRight(), steps + 1);
        };
    }
}
