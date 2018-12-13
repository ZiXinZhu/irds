package test.com.tinelion.irds.tool; 

import com.tinelion.irds.picture.Listener;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* RealListener Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 9, 2018</pre> 
* @version 1.0 
*/ 
public class RealListenerTest { 

@Before
public void before() throws Exception {

} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: contextInitialized(ServletContextEvent sce) 
* 
*/ 
@Test
public void testContextInitialized() throws Exception {
    Listener listener=new Listener();
    listener.run();
} 

/** 
* 
* Method: contextDestroyed(ServletContextEvent sce) 
* 
*/ 
@Test
public void testContextDestroyed() throws Exception { 
//TODO: Test goes here... 
} 


} 
