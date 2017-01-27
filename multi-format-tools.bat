@echo off
:: multi-format-tools.bat has to be in the same folder as the used Software:
:: sf.exe; trid.exe and file.exe
:: Furthermore, all libraries have to be there, too like triddefs.trd; zlib1.dll; magic1.dll and rege2.dll
:: To use File, the lib folder also has to be where it is expected, that's why I run this program from the Lib-folder of File
echo Ordner angeben, in denen die Dateien liegen
echo **************************
set /p inputfolder= Ordner :
for /r "%inputfolder%\" %%X in (*.*) do (
                echo new File >> neuerTest.txt
				echo Siegfried Findings >> neuerTest.txt         
				sf "%%X" >> neuerTest.txt  				
				echo TRId Findings >> neuerTest.txt     				
				trid "%%X" >> neuerTest.txt				
				echo File Findings >> neuerTest.txt     				
				file "%%X" >> neuerTest.txt								
				)
Pause
