 import java.io.File;
 import weka.core.Attribute;
 import weka.core.DenseInstance;
 import java.util.*; 
 import weka.core.Instances;
 import weka.core.converters.ArffSaver;
 
 public class TestARFF {
	 public static void main(String[] args) throws Exception 
	  {
	      ArrayList<Attribute> attributes;
	      Instances dataSet;
	      double[] values;
	      attributes = new ArrayList<Attribute>();
	       
	      attributes.add(new Attribute("att1")); 
	      attributes.add(new Attribute("att2")); 
	      attributes.add(new Attribute("att3")); 
	      attributes.add(new Attribute("att4"));
	     
	      dataSet = new Instances("TestARFF", attributes, 0); // The first argument is the name of the relation
	        
	    values = new double[dataSet.numAttributes()]; 
	    values[0] = 3;	   
	    values[1] = 7;
	    values[2] = 5;
	    values[3] = 1;
	     dataSet.add(new DenseInstance(1.0, values));
	     
	     values = new double[dataSet.numAttributes()]; 
	     values[0] = 3;	   
	     values[1] = 7;
	     values[2] = 9;
	     values[3] = 8;  
	      
	      dataSet.add(new DenseInstance(1.0, values));
	    
	      //Printing the the arff on console window
	      System.out.println(dataSet);
		    
	     //Creating arff file 
	     ArffSaver arffSaverInstance = new ArffSaver(); 
	     arffSaverInstance.setInstances(dataSet); 
	     arffSaverInstance.setFile(new File("ESDN.arff")); 
	     arffSaverInstance.writeBatch();     
	        	      
	  }
 }