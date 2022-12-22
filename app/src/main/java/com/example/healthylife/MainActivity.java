package com.example.healthylife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private EditText todo_Text;
private Button btn_add;
private Button btn_delete;
private RecyclerView recyclerView;
   AlertDialog.Builder builder;
   ArrayList<String> dataList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         getSupportActionBar().hide();
        setup();
    }

    public void setup (){
        todo_Text=findViewById(R.id.todoText);
        btn_add=findViewById(R.id.btn_add);
        btn_delete=findViewById(R.id.btn_delete);
        recyclerView=findViewById(R.id.recycler_view);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter mainAdapter=new MainAdapter(dataList);
        recyclerView.setAdapter(mainAdapter);

         btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataList.add(todo_Text.getText().toString());
                MainAdapter mainAdapter=new MainAdapter(dataList);
                recyclerView.setAdapter(mainAdapter);
                if (!todo_Text.equals("")) {
                    todo_Text.setText("");
                }
                else{
                    builder= new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("The text field must not be empty!!")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("InvalidActionAlert");
                    alert.show();
                }

            }
         });
       btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder= new AlertDialog.Builder(view.getContext());
                //Setting message manually and performing action on button click
                builder.setMessage("Are you sure you want to delete all your todos?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                dataList.clear();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("ResetConfirmation");
                alert.show();

            }
        });
    }


    }