package com.example.partial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddBookActivity extends AppCompatActivity {
    EditText etTitle,etAuthor,etYear;
    FloatingActionButton fab_add;
    Intent intent;

    public static final String BOOK_KEY = "sendBook";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        initComponents();
        intent = getIntent();

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){
                    Book book = buildBookFromComponents();
                    intent.putExtra(BOOK_KEY,book);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }

    private Book buildBookFromComponents(){
        String title = etTitle.getText().toString();
        String author = etAuthor.getText().toString();
        Integer year = Integer.parseInt(etYear.getText().toString());
        return new Book(title,author,year);
    }

    public boolean isValid(){
        if(etTitle.getText() == null || etTitle.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Book title is not valid",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etAuthor.getText() == null || etAuthor.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Author name is not valid",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etYear.getText() == null || etYear.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Year of publication is not valid",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void initComponents(){
        etTitle = findViewById(R.id.et_title);
        etAuthor = findViewById(R.id.et_author);
        etYear = findViewById(R.id.et_year);
        fab_add = findViewById(R.id.fab_add);
    }
}