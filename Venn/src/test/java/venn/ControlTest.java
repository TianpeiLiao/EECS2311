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

public class ControlTest extends ApplicationTest {

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
	public void multipleEntries() throws InterruptedException {
		clickOn("#newEntry");
		Thread.sleep(cout);
		clickOn("#name");
		write("Test - 0" );
		for(int i = 1; i < 5 ;i++) {
			clickOn("#create");
			clickOn("#name");
			write("Test - " + String.valueOf(i));
			
		}
		
		clickOn("#create");
		Thread.sleep(cout);
		type(KeyCode.ESCAPE);
		Thread.sleep(cout);	
		drag(VennController.entries.get(0)).dropTo(VennController.entries.get(1));
		Thread.sleep(cout);	
		WaitForAsyncUtils.waitForFxEvents();
		VennController.entries.removeAll(VennController.entries);
		
	}
	

	@Test 
	public void testDraggedEntry() throws InterruptedException {
		
		clickOn("#newEntry");
		Thread.sleep(cout);
		clickOn("#name");
		Thread.sleep(cout);
		write("ABCDEF");
		Thread.sleep(cout);
		clickOn("#create");
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		Thread.sleep(cout);
		type(KeyCode.ESCAPE);
		Thread.sleep(cout);
		clickOn(VennController.entries.get(0));
		Thread.sleep(cout);
		drag(VennController.entries.get(0)).dropTo(800, 700);
		Thread.sleep(cout);
		drag(VennController.entries.get(0)).dropTo(950, 700);
		Thread.sleep(cout);
		drag(VennController.entries.get(0)).dropTo(1150, 700);
		Thread.sleep(cout);
		drag(VennController.entries.get(0)).dropTo(1150, 300);
		Thread.sleep(cout);
	
	}
	

	@Test
	public void boundaryTest() throws InterruptedException {
		clickOn("#newEntry");
		Thread.sleep(cout);
		clickOn("#name");
		write("Test - 0" );
		
		for(int i = 1; i < 5 ;i++) {
			clickOn("#create");
			clickOn("#name");
			write("Test - " + String.valueOf(i));
		}
		clickOn("#create");
		Thread.sleep(cout);
		type(KeyCode.ESCAPE);
		Thread.sleep(cout);
		drag(VennController.entries.get(0)).dropTo(-100, 150);
		Thread.sleep(cout);
		drag(VennController.entries.get(1)).dropTo(1500, 150);
		Thread.sleep(cout);
		drag(VennController.entries.get(2)).dropTo(500, -500);
		Thread.sleep(cout);
		drag(VennController.entries.get(3)).dropTo(500, 1400);
		Thread.sleep(cout);
		WaitForAsyncUtils.waitForFxEvents();
		VennController.entries.removeAll(VennController.entries);
	}
	
	@Test
	public void circleTest() throws InterruptedException {
		clickOn("#newEntry");
		Thread.sleep(cout);
		clickOn("#name");
		write("Test - 0" );
		for(int i = 1; i < 5 ;i++) {
			clickOn("#create");
			clickOn("#name");
			write("Test - " + String.valueOf(i));
		}
		clickOn("#create");
		Thread.sleep(cout);
		type(KeyCode.ESCAPE);
		Thread.sleep(cout);
		
		drag(VennController.entries.get(0)).dropTo(700, 650);
		drag(VennController.entries.get(1)).dropTo(700, 650);
		drag(VennController.entries.get(2)).dropTo(950, 650);
		drag(VennController.entries.get(3)).dropTo(1200, 650);
		drag(VennController.entries.get(4)).dropTo(1200, 650);
		Thread.sleep(cout);
		
		
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
