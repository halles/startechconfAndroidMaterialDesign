package cl.continuum.stcworkshop;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpositorDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature( Window.FEATURE_NO_TITLE);
        super.onCreate ( savedInstanceState );
        setContentView(R.layout.activity_expositor_detail);
        int position = getIntent().getExtras().getInt("position");

        ImageView expositorImage = (ImageView) findViewById(R.id.expositor_image);
        TextView expositorName = (TextView) findViewById(R.id.expositor_name);
        //aquí añadimos el trasition name a la imagen

        if( Build.VERSION.SDK_INT>=21){
            expositorImage.setTransitionName ( "transitionImage" );
        }

        TextView expositorDescription = (TextView) findViewById(R.id.expositor_description);

        int id = getBaseContext().getResources().getIdentifier("expositor_"+(position+1), "drawable", getBaseContext().getPackageName ( ));
        expositorImage.setImageResource ( id );

        String name = getResources().getStringArray(R.array.expositors_names)[position];
        expositorName.setText(name);

        expositorDescription.setText ( getResources ( ).getStringArray ( R.array.expositors_desc )[ position ] );


        Toolbar toolbar= (Toolbar)findViewById ( R.id.expositor_toolbar );
        setSupportActionBar ( toolbar );
        getSupportActionBar ( ).setTitle ( " " );
        getSupportActionBar().setDisplayHomeAsUpEnabled ( true );


    }
}
