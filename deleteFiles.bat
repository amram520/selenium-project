@echo off
set extentReportsPath=C:\Users\Daniel A\Desktop\seleniumProject\src\test\resources\ExtentReports
set videosPath=C:\Users\Daniel A\Desktop\seleniumProject\src\test\resources\videos
set /p folderPath="enter which folder you want to delete videos or extentReports: "
if %folderPath%==videos (
ForFiles /p "%videosPath%" /s /d -0 /c "cmd /c del /q @file"
)else (
if %folderPath%==extentReports (
ForFiles /p "%extentReportsPath%" /s /d -0 /c "cmd /c del /q @file"
)else (
echo "you enterd an invalid value"
)
)
pause
