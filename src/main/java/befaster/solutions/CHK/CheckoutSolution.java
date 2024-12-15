package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        //if(skus == null || !skus.matches("^[ABCD]*[E]*$")){
        if(skus == null || !skus.matches("^[ABCDE]*$")){
            return -1;
        }

        if(skus == "" )
        {
            return 0;
        }

        Map<Character, Integer> itemPrices = Map.of(
                'A', 50,
                'B', 30,
                'C', 20,
                'D', 15,
                'E', 40
        );

        Map<Character, Discount> discountOffers = new HashMap<>();
        discountOffers.put('A', new Discount(new int[]{3,5}, new int[]{130, 200}));
        discountOffers.put('B', new Discount(new int[]{2}, new int[]{45}));
        discountOffers.put('E', new Discount(2,0, 'B'));

        Map<Character, Integer> skuCounts = new HashMap<>();

        for(char sku: skus.toCharArray()){
            skuCounts.put(sku, skuCounts.getOrDefault(sku,0) +1);
        }

        /*int eCount = skuCounts.getOrDefault('E', 0);
        int bCount = skuCounts.getOrDefault('B', 0);

        if(eCount >=2){
            int freeBCount = eCount / 2;
            int remainingECount = eCount % 2;


        }*/

        int totalPrice = 0;
        for(Map.Entry<Character, Integer> entry: skuCounts.entrySet()){
            char sku = entry.getKey();
            int count = entry.getValue();

             if(discountOffers.containsKey(sku)) {
                Discount discount = discountOffers.get(sku);

                 if(sku == 'A'){
                     if (count>=5){
                         totalPrice += (count / 5) * discount.bundlePrices[1];
                         count %= 5;
                     }
                     if(count>=3){
                         totalPrice += (count / 3) * discount.bundlePrices[0];
                         count %= 3;
                     }

                     totalPrice += count * itemPrices.get(sku);
                 }
                 else if(sku == 'E') {
                     //continue;
                     int freeBCount = count / 2;
                     int remainingECount = count % 2;

                     totalPrice += remainingECount * itemPrices.get(sku);
                     totalPrice +=  freeBCount * itemPrices.get('B');
                 }

                else {
                    totalPrice += (count / discount.bundleSizes[0]) * discount.bundlePrices[0];
                    totalPrice += (count % discount.bundleSizes[0]) * itemPrices.get(sku);
                }
            }
            else {
                totalPrice += count * itemPrices.get(sku);
            }
        }

        return totalPrice;
        //throw new SolutionNotImplementedException();
    }

    static class Discount{
        int[] bundleSizes;
        int[] bundlePrices;
        char freeItem;

        Discount(int[] bundleSizes, int[] bundlePrices){
            this.bundleSizes = bundleSizes;
            this.bundlePrices = bundlePrices;
            this.freeItem = 0;
        }

        // free item
         Discount(int bundleSize, int bundlePrice, char freeItem) {
            this.bundleSizes = new int[]{bundleSize};
            this.bundlePrices = new int[]{bundlePrice}; // NO price for the free item offer
            this.freeItem = freeItem;
        }
    }
}





