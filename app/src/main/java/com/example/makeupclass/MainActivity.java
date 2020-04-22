package com.example.makeupclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btn, insertbtn,getBtn;
    private DatabaseClass ojectdatabaseclass;
    EditText name, location;
    TextView showvalues;
    ArrayList<ModelClass> modelClassArrayList;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        try {
            rv =findViewById(R.id.RV);
            btn = findViewById(R.id.createbtn);
            insertbtn = findViewById(R.id.insertvaluebtn);
            name = findViewById(R.id.nameET);
            location =findViewById(R.id.locET);
            getBtn = findViewById(R.id.getvaluesbtn);
            showvalues = findViewById(R.id.valuesTV);
            ojectdatabaseclass = new DatabaseClass(this);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createdb();
                }
            });
            insertbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insertvaluestodatabase();
                }
            });
            getBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getdata();
                }
            });

            modelClassArrayList = new ArrayList<ModelClass>();

        } catch (Exception e) {
            Toast.makeText(this, "Init" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void createdb() {
        try {
            ojectdatabaseclass.getReadableDatabase();

        } catch (Exception e) {
            Toast.makeText(this, "create db " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private  void insertvaluestodatabase()
    {
        try {
            if(!name.getText().toString().isEmpty()&& !location.getText().toString().isEmpty())
            {
                SQLiteDatabase db = ojectdatabaseclass.getWritableDatabase();
                if(db!=null)
                {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name",name.getText().toString());
                    contentValues.put("location",location.getText().toString());
                    long check = db.insert("mytable",null,contentValues);
                    if(check!=-1)
                    {
                        Toast.makeText(this, "Values Added SusscessFully", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }else
            {
                Toast.makeText(this, "Please Enter Name and Location", Toast.LENGTH_SHORT).show();
                name.requestFocus();
            }
        }catch (Exception e)
        {

        }
    }

    private void getdata()
    {
        try {
            SQLiteDatabase db = ojectdatabaseclass.getReadableDatabase();
            if(db!=null)
            {
                Cursor objectcurso = db.rawQuery("select * from mytable",null);
                StringBuffer stringBuffer = new StringBuffer();
                if(objectcurso.getCount()!=0)
                {

                    while (objectcurso.moveToNext())
                    {
//                        stringBuffer.append("Name : "+objectcurso.getString(0)+"\n");
//                        stringBuffer.append("Location :"+objectcurso.getString(1)+"\n");
                        ModelClass objectmodelClass = new ModelClass();
                        objectmodelClass.setName(objectcurso.getString(0));
                        objectmodelClass.setLocation(objectcurso.getString(1));
                        modelClassArrayList.add(objectmodelClass);

                    }
                    AdapterClass objadapter = new AdapterClass(modelClassArrayList);
                    rv.setLayoutManager(new LinearLayoutManager(this));
                    rv.setAdapter(objadapter);
                }else
                {
                    Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
                }
            }else
            {
                Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "Error in geting values"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    


}
