package Database;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

//Database class to write object into serializable file (.dat)

public class database {
    
    //Constructor that reads all the data file during initialisation of programme
    public database(){
    }
    
    public String determineClass(Object object){
        Class c = object.getClass();
        String cStr = c.getName();
        cStr = cStr.substring(cStr.lastIndexOf('.')+1);
        return cStr;
    }

    //Serialise object into .dat files
    public void serializeObject(Object object){
        try{
            String classType = determineClass(object);
            System.out.println("Class name is : " + classType);
            String path = "C:/Users/wloon/Desktop/NTU/Y2S1/SC2002 Object Oriented Design & Programming/Assignment/Database/" + classType + ".dat";
            FileOutputStream fis = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fis);
            out.writeObject(object);
            out.close();
            fis.close();
            //System.out.println("Serialised object name: " + filename);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public Object readSerialisedObject(Object obj){
        try {
            String classType = determineClass(obj);
            System.out.println("Class name is : " + classType);
            String path = "C:/Users/wloon/Desktop/NTU/Y2S1/SC2002 Object Oriented Design & Programming/Assignment/Database/" + classType + ".dat";
            FileInputStream fis = new FileInputStream(path);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream in = new ObjectInputStream(bis);
            Object object = in.readObject();
            in.close();
            System.out.println("Deserialised!");
            return object;
        }
        catch (IOException ex) {
            System.out.println("IOException caught!");
			ex.printStackTrace();
            return null;
		} catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound exception caught!");
			ex.printStackTrace();
            return null;
		}
    }

    public static void main(String[] args) {
        Professor prof = new Professor("JiaWen", "Jlee242", 999);
        database obj = new database();
        obj.serializeObject(prof);
    }
}

