package org.gnomerous.ingr3.config;

// JAVA
import java.util.Properties;

public interface ConfigLoader {

    public ConfigProperties getConfig(String location);
    
    public class ConfigProperties extends Properties {

        private static final long serialVersionUID = -2228637213538532807L; 

        private ConfigProperties() { 
            // Private Constructor so only ConfigLoaders can provide properties to configurable classes in this library
        }

        //TODO: Add more functionality as needed?
    }
}
