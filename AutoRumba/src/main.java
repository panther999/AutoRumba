
public class main {

	/**
	 * @param args
	 */

    public static void main(String[] args) throws InterruptedException {
        RumbaOperation rn = new RumbaOperation(1,"A");
        System.out.println(rn.ReadFromScreen(10, 1, 27));
        
        RumbaOperation rn2 = new RumbaOperation(1,"B");
        System.out.println(rn.ReadFromScreen(10, 6, 4));
        
        rn.RumbaOperationClose();
        rn2.RumbaOperationClose();
    }
   

}
