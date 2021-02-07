package com.example.androidbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BlankFragment.FragmentListener {

    Button fragmentAdd,fragmentRmv,servicesBTN,notificationBTN;

    public static final String FRAGMENT_TAG = "fragment_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupBTN();


    }

    void setupBTN(){
        fragmentAdd = findViewById(R.id.fragmentAdd);
        fragmentRmv = findViewById(R.id.fragmentRmv);
        servicesBTN = findViewById(R.id.servicesBTN);
        notificationBTN = findViewById(R.id.notificationBTN);
    }


    public void clickHandler(View view) {

//        Bundle arguments = new Bundle();
//        arguments.putString(BlankFragment.MESSAGE_KEY,"Passed As An Argument");
//        BlankFragment blankFragment = new BlankFragment();
//        blankFragment.setArguments(arguments);
        BlankFragment blankFragment = BlankFragment.newInstance("Passed As Factory Method");

        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .add(R.id.frameContainer,blankFragment, FRAGMENT_TAG)
                .commit();
    }

    public void removeClickHandler(View view){
        Fragment blankFragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
                //.findFragmentById(R.id.frameContainer);
        if (blankFragment != null){
            getSupportFragmentManager().beginTransaction()
                    .remove(blankFragment)
                    .commit();
        }
    }

    @Override
    public void onFragmentFinish(String userInput) {
        Toast.makeText(this, userInput , Toast.LENGTH_SHORT).show();
    }
}