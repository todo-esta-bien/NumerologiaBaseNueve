package mx.com.bit01.numerologiabasenueve;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    int dia_x, mes_x, anio_x;
    static final int DIALOG_ID=0;
    Button btnFecha;
    TextView lblFechaG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getResources().getString(R.string.PedirDatos_PrimerPaso));
        showDialogOnButtonClick();

        Button btnCalcu = (Button) findViewById(R.id.btnCalcular);
        btnCalcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checarCambio()){

                    EditText nombre = (EditText) findViewById(R.id.ETNombre);
                    EditText apellidos = (EditText) findViewById(R.id.ETApellido);

                    int[] valoresCasillas = valoresCasillas(preparaString(nombre.getText().toString()+apellidos.getText().toString()));
                    int[] porcentajes = new int[9];

                    for(int i=0;i<valoresCasillas.length;i++){
                        porcentajes[i]=(valoresCasillas[i]*100)/(preparaString(nombre.getText().toString()+apellidos.getText().toString()).length());
                    }

                    Intent intent = new Intent (v.getContext(), ActivityResultado.class);
                    intent.putExtra("valores", valoresCasillas);
                    intent.putExtra("porcentajes", porcentajes);
                    intent.putExtra("dia",dia_x);
                    intent.putExtra("mes",mes_x);
                    intent.putExtra("anio",anio_x);
                    startActivity(intent);

                }else{

                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.PedirDatos_Error_Calcular), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public boolean checarCambio(){

        TextView lblFecha = (TextView) findViewById(R.id.lblFechaG);
        EditText nombre = (EditText) findViewById(R.id.ETNombre);
        EditText apellido = (EditText) findViewById(R.id.ETApellido);

        if(lblFecha.getText().toString().equals(R.string.PedirDatos_fechaNo)  ||
                nombre.getText().toString().equals("") ||
                apellido.getText().toString().equals("")){

            return false;

        }else{

            return true;

        }

    }

    public int[] valoresCasillas(String str){

        int[] res = new int[9];

        for(int i=0;i<str.length();i++){

            switch(str.charAt(i)){

                case 'a':
                case 'j':
                case 's':
                    res[0]++;
                    break;

                case 'b':
                case 'k':
                case 't':
                    res[1]++;
                    break;

                case 'c':
                case 'l':
                case 'u':
                    res[2]++;
                    break;

                case 'd':
                case 'm':
                case 'v':
                    res[3]++;
                    break;

                case 'e':
                case 'n':
                case 'w':
                    res[4]++;
                    break;

                case 'f':
                case 'o':
                case 'x':
                    res[5]++;
                    break;

                case 'g':
                case 'p':
                case 'y':
                    res[6]++;
                    break;

                case 'h':
                case 'q':
                case 'z':
                    res[7]++;
                    break;

                case 'r':
                case 'i':
                    res[8]++;
                    break;

            }

        }

        return res;

    }

    public String preparaString(String str){

        return quitarAcentos(str).toLowerCase().trim().replaceAll("\\s","");

    }

    public String quitarAcentos(String str){

        String proc = java.text.Normalizer.normalize(str,java.text.Normalizer.Form.NFD);
        StringBuilder sb = new StringBuilder();
        for (char c : proc.toCharArray()) {
            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.BASIC_LATIN) {
                sb.append(c);
            }
        }

        return sb.toString();

    }

    public void showDialogOnButtonClick(){

        btnFecha = (Button) findViewById(R.id.btnFecha);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id){

        final Calendar c=Calendar.getInstance();

        dia_x = c.get(Calendar.DAY_OF_MONTH);
        mes_x = c.get(Calendar.MONTH);
        anio_x = c.get(Calendar.YEAR);

        if(id == DIALOG_ID)
            return new DatePickerDialog(this, dPickerListener, anio_x, mes_x, dia_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            lblFechaG = (TextView) findViewById(R.id.lblFechaG);

            anio_x=year;
            mes_x=month+1;
            dia_x=dayOfMonth;

            lblFechaG.setText(dia_x+"/"+mes_x+"/"+anio_x);

        }
    };

}
