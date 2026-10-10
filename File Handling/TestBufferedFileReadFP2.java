package char_io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/*
 * Read data from text file till EOF , in buffered manner n display the same
 */
public class TestBufferedFileReadFP2 {

	public static void main(String[] args) {
		System.out.println("Enter file name along with path");
		try(Scanner sc=new Scanner(System.in);
				//Java Application <----BR <--- FR <-- text file
				BufferedReader br=new BufferedReader(
						new FileReader(sc.nextLine()))
				) {

			br.lines() //Stream<String> : all lines
			.filter(s -> s.length()>30) //Stream<String> : filtered lines
			.map(line -> line.toUpperCase())
			.forEach(line -> System.out.println(line));
			System.out.println("end of file read....");
		} //br.close() , sc.close
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("main over...");

	}

}
