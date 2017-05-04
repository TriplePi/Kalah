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
        aneki.add("Калах - настольная логическая \nигра из семейств манкала");
        aneki.add("Шел медведь по лесу, видит - \nмашина горит. Сел в неё и сгорел\nахххахахахахахахах");
        aneki.add("Возраст игры сложно определить, \nно в нее играли еще и в Древнем Египте");
        aneki.add("В этой версии Калаха исправлены \nстарые баги и добавлены новые");
        aneki.add("Компилится - значит работает. \nРаботает - значит не трогай. \nКомпилится - не трогай");
        aneki.add("Программист решил занять у друга \n1000 рублей, но для ровно счета занял 1024");
        aneki.add("Из-за того, что я умею пользоваться \nИнтернетом, меня многие считают программистом");
        aneki.add("Программисты после смерти попадают в ад \nи вечно крутят там ручку \nмеханического арифмометра");
        aneki.add("Определение программиста: \nживой организм, превращающий кофеин и пиццу \nв програмное обеспечение");
        aneki.add("В этом сезоне среди программистов \nпопулярны красные рубашки под цвет глаз");
        aneki.add("Программист должен думать \nна один-два клика дальше мышки");
        aneki.add("Программист - это профессиональный конвертер \nгаллюцинаций заказчика \nв жесткую формальную систему");
        //aneki.add("");


    }

    public String getAnek() {
        System.out.println(aneki.size());
        Random random = new Random();
        return aneki.get(random.nextInt(aneki.size()));
    }
}
