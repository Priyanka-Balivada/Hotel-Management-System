package Admin;

import java.io.*;
import java.util.*;
import Operations.*;
import Rooms.*;

public class Admin
{
	public static void admin() throws Exception
	{
		String ID;
		int ch=0,option=-1;  
		Scanner sc= new Scanner(System.in);     // Scanner class 
		File file = new File("Customer.txt");  //file crete 
 
		ArrayList<room> obj = new ArrayList<room>(); //create a arryalist of room
		ObjectOutputStream oos = null;               
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
			System.out.println("   1.    Display all Records");
			System.out.println("   2.    Search a particular customer record");
			System.out.println("   3.    Delete a customer record");
			System.out.println("   4.    Logout");
			System.out.println("\n************************************************");
			System.out.print("Enter your Choice: ");
			ch = sc.nextInt();
			sc.nextLine();

			switch(ch)
			{

				case 1:
						System.out.println("\n   ****************** Customer Data *********************\n");
						System.out.println("\nCustomer_ID    Room_Id     Room_Book  Days");    
						li = obj.listIterator();
						while(li.hasNext())
							System.out.println(li.next());
						break;
       
				case 2:
						if(file.isFile())
						{
							boolean found = false;
							System.out.print("\n\nEnter Customer Id: ");
							ID = sc.nextLine();
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
							{
								System.out.println("\n\n********************* Not such Customer ID avilable........ ********************\n\n");   
							}
						}
						else
						{
							System.out.println("\n\n************** File not Found *********************\n\n");
						}
						
						break;
        
				case 3:
						if(file.isFile())
						{
							boolean found = false;
							System.out.print("Enter Customer Id: ");
							ID = sc.nextLine();
							li = obj.listIterator();
							while(li.hasNext())
							{
								room e = (room)li.next();
								if(ID.equals(e.ID))
								{
									li.remove();
									System.out.println("\n\n***************** Record with Customer ID "+ID+" Removed Successfully !!!!!!!! *****************\n\n");
									found = true;
								}
							}
    	        
							if(!found) 
							{
								System.out.println("\n\n***************** Not such Customer ID avilable........ *****************\n\n");   
							}
						}
						else
						{
							System.out.println("\n\n***************** File not Found *****************\n\n");
						} 
						break;
						
				case 4:
						return;
    	   
			} 
   
		}while(ch!=4); 
	}
}

