package pizzerioV06;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzasOrder {
	// no of Menu Items
	static int OrderId, vegPizzaCount, nonVegPizzaCount, coldDrinkCount, FriesCount, toppingsCount ;
//	Will hold the Price of the Latest Added Pizzas
	static int priceOfLatestAddedPizza;
	static int pv, cv, fv;// pizza vale, coldDrinkVale, FriesValue
	static String str = "", toppingsNames,getColdDrink, fromDate, toDate;
	static int particularToppingSumm=0;//sum of toppigs for individual pizzas...
	
//	Maps to store the prices from the file into the respective map
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Map<Integer, Integer> pizzaPriceHolder = new HashMap();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Map<Integer, Integer> coldDrinkPriceHolder = new HashMap();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Map<Integer, Integer> friesPriceHolder = new HashMap();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Map<Integer, Integer> nonVegPizzaPriceHolder = new HashMap();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Map<Integer, Integer> toppingsPriceHolder = new HashMap();
	static Map<String, String> employeeDetails = new HashMap<>();
	
	
	int  pizzaBill; // Handles the Billings of the Pizza(Total Amount)
	
	static int toppingsBill, ColdDBill, FriesBill;
	int cnt;
	PizzerioVegPizzas p = new PizzerioVegPizzas();
	
	Integer pCodeSetter;
	// Stores the Pizzas Order
	List<Integer> PizzaOrderList = new ArrayList<>();
//	Stores the Toppings Order
	List<Integer> ToppingsOrderList = new ArrayList<>();
	
	static List<String> PizzasCounterUsageList = new ArrayList<>();
	static List<String> ToppingsCounterUsageList = new ArrayList<>();
	static List<String> ColdDrinkCounterUsageList = new ArrayList<>();
	static List<String> FriesCounterUsageList = new ArrayList<>();

	// Stores the Pizzas Prices Individually
	static List<Integer> individualPrices = new ArrayList<>();
	static List<Integer> individualPizzaPrices = new ArrayList<>();
	static List<Integer> individualToppingPrices = new ArrayList<>();
	static int individualPricesCounter=0, individualPizzaPriceCounter=0, individualToppingPriceCounter = 0;
	
//	Coke Order Storage
	List<Integer> CokeOrderList = new ArrayList<>();
	
//	Fries Order Storage
	List<Integer> FriesOrderList = new ArrayList<>();
	
	//-1-Return and To display menu once.
	Boolean goBack = false, dispMenu = true;
	static boolean continueOrdering = false, generateReport = false;
	
	// Stores the No of Toppings for the Pizzas.
	ArrayList<Integer> toppingsPizza = new ArrayList<>();
	
	//Counts the number of Pizzas
	int pCnt =0 ;//Counts the number of Pizzas
	
	//Stores the Topping for the Pizza acc to its ID
	@SuppressWarnings("rawtypes")
	Map<Integer,ArrayList> toppingsCollection = new HashMap<>();
	
//	Final Order of Pizzas With their Toppings ArrayList
	@SuppressWarnings("rawtypes")
	ArrayList<Map> OrderList = new ArrayList<>();
	
	Map<Integer,Integer> demomap = new HashMap<>();
//	List to Store the veg pizzas from file
	static List<String> vegPizzasList = new ArrayList<>();
	
//	List to Store the non veg pizzas from file
	static List<String> nonVegPizzasList = new ArrayList<>();
	
//	List to Store the ColdDrinks from file
	static List<String> coldDrinksList = new ArrayList<>();
	
//	List to Store the Fries from file
	static List<String> FriesList = new ArrayList<>();
	
//	List to Store the Toppings from the File
	static List<String> ToppingsList = new ArrayList<>();
	
//	List to Store the pizza Prices from file
	static List<String> pizzaPriceList = new ArrayList<>();
	
//	List to Store the cold drink Prices from file
	static List<String> coldDrinkPriceList = new ArrayList<>();
	
//	List to Store the fries Prices from file
	static List<String> friesPriceList = new ArrayList<>();
	
//	List to Store the pizza Prices from file
	static List<String> nonVegPizzaPriceList = new ArrayList<>();
	
//	List to Store the pizza Prices from file
	static List<String> toppingsPriceList = new ArrayList<>();
	
//	List to hold Employee Details
	static List<String> employeesList = new ArrayList<>();
	
//	List to have Grand Total...
	static List<Integer> grandTotalList = new ArrayList<>();
	
//	List to count sales of pizzas for particular time Period
	static List<String> PizzasTotalForPeriod = new ArrayList<>();

//	List to count sales of coldDrink for particular time Period
	static List<String> ColdDrinkTotalForPeriod = new ArrayList<>();
	
//	List to count sales of Fries for particular time Period
	static List<String> FriesTotalForPeriod = new ArrayList<>();
	
//	List to count sales of Toppings for particular time Period
	static List<String> ToppingsTotalForPeriod = new ArrayList<>();

	 @SuppressWarnings("rawtypes")
	ArrayList UndoDeletion = new ArrayList<>();
	Object a;
	 static String tName, cName, fName;
	String pizzaName;

	static String coldDrinkName, friesName, toppingsName;
	 
	int p1VegPizzaCounter, p1NonVegPizzaCounter, p1ColdDrinkCounter, p1FriesCounter, p1ToppingsCounter, p1CancelPizzaCounter;
	int p2VegPizzaCounter, p2NonVegPizzaCounter, p2ColdDrinkCounter, p2FriesCounter, p2ToppingsCounter, p2CancelPizzaCounter;
	int p3VegPizzaCounter, p3NonVegPizzaCounter, p3ColdDrinkCounter, p3FriesCounter, p3ToppingsCounter, p3CancelPizzaCounter;
	int p4VegPizzaCounter, p4NonVegPizzaCounter, p4ColdDrinkCounter, p4FriesCounter, p4ToppingsCounter, p4CancelPizzaCounter;
	static String p1,p2,p3,p4;
	
	/**
	 * This method displays the Pizza's name which is been read from the file
	 * @param pizzaCode
	 * @return String
	 */
	 String displayPizzaName(int pizzaCode) {
		 if(pizzaCode <= vegPizzaCount) {
			 for(int i=0; i<=vegPizzasList.size(); i++) {
				 if((pizzaCode - 1) == i) {
					 pizzaName = vegPizzasList.get(i);
//					 p.overAllDue(i);
				 }
			 }
		 }
		 else if(pizzaCode > vegPizzaCount) {
			 for(int i=0; i<=nonVegPizzasList.size(); i++) {
				 if((pizzaCode - vegPizzaCount -1) == i) {
					 pizzaName = nonVegPizzasList.get(i);
				 }
			 }
		 }
		return pizzaName;
	 }

	/**
	 * This method displays the Cold Drink's name which is been read from the file
	 * @param int coldDrinkCode
	 * @return String
	 * @author Shubham Parmar
	 */
	 
	 static String displayColdDrinkName(int coldDrinkCode) {
		 for(int i=0;i<=coldDrinkCount;i++) {
			 if((coldDrinkCode-1) == i) {
				 
				 coldDrinkName = coldDrinksList.get(i);
			 }
		 }
		return coldDrinkName;
	 }
	 
		/**
		 * This method displays the Fries's name which is been read from the file
		 * @param int FriesCode
		 * @return String
		 * @author Shubham Parmar
		 */
	 static String displayFriesName(int FriesCode) {
		 for(int i=0;i<=FriesCount;i++) {
			 if((FriesCode-1) == i) {
				 friesName = FriesList.get(i);
			 }
		 }
		return friesName;
	 }
	 
		/**
		 * This method displays the Toppings's name which is been read from the file
		 * @param int ToppingsCode
		 * @return String
		 * @author Shubham Parmar
		 */
	 static String displayToppingsName(int ToppingsCode) {
		 for(int i=0;i<=toppingsCount;i++) {
			 if((ToppingsCode) == i) {
				 toppingsName = ToppingsList.get(i);
			 }
		 }
		return toppingsName;
	 }
	 
	 /**
	  * Converts the prices provided in the file to map
	  * @param priceList
	  * @param priceHolder
	  */
	 static void PriceDistribution(List<String> priceList, Map<Integer, Integer> priceHolder) {
		 for(int i=0;i<priceList.size();i++) {
			 String tokens[] = (priceList.get(i)).split(" ");
			 priceHolder.put(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
		
		 }
	 }
	 
	 static void EmployeeKeyValue(List<String> priceList, Map<String, String> priceHolder) {
		 for(int i=0;i<priceList.size();i++) {
			 String tokens[] = (priceList.get(i)).split(" ");
			 priceHolder.put((tokens[0]), (tokens[1]));
		
		 }
	 }
	 
	 	/**
	 	 * This method does the Summation of the  pizza's prices that are being ordered
	 	 * @param PizzaCode
	 	 * @return int
	 	 * @author Shubham Parmar
	 	 */
		int pizzaTotal(int PizzaCode){
			if(PizzaCode <= vegPizzaCount) {
				for(int i=1; i<=vegPizzasList.size(); i++) {
					if((int)PizzaCode == i) {
						p.setSum((int) pizzaPriceHolder.get(PizzaCode));
					}
				}
			}
			else if(PizzaCode > vegPizzaCount) {
				for(int i=1; i<=nonVegPizzasList.size(); i++) {
					if((int)(PizzaCode-vegPizzaCount) == i) {
						p.setSum((int) nonVegPizzaPriceHolder.get(i));
					}
				}
			}
			return p.getSum();
		}
		
//		static obj
		static PizzerioVegPizzas pizzerioInstance = new PizzerioVegPizzas();
		/**
	 	 * This method does the Summation of the  ColdDrink's prices that are being ordered
	 	 * @param int coldDrinkCode
	 	 * @return int
	 	 * @author Shubham Parmar
	 	 */
		static int coldDrinkTotal(int coldDrinkCode) {
			for(int i=0; i<=coldDrinksList.size(); i++) {
				if(coldDrinkCode == i) {
					pizzerioInstance.setSum((int) coldDrinkPriceHolder.get(coldDrinkCode));
				}
			}
			return pizzerioInstance.getSum();
		}
	
		/**
	 	 * This method does the Summation of the  Frie's prices that are being ordered
	 	 * @param int friesCode
	 	 * @return int 
	 	 * @author Shubham Parmar
	 	 */
		static int friesTotal(int friesCode) {
			for(int i=0; i<=FriesList.size(); i++) {
				if(friesCode == i) {
					pizzerioInstance.setSum((int) friesPriceHolder.get(friesCode));
				}
			}
			return pizzerioInstance.getSum();
		}
		
		/**
	 	 * This method does the Summation of the  Topping's prices that are being ordered
	 	 * @param toppingsCode
	 	 * @return int
	 	 * @author Shubham Parmar
	 	 */
		static int toppingsTotal(int toppingsCode) {
			for(int i=0; i<=ToppingsList.size(); i++) {
				if(toppingsCode == i) {
					pizzerioInstance.setSum((int) toppingsPriceHolder.get(toppingsCode));
				}
			}
			return pizzerioInstance.getSum();
		}

		/**
		 * Counts the no of items sold
		 * @param CounterList
		 * @author Shubham Parmar
		 */
		static List<Integer> ItemsCounter(List<String> CounterList, int noOfItem) {
		 	List<String> Demo = new ArrayList<>();// Clear DemoList before reusing it
	        List<Integer> val = new ArrayList<>();
	       

//	        List<Integer> numbers = new ArrayList<>();
//	        for (String numberString : CounterList) {
//	            int number = Integer.parseInt(numberString);
//	            numbers.add(number);
//	        }
//
//	        int maxNumber = Collections.max(numbers);
//	        
	        for (int i = 1; i <= noOfItem; i++) {
	            String code = Integer.toString(i);
	            Demo.add(code);
	        }
	        
	        for (int i = 0; i < Demo.size(); i++) {
	            int Counter = 0;
	            for (int j = 0; j < CounterList.size(); j++) {
	                if ((Demo.get(i)).equals(CounterList.get(j))) {
	                    Counter++;
//	                    CounterList.remove(j);
//	                    j--; // Adjust the index after removal
	                }
	            }
	            val.add(Counter);
	        }
	        
	        return val;
	   }
		
//		Toppings Collections     list  							    int				List					list
//		WriteTotalCollection(PizzasOrder.ToppingsCounterUsageList,noOfToppings,PizzasOrder.ToppingsList, PizzasOrder.toppingsPriceHolder)
		/**
		 * This method writes the total collection of Sold Items right from the First day of Installation
		 * @param List<Integer> ToppingCounters
		 * @param int noOfItems
		 * @param int start
		 * @param List<String> ItemList
		 * @param Map<Integer, Integer> priceHolders
		 * @param FileWriter itemsCountingFileWriter
		 * @throws IOException
		 * @author Shubham Parmar
		 */
		void WriteTotalCollection(List<String> ToppingCounters, int noOfItems,int start,List<String> ItemList,Map<Integer,Integer> priceHolders, FileWriter itemsCountingFileWriter) throws IOException{
		List<Integer> value = new ArrayList<>();
		value=PizzasOrder.ItemsCounter(ToppingCounters, noOfItems);
		int v=start;
		int PizzaCounter=1;
		itemsCountingFileWriter.write("\n TOPPINGS COLLECTION :  \t\t\t  Sold Qty :\tTotal Collection : \n");
			for(String itemHolder : ItemList) {
				int totalPrc = 1;
				if(v<noOfItems) {
					String nxt = String.format("%-32s",PizzaCounter + ". " + itemHolder);
					String val = String.format("%-20s", value.get(v));
					itemsCountingFileWriter.write(nxt +  " \t: " + val + ""+((value.get(v))*(priceHolders.get(totalPrc)))+"\n");
					grandTotalList.add((value.get(v))*(priceHolders.get(totalPrc)));
				}
				PizzaCounter++;
				v++;totalPrc++;
			}
		}
		
		/**
		 * This method clears all the counters used to generate the Sales Report 
		 * @author Shubham Parmar
		 */
		 
		void clearReportCounter() {
			p1VegPizzaCounter=0;
			p1NonVegPizzaCounter=0;
			p2VegPizzaCounter=0;
			p2NonVegPizzaCounter=0;
			p3VegPizzaCounter=0;
			p3NonVegPizzaCounter=0;
			p4VegPizzaCounter=0;
			p4NonVegPizzaCounter=0;
			p1ColdDrinkCounter = 0;
			p2ColdDrinkCounter = 0;
			p3ColdDrinkCounter = 0;
			p4ColdDrinkCounter = 0;
			p1FriesCounter = 0;
			p2FriesCounter = 0;
			p3FriesCounter = 0;
			p4FriesCounter = 0;
			p1ToppingsCounter = 0;
			p2ToppingsCounter = 0;
			p3ToppingsCounter = 0;
			p4ToppingsCounter = 0;
		}
		
	float gstApplied() {
		float totalBill = (pizzaBill + toppingsBill + ColdDBill + FriesBill) ;
		return p.getGST(totalBill);
	}
	
	float grandTotal() {
		return pizzaBill + toppingsBill + ColdDBill + FriesBill + gstApplied();
	}
	
	
}
