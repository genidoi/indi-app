package com.example.indiapp;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {
	private GoogleMap map = null;
	MarkerOptions mo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        createMap();
        
        map.setOnCameraChangeListener(new OnCameraChangeListener() {
			

			@Override
			public void onCameraChange(CameraPosition pos) {
				
				
				Button b = (Button) findViewById(R.id.comment);
				Point p = getMarkerLocation();
				@SuppressWarnings("deprecation")
				AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) b.getLayoutParams();
				
				params.x = p.x;
				params.y = p.y;
				
				b.setLayoutParams(params);
				
			}
        });
    
        	
        
//        map.setOnCameraChangeListener(new OnCameraChangeListener(){
//       
//			@Override
//			public void onCameraChange(CameraPosition arg0) {
//				
//
//				
//				
//			}
//        	
//        });
    }


    private void createMap() {
    	if (map == null) {
    		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    		
    		if (map != null) {
    			setUpMap();
    		}
    		
    	}
	}


	private void setUpMap() {
		
		mo = new MarkerOptions().position(new LatLng(0,0)).title("Marker");
		map.addMarker(mo);
		
		
		map.getProjection().toScreenLocation(mo.getPosition());
		
	}
	private Point getMarkerLocation() {
		return map.getProjection().toScreenLocation(mo.getPosition());
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
