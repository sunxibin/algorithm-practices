package file.handle;

import java.io.*;
import java.util.ArrayList;

/**
 * ${DESCRIPTION}
 *
 * @author sunxibin
 * @date 2018/11/17 20:07
 */
public class ReadAndWriteDemo {
    //项目文件路径
    private static final String PROJECT_PATH = "G:\\sourceCode\\wcs-basic(2)\\wcs-basic";
    //输出文件路径
    private static final String TARGET_PATH = "G:\\sourceCode\\wcs\\javaCodeBasic.txt";


    public static void main(String[] args) {
        //输入文件路径
        String path = PROJECT_PATH;
        //输出文件路径
        String outputPath = TARGET_PATH;
        //java文件的集合
        ArrayList<File> javaFiles = new ArrayList<>();
        //java文件行数和
        Integer rows = 0;

        getJavaFile(path, javaFiles);
        rows = doWrite(outputPath, javaFiles);

        /*for(int i = 0; i < javaFiles.size(); i++) {
            System.out.println(javaFiles.get(i));
        }*/
        System.out.println("所有java文件的行数和为 : " + rows);

    }


    /**
     * 获取指定目录下的所有java文件
     * @param path
     * @param javaFiles
     */
    public static void getJavaFile(String path, ArrayList<File> javaFiles) {
        File file = new File(path);


        //TODO 将所有Java文件存入list
        if(file.exists()) {
            File[] files = file.listFiles();
            if(files != null && files.length != 0) {
                for(File tempFile : files) {
                    String fileName = tempFile.getName();
                    //递归
                    if (tempFile.isDirectory()) {
                        getJavaFile(tempFile.getAbsolutePath(), javaFiles);
                    }
                    //将java文件存入集合
                    else if(fileName.endsWith("java")) {
                        javaFiles.add(tempFile);
                    }
                    else {
                        continue;
                    }
                }
            }
        } else {
            System.out.println("文件不存在 : " + path);
        }

    }

    /**
     * 将所有的java文件写入到文本中
     * @param outputPath
     * @param javaFiles
     * @return  返回文件的行数
     */
    public static Integer doWrite(String outputPath, ArrayList<File> javaFiles) {
        //java程序的行数
        Integer rows = 0;

        //TODO 将list中的所有文件读取出来，写入一个文件中，统计行数
        try{
            FileOutputStream fos = new FileOutputStream(new File(outputPath));

            /*//方法一
            Vector<FileInputStream> vector = new Vector<>();
            for(File file : javaFiles) {
                vector.add(new FileInputStream(file));
            }
            Enumeration<FileInputStream> enumeration = vector.elements();
            SequenceInputStream sis = new SequenceInputStream(enumeration);

            byte[] buf = new byte[1024];
            int len = 0;

            while((len=sis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }*/
            
            //方法二
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"), 512);
            for(File file : javaFiles) {
                FileInputStream fis = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "UTF-8"), 512);
                for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                    //line.matches("\\s*");     //匹配任意空白行
                    line.replaceAll("\\s{2,}", " ");
                    if(!line.isEmpty() && !line.equals(" ") && !line.equals("\n") && !line.equals("\r\n")) {
                        line = line + "\r\n";
                        rows += 1;
                        writer.write(line);
                    }
                }
            }
            writer.flush();

            //sis.close();
            //fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return rows;
    }

}
