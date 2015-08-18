/**
* <h1>Rumba Operation</h1>
* This class is written to extend the capabilities of Selenium to 
* automate Rumba based mainframe screens
* <p>
* Mainframe machines are often accessed through client machine programs like RUMBA, This library helps user to 
* automate various actions on Rumba screen using Java. Once this library becomes flexible, we will target this to be integrated
* with Selenium and enhance the capability of one of the greatest open source tool :)
*
* Developers/ Testers are welcome to modify/Update this code to make this better :)
*
* @author  Pragati Ranjan Patra
* @version 1.0
* @since   2015-08-18 
*/




import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;




public class RumbaOperation {


     public interface EhlApi32 extends Library {
    	 EhlApi32 EhlApi32 = (EhlApi32) Native.loadLibrary(
                (Platform.isWindows() ? "EhlApi32" : "EhlApi32"), EhlApi32.class);
            public int WD_ConnectPS(int hInstance , String ShortName);     
            public int WD_AdviseHostUpdate (int hInstance, int hInstanceMsg, int Msg);
          //  public int WD_Convert (int hInstancer, int ConvertType, RowCol As RowCol, String ShortName);  
            public int WD_CopyOIA (int hInstance, String OIAData, int Length);
            public int WD_CopyPS (int hInstance, String Buffer, int Length);
            public int WD_CopyPSToString (int hInstance, int Position, byte[] Buffer, int Length);
            public int WD_CopyStringToPS (int hInstance, int Position, String Buffer, int Length);
            public int WD_DeletePS (int hInstance, String ShortName);
            public int WD_DisconnectPS (int hInstance);
            public int WD_DisplayCursor (int hInstance, int Position, String ShortName);
            public int WD_DisplayPS (int hInstance, String ShortName);
            public int WD_FindFieldLength(int hInstance, Long Length, int Position, String FindData);
            public int WD_FindFieldPosition (int hInstance, int Location, int Position, String FindData);
            public int WD_GetKey(int hInstance, String GetKeyData);
            public int WD_GetSessionHWND(int hInstance);
            public int WD_Pause(int hInstance, int Length);
            public int WD_PostInterceptStatus(int hInstance, String ShortName);
            public int WD_QueryCursorLocation(int hInstance, int Location);
            public int WD_QueryFieldAttribute(int hInstance, int iAttribute, int Position);
            public int WD_QueryHostUpdate (int hInstance, String ShortName);
            public int WD_QuerySessionStatus(int hInstance, String SessionData);
            public int WD_QuerySystem(int hInstance, String SystemData);
            public int WD_ReceiveFile (int hInstance, String TransferData);
            public int WD_Release (int hInstance);
            public int WD_Reserve (int hInstance);
            public int WD_ResetSystem (int hInstance);
            public int WD_RunMacro (int hInstance, String Buffer);
            public int WD_RunProfile (String Profile, int SW_Value);
            public int WD_SearchField (int hInstance, int Location, int Position, String Buffer);
            public int WD_SearchPS (int hInstance, int Location, int Position, String SearchData);
            public int WD_SendFile (int hInstance, String TransferData); 
            public int WD_SendKey(int hInstance, String KeyData);
            public int WD_CopyStringToField(int hInstance, int Position, String Buffer);
            public int WD_CopyFieldToString(int hInstance , int Position, String Buffer, int Length );
            public int WD_SetCursor (int hInstance, int Position);
            public int WD_SetSessionParameters (int hInstance, Long SSPData);
            public int WD_ShowSession (int hInstance, int ShowWindow);
            public int WD_StartHostNotification (int hInstance, String NotifyData);
            public int WD_StartKSIntercept (int hInstance , String SKIData);
            public int WD_StopHostNotification (int hInstance, String ShortName);
            public int WD_StopKeystrokeIntercept (int hInstance , String ShortName);
            public int WD_Wait (int hInstance);       
            public int WD_DisconnectPS(long hInstance);
        }
    public static final EhlApi32 EhlApi32 = (EhlApi32) Native.loadLibrary("EhlApi32", EhlApi32.class);   
    

    public RumbaOperation(){
    	 EhlApi32.WD_ConnectPS(1, "A");
    }
    
    public void RumbaOperationClose(){
   	 EhlApi32.WD_DisconnectPS(1);
   }
// ===============================================================

//  Function : GetRow

// ---------------------------------------------------------------

 //  Purpose : gets row from absolute screen position*/
    
    public int GetRow(int nScreenPos) {   	
        return  Math.round((nScreenPos / 80));
    } 

    
    
    
//   ===============================================================
//
//     Function : GetColumn
//
//    ---------------------------------------------------------------
//
//    Purpose : gets Column from absolute screen position

    
    public int GetColumn(int nScreenPos){
    	return (80 - ((Math.round(nScreenPos / 80) * 80) - nScreenPos));
    }
    
    
    
//    ===============================================================
//
//     Function : GetScreenPos
//
//    ---------------------------------------------------------------
//
//     Purpose : gets absolute screen position from row and column


    public int GetScreenPos(int nRow, int nColumn){
    	return ((nRow - 1) * 80) + nColumn;
    }
    
    
    
    
    
//    ===============================================================
//
//     Function : WaitForCursor
//
//    ---------------------------------------------------------------
//
//     Purpose : Sends Keys and waits till cursor is in position
    
    public boolean WaitForCursor(String vKey, int nCursorRow, int nCursorColumn) throws InterruptedException {
    	int nLoctionTest, nCursorLocation = 0, nReturnValue;
    	   nReturnValue = EhlApi32.WD_SendKey(1, vKey);

    	   nLoctionTest = ((nCursorRow - 1) * 80) + nCursorColumn;

    	   Thread.sleep(1000);
//    	   do 
//    	   
//    	   {
//    		   (nLoctionTest = nCursorLocation) Or DateDiff("s", dteStart, Now()) > nSeconds;
    	       nReturnValue = EhlApi32.WD_QueryCursorLocation(1, nCursorLocation);
//    	   }
//    	   loop

    	   if (nLoctionTest == nCursorLocation){

    	       return true;
    	   }

    	   else{

    	       return false;
    	   }
    	   

    	
    
    }
    
    
    
   /* ===============================================================

     Function : ReadFromScreen

    ---------------------------------------------------------------

     Purpose :Scrapes screen for text based on row, column, length*/
    
    

    public String ReadFromScreen(int nLength, int nScreenRow , int nScreenColumn) {
    	byte[] buffer= new byte[nLength];
        String strBuffer="";
        int pos=0;
        int intResult;
        pos = (nScreenRow-1)*80+ nScreenColumn;
    	intResult= EhlApi32.WD_CopyPSToString(1, pos, buffer, nLength);
    	if (intResult==0){
    		for (int i = 0; i< buffer.length;i++){
            	strBuffer=strBuffer + (char)buffer[i];
            }
    		return strBuffer;
    	}
    	else{
    		return "";
    	}
    }
    
    
    

}