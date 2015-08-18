package org.autorumba.ehlapi;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public interface EhlApi32 extends Library {
	
	public EhlApi32 ehlApi32 = (EhlApi32) Native.loadLibrary((Platform.isWindows() ? "EhlApi32" : "EhlApi32"),
			EhlApi32.class);

	public int WD_ConnectPS(int hInstance, String ShortName);

	public int WD_AdviseHostUpdate(int hInstance, int hInstanceMsg, int Msg);

	// public int WD_Convert (int hInstancer, int ConvertType, RowCol As
	// RowCol, String ShortName);
	public int WD_CopyOIA(int hInstance, String OIAData, int Length);

	public int WD_CopyPS(int hInstance, String Buffer, int Length);

	public int WD_CopyPSToString(int hInstance, int Position, byte[] Buffer, int Length);

	public int WD_CopyStringToPS(int hInstance, int Position, String Buffer, int Length);

	public int WD_DeletePS(int hInstance, String ShortName);

	public int WD_DisconnectPS(int hInstance);

	public int WD_DisconnectPS(long hInstance);

	public int WD_DisplayCursor(int hInstance, int Position, String ShortName);

	public int WD_DisplayPS(int hInstance, String ShortName);

	public int WD_FindFieldLength(int hInstance, Long Length, int Position, String FindData);

	public int WD_FindFieldPosition(int hInstance, int Location, int Position, String FindData);

	public int WD_GetKey(int hInstance, String GetKeyData);

	public int WD_GetSessionHWND(int hInstance);

	public int WD_Pause(int hInstance, int Length);

	public int WD_PostInterceptStatus(int hInstance, String ShortName);

	public int WD_QueryCursorLocation(int hInstance, int Location);

	public int WD_QueryFieldAttribute(int hInstance, int iAttribute, int Position);

	public int WD_QueryHostUpdate(int hInstance, String ShortName);

	public int WD_QuerySessionStatus(int hInstance, String SessionData);

	public int WD_QuerySystem(int hInstance, String SystemData);

	public int WD_ReceiveFile(int hInstance, String TransferData);

	public int WD_Release(int hInstance);

	public int WD_Reserve(int hInstance);

	public int WD_ResetSystem(int hInstance);

	public int WD_RunMacro(int hInstance, String Buffer);

	public int WD_RunProfile(String Profile, int SW_Value);

	public int WD_SearchField(int hInstance, int Location, int Position, String Buffer);

	public int WD_SearchPS(int hInstance, int Location, int Position, String SearchData);

	public int WD_SendFile(int hInstance, String TransferData);

	public int WD_SendKey(int hInstance, String KeyData);

	public int WD_CopyStringToField(int hInstance, int Position, String Buffer);

	public int WD_CopyFieldToString(int hInstance, int Position, String Buffer, int Length);

	public int WD_SetCursor(int hInstance, int Position);

	public int WD_SetSessionParameters(int hInstance, Long SSPData);

	public int WD_ShowSession(int hInstance, int ShowWindow);

	public int WD_StartHostNotification(int hInstance, String NotifyData);

	public int WD_StartKSIntercept(int hInstance, String SKIData);

	public int WD_StopHostNotification(int hInstance, String ShortName);

	public int WD_StopKeystrokeIntercept(int hInstance, String ShortName);

	public int WD_Wait(int hInstance);

}
