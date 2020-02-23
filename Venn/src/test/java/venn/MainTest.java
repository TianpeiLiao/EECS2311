package venn;

import static org.junit.Assert.*;

import java.util.concurrent.TimeoutException;

import org.junit.*;
import org.testfx.api.FxToolkit;
//import org.testfx.framework.junit.ApplicationTest;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.embed.swing.JFXPanel;
//import torgen.utils.FxRobotColourPicker;

public class MainTest extends ApplicationTest{
	
	
	ColorPicker picker;
	Slider slide;
	Button delete;
	Button addNew;
	
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

	/* Just a shortcut to retrieve widgets in the GUI. */
    public <T extends Node> T find(final String query) {
        /* TestFX provides many operations to retrieve elements from the loaded GUI. */
        return lookup(query).query();
    }
    
	@Before
	public void setUp()
	{
		addNew = find("#newEntry");
		delete = find("#dlt");
	}

/*	@Override
	public void start (Stage primaryStage) throws Exception
	{
	
		Parent mainNode = FXMLLoader.load(Main.class.getResource("App.fxml"));
		primaryStage.setScene(new Scene(mainNode));
		primaryStage.show();
		primaryStage.toFront();
		Thread.sleep(1000);
	}
*/
	
	@After
	public void tearDown() throws TimeoutException
	{
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}
	
	@Test
	public void testWidgets() throws Exception
	{
		final String errMsg = "One of the button cannot be retireved anymore!";
		assertNotNull(errMsg, addNew);
		assertNotNull(errMsg, delete);
		
	}
	
	@Test
	public void testGetData()  throws InterruptedException
	{
		clickOn("#newEntry");
		Thread.sleep(1000);
		clickOn("#name");
		Thread.sleep(1000);
		write("ABCDEF");
		Thread.sleep(1000);
		clickOn("#cp").type(KeyCode.TAB).type(KeyCode.TAB).type(KeyCode.DOWN).type(KeyCode.DOWN);
		Thread.sleep(1000);
		clickOn("#cornerRadi").type(KeyCode.TAB).type(KeyCode.RIGHT);
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
		clickOn("#name");
		Thread.sleep(1000);
		write("ABCDEF");
		Thread.sleep(1000);
		clickOn("#create");
		Thread.sleep(1000);
		type(KeyCode.ENTER);
		Thread.sleep(1000);
		type(KeyCode.ESCAPE);
		clickOn(VennController.entries.get(0));
		clickOn(VennController.entries.get(0));
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
	
	@Test 
	public void multipleEntries() throws InterruptedException {
		clickOn("#newEntry");
		Thread.sleep(1000);
		clickOn("#name");
		write("Test - 0" );
		for(int i = 1; i < 5 ;i++) {
			clickOn("#create");
			
			clickOn("#name").type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
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
	public void deleteEntry() throws InterruptedException {
		clickOn("#newEntry");
		Thread.sleep(1000);
		clickOn("#name");
		Thread.sleep(1000);
		write("ABCDEF");
		Thread.sleep(1000);
		clickOn("#create");
		Thread.sleep(1000);
		type(KeyCode.ESCAPE);
		drag(VennController.entries.get(0)).dropTo("#dlt");
		
		
		WaitForAsyncUtils.waitForFxEvents();
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
			clickOn("#name").type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			clickOn("#name");
			write("Test - " + String.valueOf(i));
		}
		clickOn("#create");
		Thread.sleep(1000);
		type(KeyCode.ESCAPE);
		Thread.sleep(1000);
		
		drag(VennController.entries.get(0)).dropTo(-Main.WIDTH, 150);
		drag(VennController.entries.get(1)).dropTo(Main.WIDTH + 500, 150);
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
			clickOn("#name").type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
			type(KeyCode.BACK_SPACE);
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
}
