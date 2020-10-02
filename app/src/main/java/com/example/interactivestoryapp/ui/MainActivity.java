package com.example.interactivestoryapp.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.interactivestoryapp.R;

public class MainActivity extends AppCompatActivity {

    private EditText nameField;
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = (EditText)findViewById(R.id.editText);
        submitButton = (Button)findViewById(R.id.mainTitleButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameField.getText().toString();
                if (TextUtils.isEmpty(name)){
                    nameField.setError("Name should not be empty");
                    return;
                }
//                Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
                startStory(name);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        nameField.setText("");
    }

    private void startStory(String name) {
        Intent intent = new Intent(this,StoryActivity.class);
//        Resources resources = getResources();
//        String key = resources.getString(R.string.pageNumber);
//        String test = getString(R.string.pageNumber);
//        intent.putExtra(getString(R.string.pageNumber),0);
        intent.putExtra(getString(R.string.name),name);
        startActivity(intent);
    }
}
