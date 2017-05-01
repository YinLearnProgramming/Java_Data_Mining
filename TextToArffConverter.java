import java.util.*; 
import java.io.*;
import weka.core.*;
import weka.core.converters.ArffSaver;
import java.text.*;

public class TextToArffConverter 
{
	public static void main(String[] args) throws IOException, ParseException
	{
		FastVector attribute;
	    Instances dataSet;

		attribute = new FastVector(5);
		attribute.addElement(new Attribute("ID"));//numeric
		attribute.addElement(new Attribute("name", (FastVector) null));//string
		
		FastVector fvClassVal = new FastVector(2);
		fvClassVal.addElement("democrat");
		fvClassVal.addElement("republican");
		attribute.addElement(new Attribute("political_party",fvClassVal));
		
		FastVector fvNominalVal = new FastVector(5);
		fvNominalVal.addElement("CA");
		fvNominalVal.addElement("TX");
		fvNominalVal.addElement("NY");
		fvNominalVal.addElement("NC");
		fvNominalVal.addElement("SC");
		attribute.addElement(new Attribute("state",fvNominalVal));
		attribute.addElement(new Attribute("birth_date", "yyyy-MM-dd"));
		
		dataSet = new Instances("MyRelation", attribute, 0);
		
		String filename = "data.txt";
		File file = new File(filename);
	    Scanner inputFile = new Scanner(file);
	    
	    while (inputFile.hasNext())
	    {
	        String line = inputFile.nextLine();
	        String result = line.replaceAll("[+.^:']","");
	        String[] words = result.split(",");
	        
	        int id = Integer.parseInt(words[0]);
	        String name = words[1];
	        String political = words[2].replaceAll("\\s","");
	        String state = words[3];
	        String dob = words[4];
	        
	        double[] attValues = new double[dataSet.numAttributes()];
	        attValues[0] = id;
	    	attValues[1] = dataSet.attribute("name").addStringValue(name);
	        attValues[2] = dataSet.attribute("political_party").indexOfValue(political);
	        attValues[3] = dataSet.attribute("state").indexOfValue(state);
	    	attValues[4] = dataSet.attribute("birth_date").parseDate(dob);
	    	dataSet.add(new DenseInstance(1.0, attValues));
	    }
	    
	    System.out.println(dataSet);
	    
	    ArffSaver arffSaverInstance = new ArffSaver(); 
	    arffSaverInstance.setInstances(dataSet); 
	    arffSaverInstance.setFile(new File("ESDN.arff")); 
	    arffSaverInstance.writeBatch();    
	    inputFile.close();
	}
}
