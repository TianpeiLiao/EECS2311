package venn;

import static org.junit.Assert.*;
import static org.junit.Assert.*;

import java.net.URL;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeoutException;
import org.junit.*;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.embed.swing.JFXPanel;
import org.testfx.util.WaitForAsyncUtils;
import java.io.File;

public class SaveLoadTest extends ApplicationTest{

	private int cout = AllVennTests.time;
	
	@Before
	public void testA() throws InterruptedException
	{
		Thread thread = new Thread(new Runnable() {
			@Override 
			public void run() {
			new JFXPanel();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try 
						{
							new Main().start(new Stage());
						} 
						catch (Exception e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			});
			thread.start();
			Thread.sleep(3000);
	}
/*	
	@Test
	public void saveTest() throws InterruptedException 
	{
		clickOn("#mFile");
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		type(KeyCode.TAB);
		Thread.sleep(cout);	
		type(KeyCode.TAB);
		Thread.sleep(cout);	
		type(KeyCode.TAB);
		Thread.sleep(cout);	
		type(KeyCode.TAB);
		Thread.sleep(cout);	
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		VennController.entries.removeAll(VennController.entries);	
	}
*/	
	@Test
	public void loadTest() throws InterruptedException 
	{
		clickOn("#mFile");
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		//write("C:\\Users\\jpras\\OneDrive\\Desktop\\TestFiles\\saved file");
		URL url = getClass().getResource("importSample.txt");
		File file = new File(url.getPath());
		
		
		
		Thread.sleep(cout);	
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		
		VennController.entries.removeAll(VennController.entries);	
	}
	
/*	
	@Test
	public void getLabelsTest() throws InterruptedException
	{
		clickOn("#ansLabels1");
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		VennController.entries.removeAll(VennController.entries);	
	}
	
	@Test
	public void submitTest() throws InterruptedException
	{
		clickOn("#submit");
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		VennController.entries.removeAll(VennController.entries);	
	}
	
	@Test
	public void addAnswersTest() throws InterruptedException
	{
		clickOn("#eFile");
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		VennController.entries.removeAll(VennController.entries);		
		
	}
	
	@Test
	public void deleteAnswersTest() throws InterruptedException
	{
		clickOn("#eFile");
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		VennController.entries.removeAll(VennController.entries);	
	}
	
	@Test
	public void circleNameTest() throws InterruptedException
	{
		clickOn("#eFile");
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		VennController.entries.removeAll(VennController.entries);	
	}
*/	
	@After
	public void tearDown() throws TimeoutException
	{
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}
}