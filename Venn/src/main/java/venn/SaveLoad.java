package venn;

import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

public class SaveLoad {
	
	
	static int counter;

	public static Iterable<DraggableText> loadData() {
		List<DraggableText> newTexts = new ArrayList<DraggableText>();
		String path = "";
		FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        path = selectedFile.getPath();
        File file = new File(path);
        System.out.println(file.length());
        BufferedReader br;
        DraggableText newTxt;
        Color c;
        try {
        	br = new BufferedReader(new FileReader(file));
        	String st;
        	String Desc = "";
        	if(!(file.length() == 0)) {
	        	while((st = br.readLine()) != null){
	        		String [] temp = st.split("\\s+");
	        		
	        		c = Color.web(temp[3]);
	        		newTxt = new DraggableText(temp[0], c , 400 );
	        		newTxt.setFont(Font.font("Roboto Slab", FontWeight.NORMAL, 15));
					newTxt.getStyleClass().add("createdText");
					Double x = Double.valueOf(temp[1]);
					Double y =Double.valueOf(temp[2]);
					System.out.println("x: "+ x);
					System.out.println("y: " + y);
					newTxt.setTranslateX(x);
					newTxt.setTranslateY(y);
					for(int i = 4; i < temp.length; i++) {
						Desc += temp[i];
						Desc += " ";
					}
					newTxt.setDescription(Desc);
					Desc = "";
					newTexts.add(newTxt);  
					VennController.upload = true;
				}
        	}
        	else {
        		Alert a = new Alert(AlertType.INFORMATION);
    			a.setTitle("Load file error");
    			a.setHeaderText("File is empty or format error!");
    			a.showAndWait();
        	}
        }catch (Exception e){
        	e.printStackTrace();
        }
        return newTexts;
	}
	public static void saveData() throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt, extensions)","*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(null);
		FileWriter fileWriter;
        
        try {
			fileWriter = new FileWriter(file,false);
			fileWriter.write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			for(DraggableText a:VennController.entries) {
				String text = a.getText() + " " + a.getBoundsInParent().getMinX() + " " + a.getBoundsInParent().getMinY() + " ";
				text += a.getColor()+ " " + a.getTooltip().getText()+"\n"; 
				SaveFile(text, file);
			}
		BufferedReader rd = new BufferedReader(new FileReader(file));
		try {
			System.out.println("\n"+rd.readLine());
		}
		catch(Exception ex){
			
		}
	}
	
	public static String exportData() throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(null);
		if(file != null){
	        try {
	            //Pad the capture area
	            WritableImage writableImage = Main.s.snapshot(null);
	            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
	            //Write the snapshot to the chosen file
	            ImageIO.write(renderedImage, "png", file);
	        } catch (IOException ex) { ex.printStackTrace(); }
	    }
		
