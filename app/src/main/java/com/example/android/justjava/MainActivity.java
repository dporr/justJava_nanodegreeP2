package com.example.android.justjava;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
     *@return the name of the person who is ordering
     */
    private String getOrderName(){
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        return nameEditText.getText().toString();

    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price=calculatePrice();
        boolean addWhippedCream= hasWhippedCream();
        boolean addChocolate = hasChocolate();
        String name= getOrderName();
        String order = orderSummary(name,price,addWhippedCream,addChocolate);
        displayPrice(order);
    }

    /**
     * Create summary of the order.
     *
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants whipped cream topping
     * @param price of the order
     * @return text summary
     */
    private String orderSummary(String name,int price,boolean addWhippedCream,boolean addChocolate){
        String order="\n"+name +
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
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
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