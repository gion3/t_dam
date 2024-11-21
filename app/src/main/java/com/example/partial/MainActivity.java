package com.example.partial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Book> books = new ArrayList<>();
    private ActivityResultLauncher<Intent> addLauncher;
    private ListView lv_books;
    private Button btn_add;
    private Button btn_details;

    Fragment1 fragment1 = new Fragment1();

    public static final String KEY_ACTIVITY_TO_FRAGMENT = "sendDataBetweenActivityFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        addLauncher = registerAddLauncher();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(getApplicationContext(), AddBookActivity.class);
                addLauncher.launch(intentAdd);
            }
        });
        btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(fragment1);
                sendDataBetweenActivityFragments(fragment1,"Voicu Daniel Ionut");
            }
        });
    }
    private ActivityResultLauncher<Intent> registerAddLauncher(){
        ActivityResultCallback<ActivityResult> callback = getCallback();
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
    }

    private ActivityResultCallback<ActivityResult> getCallback(){
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if(o.getResultCode() == RESULT_OK && o.getData() != null){
                    Book book = (Book) o.getData().getSerializableExtra(AddBookActivity.BOOK_KEY);
                    books.add(book);
                    ArrayAdapter adapter = (ArrayAdapter) lv_books.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        };
    }

    private void openFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void sendDataBetweenActivityFragments(Fragment fragment, String data){
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ACTIVITY_TO_FRAGMENT, data);
        fragment.setArguments(bundle);
    }

    private void initComponents(){
        lv_books = findViewById(R.id.listViewBooks);
        btn_add = findViewById(R.id.button_add);
        btn_details = findViewById(R.id.button_details);
        ArrayAdapter<Book> bookArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, books);
        lv_books.setAdapter(bookArrayAdapter);
    }
}