import java.util.*;
import weka.core.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import weka.classifiers.*;
import weka.classifiers.trees.J48;
import weka.classifiers.meta.FilteredClassifier;

public class J48DTree 
{
	public static void main(String[] args) throws Exception
	{
		BufferedReader reader = new BufferedReader(new FileReader("C:/Users/YH_PC/Documents/Eclipse/WekaExample/src/weather.nominal.arff"));
		Instances data = new Instances(reader);
		reader.close();
		
		data.setClassIndex(data.numAttributes() - 1);
		
		Classifier classForClassifier = new J48();
		J48 myTree = new J48();
		myTree.buildClassifier(data);
		
		
		Evaluation eva = new Evaluation(data);
		Random rand = new Random(1); //seed =1 
		int folds = 10;
		eva.crossValidateModel(classForClassifier, data, folds, rand);
		
		System.out.println(myTree);
		System.out.println(eva.toString());
		System.out.println(eva.toSummaryString());
		System.out.println(eva.toClassDetailsString());
		System.out.println(eva.toMatrixString());
	}
}
