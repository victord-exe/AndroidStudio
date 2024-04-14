package com.example.applabs2rodriguez_ibarra_rodriguez;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.applabs2rodriguez_ibarra_rodriguez.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
        Toast.makeText(this, "Método on create", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Método on Start", Toast.LENGTH_SHORT).show();
        System.out.println("Se llamó el metodo onStart en el dispositivo");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Método on Restart", Toast.LENGTH_SHORT).show();
        System.out.println("Se llamó el metodo onRestart en el dispositivo");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Método on Resume", Toast.LENGTH_SHORT).show();
        System.out.println("Se llamó el metodo onResume en el dispositivo");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Metodo onStop", Toast.LENGTH_SHORT).show();
        System.out.println("Se llamó el metodo onStop en el dispositivo");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "Metodo onDestroy", Toast.LENGTH_LONG).show();
        System.out.println("Se llamó el metodo onDestroy en el dispositivo");
    }


}