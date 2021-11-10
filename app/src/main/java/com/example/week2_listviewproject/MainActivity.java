package com.example.week2_listviewproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;

// MVVM : Model, View, View Model
// MVC
public class MainActivity extends AppCompatActivity {

    ListView collegeList;
    String[] collegeNames;
     ArrayList<String> selectedColleges;
    Spinner collegeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        collegeList = findViewById(R.id.colleges_list);
        collegeSpinner = findViewById(R.id.spinner);
        selectedColleges = new ArrayList<>(0);
        collegeNames = new String[]{"Seneca College","Niagra College", "Humber College"};
        // array adapter = only for list of strings

        // base adapter = for list of objects
        // RecyclerView Adapter = for advance list of objects
       // ArrayAdapter t = new ArrayAdapter(this,)

        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.list_view_item,R.id.text_in_row,selectedColleges);
        collegeList.setAdapter(adapter);


        collegeSpinner.setPrompt("Select a College ");
        String p = collegeSpinner.getPrompt().toString();
        ArrayAdapter spinner_adapter = new ArrayAdapter(this,R.layout.spinner_view_item,R.id.text_in_spinner,collegeNames);
        collegeSpinner.setAdapter(spinner_adapter);



        collegeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              String selectedCollege = selectedColleges.get(i);
                Toast.makeText(getApplicationContext(),"The selected College is " + selectedCollege  ,Toast.LENGTH_LONG).show();
            }
        });


      //  collegeSpinner.onItem
        collegeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedCollege = collegeNames[i]; //collegeSpinner.getSelectedItem().toString();

                Boolean contains = Arrays.stream(selectedColleges.toArray()).anyMatch(s -> {return s.equals(selectedCollege);});
                Boolean contains2 = Arrays.asList(selectedColleges).contains(selectedCollege);

                // Lamda = clouser
                // in line function
             if (!contains){
               // if (!Arrays.asList(selectedColleges).contains(selectedCollege)){
                    Toast.makeText(getApplicationContext(),"Select a college " + selectedCollege  ,Toast.LENGTH_LONG).show();
                    selectedColleges.add(selectedCollege);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(), selectedCollege + " Is already inserted!!"  ,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}