package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Ноутбук on 21.04.2017.
 */
public class AI {

    Node root;

    HashMap<Integer,Node> rates;

    class Node {
        Node father = null;
        ArrayList<Node> childs;
        Collocation collocation;


        Node(Node father, Collocation collocation) {
            this.father = father;
            childs = new ArrayList<>();
            this.collocation = collocation;
        }

        void addChild(Collocation child) {
            this.childs.add(new Node(this, child));
        }

    }

    public AI(){
        rates = new HashMap<>();
    }

    public Collocation calculate(Collocation collocation) {
        Node father = new Node(null, collocation);
        this.root = father;
        Node node;
        long t = System.currentTimeMillis();
        int deep = 5;
        createNodes(father, deep);
        System.out.println(System.currentTimeMillis()-t);
        node = rates.get(Collections.max(rates.keySet()));
        System.out.println(Arrays.toString(node.collocation.getAllStones()));
        for (int i = 0; i < deep-1; i++) {
            node = node.father;
        }
        return node.collocation;
        //print(father);
        //return null;
    }

    void createNodes(Node father, int count) {
        Collocation clone = new Collocation(father.collocation.getAllStones());
        boolean colour = clone.player;
        if (count > 0)
            for (int i = 0; i < 13; i++) {
                if (!(clone.cells[i] instanceof Kalah) && clone.cells[i].getPlayer() == colour) {
//                System.out.println("zahod2");
                    father.addChild(new Collocation(clone.getAllStones()));
                    ((SimpleCell) ((father.childs.get(father.childs.size() - 1)).collocation.cells[i])).act();
                    createNodes(father.childs.get(father.childs.size()-1),count-1);
//                System.out.println("check");
//                System.out.println(node.childs.size());
//                System.out.println(Arrays.toString(node.childs.get(node.childs.size() - 1).collocation.getAllStones()));
                }
            }
        else rates.put(father.collocation.getAllStones()[13]-father.collocation.getAllStones()[6],father);
    }

    void print(Node node) {
        System.out.println(Arrays.toString(node.collocation.getAllStones()));
        System.out.println(node.childs.size());
        if (node.childs.size() != 0) {
            for (Node child : node.childs) {
                print(child);
            }
        }
    }
}
