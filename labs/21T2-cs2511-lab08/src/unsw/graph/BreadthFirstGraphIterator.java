package unsw.graph;

import unsw.collections.ArrayListSet;

import java.util.*;

public class BreadthFirstGraphIterator<N extends Comparable<N>> implements Iterator<N> {

    private final ArrayListSet<N> visted;
    private final Queue<N> queue;
    private Graph<N> graph;

    public BreadthFirstGraphIterator(Graph<N> graph, N node) {
        queue =  new LinkedList<N>(){{
            add(node);
        }};
        this.graph = graph;
        visted = new ArrayListSet<N>(){{
            add(node);
        }};
    }

    @Override
    public boolean hasNext() {
        return queue.size() != 0;
    }

    @Override
    public N next() {
        if (hasNext()) {
            N node = queue.remove();
            List<N> nodeAdjacency = graph.getAdjacentNodes(node);
            for (N n: nodeAdjacency) {
                if (!visted.contains(n)) {
                    queue.offer(n);
                    visted.add(n);
                }
            }
            return node;
        }
        return null;
    }
}
