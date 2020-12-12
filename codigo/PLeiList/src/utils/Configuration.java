package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;

import utils.Configuration;

public class Configuration {

		private static Configuration INSTANCE = null;
		private Properties prop = new Properties();
		
		/**
		 * Construtor
		 */
		private Configuration() {
			try {
				prop.load(new FileInputStream("config.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		/**
		 * Metodo para obter uma instancia de Configuration
		 * @return um objeto Configuration
		 */
		public static Configuration getInstance() {
			if (INSTANCE == null) { 
				 INSTANCE = new Configuration();
			}
			return INSTANCE;
		}
		
		public String getString(String chave) {
			return prop.getProperty(chave);
		}

		public String[] getStringArray(String chave) {
			return getString(chave).split(";");
		}
		
		
		@SuppressWarnings("unchecked")
		public <T> T createInstance(String className, T defaultObject) {
			Class<T> c = null;
			try {
				c = (Class<T>) Class.forName(className);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			T ins = defaultObject;
			try {
				Constructor<T> cons = c.getConstructor();
				ins = cons.newInstance();	
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return ins;
		}
/*
		@SuppressWarnings("unchecked")
		public <T> Iterable<T> getInstances(String chave, T defaultObject) {
			return (Iterable<T>) Arrays.stream(this.getStringArray(chave)).map((x) -> this.createInstance(x, defaultObject));
		}
*/
		public <T> List<T> getInstances(String chave, T defaultObject) {
			List<T> ls = new ArrayList<T>();
			String[] s = this.getStringArray(chave);
			for(String h : s){
				ls.add(this.createInstance(h, defaultObject));
			}
			return ls;
		}
		public <T> T createInstanceFromKey(String chave, T defaultObject) {
			String className = this.getString(chave);
			return this.createInstance(className, defaultObject);
		}
		
	}

