package api;

import java.io.*;

public class Delete {
    File tempFile = new File("src\\files\\temp");

    public void rearrange(File goal) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        FileWriter writer= new FileWriter(goal);

        String line = reader.readLine();
        writer.write(line);
        line = reader.readLine();
        while (line!=null) {
            writer.write("\n" + line);
            line = reader.readLine();
        }
        reader.close();
        writer.close();

        FileWriter writer1= new FileWriter(tempFile);
        writer1.write("");
        writer1.close();

    }
    public void deleteall(String id,File inputFile) throws IOException {
        String[] ids=new String[5] ;

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        try (BufferedReader buffer = new BufferedReader(new FileReader(inputFile))) {
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
        if(ids[0].equals(id)) {
            String lineToRemove = ids[0];
            for (int i = 1; i < ids.length; i++) {
                lineToRemove = lineToRemove + "/" + ids[i];
            }

            String currentLine = reader.readLine();
            String trimmedLine = currentLine.trim();
            if (!trimmedLine.equals(lineToRemove)) {
                writer.write(currentLine);
            } else {
                writer.write(reader.readLine());
            }

            while ((currentLine = reader.readLine()) != null) {
                trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) continue;

                writer.write("\n" + currentLine);
            }


            reader.close();
            writer.close();

            rearrange(inputFile);
        }
    }
}
