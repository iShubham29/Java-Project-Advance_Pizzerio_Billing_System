package pizzerioV06;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class PizzerioVegPizzas {
	
	private int price;
	private int sum;
	
	int getSum() {
		return sum;
	}

	void setSum(int sum) {
		this.sum += sum;
	}

	float getGST(float totalBill) {
		float GST = 0.0f;
		GST =  (float) ((totalBill * 18.0)/100) ;
		return GST;
	}

	
}
