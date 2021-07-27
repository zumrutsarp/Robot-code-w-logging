package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.EntryListenerFlags;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class LogSubsystem {

File file=new File("C:/log.txt");
FileWriter fw = null;
BufferedWriter bw = null;
String csvString = "";
Date date = new Date(0);

  // for client 
 NetworkTableInstance inst = NetworkTableInstance.getDefault();
 NetworkTable table = inst.getTable("datatable");
 

// the area where we receive the entries


 //  get entry    from table

public NetworkTableEntry m_odometry=table.getEntry(" m_odometry");
public NetworkTableEntry Pressure=table.getEntry("Pressure");
public NetworkTableEntry battery_voltage =table.getEntry("Battery Voltage");
public NetworkTableEntry turretStatus =table.getEntry("turret Status");
public NetworkTableEntry  shooter_RPM =table.getEntry(" shooter RPM");
public NetworkTableEntry  shooterVelocity=table.getEntry("shooterVelocity");

//add data listener
public void addListener(Object RobotController) {
   //for odometry
    table.addEntryListener("m_odometry", (table, key, entry,  value,flags)-> {
     double m_odometry = value.getDouble();
    System.out.println("odometry  changed "+ m_odometry);   

    }, EntryListenerFlags.kUpdate | EntryListenerFlags.kImmediate | EntryListenerFlags.kNew);


 


     // for pressure 
     table.addEntryListener("Pressure", (table, key, entry,  value,flags)-> {
      double Pressure= value.getDouble();
     System.out.println("Pressure  changed "+ Pressure);   
 
     }, EntryListenerFlags.kUpdate | EntryListenerFlags.kImmediate | EntryListenerFlags.kNew);
 

     // for battery voltage
     table.addEntryListener("  battery_voltage ", (table, key, entry,  value,flags)-> {
      double  battery_voltage = value.getDouble();
     System.out.println("  battery voltage   changed "+   battery_voltage );   
 
     }, EntryListenerFlags.kUpdate | EntryListenerFlags.kImmediate | EntryListenerFlags.kNew);
 

     // for turret
     table.addEntryListener("turretStatus", (table, key, entry,  value,flags)-> {
      double turretStatus= value.getDouble();
     System.out.println("turretStatus changed "+ turretStatus);   
 
     }, EntryListenerFlags.kUpdate | EntryListenerFlags.kImmediate | EntryListenerFlags.kNew);
 

     //for shooter RPM
    table.addEntryListener("shooter_RPM", (table, key, entry,  value,flags)-> {
      double shooter_RPM = value.getDouble();
     System.out.println("shooter RPM  changed "+shooter_RPM);   
 
     }, EntryListenerFlags.kUpdate | EntryListenerFlags.kImmediate | EntryListenerFlags.kNew);
 


     
     // for shooter Velocity
     table.addEntryListener("shooterVelocity", (table, key, entry,  value,flags)-> {
      double shooterVelocity= value.getDouble();
     System.out.println("shooter Velocity  changed "+ shooterVelocity);   
 
     }, EntryListenerFlags.kUpdate | EntryListenerFlags.kImmediate | EntryListenerFlags.kNew);
 

 // adding  to csv file 

 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
 Date date = new Date(System.currentTimeMillis());
 csvString +=(formatter.format(date)+m_odometry+Pressure+ battery_voltage+turretStatus+shooter_RPM+shooterVelocity+"\n");
  try {

   fw = new FileWriter(file);
   bw = new BufferedWriter(fw);
  bw.write(csvString);
 
  bw.close();
} catch (IOException ioe) {
   ioe.printStackTrace();
   }
  

}
}