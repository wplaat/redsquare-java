package nl.plaatsoft.redsquare.ui;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import java.text.SimpleDateFormat;
import java.util.Iterator;

import nl.plaatsoft.redsquare.tools.MyButton;
import nl.plaatsoft.redsquare.tools.MyImageView;
import nl.plaatsoft.redsquare.tools.MyLabel;
import nl.plaatsoft.redsquare.tools.MyPanel;
import nl.plaatsoft.redsquare.tools.Score;
import nl.plaatsoft.redsquare.tools.ScoreLocal;

/**
 * The Class HighScore1.
 * 
 * @author wplaat
 */
public class HighScore1 extends MyPanel {
		   
	/** The Constant formatter. */
	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
	
	/** The y. */
	private int y;
	
	/** The lines. */
	private int lines; 
		
	/**
	 * Draw.
	 */
	public void draw() {	
		
		Image image1 = new Image("images/background1.png");
    	BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
    	BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    	Background background = new Background(backgroundImage);
		setBackground(background);
		
		y=0;    
		getChildren().add (new MyLabel(0, y, "Personal High Score", 50, "white", "-fx-font-weight: bold;"));		
		                
    	y+=60;    	
    	getChildren().add(new MyLabel(30, y, "Nr", 30));
    	getChildren().add(new MyLabel(80, y, "Date", 30));
		getChildren().add(new MyLabel(300, y, "Score", 30));	
		getChildren().add(new MyLabel(400, y, "Level", 30));
		getChildren().add(new MyLabel(490, y, "Awards", 30));	
		y=y+20;
				
		lines=1;
    	Iterator<Score> iter = ScoreLocal.getScore().iterator();    	
		while (iter.hasNext()) {
			y+=20;
			Score score = iter.next();	
			getChildren().add(new MyLabel(30, y, ""+lines, 20));					
			getChildren().add(new MyLabel(80, y, formatter.format(score.getTimestamp()), 20));
			getChildren().add(new MyLabel(300, y, ""+score.getScore(), 20));	
			getChildren().add(new MyLabel(400, y, ""+score.getLevel(), 20));	
			
			if (lines<6) {
				for (int x=0; x<(6-lines); x++) {
					getChildren().add( new MyImageView(470+(x*25),y-20, "images/star.png", 0.4));
				}
			}
			
			if (++lines>15) {
				break;
			}
		}
		
		getChildren().add( new MyButton(230, 420, "Next", 18, Navigator.GLOBAL_HIGHSCORE));				
	}
}
