package cl.continuum.stcworkshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpositorDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expositor_detail);
        int position = getIntent().getExtras().getInt("position");

        ImageView expositorImage = (ImageView) findViewById(R.id.expositor_image);
        TextView expositorName = (TextView) findViewById(R.id.expositor_name);
        TextView expositorDescription = (TextView) findViewById(R.id.expositor_description);

        int id = getBaseContext().getResources().getIdentifier("expositor_"+(position+1), "drawable", getBaseContext().getPackageName());
        expositorImage.setImageResource(id);

        String name = getResources().getStringArray(R.array.expositors_names)[position];
        expositorName.setText(name);

        expositorDescription.setText(getResources().getStringArray(R.array.expositors_desc)[position]);

        getSupportActionBar().show();
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
