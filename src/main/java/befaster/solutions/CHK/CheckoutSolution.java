package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if(skus == null || !skus.matches("[A-D]*")){
            return -1;
        }

        Map<Character, Integer> itemPrices = Map.of(
                'A', 50,
                'B', 30,
                'C', 20,
                'D', 15
        );

        Map<Character, Discount> discountOffers = new HashMap<>();
        discountOffers.put('A', new Discount(3, 130));
        discountOffers.put('B', new Discount(2, 45));

        Map<Character, Integer> skuCounts = new HashMap<>();

        for(char sku: skus.toCharArray()){
            skuCounts.put(sku, skuCounts.getOrDefault(sku,0) +1);
        }

        int totalPrice = 0;
        for(Map.Entry<Character, Integer> entry: skuCounts.entrySet()){
            char sku = entry.getKey();
            int count = entry.getValue();

            if(discountOffers.containsKey(sku)) {
                Discount discount = discountOffers.get(sku);
                totalPrice += (count / discount.bundleSize) * discount.bundlePrice;
                totalPrice += (count % discount.bundleSize) *  itemPrices.get(sku);
            }
            else {
                totalPrice += count * itemPrices.get(sku);
            }
        }

        return totalPrice;
        //throw new SolutionNotImplementedException();
    }

    static class Discount{
        int bundleSize;
        int bundlePrice;

        Discount(int bundleSize, int bundlePrice){
            this.bundleSize = bundleSize;
            this.bundlePrice = bundlePrice;
        }
    }
}
