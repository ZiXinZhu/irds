package test.com.tinelion.irds.guard.process; 

import com.tinelion.irds.guard.process.GetFile;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.annotation.ApplicationScope;

/** 
* GetFile Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 11, 2018</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
public class GetFileTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: run() 
* 
*/ 
@Test
public void testRun() throws Exception {
    GetFile getFile=new GetFile();
    getFile.run();
} 


} 
