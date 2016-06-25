package com.example.android.justjava;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends Activity{
    private int coffes;
    private final int PRICE=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        coffes=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayPrice(coffes * PRICE);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * Add one coffee to the total order
     */
    public void addOneCoffee(View view){
        coffes++;
        display(coffes);
    }

    /**
     *Decreases the total order by one coffee
     */
    public void decreaseOneCoffee(View view){
        coffes--;
        display(coffes);
    }
}