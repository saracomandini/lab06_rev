package it.unibo.generics.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<T> implements Graph<T> {

    public enum Color {
        WHITE, GRAY, BLACK
    }

    public final Map<T, Set<T>> graph = new HashMap<>();

    public GraphImpl() {
    }

    @Override
    public void addNode(final T node) {
        Set<T> edges = new HashSet<>();
        graph.put(node, edges);
    }

    @Override
    public void addEdge(final T source, final T target) {
        Set<T> edges = graph.get(source);
        if(edges == null || !graph.containsKey(target)) {
            throw new IllegalArgumentException();
        }

        edges.add(target);
    }

    @Override
    public Set<T> nodeSet() {
        Set<T> nodes = graph.keySet();
        return nodes;
    }

    @Override
    public Set<T> linkedNodes(final T node) {
        if(graph.containsKey(node)){
            Set<T> nodes = graph.get(node);
            return nodes;
        }

        throw new IllegalArgumentException();
    }

    @Override
    public List<T> getPath(final T source, final T target) {
        if (!graph.containsKey(source) || !graph.containsKey(target)) {
            return Collections.emptyList();
        }

        final Map<T, T> predecessor = dfsImpl(source);

        return buildPath(source, target, predecessor);
    }

    private Map<T, T> dfsImpl(final T source) {
        Map<T, Color> color = new HashMap<>();
        Map<T, T> predecessor = new HashMap<>();

        for (T u : graph.keySet()) {
            color.put(u, Color.WHITE);
            predecessor.put(u, null);
        }
        
        dfs(source, color, predecessor);
        return predecessor;
    }

    private void dfs(final T node, final Map<T, Color> color, final Map<T, T> predecessor) {
        color.put(node, Color.GRAY);

        for (T v : graph.get(node)) {
            if (color.get(v) == Color.WHITE) {
                predecessor.put(v, node);
                dfs(v, color, predecessor);
            }
        }
        color.put(node, Color.BLACK);
    }

    private List<T> buildPath(final T source, final T target, final Map<T, T> predecessor) {
        LinkedList<T> path = new LinkedList<>();
        T curr = target;

        while (curr != null && !curr.equals(source)) {
            path.addFirst(curr);
            curr = predecessor.get(curr);
        }
        if (curr == null) {
            return Collections.emptyList();
        }
        path.addFirst(source);
        return path;
    }
}
