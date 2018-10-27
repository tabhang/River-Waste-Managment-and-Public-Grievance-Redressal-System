/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.project;

/**
 *
 * @author tejas
 */
import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapMouseEvent;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MouseEvent;
import com.teamdev.jxmaps.swing.MapView;

import java.awt.*;

public class code_comment extends MapView 
{ 
    Comments_admin cmp;
    public code_comment() 
    {
        setOnMapReadyHandler(new MapReadyHandler() 
        {
            @Override
            public void onMapReady(MapStatus status)
            {
                if (status == MapStatus.MAP_STATUS_OK) 
                {
                    final Map map = getMap();
                    MapOptions options = new MapOptions();
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    controlOptions.setPosition(ControlPosition.TOP_RIGHT);
                    options.setMapTypeControlOptions(controlOptions);
                    map.setOptions(options);
                    map.setCenter(new LatLng(cmp.lat,cmp.longi));
                    map.setZoom(12.0);
                    final Marker marker = new Marker(map);
                    marker.setPosition(new LatLng(cmp.lat,cmp.longi));
                       
                    
                }
            }
        });
        
        
    }
    public void load_map()
    {
        final code_comment view = new code_comment();
         
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "MAP"));
        this.setSize(390, 390);
        this.setLayout(new BorderLayout());
        this.add(view);
       
    }
        
    public static void main(String[] args) {

    }
}