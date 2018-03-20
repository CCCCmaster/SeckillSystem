package org.seckill.config;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
class Demo{
    private   String name;
    private  int age;
    public Demo(String name, Integer age){
        this.name = name;
        this.age = age;
    }
    public Demo(){
    }
    public int hashCode(){
            return age/10;
    }
    public boolean equals(Demo obj){
        if(this.name.equals(obj.name) && this.age== (obj.age)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class Test {
    public static void main(String[] args) {

        Demo demo = new Demo("Tom",20);
        Demo demo2 = new Demo("Tom",20);
        System.out.println(demo.hashCode() == demo2.hashCode());
        System.out.println(demo.equals(demo2));
        Map< Demo, String> map = new HashMap<Demo, String>();
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("s","kk");
        map2.put(new String("s"),"kk2");
        String s  = "demo";
        map.put(demo,"demo1");
        map.put(demo2,"demo2");
        System.out.println(map.get(demo));
        System.out.println(map.get(demo2));
        System.out.println(map.size());
        System.out.println(map2.size()+map2.get("s"));
    }
}
