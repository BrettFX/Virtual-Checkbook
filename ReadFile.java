package allen.brett.checkbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {
	private String path;
	private int numLines;
	
	public ReadFile(String path)
	{
		this.path = path;
	}
	
	public void setNumLines() throws IOException
	{
		FileReader fReader = new FileReader(path);
		BufferedReader bReader = new BufferedReader(fReader);
		
		String line = "";
		
		while ((line = bReader.readLine()) != null)
		{
			numLines++;
		}
		bReader.close();
	}
	
	public int getNumLines()
	{	
		return numLines;
	}
	
	public String[] OpenFile() throws IOException
	{
		FileReader fReader = new FileReader(path);
		BufferedReader bReader = new BufferedReader(fReader);
		
		String[] textData = new String[numLines];
		
		for(int i = 0; i < numLines; i++)
		{
			textData[i] = bReader.readLine();
		}
		
		bReader.close();
		return textData;
	}
}
