package Management;

//import packages
import java.util.*;
import java.io.Serializable;
import java.io.*;
import Operations.*;
import Admin.*;

//interface for login Credits
interface LoginCredits
{
	void newAccount(String Name,String phone,String Email,int age,String Password);
	void CheckLogin() throws Exception;
}

//class Info defines the information of the user
class Info
{
  public static int count=0;
  String name;
  String phNo;
  String email;
  int age;
  String userID;
  String passwd;
}

class Customer extends Info implements LoginCredits,Serializable
{
	String inputPasswd;
	
	Customer()
	{
		
	}
	
	Customer(String Name,String phone,String Email,int age,String Password)
	{
		  newAccount(Name,phone,Email,age,Password);
	}

	public String toString()
	{
		return name+" "+phNo+" "+email+" "+age+" "+userID+" "+passwd;
	}
	
	public void newAccount(String Name,String phone,String Email,int age,String Password) 
	{
		name=Name;
		phNo=phone;
		email=Email;
		this.age=age;
		passwd=Password;
		userID=Name+(Integer.toString(age));
		System.out.println("\n\n***************** Your account has been created successfully !!!! *****************\nYour User ID is "+userID);
	}
	
	public void CheckLogin() throws Exception
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Password");
		inputPasswd=sc.nextLine();
	
		if(passwd.equals(inputPasswd))
		{
			System.out.println("\n\n***************** Login Successful *****************\n\n");
			
			Booking b=new Booking();
			b.Book(userID);
			
		}
		else
		{
			System.out.println("\n\n***************** Login Unsuccessful *****************\n\n");
		}
	}
}

public class HotelManagement extends Customer
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		//Customer cus;
		ArrayList<Customer> CustomerList = new ArrayList<Customer>();
		File file=new File("CustomerDetails.txt");
		ObjectOutputStream output = null;
		ObjectInputStream input = null;
		ListIterator li = null;
		
		 if (file.isFile())
		 {
			    input = new ObjectInputStream(new FileInputStream(file));
			    CustomerList = (ArrayList<Customer>)input.readObject();
			    input.close();
		 }
		
		int ch,exitStatus=0;
		System.out.println("*******************************************");
		System.out.println("Welcome to Hotel Management System !!!!!!!");
		System.out.println("*******************************************");
		
		do 
		{
			
			System.out.println("\n\n\nPress 1 : Create New Account");
			System.out.println("Press 2 : Login");
			System.out.println("Press 3 : Exit");
			ch=sc.nextInt();
			sc.nextLine();
			
			switch(ch) 
			{
			
				case 1 :
					System.out.println("\n\nEnter Name : ");
					String Name=sc.nextLine();
					System.out.println("Enter Mobile No. : ");
					String phone=sc.nextLine();
					System.out.println("Enter Email Address : ");
					String Email=sc.nextLine();
					System.out.println("Enter Age : ");
					int age=sc.nextInt();
					sc.nextLine();
					System.out.println("Create a Password");
					String Password=sc.nextLine();
					  
					CustomerList.add(new Customer(Name,phone,Email,age,Password));
					output = new ObjectOutputStream(new FileOutputStream(file));
			        output.writeObject(CustomerList);
			        output.close();
			        count++;
					break;
					
				case 2 : 
					if(file.isFile())
					{
						  boolean success=false;
				          System.out.println("\n\nEnter your User ID");
						  String id=sc.nextLine();
						  li = CustomerList.listIterator();
				          while(li.hasNext())
				          {
				           Customer cus2 = (Customer)li.next();
					       
					       if(!(id.equals("Admin"))) 
					       {
					    	   if(cus2.userID!=null) 
					    	   {
						    	   String user=cus2.userID; 
						           if(user.equals(id))
						           {
								   cus2.CheckLogin();
								   success=true;
						           } 
						       } 
					       }
					       else 
					       {
					    	   System.out.println("Enter the Password");
					   		   String inputPasswd=sc.nextLine();
					   		   if(inputPasswd.equals("AdminAPM")) 
					   		   {
					   			  System.out.println("\n\n************* Login Successful !!!! ***************");
					   			  Admin adminUser=new Admin();
					   			  adminUser.admin();
					   			  success=true;
					   			  break;
					   		   }
					   		   else 
					   		   {
					   			System.out.println("\n\n***************** Login Unsuccessful !!!! *****************");
					   			success=true;
					   			break;
					   		   }
					       }
					       
				        }
					
						if(success==false) 
						{
							System.out.println("\n\n**************** Invalid User ID Entered !!!!!!!! ******************");
						}
				     }   
					break;
					
				case 3 : 
					System.out.println("\n\n\n**************** Thank You For Visiting.... ****************\n**************** Visit again... ****************");
					return;
			}
			
			System.out.println("\n\n\nDo you want to exit .... Press 1 ..... else Press any key");
			exitStatus=sc.nextInt();
		}while(exitStatus!=1);
		
		sc.close();
	}	
}

