package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText num1,num2;
    private TextView resultado;
    private Spinner combo;
    private  String opciones[];
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = findViewById(R.id.txtNumeroUno);
        num2 = findViewById(R.id.txtNumeroDos);
        resultado = findViewById(R.id.lblResultado);
        combo = findViewById(R.id.cmbOperaciones);

        opciones = getResources().getStringArray(R.array.operaciones);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        combo.setAdapter(adapter);
    }

    public void calcular(View v){
        double n1, n2, suma;
        int op;
        if(validar()) {
            n1 = Double.parseDouble(num1.getText().toString());
            n2 = Double.parseDouble(num2.getText().toString());
            op = combo.getSelectedItemPosition();
            switch (op){
                case 0:
                    suma = n1 + n2;
                    break;
                case 1:
                    suma = n1 - n2;
                    break;
                case 2:
                    suma = n1 * n2;
                    break;
                case 3:
                    suma = n1 / n2;
                    break;
                default:
                    throw new IllegalStateException("Operacion no permitida: " + op);
            }
            resultado.setText(String.format("%.2f",suma));
            //resultado.setText(String.valueOf(suma));
        }
    }

    public boolean validar(){
        int op = combo.getSelectedItemPosition();

        if(num1.getText().toString().isEmpty()){
            num1.setError(getString(R.string.mensaje_error_numero_uno));
            num1.requestFocus();
            return false;
        }
        if(num2.getText().toString().isEmpty()){
            num2.setError(getString(R.string.mensaje_error_numero_dos));
            num2.requestFocus();
            return false;
        }
        double n2 = Double.parseDouble(num2.getText().toString());
        if(n2 == 0 && op == 3){
            num2.setError(getString(R.string.mensaje_error_divison_cero));
            num2.requestFocus();
        }
     return true;
    }

    public void limpiar(View v){
        num1.setText("");
        num2.setText("");
        resultado.setText("");
        combo.setSelection(0);
        num1.requestFocus();
    }
}