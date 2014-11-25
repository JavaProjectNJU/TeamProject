package net;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import word.Word;

public class FromBaidu extends WordEngine{
	private static String url_prefix = "http://dict.baidu.com/s?wd=";//the word that want to search
	public FromBaidu(){
		//need to do nothing
	}
	
	protected Word analyze(String text_HTML){//annalyze the word from html text
		//System.out.print(text_HTML);
		if(text_HTML == null || text_HTML.length() == 0)
			return null;//text error 
		String wo = null;
		String pron_EN_UK = null;
		String pron_EN_US = null;
		ArrayList<String> explain = new ArrayList<String>();
		Pattern wordPattern = Pattern.compile("(?<=cidianData = \\{)[\u0000-\uFFFF]*(?= \\+ netExplain \\+ zhExplain(\\s*)\\};(\\s*)window\\.cidianData = cidianData;)");
		Matcher m = wordPattern.matcher(text_HTML);
		String wordString = null;
		if(m.find()){
			wordString = m.group();
		}else{
			
			return null;
		}
		System.out.println("find"+ wordString);
		
		
		Word word = new Word();
		word.setWord(wo);
		word.setPron_EN_US(pron_EN_US);
		word.setPron_EN_UK(pron_EN_UK);
		word.setExplain(explain);
		
		return word;
	}

	@Override
	protected URL getURL(String word) throws MalformedURLException {
		// TODO Auto-generated method stub
		return new URL(url_prefix + word);
	}
	public static void main(String[] args){
		WordEngine baidu = new FromBaidu();
		baidu.search("test");
	}
}
