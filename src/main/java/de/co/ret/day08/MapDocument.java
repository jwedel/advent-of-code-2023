package de.co.ret.day08;

import de.co.ret.day05.Tuple;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(exclude = {"startingNodes"})
@ToString
public final class MapDocument {

    private static final Pattern NODE_PATTERN = Pattern.compile("^(?<value>\\w{3}) = \\((?<left>\\w{3}), (?<right>\\w{3})\\)$");

    private final List<Direction> directions;
    private final Map<String, Node<String>> nodes;
    private final List<Node<String>> startingNodes;

    public static MapDocument fromLines(
            List<String> lines,
            Function<String, Boolean> startNodeSelector) {

        List<Direction> parsedDirection = parseDirections(lines);

        Map<String, Tuple<String, String>> lookupMap = buildNodeNameLookupMap(lines);

        var nodes = new HashMap<String, Node<String>>();

        lookupMap.keySet().forEach(nodeName -> buildTreeFrom(nodeName, lookupMap, nodes));

        var startingNodes = lookupMap.keySet().stream()
                .filter(startNodeSelector::apply)
                .map(startNodeName -> buildTreeFrom(startNodeName, lookupMap, nodes))
                .toList();

        return new MapDocument(parsedDirection, nodes, startingNodes);
    }

    private static Map<String, Tuple<String, String>> buildNodeNameLookupMap(List<String> lines) {
        return lines.stream()
                .skip(2)
                .map(MapDocument::toNodeTuple)
                .collect(Collectors.toMap(Tuple::first, Tuple::second));
    }

    private static List<Direction> parseDirections(List<String> lines) {
        return Stream.of(lines.getFirst().split(""))
                .map(Direction::parse)
                .toList();
    }

    private static Node<String> buildTreeFrom(String nodeName, Map<String, Tuple<String, String>> lookupMap, HashMap<String, Node<String>> nodeCache) {
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

    public long stepsToWalkTo(Function<String, Boolean> targetSelector) {
        var currentNodes = startingNodes;
        var steps = 0L;
        var commonSteps = -1L;
        var routeCache = new HashMap<String, Tuple<Long, Node<String>>>();
        do {
            var currentNodeResults = currentNodes.stream()
                    .map(currentNode -> doCachedWalk(currentNode, routeCache, targetSelector))
                    .toList();

            commonSteps = findCommonStepsToTarget(currentNodeResults);

            if (commonSteps >= 0) {
                return steps + commonSteps;
            }

            currentNodes = currentNodeResults.stream().map(Tuple::second).toList();

            steps += directions.size();

        } while (true);
    }

    private long findCommonStepsToTarget(List<Tuple<Long, Node<String>>> nodeResults) {
        var hasUnknowns = nodeResults.stream().anyMatch(nodeResult -> nodeResult.first() < 0);

        if (hasUnknowns) {
            return -1;
        }

        var firstSteps = nodeResults.getFirst().first();
        var allMatch = nodeResults.stream()
                .map(Tuple::first)
                .allMatch(steps -> steps.equals(firstSteps));

        return allMatch ? firstSteps : -1;
    }

    private Tuple<Long, Node<String>> walkDirections(Node<String> startingNode, Function<String, Boolean> targetSelector) {
        var currentNode = startingNode;
        var stepsToTarget = -1L;
        for (int steps = 0; steps < directions.size(); steps++) {
            var nextDirection = getNextDirection(steps);
            currentNode = walkTo(currentNode, nextDirection);

            if (targetSelector.apply(currentNode.getName())) {
                stepsToTarget = steps + 1;
            }

        }

        return Tuple.of(stepsToTarget, currentNode);
    }

    private Direction getNextDirection(long steps) {
        return directions.get((int) (steps % directions.size()));
    }

    private static Node<String> walkTo(Node<String> currentNode, Direction nextDirection) {
        return switch (nextDirection) {
            case LEFT -> currentNode.getLeft();
            case RIGHT -> currentNode.getRight();
        };
    }

    private static Node<String> walkTo(Node<String> start, List<Direction> directions) {
        if (directions.isEmpty()) {
            return start;
        }
        return walkTo(walkTo(start, directions.getFirst()), directions.subList(1, directions.size()));
    }

    private Tuple<Long, Node<String>> doCachedWalk(Node<String> currentNode, HashMap<String, Tuple<Long, Node<String>>> routeCache, Function<String, Boolean> targetSelector) {
        Tuple<Long, Node<String>> cachedRoute = routeCache.get(currentNode.getName());
        if (null == cachedRoute) {
            cachedRoute = walkDirections(currentNode, targetSelector);
            routeCache.put(currentNode.getName(), cachedRoute);
            System.out.println("Cache miss for: %s -> %s".formatted(currentNode.getName(), cachedRoute));
        }
        return cachedRoute;
    }
}