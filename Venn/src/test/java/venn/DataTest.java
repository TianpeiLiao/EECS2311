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
	public void testImportData() throws InterruptedException {
		clickOn("#mFile");
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
	public void testExportData() throws InterruptedException 
	{	
		addLabels();
		clickOn("#mFile");
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
	public void AboutTest() throws InterruptedException 
	{
		clickOn("#hFile");
		Thread.sleep(cout);
		type(KeyCode.TAB);
		Thread.sleep(cout);
		type(KeyCode.ENTER);
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
