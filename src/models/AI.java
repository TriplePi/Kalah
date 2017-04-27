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

    HashMap<Integer, Node> rates;

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

    public AI() {
        rates = new HashMap<>();
    }

    public Collocation calculate(Collocation collocation) {
        Node father = new Node(null, collocation);
        this.root = father;
        Node node;
        long t = System.currentTimeMillis();
        int deep = 7;
        createNodes(father, deep);
        //System.out.println(System.currentTimeMillis() - t);
        if(rates.size()==0){
            return Collocation.getCollocation();
        }
        node = rates.get(Collections.max(rates.keySet()));
        //System.out.println(Arrays.toString(node.collocation.getAllStones()));
        for (int i = 0; i < deep - 1; i++) {
            node = node.father;
        }
        if (node == null)
            return Collocation.getCollocation();
        if(node.collocation!=null)
        return node.collocation;
        else return Collocation.getCollocation();
    }

    void createNodes(Node father, int count) {
        Collocation clone = new Collocation(father.collocation);
        Collocation collocation;
        if(clone.check()==1 || clone.check()==2)        
            return;
        if(clone.check()!=-1 && count > 0)
            for (int i = 0; i < 13; i++) {
                if (!(clone.cells[i] instanceof Kalah) && clone.cells[i].getPlayer() == clone.player && clone.cells[i].getStones()!= 0) {
                    collocation = new Collocation(clone);
                    father.addChild(collocation);
                    ((SimpleCell) collocation.cells[i]).act(collocation);
                    createNodes(father.childs.get(father.childs.size() - 1), count - 1);
                }
            }
        else {
            collocation = father.collocation;
            if (collocation.getAllStones()[13] > collocation.getAllStones()[6])
                rates.put(father.collocation.getAllStones()[13] - father.collocation.getAllStones()[6], father);
        }
    }

    void print(Node node) {
        //System.out.println(Arrays.toString(node.collocation.getAllStones()));
        //System.out.println(node.childs.size());
        if (node.childs.size() != 0) {
            for (Node child : node.childs) {
                print(child);
            }
        }
    }
}
