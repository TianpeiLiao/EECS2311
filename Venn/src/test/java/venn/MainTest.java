package venn;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;
//import org.testfx.framework.junit.ApplicationTest;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class MainTest extends ApplicationTest{
	
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
			Thread.sleep(5000);
	}

	@After
	public void tearDown() throws Exception
	{
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}
	
	@Test
	public void testGetData()  throws InterruptedException
	{
		clickOn("#newEntry");
		Thread.sleep(1000);
		clickOn("#name");
		Thread.sleep(1000);
		write("ABCDEF");
		clickOn();
		Thread.sleep(1000);
	}
	
	

}
