package venn;

import static org.junit.Assert.*;
import java.util.concurrent.TimeoutException;
import org.junit.*;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.embed.swing.JFXPanel;

public class EntryCustomizeTest extends ApplicationTest{

	private int time = 0;
	
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
	public void testGetData()  throws InterruptedException
	{
		clickOn("#newEntry");
		Thread.sleep(1000);
		clickOn("#create");
		Thread.sleep(1000);
		clickOn("#name");
		Thread.sleep(1000);
		write("ABCDEF");
		Thread.sleep(1000);
		clickOn("#cp").type(KeyCode.TAB).type(KeyCode.TAB).type(KeyCode.DOWN).type(KeyCode.DOWN);
		Thread.sleep(1000);
		clickOn("#cornerRadi").type(KeyCode.TAB).type(KeyCode.RIGHT);
		Thread.sleep(1000);
		clickOn("#description");
		Thread.sleep(1000);
		write("This is a sample1 test description for above entry");
		Thread.sleep(1000);
		clickOn("#create");
		Thread.sleep(1000);
		type(KeyCode.ENTER);
		Thread.sleep(1000);
		
		WaitForAsyncUtils.waitForFxEvents();
		VennController.entries.removeAll(VennController.entries);
	}
	
	@Test
	public void testEditData()  throws InterruptedException
	{
		clickOn("#newEntry");
		Thread.sleep(1000);
		clickOn("#create");
		Thread.sleep(1000);
		clickOn("#name");
		Thread.sleep(1000);
		write("ABCDEF");
		Thread.sleep(1000);
		clickOn("#cp").type(KeyCode.TAB).type(KeyCode.TAB).type(KeyCode.DOWN).type(KeyCode.DOWN);
		Thread.sleep(1000);
		clickOn("#cornerRadi").type(KeyCode.TAB).type(KeyCode.RIGHT);
		Thread.sleep(1000);
		clickOn("#description");
		Thread.sleep(1000);
		write("This is a sample1 test description for above entry");
		Thread.sleep(1000);
		clickOn("#create");
		Thread.sleep(1000);
		type(KeyCode.ENTER);
		Thread.sleep(1000);
		type(KeyCode.ESCAPE);
		doubleClickOn(VennController.entries.get(0));
		Thread.sleep(1000);
		clickOn("#tf");
		Thread.sleep(1000);
		write("TEST");
		type(KeyCode.ENTER);
		Thread.sleep(1000);
		clickOn("#cp").type(KeyCode.TAB).type(KeyCode.TAB).type(KeyCode.DOWN).type(KeyCode.DOWN);
		Thread.sleep(1000);
		clickOn("#sd").type(KeyCode.TAB).type(KeyCode.RIGHT);
		Thread.sleep(1000);
		clickOn("#apply");
		Thread.sleep(1000);

		clickOn(VennController.entries.get(1));
		clickOn(VennController.entries.get(1));
		clickOn("#cp").type(KeyCode.TAB).type(KeyCode.TAB).type(KeyCode.DOWN).type(KeyCode.DOWN);
		Thread.sleep(1000);
		clickOn("#sd").type(KeyCode.TAB).type(KeyCode.RIGHT);
		Thread.sleep(1000);
		clickOn("#apply");

		
		WaitForAsyncUtils.waitForFxEvents();
		VennController.entries.removeAll(VennController.entries);
	
	}
*/	
	@Test
	public void testdDeleteEntry() throws InterruptedException {
		clickOn("#newEntry");
		Thread.sleep(time);
		clickOn("#name");
		Thread.sleep(time);
		write("ABCDEF");
		Thread.sleep(time);
		clickOn("#create");
		Thread.sleep(time);
		type(KeyCode.ESCAPE);
		drag(VennController.entries.get(0)).dropTo("#dlt");
			
		WaitForAsyncUtils.waitForFxEvents();
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
