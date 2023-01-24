package com.example.clock;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private ClockSurfaceView clock;


    int clr;
    private int length=400;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int red= ContextCompat.getColor(MainActivity.this,R.color.red);
        clock=new ClockSurfaceView(this,length,red);
        setContentView(clock);
    }

    @Override
    protected void onPause() {
        super.onPause();
        clock.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        clock.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu
        getMenuInflater().inflate(R.menu.activity_menu,menu);

        //setup some menu components with their listeners
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int itemID=item.getItemId();
        switch(itemID){
            case R.id.about:
                //do a dialog box to say who you are
                AlertDialog.Builder builder1=new AlertDialog.Builder(this);
                builder1.setMessage("This is Clock")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //do nothing
                            }
                        });

                builder1.show();
                break;
            case R.id.settings:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Select a Color: ");



                builder.setNeutralButton("RED", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clr=ContextCompat.getColor(MainActivity.this,R.color.red);
                        clock=new ClockSurfaceView(MainActivity.this,length,clr);
                        setContentView(clock);


                    }
                });
                builder.setNegativeButton("BLUE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clr=ContextCompat.getColor(MainActivity.this,R.color.blue);
                        clock=new ClockSurfaceView(MainActivity.this,length,clr);
                        setContentView(clock);


                    }
                });
                builder.setPositiveButton("YELLOW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clr=ContextCompat.getColor(MainActivity.this,R.color.yellow);
                        clock=new ClockSurfaceView(MainActivity.this,length,clr);
                        setContentView(clock);
                    }
                });

//                Intent intent=new Intent(MainActivity.this,MainActivity.class);
//                Bundle bundle=new Bundle();
//                bundle.putSerializable("clr",clr);
//                intent.putExtras(bundle);
                builder.show();
//                Intenta();

                break;
            case R.id.timer:

                AlertDialog.Builder builder2=new AlertDialog.Builder(this);
                builder2.setTitle("Timer Ends In?");
                builder2.setMessage("Enter Time in seconds: ");
                EditText input=new EditText(this);
                builder2.setView(input)
                        .setPositiveButton("Run", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int num=Integer.parseInt(String.valueOf(input.getText()));

                                Intent intent=new Intent(MainActivity.this,timerview.class);
                                Bundle bundle=new Bundle();
                                bundle.putSerializable("num",num);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder2.show();

                break;


        }

        return  false;
    }
//    public void Intenta(){
//        Intent intent=getIntent();
//        Bundle bundle=intent.getExtras();
//        int num= (int) bundle.getSerializable("clr");
//        clock=new ClockSurfaceView(this,length,num);
//        setContentView(clock);
//
//    }


}