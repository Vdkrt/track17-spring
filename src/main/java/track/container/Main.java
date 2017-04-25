package track.container;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import track.container.beans.Car;
import track.container.beans.Gear;
import track.container.config.Bean;
import track.container.JsonConfigReader;
import track.container.Container;

import track.container.config.ConfigReader;
import track.container.config.InvalidConfigurationException;

/**
 *
 */
public class Main {

    public static void main(String[] args) throws InvalidConfigurationException, IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        /*

        ПРИМЕР ИСПОЛЬЗОВАНИЯ

         */

//        // При чтении нужно обработать исключение
//        ConfigReader reader = new JsonReader();
//        List<Bean> beans = reader.parseBeans("config.json");
//        Container container = new Container(beans);
//
//        Car car = (Car) container.getByClass("track.container.beans.Car");
//        car = (Car) container.getById("carBean");
        ConfigReader jsonConfig = new JsonConfigReader();
        List<Bean> list = jsonConfig.parseBeans(new File("src/main/resources/config.json"));
        System.out.print(list);

        Container container = new Container(list);
        Gear gear = (Gear) container.getByClass("track.container.beans.Gear");
        //Gear gear = (Gear) container.getById("gear");
        System.out.print(gear);


    }
}
