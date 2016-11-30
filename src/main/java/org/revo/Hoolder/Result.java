package org.revo.Hoolder;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<Node> path = new ArrayList<>();
    private Node out;

    public Result() {
    }

    public void addToPath(Node sNode) {
        path.add(sNode);
    }

    public Result setOut(Node out) {
        this.out = out;
        return this;
    }

    public List<Node> getPath() {
        return path;
    }

    public Node getOut() {
        return out;
    }
}
