package cl.continuum.stcworkshop;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    ExpositorsListAdapter mAdapter;

    AdapterView.OnItemClickListener expositorDetail = new AdapterView.OnItemClickListener ( )
    {

        @Override
        public void onItemClick ( AdapterView< ? > parent, View view, int position, long id )
        {
            Intent intent = new Intent ( MainActivity.this, ExpositorDetailActivity.class );
            Bundle mBundle = new Bundle ( );
            mBundle.putInt ( "position", position );
            intent.putExtras ( mBundle );

            //verificamos si la versión del sdk es mayor o igual a 21
            if ( Build.VERSION.SDK_INT >= 21 )
            {
                View sharedImage = view.findViewById ( R.id.expositor_image );
                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation
                        ( MainActivity.this, sharedImage,"transitionImage" );
                startActivity ( intent, transitionActivityOptions.toBundle ( ) );
            } else
            {
                startActivity ( intent );
            }
        }
    };

    @Override
    protected void onCreate ( Bundle savedInstanceState )
    {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        mAdapter = new ExpositorsListAdapter ( this );
        ListView list = ( ListView ) findViewById ( R.id.expositors );
        list.setAdapter ( mAdapter );
        getSupportActionBar ( ).show ( );
        getSupportActionBar ( ).setTitle ( getResources ( ).getString ( R.string.expositors_act ) );

        list.setOnItemClickListener ( expositorDetail );
    }

    private class ExpositorsListAdapter extends BaseAdapter
    {

        private String[] expositorsNames = getResources ( ).getStringArray ( R.array.expositors_names );
        private LayoutInflater inflater = null;

        public ExpositorsListAdapter ( Activity activity )
        {
            inflater = ( LayoutInflater ) activity.getSystemService ( ( Context.LAYOUT_INFLATER_SERVICE ) );
        }

        @Override
        public int getCount ( )
        {
            return expositorsNames.length;
        }

        @Override
        public Object getItem ( int position )
        {
            return expositorsNames[ position ];
        }

        @Override
        public long getItemId ( int position )
        {
            return position;
        }

        @Override
        public View getView ( int position, View convertView, ViewGroup parent )
        {
            View view = convertView;
            if ( convertView == null )
                view = inflater.inflate ( R.layout.expositor_cell, null );
            TextView name = ( TextView ) view.findViewById ( R.id.expositor_name );
            ImageView image = ( ImageView ) view.findViewById ( R.id.expositor_image );
            name.setText ( expositorsNames[ position ] );
            int id = getBaseContext ( ).getResources ( ).getIdentifier ( "expositor_" + ( position + 1 ), "drawable", getBaseContext ( ).getPackageName ( ) );
            image.setImageResource ( id );
            return view;
        }
    }
}
