package api;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class User {

    String[] names = new String[100];
    String[] types = new String[100];
    String[] addresses = new String[100];
    String[] findBen = new String[100];
    String findIdReview;

    public static String findAccId(String r)
    {
        String[] row=r.split("/");
        String[] info=new ViewAcc().findFull(row[0]);
        String id=info[0];

        return id;

    }

    public int findAccSum() {
        String path = "src\\files\\accommondations";
        int sum=0;

        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                sum++;
                line = buffer.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public String[] findName(String name)
    {
        String path = "src\\files\\accommondations";
        int i = 0;

        String[] ids;
        String[] row;
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");
                row = ids[2].split(" ");

                for(int j=0;j<row.length;j++)
                {
                    if (name.equals(row[j])) {
                        names[i] = ids[0];
                        i++;
                    }
                }

                if(name.equals(ids[2]))
                {
                    names[i] = ids[0];
                    i++;
                }


                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    public String[] findType(String selectedItem)
    {
        String path = "src\\files\\accommondations";
        int i = 0;

        String[] ids;
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");

                if (selectedItem.equals(ids[3])) {
                    types[i] = ids[0];
                    i++;
                }

                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return types;
    }

    public String[] findAddress(String text)
    {
        String path = "src\\files\\accommondations";
        int i = 0;
        int sum = 0;

        String[] ids;
        String[] row;
        String[] spaces = new String[100];
        String[] spaces2 = new String[100];
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");
                row = ids[4].split(",");


                for(int j=0;j< row.length;j++)
                {
                    spaces = row[j].split(" ");

                    for (int l=0;l< spaces.length;l++)
                    {
                        if(text.equals(spaces[l]))
                        {
                            for(int k=0;k<addresses.length;k++)
                            {
                                if(addresses[k]!=ids[0])
                                {
                                    addresses[i] = ids[0];
                                    i++;
                                    break;
                                }
                                else{
                                    break;
                                }
                            }

                        }
                    }


                    if(text.equals(row[j]))
                    {
                        for(int k=0;k<addresses.length;k++)
                        {
                            if(addresses[k]!=ids[0])
                            {
                                addresses[i] = ids[0];
                                i++;
                                break;
                            }
                            else{
                                break;
                            }
                        }
                    }

                }

                if (text.equals(ids[4]))
                {
                    for(int k=0;k<addresses.length;k++)
                    {
                        if(addresses[k]!=ids[0])
                        {
                            addresses[i] = ids[0];
                            i++;
                            break;
                        }
                        else{
                            break;
                        }
                    }
                }


                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    public String[] findBenefits(ArrayList<String> benefits)
    {
        String path = "src\\files\\accommondations";
        int i = 0;
        int k = 0;
        int sum = 0;

        String[] ids;
        String[] row;
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");
                row = ids[5].split(",");

                for (String s : row) {
                    for (i = 0; i < benefits.size(); i++) {

                        if (s.equals(benefits.get(i))) {
                            sum++;
                        }

                    }

                    if (sum >= 5) {
                        findBen[k] = ids[0];
                        k++;
                        break;
                    }

                }

               // System.out.println("sum : " + sum);
                sum=0;

                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return findBen;
    }

    public void leaveReviewParag(String id, String text) throws IOException {
        String path = "src\\files\\reviewparagraph";
        String[] ids = new String[100];
        int charCount=0;
        int sum=0;
        int flag =0;

        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");
                charCount= charCount + ids.length;

                flag=0;

                if(id.equals(ids[0])) {
                    flag=1;

                    String oldLine = "";
                    String newLine = "";

                    Scanner sc = new Scanner(new File(path));
                    StringBuilder buffer2 = new StringBuilder();
                    buffer2.append(sc.nextLine());
                    while (sc.hasNextLine()) {
                        buffer2.append("\n").append(sc.nextLine()); }

                    String fileContents = buffer2.toString();
                    sc.close();

                    for(int i=0;i<charCount;i++)
                    {
                        if(i==charCount-1) {
                            oldLine = oldLine + ids[i];
                            break;
                        }

                        oldLine = oldLine + ids[i]+"/";
                    }

                    for(int j=0;j<charCount+1;j++)
                    {

                        if(j==charCount)
                        {
                            newLine = newLine + text;
                            break;
                        }

                        newLine = newLine + ids[j] + "/";

                    }

                    fileContents = fileContents.replaceAll(oldLine, newLine);

                    FileWriter writer = new FileWriter(path);
                    writer.append(fileContents);
                    writer.flush();

                    line = buffer.readLine();

                    break;
                }

                    line = buffer.readLine();
                    charCount = 0;
                }

            if(flag==0)
            {
                try (BufferedWriter buffer1 = new BufferedWriter(new FileWriter(path, true))) {
                    buffer1.write("\n" + id + "/");
                    buffer1.write(text);
                }
                catch(IOException e){
                    e.printStackTrace();
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String findReview(String id) throws IOException
    {
        String path = "src\\files\\reviews";
        String[] ids = new String[100];
        double avg=0.0;
        String str="";
        int sum=0;
        int count=0;
        int charCount=0;

        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");

                charCount= charCount + ids.length;
                if (id.equals(ids[0])) {
                    break;
                }
                line = buffer.readLine();
                charCount = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<charCount;i=i+2)
        {

            if(i!=0) {
                count = count + Integer.parseInt(ids[i]);
                sum++;
            }
        }


        if(sum!=0)
          avg = count / (sum*1.0);

        str = String. format("%.1f",avg);

        return str;

    }

    public String findReviewCount(String id) throws IOException
    {
        String path = "src\\files\\reviews";
        String[] ids = new String[100];
        float avg=0;
        String str="";
        int sum=0;
        int count=0;
        int charCount=0;

        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");

                charCount= charCount + ids.length;
                if (id.equals(ids[0])) {
                    break;
                }
                line = buffer.readLine();
                charCount = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<charCount;i=i+2)
        {

            if(i!=0) {
                count = count + Integer.parseInt(ids[i]);
                sum++;
            }

        }

        str = Integer.toString(sum);

        return str;

    }



    public void leaveReview(String id, String name, String rate) throws IOException
    {
        String path = "src\\files\\reviews";
        String[] ids = new String[100];
        int charCount=0;
        int flag=0;

        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                ids = line.split("/");

                charCount= charCount + ids.length;
                if (id.equals(ids[0])) {
                    flag=1;


                    String oldLine = "";
                    String newLine = "";

                    Scanner sc = new Scanner(new File(path));
                    StringBuilder buffer2 = new StringBuilder();
                    buffer2.append(sc.nextLine());
                    while (sc.hasNextLine()) {
                        buffer2.append("\n").append(sc.nextLine());
                    }
                    String fileContents = buffer2.toString();
                    sc.close();

                    for(int i=0;i<charCount;i++)
                    {
                        if(i==charCount-1) {
                            oldLine = oldLine + ids[i];
                            break;
                        }

                        oldLine = oldLine + ids[i]+"/";
                    }

                    for(int j=0;j<charCount+2;j++)
                    {

                        if(j==charCount)
                            newLine = newLine + name + "/";

                        if(j==charCount+1)
                        {
                            newLine = newLine + rate;
                            break;
                        }

                        if(j!=charCount)
                            newLine = newLine + ids[j] + "/";

                    }

                    fileContents = fileContents.replaceAll(oldLine, newLine);

                    FileWriter writer = new FileWriter(path);
                    writer.append(fileContents);
                    writer.flush();

                    break;
                }
                line = buffer.readLine();
                charCount = 0;
            }

            if(flag==0)
            {
                try (BufferedWriter buffer1 = new BufferedWriter(new FileWriter(path, true))) {
                    buffer1.write("\n" + id + "/" + name + "/" );
                    buffer1.write(rate);
                }
                catch(IOException e){
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String[] showSearch2(String id) {
        String path = "src\\files\\accommondations";
        int i = 0;
        int j=0;
        String[] ids = new String[100];
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
        return ids;
    }

}
