package Data_collector.data_collector.DataCollector;

import java.io.*;
public class SpectralAnalysis {
	private long lastDoc = 0;
	
	public static void main(String [] args) throws IOException{

	}
	
	public String[] getSpectralAnalysis() throws Exception{
	    File dir = new File("/temp"); 
		//long mod=0;
		File found=null;
		
		while(found == null){
			for(File tmp : dir.listFiles())
			{
				if(tmp.isFile() && tmp.getName().toLowerCase().endsWith(".erp") && tmp.lastModified() > lastDoc)
				{
					lastDoc = tmp.lastModified();
					found = tmp;
				}
			}
		}
		//System.out.println(found);
		String [] erparray = new String [9];
		
			FileReader fr = new FileReader(found);
		    BufferedReader br = new BufferedReader(fr);
		    String zeile = br.readLine();
		    erparray [0] =zeile.substring(zeile.indexOf("\"em1\":")+6,zeile.indexOf(",\"em2\""));; //em1
		    erparray [1] =zeile.substring(zeile.indexOf("\"em2\":")+6,zeile.indexOf(",\"a1\""));; //em2
		    erparray [2] =zeile.substring(zeile.indexOf("\"a1\":")+5,zeile.indexOf(",\"a2\""));; //a1
		    erparray [3] =zeile.substring(zeile.indexOf("\"a2\":")+5,zeile.indexOf(",\"b2\""));; //a2
		    erparray [4] =zeile.substring(zeile.indexOf("\"b2\":")+5,zeile.indexOf(",\"b1\""));; //b2
		    erparray [5] =zeile.substring(zeile.indexOf("\"b1\":")+5,zeile.indexOf(",\"overallStatus\""));; //b1
		    erparray [6] =zeile.substring(zeile.indexOf("\"overallStatus\":\"")+17,zeile.indexOf("\",\"ts_start\""));; //overallStatus
		    erparray [7] =zeile.substring(zeile.indexOf("\"ts_start\":")+11,zeile.indexOf(",\"ts_stop\""));; //ts_start
		    erparray [8] =zeile.substring(zeile.indexOf("\"ts_stop\":")+10,zeile.indexOf("}"));; //ts_stop
		    for(int j=0; j<erparray.length; j++){
		    	//System.out.println(erparray[j]);
		    }
		    br.close();
		    fr.close();
		    return erparray;

	}
}
