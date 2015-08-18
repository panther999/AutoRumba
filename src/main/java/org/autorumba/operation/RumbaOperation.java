package org.autorumba.operation;

import org.autorumba.ehlapi.EhlApi32;

import com.sun.jna.Native;


/**
* <h1>Rumba Operation</h1>
* This class is written to extend the capabilities of Selenium to 
* automate Rumba based mainframe screens
* <p>
* Mainframe machines are often accessed through client machine programs like RUMBA, This library helps user to 
* automate various actions on Rumba screen using Java. Once this library becomes flexible, we will target this to be integrated
* with Selenium and enhance the capability of one of the greatest open source tool :)</p>
*
* Developers/ Testers are welcome to modify/update this code to make this better :)
*
* @author  Pragati Ranjan Patra
* @version 1.0
* @since   2015-08-18 
*/
public class RumbaOperation {

	public static final EhlApi32 ehlApi32 = (EhlApi32) Native.loadLibrary("EhlApi32", EhlApi32.class);

	public int rumbaInstance;

	public RumbaOperation(int instanceno, String instanceVal) {
		rumbaInstance = instanceno;
		ehlApi32.WD_ConnectPS(rumbaInstance, instanceVal);
	}

	public void RumbaOperationClose() {
		ehlApi32.WD_DisconnectPS(rumbaInstance);
	}
	// ===============================================================

	// Function : GetRow

	// ---------------------------------------------------------------

	// Purpose : gets row from absolute screen position*/

	public int GetRow(int nScreenPos) {
		return Math.round((nScreenPos / 80));
	}

	// ===============================================================
	//
	// Function : GetColumn
	//
	// ---------------------------------------------------------------
	//
	// Purpose : gets Column from absolute screen position

	public int GetColumn(int nScreenPos) {
		return (80 - ((Math.round(nScreenPos / 80) * 80) - nScreenPos));
	}

	// ===============================================================
	//
	// Function : GetScreenPos
	//
	// ---------------------------------------------------------------
	//
	// Purpose : gets absolute screen position from row and column

	public int GetScreenPos(int nRow, int nColumn) {
		return ((nRow - 1) * 80) + nColumn;
	}

	// ===============================================================
	//
	// Function : WaitForCursor
	//
	// ---------------------------------------------------------------
	//
	// Purpose : Sends Keys and waits till cursor is in position

	public boolean WaitForCursor(String vKey, int nCursorRow, int nCursorColumn) throws InterruptedException {
		int nLoctionTest, nCursorLocation = 0, nReturnValue;
		nReturnValue = ehlApi32.WD_SendKey(1, vKey);

		nLoctionTest = ((nCursorRow - 1) * 80) + nCursorColumn;

		Thread.sleep(1000);
		int nSeconds = 30000;
		long timenow1 = System.currentTimeMillis();

		do {
			long timenow2 = System.currentTimeMillis();
			if ((timenow2 - timenow1) == 0) {
				break;
			}
			nReturnValue = ehlApi32.WD_QueryCursorLocation(rumbaInstance, nCursorLocation);
		} while (nLoctionTest != nCursorLocation);

		if (nLoctionTest == nCursorLocation) {

			return true;
		}

		else {

			return false;
		}

	}

	/*
	 * ===============================================================
	 * 
	 * Function : ReadFromScreen
	 * 
	 * ---------------------------------------------------------------
	 * 
	 * Purpose :Scrapes screen for text based on row, column, length
	 */

	public String ReadFromScreen(int nLength, int nScreenRow, int nScreenColumn) {
		byte[] buffer = new byte[nLength];
		String strBuffer = "";
		String result = "";
		int pos = 0;
		int intResult;
		pos = (nScreenRow - 1) * 80 + nScreenColumn;
		intResult = ehlApi32.WD_CopyPSToString(rumbaInstance, pos, buffer, nLength);
		if (intResult == 0) {
			for (int i = 0; i < buffer.length; i++) {
				strBuffer = strBuffer + (char) buffer[i];
			}

			result = strBuffer;
		} else {
			result = "";
		}
		System.out.println("Read From Screen : " + result);
		return result;
	}

	/*
	 * ===============================================================
	 * 
	 * Function : WriteOnScreen
	 * 
	 * ---------------------------------------------------------------
	 * 
	 * Purpose :Write on screen for text based on row, column, length
	 */

