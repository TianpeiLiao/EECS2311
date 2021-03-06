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

public class MainTest extends ApplicationTest{
	
	
	ColorPicker picker;
	Slider slide;
	Button delete;
	Button addNew;
	MouseButton btn;
	
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
	

	@Test
	public void testWidgets() throws Exception
	{
			final String errMsg = "One of the button cannot be retireved anymore!";
			assertNotNull(errMsg, addNew);
	}
	
	@After
	public void tearDown() throws TimeoutException
	{
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}

/*	
	@Test
	public void testExit() throws InterruptedException {
		
		
		clickOn("#mFile");
		Thread.sleep(1000);
		type(KeyCode.TAB);
		Thread.sleep(1000);
		type(KeyCode.TAB);
		Thread.sleep(1000);
		type(KeyCode.TAB);
		Thread.sleep(1000);
		type(KeyCode.TAB);
		Thread.sleep(1000);
		type(KeyCode.TAB);
		Thread.sleep(1000);
		type(KeyCode.ENTER);
		Thread.sleep(1000);
	
	}
	
  	@Before
	public void setUp()
	{
		addNew = find("#newEntry");
		delete = find("#dlt");
	}

	@Override
	public void start (Stage primaryStage) throws Exception
	{
	
		Parent mainNode = FXMLLoader.load(Main.class.getResource("App.fxml"));
		primaryStage.setScene(new Scene(mainNode));
		primaryStage.show();
		primaryStage.toFront();
		Thread.sleep(1000);
	}
*/
	
}
