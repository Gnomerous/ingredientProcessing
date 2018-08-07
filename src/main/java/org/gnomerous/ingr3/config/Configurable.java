package org.gnomerous.ingr3.config;

// GNOMEROUS
import org.gnomerous.ingr3.config.ConfigLoader.ConfigProperties;

/**
 * A {@link Configurable} is an interface that can be used to handle
 * objects within the application that need access to the configuration.
 * 
 * @author Marshal J. Dickey
 *
 */
public interface Configurable {

    /**
     * Use the configuration passed in to configure the current {@link Configurable}.
     * 
     * @param configuration
     *          The {@link ConfigProperties} that will be used as the configuration.
     */
    public void configure(ConfigProperties configuration);
}
