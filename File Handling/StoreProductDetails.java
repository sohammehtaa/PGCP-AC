package tester;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Scanner;

import com.shop.core.Product;

import static utils.ShopUtils.*;

public class StoreProductDetails {

	public static void main(String[] args) {
		/*
		 * Get populated map of products from utils Store it in a bin file using -
		 * serialization
		 */
		System.out.println("Enter file name to store product details in bin format");
		try (Scanner sc = new Scanner(System.in);
				// Java App --> OOS --> FOS ---> bin file
				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream(sc.nextLine()))) {
			//get product map
			Map<Integer, Product> productMap =
					populateProductMap(populateProductList());
			//store product details in bin file
			out.writeObject(productMap);//serialiazation
			System.out.println("serialization done !");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
