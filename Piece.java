// JAV3\ChessGame 

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class Piece
{
  private Image      image, scaleImage;
  private Image      selectedImage, selectedScaleImage;
  private PieceColor color;
  private PieceRole  role;
  private Rules      rules;
  private Point      position;
  private int        scaleWidth, scaleHeight;
  private boolean    marked;
  
  public Piece(PieceColor color, PieceRole role, Point position, int step)
  {
    this.color    = color;
    this.role     = role;
    this.position = position;
    
    this.rules = new Rules(role);

    scaleWidth = step;
    scaleHeight = step;

    loadImage(color, role);
  }
  
  private void loadImage(PieceColor color, PieceRole role)
  {
    ImageIcon imageIcon = new ImageIcon(Chess.imgs[color.ordinal()][role.ordinal()]);
    image = imageIcon.getImage();
    scaleImage = scaleImage(image, scaleWidth, scaleHeight);
    
    imageIcon = new ImageIcon(Chess.imgs[2][role.ordinal()]);
    selectedImage = imageIcon.getImage();
    selectedScaleImage = scaleImage(selectedImage, scaleWidth, scaleHeight);
  }
  
  public void setStep(int step)
  {
    scaleWidth = step;
    scaleHeight = step;
    
    scaleImage = scaleImage(image, step, step);
    selectedScaleImage = scaleImage(selectedImage, step, step);
  }
  
  private Image scaleImage(Image image, int width, int height)
  {
    return image.getScaledInstance(width,
                                   height,
                                   Image.SCALE_SMOOTH);
  }

  public void mark()
  {
    marked = true;
//    System.out.println(color + " " + role + " at " + 
//                       Board.getFieldName(position.x, position.y) + " marked");
  }
  
  public void unmark()
  {
    marked = false;
//    System.out.println(color + " " + role + " unmarked");
  }
  
  public boolean isMarked()
  {
    return marked;
  }
  
  public int getXPos() 
  {
    return position.x;
  }

  public int getYPos() 
  {
    return position.y;
  }
  
  public Point getPosition()
  {
    return position;
  }
    
  public Image getScaleImage() 
  {
    return scaleImage;
  }

  public Image getSelectedScaleImage() 
  {
    return selectedScaleImage;
  }
  
  public PieceColor getColor()
  {
    return color;
  }
  
  public PieceRole getRole()
  {
    return role;
  }
  
  public void setPosition(Point p)
  {
    position = p;
  }
  
  public String toString()
  {
    return new String(color + " " + role);
  }
}