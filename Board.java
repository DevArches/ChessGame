import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener
{
  private Graphics  g;
  private int       height;
  private int       border;
  private int       step;
  private Dimension size;
  private Piece     piece;
  private Piece     marked = null;
  private Point     markedField = null;
  private Color[]   colors = { Color.lightGray, 
                               Color.darkGray };
    

  
  Piece[] pieces = new Piece[32];
  
  public Board(int height)
  {
    g = getGraphics();
    setHeight(height);
    
    initPieces();
    markKing(PieceColor.WHITE);
    
    addMouseListener(this);
    
    setBackground(Color.gray);
    setMinimumSize(size);
    setPreferredSize(size);
  }

  protected void paintComponent(Graphics g) 
  {
    super.paintComponent(g);
    this.g = g;
    // paint the board
    for(int x = 0; x < 8; x++)
    {
      for(int y = 0; y < 8; y++)
      {
        g.setColor(colors[(x + y) % 2]);
        g.fillRect(border + x * step,
                   border + y * step,
                   step,
                   step); 
      }
    }
    
    // paint the pieces
    for(int i = 0; i < 32; i++)
    {
      if(pieces[i] != null)
      {
        if(pieces[i].isMarked())
        {
          g.drawImage(pieces[i].getSelectedScaleImage(), 
                      border + pieces[i].getXPos() * step, 
                      border + pieces[i].getYPos() * step, 
                      this);
        
        }
        g.drawImage(pieces[i].getScaleImage(), 
                    border + pieces[i].getXPos() * step, 
                    border + pieces[i].getYPos() * step, 
                    this);
      }
    }
  }

  // board dimensions  
  private void setHeight(int height)
  {
    border      = height / 18;
    step        = border * 2;
    this.height = step * 9;
    size        = new Dimension(this.height, this.height);
    
    for(int i = 0; i < 32; i++)
    {
      if(pieces[i] != null)
        pieces[i].setStep(step);
    }
  }
  
  public Dimension getBoardSize()
  {
    return size;
  }
  
  public Dimension setBoardSize(Dimension size)
  {
    setHeight(size.height);
    return size;
  }
  
  // initialize the game pieces
  private void initPieces()
  {
    int x = 0, y = 0;
    PieceColor pc = PieceColor.BLACK;
    PieceRole  pr = PieceRole.PAWN;
    
    for(int i = 0; i < 32; i++)
    {
      x = i % 8;
      y = i / 8;
      if(y  > 1)
        y = y + 4;
      
      if(y == 1 || y == 6)
      {
        if(y == 1)
          pc = PieceColor.BLACK;
        else
          pc = PieceColor.WHITE;
        
        pieces[i] = new Piece(pc, PieceRole.PAWN, new Point(x, y), step);
      }
      else
      {
        if(y == 0)
          pc = PieceColor.BLACK;
        else
          pc = PieceColor.WHITE;
        
        if(x == 0 || x == 7)
          pieces[i] = new Piece(pc, PieceRole.ROOK, new Point(x, y), step);
        else if(x == 1 || x == 6)
          pieces[i] = new Piece(pc, PieceRole.KNIGHT, new Point(x, y), step);
        else if(x == 2 || x == 5)
          pieces[i] = new Piece(pc, PieceRole.BISHOP, new Point(x, y), step);
        else if(x == 3)
          pieces[i] = new Piece(pc, PieceRole.QUEEN, new Point(x, y), step);
        else
          pieces[i] = new Piece(pc, PieceRole.KING, new Point(x, y), step);
      }
    }
  }

  // mark the king for the next player
  private void markKing(PieceColor color)
  {
    if(marked != null)
    {
      marked.unmark();
    }
    
    if(pieces != null)
    {
      for(int i = 0; i < pieces.length; i++)
      {
        if (pieces[i] != null && pieces[i].getColor() == color && pieces[i].getRole() == PieceRole.KING)
        {
          pieces[i].mark();
          marked = pieces[i];
          return;
        }
      }
    }
  } 

  // mark the selected piece
  private boolean markPiece(int x, int y)
  {
    //System.out.println(x + ", " + y);
    
    for(int i = 0; i < 32; i++)
    {
      if(pieces[i] != null && pieces[i].getXPos() == x && pieces[i].getYPos() == y)
      {
        unmarkPiece();
        pieces[i].mark();
        marked = pieces[i];
        this.repaint();
        return true;
      }
    }
    return false;
  }
  
  private void unmarkPiece()
  {
    if(marked != null)
    {
      marked.unmark();
    }
    marked = null;
  }
  
  public void mousePressed(MouseEvent e) 
  {
    Point pb = getField(e.getPoint().x, e.getPoint().y);
    // System.out.println("Clicked at " + pb.x + ", " + pb.y);
    if(pb.x > 7 || pb.y > 7)
    {
      return;
    }
    int ip = findPiece(pb);
    PieceColor pc = marked.getColor();
    if(ip < 0)
    {
      // empty field
      // System.out.println("Found empty field at " + pb.x + "," + pb.y);
      
      if(marked != null)
      {
        marked.setPosition(pb);
        
      if(pc == PieceColor.WHITE)
        markKing(PieceColor.BLACK);
      else
        markKing(PieceColor.WHITE);
      }
    }
    else 
    {
      if(pieces[ip].getColor() == marked.getColor())
      // same color,  mark piece as selected
      {
        // System.out.println("found same color: " + pieces[ip].toString());
        markPiece(pb.x, pb.y);
      }
      else
      // other color, remove piece
      {
        // System.out.println("found different color: " + pieces[ip].toString());
        
        pieces[ip] = null;
        marked.setPosition(pb);
        
        if(pc == PieceColor.WHITE)
          markKing(PieceColor.BLACK);
        else
          markKing(PieceColor.WHITE);
      }
    }
    this.repaint();
  }
  
  public void mouseExited(MouseEvent e){};
  public void mouseEntered(MouseEvent e){};
  public void mouseReleased(MouseEvent e){};
  public void mouseClicked(MouseEvent e){};
  
  public static String getFieldName(int x, int y)
  {
    return new String(new char[]{(char)(x + 65)}) + (8 - y);
  }
  
  public Point getField(int x, int y)
  {
    int px = (x - border) / step;
    int py = (y - border) / step;
    return new Point(px, py);
  }

  // return the index of the piece at x, y
  private int findPiece(int x, int y)
  {
    Point p = getField(x, y);
    return findPiece(p);
  }
  
  // return the index of the piece at this position or -1
  private int findPiece(Point p)
  {
    for(int i = 0; i < pieces.length ; i++)
    {
      if(pieces[i] != null)
        if(pieces[i].getXPos() == p.x && pieces[i].getYPos() == p.y)
          return i;
    }
    return -1;
  }
}