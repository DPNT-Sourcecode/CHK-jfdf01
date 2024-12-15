package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        //if(skus == null || !skus.matches("^[ABCD]*[E]*$")){
        if(skus == null || !skus.matches("^[ABCDEF]*$")){
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
                'E', 40,
                'F', 10
        );

        Map<Character, Discount> discountOffers = new HashMap<>();
        discountOffers.put('A', new Discount(new int[]{3,5}, new int[]{130, 200}));
        discountOffers.put('B', new Discount(new int[]{2}, new int[]{45}));
        discountOffers.put('E', new Discount(2, 'B'));
        discountOffers.put('F', new Discount(2, 'F'));

        Map<Character, Integer> skuCounts = new HashMap<>();
        for(char sku: skus.toCharArray()){
            skuCounts.put(sku, skuCounts.getOrDefault(sku,0) + 1);
        }



        int totalPrice = 0;

        int eCount = skuCounts.getOrDefault('E', 0);
        int bCount = skuCounts.getOrDefault('B', 0);

        if (eCount >= 2){
            totalPrice = eCount * itemPrices.get('E');
            if(bCount > (eCount/2)){
                int leftB = bCount - eCount/2;
                if(leftB >= 1){
                    if(leftB%2 ==0)
                        totalPrice += (leftB/2) * discountOffers.get('B').bundlePrices[0];
                    else{
                        int evenPairs = leftB/2;
                        totalPrice += 1 * itemPrices.get('B');
                        totalPrice += evenPairs * discountOffers.get('B').bundlePrices[0];
                    }

                }
            }
        }
        else{
            totalPrice += eCount * itemPrices.get('E');
            if(bCount == 1){
                totalPrice += bCount * itemPrices.get('B');
            }
            if(bCount > 1){
                if(bCount%2 ==0)
                    totalPrice += (bCount/2) * discountOffers.get('B').bundlePrices[0];
                else{
                    int evenPairs = bCount/2;
                    totalPrice += 1 * itemPrices.get('B');
                    totalPrice += evenPairs * discountOffers.get('B').bundlePrices[0];
                }
            }
        }

        int fCount = skuCounts.getOrDefault('F', 0);
        if(fCount>=2){
            if(fCount % 3 ==0){
                int CountableC = fCount - (fCount/3);
                totalPrice += CountableC * discountOffers.get('F').bundlePrices[0];
            }
        }

        //for(Map.Entry<Character, Integer> entry: skuCounts.entrySet()){
        for(char sku: skuCounts.keySet()){
            if(sku == 'E' || sku == 'B'){
                continue;
            }
            int count = skuCounts.get(sku);
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
                 else if(sku == 'B') {
                     totalPrice += (count / 2) * discount.bundlePrices[0];
                     count %= 2;
                     totalPrice +=  count * itemPrices.get(sku);
                 }
            }
            else {
                totalPrice += count * itemPrices.get(sku);
            }
        }
        //totalPrice +=  skuCounts.getOrDefault('C', 0) * itemPrices.get('C');
        //totalPrice +=  skuCounts.getOrDefault('D', 0) * itemPrices.get('D');

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
         Discount(int bundleSize, char freeItem) {
            this.bundleSizes = new int[]{bundleSize};
            this.bundlePrices = new int[]{0}; // NO price for the free item offer
            this.freeItem = freeItem;
        }
    }
}




