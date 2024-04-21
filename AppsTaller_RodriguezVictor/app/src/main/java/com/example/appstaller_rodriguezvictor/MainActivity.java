package com.example.appstaller_rodriguezvictor;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText txtNombre, txtEdad;
    Spinner spnCurso;
    RadioGroup radioGroup;
    Button btnMatricular;
    TextView lblSalida;
    CheckBox ckMostrarProfe;
    List<String> cursos = new ArrayList<>();
    private String cursoSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.inicializarControles();

        for (int i = 1; i<=10; i++){
            cursos.add("Desarrollo de software " + i);
        }

        ArrayAdapter<String> adapterList = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, cursos);
        adapterList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCurso.setAdapter(adapterList);

        // Cuando se presiona el botón matricular, entonces:
        btnMatricular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String turno, profe;
                int edad = 0;
                String nombre = txtNombre.getText().toString();
                boolean isChecked = IsmostrarProfesorChecked();
                // isChecked: says if the user checked the "mostrar profe" checkbox

                try{
                    if (TextUtils.isEmpty(nombre)){
                        Toast.makeText(MainActivity.this, "Faltan campos por rellenar!", Toast.LENGTH_SHORT).show();
                        txtNombre.setError("Este campo es obligatorio!");
                        return;
                    } else if (TextUtils.isEmpty(txtEdad.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Ingrese una edad válida!", Toast.LENGTH_SHORT).show();
                        txtNombre.setError("Este campo es obligatorio!");
                        return;
                    }
                    edad = Integer.parseInt(txtEdad.getText().toString());

                }catch (NumberFormatException er){
                    txtEdad.setError(null);
                    txtNombre.setError(null);
                }

                RadioButton selectedRBTN = findViewById(radioGroup.getCheckedRadioButtonId());
                // SelectedRBTN: contains user's selection (Diurno a.m. / Diurno p.m. / nocturno)

                if (selectedRBTN == null){
                    Toast.makeText(MainActivity.this, "Seleccione un turno!", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    turno = obtenerTurno(selectedRBTN);
                    profe = obtenerProfesor(turno);
                }

                spnCurso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // Obtén el curso seleccionado
                        cursoSeleccionado = parent.getItemAtPosition(position   ).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Se llama cuando no se ha seleccionado nada en el Spinner
                    }
                });

                imprimirSalida(view, isChecked, turno, cursoSeleccionado, nombre, profe, edad);

            }
        });
    }


    public void imprimirSalida(View view, boolean mostrarProfeIsChecked, String turno, String curso, String nombre, String profe, int edad){
        try{

            String mensaje = "Sr(a) " + nombre + ", de " + edad + " años, usted está inscrito en el curso "
                    + curso + ", en el turno: " + turno;
            if (mostrarProfeIsChecked) {
                mensaje += ", con el profesor " + profe;
            }

            mostrarAlertDialog(mensaje);


        }catch (Exception e){
            Toast.makeText(this, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT);
        }
    }

    public boolean IsmostrarProfesorChecked(){
        boolean mostrarProfesor = false;
        if (ckMostrarProfe.isChecked()){
            mostrarProfesor = true;
        }
        return mostrarProfesor;
    }

    public String obtenerProfesor(String turno){
        String profe = "";
        if(turno.equalsIgnoreCase("Diurno A.M.")){
            profe = "Arnulfo Donenrique";
        } else if (turno.equalsIgnoreCase("Diurno P.M.")) {
            profe = "Donenrique Arnulfo";
        } else if (turno.equalsIgnoreCase("Nocturno")) {
            profe = "batman";
        }
        return profe;
    }

    public String obtenerTurno(RadioButton selectedRBTN){
        String msgRBTN = "";
        if (selectedRBTN.getId() == R.id.rbtDiurnoAM){
            msgRBTN = "Diurno A.M.";
        } else if (selectedRBTN.getId() == R.id.rbtDiurnoPM) {
            msgRBTN = "Diurno P.M.";
        } else if (selectedRBTN.getId() == R.id.rbtNocturno) {
            msgRBTN = "Nocturno";
        }
        return msgRBTN;
    }

    private void inicializarControles(){
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        spnCurso = (Spinner) findViewById(R.id.spnCurso);
        radioGroup = (RadioGroup) findViewById(R.id.rgrOpciones); //idk cual es el prefijo para los radioGroup asi q rgr
        btnMatricular = (Button) findViewById(R.id.btnMatricular);
        lblSalida = (TextView) findViewById(R.id.lblSalida);
        ckMostrarProfe = (CheckBox) findViewById(R.id.ckMostrarProfe);
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


}
