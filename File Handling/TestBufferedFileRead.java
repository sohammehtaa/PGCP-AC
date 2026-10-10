package char_io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/*
 * Read data from text file till EOF , in buffered manner n display the same
 */
public class TestBufferedFileRead {

	public static void main(String[] args) {
		System.out.println("Enter file name along with path");
		try(Scanner sc=new Scanner(System.in);
				//Java Application <----BR <--- FR <-- text file
				BufferedReader br=new BufferedReader(
						new FileReader(sc.nextLine()))
				) {
			//read data from Buffer of BR till EOS
			String line=null;
			while((line=br.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("end of file read....");
		} //br.close() , sc.close
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("main over...");

	}

}
