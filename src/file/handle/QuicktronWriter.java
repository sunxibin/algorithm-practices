package file.handle;

import java.io.*;
import java.text.Collator;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * ${DESCRIPTION}
 *
 * @author sunxibin
 * @date 2019/1/23 17:00
 */
public class QuicktronWriter {

    public static void main(String[] args) {
        String inputPath = "C:\\Users\\user\\Desktop\\WCS标准接口资料\\快仓字典.txt";
        String outputPath = "C:\\Users\\user\\Desktop\\WCS标准接口资料\\dictionary.txt";

        readAndWrite(inputPath, outputPath);
    }

    public static void readAndWrite(String inputPath, String outputPath) {
        try{
            FileInputStream fis = new FileInputStream(new File(inputPath));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "UTF-8"),512);

            FileOutputStream fos = new FileOutputStream(new File(outputPath));
            //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"), 512);

            StringBuilder stringBuilder = new StringBuilder();
            for(String line = reader.readLine(); null != line; line = reader.readLine()) {
                line = line.replaceAll("\r\n","");
                line = line.replaceAll(" ","");
                stringBuilder.append(line);
            }
            String temp = stringBuilder.toString();
            String[] array = temp.split(",");
            List<String> list = Arrays.asList(array);

            //对中文词汇按首字母排序
            /*test.list.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return Collator.getInstance(Locale.CHINESE).compare(o1, o2);
                }
            });*/
            list.sort((String o1, String o2) -> Collator.getInstance(Locale.CHINESE).compare(o1, o2));

            String content = list.toString();
            fos.write(content.getBytes());
            fos.close();
            fis.close();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }
}
