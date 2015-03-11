package com.ezreb.testing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.ezreb.alarm.util.OnStart;

public class MainTest {

	public static void main(String[] args) {
		Reflections ref = new Reflections(new ConfigurationBuilder()
	     .setUrls(ClasspathHelper.forPackage("com.ezreb"))
	     .setScanners(new SubTypesScanner(), 
	                  new TypeAnnotationsScanner(),
	                  new MethodAnnotationsScanner())
	     .filterInputsBy(new FilterBuilder().includePackage("com.ezreb")));
		Set<Method> initMethods = ref.getMethodsAnnotatedWith(OnStart.class);
		for (Method method : initMethods) {
			System.out.println(method.getName()+" "+method.isAnnotationPresent(OnStart.class));
			try {
				Thread.sleep(5);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				System.err.println(method.invoke(method.getDeclaringClass().newInstance(), (Object[]) null));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String test() {
		return "test";
	}

}
