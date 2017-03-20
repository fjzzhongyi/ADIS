package curve.anomaly;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class AnomalyCurve {
	private Vector<Float> prec_recall_fmeasure(ArrayList<String> detect_subgraph, ArrayList<String> true_subgraph) {
		float n = 0;
		for (String i : detect_subgraph) {
			if (true_subgraph.contains(i)) {
				n += 1;
			}
		}
		float prec = n / detect_subgraph.size();
		float recall = n / true_subgraph.size();
		float fmeasure = 0;
		if (prec < 1e-6 && recall < 1e-6) {
			fmeasure = 0;
		} else {
			fmeasure = 2 * (prec * recall / (prec + recall));
		}
		Vector<Float> re_vec = new Vector();
		re_vec.add(prec);
		re_vec.add(recall);
		re_vec.add(fmeasure);
		System.out.println(String.valueOf(prec)+" "+String.valueOf(recall)+" "+String.valueOf(fmeasure));
		return re_vec;
	}

	public AnomalyCurve(String truepath, String resultpath) {
		XYSeries prec = new XYSeries("precision");
		XYSeries recall = new XYSeries("recall");
		XYSeries fmeasure = new XYSeries("fmeasure");
		try {
			File f1 = new File(truepath);
			File f2 = new File(resultpath);
			System.out.println(f1.exists());
			FileReader fr1 = new FileReader(f1);
			FileReader fr2 = new FileReader(f2);
			BufferedReader br1 = new BufferedReader(fr1);
			BufferedReader br2 = new BufferedReader(fr2);
			String s1;
			s1 = br1.readLine();
			prec = new XYSeries("precision");
			recall = new XYSeries("recall");
			fmeasure = new XYSeries("fmeasure");
			for (int line = 1; s1!=null && s1.length() > 0; line++) {
				ArrayList<String> true_subgraph = new ArrayList<String>(Arrays.asList(s1.split(" ")));
				String s2=br2.readLine();
				System.out.println(s2);
				ArrayList<String> detect_subgraph;
				if (s2.length()>4){
					detect_subgraph = new ArrayList<String>(
						Arrays.asList(s2.substring(2, s2.length()-2).split("\', \'")));
				}else{
					detect_subgraph = new ArrayList<String>();
				}
				Vector v = prec_recall_fmeasure(detect_subgraph, true_subgraph);
				prec.add(line, (float) v.get(0));
				recall.add(line, (float) v.get(1));
				fmeasure.add(line, (float) v.get(2));
				s1 = br1.readLine();
			}
			fr1.close();
			fr2.close();
			br1.close();
			br2.close();
		} catch (IOException e) {

		}
		XYSeriesCollection collect = new XYSeriesCollection();
		collect.addSeries(prec);
		collect.addSeries(recall);
		collect.addSeries(fmeasure);
		ChartFrame cf = new ChartFrame(new XYDataset[]{(XYDataset)collect},new String[]{"Anomaly Detection Result"},new String[]{"Criteria"},new String[]{""});
	}
}
