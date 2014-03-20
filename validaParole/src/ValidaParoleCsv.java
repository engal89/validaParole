import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class ValidaParoleCsv {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		populateAnagWordFromCsv();
	}
	
	public static void populateAnagWordFromCsv() {
  		String line = "";
  			
  		try{
  			InputStream inputStream = new FileInputStream("/anagrammed_words.csv");
			InputStreamReader is = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(is);
			
			int count=100;
				ArrayList<String> invalid = new ArrayList<String>();
				while ((line = br.readLine()) != null) {
					count++;
					if(count>1000 && count<1050){
						String[] sarray = line.split(",");
					    for (int i = 0; i < sarray.length; i++) {
					    	try {
						    	if(!isExistingWord(sarray[i]))
						    		invalid.add(sarray[i]);
					    	}catch(Exception e){
					    		System.out.println(e.toString());
					    		invalid.add(sarray[i]);
					    	}
						}
					}
				}
				for (int i = 0; i < invalid.size(); i++) {
					System.out.println(invalid.get(i));
				}
			}catch(Exception e){
				System.out.println("isExistingWord()"+ e.toString());
			}
	}
	
	public static Boolean isExistingWord(String word) throws Exception
    {
		Boolean exist = false;
		
		//Wiktionary
		String url = "http://en.wiktionary.org/wiki/";
		String message = "Wiktionary does not yet have an entry for";
		
		
//		String message = "Wikizionario non ha ancora una pagina con questo nome";
				
		//dizionario-italiano.org
//		String url ="http://www.dizionario-italiano.org/";
//		String message = "In questo momento la pagina richiesta  vuota";
		String html;
		
		if(word!=null && !"".equalsIgnoreCase(word)){

			html=getHTML(url+word.toLowerCase());
	        /*
	         * TODO: CONTROLLARE VARI LINGUAGGI
	         */
			
		    try{
				exist=!html.contains(message);
				
		        if(exist)
					System.out.println("La parola esiste - "+word.toUpperCase());
				else
					System.out.println("La parola NON esiste - "+word.toUpperCase());
		    } catch (Exception e){
		    	System.out.println("isExistingWord()"+ e.toString());
		    }
		} else {
			exist=false;
		}
		return exist;
    }
	
	public static String getHTML(String addr) throws Exception {
        URL url = new URL(addr);
        String buf = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = in.readLine())!=null) {
            buf += line+"\n";
        }
        return buf;
    }
}