	public boolean WriteOnScreen(int nScreenRow, int nScreenColumn, String strValue) throws InterruptedException {
		int pos = (nScreenRow - 1) * 80 + nScreenColumn;

		int intResult = ehlApi32.WD_CopyStringToField(rumbaInstance, pos, strValue);

		System.out.println(
				"Write on screen: " + strValue + " at " + nScreenRow + "," + nScreenColumn + ". Status : " + intResult);

		if (intResult == 0) {
			return true;
		} else {
			System.out.println("Error while writing on screen : " + intResult);

			/*
			 * 0 The function was successful 1 An incorrect PSID was specified 8
			 * No prior call to Start Communication Notification (80) function
			 * was called for the PSID 9 A system error was encountered
			 */

			return false;
		}

	}

	/*
	 * ==================================================================
	 * Function : GetHWNDId
	 * 
	 * -------------------------------------------------- Purpose : Get
	 * WindowHandler of the opened Rumba instance
	 */

	public int GetWindowHandlerId() {
		return ehlApi32.WD_GetSessionHWND(this.rumbaInstance);
	}

	/*
	 * ==================================================================
	 * Function : SendKey
	 * 
	 * -------------------------------------------------- Purpose : SendKey to
	 * the opened Rumba instance
	 * 
	 */
	public int SendKey(String keydata) throws InterruptedException {
		ehlApi32.WD_SendKey(this.rumbaInstance, keydata);
		Thread.sleep(1000);
		return 1;
		/*
		 * String Constants for SendKeys ############# Key list ################
		 * Meaning Mnemonic PA1 @x PA2 @y PA3 @z PA4 @+ ENTER @E CURSOR_DOWN @V
		 * CURSOR_UP @U PAGE_DOWN @v PAGE_UP @u
		 * 
		 * @ @@ Alt @A Alternate Cursor @$ Attention @A@Q Backspace @< Backtab
		 * (Left Tab) @B Clear @C Cmd Function Key @A@Y Cursor Down @V Cursor
		 * Left @L Cursor Right @Z Cursor Select @A@J Cursor Up @U Delete @D
		 * Dup @S@x End @q Enter @E Erase EOF @F Erase Input @A@F Field
		 * Exit @A@E Field Mark @S@y Field - @A@- Field + @A@+ Help @H
		 * Hexadecimal @A@X Home @0 (zero) Insert @I Insert Toggle @A@I Host
		 * Print @P Left Tab(Back Tab) @B New Line @N Page Up @u Page Down @v
		 * Print (PC) @A@t Record Backspace @A@< Reset @R Right Tab (Tab) @T
		 * Shift @S Sys Request @A@H Tab (Right Tab) @T Test @A@C PA1 @x PA2 @y
		 * PA3 @z PA4 @+ PA5 @% PA6 @& PA7 @ PA8 @( PA9 @) PA10 @* PF1/F1 @1
		 * PF2/F2 @2 PF3/F3 @3 PF4/F4 @4 PF5/F5 @5 PF6/F6 @6 PF7/F7 @7 PF8/F8 @8
		 * PF9/F9 @9 PF10/F10 @a PF11/F11 @b PF12/F12 @c PF13 @d PF14 @e PF15 @f
		 * PF16 @g PF17 @h PF18 @i PF19 @j PF20 @k PF21 @l PF22 @m PF23 @n
		 * PF24 @o
		 */

	}

	/*
	 * ==================================================================
	 * Function : SearchStringInField
	 * 
	 * -------------------------------------------------- Purpose : Search for
	 * the string on a particular field of an opened Rumba instance
	 */

	public int SearchStringInField(int nScreenRow, int nScreenColumn, String strValue) {
		int slLocation = 0;
		int pos = (nScreenRow - 1) * 80 + nScreenColumn;
		int intResult = ehlApi32.WD_SearchField(this.rumbaInstance, slLocation, pos, strValue);
		System.out.println(intResult);
		if (intResult == 0) {
			return slLocation;
		} else {
			System.out.println("Failed to find passed string ");
			return 0;
		}
	}

	/*
	 * ==================================================================
	 * Function : FindFieldPosition
	 * 
	 * -------------------------------------------------- Purpose : Search for
	 * the string on a particular field of an opened Rumba instance
	 */
	public int FindFieldPosition(int nScreenRow, int nScreenColumn, String szCode) {
		/*
		 * Content Description ^^ or T^ This field. N^ Next field (protected or
		 * unprotected). NP Next protected field. NU Next unprotected field. P^
		 * Previous field (protected or unprotected) PP Previous protected
		 * field. PU Previous unprotected field.
		 */
		szCode = "NU";
		int sLoc = 0;
		int pos = (nScreenRow - 1) * 80 + nScreenColumn;
		int intResult = ehlApi32.WD_FindFieldPosition(this.rumbaInstance, sLoc, pos, szCode);
		return sLoc;
	}
}