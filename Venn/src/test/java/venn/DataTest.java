package venn;

import static org.junit.Assert.*;
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

public class DataTest extends ApplicationTest{

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
		
	@Test
	public void testImportData() throws InterruptedException {
		clickOn("#mFile");
		Thread.sleep(1000);
		type(KeyCode.TAB);
		Thread.sleep(1000);
		type(KeyCode.ENTER);
		Thread.sleep(1000);	
		type(KeyCode.TAB);
		Thread.sleep(1000);	
		type(KeyCode.TAB);
		Thread.sleep(1000);	
		type(KeyCode.ENTER);
		Thread.sleep(1000);	
		VennController.entries.removeAll(VennController.entries);	
	}
	
	
	@Test
	public void testExportData() throws InterruptedException {
		clickOn("#mFile");
		Thread.sleep(1000);
		type(KeyCode.TAB);
		Thread.sleep(1000);
		type(KeyCode.TAB);
		Thread.sleep(1000);
		type(KeyCode.ENTER);
		Thread.sleep(1000);	
		type(KeyCode.TAB);
		Thread.sleep(1000);	
		type(KeyCode.TAB);
		Thread.sleep(1000);	
		type(KeyCode.TAB);
		Thread.sleep(1000);	
		type(KeyCode.TAB);
		Thread.sleep(1000);	
		type(KeyCode.ENTER);
		Thread.sleep(1000);	
		VennController.entries.removeAll(VennController.entries);	
	
	}
	
	@After
	public void tearDown() throws TimeoutException
	{
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}

}
