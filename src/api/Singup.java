package api;

import java.io.*;

public class Singup {

    public static boolean checkusername(String name){
        String path = "src\\files\\accounts";
        boolean noDoubles=true;
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            String[] usernames;
            while (line != null) {
                usernames = line.split(" ");
               if(usernames[3].equals(name)){ noDoubles=false; }
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }return noDoubles;
    }

    public static void signup(String name, String lname, String user, String pass, int n) {
        String path = "src\\files\\accounts";
        int previd = 0;

        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            String[] ids = new String[0];
            while (line != null) {
                ids = line.split(" ");
                line = buffer.readLine();
            }
            previd = Integer.parseInt(ids[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter buffer1 = new BufferedWriter(new FileWriter(path, true))) {
            previd=previd+1;
            buffer1.write("\n"+previd+" ");
            buffer1.write(name + " ");
            buffer1.write(lname + " ");
            buffer1.write(user + " ");
            buffer1.write(pass + " ");
            if (n == 1) {
                buffer1.write("provider");
            } else if (n == 2) {
                buffer1.write("user");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}

