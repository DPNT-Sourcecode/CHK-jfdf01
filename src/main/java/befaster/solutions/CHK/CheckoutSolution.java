package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;

import java.util.*;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        //if(skus == null || !skus.matches("^[ABCD]*[E]*$")){
        if(skus == null || !skus.matches("^[ABCDEFGHIJKLMNOPQRSTUVWXYZ]*$")){
            return -1;
        }

        if(skus == "" )
        {
            return 0;
        }

        Map<Character, Integer> itemPrices = new HashMap<>();
        itemPrices.put('A', 50);
        itemPrices.put('B', 30);
        itemPrices.put('C', 20);
        itemPrices.put('D', 15);
        itemPrices.put('E', 40);
        itemPrices.put('F', 10);
        itemPrices.put('G', 20);
        itemPrices.put('H', 10);
        itemPrices.put('I', 35);
        itemPrices.put('J', 60);
        itemPrices.put('K', 70);
        itemPrices.put('L', 90);
        itemPrices.put('M', 15);
        itemPrices.put('N', 40);
        itemPrices.put('O', 10);
        itemPrices.put('P', 50);
        itemPrices.put('Q', 30);
        itemPrices.put('R', 50);
        itemPrices.put('S', 20);
        itemPrices.put('T', 20);
        itemPrices.put('U', 40);
        itemPrices.put('V', 50);
        itemPrices.put('W', 20);
        itemPrices.put('X', 17);
        itemPrices.put('Y', 20);
        itemPrices.put('Z', 21);

        Map<Character, Discount> discountOffers = new HashMap<>();
        discountOffers.put('A', new Discount(new int[]{3,5}, new int[]{130, 200}));
        discountOffers.put('B', new Discount(new int[]{2}, new int[]{45}));
        discountOffers.put('E', new Discount(2, 'B'));
        discountOffers.put('F', new Discount(2, 'F'));
        discountOffers.put('H', new Discount(new int[]{5,10}, new int[]{45, 80}));
        discountOffers.put('K', new Discount(new int[]{2}, new int[]{120}));
        discountOffers.put('N', new Discount(3, 'M'));
        discountOffers.put('P', new Discount(new int[]{5}, new int[]{200}));
        discountOffers.put('Q', new Discount(new int[]{3}, new int[]{80}));
        discountOffers.put('R', new Discount(3, 'Q'));
        discountOffers.put('U', new Discount(3, 'U'));
        discountOffers.put('V', new Discount(new int[]{2,3}, new int[]{90, 130}));

        Map<Character, Integer> skuCounts = new HashMap<>();
        char[] skuCharArray = skus.toCharArray();
        Arrays.sort(skuCharArray);
        for(char sku: skuCharArray){
            skuCounts.put(sku, skuCounts.getOrDefault(sku,0) + 1);
        }

        // ------------------------------E and B------------------------------
        int totalPrice = 0;

        int eCount = skuCounts.getOrDefault('E', 0);
        int bCount = skuCounts.getOrDefault('B', 0);

        if (eCount >= 2){
            totalPrice += eCount * itemPrices.get('E');
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


        // ------------------------------F------------------------------
        int fCount = skuCounts.getOrDefault('F', 0);
        if(fCount>=2){
            if(fCount % 3 ==0){
                int CountableC = fCount - (fCount/3);
                totalPrice += CountableC * itemPrices.get('F');
            }
            else if (fCount % 3 == 1){
                totalPrice += 1 * itemPrices.get('F');
                int CountableC = (fCount - 1) - (fCount/3);
                totalPrice += CountableC * itemPrices.get('F');
            }
            else if (fCount % 3 == 2){
                totalPrice += 2 * itemPrices.get('F');
                int CountableC = (fCount - 2) - (fCount/3);
                totalPrice += CountableC * itemPrices.get('F');
            }
        } else if (fCount==1) {
            totalPrice += 1 * itemPrices.get('F');
        }

        // ------------------------------K------------------------------
        int kCount = skuCounts.getOrDefault('K', 0);
        if(kCount > 1){
            if(kCount%2 ==0)
                totalPrice += (kCount/2) * discountOffers.get('K').bundlePrices[0];
            else{
                int evenPairs = kCount/2;
                totalPrice += 1 * itemPrices.get('K');
                totalPrice += evenPairs * discountOffers.get('K').bundlePrices[0];
            }
        }
        else if(kCount == 1){
            totalPrice += 1 * itemPrices.get('K');
        }

        // ------------------------------N and M------------------------------
        int nCount = skuCounts.getOrDefault('N', 0);
        int mCount = skuCounts.getOrDefault('M', 0);

        if (nCount >= 3){
            totalPrice += nCount * itemPrices.get('N');
            if(mCount > (nCount/3)){
                int leftM = mCount - nCount/3;
                if(leftM >= 1){
                    totalPrice += leftM * itemPrices.get('M');
                }
            }
        }
        else{
            totalPrice += nCount * itemPrices.get('N');
            totalPrice += mCount * itemPrices.get('M');

        }

        // ------------------------------P------------------------------
        int pCount = skuCounts.getOrDefault('P', 0);
        if(pCount >= 5){
            if(pCount%5 ==0)
                totalPrice += (pCount/5) * discountOffers.get('P').bundlePrices[0];
            else{
                int disCountedP = pCount / 5;
                totalPrice += disCountedP * discountOffers.get('P').bundlePrices[0];
                int pLeft = pCount % 5;
                totalPrice += pLeft * itemPrices.get('P');
            }
        }
        else if(pCount == 1){
            totalPrice += 1 * itemPrices.get('P');
        }
        else if(pCount == 2){
            totalPrice += 2 * itemPrices.get('P');
        }
        else if(pCount == 3){
            totalPrice += 3 * itemPrices.get('P');
        }
        else if(pCount == 4){
            totalPrice += 4 * itemPrices.get('P');
        }


        // ------------------------------R and Q------------------------------

        int rCount = skuCounts.getOrDefault('R', 0);
        int qCount = skuCounts.getOrDefault('Q', 0);

        if (rCount >= 3){
            totalPrice += rCount * itemPrices.get('R');
            if(qCount > (rCount/3)){
                int leftQ = qCount - rCount/3;
                if(leftQ >= 1){
                    if(leftQ%3 ==0)
                        totalPrice += (leftQ/3) * discountOffers.get('Q').bundlePrices[0];
                    else{
                        int disCountedQ = leftQ / 3;
                        totalPrice += disCountedQ * discountOffers.get('Q').bundlePrices[0];
                        int qLeft = leftQ%3;
                        totalPrice += qLeft * itemPrices.get('Q');
                    }

                }
            }
        }
        else{
            totalPrice += rCount * itemPrices.get('R');
            if(qCount == 1 || qCount == 2 ){
                totalPrice += qCount * itemPrices.get('Q');
            }
            if(qCount > 2){
                if(qCount%3 ==0)
                    totalPrice += (qCount/3) * discountOffers.get('Q').bundlePrices[0];
                else{
                    int disCountedQ = qCount / 3;
                    totalPrice += disCountedQ * discountOffers.get('Q').bundlePrices[0];
                    int qLeft = qCount%3;
                    totalPrice += qLeft * itemPrices.get('Q');
                }
            }
        }

        // ------------------------------U------------------------------
        int uCount = skuCounts.getOrDefault('U', 0);
        if(uCount>3){
            if(uCount % 4 ==0){
                int CountableU = uCount - (uCount/4);
                totalPrice += CountableU * itemPrices.get('U');
            }
            else if (uCount % 4 == 1){
                totalPrice += 1 * itemPrices.get('U');
                int CountableU = (uCount - 1) - (uCount/4);
                totalPrice += CountableU * itemPrices.get('U');
            }
            else if (uCount % 4 == 2){
                totalPrice += 2 * itemPrices.get('U');
                int CountableU = (uCount - 2) - (uCount/4);
                totalPrice += CountableU * itemPrices.get('U');
            }
            else if (uCount % 4 == 3){
                totalPrice += 3 * itemPrices.get('U');
                int CountableU = (uCount - 3) - (uCount/4);
                totalPrice += CountableU * itemPrices.get('U');
            }
        }
        else if (uCount==3) {
            totalPrice += 3 * itemPrices.get('U');
        }
        else if (uCount==2) {
            totalPrice += 2 * itemPrices.get('U');
        }
        else if (uCount==1) {
            totalPrice += 1 * itemPrices.get('U');
        }

        // ------------------------------'S','T','X','Y','Z'------------------------------
        int groupOfferPrice = 45;

        List<Character> groupSku = Arrays.asList('S','T','X','Y','Z');
        List<Character> sortedGroupSku = new ArrayList<>(groupSku);
        sortedGroupSku.sort(Comparator.comparingInt(itemPrices::get));

        int groupCount = 0;
        for(char item: groupSku){
            groupCount += skuCounts.getOrDefault(item,0);
        }

        int groupBundles = groupCount / 3;
        int remainingSku = groupCount % 3;

        totalPrice += groupBundles * groupOfferPrice;

        for(char item: sortedGroupSku){
            int count = skuCounts.getOrDefault(item,0);
            if(remainingSku <= 0){
                break;
            }
            int usedForRemaining = Math.min(count, remainingSku);

            totalPrice += usedForRemaining * itemPrices.get(item);
            remainingSku -= usedForRemaining;
        }

        for(char sku: skuCounts.keySet()){
            if(sku == 'E' || sku == 'B' || sku == 'F' || sku == 'K' || sku == 'M' || sku == 'N'
                    || sku == 'P' || sku == 'Q' || sku == 'R' || sku == 'U'
                    || sku == 'S' || sku == 'T' || sku == 'X' || sku == 'Y' || sku == 'Z'){
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
                if(sku == 'H'){
                    if (count>=10){
                        totalPrice += (count / 10) * discount.bundlePrices[1];
                        count %= 10;
                    }
                    if(count>=5){
                        totalPrice += (count / 5) * discount.bundlePrices[0];
                        count %= 5;
                    }

                    totalPrice += count * itemPrices.get(sku);
                }
                if(sku == 'V'){
                    if (count>=3){
                        totalPrice += (count / 3) * discount.bundlePrices[1];
                        count %= 3;
                    }
                    if(count>=2){
                        totalPrice += (count / 2) * discount.bundlePrices[0];
                        count %= 2;
                    }

                    totalPrice += count * itemPrices.get(sku);
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
         Discount(int bundleSize, char freeItem) {
            this.bundleSizes = new int[]{bundleSize};
            this.bundlePrices = new int[]{0}; // NO price for the free item offer
            this.freeItem = freeItem;
        }
    }
}

