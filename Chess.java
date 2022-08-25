public class Chess
{
  public static String[][] imgs;
  
  public static void main(String[] args)
  {
    new Gameboard();
  }
  
  static
  {
    imgs = new String[3][6];
    
    imgs[0][0] = "./resources/pawn_white.png";
    imgs[0][1] = "./resources/rook_white.png";
    imgs[0][2] = "./resources/bishop_white.png";
    imgs[0][3] = "./resources/knight_white.png";
    imgs[0][4] = "./resources/queen_white.png";
    imgs[0][5] = "./resources/king_white.png";
    imgs[1][0] = "./resources/pawn_black.png";
    imgs[1][1] = "./resources/rook_black.png";
    imgs[1][2] = "./resources/bishop_black.png";
    imgs[1][3] = "./resources/knight_black.png";
    imgs[1][4] = "./resources/queen_black.png";
    imgs[1][5] = "./resources/king_black.png";
    imgs[2][0] = "./resources/pawn_selected.png";
    imgs[2][1] = "./resources/rook_selected.png";
    imgs[2][2] = "./resources/bishop_selected.png";
    imgs[2][3] = "./resources/knight_selected.png";
    imgs[2][4] = "./resources/queen_selected.png";
    imgs[2][5] = "./resources/king_selected.png";
  }
}