package com.github.melpis.board.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ContextLoadListener
 *
 */
public class ContextLoadListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextLoadListener() {
        // TODO Auto-generated constructor stub
    }
    

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
    	Integer a;
    	Map<String, String > dbInfoMap = new HashMap<String, String>();
    	String sDBdriver = sce.getServletContext().getInitParameter("dbDriver");
    	String sDBURI = sce.getServletContext().getInitParameter("dbURI");
    	String sDBUserId = sce.getServletContext().getInitParameter("dbUserId");
    	String sDBuserPw=sce.getServletContext().getInitParameter("dbUserPw");
    	dbInfoMap.put("dbDriver", sDBdriver);
    	dbInfoMap.put("dbURI", sDBURI);
    	dbInfoMap.put("dbUserId", sDBUserId);
    	dbInfoMap.put("dbUserPw",sDBuserPw);
    	
    	String className = sce.getServletContext().getInitParameter("boardManager");

    	Object obj = null;
	
    	
    	Constructor<?> constructor=null;
		
			try {
				constructor = Class.forName(className).getConstructor(java.util.Map.class);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			try {
				obj=constructor.newInstance(dbInfoMap);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	sce.getServletContext().setAttribute("boardManager", obj);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
    }
	
}
