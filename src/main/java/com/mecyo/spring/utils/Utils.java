package com.mecyo.spring.utils;
import java.io.File;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Utils {

	public static String getDataDir(Class<?> c) {
		File dir = new File(System.getProperty("user.dir"));
		dir = new File(dir, "src");
		dir = new File(dir, "main");
		dir = new File(dir, "resources");

		for (String s : c.getName().split("\\.")) {
			dir = new File(dir, s);
		}

		System.out.println("Using data directory: " + dir.toString());
		return dir.toString() + File.separator;
	}

	public static String getSharedDataDir(Class<?> c) {
		File dir = new File(System.getProperty("user.dir"));
		dir = new File(dir, "src");
		dir = new File(dir, "main");
		dir = new File(dir, "resources");

		return dir.toString() + File.separator;
	}

	public static String getUserName() {
		return ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}

}