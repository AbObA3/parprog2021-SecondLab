package ru.spbstu.telematics.java;


import junit.framework.TestCase;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    public void testSize()
    {
        ru.spbstu.telematics.java.TreeMap<Integer, String> mymap = new ru.spbstu.telematics.java.TreeMap<Integer,String>();//Создание
        mymap.put(12, "Regalinov");//Добавление в коллекцию
        mymap.put(13,"Ivanov");
        mymap.put(14, "Gerilin");
        int size = mymap.size();//
        java.util.TreeMap<Integer,String> surnames =new java.util.TreeMap<Integer,String>();
        surnames.put(12, "Regalinov");//Добавление в коллекцию
        surnames.put(13,"Ivanov");
        surnames.put(14, "Gerilin");
        int size1 = surnames.size();
        assertEquals(size,size1);
    }
    public void testContainsByKey()
    {
        ru.spbstu.telematics.java.TreeMap<Integer, String> mymap = new ru.spbstu.telematics.java.TreeMap<Integer,String>();//Создание
        mymap.put(12, "Regalinov");//Добавление в коллекцию
        mymap.put(13,"Ivanov");
        mymap.put(14, "Gerilin");
        boolean key = mymap.containsByKey(14);
        java.util.TreeMap<Integer,String> surnames =new java.util.TreeMap<Integer,String>();
        surnames.put(12, "Regalinov");//Добавление в коллекцию
        surnames.put(13,"Ivanov");
        surnames.put(14, "Gerilin");
        boolean key1 = surnames.containsKey(14);
        assertEquals(key,key1);
    }
    public void testContainsByValue(){
        ru.spbstu.telematics.java.TreeMap<Integer, String> mymap = new ru.spbstu.telematics.java.TreeMap<Integer,String>();//Создание
        mymap.put(12, "Regalinov");//Добавление в коллекцию
        mymap.put(13,"Ivanov");
        mymap.put(14, "Gerilin");
        boolean value = mymap.containsByValue("Regalinov");
        java.util.TreeMap<Integer,String> surnames =new java.util.TreeMap<Integer,String>();
        surnames.put(12, "Regalinov");//Добавление в коллекцию
        surnames.put(13,"Ivanov");
        surnames.put(14, "Gerilin");
        boolean value1 = surnames.containsValue("Regalinov");
        assertEquals(value,value1);
    }
    public void testGet(){
        ru.spbstu.telematics.java.TreeMap<Integer, String> mymap = new ru.spbstu.telematics.java.TreeMap<Integer,String>();//Создание
        mymap.put(12, "Regalinov");//Добавление в коллекцию
        mymap.put(13,"Ivanov");
        mymap.put(14, "Gerilin");

        java.util.TreeMap<Integer,String> surnames =new java.util.TreeMap<Integer,String>();
        surnames.put(12, "Regalinov");//Добавление в коллекцию
        surnames.put(13,"Ivanov");
        surnames.put(14, "Gerilin");

        assertEquals(mymap.get(12), surnames.get(12));
    }
    public void testRemove(){
        ru.spbstu.telematics.java.TreeMap<Integer, String> mymap = new ru.spbstu.telematics.java.TreeMap<Integer,String>();//Создание
        mymap.put(12, "Regalinov");//Добавление в коллекцию
        mymap.put(13,"Ivanov");
        mymap.put(14, "Gerilin");
        mymap.remove(12);
        java.util.TreeMap<Integer,String> surnames =new java.util.TreeMap<Integer,String>();
        surnames.put(12, "Regalinov");//Добавление в коллекцию
        surnames.put(13,"Ivanov");
        surnames.put(14, "Gerilin");
        surnames.remove(12);
        assertEquals(mymap.containsByKey(12),surnames.containsKey(12));
        assertEquals(mymap.size(),surnames.size());
    }
    public void testExchange(){
        ru.spbstu.telematics.java.TreeMap<Integer, String> mymap = new ru.spbstu.telematics.java.TreeMap<Integer,String>();//Создание
        mymap.put(12, "Regalinov");//Добавление в коллекцию
        mymap.put(13,"Ivanov");
        mymap.put(14, "Gerilin");
        mymap.replace(14, "exchange");
        java.util.TreeMap<Integer,String> surnames =new java.util.TreeMap<Integer,String>();
        surnames.put(12, "Regalinov");//Добавление в коллекцию
        surnames.put(13,"Ivanov");
        surnames.put(14, "Gerilin");
        surnames.replace(14, "exchange");
        assertEquals(surnames.get(14),mymap.get(14));
    }
    public void testIteratorEntrySet(){
        ru.spbstu.telematics.java.TreeMap<Integer, String> mymap = new ru.spbstu.telematics.java.TreeMap<Integer,String>();//Создание
        mymap.put(12, "Regalinov");//Добавление в коллекцию
        mymap.put(13,"Ivanov");
        mymap.put(14, "Gerilin");
        Iterator<TreeMap.Node<Integer, String>> itMymap= mymap.iterator();
        TreeMap.Node<Integer,String> tmp = itMymap.next();

        assertEquals(tmp.m_value,"Regalinov");
        assertEquals(itMymap.hasNext(),true);
    }
    public void testIteratorValue(){
        ru.spbstu.telematics.java.TreeMap<Integer, String> mymap = new ru.spbstu.telematics.java.TreeMap<Integer,String>();//Создание
        mymap.put(12, "Regalinov");//Добавление в коллекцию
        mymap.put(13,"Ivanov");
        mymap.put(14, "Gerilin");
        Iterator<String> itMymap= mymap.iteratorByValue();
        String tmp = itMymap.next();

        assertEquals(tmp,"Regalinov");
        assertEquals(itMymap.hasNext(),true);

    }
    public void testIteratorKey(){
        ru.spbstu.telematics.java.TreeMap<Integer, String> mymap = new ru.spbstu.telematics.java.TreeMap<Integer,String>();//Создание
        mymap.put(12, "Regalinov");//Добавление в коллекцию
        mymap.put(13,"Ivanov");
        mymap.put(14, "Gerilin");
        Iterator<Integer> itMymap= mymap.iteratorByKey();
        int tmp = itMymap.next();

        assertEquals(tmp,12);
        assertEquals(itMymap.hasNext(),true);

    }
}
