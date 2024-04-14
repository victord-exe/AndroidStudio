package com.example.buttonappclass110424;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtNum1, txtNum2;
    RadioGroup radioGroup;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.inicializarControles();

        radioGroup = findViewById(R.id.radioGroup);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                if (selectedRadioButton != null) {
                    if (selectedRadioButton.getId() == R.id.radioSuma) {
                        sumarNumeros(view);
                        mostrarImagen(view);
                    } else if (selectedRadioButton.getId() == R.id.radioResta) {
                        restarNumeros(view);
                        mostrarImagen(view);
                    } else if (selectedRadioButton.getId() == R.id.radioMult) {
                        multiplicarNumeros(view);
                        mostrarImagen(view);
                    } else if (selectedRadioButton.getId() == R.id.radioDivision) {
                        dividirNumeros(view);
                        mostrarImagen(view);
                    } else if (selectedRadioButton.getId() == R.id.radioModulo) {
                        obtenerModulo(view);
                        mostrarImagen(view);
                    }
                }

            }
        });
    }

    public void mostrarImagen(View view){
        ImageView imageView = findViewById(R.id.chayanne);
        imageView.setVisibility(View.VISIBLE);
    }
    public void mostrarAlertDialog(String resultado){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(resultado)
                .setTitle("Resultado")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void inicializarControles() {
        txtNum1 = (EditText) findViewById(R.id.txtNum1);
        txtNum2 = (EditText) findViewById(R.id.txtNum2);

    }


    // pregunta de examen: debe ser un método público, void y debe tener un parametro de tipo View.
    public void sumarNumeros(View view){
       try {
           int n1 = Integer.parseInt(txtNum1.getText().toString());
           int n2 = Integer.parseInt(txtNum2.getText().toString());

           int res = n1 + n2;

           mostrarAlertDialog("Su resultado es: " + res);
       }catch (Exception e){
           Toast.makeText(this,
                   "Error en la suma de numeros." + e.getMessage(),
                   Toast.LENGTH_SHORT).show();
       }
    }

    public void restarNumeros(View view){
        try {
            int n1 = Integer.parseInt(txtNum1.getText().toString());
            int n2 = Integer.parseInt(txtNum2.getText().toString());

            int res = n1 - n2;

            mostrarAlertDialog("Su resultado es: " + res);
        }catch (Exception e){
            Toast.makeText(this,
                    "Error en la resta de numeros." + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void multiplicarNumeros(View view){
        try {
            int n1 = Integer.parseInt(txtNum1.getText().toString());
            int n2 = Integer.parseInt(txtNum2.getText().toString());

            int res = n1 * n2;

            mostrarAlertDialog("Su resultado es: " + res);
        }catch (Exception e){
            Toast.makeText(this,
                    "Error en la multiplicación de numeros." + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void dividirNumeros(View view){
        try {
            int n1 = Integer.parseInt(txtNum1.getText().toString());
            int n2 = Integer.parseInt(txtNum2.getText().toString());

            int res = n1 / n2;

            mostrarAlertDialog("Su resultado es: " + res);
        }catch (Exception e){
            Toast.makeText(this,
                    "Error en la división de numeros." + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void obtenerModulo(View view){
        try {
            int n1 = Integer.parseInt(txtNum1.getText().toString());
            int n2 = Integer.parseInt(txtNum2.getText().toString());

            int res = n1 % n2;

            mostrarAlertDialog("Su resultado es: " + res);
        }catch (Exception e){
            Toast.makeText(this,
                    "Error obteniendo el módulo de los numeros." + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}