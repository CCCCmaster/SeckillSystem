package org.seckill.config;

import java.util.*;

class Demo implements Comparable<Demo>{
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

    @Override
    public int compareTo(Demo demo) {
        return this.age > demo.age?-1:1;
    }
}
public class Test {
    public static void main(String[] args) {

        Map<Demo, String> treemap = new TreeMap<Demo, String>();
        Demo d1 = new Demo("Tom",20);
        Demo d2 = new Demo("Jack",18);
        Demo d3 = new Demo("Mary",21);
        Demo d4 = new Demo("Tim",11);
        treemap.put(d1,"21");
        treemap.put(d2,"1");
        treemap.put(d3,"v");
        treemap.put(d4,"a");
//        treemap.put(d4,null);
        Collection col = treemap.values();
        Iterator iterator = col.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
