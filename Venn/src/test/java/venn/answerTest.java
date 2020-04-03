package venn;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
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

public class answerTest extends ApplicationTest{
	
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
	
	
	
	@After
	public void tearDown() throws TimeoutException
	{
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}
}
