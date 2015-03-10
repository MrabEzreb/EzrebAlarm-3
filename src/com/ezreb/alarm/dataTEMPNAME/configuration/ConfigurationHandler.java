package com.ezreb.alarm.dataTEMPNAME.configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class ConfigurationHandler {

	public static void run() {}
	static {
		Reflections ref = new Reflections(new ConfigurationBuilder()
	     .setUrls(ClasspathHelper.forPackage("com.ezreb"))
	     .setScanners(new SubTypesScanner(), 
	                  new TypeAnnotationsScanner(),
	                  new MethodAnnotationsScanner(),
	                  new FieldAnnotationsScanner())
	     .filterInputsBy(new FilterBuilder().includePackage("com.ezreb")));
		Set<Field> instance = ref.getFieldsAnnotatedWith(Config.Instance.class);
		for (Field field : instance) {
			try {
				final Configuration c = (Configuration) field.get(null);
				c.load();
				Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
					
					@Override
					public void run() {
						c.save();
					}
				}));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
