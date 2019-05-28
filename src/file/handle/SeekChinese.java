package file.handle;

import java.io.*;
import java.util.ArrayList;

/**
 * ${DESCRIPTION}
 *
 * @author sunxibin
 * @date 2018/12/20 15:09
 */
public class SeekChinese {
    //项目文件路径
    private static final String PROJECT_PATH = "G:\\sourceCode\\wcs\\yudo\\wcs-standardized-api";
    //private static final String PROJECT_PATH = "G:\\sourceCode\\wcs-basic\\wcs-basic";
    //输出文件路径
    private static final String TARGET_PATH = "G:\\sourceCode\\wcs\\test.txt";
    //private static final String TARGET_PATH = "G:\\sourceCode\\wcs-basic\\test.txt";


    public static void main(String[] args) {
        //输入文件路径
        String path = PROJECT_PATH;
        //输出文件路径
        String outputPath = TARGET_PATH;
        //项目文件的集合
        ArrayList<File> javaFiles = new ArrayList<>();
        ArrayList<String> fileNameList = new ArrayList<>();

        getFile(path, javaFiles);

        for(int i = 0; i < javaFiles.size(); i++) {
            File file = javaFiles.get(i);
            String fileName = file.getName();

            fileNameList.add(fileName);
        }
        doWrite(outputPath, javaFiles, fileNameList);

//        for(int i = 0; i < javaFiles.size(); i++) {
//            File file = javaFiles.get(i);
//            String fileName = file.getName();
//            if(fileName.equals("WmsMessageState.java")) {
//                filterFile(file, outputPath);
//            }
//        }

    }

    /**
     * 获取指定目录下的所有文件
     * @param path 项目路径
     * @param javaFiles 文件集合
     */
    public static void getFile(String path, ArrayList<File> javaFiles) {
        File file = new File(path);

        if(file.exists()) {
            File[] files = file.listFiles();
            if(files != null && files.length != 0) {
                for(File tempFile : files) {
                    String fileName = tempFile.getName();
                    //递归
                    if (tempFile.isDirectory()) {
                        getFile(tempFile.getAbsolutePath(), javaFiles);
                    }
                    else if( ( fileName.endsWith("java") || fileName.endsWith("properties") )
                            && !( fileName.contains("Test") || fileName.contains("test") ) ) {
                        javaFiles.add(tempFile);
                    }
                    //将文件存入集合
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
     * 将所有的文件中的中文写到文本中
     * @param outputPath    输出路径
     * @param javaFiles     文件集合
     */
    public static void doWrite(String outputPath, ArrayList<File> javaFiles, ArrayList<String> fileNameList) {
        //中文字符
        String reg = "[^\u4e00-\u9fa5]";

        try{
            FileOutputStream fos = new FileOutputStream(new File(outputPath));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"), 512);

            for(int i = 0; i < javaFiles.size(); i++) {
                File file = javaFiles.get(i);
                String fileName = fileNameList.get(i);
                //当前行数
                Integer rows = 0;
                Boolean flag = false;

                FileInputStream fis = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "UTF-8"), 512);
                for(String line = reader.readLine(); line != null; line = reader.readLine()) {
                    rows ++;

                    // 过滤注释
                    if(flag) {
                        if(line.contains("*/")) {
                            flag = false;
                        }
                        continue;
                    }
                    if(line.contains("/**") || line.contains("/*")) {
                        flag = true;
                        continue;
                    }
                    String tempLine = line;
                    tempLine = tempLine.replaceAll("\\s{2,}", "");
                    if(tempLine.startsWith("//")) {
                        continue;
                    }

                    // 提取出中文字符
                    String str = line.replaceAll("\\s{2,}", " ");
                    line = line.replaceAll(reg, "");
                    if(!line.isEmpty()) {
                        //line = line + "\r\n";
                        str = str + "\r\n";
                        writer.write("文件名：" + fileName + "    行数：" + rows + "    内容：" + str);
                    }

                }
            }
            writer.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 将指定文件中的指定内容读取出来
     * 过滤注释、过滤空行、过滤不包含中文字符的行
     * @param file
     * @param outputPath
     */
    public static void filterFile(File file, String outputPath) {
        try {
            //获取输出流
            FileOutputStream fos = new FileOutputStream(new File(outputPath));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"), 512);
            //获取输入流
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "UTF-8"), 512);

            String reg = "[^\u4e00-\u9fa5]";
            Boolean flag = false;
            for (String line = reader.readLine(); null != line; line = reader.readLine()) {
                // 过滤注释
                if (flag) {
                    if (line.contains("*/")) {
                        flag = false;
                    }
                    continue;
                }
                if (line.contains("/**") || line.contains("/*")) {
                    flag = true;
                    continue;
                }
                if (line.contains("//")) {
                    continue;
                }
                String temp = line.replaceAll("\\s{2,}", "");
                line = line.replaceAll(reg, "");
                if (!line.isEmpty()) {
                    /*String[] array1 = temp.split("\\(");
                    String[] array2 = temp.split("\"");
                    String s1 = array1[0];
                    String s2 = array2[3];*/
                    String s1 = temp.substring(0, temp.indexOf("("));
                    String s2 = temp.substring(temp.indexOf("WMS"), temp.indexOf("\")"));
                    writer.write(s1 + "=" + s2 + "\r\n");

                    writer.flush();
                }
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

}
