package cn.xsk.polygonview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RegularPolygonView rpv;
    private EditText inputEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEt = (EditText) findViewById(R.id.input_et);
        rpv = (RegularPolygonView) findViewById(R.id.rpv);
    }

    public void onClick(View view) {
        try {
            Integer n = Integer.valueOf(inputEt.getText().toString());
            if (n < 3){
                Toast.makeText(this , "至少三条边" , Toast.LENGTH_SHORT).show();
                return;
            }
            rpv.setN(n);
        }catch (Exception ignored){}

    }
}
