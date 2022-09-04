package targil1;
/*Tom Kondat 318275591
David Sharabi 315313981
 */
import java.util.ArrayList;
import java.util.Iterator;

public class Node {

	private Node L;
	private Node R;
	private final int size;
	private int rest;
	private int P;
	private Node parent;
	public static int inFragment = 0;
	private ArrayList<int[]> arr;//list of process in the node. each process represent as a array size 3 that 0 index present the number of the process and the rest are the indexes he is allocated.
	private int l;
	private int h;
	private int inFrag;

	//Constructor
	public Node(int size,Node parent,int l, int h){
		P = 0;
		this.inFrag = 0;
		this.size = size;
		this.l = l;
		this.h = h;

		if (size/2 >= 1) {//limit to 1 node 
			L = new Node(size/2,this, l, (l+h+1)/2-1 );
			R = new Node(size/2,this,(l+h+1)/2, h );
		}
		arr = new ArrayList<int[]>();

		rest = size;
		this.parent = parent;
	}

	//print the indexes of empty blocks
	public void memoryEmpty() {


		if(L != null ) {
			//if the children are empty then the this block is empty
			if(L.getArr().isEmpty()&& R.getArr().isEmpty()) {
				System.out.print(l + " - " + h + ", ");
				return;//stop go down in the tree
			}

			L.memoryEmpty();//left child
			R.memoryEmpty();//right child
		}
		else {
			//System.out.print(l + " - " + h + " ");
		}
	}

	public boolean findProcess(int P) {

		for (int i = 0; i < arr.size(); i++) {

			if( arr.get(i)[0] == P) {
				return true;
			}
		}	
		return false;
	}

	//print full blocks in the memory
	public void memoryFull() {

		//                         
		for (int i = 0; i < arr.size(); i++) {

			System.out.println("P " + arr.get(i)[0] + ", memory block: " + arr.get(i)[1] + " - "+ arr.get(i)[2]);

		}	
	}



	public void setProcess(int p,int sizeP,int sizeM ) {

		if( sizeM == this.size && arr.isEmpty() ) {
			P = p;
			setRest(sizeP);//the rest like size but not final
			inFragment += inFrag;
			int[] P = {p,l,h};
			//update of the parents arr
			setArrP(P);
			//update arr of the children
			L.setArrC(P);
			R.setArrC(P);
		}
		//go left

		else if(L!=null) {
			System.out.println(L.getRest());
			if(L.getRest() >= sizeM) {
				System.out.println("go left");
				L.setProcess(p, sizeP,sizeM);

			}

			//go right
			else if(R.getRest() >= sizeM) {
				System.out.println("go right");
				R.setProcess(p, sizeP,sizeM);
			}

		}
	}


	public void removeProcess(int P) {


		//remove from this node

		if(this.P == P) {
			this.inFragment -= this.inFrag;
			this.inFrag = 0;
			this.rest = this.size;
			setRestP(this.size);
			this.P = 0;
			//remove from arr
			for (int i = 0; i < arr.size(); i++) {
				if(arr.get(i)[0] == P)
					arr.remove(arr.get(i));
			}
			System.out.println("Process remove!");

		}
		if(L != null) {
			if(!L.getArr().isEmpty()) {
				for (int i = 0; i < L.getArr().size(); i++) {
					if(L.getArr().get(i)[0] == P) {
						arr.remove(L.getArr().get(i));//remove from this arr
						L.removeProcess(P);

					}
				}
			}

			if(!R.getArr().isEmpty()) {

				for (int i = 0; i < R.getArr().size(); i++) {
					if(R.getArr().get(i)[0] == P) {
						arr.remove(R.getArr().get(i));//remove from this arr
						R.removeProcess(P);

					}
				}
			}

		}

	}	


	public ArrayList<int[]> getArr() {
		return arr;
	}

	//set parent array list
	public void setArrP(int[] P) {

		arr.add(P);
		if(parent!= null) {
			parent.setArrP(P);
		}


	}
	//set children array list
	public void setArrC(int[] P) {

		arr.add(P);
		if(L!= null) {
			L.setArrC(P);
			R.setArrC(P);
		}

	}


	public int getRest() {
		return rest;
	}

	//update rest in setProcess
	public void setRest(int space) {
		if( space > this.size/2 && space <=this.size) {
			rest = 0;
		}
		else rest = size - space;

		if(parent!=null) {
			if(this.rest == 0)
				parent.setRest(size);
			else parent.setRest(space);
		}
		this.inFrag += this.size - space;
	}

	//update parent rest after remove
	public void setRestP(int size) {

		rest += size;
		if(parent!= null) {
			parent.setRestP(size);
		}



	}

	public Node getL() {
		return L;
	}

	public void setL(Node l) {
		L = l;
	}

	public Node getR() {
		return R;
	}

	public void setR(Node r) {
		R = r;
	}

	public int getSize() {
		return size;
	}







}
