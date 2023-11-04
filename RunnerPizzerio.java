package pizzerioV06;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class RunnerPizzerio {
	
	static final String RESOURCE_PATH =System.getProperty("user.dir");
	
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public static void main(String[] args) throws IOException, InterruptedException   {
		
		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in); 
		
		System.out.println("Please Enter your ID : ");
		String employeeId = scn.next();
		
		String fname = RESOURCE_PATH + "\\src\\pizzerioV06\\EmployeeAccessibility";
		PizzasOrder.employeesList = new ArrayList();
		readerFile(PizzasOrder.employeesList, fname);
		
		PizzasOrder.pizzaPriceHolder = new HashMap<>();
		PizzasOrder.EmployeeKeyValue(PizzasOrder.employeesList, PizzasOrder.employeeDetails);
		
		String fileLoc = RESOURCE_PATH + "\\src\\pizzerioV06\\pizzerioCollection.txt";
		FileWriter fw = new FileWriter(fileLoc,true);
		
		if(PizzasOrder.employeeDetails.containsKey(employeeId)) {
			String passwordValue = PizzasOrder.employeeDetails.get(employeeId);
			System.out.println("Please Enter Password : ");
			String employeePassword = scn.next();
			if(passwordValue.equals(employeePassword)) {
				System.out.println("Logged In Successfully...");
			
				do {
				
				PizzasOrder pOrdr = new PizzasOrder();
				
		//		String fileLoc ="D:\\Shubham_Java\\Eclipse_Java_WorkSpace\\javaPrograms\\src\\pizzerioV06\\pizzerioCollection.txt";
				
				PizzasOrder.toppingsBill=0;
				
				Random rnd = new Random();
				
				fname = RESOURCE_PATH + "\\src\\pizzerioV06\\AddVegPizzas";
				PizzasOrder.vegPizzasList = new ArrayList();
				readerFile(PizzasOrder.vegPizzasList, fname);
				
				//		count of veg pizzas
				int noOfVegPizza = PizzasOrder.vegPizzasList.size();
				PizzasOrder.vegPizzaCount = noOfVegPizza;
				
				fname = RESOURCE_PATH + "\\src\\pizzerioV06\\AddNonVegPizzas";
				PizzasOrder.nonVegPizzasList = new ArrayList();
				readerFile(PizzasOrder.nonVegPizzasList, fname);
		
				//		count of non-veg pizzas
				int noOfNonVegPizza= PizzasOrder.nonVegPizzasList.size()+noOfVegPizza;
				PizzasOrder.nonVegPizzaCount = noOfNonVegPizza;
				
		//		reads from the coldDrink file
				fname = RESOURCE_PATH + "\\src\\pizzerioV06\\AddColdDrnks";
				PizzasOrder.coldDrinksList = new ArrayList();
				readerFile(PizzasOrder.coldDrinksList, fname);
				int noOfColdDrnk=PizzasOrder.coldDrinksList.size();
				PizzasOrder.coldDrinkCount = noOfColdDrnk;
				
		//		reads Fries from the Fries File
				fname = RESOURCE_PATH + "\\src\\pizzerioV06\\AddFries";
				PizzasOrder.FriesList = new ArrayList();
				readerFile(PizzasOrder.FriesList, fname);
				int noOfFries =PizzasOrder.FriesList.size();
				PizzasOrder.FriesCount = noOfFries;
				
				fname = RESOURCE_PATH + "\\src\\pizzerioV06\\AddToppings";
				PizzasOrder.ToppingsList = new ArrayList();
				readerFile(PizzasOrder.ToppingsList, fname);
				int noOfToppings = PizzasOrder.ToppingsList.size();
				PizzasOrder.toppingsCount = noOfToppings;
				
		//		Reading the Prices from the particular file
				fname = RESOURCE_PATH + "\\src\\pizzerioV06\\PRICES_VEG_PIZZA";
				readerFile(PizzasOrder.pizzaPriceList, fname);
				System.out.println();
				
				fname = RESOURCE_PATH + "\\src\\pizzerioV06\\PRICES_COLDDRNK";
				readerFile(PizzasOrder.coldDrinkPriceList, fname);
				System.out.println();
				
				fname = RESOURCE_PATH + "\\src\\pizzerioV06\\PRICES_FRIES";
				readerFile(PizzasOrder.friesPriceList, fname);
				
				fname = RESOURCE_PATH + "\\src\\pizzerioV06\\PRICES_NON_VEG_PRICES";
				readerFile(PizzasOrder.nonVegPizzaPriceList, fname);
				
				fname = RESOURCE_PATH + "\\src\\pizzerioV06\\PRICES_TOPPINGS";
				readerFile(PizzasOrder.toppingsPriceList, fname);
				
//				used for retriving the Data for a particular period entered.
				fname = RESOURCE_PATH + "/src/pizzerioV06/CountersUsageFiles/SalesPizzas.txt";
				PizzasOrder.PizzasTotalForPeriod = new ArrayList<>();
				readerFile(PizzasOrder.PizzasTotalForPeriod, fname);
				
				fname = RESOURCE_PATH + "/src/pizzerioV06/CountersUsageFiles/SalesColdDrink.txt";
				PizzasOrder.ColdDrinkTotalForPeriod = new ArrayList<>();
				readerFile(PizzasOrder.ColdDrinkTotalForPeriod, fname);
				
				fname = RESOURCE_PATH + "/src/pizzerioV06/CountersUsageFiles/SalesFries.txt";
				PizzasOrder.FriesTotalForPeriod = new ArrayList<>();
				readerFile(PizzasOrder.FriesTotalForPeriod, fname);
				
				fname = RESOURCE_PATH + "/src/pizzerioV06/CountersUsageFiles/SalesToppings.txt";
				PizzasOrder.ToppingsTotalForPeriod = new ArrayList<>();
				readerFile(PizzasOrder.ToppingsTotalForPeriod, fname);
				
				Set<String> EI = PizzasOrder.employeeDetails.keySet();
				List<String> empIds = new ArrayList<>(); 
				int setToListCounter=0;
				for(String x : EI) {
					empIds.add(x);
				}
				
		//		Price distribution according to the respective PRICE Files
				PizzasOrder.pizzaPriceHolder = new HashMap<>();
				PizzasOrder.PriceDistribution(PizzasOrder.pizzaPriceList, PizzasOrder.pizzaPriceHolder);
				PizzasOrder.coldDrinkPriceHolder = new HashMap<>();
				PizzasOrder.PriceDistribution(PizzasOrder.coldDrinkPriceList, PizzasOrder.coldDrinkPriceHolder);
				PizzasOrder.friesPriceHolder = new HashMap<>();
				PizzasOrder.PriceDistribution(PizzasOrder.friesPriceList, PizzasOrder.friesPriceHolder);
				PizzasOrder.nonVegPizzaPriceHolder = new HashMap<>();
				PizzasOrder.PriceDistribution(PizzasOrder.nonVegPizzaPriceList, PizzasOrder.nonVegPizzaPriceHolder);
				PizzasOrder.toppingsPriceHolder = new HashMap<>();
				PizzasOrder.PriceDistribution(PizzasOrder.toppingsPriceList, PizzasOrder.toppingsPriceHolder);
		
				System.out.println("\t\t*** WELCOME TO PIZZERIO ***");
				
				List<Integer> value = new ArrayList<>();
		
					int pBillCnt = 0;
					int i= 0, j=0 ; // general common counters
				
					int cnt;
					int CommonCounter;
				
					do {
						System.out.println("");
						System.out.println("------------------------------------------");
						System.out.println("1. Veg Pizzas.\n2. Non-Veg Pizzas.\n3. Cold Drinks\n4. Fries\n5. CANCEL PIZZA!\n6. !* BILL *!\n7. Sales Report");
						System.out.println("");
						System.out.println("Enter the Choice : ");
						int ch = scn.nextInt();
						
						pOrdr.goBack = false; pOrdr.dispMenu = true;
						
						do {
							if(ch>0 && ch < 8)	{
								int pCnt = 0;
			//					clear the Reading
								for(;j<1;j++) {
									PizzasOrder.vegPizzasList    = new ArrayList<>();
									PizzasOrder.nonVegPizzasList = new ArrayList<>();
								}
								
								switch (ch) {
								
								
								
								case 1:	    // Veg Pizza's Order
									PizzasOrder.particularToppingSumm = 0;
									fname = RESOURCE_PATH + "\\src\\pizzerioV06\\AddVegPizzas";
			//						read the pizzas from the file 
									readerFile(PizzasOrder.vegPizzasList, fname);
									pOrdr.toppingsPizza = new ArrayList<>();
									pOrdr.toppingsCollection = new HashMap<>();
									CommonCounter=1;
									if (pOrdr.dispMenu == true) {
										pOrdr.dispMenu = false;
										int vegPizzaCounter=1;
			//							System.out.println("1. Margerita Pizza.\n2. Onion Pizza.\n3. Paneer BBQ Pizza\n4. Veggi Pizza.\n5. Spl Pizzerio Pizza.");
										for(String vegPizzas : PizzasOrder.vegPizzasList) {
											if(CommonCounter<=noOfVegPizza)
												System.out.println(vegPizzaCounter + ". " + vegPizzas);
											CommonCounter++;
											vegPizzaCounter++;
										}
										Thread.sleep(200l);
										System.err.println("-1. RETURN");
										System.out.println();
			//							System.out.print("Toppings : 1. Olives\t2. Jalapenos\t3. Peprika\t4. PineApples\t5. Cheese");
										System.out.println("");
										System.out.println("Toppings : ");
										int toppingCounter=1;
										for(String topping : PizzasOrder.ToppingsList) 
											System.out.print(toppingCounter++ + ". " + topping + ", ");
										Thread.sleep(100l);
										System.out.println();
										System.err.print("\t-1. Return");
									}
									
									System.out.println("\n");
									System.out.print("Entre the Pizza Code : ");
									Integer pCode = scn.nextInt();
				//					pOrdr.pCnt++;
									pOrdr.pCodeSetter = pCode;
									
									if(pCode >0 && pCode <= noOfVegPizza) {
										pOrdr.PizzaOrderList.add(pCode);
										
									}
									else if(pCode != -1)
										System.err.println("Enter valid pizzas Code...");
									if(pCode == -1) {
										pOrdr.goBack = true;
										break;}
									
									if(pCode>0) {
										System.out.println("Add the Toppings? : ");
										Integer toppings = scn.nextInt();
										while(toppings!=-1){
										
										 	if(toppings >0 && toppings <= noOfToppings) {
										 		pOrdr.toppingsPizza.add(toppings);
										 		pOrdr.ToppingsOrderList.add(toppings);
										 	}
										 	else if(toppings != -1)
										 		System.err.println("Enter valid topping Code...");
										 	
										 	 toppings = scn.nextInt();
										}
									}
									
										
										if(pCode != -1) {
											pOrdr.toppingsCollection.put(pOrdr.pCodeSetter, pOrdr.toppingsPizza);
										}
				//						
				//						pOrdr.mapArr[i++] = pOrdr.toppingsCollection;
										for(;;i++) {
											 pOrdr.OrderList.add(pOrdr.toppingsCollection);
											break;
										}
										System.out.println("TOPPINGS ===>>> " + pOrdr.toppingsPizza);
										System.out.println("PIZZAS ===>> " + pOrdr.PizzaOrderList);
										
										
										individualBill(pOrdr.toppingsPizza, pOrdr.PizzaOrderList, PizzasOrder.pizzaPriceHolder);
										
										
										
//										rmvd
										break;
								case 2 : 		// Non Veg Pizza's Ordrer
									fname = RESOURCE_PATH + "\\src\\pizzerioV06\\AddNonVegPizzas";
			//						reads each and every pizzas from the file
									readerFile(PizzasOrder.nonVegPizzasList, fname);
									
									pOrdr.toppingsPizza = new ArrayList<>();
									pOrdr.toppingsCollection = new HashMap<>();
									 CommonCounter =1 ;
									if (pOrdr.dispMenu == true) {
										pOrdr.dispMenu = false;
										int nonVegPizzaCounter = 1;
			//							System.out.println("1. Ch. Margerita Pizza.\n2. Ch. Onion Pizza.\n3. Ch. BBQ Pizza\n4. Ch. Feast Pizza.\n5. Spl Ch. Pizzerio Pizza. ");
										for(String nonVegPizzas : PizzasOrder.nonVegPizzasList)
										{
											if(CommonCounter<=noOfNonVegPizza-noOfVegPizza)
												System.out.println(nonVegPizzaCounter + ". " + nonVegPizzas);
											CommonCounter++;
											nonVegPizzaCounter++;
										}
										Thread.sleep(200l);
										System.err.println("-1. RETURN");
										System.out.println();
			//							System.out.print("Toppings : 1. Olives\t2. Jalapenos\t3. Peprika\t4. PineApples\t5. Cheese");
										System.out.println("");
										System.out.println("Toppings : ");
										int toppingCounter=1;
										for(String topping : PizzasOrder.ToppingsList) 
											System.out.print(toppingCounter++  + ". " + topping + ", ");
										Thread.sleep(100l);
										System.out.println();
										System.err.print("\t-1. Return");
									}
									
									System.out.println("\n");
									
									System.out.print("Entre the Pizza Code : ");
									pCode = scn.nextInt();
				//					pOrdr.pCnt++;
									
									if(pCode != -1)
										pCode += noOfVegPizza;
										pOrdr.pCodeSetter = pCode;
									if(pCode >0 && pCode <= (noOfNonVegPizza))
										pOrdr.PizzaOrderList.add(pCode);
									else if(pCode != -1)
										System.err.println("Enter valid Pizza Code...");
									if(pCode == -1) {
										pOrdr.goBack = true;
										break;}
									
									if(pCode>0) {
									System.out.println("Add the Toppings? : ");
									Integer toppings = scn.nextInt();
									
									 while(toppings!=-1){
									
									 	if(toppings >0 && toppings <= PizzasOrder.toppingsCount) {
									 		pOrdr.toppingsPizza.add(toppings);
									 		pOrdr.ToppingsOrderList.add(toppings);
									 	}
									
									 	else if( toppings != -1)
									 		System.err.println("Enter valid topping Code...");
									 	
									 	 toppings = scn.nextInt();
									}
									}
									if(pCode != -1)
										pOrdr.toppingsCollection.put(pOrdr.pCodeSetter, pOrdr.toppingsPizza);
				//						pOrdr.mapArr[i++] = pOrdr.toppingsCollection;
									for(;;i++) {
										pOrdr.OrderList.add(pOrdr.toppingsCollection);
										break;
									}
				
									System.out.println("TOPPINGS ===>>> " + pOrdr.toppingsPizza);
									System.out.println("PIZZAS ===>> " + pOrdr.PizzaOrderList);
									
									individualBill(pOrdr.toppingsPizza, pOrdr.PizzaOrderList, PizzasOrder.nonVegPizzaPriceHolder);
									
									
									break;
									
								case 3 : 		// Cold Drinks Order
									fname = RESOURCE_PATH + "\\src\\pizzerioV06\\AddColdDrnks";
			//						reads each and every pizzas from the file
									readerFile(PizzasOrder.coldDrinksList, fname);
									
									if (pOrdr.dispMenu == true) {
										pOrdr.dispMenu = false;
										System.out.println();
										int coldDrinkCounter = 1;
										for(String coldDrink : PizzasOrder.coldDrinksList) {
											if(coldDrinkCounter<=noOfColdDrnk)
												System.out.println(coldDrinkCounter + ". " + coldDrink);
											coldDrinkCounter++;
										}
										Thread.sleep(200l);
										System.err.println("-1. RETURN");
										System.out.println();
										
									}
									
									System.out.print("Enter the Cold Drink Code : ");
									Integer cokeCode = scn.nextInt();
									
									if(cokeCode > 0 && cokeCode <= (noOfColdDrnk))
										pOrdr.CokeOrderList.add(cokeCode);
									else if(cokeCode != -1)
										System.err.println("Enter valid Coke Code...");
									if(cokeCode == -1) {
										pOrdr.goBack = true;
										break;}
									break;
									
								case 4 : 		// Fries Order
									if (pOrdr.dispMenu == true) {
										pOrdr.dispMenu = false;
										System.out.println("");
										int friesCounter = 1;
										for(String Fries : PizzasOrder.FriesList) {
											System.out.println(friesCounter + ". " + Fries);
											friesCounter++;
										}
										Thread.sleep(200l);
										System.err.println("-1. RETURN");
										System.out.println();
									}
									System.out.print("Enter the Fries Code : ");
									Integer friesCode = scn.nextInt();
									
									if(friesCode > 0 && friesCode < (noOfFries+1))
										pOrdr.FriesOrderList.add(friesCode);
									else if(friesCode != -1)
										System.err.println("Enter valid Pizza Code...");
									if(friesCode == -1) {
										pOrdr.goBack = true;
										break;}
									break;
									
								case 5 : 
									System.out.println();
									Iterator iter = pOrdr.OrderList.iterator();
//									while(iter.hasNext()) {
//									
//										System.out.println( pCnt + " : "+iter.next());
//										pCnt++;
//									}
									
									for(int k=0;k<pOrdr.PizzaOrderList.size();k++) {
										System.out.println();
										System.out.print(k + " : " + pOrdr.displayPizzaName(pOrdr.PizzaOrderList.get(k)) + " | ");
										Map<Integer, List> top = new HashMap<>();
										top = pOrdr.OrderList.get(k);
										List<Integer> topList = new ArrayList<>();
										topList = top.get(pOrdr.PizzaOrderList.get(k));
										for(int x : topList) {
											System.out.print(" " + PizzasOrder.displayToppingsName(x-1));
										}
									}
									System.out.println();
									System.out.println("Which Pizza Do You want to Delete ?  (Press -1 To Return...    -2 To Undo)");
									int deletePizzaNo = scn.nextInt();
									if(deletePizzaNo < pOrdr.OrderList.size() && deletePizzaNo > 0) {
										if(deletePizzaNo != -1) {
												System.out.println("Are you Sure you want to delete it ? ( 1 : YES  //  0 : NO )");
												int finalDes = scn.nextInt();
											
											if(finalDes == 1) {
												pOrdr.UndoDeletion.add(pOrdr.OrderList.get(deletePizzaNo));
												pOrdr.OrderList.remove(deletePizzaNo);
			//									System.out.println("Undo List : " + pOrdr.UndoDeletion);
												System.err.println("Deleted Successfully!!");
												if(employeeId.equals(empIds.get(0))){
													pOrdr.p1CancelPizzaCounter++;
												}
												else if(employeeId.equals(empIds.get(1))){
													pOrdr.p2CancelPizzaCounter++;
												}
												else if(employeeId.equals(empIds.get(2))){
													pOrdr.p4CancelPizzaCounter++;
												}
												else if(employeeId.equals(empIds.get(3))){
													pOrdr.p4CancelPizzaCounter++;
												}
											}
											else if(finalDes == 0){
												System.out.println("Pizza Not Deleted !");
												break;
											}
											
											else
												System.err.println("Enter Vaild Descision...");
										}
									}	
									else if(deletePizzaNo == -2) { //undo the deletion...
										if(pOrdr.UndoDeletion.size() >= 1) {
											pOrdr.OrderList.add((Map) pOrdr.UndoDeletion.get((pOrdr.UndoDeletion.size()) -1 ));		
											pOrdr.UndoDeletion.remove(pOrdr.UndoDeletion.size() - 1);
			//								System.err.println("in -2 : " + pOrdr.UndoDeletion);
										}
										else
											System.err.println("No more Elemts Left to Undo...");
									}
									else if(deletePizzaNo == -1) {
										pOrdr.goBack = true;
										break;
									}
									else {
										System.err.println("Enter the Valid Pizza Code to be Deleted");
									}
									break;
									
				//					EXIT#
								case 6 : 
									break;
									
//									Sales in a range
								case 7 :
									
									String regexPattern = "(\\d{4})-(\\d{2})-(\\d{2})";
									
									boolean isDateSet = false;
									
									PizzasOrder.generateReport = true;
									System.out.println("Enter the Data to have been load...");
									
									for(;;) {
										System.out.print("Enter 'From' Date (YYYY-MM-DD) : ");
										String fromDatePattern = scn.next();
										
										Pattern pattern = Pattern.compile(regexPattern);
								        Matcher matcher = pattern.matcher(fromDatePattern);
								        
								        if (matcher.matches()) {
								            String year = matcher.group(1);
								            String month = matcher.group(2);
								            String day = matcher.group(3);
	
								            // Format the date as desired (YYYY-MM-DD)
								            PizzasOrder.fromDate = year + "-" + month + "-" + day;
								            break;
								        } 
								        else {
								            System.out.println("Invalid date format");
								        }
									}
									System.out.println();
									for(;;) {
										System.out.print("Enter 'To' Date (YYYY-MM-DD) : ");
										String toDatePattern = scn.next();
										
										Pattern pattern = Pattern.compile(regexPattern);
								        Matcher matcher = pattern.matcher(toDatePattern);
								        
								        if (matcher.matches()) {
								            String year = matcher.group(1);
								            String month = matcher.group(2);
								            String day = matcher.group(3);
	
								            // Format the date as desired (YYYY-MM-DD)
								            PizzasOrder.toDate = year + "-" + month + "-" + day;
								            break;
								        } else {
								            System.out.println("Invalid date format");
								        }
									}
									System.out.println();
									break;
									
									default : 
										System.err.println("INVALID CODE !!");
										System.out.println("Please Enter the Valid Pizza Code : ");
										break;
									
								} // Switch Closing Braces
							}// if closure
							else {
								pOrdr.goBack=false;
								System.err.println("Please do enter the valid Code");
								pOrdr.goBack = true;
								break;
							}
						}while((ch != 6 && ch != 7)  && pOrdr.goBack == false);
					}while(pOrdr.goBack == true);
				
//					
					System.out.println();
					
				System.out.println();
				System.out.println("Your Ordres Are : ");
				System.out.println();
				                                                                                                				
				if(pOrdr.OrderList.size()!=0) {                                                       			
					System.out.println("No.\t|          Pizzas..     \t |  \t    Toppings    \t\t\t\t|  Pizza.  |   Toppings.  |  Total");
					System.out.println("   \t|                       \t |  \t                \t\t\t\t|\t   | \t\t  |  ");
					Iterator iter = pOrdr.OrderList.iterator();
					PizzasOrder.individualPricesCounter =0;
					while(iter.hasNext()) {
						int getPizzaCode;
						
						Map p = (Map) iter.next();
			//			SysteXm.out.println(p.keySet());
						getPizzaCode = getPizza(p.keySet());
						
						pOrdr.pizzaBill = pOrdr.pizzaTotal(getPizzaCode);
						
						ArrayList<Integer> a = new ArrayList<>();
						String str = new String (p.values().toString());
						
		//				System.out.println(getPizzaCode);
						int spc = 28;
						String PizzaName = String.format("%-"+spc+"s", pOrdr.displayPizzaName(getPizzaCode));
						
						
						if(str.length()>4){//as empty topping String will go as [[]]
							PizzasOrder.toppingsNames = new String();
							PizzasOrder.toppingsNames = String.format("%-47s", getTopp(str));
		//					getTopp(str);
						}  
						else {
							PizzasOrder.toppingsNames = new String();
							PizzasOrder.toppingsNames = String.format("%-47s", "---");
						}
						System.out.printf(++pBillCnt + " \t|   " + PizzaName + " |  \t" + PizzasOrder.toppingsNames + " |  ");
		//				spc = 40;
		//				String spaces = String.format("%-"+spc+"s", getTopp(str));
						System.out.print( PizzasOrder.individualPizzaPrices.get(PizzasOrder.individualPizzaPriceCounter++) + "     | ");
						System.out.print("\t" + PizzasOrder.individualToppingPrices.get(PizzasOrder.individualToppingPriceCounter++) + "\t  | ");
						System.out.print("\t" + pOrdr.individualPrices.get(PizzasOrder.individualPricesCounter++));
						
						
						
						System.out.println();
					}//while loop

					
				}//if stt
				System.out.println("--".repeat(60));
				PizzasOrder.ColdDBill=0;
				List<String> ColdDrinkList = new ArrayList<>();
				for(int x : pOrdr.CokeOrderList) {
					String code = Integer.toString(x);
					ColdDrinkList.add(code);
				}
				
				List<Integer> coldDrinkValue = PizzasOrder.ItemsCounter(ColdDrinkList, noOfColdDrnk);
				System.out.println();
				if(pOrdr.CokeOrderList.size()>0)
					System.out.println("\tName\t\t\t| Price\t| QTY \t| Total");
				for(int lmt=0;lmt<noOfColdDrnk;lmt++) {
					int vale = lmt;
					pOrdr.demomap.put(vale+1, coldDrinkValue.get(lmt));
				}
				int ColdDrinkCounter = 1;
				System.out.println();

				int m=1;
				for(Map.Entry<Integer, Integer> entry : pOrdr.demomap.entrySet()) {
					if(pOrdr.demomap.get(m)!=0) {
						String dispName = String.format("%-23s", PizzasOrder.displayColdDrinkName(entry.getKey()));
						System.out.println(ColdDrinkCounter + ". "+ dispName + "\t| " + PizzasOrder.coldDrinkPriceHolder.get(m) + " \t| " + pOrdr.demomap.get(m) + " \t| " +(PizzasOrder.coldDrinkPriceHolder.get(m)*pOrdr.demomap.get(m)));
						ColdDrinkCounter++;
						PizzasOrder.ColdDBill += (PizzasOrder.coldDrinkPriceHolder.get(m)*pOrdr.demomap.get(m));
					}
					m++;
				}
				
				
				PizzasOrder.FriesBill=0;
				pOrdr.demomap = new HashMap<>();
				
				List<String> FriesList = new ArrayList<>();
				for(int x : pOrdr.FriesOrderList) {
					String code = Integer.toString(x);
					FriesList.add(code);
				}
				System.out.println();
				List<Integer> friesValue = PizzasOrder.ItemsCounter(FriesList, noOfFries);
				if(pOrdr.FriesOrderList.size()>0)
					System.out.println("\tName\t\t\t| Price\t| QTY \t| Total");
				for(int lmt=0;lmt<noOfFries;lmt++) {
					int vale = lmt;
					pOrdr.demomap.put(vale+1, friesValue.get(lmt));
				}
				int FriesCounter = 1;
				m=1;
				for(Map.Entry<Integer, Integer> entry : pOrdr.demomap.entrySet()) {
					if(pOrdr.demomap.get(m)!=0) {
						String dispName = String.format("%-23s", PizzasOrder.displayFriesName(entry.getKey()));
						System.out.println(FriesCounter + ". "+ dispName + "\t| " + PizzasOrder.friesPriceHolder.get(m) + " \t| " + pOrdr.demomap.get(m) + " \t| " +(PizzasOrder.friesPriceHolder.get(m)*pOrdr.demomap.get(m)));
						FriesCounter++;
						PizzasOrder.FriesBill+=(PizzasOrder.friesPriceHolder.get(m)*pOrdr.demomap.get(m));
					}
					m++;
				}
				
				
					LocalDate date = LocalDate.now();
					LocalTime time = LocalTime.now();
					System.out.println();
					
					System.out.println();
					System.err.println("\t\t\t\tBILL");
					System.out.println();
					System.out.println("Date - " + date + " \t\t\t\t Time - " + time);
					System.out.println();
					System.out.println("Order Handler - " + employeeId);
					System.out.println();
					System.out.println("\tPizza's Total : ==> \t\t ₹ " + pOrdr.pizzaBill);
					System.out.println();
					System.out.println("\tToppings Total : ==>\t\t ₹ " + PizzasOrder.toppingsBill);
					System.out.println();
					System.out.println("\tCold Drink Total : ==>\t\t ₹ " + PizzasOrder.ColdDBill);
					System.out.println();
					System.out.println("\tFries Total : ==>\t\t ₹ " + PizzasOrder.FriesBill);
					System.out.println();
					System.out.println("\tGST : ==>\t\t         ₹ " + pOrdr.gstApplied());
					System.out.println();
					System.out.printf("\t\t TOTAL (incl. tax) : ==> ₹ " + pOrdr.grandTotal());
					System.out.println();
					System.out.println("\t\t\tHave a Cheesy Peesy Day !");

//					Writes the Code of the Ordered Pizzas in a file
					String FileLoc = RESOURCE_PATH + "\\src\\pizzerioV06\\CountersUsageFiles\\PizzasCounter.txt";
					FileWriter pizzaFileWriter = new FileWriter(FileLoc,true);
					for(int x : pOrdr.PizzaOrderList) {
						pizzaFileWriter.write(x + "\n");
					}
					
//					Writes the Code of the Ordered Toppings in a file
					FileLoc = RESOURCE_PATH + "\\src\\pizzerioV06\\CountersUsageFiles\\ToppingssCounter.txt";
					FileWriter ToppingFileWriter = new FileWriter(FileLoc,true);
					for(int x : pOrdr.ToppingsOrderList) {
						ToppingFileWriter.write(x + "\n");
					}

//					Writes the Code of the Ordered ColdDrink in a file
					FileLoc = RESOURCE_PATH + "\\src\\pizzerioV06\\CountersUsageFiles\\ColdDrinksCounter.txt";
					FileWriter ColdDrinkFileWriter = new FileWriter(FileLoc,true);
					for(int x : pOrdr.CokeOrderList) {
						ColdDrinkFileWriter.write(x + "\n");
					}
					
//					Writes the Code of the Ordered ColdDrink in a file
					FileLoc = RESOURCE_PATH + "\\src\\pizzerioV06\\CountersUsageFiles\\FriesCounter.txt";
					FileWriter FriesFileWriter = new FileWriter(FileLoc,true);
					for(int x : pOrdr.FriesOrderList) {
						FriesFileWriter.write(x + "\n");
					}
					
					pizzaFileWriter.close();
					ToppingFileWriter.close();
					ColdDrinkFileWriter.close();
					FriesFileWriter.close();
					
//					Writing the Total ites sold in no...
//					veg collection
					FileLoc = RESOURCE_PATH + "\\src\\pizzerioV06\\noOfItemsSold.txt";
					FileWriter itemsCountingFileWriter = new FileWriter(FileLoc,false);
					
					fname = RESOURCE_PATH + "\\src\\pizzerioV06\\CountersUsageFiles\\PizzasCounter.txt";
					readerFile(PizzasOrder.PizzasCounterUsageList, fname);
					
					fname = RESOURCE_PATH + "/src/pizzerioV06/CountersUsageFiles/ToppingssCounter.txt";
					readerFile(PizzasOrder.ToppingsCounterUsageList, fname);
					
					fname = RESOURCE_PATH + "/src/pizzerioV06/CountersUsageFiles/ColdDrinksCounter.txt";
					readerFile(PizzasOrder.ColdDrinkCounterUsageList, fname);
					
					fname = RESOURCE_PATH + "/src/pizzerioV06/CountersUsageFiles/FriesCounter.txt";
					readerFile(PizzasOrder.FriesCounterUsageList, fname);
					
//					Writes the Total Collection Sold untill Now....
					pOrdr.WriteTotalCollection(PizzasOrder.PizzasCounterUsageList,noOfVegPizza,0,PizzasOrder.vegPizzasList, PizzasOrder.pizzaPriceHolder,itemsCountingFileWriter);

					pOrdr.WriteTotalCollection(PizzasOrder.PizzasCounterUsageList,noOfNonVegPizza,noOfVegPizza,PizzasOrder.nonVegPizzasList, PizzasOrder.pizzaPriceHolder,itemsCountingFileWriter);
					
					pOrdr.WriteTotalCollection(PizzasOrder.ToppingsCounterUsageList,noOfToppings,0,PizzasOrder.ToppingsList, PizzasOrder.toppingsPriceHolder,itemsCountingFileWriter);

					pOrdr.WriteTotalCollection(PizzasOrder.ColdDrinkCounterUsageList,noOfColdDrnk,0,PizzasOrder.coldDrinksList, PizzasOrder.coldDrinkPriceHolder,itemsCountingFileWriter);

					pOrdr.WriteTotalCollection(PizzasOrder.FriesCounterUsageList,noOfFries,0,PizzasOrder.FriesList, PizzasOrder.friesPriceHolder,itemsCountingFileWriter);

					

					int sum=0;
					for(int collects : PizzasOrder.grandTotalList) {
						sum+=collects;
					}
					
					itemsCountingFileWriter.write("\n\n\t\t\tTOTAL COLLECTIONS  :-  Rs. " + sum);
					
					itemsCountingFileWriter.close();
					
					
					System.out.println(empIds);
					System.out.println();
						
					if(PizzasOrder.employeeDetails.size()>=1) {
						PizzasOrder.p1 = empIds.get(0);
					}
					if(PizzasOrder.employeeDetails.size()>=2) {
						PizzasOrder.p2 = empIds.get(1);
					}
					if(PizzasOrder.employeeDetails.size()>=3) {
						PizzasOrder.p3 = empIds.get(2);
					}
					if(PizzasOrder.employeeDetails.size()>=4) {
						PizzasOrder.p4 = empIds.get(3);
					}
						
					FileLoc = RESOURCE_PATH+"/src/pizzerioV06/CountersUsageFiles/SalesPizzas.txt";
					FileWriter salesFileWriter = new FileWriter(FileLoc, true);
					for(int k=0; k<pOrdr.PizzaOrderList.size();k++) {
						salesFileWriter.write(pOrdr.PizzaOrderList.get(k) + " " + date + " " + employeeId + "\n");
					}
					
					FileLoc = RESOURCE_PATH + "/src/pizzerioV06/CountersUsageFiles/SalesFries.txt";
					FileWriter salesFileWriterFries = new FileWriter(FileLoc, true);
					for(int k=0; k<pOrdr.FriesOrderList.size(); k++) {
						salesFileWriterFries.write(pOrdr.FriesOrderList.get(k) + " " + date + " " + employeeId + "\n");
					}
					
					FileLoc = RESOURCE_PATH + "/src/pizzerioV06/CountersUsageFiles/SalesColdDrink.txt";
					FileWriter salesFileWriterColdDrink = new FileWriter(FileLoc, true);
					for(int k=0; k<pOrdr.CokeOrderList.size(); k++) {
						salesFileWriterColdDrink.write(pOrdr.CokeOrderList.get(k) + " " + date + " " + employeeId + "\n");
					}
					
					FileLoc = RESOURCE_PATH + "/src/pizzerioV06/CountersUsageFiles/SalesToppings.txt";
					FileWriter salesFileWriterToppings = new FileWriter(FileLoc, true);
					for(int k=0; k<pOrdr.ToppingsOrderList.size(); k++) {
						salesFileWriterToppings.write(pOrdr.ToppingsOrderList.get(k) + " " + date + " " + employeeId + "\n");
					}
					
					FileLoc = RESOURCE_PATH + "/src/pizzerioV06/REPORT_OF_SALE.txt";
					FileWriter writeSalesReport = new FileWriter(FileLoc, false); 
					
					salesFileWriter.close();
					salesFileWriterFries.close();
					salesFileWriterColdDrink.close();
					salesFileWriterToppings.close();
	
					if(PizzasOrder.generateReport == true) {
					
						int enteredFromDate = Integer.parseInt(PizzasOrder.fromDate.replace("-", ""));
						int enteredToDate = Integer.parseInt(PizzasOrder.toDate.replace("-", ""));
						
						for(String x: PizzasOrder.PizzasTotalForPeriod) {
							String token[] = x.split(" ");
							int verifyDate = Integer.parseInt(token[1].replace("-", ""));
	//						PizzasOrder.toDate.replace("-", "");
							if(PizzasOrder.p1.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfVegPizza) {
										pOrdr.p1VegPizzaCounter++;
									}
									else if(Integer.parseInt(token[0]) <= noOfNonVegPizza) {
										pOrdr.p1NonVegPizzaCounter++;
									}
								}
								else {
									
								}
							}
							else if(PizzasOrder.p2.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfVegPizza) {
										pOrdr.p2VegPizzaCounter++;
									}
									else if(Integer.parseInt(token[0]) <= noOfNonVegPizza) {
										pOrdr.p2NonVegPizzaCounter++;
									}
								}
							}
							else if(PizzasOrder.p3.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfVegPizza) {
										pOrdr.p3VegPizzaCounter++;
									}
									else if(Integer.parseInt(token[0]) <= noOfNonVegPizza) {
										pOrdr.p3NonVegPizzaCounter++;
									}
								}
							}
							else if(PizzasOrder.p4.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfVegPizza) {
										pOrdr.p4VegPizzaCounter++;
									}
									else if(Integer.parseInt(token[0]) <= noOfNonVegPizza) {
										pOrdr.p4NonVegPizzaCounter++;
									}
								}
							}
						}
						for(String x: PizzasOrder.ColdDrinkTotalForPeriod) {
							String token[] = x.split(" ");
							int verifyDate = Integer.parseInt(token[1].replace("-", ""));
							if(PizzasOrder.p1.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfColdDrnk) {
										pOrdr.p1ColdDrinkCounter++;
									}
								}
							}
							else if(PizzasOrder.p2.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfColdDrnk) {
										pOrdr.p2ColdDrinkCounter++;
									}
								}
							}
							else if(PizzasOrder.p3.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfColdDrnk) {
										pOrdr.p3ColdDrinkCounter++;
									}
								}
							}
							else if(PizzasOrder.p4.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfColdDrnk) {
										pOrdr.p4ColdDrinkCounter++;
									}
								}
					  		}
						}
						for(String x: PizzasOrder.FriesTotalForPeriod) {
							String token[] = x.split(" ");
							int verifyDate = Integer.parseInt(token[1].replace("-", ""));
							if(PizzasOrder.p1.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfFries) {
										pOrdr.p1FriesCounter++;
									}
								}
							}
							else if(PizzasOrder.p2.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfFries) {
										pOrdr.p2FriesCounter++;
									}
								}
							}
							else if(PizzasOrder.p3.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfFries) {
										pOrdr.p3FriesCounter++;
									}
								}
							}
							else if(PizzasOrder.p4.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfFries) {
										pOrdr.p4FriesCounter++;
									}
								}
					  		}
						}	
						for(String x: PizzasOrder.ToppingsTotalForPeriod) {
							String token[] = x.split(" ");
							int verifyDate = Integer.parseInt(token[1].replace("-", ""));
							if(PizzasOrder.p1.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfToppings) {
										pOrdr.p1ToppingsCounter++;
									}
								}
							}
							else if(PizzasOrder.p2.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfToppings) {
										pOrdr.p2ToppingsCounter++;
									}
								}
							}
							else if(PizzasOrder.p3.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfToppings) {
										pOrdr.p3ToppingsCounter++;
									}
								}
							}
							else if(PizzasOrder.p4.equals(token[2])) {
								if(verifyDate>=enteredFromDate && verifyDate <= enteredToDate) {
									if(Integer.parseInt(token[0]) <=noOfToppings) {
										pOrdr.p4ToppingsCounter++;
									}
								}
					  		}
						}	
						
						
						int padding = 13; 
						writeSalesReport.write("\n\n\t\t\tPIZZERIO SALES REPORT ...\n");
						writeSalesReport.write("\n  FROM : " + PizzasOrder.fromDate + "  \t\t\t TO : " + PizzasOrder.toDate);                           
						
						writeSalesReport.write("\n\n Cashier's Secerete Code : \t  " + empIds.get(0) +"\t\t    |  " + empIds.get(1) +"\t\t |  " + empIds.get(2)+"\t\t     |  " + empIds.get(3));
						writeSalesReport.write("\n\n Veg Pizzas              : \t  " + pOrdr.p1VegPizzaCounter + "       \t\t\t    |  "  + pOrdr.p2VegPizzaCounter + "       \t\t\t |  "  + pOrdr.p3VegPizzaCounter + "       \t\t\t |  "  + pOrdr.p4VegPizzaCounter );
						writeSalesReport.write("\n Non Veg Pizzas          : \t  " + pOrdr.p1VegPizzaCounter + "       \t\t\t    |  " + pOrdr.p2NonVegPizzaCounter + "       \t\t\t |  "  + pOrdr.p3NonVegPizzaCounter + "       \t\t\t |  " + pOrdr.p4NonVegPizzaCounter);
						writeSalesReport.write("\n Cold Drinks             : \t  " + pOrdr.p1ColdDrinkCounter + "       \t\t\t    |  "  + pOrdr.p2ColdDrinkCounter + "       \t\t\t |  "  + pOrdr.p3ColdDrinkCounter + "       \t\t\t |  "  + pOrdr.p4ColdDrinkCounter );
						writeSalesReport.write("\n Fries                   : \t  " + pOrdr.p1FriesCounter + "       \t\t\t    |  "  + pOrdr.p2FriesCounter + "       \t\t\t |  "  + pOrdr.p3FriesCounter + "       \t\t\t |  "  + pOrdr.p4FriesCounter );
						writeSalesReport.write("\n Toppings                : \t  " + pOrdr.p1ToppingsCounter + "       \t\t\t    |  "  + pOrdr.p2ToppingsCounter + "       \t\t\t |  "  + pOrdr.p3ToppingsCounter + "       \t\t\t |  "  + pOrdr.p4ToppingsCounter );
						writeSalesReport.write("\n Pizza's Order Canceled  : \t  " + pOrdr.p1CancelPizzaCounter + "       \t\t\t    |  "  + pOrdr.p2CancelPizzaCounter + "       \t\t\t |  "  + pOrdr.p3CancelPizzaCounter + "       \t\t\t |  "  + pOrdr.p4CancelPizzaCounter );
						


						pOrdr.clearReportCounter();
						PizzasOrder.generateReport=false;
				}// generate report if statement
				else {
						writeSalesReport.write("\n\n\n\t\t\tNO DATA TO DISPLAY ! ");
				}
					writeSalesReport.close();

				
				System.out.println("---".repeat(14));
				PizzasOrder.OrderId = rnd.nextInt(10000, 99999);
				String str="\n";
				fw.write(""+PizzasOrder.OrderId + "\t\t" + pOrdr.grandTotal() + "\t\t" + date + "   " + time + "\t\t" + employeeId + "\n");
				
				
				System.out.println("Want to Continue ? (1 : YES  |  0 : NO)");
				int wantToContinue = scn.nextInt();
				if(wantToContinue == 1) {
					PizzasOrder.continueOrdering = true;
				}
				else if(wantToContinue == 0) {
					PizzasOrder.continueOrdering = false;
					System.out.println("Thankyou for using PizzaWare");
				}
				else 
					System.err.println("Enter valid Input....");
				
				
				}while(PizzasOrder.continueOrdering == true);
				}
			
			else
				System.err.println("Wrong Password...");
		}//accessibiltiy if condition
		else {
			System.err.println("Sorry you are not accessed...");
			fw.write("\n"+ " Employee with ID : " + employeeId + " Tried to Access the software !!! " +"\n");
		}
		
		fw.close();
	}// main
	
	// main end here ...
	static PizzasOrder pO = new PizzasOrder();
	
	static int getPizza(Set<Integer> pCode) {
		
		for(Integer i : pCode) {
			PizzasOrder.pv=i;
		}
			return PizzasOrder.pv;
	} 
	
		
	static String getTopp(String withSqrBrackets) {
			String withoutFirstSqrBrackets = withSqrBrackets.replace("[[", "");
			String withoutanySqrBrackets = withoutFirstSqrBrackets.replace("]]", "");
			String token[] = withoutanySqrBrackets.split(", "); 
			
			PizzasOrder.str = "";
			for(int j=0;j<token.length;j++) {
				int toppingCode = Integer.parseInt(token[j]);
				PizzasOrder.str = PizzasOrder.str + (PizzasOrder.displayToppingsName(toppingCode-1) + ", ");
				PizzasOrder.toppingsBill =  PizzasOrder.toppingsTotal(toppingCode);
			}
		return PizzasOrder.str;
	}


	/**
	 * Reads the Content from the File and Converts it into the List.
	 * @param pizzasList
	 * @param fName
	 * @throws IOException
	 * @author Shubham Parmar
	 */
	
	static void readerFile(List<String> pizzasList,String fName) throws IOException {
//		String fName = "D:\\Shubham_Java\\Eclipse_Java_WorkSpace\\javaPrograms\\src\\javaTestDemo\\NewFile.txt";
		File f = new File(fName);
		StringBuilder sb = new StringBuilder();
		@SuppressWarnings("resource")
		FileReader fr = new FileReader(f);
		int characterValue;
		while ((characterValue = fr.read()) != -1) {
			if ((Character.isWhitespace((char) characterValue)) && ((char) characterValue) != ' ') {
				String word = sb.toString();
				if (!word.isEmpty()) {
					
					pizzasList.add(word);
				}
				sb.setLength(0);
			} else {
				sb.append((char) characterValue);
			}
		}
		String word = sb.toString();
		if (!word.isEmpty()) {
			pizzasList.add(word);
		}
	}
	static PizzasOrder pOrdr = new PizzasOrder();
	
	/**
	 * Provides the Individual Prices of each and every pizzas
	 * @param toppingsPizza
	 * @param PizzaOrderList
	 * @param PriceHolder
	 * @author Shubham Parmar
	 */
	static void individualBill(List<Integer> toppingsPizza, @SuppressWarnings("rawtypes") List PizzaOrderList, Map<Integer, Integer> PriceHolder) {
		 PizzasOrder.particularToppingSumm = 0;
		 
		 for(Integer topping : toppingsPizza) {
			int k=0;
			while(k<=PizzasOrder.toppingsPriceHolder.size()) {
				if(k == topping) {
					PizzasOrder.particularToppingSumm += PizzasOrder.toppingsPriceHolder.get(k);
					break;
				}
				k++;
			}
		
		}
		int LatestAddedPizza = (int) PizzaOrderList.get(PizzaOrderList.size()-1);//gets Latest Pizzas Entered
		if(LatestAddedPizza <= PizzasOrder.vegPizzaCount) {
			 PizzasOrder.priceOfLatestAddedPizza = PriceHolder.get(LatestAddedPizza);
		}
		else {
			PizzasOrder.priceOfLatestAddedPizza = PriceHolder.get(LatestAddedPizza-PizzasOrder.vegPizzaCount);
		}
		for(;;PizzasOrder.individualPricesCounter++) {
			PizzasOrder.individualPrices.add( PizzasOrder.priceOfLatestAddedPizza + PizzasOrder.particularToppingSumm);
			PizzasOrder.individualPizzaPrices.add(PizzasOrder.priceOfLatestAddedPizza);
			PizzasOrder.individualToppingPrices.add(PizzasOrder.particularToppingSumm);
			break;
		}
	}
}

