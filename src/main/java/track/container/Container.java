package track.container;

import java.lang.reflect.Field;
import java.util.*;

import track.container.config.*;

/**
 * Основной класс контейнера
 * У него определено 2 публичных метода, можете дописывать свои методы и конструкторы
 */
public class Container {
    private HashMap<String, Object> objById;
    private HashMap<String, Object> objByClassName;
    private HashMap<String, ArrayList<String>> familyGraph;

    private void initiateContainer(List<Bean> beans) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
            for (Bean bean : beans) {
                Class klass = Class.forName(bean.getClassName());
                //Constructor konstr = klass.getConstructor();
                Object object;
                if (objById.containsKey(bean.getId())) {
                    object = objById.get(bean.getId());
                } else {
                    object = klass.newInstance();
                }
                Map<String, Property> properties = bean.getProperties();
                Field[] fields = klass.getDeclaredFields();
                for (Field field : fields) {
                    if (properties.containsKey(field.getName())) {
                        field.setAccessible(true);
                        if (properties.get(field.getName()).getType() == ValueType.VAL) {
                            int value = Integer.parseInt(properties.get(field.getName()).getValue());
                            field.set(object, value);
                            ArrayList<String> childList = new ArrayList<>();
                            if (familyGraph.containsKey(bean.getId())) {
                                childList = familyGraph.get(bean.getId());
                                childList.add(properties.get(field.getName()).getValue());
                            } else {
                                childList.add(properties.get(field.getName()).getValue());
                                familyGraph.put(bean.getId(), childList);
                            }
                        } else {
                            if (objById.containsKey(properties.get(field.getName()).getValue())) {
                                field.set(object, objById.get(properties.get(field.getName()).getValue()));
                            } else {
                                String className = properties.get(field.getName()).getName();
                                Class child = Class.forName("track.container.beans." + className.substring(0, 1).toUpperCase() + className.substring(1));
                                Object childObj = child.newInstance();
                                field.set(object, childObj);
                                String childId = properties.get(field.getName()).getValue();
                                objById.put(childId, childObj);
                            }
                        }
                    }
                }
                objById.putIfAbsent(bean.getId(), object);
                objByClassName.putIfAbsent(bean.getClassName(), object);
            }

    }

    public boolean cycleCheck() {
        int cycles = 1;
        for (int i = 0; i < cycles; i++) {
            for (Map.Entry<String, ArrayList<String>> entry : familyGraph.entrySet()) {
                for (String child : entry.getValue()) {
                    if (entry.getKey().equals(child)) {
                        return true;
                    } else if (familyGraph.containsKey(child)) {
                        ArrayList<String> childList = familyGraph.get(child);
                        ArrayList<String> parentList = familyGraph.get(entry.getKey());
                        if (!childList.isEmpty()) {
                            int indicator = 0;
                            for (String grandChild : childList) {
                                if (!parentList.contains(grandChild)) {
                                    indicator += 1;
                                    parentList.add(grandChild);
                                }
                            }
                            if (indicator > 0) {
                                cycles += 1;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    // Реализуйте этот конструктор, используется в тестах!

    public Container(List<Bean> beans) throws InvalidConfigurationException{
        objById = new HashMap<>();
        objByClassName = new HashMap<>();
        familyGraph = new HashMap<>();
        try {
            initiateContainer(beans);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException except) {
            except.printStackTrace();
        }
        if (cycleCheck()) {
            throw new InvalidConfigurationException("Cycles in configuration");
        }
    }

    /**
     * Вернуть объект по имени бина из конфига
     * Например, Car car = (Car) container.getById("carBean")
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