//		for(DraggableText a:VennController.entries) {
//			String text = a.getText() + " " + a.getTranslateX() + " " + a.getTranslateX() + " ";
//			text += a.getColor()+ " " + a.getTooltip().getText()+"\n"; 
//			SaveFile(a.getText() + "\n", file);
//		}
//		BufferedReader rd = new BufferedReader(new FileReader(file));
//		try {
//			System.out.println("\n"+rd.readLine());
//		}
//		catch(Exception ex){
//			
//		}
		return " ";
	}
	public static void loadAnswers(ArrayList<String> s1, ArrayList<String> s2) {
		String path = "";
		FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        try {
        path = selectedFile.getPath();
        }
        catch(NullPointerException e){
        	System.out.println("Null pointer");
        }
        File file = new File(path);
        BufferedReader br;
		try {
			String st;
			int i = 0;
			br = new BufferedReader(new FileReader(file));

			while ( i < 2 && ((st = br.readLine()) != null)) {
				String [] temp = st.split("\\s+");
				if(i == 0 ) {
					Collections.addAll(s1, temp);
				}else {
					Collections.addAll(s2, temp);
				}
				i++;
			}
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Answer information");
			if(!s2.isEmpty() && !s1.isEmpty()) {
				a.setHeaderText("Answers have been set and saved.");
				Collections.sort(s1);
				Collections.sort(s2);
				VennController.upload = true;
			}else {
				a.setHeaderText("Answers couldn't been saved please try again.");
				VennController.upload = false;
			}
			a.showAndWait();
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void showAnswerLabels(ArrayList<String> answerSet1, ArrayList<String> answerSet2, double x, double y) {
		counter = 0;
		Alert a = new Alert(AlertType.ERROR);
		int i = 0; int j = 0;
		double k = 0;
		DraggableText newTxt;
		if(answerSet1.isEmpty() || answerSet2.isEmpty()) {
			a.setTitle("Answers could not be imported");
			a.setHeaderText("Answer sets have not been imported.");
			a.showAndWait();
		}
			VennController.entries.removeAll(VennController.entries);
		
			while(i < answerSet1.size() && j < answerSet2.size()) {
				k = Math.floor(Math.random() * 2);
				if(k == 1) {
					newTxt = new DraggableText(answerSet1.get(i), Color.WHITE, 400);
					i++;
				}else {
					newTxt = new DraggableText(answerSet2.get(j), Color.WHITE, 400);
					j++;
				}
				newTxt.setFont(Font.font("Roboto Slab", FontWeight.NORMAL, 15));
				newTxt.getStyleClass().add("createdText");
				findEmpty(newTxt, x, y);
				VennController.entries.add(newTxt);
				
				counter++;
			}
			while(j < answerSet2.size()) {
				newTxt = new DraggableText(answerSet2.get(j), Color.WHITE, 400);
				j++;
				newTxt.setFont(Font.font("Roboto Slab", FontWeight.NORMAL, 15));
				newTxt.getStyleClass().add("createdText");
				findEmpty(newTxt, x, y);
				VennController.entries.add(newTxt);
				
				counter++;
			}
			while(i < answerSet1.size()) {
				newTxt = new DraggableText(answerSet1.get(i), Color.WHITE, 400);
				i++;
				newTxt.setFont(Font.font("Roboto Slab", FontWeight.NORMAL, 15));
				newTxt.getStyleClass().add("createdText");
				findEmpty(newTxt, x, y);
				VennController.entries.add(newTxt);
				
				counter++;
			}
	}
	
	public static Iterable<DraggableText> captureData(double x, double y) {
		List<DraggableText> newTexts = new ArrayList<DraggableText>();
		String path = "";
		Color c = Color.WHITE;
		DraggableText newTxt;
		FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        try {
        path = selectedFile.getPath();
        }
        catch(NullPointerException e){
        	return null;      
        }
       
        File file = new File(path);
        BufferedReader br;
		try {
			String st;
			br = new BufferedReader(new FileReader(file));
			VennController.entries.removeAll(VennController.entries);
			while ((st = br.readLine()) != null) {

				  System.out.println(st);

				  	 newTxt = new DraggableText(st, c, 400);
					 newTxt.setFont(Font.font("Roboto Slab", FontWeight.NORMAL, 15));
					 newTxt.getStyleClass().add("createdText");
			
					 if(newTexts.size() != 0) {
						 DraggableText prev = newTexts.get(newTexts.size() - 1);
						 if(newTexts.size()/16.0 <=1) {
						 newTxt.setTranslateX(x);
						 newTxt.setTranslateY(prev.getBoundsInParent().getMaxY() + 30);
						 }
						 else if(newTexts.size()/16.0 ==2 || counter/16.0==3 || counter/16.0==4)
						 {
							 DraggableText prev1 = newTexts.get(15);
							 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 150*(counter/16.0-1));
							 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());
						 }
						 else if(counter/16.0 > 1 && counter/16.0 <2) {
							 DraggableText prev1 = newTexts.get((int)counter%16-1);
							 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 150);
							 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());
							 
						 }
						 else if(counter/16.0 > 2 && counter/16.0<3) {
							 DraggableText prev1 = newTexts.get((int)counter%16-1);
							 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 300);
							 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());
							 
						 }
						 else if(counter/16.0 > 3 && counter/16.0<4) {
							 DraggableText prev1 = newTexts.get((int)counter%16-1);
							 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 450);
							 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());							 
						 }
						 else if(counter/16.0 > 4 && counter/16.0<5) {
							 DraggableText prev1 = newTexts.get((int)counter%16-1);
							 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 600);
							 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());							 
						 }
					 }else {
					 newTxt.setTranslateX(x);

					 newTxt.setTranslateY(y);
					 }
					 
					 
					// findEmpty(newTxt, x, y);
					 newTexts.add(newTxt);
					 counter++; 
				}
	}    
		catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return newTexts;
	}
	private static void findEmpty(DraggableText newTxt, double x, double y) {
		// TODO Auto-generated method stub
		
		
		 if(VennController.entries.size() != 0) {
			 DraggableText prev = VennController.entries.get(VennController.entries.size() - 1);
			 if(VennController.entries.size()/16.0 <=1) {
			 newTxt.setTranslateX(x);
			 newTxt.setTranslateY(prev.getBoundsInParent().getMaxY() + 30);
			 }
			 else if(VennController.entries.size()/16.0 ==2 || counter/16.0==3 || counter/16.0==4)
			 {
				 DraggableText prev1 = VennController.entries.get(15);
				 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 150*(counter/16.0-1));
				 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());
			 }
			 else if(counter/16.0 > 1 && counter/16.0 <2) {
				 DraggableText prev1 = VennController.entries.get((int)counter%16-1);
				 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 150);
				 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());
				 
			 }
			 else if(counter/16.0 > 2 && counter/16.0<3) {
				 DraggableText prev1 = VennController.entries.get((int)counter%16-1);
				 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 300);
				 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());
				 
			 }
			 else if(counter/16.0 > 3 && counter/16.0<4) {
				 DraggableText prev1 = VennController.entries.get((int)counter%16-1);
				 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 450);
				 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());							 
			 }
			 else if(counter/16.0 > 4 && counter/16.0<5) {
				 DraggableText prev1 = VennController.entries.get((int)counter%16-1);
				 newTxt.setTranslateX(prev1.getBoundsInParent().getMinX() + 600);
				 newTxt.setTranslateY(prev1.getBoundsInParent().getMaxY());							 
			 }
		 }else {
		 newTxt.setTranslateX(x);

		 newTxt.setTranslateY(y);
		 }
	}
	
	private static void SaveFile(String content, File file){
        try {
            FileWriter fileWriter;
            
            fileWriter = new FileWriter(file,true);
            
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
           
        }
          
    }	
	
}
