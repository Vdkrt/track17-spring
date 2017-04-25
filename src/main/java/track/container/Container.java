package track.container;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import track.container.config.Bean;
import track.container.config.InvalidConfigurationException;
import track.container.config.Property;
import track.container.config.ValueType;

/**
 * Основной класс контейнера
 * У него определено 2 публичных метода, можете дописывать свои методы и конструкторы
 */
public class Container {
    private HashMap<String, Object> objById;
    private HashMap<String, Object> objByClassName;
    private boolean cycle;

    private class Node {
        private Bean bean;
        private int parents, children;
        private boolean created;

        private Node(Bean bean) {
            this.bean = bean;
            this.parents = 0;
            this.children = 0;
            this.created = true;
        }

        private Node(Bean bean, int parents){
            this.bean = bean;
            this.parents = parents;
            this.children = 0;
            this.created = true;
        }
    }

    private Node head;

    // Реализуйте этот конструктор, используется в тестах!


        public Container(List<Bean> beans) throws InvalidConfigurationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        objById = new HashMap<>();
        objByClassName = new HashMap<>();
        for(Bean bean : beans){
            Node node = new Node(bean);

            Class<?> klass = Class.forName(bean.getClassName());
            Constructor<?> konstr = klass.getConstructor();
            Object object = konstr.newInstance();
            objById.putIfAbsent(bean.getId(), object);
            objByClassName.putIfAbsent(bean.getClassName(), object);
        }
    }

    /**
     *  Вернуть объект по имени бина из конфига
     *  Например, Car car = (Car) container.getById("carBean")
     */
    public Object getById(String id) {
        return objById.get(id);
    }

    /**
     * Вернуть объект по имени класса
     * Например, Car car = (Car) container.getByClass("track.container.beans.Car")
     */
    public Object getByClass(String className) {
        return objByClassName.get(className);
    }
}
