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
import android.widget.Toast;

public class timerview extends AppCompatActivity {
    private TimerSurfaceView timer;
    int clr;
    int time;
    int length=400;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int red= ContextCompat.getColor(this,R.color.red);
        timer=new TimerSurfaceView(this,length,Intenta(),red);

        setContentView(timer);
    }
    public int Intenta(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int num= (int) bundle.getSerializable("num");
        return num;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu
        getMenuInflater().inflate(R.menu.activity_timerview,menu);

        //setup some menu components with their listeners
        return true;
    }
    protected void onPause() {
        super.onPause();
        timer.onPauseClock();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer.onResumeClock();
    }

    public boolean onOptionsItemSelected(MenuItem item){

        int itemID=item.getItemId();
        switch(itemID){
            case R.id.about:
                //do a dialog box to say who you are
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("This is Timer ")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                builder.show();
                break;

                // Used for changing colours of the timer
            case R.id.settings:
                AlertDialog.Builder builder1=new AlertDialog.Builder(this);
                builder1.setTitle("Select a Color: ");



                builder1.setNeutralButton("RED", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        timer.onPauseClock();
                        AlertDialog.Builder builder2=new AlertDialog.Builder(timerview.this);
                        builder2.setTitle("Timer Ends In?");
                        builder2.setMessage("Enter Time in seconds: ");
                        EditText input=new EditText(timerview.this);
                        builder2.setView(input)
                                .setPositiveButton("Run", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        time=Integer.parseInt(String.valueOf(input.getText()));

                                        clr= ContextCompat.getColor(timerview.this,R.color.red);
                                        timer=new TimerSurfaceView(timerview.this,length,time,clr);
                                        setContentView(timer);
                                        timer.onResumeClock();

                                    }
                                });
                        builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                timer.onResumeClock();
                            }
                        });
                        builder2.show();



                    }
                });
                builder1.setNegativeButton("BLUE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        timer.onPauseClock();
                        AlertDialog.Builder builder2=new AlertDialog.Builder(timerview.this);
                        builder2.setTitle("Timer Ends In?");
                        builder2.setMessage("Enter Time in seconds: ");
                        EditText input=new EditText(timerview.this);
                        builder2.setView(input)
                                .setPositiveButton("Run", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        time=Integer.parseInt(String.valueOf(input.getText()));

                                        clr= ContextCompat.getColor(timerview.this,R.color.blue);
                                        timer=new TimerSurfaceView(timerview.this,length,time,clr);
                                        setContentView(timer);
                                        timer.onResumeClock();

                                    }
                                });
                        builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                timer.onResumeClock();
                            }
                        });
                        builder2.show();


                    }
                });
                builder1.setPositiveButton("YELLOW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        timer.onPauseClock();
                        AlertDialog.Builder builder2=new AlertDialog.Builder(timerview.this);
                        builder2.setTitle("Timer Ends In?");
                        builder2.setMessage("Enter Time in seconds: ");
                        EditText input=new EditText(timerview.this);
                        builder2.setView(input)
                                .setPositiveButton("Run", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        time=Integer.parseInt(String.valueOf(input.getText()));

                                        clr= ContextCompat.getColor(timerview.this,R.color.yellow);
                                        timer=new TimerSurfaceView(timerview.this,length,time,clr);
                                        setContentView(timer);
                                        timer.onResumeClock();

                                    }
                                });
                        builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                timer.onResumeClock();
                            }
                        });
                        builder2.show();
                    }
                });

//                Intent intent=new Intent(MainActivity.this,MainActivity.class);
//                Bundle bundle=new Bundle();
//                bundle.putSerializable("clr",clr);
//                intent.putExtras(bundle);
                builder1.show();
//                Intenta();

                break;
            case R.id.clock:
                finish();
                break;
        }
        return false;
    }
}