package configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigParser {

    private  Properties properties;

    public ConfigParser() {
        Properties property = new Properties();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db/liquibase.properties")){
            property.load(inputStream);
            this.properties = property;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyName){return properties.getProperty(propertyName);}

}
