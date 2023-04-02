
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.atm;

/**
 *
 * @author Mwansa Mwelwa
 */
//This program that shows the basic operations of an atm
import java.util.Scanner;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
// ################################# ATM CLASS ##########################################
public class Atmproject{

	static int pin;
	static double startAmount= 4000;
	public static void main(String[] args){
		User a = new User();
		// ########################## 1. User inserts the ATM card ##########################################################

		Scanner input = new Scanner(System.in);
		System.out.println("Insert Card (press 1 for Insert and 0 for Cancel) ");
		int insert = input.nextInt();

		//##########################2. ATM verifies if the card is real and who it belongs to ##########################################################

		if(insert == 1  ){

			pin();


			//########################## 3. If pin is correct : there are other services which are offered ##########################################################

			if(pin == a.pin){
				System.out.println(" WELCOME " + a.name);
				System.out.println("You have " + startAmount + " available in your account." );
				

//########################## ATM displays the options to the user ##########################################################
				System.out.println("How would you like to proceed ?");

				System.out.println("1. Cash Withdraw");
				System.out.println("2. Cash Deposite");
				System.out.print("Choose the option :");
				int opt = input.nextInt();
				if(opt ==1 ){
					withdraw();
				}else if (opt == 2){
					deposite();
				}
			}else{
				System.out.println("You have inserted the wrong pin");
				System.out.print("Press 1 to try again. You have 3 chances left: ");
				int tryAgain = input.nextInt();
				if(tryAgain == 1){
					for(int i = 1 ; i <= 3 ; i++){
						if(pin != a.pin){
							pin();
						}
						if(i == 3 ){
							System.out.println("Your account has been locked.");
						}
					}
				}

			}

		}else{
			System.out.println("You have cancelled");
		}

	}

	public static void pin(){
			Scanner input = new Scanner(System.in);
			System.out.println("Put in the pin : ");
			 pin = input.nextInt();
	}
//########################## Withdraw Method ##########################################################

	public static void withdraw(){
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the amount that you want to withdraw : ");
		double withdrawAmount = input.nextDouble();
		if(withdrawAmount < (startAmount -100)){
					
					double remainingAmount = startAmount -withdrawAmount;
					System.out.println("You have successfully withdrawn " + withdrawAmount + " you have " + remainingAmount + " left.");
					System.out.println("Do you need a receipt for the transaction ? (Enter 1 for yes and 0 for no)");
					int reciept = input.nextInt();
					if(reciept == 1){
						System.out.println("Prints receipt. Thank you");
					}else{
						System.out.println("Thank you");
					}
				}else{
					System.out.println(" You have insufficient balance");
					System.out.print(" Press 1 to insert new amount or 0 to cancel");
					int tryAgain = input.nextInt();
					if(tryAgain == 1){
						withdraw();
					}else{
						System.out.println("Thank you");
					}
				}
	}


//########################## deposite Method ##########################################################

	public static void deposite(){
		Scanner input = new Scanner(System.in);
		System.out.println("How much do you want to deposite ? ");
		double depositeAmount = input.nextDouble();

		double newAmount = startAmount + depositeAmount;

		System.out.println("Your new amount is " + newAmount);
		System.out.println("Do you need a receipt for the transaction ? (Enter 1 for yes and 0 for no)");
		int reciept = input.nextInt();
		if(reciept == 1){
			System.out.println("Prints receipt. Thank you");
		}else{
			System.out.println("Thank you");
		}
	}
}

//#############################  USER CLASS ########################################
 class User{
	
		public String name = "Barry Allen";
		public int accN = 246810;
		public int pin = 1234;
	
}


