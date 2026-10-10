package char_io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Copy data from text file , in buffered manner 
 */
public class TextFileCopy {

	public static void main(String[] args) {
		System.out.println("Enter src file name n dest file names , on separate lines");
		try (Scanner sc = new Scanner(System.in);
				// Java Application <----BR <--- FR <-- text file
				BufferedReader br = 
						new BufferedReader(new FileReader(sc.nextLine()));
		// Java App ---> PW --(buf full | flush | close | flush upon new line ) -> FW ->
		// text file
			PrintWriter out=
			new PrintWriter(new FileWriter(sc.nextLine()))
		) {	
			br.lines() // Stream<String>
			
					.forEach(line -> out.println(line));
			System.out.println("end of copy....");
		} // br.close() , sc.close
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("main over...");

	}

}
