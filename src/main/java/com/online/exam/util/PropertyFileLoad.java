/*
 * 
 */
package com.online.exam.util;

import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

// TODO: Auto-generated Javadoc
/**
 * The Class PortalPropertyLoad.
 */
public final class PropertyFileLoad {
	
	/** The prop resource bundle. */
	private static PropertyResourceBundle propResourceBundle;

	/**
	 * Builds the.
	 */
	protected void build() {
		propResourceBundle = (PropertyResourceBundle) ResourceBundle.getBundle("file");
	}

	/**
	 * Instantiates a new portal property load.
	 */
	private PropertyFileLoad() {
		build();
	}

	/** The instance. */
	private static PropertyFileLoad instance = new PropertyFileLoad();

	/**
	 * Gets the single instance of PortalPropertyLoad.
	 * 
	 * @return single instance of PortalPropertyLoad
	 */
	public static PropertyFileLoad getInstance() {
		if (null == instance) {
			instance = new PropertyFileLoad();
		}

		return instance;
	}

	/**
	 * Gets the string.
	 * 
	 * @param name
	 *            the name
	 * @return the string
	 */
	public String getString(final String name) {
		return PropertyFileLoad.propResourceBundle.getString(name);
	}

	/**
	 * Gets the keys.
	 * 
	 * @return the keys
	 */
	@SuppressWarnings("rawtypes")
	public Enumeration getKeys() {
		return PropertyFileLoad.propResourceBundle.getKeys();
	}
}
