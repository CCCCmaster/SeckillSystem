package org.seckill.controller;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;

public class Test2 {

    public static Integer test() {
        Integer s = new Integer(10);
        try {
            s = new Integer(11);
            return s ;
        } catch (IndexOutOfBoundsException e) {
            s = new Integer(12);
            return s ;
        } finally {
           s = new Integer(13);
        }
    }
    public static void main(String[] args){
        System.out.println(test());
    }

}
