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
        try {
            aneki = new ArrayList<>();
            File aneks = new File("forFunnyReasons.txt");
            if(aneks != null)
                System.out.println("vsyo OK");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(aneks), "windows-1251"))) {
                String buffer;
                do {
                    buffer = br.readLine();
                    aneki.add(buffer);
                }
                while (!Objects.equals(buffer, ""));
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAnek() {
        System.out.println(aneki.size());
        Random random = new Random();
        return aneki.get(random.nextInt(aneki.size()));
    }
}
