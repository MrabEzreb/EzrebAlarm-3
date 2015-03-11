package com.ezreb.alarm.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class OnStartHandler {

	public static void load() {
		Reflections searcher = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("com.ezreb")).setScanners(new MethodAnnotationsScanner()));
		Set<Method> toRun = searcher.getMethodsAnnotatedWith(OnStart.class);
		//System.out.println("Loading methods");
		for (Method method : toRun) {
			//System.out.println(method.getName()+" "+method.getDeclaringClass().toString()+" "+Modifier.isStatic(method.getModifiers()));
			if(Modifier.isStatic(method.getModifiers())) {
				try {
					method.invoke(null, (Object[]) null);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		//System.out.println("done loading methods");
	}
}
