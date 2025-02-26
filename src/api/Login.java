package api;
import java.io.*;
import java.util.Scanner;

public class Login {

    public static boolean chechforrole(String un){
        boolean isAuthenticated=false;

        String path= "src\\files\\accounts";
        File file= new File(path);

        try{
            Scanner inputBuffer= new Scanner(file);

            while(inputBuffer.hasNext()){
                String line= inputBuffer.nextLine();
                String[] values =line.split(" ");

                if(values[3].equals(un)){
                    if(values[5].equals("provider")){
                        isAuthenticated= true;
                    }
                }
            }
        }catch(FileNotFoundException fe){
            fe.printStackTrace();
        }
        return isAuthenticated;
    }


    public static boolean login(String un, String pw){
        boolean isAuthenticated=false;

        String path= "src\\files\\accounts";
        File file= new File(path);

        try{
            Scanner inputBuffer= new Scanner(file);

            while(inputBuffer.hasNext()){
                String line= inputBuffer.nextLine();
                String[] values =line.split(" ");

                if(values[3].equals(un)){
                    if(values[4].equals(pw)){
                        isAuthenticated= true;
                    }
                }
            }
        }catch(FileNotFoundException fe){
            fe.printStackTrace();
        }
        return isAuthenticated;
    }
}
