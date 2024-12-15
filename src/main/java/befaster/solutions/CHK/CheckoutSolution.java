package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if(skus == null || !skus.matches("^[ABCD]*[E]*$")){
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

        int totalPrice = 0;
        for(Map.Entry<Character, Integer> entry: skuCounts.entrySet()){
            char sku = entry.getKey();
            int count = entry.getValue();

             if(discountOffers.containsKey(sku)) {
                Discount discount = discountOffers.get(sku);

                 if(sku == 'A'){
                     if (count>=5){
                         totalPrice += (count / 5) * discount.bundlePrice[1];
                         count *= 5;
                     }
                     if(count>=3){
                         totalPrice += (count / 3) * discount.bundlePrice[0];
                         count %= 3;
                     }

                     totalPrice += count * itemPrices.get(sku);
                 }
                else if(discount.freeItem !=0){
                    //int freeBCount = (count / discount.bundleSize) * (count / discount.bundleSize);
                     int freeBCount = (count / discount.bundleSize);
                     int remainingECount = count % discount.bundleSize;

                    totalPrice += (remainingECount * itemPrices.get(sku));
                    totalPrice +=  freeBCount * itemPrices.get('B');
                }
                else {
                    totalPrice += (count / discount.bundleSize) * discount.bundlePrice;
                    totalPrice += (count % discount.bundleSize) * itemPrices.get(sku);
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

