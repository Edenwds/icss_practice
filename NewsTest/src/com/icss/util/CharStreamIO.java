package com.icss.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class CharStreamIO {

	/**
	 * 复制文件
	 * @param path  源文件路径
	 * @param dpath  目的文件路径
	 */
	public void copyFile(String path, String dpath){
		File dfile = new File(dpath);
		if(dfile.exists()){
			dfile.delete();
		}
		PrintWriter out = null;
		BufferedReader buf = null;
		try {
			buf = new BufferedReader(new FileReader(path));
			out = new PrintWriter(new FileWriter(dpath));
			String str = "";
			while((str = buf.readLine()) != null){
				out.println(str);
				out.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out !=  null){
				out.close();
			}
			if(buf != null){
				try {
					buf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 将字符串类型信息写入新文件
	 * @param info
	 * @param dpath
	 */
	public void writeFile(String info, String dpath){
		File dfile = new File(dpath);
		if(dfile.exists()){
			dfile.delete();
		}
		PrintWriter out =null;
		try {
			out = new PrintWriter(new FileWriter(dfile));
			out.write(info);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
				
	}
	/**
	 * 读取文件 返回字符串
	 * @param path
	 * @return
	 * @throws NoSuchFileException 
	 */
	public String readFile(String path){
		File file = new File(path);
		BufferedReader buf = null;
		StringBuilder sb = new StringBuilder();
		if(file.exists()){
			try {
				buf = new BufferedReader(new InputStreamReader(new FileInputStream(file),"gbk"));
				String str = "";
				while((str = buf.readLine()) != null){
					sb.append(str);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(buf != null){
					try {
						buf.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else{
			System.out.println(file.getName()+"文件不存在 ");
		}
		return sb.toString();
	}
	
}
