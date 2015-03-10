package com.ezreb.alarm.dataTEMPNAME.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Config {

	@Target(ElementType.FIELD)
	public @interface Instance {
		
	}
	@Target(ElementType.METHOD)
	public @interface OnLoad {
		
	}
	@Target(ElementType.METHOD)
	public @interface OnExit {
		
	}
}