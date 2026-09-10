package com.coloradocollege.cp122homework.project.map.node;

import com.coloradocollege.cp122homework.project.entity.*;

public abstract class MapNode {
    private int nodeId;
    private boolean visited;

    public MapNode(int nodeId) {
        this.nodeId = nodeId;
        this.visited = false;
    }
    public abstract void enter(Player player);
    public void markVisited() {
        visited = true;
    }
    public boolean isVisited() {
        return visited;
    }
    public int getNodeId() {
        return nodeId;
    }
}
