package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class LogSubsystem {

private static ArrayList<String> entryList;
File file=new File("C:/log.csv");
String csvString = "";

 // for client 
NetworkTableInstance inst = NetworkTableInstance.getDefault();
NetworkTable table = inst.getTable("datatable");
 


public void  getLog( String entryName,String level){

  // the method get our entries
  entryList = new ArrayList<>();
  table.getEntry(entryName);
  entryList.add(table.getEntry( entryName).getName()); 
  

  // Time stamp
  SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
  Date date = new Date(System.currentTimeMillis());


  switch(level)

  {

     case "Info": csvString += ("INFO:"+formatter.format(date)+"\n");   break;
    
     case "Debug": csvString += ("DEBUG:"+formatter.format(date)+"\n");  break; 

    case "Eror":   csvString += ("EROR:"+formatter.format(date)+"\n");   break;
    
     case "Warning":   csvString += ("WARNİNG:"+formatter.format(date)+entryList+"\n");  break;
   }
   

   try {

    FileWriter fw  = new FileWriter(file);
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(csvString);
    bw.close();
    }

  catch (IOException ioe) {
    ioe.printStackTrace();
    }


}
     
    }