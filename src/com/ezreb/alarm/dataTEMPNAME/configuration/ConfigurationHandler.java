package com.ezreb.alarm.dataTEMPNAME.configuration;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.ezreb.alarm.util.Logger;
import com.ezreb.alarm.util.OnStart;

public class ConfigurationHandler {

	@OnStart
	public static void run() {
		Reflections searcher = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("com.ezreb")).setScanners(new SubTypesScanner(), new TypeAnnotationsScanner()));
		Set<Class<?>> classes = searcher.getTypesAnnotatedWith(Config.class);
		for (final Class<?> class1 : classes) {
			Reflections searcher2 = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forClass(class1)).setScanners(new SubTypesScanner(), new TypeAnnotationsScanner(), new MethodAnnotationsScanner(), new FieldAnnotationsScanner()));
			Set<Field> instances = searcher2.getFieldsAnnotatedWith(Config.Instance.class);
			if(instances.size() == 0) {
				Logger.printErr("Could not find instance of configuration!", "Could not find @Config.Instance in class "+class1.getName()+", which is annotated with @Config.");
			} else if(instances.size() > 1) {
				Logger.printErr("Multiple instances of configuration!", "Found multple @Config.Instance in class "+class1.getName()+", which is annotated with @Config.");
			} else if(instances.size() == 1) {
				try {
					final Object object = ((Field) instances.toArray()[0]).get(null);
					Set<Method> onload = searcher2.getMethodsAnnotatedWith(Config.OnLoad.class);
					Set<Method> onexit = searcher2.getMethodsAnnotatedWith(Config.OnExit.class);
					if(onload.size() == 0) {
						Logger.printErr("Could not find onload method of configuration!", "Could not find @Config.OnLoad in class "+class1.getName()+", which is annotated with @Config.");
					} else if(onload.size() > 1) {
						Logger.printErr("Multiple onload methods of configuration!", "Found multple @Config.OnLoad in class "+class1.getName()+", which is annotated with @Config.");
					} else if(onload.size() == 1) {
						if(onexit.size() == 0) {
							Logger.printErr("Could not find OnExit method of configuration!", "Could not find @Config.OnExit in class "+class1.getName()+", which is annotated with @Config.");
						} else if(onexit.size() > 1) {
							Logger.printErr("Multiple OnExit methods of configuration!", "Found multple @Config.OnExit in class "+class1.getName()+", which is annotated with @Config.");
						} else if(onexit.size() == 1) {
							final Method loader = (Method) onload.toArray()[0];
							final Method exiter = (Method) onexit.toArray()[0];
							try {
								loader.invoke(class1.cast(object), (Object[]) null);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
							Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
								
								@Override
								public void run() {
									try {
										exiter.invoke(class1.cast(object), (Object[]) null);
									} catch (IllegalAccessException e) {
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										e.printStackTrace();
									}
								}
							}));
						}
					}
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
