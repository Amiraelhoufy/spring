package org.agcodes;

import org.agcodes.model.Song;
import org.agcodes.config.ProjectConfig;
import org.agcodes.services.VehicleServices;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example17 {

  public static void main(String[] args) {

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    VehicleServices vehicleServices = context.getBean(VehicleServices.class);

    Song song = new Song();
    song.setTitle("Blank Space");
    song.setSingerName("Taylor Swift");

    boolean vehicleStarted = true;
//    boolean vehicleStarted = false;
    String moveVehicleStatus = vehicleServices.moveVehicle(vehicleStarted);
    System.out.println("moveVehicleStatus: " + moveVehicleStatus);

    String playMusicStatus = vehicleServices.playMusic(vehicleStarted,song);
    System.out.println("playMusicStatus: " + playMusicStatus);

    String applyBrakeStatus = vehicleServices.applyBrake(vehicleStarted);
    System.out.println("applyBrakeStatus: " + applyBrakeStatus);

   }
}
