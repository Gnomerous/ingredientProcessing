package org.gnomerous.ingr3.config;

// JAVA
import java.util.Properties;

/**
 * A ConfigLoader is used to pull and load a configuration file.
 * 
 * @author Marshal J. Dickey
 * 
 */
public interface ConfigLoader {

    /**
     * Pulls the configuration from the specified location.
     *
     * @param location
	 *          The location for the file (What is passed in depends on the implementation
     *          of the {@link ConfigLoader}.
     * @return {@link ConfigProperties}
     */
    public ConfigProperties getConfig(String location);

    /**
     * ConfigProperties is only returned by a {@link ConfigLoader} and will be used to
     * pull certain properties that are used throughout the application
     * 
     * @author Marshal J. Dickey
     *
     */
    public class ConfigProperties extends Properties {

        private static final long serialVersionUID = -2228637213538532807L;

        /**
         * Constructor for {@link ConfigProperties}
         */
        private ConfigProperties() {
            // Private Constructor so only ConfigLoaders can provide properties
            // to configurable classes in this library
        }

        // TODO: Add more functionality as needed?
    }
}
