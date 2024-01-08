@ECHO ON
start  cmd /c "cd /d c:\DataImport & Start DataDownload.exe -TYPEID 0 -JOBID %1 -FILESYSTEMID %2 -LOG
PAUSE 
::cmd.exe   cmd /c "cd /d c:\temp && dir" Start DataDownload.exe  
:End