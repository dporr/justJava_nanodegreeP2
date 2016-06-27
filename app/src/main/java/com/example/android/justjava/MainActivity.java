package com.example.android.justjava;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends Activity{
    private int numberOfCoffees=1;
    private final int COFFEE_PRICE=5;
    private final int WHIPPED_CREAM_FEE=1;
    private final int CHOCOLATE_FEE=2;
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
     * Composes an email including the order summary as the body
     * @param order
     */

    public void sendOrderEmail(String order){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for " +getOrderName());
        intent.putExtra(Intent.EXTRA_TEXT, order);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

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
        sendOrderEmail(order);
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
        String order="\n"+getString(R.string.name)+name +
                "\n"+getString(R.string.quantity) +": "+numberOfCoffees+
                "\n"+getString(R.string.add_whipped_cream)+ addWhippedCream +
                "\n"+getString(R.string.add_chocolate)+ addChocolate +
                "\nTotal: " +NumberFormat.getCurrencyInstance().format(price)+
                getString(R.string.thank_you);
        return order;
    }
    /**
     * @return  total price based on the number of ordered coffees
     */
    private int calculatePrice()
    {
        int total= numberOfCoffees * COFFEE_PRICE;
        if(hasWhippedCream()) total += numberOfCoffees * WHIPPED_CREAM_FEE;
        if(hasChocolate()) total += numberOfCoffees * CHOCOLATE_FEE;
        return total;
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
        if(numberOfCoffees == 100){
            Toast.makeText(this,"Cannot order more than 100 coffees.",Toast.LENGTH_SHORT).show();
            return;}
        numberOfCoffees++;
        displayQuantity(numberOfCoffees);
    }

    /**
     *Decreases the total order by one coffee
     */
    public void decreaseOneCoffee(View view){
        if(numberOfCoffees ==1){
            Toast.makeText(this,"Cannot order negative number of coffees.",Toast.LENGTH_SHORT).show();
            return;}
        numberOfCoffees--;
        displayQuantity(numberOfCoffees);
    }
}