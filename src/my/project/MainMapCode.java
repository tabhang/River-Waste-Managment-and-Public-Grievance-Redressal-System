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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMapCode extends MapView 
{
    Map_Main mp;
    double lati,longi;
    static double value1,value2;
    
    Connection myConn=null;
        Statement myStmt=null;
        ResultSet rs;
        String user="root";
        String pass="1234";
        String comment;
        static int act = 0;
        static int inact = 0;
        
   
    public MainMapCode() 
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
                       
                           marker.addEventListener("click", new MapMouseEvent() {
                                @Override
                                public void onEvent(MouseEvent mouseEvent) {
                                    // Removing marker from the map
                                    marker.remove();
                                }
                            });
                           
                        }
                         
                    });
                    try {
                        
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        myConn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/river_mini?autoReconnect=true&useSSL=false",user,pass);
                        myStmt = myConn.createStatement();
                        
                        if (Map_Main.lo_flag==1)
                        {
                            if(Map_Main.ty_flag == 1)
                            {
                            rs = myStmt.executeQuery("select latitude,longitude from site");
                        
                            while(rs.next())
                            {
                                final Marker marker1 = new Marker(map);
                                
                                marker1.setPosition(new LatLng(Double.parseDouble(rs.getString("latitude")),Double.parseDouble(rs.getString("longitude"))));
                            }
                            }
                            else if(Map_Main.ty_flag==2)
                            {
                                if(Map_Main.sp_flag1==1){
                                    rs = myStmt.executeQuery("select latitude,longitude from site where site_no in (select site_no_fk from complaint where type like 'Waste Dump%')");
                        
                                    while(rs.next())
                                    {
                                        final Marker marker1 = new Marker(map);
                                        marker1.setPosition(new LatLng(Double.parseDouble(rs.getString("latitude")),Double.parseDouble(rs.getString("longitude"))));
                                    }
                                }
                                
                                
                                
                                if(Map_Main.sp_flag2==1){
                                    rs = myStmt.executeQuery("select latitude,longitude from site where site_no in (select site_no_fk from complaint where type like 'Construction%')");
                        
                                    while(rs.next())
                                    {
                                        final Marker marker1 = new Marker(map);
                                        marker1.setPosition(new LatLng(Double.parseDouble(rs.getString("latitude")),Double.parseDouble(rs.getString("longitude"))));
                                    }
                                }
                                
                                if(Map_Main.sp_flag3==1){
                                    rs = myStmt.executeQuery("select latitude,longitude from site where site_no in (select site_no_fk from complaint where type like 'Untreated%')");
                        
                                    while(rs.next())
                                    {
                                        final Marker marker1 = new Marker(map);
                                        
                                        marker1.setPosition(new LatLng(Double.parseDouble(rs.getString("latitude")),Double.parseDouble(rs.getString("longitude"))));
                                    }
                                }
                                
                                if(Map_Main.sp_flag4==1){
                                    rs = myStmt.executeQuery("select latitude,longitude from site where site_no in (select site_no_fk from complaint where type like 'Industrial%')");
                        
                                    while(rs.next())
                                    {
                                        final Marker marker1 = new Marker(map);
                                        marker1.setPosition(new LatLng(Double.parseDouble(rs.getString("latitude")),Double.parseDouble(rs.getString("longitude"))));
                                    }
                                }
                                
                                if(Map_Main.sp_flag5==1){
                                    rs = myStmt.executeQuery("select latitude,longitude from site where site_no in (select site_no_fk from complaint where type like 'River%')");
                        
                                    while(rs.next())
                                    {
                                        final Marker marker1 = new Marker(map);
                                        System.out.println("ladu khau jhala tar");
                                        marker1.setPosition(new LatLng(Double.parseDouble(rs.getString("latitude")),Double.parseDouble(rs.getString("longitude"))));
                                    }
                                }
                                
                            }
                        }
                       
                        else if(Map_Main.lo_flag==2)
                        {   
                                    if(Map_Main.ty_flag == 1)
                                    {
                                    rs = myStmt.executeQuery("select latitude,longitude from site where district = '"+Map_Main.dist+"';");

                                    while(rs.next())
                                    {
                                        final Marker marker1 = new Marker(map);
                                        marker1.setPosition(new LatLng(Double.parseDouble(rs.getString("latitude")),Double.parseDouble(rs.getString("longitude"))));
                                    }
                                    }
                                    else if(Map_Main.ty_flag==2)
                                    {
                                        if(Map_Main.sp_flag1==1){
                                            rs = myStmt.executeQuery("select latitude,longitude from site where district = '"+Map_Main.dist+"' and site_no in (select site_no_fk from complaint where type like 'Waste Dump%')");

                                            while(rs.next())
                                            {
                                                final Marker marker1 = new Marker(map);
                                                marker1.setPosition(new LatLng(Double.parseDouble(rs.getString("latitude")),Double.parseDouble(rs.getString("longitude"))));
                                            }
                                        }



                                        if(Map_Main.sp_flag2==1){
                                            rs = myStmt.executeQuery("select latitude,longitude from site where district = '"+Map_Main.dist+"' and site_no in (select site_no_fk from complaint where type like 'Construction%')");

                                            while(rs.next())
                                            {
                                                final Marker marker1 = new Marker(map);
                                                marker1.setPosition(new LatLng(Double.parseDouble(rs.getString("latitude")),Double.parseDouble(rs.getString("longitude"))));
                                            }
                                        }

                                        if(Map_Main.sp_flag3==1){
                                            rs = myStmt.executeQuery("select latitude,longitude from site where district = '"+Map_Main.dist+"' and site_no in (select site_no_fk from complaint where type like 'Untreated%')");

                                            while(rs.next())
                                            {
                                                final Marker marker1 = new Marker(map);

                                                marker1.setPosition(new LatLng(Double.parseDouble(rs.getString("latitude")),Double.parseDouble(rs.getString("longitude"))));
                                            }
                                        }

                                        if(Map_Main.sp_flag4==1){
                                            rs = myStmt.executeQuery("select latitude,longitude from site where district = '"+Map_Main.dist+"' and site_no in (select site_no_fk from complaint where type like 'Industrial%')");

                                            while(rs.next())
                                            {
                                                final Marker marker1 = new Marker(map);
                                                marker1.setPosition(new LatLng(Double.parseDouble(rs.getString("latitude")),Double.parseDouble(rs.getString("longitude"))));
                                            }
                                        }

                                        if(Map_Main.sp_flag5==1){
                                            rs = myStmt.executeQuery("select latitude,longitude from site where district = '"+Map_Main.dist+"' and site_no in (select site_no_fk from complaint where type like 'River%')");

                                            while(rs.next())
                                            {
                                                final Marker marker1 = new Marker(map);
                                                marker1.setPosition(new LatLng(Double.parseDouble(rs.getString("latitude")),Double.parseDouble(rs.getString("longitude"))));
                                            }
                                        }
                                
                                    }
                           
                            GeocoderRequest request = new GeocoderRequest(map);
                            request.setAddress(Map_Main.dist);

                                getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
                                @Override
                                    public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                                    if (status == GeocoderStatus.OK) {
                                        map.setCenter(result[0].getGeometry().getLocation());
                                        Marker marker = new Marker(map);
                                        marker.setPosition(result[0].getGeometry().getLocation());

                                        final InfoWindow window = new InfoWindow(map);
                                        window.setContent(Map_Main.dist);
                                        window.open(map, marker);
                                    }
                                }
                            });
                        }
                        
                        
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(MainMapCode.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MainMapCode.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    
                    
                    
                 
                }
            }
        });
    }
    public void load_map()
    {
        
        final MainMapCode view = new MainMapCode();
        
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "MAP"));
        this.setSize(700, 500);
        this.setLayout(new BorderLayout());
        this.add(view);
       
    }
    public static void main(String[] args) {

    }
}