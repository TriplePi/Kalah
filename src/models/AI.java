package models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ноутбук on 21.04.2017.
 */
public class AI {

    Node root;

    class Node{
        Node father = null;
        ArrayList<Node> childs;
        Collocation collocation;


        Node(Node father, Collocation collocation){
            this.father = father;
            childs = new ArrayList<>();
            this.collocation = collocation;
        }

        void addChild(Collocation child){
            this.childs.add(new Node(this,child));
        }

    }

    public void colculate(Collocation collocation){
        Node father = new Node(null,collocation);
        this.root = father;
        Node node;
        createNodes(father);
        print(father);
        //return null;
    }

    void createNodes(Node father){
        Collocation clone = new Collocation(father.collocation.getAllStones());
        Node node = new Node(father,clone);
        boolean colour = clone.player;
        for (int i = 0; i < 13; i++) {
            if(!(clone.cells[i] instanceof Kalah) && clone.cells[i].getPlayer()==colour) {
                System.out.println("zahod2");
                node.addChild(new Collocation(clone.getAllStones()));
                System.out.println();
                ((SimpleCell)((node.childs.get(node.childs.size()-1)).collocation.cells[i])).act();
            }
        }
    }

    void print(Node node){
        System.out.println(node.childs.size());
        if (node.childs.size()!= 0){
            for (Node child:node.childs) {
                System.out.println("zahod");
                print(child);
            }
        }
        System.out.println(Arrays.toString(node.collocation.getAllStones()));
    }
}
