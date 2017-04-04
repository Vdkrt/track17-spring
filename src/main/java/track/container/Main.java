package track.container;

import java.io.File;
import java.io.IOException;
import java.util.List;
import track.container.config.Bean;
import track.container.JsonConfigReader;

import track.container.config.ConfigReader;
import track.container.config.InvalidConfigurationException;

/**
 *
 */
public class Main {

    public static void main(String[] args) throws InvalidConfigurationException, IOException{

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


    }
}
