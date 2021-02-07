package com.example.androidbasics;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BlankFragment extends Fragment {

    private static final String TAG = "BlankFragment";
    public static final String MESSAGE_KEY = "message_key";
    TextView passedTXT;
    EditText inputValueET;
    Button submitBTN;
    private FragmentListener mListener;

    public BlankFragment() {
    }

    public static BlankFragment newInstance(String message) {

        Bundle args = new Bundle();
        args.putString(BlankFragment.MESSAGE_KEY,message);
        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (BuildConfig.DEBUG && !(context instanceof FragmentListener)) {
            throw new AssertionError("Assertion failed");
        }
        mListener = (FragmentListener) context;
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        Bundle arguments = getArguments();
        if (arguments != null){
            String message = arguments.getString(MESSAGE_KEY,"");
            passedTXT = view.findViewById(R.id.passedTXT);
            passedTXT.setText(message);
        }

        inputValueET = view.findViewById(R.id.inputValueET);
        submitBTN = view.findViewById(R.id.submitBTN);

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener == null){
                    throw new AssertionError();
                }
                String userInput = inputValueET.getText().toString();
                mListener.onFragmentFinish(userInput);
            }
        });
        return view;
    }

    public interface FragmentListener {
        void onFragmentFinish(String userInput);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }
}