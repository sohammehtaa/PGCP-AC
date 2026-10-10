package strms;

import static utils.CollectionUtils.populateData;
import static utils.CollectionUtils.populateMapFromList;

import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;

import com.app.core.Category;
import com.app.core.Product;

import custom_exceptions.ProductHandlingException;
/*
 * Use CollectionUtils method to get map of products (Can add more data for testing if you want!)
Display name of the product , from a specific category , having least price.
If there are no products , in specified category , throw : ProductNotFound : custom exception
Accept category from user.
API : Optional : orElseThrow
 */
public class TestStreams {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			Map<Integer, Product> map = populateMapFromList(populateData());
			System.out.println("Available Products");
			map.values().forEach(System.out::println);
			//How will you display both : K & V
			System.out.println("Display product ids n details");
			map.forEach((k,v) -> System.out.println("ID "+k+":"+v));
		System.out.println("Enter Category");
			Category cat = Category.valueOf(sc.next().toUpperCase());
			String pName =map.values().stream()// Stream<Product> : all products : src of the
																		// stream
					.filter(p -> p.getProductCatgeory() == cat) // Stream<Product> : filtered products only by specific
																// category :intermediate opearion
					.min(Comparator.comparing(Product::getPrice)) // terminal operation returning Optional<Product>
					.orElseThrow(() -> new ProductHandlingException("No product under category " + cat)).
					getName();
			System.out.println("Prdouct name with least price under category "+cat+" is "+pName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
