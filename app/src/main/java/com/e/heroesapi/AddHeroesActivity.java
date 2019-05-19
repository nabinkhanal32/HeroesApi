package com.e.heroesapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

import API.HeroesApi;
import BaseURL.url;
import models.Heroes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddHeroesActivity extends AppCompatActivity {
    private EditText etName,etDesc;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_heroes);

        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });
    }
    private void Save()
    {
        String name = etName.getText().toString();
        String desc = etDesc.getText().toString();

        Heroes heroes= new Heroes(name,desc);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HeroesApi heroesApi = retrofit.create(HeroesApi.class);
        Call<Void> heroesCall = heroesApi.addHero(heroes);
        heroesCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful())
                {
                    Toast.makeText(AddHeroesActivity.this, "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(AddHeroesActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddHeroesActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
