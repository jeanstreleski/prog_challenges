/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JEAN STRELESKI
 *
 * Google Code Jam - Store Credit
 *
 * You receive a credit C at a local store and would like to buy two items. You
 * first walk through the store and create a list L of all available items. From
 * this list you would like to buy two items that add up to the entire value of
 * the credit. The solution you provide will consist of the two integers
 * indicating the positions of the items in your list (smaller number first).
 *
 * Input
 *
 * The first line of input gives the number of cases, N. N test cases follow.
 * For each test case there will be:
 *
 * One line containing the value C, the amount of credit you have at the store.
 * One line containing the value I, the number of items in the store. One line
 * containing a space separated list of I integers. Each integer P indicates the
 * price of an item in the store. Each test case will have exactly one solution.
 * Output
 *
 * For each test case, output one line containing "Case #x: " followed by the
 * indices of the two items whose price adds up to the store credit. The lower
 * index should be output first.
 *
 * Limits
 *
 * 5 ≤ C ≤ 1000 1 ≤ P ≤ 1000
 *
 * Small dataset
 *
 * N = 10 3 ≤ I ≤ 100
 *
 * Large dataset
 *
 * N = 50 3 ≤ I ≤ 2000
 *
 * Input 3 - number of test cases
 * 
* Case 1: 100 - credit 3 - number of itens on store 5 75 25 - price of each
 * item
 *
 * Case 2: 200 7 150 24 79 50 88 345 3
 *
 * Case 3: 8 8 2 1 9 4 4 56 90 3
 *
 * Output Case #1: 2 3 Case #2: 1 4 Case #3: 4 5
 *
 *
 * URL: https://code.google.com/codejam/contest/351101/dashboard#s=p0
 *
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Main m = new Main();

    }

    public Main() {
        UICreditAnalysis ui = new UICreditAnalysis();
    }

    private class CalcCreditUse {

        private String runCreditAnalysis(UICreditAnalysis.TestCase t) {
            int i;
            int x = 0;
            int soma=0;
            for (i = 0; i < t.getAmountOfItensStore(); i++) {
                soma = 0;
                x = i + 1;
                do {
                    if (x < t.getAmountOfItensStore())
                        soma = t.getListOfItensAtStore().get(i).getPriceOfItem() + t.getListOfItensAtStore().get(x).getPriceOfItem();
                    x++;                    
                }while ((soma != t.amountOfCredit) && (x <= t.getAmountOfItensStore()));                    
                    
                                 
                if (soma == t.getAmountOfCredit()) {                    
                    break;
                }

            }
            if (soma > 0 )
                return String.valueOf(i+1).concat(", ").concat(String.valueOf(x));
            else
                return "There is no result to show";
                  

        }

    }

    private final class UICreditAnalysis {

        public UICreditAnalysis() {
            this.startUI();
            
        }

        private void startUI() {
            List<TestCase> listOfTestCases = new ArrayList<TestCase>();
            int numberOfTestCases = this.inputNumberOfTestCases();

            for (int i = 0; i < numberOfTestCases; i++) {
                listOfTestCases.add(i, this.inputOfTestCase());
                System.out.println(listOfTestCases.get(i));
            }
            CalcCreditUse calc = new CalcCreditUse();
            int count = 1;
            for (TestCase t : listOfTestCases) {
                System.out.println("Case #".concat(String.valueOf(count).concat(" :").concat(calc.runCreditAnalysis(t))));
                count++;
            }

        }

        private int inputNumberOfTestCases() {
            System.out.println("Please type number of test cases");
            BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

            int numberOfTestCases = 0;
            do {
                try {
                    numberOfTestCases = Integer.valueOf(buf.readLine()).intValue();
                } catch (IOException ex) {
                    System.out.println("Invalid format. Please type a number of test cases.");
                    numberOfTestCases = 0;
                }
            } while (numberOfTestCases <= 0);
            return numberOfTestCases;
        }

        public TestCase inputOfTestCase() {
            BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
            String lineTyped = "";
            do {
                try {
                    System.out.println("Please type amount of credit, number of itens on store and price of each item.");
                    lineTyped = buf.readLine();
                } catch (IOException ex) {
                    System.out.println("Please enter with real values or type QUIT to quit");
                }
            } while ((lineTyped.equals("") || (lineTyped.equals("QUIT"))));

            if (lineTyped.equals("QUIT")) {
                return null;
            } else {
                //This part of code might be used on specific class of control view (by DDD theory)
                TestCase testCase = new TestCase();
                String subStringAux = "";
                String partOfLine = "";

                // while to get amount of credit
                int aux = 0;
                do {
                    partOfLine = lineTyped.substring(aux, aux + 1);
                    if (!partOfLine.equals(" ")) {
                        subStringAux = subStringAux.concat(partOfLine);
                    }
                    aux = aux + 1;
                } while (!partOfLine.equals(" "));
                // must have a test to check if value typed contains any kind of String value before make a ValueOf
                testCase.setAmountOfCredit(Integer.valueOf(subStringAux).intValue());

                // while to get amount of itens on store
                subStringAux = "";
                partOfLine = "";
                do {
                    partOfLine = lineTyped.substring(aux, aux + 1);
                    if (!partOfLine.equals(" ")) {
                        subStringAux = subStringAux.concat(partOfLine);
                    }
                    aux = aux + 1;
                } while (!partOfLine.equals(" "));

                testCase.setAmountOfItensStore(Integer.valueOf(subStringAux).intValue());

                // while to get price of each item store
                for (int x = 0; x < testCase.getAmountOfItensStore(); x++) {
                    subStringAux = "";
                    partOfLine = "";
                    do {
                        if (aux < lineTyped.length()) {
                            partOfLine = lineTyped.substring(aux, aux + 1);
                            if (!partOfLine.equals(" ")) {
                                subStringAux = subStringAux.concat(partOfLine);
                            }
                        } else {
                            partOfLine = " ";
                        }
                        aux = aux + 1;

                    } while (!partOfLine.equals(" "));

                    testCase.addStoreItem(x, Integer.valueOf(subStringAux).intValue());
                }

                return testCase;
            }

        }

        private class TestCase {

            private int amountOfCredit;
            private int amountOfItensStore;
            private List<StoreItem> listOfItensAtStore = new ArrayList<StoreItem>();

            public int getAmountOfCredit() {
                return amountOfCredit;
            }

            public void setAmountOfCredit(int amountOfCredit) {
                this.amountOfCredit = amountOfCredit;
            }

            public int getAmountOfItensStore() {
                return amountOfItensStore;
            }

            public void setAmountOfItensStore(int amountOfItensStore) {
                this.amountOfItensStore = amountOfItensStore;
            }

            public List<StoreItem> getListOfItensAtStore() {
                return listOfItensAtStore;
            }

            public void addStoreItem(int index, int priceOfItem) {
                StoreItem storeItem = new StoreItem();
                storeItem.setIndexOfItem(index);
                storeItem.setPriceOfItem(priceOfItem);

                this.listOfItensAtStore.add(index, storeItem);
            }

            private class StoreItem {

                private int indexOfItem;
                private int priceOfItem;

                public int getIndexOfItem() {
                    return indexOfItem;
                }

                public void setIndexOfItem(int indexOfItem) {
                    this.indexOfItem = indexOfItem;
                }

                public int getPriceOfItem() {
                    return priceOfItem;
                }

                public void setPriceOfItem(int priceOfItem) {
                    this.priceOfItem = priceOfItem;
                }

            }

        }

    }
}
