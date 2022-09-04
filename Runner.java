package targil1;
/*Tom Kondat 318275591
David Sharabi 315313981
 */
import java.util.Scanner;

public class Runner {

	public static Scanner sc= new Scanner(System.in);

	public static void main(String[] args) {

		boolean flag = true;
		int p = 0;
		int sizeP = 0;
		int mainSize = 0;

		//check strong of two
		while(flag) {
			System.out.println("What is the memory size of the program?");
			mainSize = sc.nextInt();
			int temp = 1;
			while(temp < mainSize) {
				temp *= 2;

			}
			if( temp == mainSize)
				flag = false;
			else {
				System.out.println("Not strong of two, please try again...");
			}
		}
		//create the tree of nodes
		Node memory = new Node(mainSize,null, 0, mainSize - 1);

		boolean exit = true;//exit the program

		while(exit) {
			//menu
			System.out.println("1. Enter process\r\n"
					+ "2. Exit process\r\n"
					+ "3. Print status\r\n"
					+ "4. Exit\r\n"
					+ "Enter your choice:"
					+ "");
			switch( sc.nextInt()) {
			case 1://set process
				System.out.println("What is the number of the process?"); 
				p = sc.nextInt();
				System.out.println("What is the size of the process?");
				sizeP = sc.nextInt();

				if(sizeP <= mainSize) {
					int sizeM = 0;
					int temp = mainSize;
					while(temp >=1 ) {
						if(sizeP > temp/2 && sizeP <= temp) {
							sizeM = temp;
							temp = 0;
						}
						else temp /= 2;
					}
					    memory.setProcess(p, sizeP, sizeM);
					    if(!memory.findProcess(p)) {
						System.out.println("Process rejected, no memory can be allocated...");
						int sum = 0;
						//calculate the External fragmentation by sum the memory allocated and find the rest from mainSize
						for (int i = 0; i < memory.getArr().size(); i++) {
							sum+=(memory.getArr().get(i)[2]-memory.getArr().get(i)[1]+1);

						}
						System.out.println("External fragmentation: "+(mainSize-sum));
					    }
				}
				else {
					System.out.println("Process rejected, no memory can be allocated...");
					int sum = 0;
					//calculate the External fragmentation by sum the memory allocated and find the rest from mainSize
					for (int i = 0; i < memory.getArr().size(); i++) {
						sum+=(memory.getArr().get(i)[2]-memory.getArr().get(i)[1]+1);

					}
					System.out.println("External fragmentation: "+(mainSize-sum));
				}
				
				break;
			case 2://remove process
				
				System.out.println("What is the number of the process?"); 
				p = sc.nextInt();
				if(memory.findProcess(p)) {
				memory.removeProcess(p);
				}
				else System.out.println("Process not found!");
				
				break;
			case 3://print status of memory

				memory.memoryFull();
				System.out.print("Empty: ");
				memory.memoryEmpty();
				System.out.println("Internal fragmentation: " + Node.inFragment);
				break;

			case 4://exit program
				exit = false;

				break;

			default:

			};



		}



	}

}
