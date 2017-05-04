package models;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Ноутбук on 28.04.2017.
 */
public class Anek {
    ArrayList<String> aneki;
    private static Anek ourInstance = new Anek();

    public static Anek getInstance() {
        return ourInstance;
    }

    private Anek() {
        aneki = new ArrayList<>();
        aneki.add("Калах - настольная логическая игра из семейств манкала");
        aneki.add("Шел медведь по лесу, видит - машина горит\nСел в неё и сгорел");
    }

    public String getAnek() {
        System.out.println(aneki.size());
        Random random = new Random();
        return aneki.get(random.nextInt(aneki.size()));
    }
}
