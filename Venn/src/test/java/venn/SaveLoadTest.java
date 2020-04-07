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

	private int cout = AllVennTest.stime;
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
	
	public void addLabels() throws InterruptedException
	{
		for(int i=0; i<2; i++)
		{
			clickOn("#newEntry");
			Thread.sleep(cout);
			clickOn("#create");
			Thread.sleep(cout);
			clickOn("#name");
			Thread.sleep(cout);
			write("ABCDEF");
			Thread.sleep(cout);
			type(KeyCode.ENTER);
			Thread.sleep(cout);
		}
		type(KeyCode.ESCAPE);
		Thread.sleep(cout);
		
	}
	
	@Test
	public void saveTest() throws InterruptedException 
	{
		addLabels();
		clickOn("#mFile");
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		Thread.sleep(wout);		
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		VennController.entries.removeAll(VennController.entries);	
	}
	
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
		Thread.sleep(wout);	
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		VennController.entries.removeAll(VennController.entries);	
	}
	
	
	@Test
	public void getLabelsTest() throws InterruptedException
	{
		clickOn("#ansLabels1");
		Thread.sleep(wout);
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		
		drag(VennController.entries.get(0)).dropTo(800, 700);
		Thread.sleep(cout);
		drag(VennController.entries.get(1)).dropTo(850, 700);
		Thread.sleep(cout);
		drag(VennController.entries.get(2)).dropTo(1150, 700);
		Thread.sleep(cout);
		drag(VennController.entries.get(3)).dropTo(1200, 700);
		Thread.sleep(cout);
		clickOn("#submit");
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		
		drag(VennController.entries.get(3)).dropTo(1200, 300);
		Thread.sleep(cout);
		clickOn("#submit");
		Thread.sleep(cout);
		
		clickOn("#eFile");
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		Thread.sleep(wout);	
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		type(KeyCode.ENTER);
		
		clickOn("#eFile");
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
		type(KeyCode.ENTER);
		Thread.sleep(cout);	
			
		VennController.entries.removeAll(VennController.entries);	
	}
	
	@Test
	public void UndoRedoTest() throws InterruptedException
	{
		addLabels();
		Thread.sleep(cout);
		drag(VennController.entries.get(0)).dropTo(800, 700);
		Thread.sleep(cout);
		drag(VennController.entries.get(0)).dropTo(1150, 700);
		Thread.sleep(cout);
		type(KeyCode.Z);
		Thread.sleep(cout);
		type(KeyCode.X);
		Thread.sleep(cout);
		
	}
	
	@After
	public void tearDown() throws TimeoutException
	{
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}
}