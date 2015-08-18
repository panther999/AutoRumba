
public class main {

	/**
	 * @param args
	 */

    public static void main(String[] args) throws InterruptedException {
        RumbaOperation rn = new RumbaOperation();
        System.out.println(rn.ReadFromScreen(10, 1, 27));
        
        
        
        rn.RumbaOperationClose();
    }
   /*  0  The function was successful  
       1  An incorrect PSID was specified  
       8  No prior call to Start Communication Notification (80) function was called for the PSID  
       9  A system error was encountered  
   */

}
