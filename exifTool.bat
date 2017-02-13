@echo off
echo Copy the folder path with images to examine
echo **************************
::Copy the Folder path here
set /p inputfolder= Folder :
exiftool -api validate -a -u -G1 -csv "%inputfolder%" > jpegs.csv
pause
