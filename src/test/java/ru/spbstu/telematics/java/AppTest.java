package ru.spbstu.telematics.java;


import junit.framework.TestCase;


/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    public void testApp1()
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
    public void testApp2()
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
    public void testApp3(){
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

}
