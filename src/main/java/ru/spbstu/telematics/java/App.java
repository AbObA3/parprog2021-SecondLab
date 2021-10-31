package ru.spbstu.telematics.java;


import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


public class App 
{
    public static void main( String[] args )
    {
        //Реализованный интерфейс TreeMap
        treemap<Integer, String> mymap = new treemap<Integer,String>();//Создание
        mymap.put(12, "Regalinov");//Добавление в коллекцию
        mymap.put(13,"Ivanov");
        mymap.put(14, "Gerilin");
        int size = mymap.size();//Размер коллекции
        boolean key = mymap.containsByKey(14);//Проверка существования элемента в коллекции по ключу
        boolean value = mymap.containsByValue("Regalinov");//Проверка существования элемента в коллекции по значению
        treemap.Node<Integer,String> tmp = mymap.getNode(13);//Получение элемента по ключу
        System.out.println(tmp.toString());//Вывод полученного элемента для проверки
        Iterator<treemap.Node<Integer,String>> it = mymap.iterator();//Проверка интерфейса Iterable<T>
        boolean hn = it.hasNext();
        tmp=it.next();
        mymap.remove(14);//Удаление элемента по ключу
        mymap.clearAll();//Удаление всех элементов

        //Коллекция TreeMap
        TreeMap<Integer,String> surnames =new TreeMap<Integer,String>();
        surnames.put(12, "Regalinov");//Добавление в коллекцию
        surnames.put(13,"Ivanov");
        surnames.put(14, "Gerilin");
        int size1 = surnames.size();
        boolean key1 = surnames.containsKey(14);//Проверка существования элемента в коллекции по ключу
        boolean value1 = surnames.containsValue("Regalinov");//Проверка существования элемента в коллекции по значению
        surnames.remove(14);//Удаление элемента по ключу
        surnames.clear();//Удаление всех элементов
    }
}
