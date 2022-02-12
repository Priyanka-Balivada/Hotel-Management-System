package Rooms;
import java.io.Serializable;

public class room implements Serializable
{
  public String ID;
  int roomID;
  int roomNo;
  int days;

  public room(String ID, int roomID,int roomNo, int days)
  {
     this.ID = ID;
     this.roomID = roomID;
     this.roomNo = roomNo;
     this.days = days;
  }//end of room constructor
 
 //overriding the toString() method
  public String toString()
  {
   return "    "+ID+"             "+roomID+"           "+roomNo+"          "+days+"\n\n";
  }
  
}//end of room class