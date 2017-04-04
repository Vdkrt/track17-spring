package track.container;

import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.util.List;

import track.container.config.Bean;
import track.container.config.BeanList;
import track.container.config.ConfigReader;
import track.container.config.InvalidConfigurationException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: Реализовать
 */
public class JsonConfigReader implements ConfigReader {

    @Override
    public List<Bean> parseBeans(File configFile) throws InvalidConfigurationException{
        ObjectMapper mapper = new ObjectMapper();
        BeanList beans = new BeanList();
        try {
            beans = mapper.readValue( configFile, BeanList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beans.getBeans();
    }
}
