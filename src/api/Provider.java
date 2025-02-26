package api;
import java.io.*;
import java.util.ArrayList;

public class Provider {
    int previd = 0;
    String[] myids = new String[100];

    public int findlastid() {
        String path = "src\\files\\accommondations";

        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            String[] ids = new String[0];
            while (line != null) {
                ids = line.split("/");
                line = buffer.readLine();
            }
            previd = Integer.parseInt(ids[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return previd;
    }

    public void firstInfo(String user, String name, String type, String address, ArrayList<String> benefits) {
        String path = "src\\files\\accommondations";
        String bnf = "/";
        for (int i = 0; i < benefits.size(); i++) {
            if (i == (benefits.size() - 1)) {
                bnf = bnf + benefits.get(i);
            }else{bnf = bnf + benefits.get(i) + ",";}
        }
        try (BufferedWriter buffer1 = new BufferedWriter(new FileWriter(path, true))) {
            previd = findlastid() + 1;
            buffer1.write("\n" + previd + "/");
            buffer1.write(user + "/");
            buffer1.write(name + "/");
            buffer1.write(type + "/");
            buffer1.write(address);
            buffer1.write(bnf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] findmyids(String username) {
        String path = "src\\files\\accommondations";
        int i = 0;
        String str = " ";
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            String[] ids;
            while (line != null) {
                ids = line.split("/");
                line = buffer.readLine();
                str = ids[0];
                if (ids[1].equals(username)) {
                    myids[i] = str;
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myids;
    }

    public String[] showAccommodations(String id) {
        String path = "src\\files\\accommondations";
        int i = 0;
        String[] ids = new String[10];
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

    public String[] findreviews(String[] userids){
        String path = "src\\files\\reviews";
        String[] ids=new String[200];
        String[] tall;
        int j=0;
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                tall= line.split("/");
                for(int i = 0; userids[i]!=null; i++){
                    if(tall[0].equals(userids[i])){
                        ids[j] = line;
                        j++;
                    }
                }
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public int[] calculate(int[] reviews) {
        int[] calc = new int[2];
        int sum = 0, total = 0, a = 0;
        for (int i=0;reviews[i]!=0;i++){
            sum=sum+reviews[i];
            total++;
        }
        calc[0] = sum;
        calc[1] =total;
        return calc;
    }

    public String onlyAvg(String id) {
        String path = "src\\files\\reviews";
        int num = 0;
        double avg = 0.0, sum = 0.0, total = 0.0;
        String[] tall;
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            while (line != null) {
                tall = line.split("/");
                if (tall[0].equals(id)) {
                    for(int i=2;i<=tall.length;i=i+2){
                        sum= sum+Integer.parseInt(tall[i]);
                        total++;
                    }
                    avg=sum/total;
                }
                line = buffer.readLine();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        String str = String. format("%.1f",avg);
        return str;
    }
    public void addinfo(String str){
        String path = "src\\files\\paragraphsAcc";
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {
            String line = buffer.readLine();
            String[] ids = new String[0];
            while (line != null) {
                ids = line.split("/");
                line = buffer.readLine();
            }
            previd = Integer.parseInt(ids[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter buffer1 = new BufferedWriter(new FileWriter(path, true))) {
            previd = previd+ 1;
            buffer1.write("\n" + previd + "/");
            buffer1.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
