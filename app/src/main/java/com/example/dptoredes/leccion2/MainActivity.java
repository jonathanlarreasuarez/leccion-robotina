package com.example.dptoredes.leccion2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    ListView listadoUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listadoUsuarios = (ListView) findViewById(R.id.listadoUsuarios);
        obtenerDatos();
    }
    public void obtenerDatos(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://jsonplaceholder.typicode.com/posts";


        client.get(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                if(statusCode==200){//OK
                    cargarUsuarios(obtenerDatosJson(new String(responseBody)));
                    Log.d("Mensaje", "Se cargo Correctamente");

                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }
    public void cargarUsuarios(ArrayList<String> usuarios){
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1, usuarios);
        listadoUsuarios.setAdapter(adaptador);
    }

    public ArrayList<String> obtenerDatosJson(String respuesta){
        ArrayList<String> usuarios = new ArrayList<String>();
        Log.d("Respuesta", respuesta);

        try {
            String txt,txt1,txt2,txt3;

            JSONObject object= new JSONObject(respuesta);
            JSONArray ja = object.getJSONArray("");
            //vehicles.clear();
            for(int i = 0; i < ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                //Vehicle Ve = new Vehicle();

                // Ve.setVehicleID(jo.getInt("nombre"));
                txt = jo.getString("userId");
                txt1 = jo.getString("id");
                txt2 = jo.getString("tittle");
                txt3 = jo.getString("boddy");


                usuarios.add(txt);
                usuarios.add(txt1); usuarios.add(txt2); usuarios.add(txt3);



                //vehicles.add(Ve);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Mensaje", "No lo puedo convertir a json");
        }
        return usuarios;

    }
}
