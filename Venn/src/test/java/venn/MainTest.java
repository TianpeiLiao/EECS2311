package venn;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class MainTest {
	
//	@Override
//	public void start (Stage primaryStage) throws Exception
//	{
//		Parent mainNode = FXMLLoader.load(Main.class.getResource("App.fxml"));
//		primaryStage.setScene(new Scene(mainNode));
//		primaryStage.show();
//		primaryStage.toFront();
//	}
	
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
			Thread.sleep(10000);
		}
			
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
