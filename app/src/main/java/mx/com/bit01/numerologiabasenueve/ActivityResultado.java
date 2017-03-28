package mx.com.bit01.numerologiabasenueve;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ActivityResultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        setTitle(getResources().getString(R.string.ResultadosTexto));

        Bundle extras = getIntent().getExtras();

        int[] valoresCasillas = extras.getIntArray("valores");
        int[] valoresPorcentajes = extras.getIntArray("porcentajes");
        int dia = extras.getInt("dia");
        int mes = extras.getInt("mes");
        int anio = extras.getInt("anio");

        TextView[] casillas = {(TextView)findViewById(R.id.casilla1),(TextView)findViewById(R.id.casilla2),(TextView)findViewById(R.id.casilla3),
                (TextView)findViewById(R.id.casilla4),(TextView)findViewById(R.id.casilla5),(TextView)findViewById(R.id.casilla6),
                (TextView)findViewById(R.id.casilla7),(TextView)findViewById(R.id.casilla8),(TextView)findViewById(R.id.casilla9)};

        TextView[] porcentajes = {(TextView)findViewById(R.id.porcentaje1),(TextView)findViewById(R.id.porcentaje2),(TextView)findViewById(R.id.porcentaje3),
                (TextView)findViewById(R.id.porcentaje4),(TextView)findViewById(R.id.porcentaje5),(TextView)findViewById(R.id.porcentaje6),
                (TextView)findViewById(R.id.porcentaje7),(TextView)findViewById(R.id.porcentaje8),(TextView)findViewById(R.id.porcentaje9)};

        TextView TextNumExpresion = (TextView) findViewById(R.id.numExpresion);

        int valorPorcentajeMaximo = 0;
        int numExpresion = 0;

        for(int i =0;i<9;i++){
            numExpresion = numExpresion+valoresCasillas[i]*(i+1);
            casillas[i].setText(valoresCasillas[i]+"");
            porcentajes[i].setText(valoresPorcentajes[i]+"");

            switch(i){

                case 0:
                case 2:
                case 3:
                case 8:
                    valorPorcentajeMaximo = 12;
                    break;
                case 1:
                case 7:
                    valorPorcentajeMaximo = 8;
                    break;
                case 4:
                    valorPorcentajeMaximo = 20;
                    break;
                case 5:
                    valorPorcentajeMaximo = 10;
                    break;
                case 6:
                    valorPorcentajeMaximo = 7;
                    break;

            }

            if(valoresPorcentajes[i]>valorPorcentajeMaximo){
                porcentajes[i].setTextColor(Color.parseColor("#f44153"));
            }

        }

        numExpresion = sumarTodosLosDigitos(numExpresion,true);

        if(numExpresion<10){

            TextNumExpresion.setText(numExpresion+"");

        }else{

            TextNumExpresion.setText(numExpresion+"/"+sumarTodosLosDigitos(numExpresion,false));

        }

        calcularAVDan(dia,mes,anio);
        calcularAVHorizontal(dia,mes,anio);
        calcularAVVertical(dia,mes,anio);
        calcularFuerzaDan(dia,mes);
        calcularFuerzaHorizontal(dia,mes);
        calcularFuerzaVertical(dia,mes);
        calcularPP(dia,mes,anio);
        calcularAnioPersonal(dia,mes);

    }

    public void calcularAnioPersonal(int d, int m){

        TextView textAV = (TextView) findViewById(R.id.idAnioVida);

        Calendar c = new GregorianCalendar();

        int diaActual=c.get(Calendar.DAY_OF_MONTH);
        int mesActual=c.get(Calendar.MONTH)+1;
        int anio=0;

        if(mesActual<m){

            anio=2016;

        }else{

            if(mesActual>m){

                anio=2017;

            }else{

                if(diaActual<d){

                    anio=2016;

                }else{

                    if(diaActual> d){

                        anio=2017;

                    }else{

                        anio=2017;

                    }

                }

            }

        }

        String diaS = preparaNum(d);
        String mesS = preparaNum(m);
        String anioS = preparaNum(anio);

        String res="";

        int sumaDia = Character.getNumericValue(diaS.charAt(0))+Character.getNumericValue(diaS.charAt(1));
        int sumaMes = Character.getNumericValue(mesS.charAt(0))+Character.getNumericValue(mesS.charAt(1));
        int sumaAnio = Character.getNumericValue(anioS.charAt(0))+Character.getNumericValue(anioS.charAt(1))+Character.getNumericValue(anioS.charAt(2))+Character.getNumericValue(anioS.charAt(3));

        int primeraSuma = sumaDia+sumaMes+sumarTodosLosDigitos(sumaAnio, false);

        if(esMaestro(primeraSuma+"")){

            res=primeraSuma+"/"+sumarTodosLosDigitos(primeraSuma,true);

        }else{

            res=""+sumarTodosLosDigitos(primeraSuma,true);

        }

        textAV.setText(res);

    }

    public void calcularFuerzaHorizontal(int dia, int mes){

        TextView textAV = (TextView) findViewById(R.id.idFuerzaH);

        String diaS = preparaNum(dia);
        String mesS = preparaNum(mes);

        String res="";

        int sumaDia = Character.getNumericValue(diaS.charAt(0))+Character.getNumericValue(diaS.charAt(1));
        int sumaMes = Character.getNumericValue(mesS.charAt(0))+Character.getNumericValue(mesS.charAt(1));

        int primeraSuma = sumaDia+sumaMes;

        if(esMaestro(primeraSuma+"")){

            res=primeraSuma+"/"+sumarTodosLosDigitos(primeraSuma,true);

        }else{

            res=""+sumarTodosLosDigitos(primeraSuma,true);

        }

        textAV.setText(res);

    }

    public void calcularFuerzaDan(int dia, int mes){

        TextView textAV = (TextView) findViewById(R.id.idFuerzaD);

        String diaS = preparaNum(dia);
        String mesS = preparaNum(mes);

        String res="";

        int sumaDia = Character.getNumericValue(diaS.charAt(0))+Character.getNumericValue(diaS.charAt(1));
        int sumaMes = Character.getNumericValue(mesS.charAt(0))+Character.getNumericValue(mesS.charAt(1));

        int primeraSuma = sumaDia+sumaMes;

        if(esMaestro(primeraSuma+"")){

            res=primeraSuma+"/"+sumarTodosLosDigitos(primeraSuma,true);

        }else{

            res=""+sumarTodosLosDigitos(primeraSuma,true)+"";

        }

        textAV.setText(res);

    }

    public void calcularFuerzaVertical(int dia, int mes){

        TextView textAV = (TextView) findViewById(R.id.idFuerzaV);

        int sumaAnio = dia+mes;
        String res="";

        if(esMaestro(sumaAnio+"")){

            res=sumaAnio+"/"+sumarTodosLosDigitos(sumaAnio,true);

        }else{

            res=""+sumarTodosLosDigitos(sumaAnio,true)+"";

        }

        textAV.setText(res);

    }

    public void calcularAVHorizontal(int dia, int mes, int anio) {

        TextView textAV = (TextView) findViewById(R.id.idAVHorizontal);

        String diaS = preparaNum(dia);
        String mesS = preparaNum(mes);
        String anioS = preparaNum(anio);

        String res="";

        int sumaDia = Character.getNumericValue(diaS.charAt(0))+Character.getNumericValue(diaS.charAt(1));
        int sumaMes = Character.getNumericValue(mesS.charAt(0))+Character.getNumericValue(mesS.charAt(1));
        int sumaAnio = Character.getNumericValue(anioS.charAt(0))+Character.getNumericValue(anioS.charAt(1))+Character.getNumericValue(anioS.charAt(2))+Character.getNumericValue(anioS.charAt(3));

        int primeraSuma = sumaDia+sumaMes+sumarTodosLosDigitos(sumaAnio, false);

        if(esMaestro(primeraSuma+"")){

            res=primeraSuma+"/"+sumarTodosLosDigitos(primeraSuma,true);

        }else{

            res=""+sumarTodosLosDigitos(primeraSuma,true);

        }

        textAV.setText(res);

    }

    public void calcularAVDan(int dia, int mes, int anio){

        TextView textAV = (TextView) findViewById(R.id.idAVDan);

        String diaS = preparaNum(dia);
        String mesS = preparaNum(mes);
        String anioS = preparaNum(anio);

        String res="";

        int sumaDia = Character.getNumericValue(diaS.charAt(0))+Character.getNumericValue(diaS.charAt(1));
        int sumaMes = Character.getNumericValue(mesS.charAt(0))+Character.getNumericValue(mesS.charAt(1));
        int sumaAnio = Character.getNumericValue(anioS.charAt(0))+Character.getNumericValue(anioS.charAt(1))+Character.getNumericValue(anioS.charAt(2))+Character.getNumericValue(anioS.charAt(3));

        int primeraSuma = sumaDia+sumaMes+sumaAnio;

        if(esMaestro(primeraSuma+"")){

            res=primeraSuma+"/"+sumarTodosLosDigitos(primeraSuma,true);

        }else{

            res=""+sumarTodosLosDigitos(primeraSuma,true)+"";

        }

        textAV.setText(res);

    }

    public void calcularAVVertical(int dia, int mes, int anio){

        TextView textAV = (TextView) findViewById(R.id.idAVVertical);
        TextView textAVAnio = (TextView) findViewById(R.id.idAnioC);

        int suma = dia+mes+anio;
        String res="";
        String anioS=suma+"";
        int sumaAnio = Character.getNumericValue(anioS.charAt(0))+Character.getNumericValue(anioS.charAt(1))+Character.getNumericValue(anioS.charAt(2))+Character.getNumericValue(anioS.charAt(3));

        if(esMaestro(sumaAnio+"")){

            res=sumaAnio+"/"+sumarTodosLosDigitos(sumaAnio,true);

        }else{

            res=""+sumarTodosLosDigitos(sumaAnio,true)+"";

        }

        textAV.setText(res);
        textAVAnio.setText(suma+"");

    }

    public void calcularPP(int dia, int mes, int anio){

        TextView textAV = (TextView) findViewById(R.id.idPP);

        String diaS = preparaNum(dia);
        String mesS = preparaNum(mes);
        String anioS = preparaNum(anio);

        String res="";

        int sumaDia = Character.getNumericValue(diaS.charAt(0))+Character.getNumericValue(diaS.charAt(1));
        int sumaMes = Character.getNumericValue(mesS.charAt(0))+Character.getNumericValue(mesS.charAt(1));
        int sumaAnio = Character.getNumericValue(anioS.charAt(0))+Character.getNumericValue(anioS.charAt(1))+Character.getNumericValue(anioS.charAt(2))+Character.getNumericValue(anioS.charAt(3));

        int primeraSuma = sumaDia+sumaMes+sumarTodosLosDigitos(sumaAnio, false);

        if(esMaestro(primeraSuma+"")){

            res=primeraSuma+"/"+sumarTodosLosDigitos(primeraSuma,true);

        }else{

            res=""+sumarTodosLosDigitos(primeraSuma,true);

        }

        textAV.setText(res);

    }

    public static String preparaNum(int num){

        String str="";

        if(num<10){
            str="0"+num;
        }else{
            str=num+"";
        }

        return str;

    }

    public boolean esMaestro(String numero){

        if(numero.equals("11") || numero.equals("13") || numero.equals("14") || numero.equals("16") || numero.equals("19") ||
                numero.equals("22") || numero.equals("26") || numero.equals("33") || numero.equals("40") || numero.equals("44")){
            return true;
        }else {

            return false;

        }

    }

    public int sumarTodosLosDigitos(int numero, boolean pararSiMaestro){

        String sumaTmp=numero+"";

        int sumaTmpInt = 0;

        int[] digs = new int[sumaTmp.length()];

        boolean mayorADiez=true, esMaestro=false;

        if(pararSiMaestro){

            while(mayorADiez && !esMaestro){

                sumaTmpInt = 0;

                for(int i=0;i<sumaTmp.length();i++){

                    digs[i]=Character.getNumericValue(sumaTmp.charAt(i));
                    sumaTmpInt=sumaTmpInt+digs[i];

                }

                sumaTmp = (sumaTmpInt)+"";

                esMaestro = esMaestro(sumaTmp);

                if(sumaTmpInt<10){

                    mayorADiez = false;

                }

            }

            return sumaTmpInt;

        }else{

            while(mayorADiez){

                sumaTmpInt = 0;

                for(int i=0;i<sumaTmp.length();i++){

                    digs[i]=Character.getNumericValue(sumaTmp.charAt(i));
                    sumaTmpInt=sumaTmpInt+digs[i];

                }

                sumaTmp = (sumaTmpInt)+"";

                if(sumaTmpInt<10){

                    mayorADiez = false;

                }

            }

            return sumaTmpInt;

        }

    }

}
