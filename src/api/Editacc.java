package api;

import java.io.*;
import java.util.*;

public class Editacc {


    public String getparagraph(String id){
        String path = "src\\files\\paragraphsAcc";
        int i = 0;
        String[] ids = new String[3];
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
        return ids[1];
    }
    public void changebenefits(String id,ArrayList<String> benefits) throws IOException {
        String path = "src\\files\\accommondations";
        String bnf = "";
        String newLine,oldLine;
        for (int i = 0; i < benefits.size(); i++) {
            if (i == (benefits.size() - 1)) {
                bnf = bnf + benefits.get(i);
            }else{bnf = bnf + benefits.get(i) + ",";}
        }
        String[] ids = new String[5];
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");
                if (id.equals(ids[0])) {
                    break;
                }
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(new File(path));
        StringBuilder buffer = new StringBuilder();
        buffer.append(sc.nextLine());
        while (sc.hasNextLine()) {
            buffer.append("\n").append(sc.nextLine());
        }
        String fileContents = buffer.toString();
        sc.close();
        if(benefits.isEmpty()){newLine = ids[0]+"/"+ids[1]+"/"+ids[2]+"/"+ids[3]+"/"+ids[4]+"/"+"";}
        else {newLine = ids[0]+"/"+ids[1]+"/"+ids[2]+"/"+ids[3]+"/"+ids[4]+"/"+bnf;}
        oldLine = ids[0]+"/"+ids[1]+"/"+ids[2]+"/"+ids[3]+"/"+ids[4]+"/"+ids[5];

        fileContents = fileContents.replaceAll(oldLine, newLine);

        FileWriter writer = new FileWriter(path);
        writer.append(fileContents);
        writer.flush();
    }
    public void changename(String id,String name ) throws IOException {
        String path = "src\\files\\accommondations";
        String[] ids = new String[5];
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");
                if (id.equals(ids[0])) {
                    break;
                }
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(new File(path));
        StringBuilder buffer = new StringBuilder();
        buffer.append(sc.nextLine());
        while (sc.hasNextLine()) {
            buffer.append("\n").append(sc.nextLine());
        }
        String fileContents = buffer.toString();
        sc.close();
        String oldLine = ids[0]+"/"+ids[1]+"/"+ids[2]+"/"+ids[3]+"/"+ids[4]+"/"+ids[5];
        String newLine = ids[0]+"/"+ids[1]+"/"+name+"/"+ids[3]+"/"+ids[4]+"/"+ids[5];

        fileContents = fileContents.replaceAll(oldLine, newLine);

        FileWriter writer = new FileWriter(path);
        writer.append(fileContents);
        writer.flush();
    }
    public void changelocation(String id,String location) throws IOException {
        String path = "src\\files\\accommondations";
        String[] ids = new String[5];
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");
                if (id.equals(ids[0])) {
                    break;
                }
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(new File(path));
        StringBuilder buffer = new StringBuilder();
        buffer.append(sc.nextLine());
        while (sc.hasNextLine()) {
            buffer.append("\n").append(sc.nextLine());
        }
        String fileContents = buffer.toString();
        sc.close();
        String oldLine = ids[0]+"/"+ids[1]+"/"+ids[2]+"/"+ids[3]+"/"+ids[4]+"/"+ids[5];
        String newLine = ids[0]+"/"+ids[1]+"/"+ids[2]+"/"+ids[3]+"/"+location+"/"+ids[5];

        fileContents = fileContents.replaceAll(oldLine, newLine);

        FileWriter writer = new FileWriter(path);
        writer.append(fileContents);
        writer.flush();
    }
    public void changetype(String id,String type) throws IOException {
        String path = "src\\files\\accommondations";
        String[] ids = new String[5];
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");
                if (id.equals(ids[0])) {
                    break;
                }
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(new File(path));
        StringBuilder buffer = new StringBuilder();
        buffer.append(sc.nextLine());
        while (sc.hasNextLine()) {
            buffer.append("\n").append(sc.nextLine());
        }
        String fileContents = buffer.toString();
        sc.close();
        String oldLine = ids[0]+"/"+ids[1]+"/"+ids[2]+"/"+ids[3]+"/"+ids[4]+"/"+ids[5];
        String newLine = ids[0]+"/"+ids[1]+"/"+ids[2]+"/"+type+"/"+ids[4]+"/"+ids[5];

        fileContents = fileContents.replaceAll(oldLine, newLine);

        FileWriter writer = new FileWriter(path);
        writer.append(fileContents);
        writer.flush();
    }

    public void replaceLines(String id, String p) throws IOException {
        String path = "src\\files\\paragraphsAcc";
        String[] ids = new String[10];
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");
                if (id.equals(ids[0])) {
                    break;
                }
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(new File(path));
        StringBuilder buffer = new StringBuilder();
        buffer.append(sc.nextLine());
        while (sc.hasNextLine()) {
            buffer.append("\n").append(sc.nextLine());
        }
        String fileContents = buffer.toString();
        sc.close();
        String oldLine = ids[0]+"/"+ids[1];
        String newLine = ids[0]+"/"+p;

        fileContents = fileContents.replaceAll(oldLine, newLine);

        FileWriter writer = new FileWriter(path);
        writer.append(fileContents);
        writer.flush();
    }

}
