package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewAcc {

    public String[] showParagraph(String id) {
        String path = "src\\files\\paragraphsAcc";
        String[] ids = new String[2];
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");
                line = buffer.readLine();
                if (id.equals(ids[0])) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ids;
    }
    public String[] findFull(String name) {
        String path = "src\\files\\accommondations";
        String[] info = new String[6];
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                info = line.split("/");
                line = buffer.readLine();
                if (name.equals(info[2]+" ")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }
    public String[] findreview(String id){
        String path = "src\\files\\reviews";
        String[] info = new String[10];
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                info = line.split("/");
                line = buffer.readLine();
                if(id.equals(info[0])){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!id.equals(info[0])){info=null;}
        return info;
    }
    public String[] findrp(String id){
        String path = "src\\files\\reviewparagraph";
        String[] info = new String[10];
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                info = line.split("/");
                line = buffer.readLine();
                if(id.equals(info[0])){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!id.equals(info[0])){info=null;}
        return info;
    }
    public String findname(String user){
        String path = "src\\files\\accounts";
        String[] info = new String[10];
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                info = line.split(" ");
                line = buffer.readLine();
                if(user.equals(info[3])){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info[1];
    }
}
