package unsw.graph;

import unsw.collections.ArrayListSet;

import java.util.*;

public class DepthFirstGraphIterator<N extends Comparable<N>> implements Iterator<N> {

    private final ArrayListSet<N> visted;
    private final Stack<N> stack;
    private Graph<N> graph;

    public DepthFirstGraphIterator(Graph<N> graph, N node) {
        this.graph = graph;
        stack = new Stack<>(){{
            push(node);
        }};
        visted = new ArrayListSet<>(){{
            add(node);
        }};
    }

    @Override
    public boolean hasNext() {
        return stack.size() != 0;
    }

    @Override
    public N next() {
        if (hasNext()) {
            N node = stack.pop();
            List<N> nodeAdjacency = graph.getAdjacentNodes(node);
            Collections.reverse(nodeAdjacency);
            for (N n: nodeAdjacency) {
                if (!visted.contains(n)) {
                    stack.push(n);
                    visted.add(n);
                }
            }
            return node;
        }
        return null;
    }
}
