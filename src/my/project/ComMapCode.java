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
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MouseEvent;
import com.teamdev.jxmaps.swing.MapView;

import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;

public class ComMapCode extends MapView 
{
    MapComplaint mp;
    double lati,longi;
    static double value1,value2;
    
    public void get_lat(double k)
    {   
        value1 = k;
    }
    
    public double get_final_lat()
    {
        return value1;
    }

    public void get_lng(double i)
    {   
        value2 = i;
    }
    
    public double get_final_lng()
    {
        return value2;
    }    
    
    
    
    public ComMapCode(String ccd) 
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
                    map.setCenter(new LatLng(19.7515, 75.7139));
                    map.setZoom(9.0);

                    map.addEventListener("click", new MapMouseEvent()
                    {
                        @Override
                        public void onEvent(MouseEvent mouseEvent)
                        {
                            final Marker marker = new Marker(map);
                            marker.setPosition(mouseEvent.latLng());
                       
                            LatLng posi = marker.getPosition();
                            lati =  posi.getLat();
                            get_lat(lati);
                            longi = posi.getLng();
                            get_lng(longi);
                             marker.addEventListener("click", new MapMouseEvent() {
                                @Override
                                public void onEvent(MouseEvent mouseEvent) {
                                    // Removing marker from the map
                                    marker.remove();
                                }
                            });
                           
                        }
                         
                    });
                    
                    GeocoderRequest request = new GeocoderRequest(map);
                    request.setAddress(ccd);

                    getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
                        @Override
                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                            if (status == GeocoderStatus.OK) {
                                map.setCenter(result[0].getGeometry().getLocation());
                                Marker marker = new Marker(map);
                                marker.setPosition(result[0].getGeometry().getLocation());

                                final InfoWindow window = new InfoWindow(map);
                                window.setContent(ccd);
                                window.open(map, marker);
                            }
                        }
                    });
                }
            }
        });
        
        
    }
    public void load_map(String k)
    {
        final ComMapCode view = new ComMapCode(k);
         
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "MAP"));
        this.setSize(700, 500);
        this.setLayout(new BorderLayout());
        this.add(view);
       
    }
        
    public static void main(String[] args) {

    }
}