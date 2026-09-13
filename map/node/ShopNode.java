package com.coloradocollege.cp122homework.project.map.node;

import com.coloradocollege.cp122homework.project.entity.*;
import com.coloradocollege.cp122homework.project.event.Shop;

import java.util.Scanner;

public class ShopNode extends MapNode {
    private Shop shop;

    public ShopNode(int nodeId, Scanner console) {
        super(nodeId);
        shop = new Shop(console);
    }
    public void enter(Player player) {
        shop.execute(player);
    }
    public String toString() {
        return "[Shop]";
    }
}
