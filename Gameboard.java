import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;

public class Gameboard extends JFrame implements ComponentListener
{
  Board board;
  double test = 50;
  
  public Gameboard()
  {
    board = new Board((int)(Toolkit.getDefaultToolkit().getScreenSize().height * 0.75));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    add(board);
    
    addComponentListener(this);
    
    pack();
    setVisible(true);
  }
  
  public void componentResized(ComponentEvent e) {
    int x = getContentPane().getWidth();
    int y = getContentPane().getHeight();
    if (x < y)
      y = x;
    board.setBoardSize(new Dimension(x, y));
    board.repaint();
  }
  
  public void componentHidden(ComponentEvent e) {}
  public void componentMoved(ComponentEvent e) {}
  public void componentShown(ComponentEvent e) {}
}