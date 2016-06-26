package com.example.android.justjava;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends Activity{
    private int numberOfCoffees;
    private final int COFFEE_PRICE=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        numberOfCoffees =0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *@return true if adds whipped cream to the order
     */
    private boolean hasWhippedCream(){
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        return whippedCreamCheckBox.isChecked();
    }

    /**
     *@return true if adds chocolate to the order
     */
    private boolean hasChocolate(){
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        return chocolateCheckBox .isChecked();
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price=calculatePrice();
        boolean addWhippedCream= hasWhippedCream();
        boolean addChocolate = hasChocolate();
        String order = orderSummary(price,addWhippedCream,addChocolate);
        displayPrice(order);
    }

    private String orderSummary(int price,boolean addWhippedCream,boolean addChocolate){
        String order="Name:Diego Porras" +
                "\nQuantity: "+numberOfCoffees+
                "\nAdd whipped cream:"+ addWhippedCream +
                "\nAdd chocolate:"+ addChocolate +
                "\nTotal: " +NumberFormat.getCurrencyInstance().format(price)+
                "\nThank you!";
        return order;
    }
    /**
     * @return  total price based on the number of ordered coffees
     */
    private int calculatePrice(){
        return numberOfCoffees * COFFEE_PRICE;
    }
    /**
     * This method displays the given quantity value on the screen.
     * @param number of ordered coffees
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the total price on the screen.
     */
    private void displayPrice(String order) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(order);
    }

    /**
     * Add one coffee to the total order
     */
    public void addOneCoffee(View view){
        numberOfCoffees++;
        displayQuantity(numberOfCoffees);
    }

    /**
     *Decreases the total order by one coffee
     */
    public void decreaseOneCoffee(View view){
        if(numberOfCoffees ==0) return;
        numberOfCoffees--;
        displayQuantity(numberOfCoffees);
    }
}