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

	private int cout = AllVennTest.time;
	private int wout = AllVennTest.wtime;
	
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
	public void testGetData()  throws InterruptedException
	{
		for(int i=0; i<2; i++) {
			clickOn("#newEntry");
			Thread.sleep(cout);
			clickOn("#create");
			Thread.sleep(cout);
			clickOn("#name");
			Thread.sleep(cout);
			write("ABCDEF");
			Thread.sleep(cout);
			clickOn("#cp").type(KeyCode.TAB).type(KeyCode.TAB).type(KeyCode.DOWN).type(KeyCode.DOWN);
			Thread.sleep(cout);
			clickOn("#cornerRadi").type(KeyCode.TAB).type(KeyCode.RIGHT);
			Thread.sleep(cout);
			clickOn("#description");
			Thread.sleep(cout);
			write("This is a sample1 test description for above entry");
			Thread.sleep(cout);
			clickOn("#create");
			Thread.sleep(cout);
			type(KeyCode.ENTER);
			Thread.sleep(cout);
		}
		type(KeyCode.ESCAPE);
		Thread.sleep(3000);
		type(KeyCode.Z);
		Thread.sleep(3000);
		type(KeyCode.X);
		Thread.sleep(3000);
		
		WaitForAsyncUtils.waitForFxEvents();
		VennController.entries.removeAll(VennController.entries);
	}
	
	@Test
	public void testEditData()  throws InterruptedException
	{
		clickOn("#newEntry");
		Thread.sleep(cout);
		clickOn("#create");
		Thread.sleep(cout);
		clickOn("#name");
		Thread.sleep(cout);
		write("ABCDEF");
		Thread.sleep(cout);
		clickOn("#cp").type(KeyCode.TAB).type(KeyCode.TAB).type(KeyCode.DOWN).type(KeyCode.DOWN);
		Thread.sleep(cout);
		clickOn("#cornerRadi").type(KeyCode.TAB).type(KeyCode.RIGHT);
		Thread.sleep(cout);
		clickOn("#description");
		Thread.sleep(cout);
		write("This is a sample1 test description for above entry");
		Thread.sleep(cout);
		clickOn("#create");
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		Thread.sleep(cout);
		type(KeyCode.ESCAPE);
		doubleClickOn(VennController.entries.get(0));
		Thread.sleep(cout);
		clickOn("#tf");
		Thread.sleep(cout);
		write("TEST");
		type(KeyCode.ENTER);
		Thread.sleep(cout);
		clickOn("#cp").type(KeyCode.TAB).type(KeyCode.TAB).type(KeyCode.DOWN).type(KeyCode.DOWN);
		Thread.sleep(cout);
		clickOn("#sd").type(KeyCode.TAB).type(KeyCode.RIGHT);
		Thread.sleep(cout);
		clickOn("#apply");
		Thread.sleep(cout);	
		WaitForAsyncUtils.waitForFxEvents();
		VennController.entries.removeAll(VennController.entries);
	
	}
	
	@Test
	public void testdDeleteEntry() throws InterruptedException {
		clickOn("#newEntry");
		Thread.sleep(cout);
		clickOn("#name");
		Thread.sleep(cout);
		write("ABCDEF");
		Thread.sleep(cout);
		clickOn("#create");
		Thread.sleep(cout);
		type(KeyCode.ESCAPE);
		Thread.sleep(cout);
		drag(VennController.entries.get(0)).dropTo("#dlt");	
		Thread.sleep(cout);
		type(KeyCode.Z);
		Thread.sleep(cout);
		type(KeyCode.X);
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
