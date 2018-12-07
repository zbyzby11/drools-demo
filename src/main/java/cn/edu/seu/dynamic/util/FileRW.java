package cn.edu.seu.dynamic.util;

import java.io.*;

public class FileRW
{
    public String readFile(String filePath)
    {
        String result = "";
        try
        {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null)
            {
                result += line;
                result += "\n";
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean writeFile(String content, String filePath)
    {
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(content);
            writer.close();
            return Boolean.TRUE;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
}
