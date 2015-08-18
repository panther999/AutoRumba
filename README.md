# AutoRumba

Java Library to automate Rumba mainframe screen.
Mainframe machines are often accessed through client machine programs like RUMBA, this library helps user to automate various actions on Rumba screen using Java.

**Initial Finding :**

I have worked in QTP for quite a while, While trying to understand how QTP works with Terminal Emulator, I discovered that 
QTP interacts with a dll  file "EHLApi32.dll", which is present in installation folder of the RUMBA
generally in "C:\Program Files (x86)\NetManage\System". This dll file is written in some native language ( may be C++),
There are lot of free tools present which can drill through dlls and can show the functions inside those. I used "depends.exe"
to find the functions listed there.
Once functions were known, I used JNI to connect with dll through java code, and used those native functions to do automate my
tasks. I am still working on finding how we can use all the native functions listed in dll.

Some of the functions present inside dll are :

- WD_ConnectPS
- WD_DisconnectPS
- WD_SendKey
- WD_Wait
- WD_CopyPS
- WD_SearchPS
- WD_QueryCursorLocation
- WD_CopyPSToString
- WD_SetSessionParamEx
- WD_QuerySessions
- WD_Reserve
- WD_Release
- WD_CopyOIA
- WD_QueryFieldAttribute
- WD_CopyStringToPS
- WD_Pause
- WD_QuerySystem
- WD_ResetSystem
- WD_QuerySessionStatus
- WD_StartHostNotification
- WD_QueryHostUpdate
- WD_StopHostNotification
- WD_SearchField
- WD_FindFieldPosition
- WD_FindFieldLength
- WD_CopyStringToField
- WD_CopyFieldToString
- WD_DeletePS
- WD_SetCursor
- WD_StartKSIntercept
- WD_GetKey
- WD_PostInterceptStatus
- WD_StopKSIntercept
- WD_SendFile
- WD_ReceiveFile
- WD_Convert
- WD_ConnectWindowServices
- WD_DisconnectWindowServices
- WD_QueryWindowCoordinates
- WD_WindowStatus
- WD_ChangeWindowName
- WD_RunProfile
- WD_ShowSession


My Target:
To create a class containing underdefined functions which will help automation testers to test RUMBA based screen using JavaCode.
