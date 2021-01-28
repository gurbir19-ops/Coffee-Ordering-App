package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    int i = 0;
    int totalPrice = 0;
    boolean wantWhippedCream = false;
    boolean wantChocolate=false;
    public void incrementOrder(View view) {
        if(i<10)
        display(++i);
        else
            display(i);
    }

    public void decrementOrder(View view) {
        if(i>0)
        display(--i);
        else
            display(i);
    }

    public void submitOrder(View view) {
        CheckBox checkWhippedCream = (CheckBox) findViewById(R.id.checkbox_met);
        CheckBox checkChocolate = (CheckBox) findViewById(R.id.checkbox_choco);
        EditText name=(EditText) findViewById(R.id.name);
        if (checkWhippedCream.isChecked() && !checkChocolate.isChecked()) {
            wantChocolate=false;
            wantWhippedCream=true;
            totalPrice = i * 5 + i*3;
        } else if(checkChocolate.isChecked() && !checkWhippedCream.isChecked())
        {wantChocolate=true;
        wantWhippedCream=false;
            totalPrice = i * 5+i*2;}
        else if(checkChocolate.isChecked() && checkWhippedCream.isChecked())
        {
            wantChocolate=true;
            wantWhippedCream=true;
            totalPrice = i * 5 + i*3+i*2;
        }
        else
        {
            wantWhippedCream=false;
            wantChocolate=false;
            totalPrice = i * 5;
        }
        String price = displayPrice(totalPrice);
        String Name=name.getText().toString();
        String order = "Name : "+Name+ "\nQuantity : " + i + "\nWant any whipped cream on your coffee?" + wantWhippedCream +"\nWant any chocolate toppings on your coffee?"+wantChocolate+ "\nTotal : " + price + "\n"+getString(R.string.thank_you);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Order for : "+Name);
        intent.putExtra(Intent.EXTRA_TEXT,order);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
      //  displayMessage(order);
//        displayPrice((i)*5);
    }

    private void display(int number) {
        TextView q = (TextView) findViewById(R.id.quantity_text_view);
        q.setText("" + number);
    }

    private String displayPrice(int number) {
        return NumberFormat.getCurrencyInstance().format(number);
    }

  //  private void displayMessage(String message) {
    //    TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
      //  priceTextView.setText(message);
    //}

  /*  public void onCheckboxClicked(View view) {
        wantWhippedCream=true;
    }*/
}