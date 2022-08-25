// Z:\ChessGame

public class Rules
{
  public Rules(PieceRole role)
  {
  }
  
  public Rules getRules(PieceRole role)
  {
    return new Rules(role);
  }
}