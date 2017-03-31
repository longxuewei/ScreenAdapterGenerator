package cn.lxw.jar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class ScreenValueGenerator {

	private static float WIDTH = 720; // 标准宽度
	private static float HEIGHT = 1280;// 标准宽度
	public static File file;// 装载所有数据的文件夹
	public static DecimalFormat decimalFormat=new DecimalFormat("0.00");
	public static void main(String[] args) {
		
		if(args.length<2){
			System.out.println("请输入正确的参数: java -jar ScreenParams.jar 标宽 标高");
			return;
		}
		
		WIDTH = Float.parseFloat(args[0]);
		HEIGHT = Float.parseFloat(args[1]);
		
		
		initFile();
		generateFileData(854,480);
		generateFileData(960,640);
		generateFileData(1280,720);
		generateFileData(1280,768);
		generateFileData(1280,800);
		generateFileData(1920,1080);
		generateFileData(2560,1440);
		
		
	}

	public static void generateFileData(int h, int w) {
		try {

			File file = new File(ScreenValueGenerator.file, "value-" + h + "x" + w);

			if (!file.exists()) {
				file.mkdirs();
			}

			File xmlFile_x = new File(file, "x.xml");
			
			StringBuffer putData_x = putData(new StringBuffer(),"x",w,WIDTH);
			PrintWriter printWriter_x = new PrintWriter(new FileOutputStream(xmlFile_x));
			printWriter_x.println(putData_x.toString());
			printWriter_x.close();

			
			putData_x.delete(0, putData_x.length());
			
			
			File xmlFile_y = new File(file, "y.xml");
			StringBuffer putData_y = putData(putData_x,"y",h,HEIGHT);
			PrintWriter printWriter_y = new PrintWriter(new FileOutputStream(xmlFile_y));
			printWriter_y.println(putData_y.toString());
			printWriter_y.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 创建文件夹!
	 */
	private static void initFile() {
		file = new File("AllFile");
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	
	/**
	 * 拼接需要生成的字符串.
	 * @param buffer
	 * @param str
	 * @param dimen
	 * @param BaseDimen
	 * @return
	 */
	private static StringBuffer putData(StringBuffer buffer, String str,float dimen,float BaseDimen) {
		buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		buffer.append("<resources>\n");
		for (int i = 1; i <= 300; i++) {
			buffer.append("<dimen name=\"" + str + i + "\">" + decimalFormat.format(((dimen / BaseDimen) * i)) + "px</dimen>\n");
		}
		buffer.append("</resources>\n");
		return buffer;
	}

}
