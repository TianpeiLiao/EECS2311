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
		Thread.sleep(1000);
		clickOn("#name");
		write("Test - 0" );
		for(int i = 1; i < 5 ;i++) {
			clickOn("#create");
			clickOn("#name");
			write("Test - " + String.valueOf(i));
			
		}
		
		clickOn("#create");
		Thread.sleep(1000);
		type(KeyCode.ESCAPE);
		Thread.sleep(1000);	
		drag(VennController.entries.get(0)).dropTo(VennController.entries.get(1));
		Thread.sleep(1000);	
		WaitForAsyncUtils.waitForFxEvents();
		VennController.entries.removeAll(VennController.entries);
		
	}

	@Test 
	public void testDraggedEntry() throws InterruptedException {
		
		clickOn("#newEntry");
		Thread.sleep(1000);
		clickOn("#name");
		Thread.sleep(1000);
		write("ABCDEF");
		Thread.sleep(1000);
		clickOn("#create");
		Thread.sleep(1000);
		type(KeyCode.ENTER);
		Thread.sleep(1000);
		type(KeyCode.ESCAPE);
		Thread.sleep(1000);
		clickOn(VennController.entries.get(0));
		Thread.sleep(1000);
		drag(VennController.entries.get(0)).dropTo(800, 700);
		Thread.sleep(1000);
		drag(VennController.entries.get(0)).dropTo(950, 700);
		Thread.sleep(1000);
		drag(VennController.entries.get(0)).dropTo(1150, 700);
		Thread.sleep(1000);
		drag(VennController.entries.get(0)).dropTo(1150, 300);
		Thread.sleep(1000);
		
		double newX = VennController.entries.get(1).getBoundsInParent().getMinX();
		double newY = VennController.entries.get(1).getBoundsInParent().getMinX();
		
		drag(VennController.entries.get(0)).dropTo(VennController.entries.get(1)).dropTo(newX, newY);
		Thread.sleep(1000);
		VennController.entries.removeAll(VennController.entries);	
	}
	
	@Test
	public void boundaryTest() throws InterruptedException {
		clickOn("#newEntry");
		Thread.sleep(1000);
		clickOn("#name");
		write("Test - 0" );
		
		for(int i = 1; i < 5 ;i++) {
			clickOn("#create");
			clickOn("#name");
			write("Test - " + String.valueOf(i));
		}
		clickOn("#create");
		Thread.sleep(1000);
		type(KeyCode.ESCAPE);
		Thread.sleep(1000);
		
		drag(VennController.entries.get(0)).dropTo(-Main.sWidth, 150);
		drag(VennController.entries.get(1)).dropTo(Main.sWidth + 500, 150);
		drag(VennController.entries.get(2)).dropTo(500, -500);
		drag(VennController.entries.get(3)).dropTo(500, 1400);
		Thread.sleep(1000);
		
		
		WaitForAsyncUtils.waitForFxEvents();
		VennController.entries.removeAll(VennController.entries);
		}
	
	@Test
	public void circleTest() throws InterruptedException {
		clickOn("#newEntry");
		Thread.sleep(1000);
		clickOn("#name");
		write("Test - 0" );
		for(int i = 1; i < 5 ;i++) {
			clickOn("#create");
			clickOn("#name");
			write("Test - " + String.valueOf(i));
		}
		clickOn("#create");
		Thread.sleep(1000);
		type(KeyCode.ESCAPE);
		Thread.sleep(1000);
		
		drag(VennController.entries.get(0)).dropTo(700, 650);
		drag(VennController.entries.get(1)).dropTo(700, 650);
		drag(VennController.entries.get(2)).dropTo(950, 650);
		drag(VennController.entries.get(3)).dropTo(1200, 650);
		drag(VennController.entries.get(4)).dropTo(1200, 650);
		Thread.sleep(1000);
		
		
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
