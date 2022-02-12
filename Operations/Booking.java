package Operations;

import java.util.Scanner;
import java.util.*;
import java.io.*;
import Rooms.*;

public class Booking
{
  public void Book(String ID) throws Exception
  {
    int choice = -1;
    Booking Obj1 = new Booking();           //creating a object 
    Scanner sc= new Scanner(System.in);     // Scanner class 
    File file = new File("Customer.txt");  //file crete 
 
    ArrayList<room> obj = new ArrayList<room>(); //create a arryalist of room
    ObjectOutputStream oos = null;               //
    ObjectInputStream ois = null;
    ListIterator li = null;

   if (file.isFile())
   {
    ois = new ObjectInputStream(new FileInputStream(file));
    obj = (ArrayList<room>)ois.readObject();
    ois.close();
   }

   do
   {
      System.out.println("\n\n****************** Services ***********************");
      System.out.println("   Press 1.    Book a Room");
      System.out.println("   Press 2.    View Your Booking");
      System.out.println("   Presss 0.    Logout");
      System.out.println("\n************************************************");
      System.out.print("\nEnter your Choice: ");
      choice = sc.nextInt();
      sc.nextLine();

      switch(choice)
      {
        case 1:

         //System.out.print("Enter Customer ID : ");
	     //int ID=sc.nextInt();
         System.out.println("\n\nEnter Room type : \n");
         System.out.print("************** Do you want to See a Room type - (Y/N) **************");
         String option = sc.nextLine();
         if(option.equals("Y"))
         {
             Obj1.roomType();
         }
         
         System.out.print("\n\nEnter Room type : ");
         int roomID =sc.nextInt();

         System.out.print("\nHow many rooms you want to book : ");
         int roomNo =sc.nextInt();         
         
         System.out.print("\nEnter Days for Stay-In :");
         int days=sc.nextInt();
          
         System.out.println("\n\n  ************** Your Entered Data **************\n");
         System.out.println("      Customer ID: "+ID);
         System.out.println("      Room Type: "+roomID);
         System.out.println("      No of Room book: "+roomNo);
         System.out.println("      Days : "+days);

         System.out.println("\n\n ***************** Your Booking Successfully!!! **************\n");
         System.out.println("\n\n\nYour Payment: ");
         Obj1.payment(roomID, roomNo, days);

         obj.add(new room(ID,roomID,roomNo,days));
         oos = new ObjectOutputStream(new FileOutputStream(file));
         oos.writeObject(obj);
         oos.close();
         break;
       
       case 2:

        if(file.isFile())
        {
          boolean found = false;
          //System.out.print("Enter Customer Id: ");
          //ID = sc.nextInt();
          li = obj.listIterator();
          while(li.hasNext())
          {
           room e = (room)li.next();
           if(ID.equals(e.ID))
           {
            System.out.println("\nCustomer_ID    Room_Id     Room_Book  Days");  
            System.out.println(e);
            found = true;
           }
          }
          
         if(!found)
          System.out.println("\n\n******************* Not such Customer ID avilable........ *****************\n\n");   
         }
         else{
         System.out.println("\n\n************** File not Found **********************\n\n");
         break;
       }
      }         
    }while(choice!=0); 
  ID=null;
}//end of Book method

  void roomType()
   {
      System.out.println("\nNo.    Room Type     Rent"); 
      System.out.println(" 1.   Single Room    500");      
      System.out.println(" 2.   Double Room    1000");     
      System.out.println(" 3.   Triple Room    1500");        
      System.out.println(" 4.   Quad Room      2000\n"); 
   }//end of room type

  void payment(int roomID, int roomNo, int days)
  {
    int rent =0;
    if(roomID==1)
    {
      rent=500;
    }
    else if(roomID==2)
    {
       rent=1000;
    }
    else if(roomID==3)
    {
       rent=1500;
    }
    else
    {
       rent=2000;
    }
    System.out.println("Your Total payment: "+days*roomNo*rent+"\n\n");
  }//end of payment method


}//end of class BookRoom