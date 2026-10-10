package tester;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.Scanner;

import com.shop.core.Product;

public class RestoreDeatils {

	public static void main(String[] args) {
		System.out.println("Enter file name for resting product details");
		try (Scanner sc = new Scanner(System.in);
				// Java APP <--- OIS <--- FIS <--- bin file
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(sc.nextLine()))) {
			Map<Integer, Product> map = 
					(Map<Integer, Product>) in.readObject();
			System.out.println("Restored products");
			map.forEach((k,v) ->System.out.println(v));
			System.out.println("data restored....");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
